package com.wang.core;

import com.wang.entity.Repository;
import com.wang.service.RepositoryService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator 生成excel
 */
public class planexcel {
    @Resource
    private RepositoryService repositoryService;
    private Repository waste;
    private String PATH;
    private List<Repository> plan=new ArrayList<>() ;
    private double allheat = 0.0;
    private double allweight = 0.0;
    private double allS = 0.0;
    private double allP = 0.0;
    private double allho2 = 0.0;
    private double allC = 0.0;
    public Workbook wb;


    public planexcel(List<Repository> list) throws Exception {
        this.plan=list;
        String[] wasteID=new String[plan.size()];
        Double[] mass =new Double[plan.size()];

        int i =0;


        wb = new HSSFWorkbook();
//创建工作表
        Sheet sheet = wb.createSheet("配伍方案");

//创建行列
        Row Row1 = sheet.createRow(0);
        Cell cell11 = Row1.createCell(0);
        cell11.setCellValue("方案");
        Cell cell12 = Row1.createCell(1);
        cell12.setCellValue("ID");



        Row Row8 = sheet.createRow(8);
        Cell cell81 = Row8.createCell(0);
        cell81.setCellValue("废弃物编号");
        Cell cell82 = Row8.createCell(1);
        cell82.setCellValue("废弃物名称");
        Cell cell83 = Row8.createCell(2);
        cell83.setCellValue("来源");
        Cell cell84 = Row8.createCell(3);
        cell84.setCellValue("入库时间");
        Cell cell85 = Row8.createCell(4);
        cell85.setCellValue("处理设备");
        Cell cell86 = Row8.createCell(5);
        cell86.setCellValue("处理量");
        Cell cell87 = Row8.createCell(6);
        cell87.setCellValue("热量");
        Cell cell88 = Row8.createCell(7);
        cell88.setCellValue("C");
        Cell cell89 = Row8.createCell(8);
        cell89.setCellValue("P");
        Cell cell810 = Row8.createCell(9);
        cell810.setCellValue("S");
        Cell cell811 = Row8.createCell(10);
        cell811.setCellValue("水");
//        System.out.println("plan");
        Row x;
        Cell cell;

        for (i=0;i<plan.size();i++){
            waste = plan.get(i);
            x = sheet.createRow(9 + i);
            cell = x.createCell(0);
            cell.setCellValue(waste.getWasteID());
            cell = x.createCell(1);
            cell.setCellValue(waste.getWasteName());
            cell = x.createCell(2);
            cell.setCellValue(waste.getResourceCompany());
            cell = x.createCell(3);
            cell.setCellValue(waste.getRepoTime());
            cell = x.createCell(4);
            cell.setCellValue(waste.getSolveEquipment());
            cell = x.createCell(5);
            cell.setCellValue(waste.getWeight());
            cell = x.createCell(6);
            cell.setCellValue(waste.getHeatValue());
            cell = x.createCell(7);
            cell.setCellValue(waste.getC());
            cell = x.createCell(8);
            cell.setCellValue(waste.getP());
            cell = x.createCell(9);
            cell.setCellValue(waste.getS());
            cell = x.createCell(10);
            cell.setCellValue(waste.getHo2());
            allheat+=waste.getHeatValue();
            allC+=waste.getC();
            allP+=waste.getP();
            allS+=waste.getS();
            allho2+=waste.getHo2();
            allweight+=waste.getWeight();
        }

//        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "plan.xls");
//        wb.write(fileOutputStream);
//        fileOutputStream.close();
//        System.out.println("完毕");
        Row Row2 = sheet.createRow(1);
        Cell cell21 = Row2.createCell(0);
        cell21.setCellValue("总重量");
        Cell cell22 = Row2.createCell(1);
        cell22.setCellValue(allweight + "kg");

        Row Row3 = sheet.createRow(2);
        Cell cell31 = Row3.createCell(0);
        cell31.setCellValue("总热量");
        Cell cell32 = Row3.createCell(1);
        cell32.setCellValue(allheat);

        Row Row4 = sheet.createRow(3);
        Cell cell41 = Row4.createCell(0);
        cell41.setCellValue("含C量");
        Cell cell42 = Row4.createCell(1);
        cell42.setCellValue(allC + "%");

        Row Row5 = sheet.createRow(4);
        Cell cell51 = Row5.createCell(0);
        cell51.setCellValue("含S量");
        Cell cell52 = Row5.createCell(1);
        cell52.setCellValue(allS + "%");

        Row Row6 = sheet.createRow(5);
        Cell cell61 = Row6.createCell(0);
        cell61.setCellValue("含P量");
        Cell cell62 = Row6.createCell(1);
        cell62.setCellValue(allP + "%");

        Row Row7 = sheet.createRow(6);
        Cell cell71 = Row7.createCell(0);
        cell71.setCellValue("含水量");
        Cell cell72 = Row7.createCell(1);
        cell72.setCellValue(allho2 + "%");


          System.out.println("wb");
    }
    public  Workbook output(){

        return wb;
    }


}
