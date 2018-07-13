/**
 * Created by Administrator on 2017/9/21.
 */
window.ticcEasyUiFrame = {
    /**
     * 数据表格
     * @param title
     * @param elementId
     * @param controllerRequestPath
     * @param idField
     * @param singleSelect
     * @param border
     * @param pagination
     * @param columns
     * @param toolbar
     */
    intiDatagrid: function (title,elementId,controllerRequestPath,idField,singleSelect,border,
                            pagination,columns,toolbar) {
        $(elementId).datagrid({
            // url: controllerRequestPath+'/list.action?time=' + new Date().getTime(),
            url: controllerRequestPath+'/list?time=' + new Date().getTime(),
            type: 'POST',
            title: title,
            idField:idField,
            singleSelect : singleSelect,
            striped: true,
            fitColumns: true,
            fit: true,
            border:border,
            rownumbers: true,
            pagination : pagination,
            pageNumber : 1,
            pageSize : 15,
            pageList : [10,15,20,30,40,50],
            columns : columns,
            onLoadError: function () {
                ticc.alertError('系统繁忙,请稍后再试');
            },
            onLoadSuccess: function (data) {
                $(this).datagrid('clearSelections');
                $(this).datagrid('clearChecked');
            },
            toolbar:toolbar
           /* toolbar: [{
                iconCls: 'icon-add',
                text: '添加',
                handler: function(){
                    $('.dialog').dialog({
                        title: '添加',
                        href: controllerRequestPath+'/initAdd.action',
                        onClose:function () {
                            $(elementId).datagrid('reload');
                        }
                    });
//                    $('#dd').dialog('refresh', 'new_content.php');
                }
            },'-',{
                iconCls: 'icon-edit',
                text: '修改',
                handler: function(){
                    if (!ticcFrame.datagridCheckOne(elementId)){
                        return;
                    }
                    $('.dialog').dialog({
                        title: '修改',
                        href: controllerRequestPath+'/initUpdate.action',
                        queryParams: {'id':$(elementId).datagrid('getChecked')[0][idField]},
                        onClose:function () {
                            $(elementId).datagrid('reload');
                        }
                    });
                }
            },'-',{
                iconCls: 'icon-remove',
                text: '删除',
                handler: function(){
                    var rows = $(elementId).datagrid('getChecked');
                    var len = rows.length;
                    var ids = [];
                    for(var i = 0; i < len; i++){
                        ids.push(rows[i][idField]);
                    }
                    $.messager.confirm('提示','你确定要删除吗？',function (val) {
                        if (!val){
                            return;
                        }
                        ticc.ajaxRequest(controllerRequestPath+'/delete.action',
                            {'ids':ids},function (data) {
                                if(true === data.status){
                                    $(elementId).datagrid('reload');
                                }else {
                                    $.messager.alert('提示',data.msg,'error');
                                }
                            });

                    });
                }
            },'-',{
                iconCls: 'icon-search',
                text: '查看',
                handler: function(){
//                    $.messager.alert('提示','查看按钮','info');
                    if (!ticcFrame.datagridCheckOne(elementId)){
                        return;
                    }
                    $('.dialog').dialog({
                        title: '查看',
                        href: controllerRequestPath+'/initQueryDetail.action',
                        queryParams: {'id':$(elementId).datagrid('getChecked')[0][idField]},
                        onClose:function () {
                            $(elementId).datagrid('reload');
                        }
                    });
                }
            }]*/
        });
    },
    /**
     * 树形表格
     * @param title
     * @param elementId
     * @param controllerRequestPath
     * @param idField
     * @param singleSelect
     * @param border
     * @param pagination
     * @param columns
     * @param toolbar
     * @param treeField
     */
    intiTreegrid: function (title,elementId,controllerRequestPath,idField,singleSelect,border,
                            pagination,columns,toolbar,treeField,initQueryParams) {
        $(elementId).treegrid({
            // url: controllerRequestPath+'/list.action?time=' + new Date().getTime(),
            url: controllerRequestPath+'/list?time=' + new Date().getTime(),
            type: 'POST',
            title: title,
            idField:idField,
            singleSelect : singleSelect,
            striped: true,
            fitColumns: true,
            fit: true,
            border:border,
            rownumbers: true,
            pagination : pagination,
            pageNumber : 1,
            pageSize : 15,
            pageList : [10,15,20,30,40,50],
            columns : columns,
            toolbar:toolbar,
            treeField:treeField,
            queryParams: initQueryParams,
            onLoadError: function () {
                ticc.alertError('系统繁忙,请稍后再试');
            },
            onLoadSuccess: function (data) {
                $(this).datagrid('clearSelections');
                $(this).datagrid('clearChecked');
            }
        });
    },
    datagridCheckOne: function (elementId) {
        var rows = $(elementId).datagrid('getChecked');
        var len = rows.length;
        if (0 >= len){
            ticc.alertInfo('请选择一行');
            return false;
        }else if(2 <= len){
            ticc.alertInfo('只能选择一行');
            return false;
        }
        return true;
    }
};