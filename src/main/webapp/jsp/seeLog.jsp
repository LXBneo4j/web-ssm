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
    <title>日志管理</title>
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
            $("#seeLog").datagrid({
                title:'日志信息' ,
                url:'${pageContext.request.contextPath }/getLogData',
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
                    {field:'logNumber',title:'所有编号',width:20},
                    {field:'checkUser',title:'谁审核',width:20},
                    {field:'remark',title:'状态',width:20},
//                    {field:'operation',title:'操作',width:100,
//                        formatter:function(val,row,rowIndex){
//                            return "<a href='javascript:editUser("+row.id+");'>修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:deleteUser("+row.id+");'>删除</a>";
//                            alert(row.id);
//                        }
//                    }
                ]],
                pagination:true,
                rownumbers:true
            });
            $('#dlg').dialog({
                // 初始化后不打开
                closed: true,
                cache: false,
                modal: true
            });
        });
    </script>
</head>
<body>
<table id="seeLog" class="easyui-datagrid" style="width: 100%;height:400px">
</table>
<div id="dialog"></div>
</body>
</html>
