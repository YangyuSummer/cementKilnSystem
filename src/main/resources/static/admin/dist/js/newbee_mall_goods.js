$(function () {
    $("#jqGrid").jqGrid({
        url: '/sss',
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

function jqgridAddGoods() {
    window.location.href = "/admin/jqgridToAdd";
}

function editGoods(){
    var wasteID = $("#jqGrid").jqGrid('getGridParam', 'selrow');

    window.location.href = "/admin/toEditWaste?wasteID="+wasteID;
}
/**
 * var page = $("#jqGrid").jqGrid('getGridParam', 'page');
 * 这个语可以获取当前表格page参数
 * var id = $("#jqGrid").jqGrid('getGridParam', 'selrow');
 * 这个可以获取选中行的ID,如果多选，就是最后一个id
 */

function deleteGoods(){

    var wasteID = $("#jqGrid").jqGrid('getGridParam', 'selrow');

    window.location.href = "/admin/jqDeleteWaste?wasteID="+wasteID;

}

function testGoods(){

    var id = $("#jqGrid").jqGrid('getGridParam', 'selrow');

    var ids = $("#jqGrid").jqGrid('getGridParam', 'selarrrow');

    var rowData = $("#jqGrid").jqGrid('getRowData', id);

    var rowName = rowData.wasteName;

    alert(rowName);


}