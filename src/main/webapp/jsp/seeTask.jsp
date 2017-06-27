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
    <title>任务管理</title>
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
            $("#seeTask").datagrid({
                title:'任务信息' ,
                url:'${pageContext.request.contextPath }/getTaskData?phone=${phone}',
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
                    {field:'id',title:'编号',width:50},
                    {field:'content',title:'内容',width:50},
                    {field:'ename',title:'有谁完成',width:20,width:50,sortable:true},
                    {field:'beginTime',title:'开始时间',width:20},
                    {field:'endTime',title:'结束时间',width:20},
                    {field:'state',title:'所属部门',width:20},
                    {field:'operation',title:'操作',width:100,
                        formatter:function(val,row,rowIndex){
                            return "<a href='javascript:editUser("+id+");'>通过</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:deleteUser("+id+");'>删除</a>";
                            alert(id);
                        }
                    }
                ]],
                pagination:true,
                rownumbers:true,
                toolbar:[{
                    id:'btnchange',
                    text:'增加',
                    iconCls:'icon-add',
                    handler:function(){
                        addEmp();
                    }
                }]
            });
            $('#dlg').dialog({
                // 初始化后不打开
                closed: true,
                cache: false,
                modal: true
            });
            $('#adddlg').dialog({
                // 初始化后不打开
                closed: true,
                cache: false,
                modal: true
            });
        });

        function deleteUser(id) {
            $.post('${pageContext.request.contextPath }/deleteRole',{'roleDO.id':id,phone:${phone}},function (data) {
                var d=eval('(data)');
                alert(d.message);
            });
        }

        function addEmp(){
            $('#adddlg').dialog('open').dialog('setTitle','增加');


        }

        function editUser(id) {
//            //alert("1111");
//            // 获取当前选中行
//            // alert(row.name);
//
//            //打开新增的dialog
            var row = $('#seeRole').datagrid('getSelected');
            console.log(row);
            $('#dlg').dialog('open').dialog('setTitle','编辑');
            //将数据绑定到对应的数形值中
            $('#roleDO.name').val(row.name);
            $('#roleDO.func').val(row.func);
            $('#roleDO.level').val(row.level);
            $('#roleDO.dname').val(row.dname);
        }


        function addData() {
            // 表单验证失败则中断此函数执行
            if($("#fm1").form('validate') == false) {
                return;
            }
            var data = $("#fm1").serialize();
            //console.log(data);类似于alert
            //将数据data以json格式提交到后台
            changeStatus('${pageContext.request.contextPath }/addRole?phone=${phone}',data);
            $('#adddlg').dialog('close');
        }

        function saveData() {

            // 表单验证失败则中断此函数执行
            if($("#fm").form('validate') == false) {
                return;
            }
            var data = $("#fm").serialize();
            //console.log(data);类似于alert
            //将数据data以json格式提交到后台
            changeStatus('${pageContext.request.contextPath }/updateRole?phone=${phone}',data);
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
                    var json=eval('(data)');
                    if(json.code=9999){
                        $.messager.show({  title:'操作提示',    msg:json.message,  showType:'show' });
                    }else{
                        $.messager.show({  title:'操作提示',    msg:'操作成功!',  showType:'show' });
                    }
                    reloadTable();
                }
            });
        }
        function reloadTable(){
            $('#seeRole').datagrid('reload');
        }
    </script>
</head>
<body>
<table id="seeTask" class="easyui-datagrid" style="width: 100%;height:400px">
</table>
<div id="adddlg" class="easyui-dialog" style="width:330px;height:240px;padding:10px 20px">
    <form id="fm1" >
        <input type="hidden" id="employeeDO1.id" name="id" value="">
        <!--这里面写你的dlg代码，就是你那个新增的页面的HTML代码-->
        <table style="width: 100%;">

            <tr>
                <td><span>任务内容</span></td>
                <td><input type="text" id="task1.name" name="name"/></td>
            </tr>
            <tr>
                <td><span>预计时长（小时）</span></td>
                <td><input type="text" id="task1.id" name="name"/></td>
            </tr>
                <tr>
                    <td><span>上交或指派</span></td>
                    <%--<td><input type="text" id="roleDO1.level" name="level"/></td>--%>
                    <td>
                        <select  id="roleDO1.level" name="level">
                            <option value="0">指派</option>
                            <option value="1">上交</option>
                        </select>
                    </td>

                </tr>
            <tr>
                <td><span>谁来完成</span></td>
                <td>
                    <%--<input type="text" id="roleDO1.func" name="func"/>--%>
                <select onclick="#" id="task1.eid" name="eid">
                    <option value="">选择</option>
                    <option value="">李林</option>
                </select>
                </td>
            </tr>

            <%--<tr>--%>
                <%--<td><span>部门名称</span></td>--%>
                <%--<td><input type="text" id="roleDO1.dname" name="dname"/></td>--%>
            <%--</tr>--%>

        </table>
        <div class="fitem" style="text-align: center">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addData()">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
        </div>
    </form>
</div>
<div id="dlg" class="easyui-dialog" style="width:330px;height:240px;padding:10px 20px"    >
    <form id="fm" >
        <input type="hidden" id="employeeDO.id" name="id" value="">
        <!--这里面写你的dlg代码，就是你那个新增的页面的HTML代码-->
        <table style="width: 100%;">
            <tr>
                <td><span>名字</span></td>
                <td><input type="text" id="roleDO.name" name="name"/></td>
            </tr>
            <tr>
                <td><span>功能</span></td>
                <td><input type="text" id="roleDO.func" name="func"/></td>
            </tr>
            <tr>
                <td><span>级别</span></td>
                <td><input type="text" id="roleDO.level" name="level"/></td>
            </tr>
            <tr>
                <td><span>部门名称</span></td>
                <td><input type="text" id="roleDO.dname" name="dname"/></td>
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
