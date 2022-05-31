$(function () {
    $("#jqGrid").jqGrid({
        url: '/ppp',
        datatype: "json",
        colModel: [
            {label: '废料编号', name: "wasteID", index: 'wasteID',sortable:false,width: 10, key: true},
            {label: '废料名称', name: 'wasteName', index: 'wasteName',sortable:false, width: 10},
            {label: '来源公司', name: 'resourceCompany', index: 'resourceCompany', sortable:false,width: 20},
            {label: '入库时间', name: 'repoTime', index: 'repoTime',sortable:false, width: 10, },
            {label: '处理设备', name: 'solveEquipment', index: 'solveEquipment', sortable:false,width: 15},
            {label: '重量', name: 'weight', index: 'weight',sortable:false, width: 10},
            {label: '热值', name: 'heatValue', index: 'heatValue',sortable:false, width: 10},
            {label: '硫含量', name: 's', index: 's', sortable:false,width: 10},
            {label: '磷含量', name: 'p', index: 'p', sortable:false,width: 10},
            {label: '含水量', name: 'ho2', index: 'ho2',sortable:false, width: 10},
            {label: '碳含量', name: 'c', index: 'c', sortable:false,width: 10},
        ],
        height: 760,
        rowNum: 20,
        rowList: [2,5,8],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

});

function reload() {
    initFlatPickr();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function chooseMaker(){

    // var planID = document.getElementById("planID").value;
    // var equipment =document.getElementById("equipment").value;
    // var heatValueLow =document.getElementById("heatValueLow").value;
    // var heatValueHigh =document.getElementById("heatValueHigh").value;
    // var planIntro = document.getElementById("planIntro").value;
    //
    // window.location.href = "/admin/autoJqMakerr?planID="+planID+"&equipment="+equipment+"&heatValueLow="+heatValueLow
    //     +"&heatValueHigh="+heatValueHigh+"&planIntro="+planIntro;

    var wasteIDs = $("#jqGrid").jqGrid('getGridParam', 'selarrrow');

    var planID = document.getElementById("planID").value;
    var equipment =document.getElementById("equipment").value;
    var heatValueLow =document.getElementById("heatValueLow").value;
    var heatValueHigh =document.getElementById("heatValueHigh").value;
    var planIntro = document.getElementById("planIntro").value;

    window.location.href = "/admin/chooseMakerr?wasteIDs="+wasteIDs+"&planID="+planID+"&equipment="+equipment+"&heatValueLow="+heatValueLow+"&heatValueHigh="+heatValueHigh+"&planIntro="+planIntro;
}