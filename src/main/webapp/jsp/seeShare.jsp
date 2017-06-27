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
    <title>班级管理</title>
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
            $("#seeShare").datagrid({
                title:'班级信息' ,
                url:'${pageContext.request.contextPath }/getShareData',
                fitColumns:true,
                singleSelect:true,
                iconcls:'icon-save',
                idField:'id',
                rowStyler: function(index,row){
                    if (row.listprice>80){
                        return 'background-color:#6293BB;color:#fff;';
                    }
                },
                columns:[[
                    {field:'id',title:'编号',width:100},
                    {field:'name',title:'名称',width:100},
                ]],
                rownumbers:false,
                singleSelect:true,
                pagination:true,
                fitColumns:true,
                toolbar:[{
                    id:'btnchange',
                    text:'修改',
                    iconCls:'icon-edit',
                    handler:function(){
                        updateClass();
                    }
                }]
            });
            var p = $('#seeUser').datagrid('getPager');
            $(p).pagination({
                pageSize: 10,//每页显示的记录条数，默认为10
                pageList:[5,10,15,20],//每页显示几条记录
                beforePageText: '第',//页数文本框前显示的汉字
                afterPageText: '页    共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录    共 {total} 条记录',
                onBeforeRefresh:function(){
                    $(this).pagination('loading');//正在加载数据中...
                    $(this).pagination('loaded'); //数据加载完毕
                }
            });

        });
    </script>
</head>
<body>
<table id="seeShare" class="easyui-datagrid" style="width: 100%;height:400px">
</table>
<div id="dialog"></div>
</body>
</html>
