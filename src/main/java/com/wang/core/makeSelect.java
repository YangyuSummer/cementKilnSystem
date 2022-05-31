package com.wang.core;

import com.wang.entity.Repository;
import com.wang.service.RepositoryService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Administrator 生成方案
 */

public class makeSelect {
    @Resource
    private RepositoryService repositoryService;
    private Double heatlow;
    private Double heathigh;
    private String planID;
    private String equipment;
    private String planIntro;
    private String PATH;
    private List<Repository> material;
    private List<Repository> materialSelect;
    Map<String, Double> plans = new HashMap<>();

    public makeSelect(String planID, double heathigh, double heatlow, String planIntro, List<Repository> material,List<Repository> materialSelect,double smaxContain,double pmaxContain,double ho2maxContain,double cmaxContain )  {
        this.planID=planID;
        this.equipment=equipment;
        this.heathigh=heathigh;
        this.heatlow=heatlow;
        this.planIntro=planIntro;
        this.material=material;
        this.materialSelect=materialSelect;
//        库存物料
        HashMap<String, String> originMaterialListC = new HashMap<String, String>();
        HashMap<String, String> originMaterialListP = new HashMap<String, String>();
        HashMap<String, String> originMaterialListS = new HashMap<String, String>();
        HashMap<String, String> originMaterialListho2 = new HashMap<String, String>();
        HashMap<String, String> originMaterialListweight = new HashMap<String, String>();
        HashMap<String, String> originMaterialListheatValue = new HashMap<String, String>();
//        选择物料
        HashMap<String, String> selectMaterialListC = new HashMap<String, String>();
        HashMap<String, String> selectMaterialListP = new HashMap<String, String>();
        HashMap<String, String> selectMaterialListS = new HashMap<String, String>();
        HashMap<String, String> selectMaterialListho2 = new HashMap<String, String>();
        HashMap<String, String> selectMaterialListweight = new HashMap<String, String>();
        HashMap<String, String> selectMaterialListheatValue = new HashMap<String, String>();

//        List<Repository> material = repositoryService.queryWasteByEquipment(equipment);
//        库存物料数值
        String wasteID[] = new String[material.size()];

        int i = 0;
        for (Repository repository : material) {
            wasteID[i] = repository.getWasteID();
            i++;
            originMaterialListC.put(repository.getWasteID(), String.valueOf(repository.getC()));
            originMaterialListP.put(repository.getWasteID(), String.valueOf(repository.getP()));
            originMaterialListS.put(repository.getWasteID(), String.valueOf(repository.getS()));
            originMaterialListho2.put(repository.getWasteID(), String.valueOf(repository.getHo2()));
            originMaterialListweight.put(repository.getWasteID(), String.valueOf(repository.getWeight()));
            originMaterialListheatValue.put(repository.getWasteID(), String.valueOf(1.0 * repository.getHeatValue() / repository.getWeight()));
        }
//        选择物料数值
        i=0;
        String selectID[] = new String[materialSelect.size()];
        for (Repository repository : materialSelect) {
            selectID[i] = repository.getWasteID();
            i++;
            selectMaterialListC.put(repository.getWasteID(), String.valueOf(repository.getC()*repository.getWeight()));
            selectMaterialListP.put(repository.getWasteID(), String.valueOf(repository.getP()*repository.getP()));
            selectMaterialListS.put(repository.getWasteID(), String.valueOf(repository.getS()*repository.getS()));
            selectMaterialListho2.put(repository.getWasteID(), String.valueOf(repository.getHo2()*repository.getHo2()));
            selectMaterialListweight.put(repository.getWasteID(), String.valueOf(repository.getWeight()));
            selectMaterialListheatValue.put(repository.getWasteID(), String.valueOf(1.0 * repository.getHeatValue() / repository.getWeight()));
        }





        //系数矩阵
        double massC = cmaxContain;
        double massP = pmaxContain;
        double massS = smaxContain;
        double massho2 = ho2maxContain;
//        heatlow = 1000;
//        heathigh = 10000;
        double aa[][] = new double[material.size() + 6][material.size() + 1];
        //质量
        for (int row = 0; row < material.size(); row++) {
            for (i = 0; i < material.size(); i++) {
                if (i == row) {
                    aa[row][i] = 1;
                } else {
                    aa[row][i] = 0;
                }
            }
        }
        int row = 0;
        //质量
        i = 0;
        double all = 0;
        Set<String> setweight = originMaterialListweight.keySet();
        Iterator<String> weight = setweight.iterator();
        while (weight.hasNext()) {
            String key = weight.next();
            String value = originMaterialListweight.get(key);
            aa[i][material.size()] = Double.parseDouble(value);
            all += Double.parseDouble(value);
            i++;
        }
//         选择物料总质量
        double selectAll = 0;
        Set<String> setSelectweight = selectMaterialListweight.keySet();
        Iterator<String> selectWeight = setSelectweight.iterator();
        while (selectWeight.hasNext()) {
            String key = selectWeight.next();
            String value = selectMaterialListweight.get(key);
            selectAll += Double.parseDouble(value);
        }



//        System.out.println(aa[3][1]);
        row = material.size();//C
        i = 0;
        Set<String> setC = originMaterialListC.keySet();
        Iterator<String> C = setC.iterator();
        while (C.hasNext()) {
            String key = C.next();
            String value = originMaterialListC.get(key);
            aa[row][i] = Double.parseDouble(value);
            i++;
        }

        double selectCmass = 0;
        i = 0;
        Set<String> setSeletC = selectMaterialListC.keySet();
        Iterator<String> SeletC = setSeletC.iterator();
        while (SeletC.hasNext()) {
            String key = SeletC.next();
            String value = selectMaterialListC.get(key);
            selectCmass = Double.parseDouble(value);
        }
        double v = all + selectAll;
        aa[row][material.size()] = massC *v-selectCmass;


        i = 0;
        row = material.size() + 1;//P
        Set<String> setP = originMaterialListP.keySet();
        Iterator<String> P = setP.iterator();
        while (P.hasNext()) {

            String key = P.next();
            String value = originMaterialListP.get(key);
            aa[row][i] = Double.parseDouble(value);
//            System.out.println(key + ":" + aa[row][i]);
            i++;
        }
        double selectPmass = 0;
        i = 0;
        Set<String> setSeletP = selectMaterialListP.keySet();
        Iterator<String> SeletP = setSeletP.iterator();
        while (SeletP.hasNext()) {
            String key = SeletP.next();
            String value = selectMaterialListP.get(key);
            selectPmass = Double.parseDouble(value);
        }
        aa[row][material.size()] = massP *v-selectPmass;




        row = material.size() + 2;//S
        i = 0;
        Set<String> setS = originMaterialListS.keySet();
        Iterator<String> S = setP.iterator();
        while (S.hasNext()) {

            String key = S.next();
            String value = originMaterialListS.get(key);
            aa[row][i] = Double.parseDouble(value);
//            System.out.println(key + ":" + aa[row][i]);
            i++;
        }
        double selectSmass = 0;
        i = 0;
        Set<String> setSeletS = selectMaterialListS.keySet();
        Iterator<String> SeletS = setSeletS.iterator();
        while (SeletS.hasNext()) {
            String key = SeletS.next();
            String value = selectMaterialListS.get(key);
            selectSmass = Double.parseDouble(value);
        }
        aa[row][material.size()] = massS *v-selectSmass;


        row = material.size() + 3;//ho2
        i = 0;
        Set<String> setho2 = originMaterialListho2.keySet();
        Iterator<String> ho2 = setho2.iterator();
        while (ho2.hasNext()) {

            String key = ho2.next();
            String value = originMaterialListho2.get(key);
            aa[row][i] = Double.parseDouble(value);
//            System.out.println(key + ":" + aa[row][i]);
            i++;
        }


        double selectho2mass = 0;
        i = 0;
        Set<String> setSeletho2 = selectMaterialListho2.keySet();
        Iterator<String> Seletho2 = setSeletho2.iterator();
        while (Seletho2.hasNext()) {
            String key = Seletho2.next();
            String value = selectMaterialListho2.get(key);
            selectho2mass = Double.parseDouble(value);
        }
        aa[row][material.size()] = massho2 *v-selectho2mass;


        //常数

        //热值

        row = material.size() + 4;
        i = 0;
        Set<String> setheat = originMaterialListheatValue.keySet();
        Iterator<String> heat = setheat.iterator();
        while (heat.hasNext()) {
            String key = heat.next();
            String value = originMaterialListheatValue.get(key);
            aa[row][i] = Double.parseDouble(value);
            aa[row + 1][i] = Double.parseDouble(value);
//            System.out.println( i+ ":" + aa[row][i]);
            i++;
        }

        double selectHeat= 0;
        i = 0;
        Set<String> setSelectheat = selectMaterialListheatValue.keySet();
        Iterator<String> Selectheat =setSelectheat.iterator();
        while ( Selectheat .hasNext()) {
            String key =  Selectheat .next();
            String value = selectMaterialListheatValue.get(key);
            selectHeat = Double.parseDouble(value);
        }
        aa[row][material.size()] = heathigh-selectHeat;
        row = material.size() + 5;
        if(heatlow>selectHeat){
            aa[row][material.size()] = heatlow-selectHeat;
        }else {
            aa[row][material.size()] = 0;
        }



        double xx[] = new double[material.size()];// 目标函数  物料个数+目标变量
        for (int j = 0; j < material.size(); j++) {
            xx[j] = 1;
        }
        int totalcondition = material.size() + 6;
        int totalx = material.size();
        double allheat = 0.0;
        double allweight = 0.0;
        double allS = 0.0;
        double allP = 0.0;
        double allho2 = 0.0;
        double allC = 0.0;

        LinearProgram lp = new LinearProgram(1, totalcondition, totalx, material.size() + 5, 0, 1, aa, xx);
        Map<String, Map<String, Double>> outObject0 = new HashMap<String, Map<String, Double>>();
        Map<String, Double> solve = new HashMap<>();
        outObject0 = lp.solve1();
        double bb[] = new double[material.size()];
        if (outObject0.containsKey("可行方案")) {
            Set<String> setfangan = outObject0.keySet();
            Iterator<String> fangan = setfangan.iterator();
            while (fangan.hasNext()) {
                String key = fangan.next();
                solve = outObject0.get(key);
            }
            int b = 0;
            Set<String> setmethod = solve.keySet();
            Iterator<String> method = setmethod.iterator();
            while (method.hasNext()) {

                String key = method.next();
                double shuzhi = solve.get(key);
                bb[b] = shuzhi;
                System.out.println(b + ":" + bb[b]);
                allweight+=bb[b];
                b++;
            }
        }


        for (int k = 0; k < material.size(); k++) {
            allheat += aa[material.size() + 5][k] * bb[k];
            allC += aa[material.size()][k] * bb[k] / allweight;
            allP += aa[material.size() + 1][k] * bb[k] / allweight;
            allS += aa[material.size() + 2][k] * bb[k] / allweight;
            allho2 += aa[material.size() + 3][k] * bb[k] / allweight;
        }
        for (i = 0; i < material.size(); i++)
            plans.put(wasteID[i], bb[i]);
        System.out.println(plans);




//        Workbook wb = new HSSFWorkbook();
////创建工作表
//        Sheet sheet = wb.createSheet("配伍方案");
//
////创建行列
//        Row Row1 = sheet.createRow(0);
//        Cell cell11 = Row1.createCell(0);
//        cell11.setCellValue("方案");
//        Cell cell12 = Row1.createCell(1);
//        cell12.setCellValue("ID");
//
//        Row Row2 = sheet.createRow(1);
//        Cell cell21 = Row2.createCell(0);
//        cell21.setCellValue("总重量");
//        Cell cell22 = Row2.createCell(1);
//        cell22.setCellValue(allweight + "kg");
//
//        Row Row3 = sheet.createRow(2);
//        Cell cell31 = Row3.createCell(0);
//        cell31.setCellValue("总热量");
//        Cell cell32 = Row3.createCell(1);
//        cell32.setCellValue(allheat);
//
//        Row Row4 = sheet.createRow(3);
//        Cell cell41 = Row4.createCell(0);
//        cell41.setCellValue("含C量");
//        Cell cell42 = Row4.createCell(1);
//        cell42.setCellValue(allC + "%");
//
//        Row Row5 = sheet.createRow(4);
//        Cell cell51 = Row5.createCell(0);
//        cell51.setCellValue("含S量");
//        Cell cell52 = Row5.createCell(1);
//        cell52.setCellValue(allS + "%");
//
//        Row Row6 = sheet.createRow(5);
//        Cell cell61 = Row6.createCell(0);
//        cell61.setCellValue("含P量");
//        Cell cell62 = Row6.createCell(1);
//        cell62.setCellValue(allP + "%");
//
//        Row Row7 = sheet.createRow(6);
//        Cell cell71 = Row7.createCell(0);
//        cell71.setCellValue("含水量");
//        Cell cell72 = Row7.createCell(1);
//        cell72.setCellValue(allho2 + "%");
//
//        Row Row8 = sheet.createRow(8);
//        Cell cell81 = Row8.createCell(0);
//        cell81.setCellValue("废弃物编号");
//        Cell cell82 = Row8.createCell(1);
//        cell82.setCellValue("废弃物名称");
//        Cell cell83 = Row8.createCell(2);
//        cell83.setCellValue("来源");
//        Cell cell84 = Row8.createCell(3);
//        cell84.setCellValue("入库时间");
//        Cell cell85 = Row8.createCell(4);
//        cell85.setCellValue("处理设备");
//        Cell cell86 = Row8.createCell(5);
//        cell86.setCellValue("处理量");
//        Cell cell87 = Row8.createCell(6);
//        cell87.setCellValue("热量");
//        Cell cell88 = Row8.createCell(7);
//        cell88.setCellValue("C");
//        Cell cell89 = Row8.createCell(8);
//        cell89.setCellValue("P");
//        Cell cell810 = Row8.createCell(9);
//        cell810.setCellValue("S");
//        Cell cell811 = Row8.createCell(10);
//        cell811.setCellValue("水");
//
//        Repository repository;
//        Row x;
//        Cell cell;
//        for (i = 0; i < material.size(); i++) {
//            repository = material.get(i);
//            x = sheet.createRow(9 + i);
//            cell = x.createCell(0);
//            cell.setCellValue(repository.getWasteID());
//            cell = x.createCell(1);
//            cell.setCellValue(repository.getWasteName());
//            cell = x.createCell(2);
//            cell.setCellValue(repository.getResourceCompany());
//            cell = x.createCell(3);
//            cell.setCellValue(repository.getRepoTime());
//            cell = x.createCell(4);
//            cell.setCellValue(repository.getSolveEquipment());
//            cell = x.createCell(5);
//            cell.setCellValue(bb[i]);
//            cell = x.createCell(6);
//            cell.setCellValue(1.0*repository.getHeatValue()/repository.getHeatValue()*bb[i]);
//            cell = x.createCell(7);
//            cell.setCellValue(repository.getC());
//            cell = x.createCell(8);
//            cell.setCellValue(repository.getP());
//            cell = x.createCell(9);
//            cell.setCellValue(repository.getS());
//            cell = x.createCell(10);
//            cell.setCellValue(repository.getHo2());
//
//        }
//        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "plan.xls");
//        wb.write(fileOutputStream);
//        fileOutputStream.close();
//        System.out.println("完毕");
    }
    public Map<String,Double> plan() {
        return plans;
    }
}