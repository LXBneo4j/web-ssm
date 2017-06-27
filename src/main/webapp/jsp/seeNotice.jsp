<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/5
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>公告管理</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.5/themes/demo.css">


    <script type="text/javascript" src="${pageContext.request.contextPath}/js/class.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#seeNotice").datagrid({
                title:'公告信息' ,
                url:'${pageContext.request.contextPath }/getNoticeData?phone=${phone}',
                iconCls:'icon-save',
                width:'auto',
                height:'auto',
                // 自适应列宽
                fitColumns: true,
                // 数据超过列宽时自动截取
                nowrap: true,
                autoRowHeight: false,
                // 交替显示行背景
                striped: true,
                // 请求数据url
                // 设置可选择的每一页记录条数
                pageList : [10,15,20],
                // 设置行的唯一编号绑定id字段
                idField:'id',
                // 固定在左边的列，不会滚动
                frozenColumns:[[
                    {field:'id',checkbox:true},
                ]],
                columns:[[
                    {field:'name',title:'名称',width:50},
                    {field:'content',title:'内容',width:50},
                    {field:'lookNumber',title:'所有编号',width:20},
                    {field:'remark',title:'发布人',width:20},
                    {field:'createTime',title:'发布时间',width:20},
//                    {field:'operation',title:'操作',width:100,
//                        formatter:function(val,row,rowIndex){
//                            return "<a href='javascript:editUser("+row.id+");'>修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:deleteUser("+row.id+");'>删除</a>";
//                            alert(row.id);
//                        }
//                    }
                ]],
                pagination:true,
                rownumbers:true,
                toolbar:[{
                    id:'btnchange',
                    text:'发布',
                    iconCls:'icon-add',
                    handler:function(){
                        addNotice();
                    }
                }]

            });
            $('#dlg').dialog({
                // 初始化后不打开
                closed: true,
                cache: false,
                modal: true
            });

        });

        function addNotice(id) {
//            //alert("1111");
//            // 获取当前选中行
//            var row = $('#seeNotice').datagrid('getSelected');
//            // alert(row.name);
//            console.log(row);
//            //打开新增的dialog
            $('#dlg').dialog('open').dialog('setTitle','发布');
//            //将数据绑定到对应的数形值中
//            $('#id').val(row.id);
        }
        function saveData() {

            // 表单验证失败则中断此函数执行
            if($("#fm").form('validate') == false) {
                return;
            }






            var data = $("#fm").serialize();
            //console.log(data);类似于alert
            //将数据data以json格式提交到后台
            changeStatus('${pageContext.request.contextPath }/addNotice',{phone:${phone}},data);
            $('#dlg').dialog('close');
        }
        function changeStatus(url,data){
            $.ajax({
                async : false,
                cache : false,
                type : 'POST',
                dataType : "json",
                url : url,
                data : data,
                error: function () {
                    alert('请求失败');
                },success:function(data){
//                    var json=eval('(data)');
//                    if(json.code=9999){
//                        $.messager.show({  title:'操作提示',    msg:'权限不足!',  showType:'show' });
//                    }else{
                        $.messager.show({  title:'操作提示',    msg:'操作成功!',  showType:'show' });
//                    }
                    reloadTable();
                }
            });
        }
        function reloadTable(){
            $('#seeNotice').datagrid('reload');
        }
    </script>
</head>
<body>
<table id="seeNotice" class="easyui-datagrid" style="width: 100%;height:400px">
</table>
<div id="dlg" class="easyui-dialog" style="width:330px;height:240px;padding:10px 20px"    >
    <form id="fm" >
        <input type="hidden" id="id" name="id" value="">
        <!--这里面写你的dlg代码，就是你那个新增的页面的HTML代码-->
        <table style="width: 100%;">
            <tr>
                <td><span>名称</span></td>
                <td><input type="text" id="noticeDO.name" name="name"/></td>
            </tr>
            <tr>
                <td><span>内容</span></td>
                <td><input type="text" id="noticeDO.content" name="content"/></td>
            </tr>
            <tr>
                <td><span>选择</span></td>
                <td>
                    <select name="select" id="noticeDO.category" >
                        <option value="0">内部</option>
                        <option value="1">外部</option>
                    </select>
                </td>
            </tr>
        </table>
        <div class="fitem" style="text-align: center">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveData()">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
        </div>
    </form>
</div>
</body>
</html>
