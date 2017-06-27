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
        $('#user').datagrid({
            title:'个人信息',
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
            url:"${pageContext.request.contextPath }/getUserData?phone=${phone}",
            // 设置可选择的每一页记录条数
            pageList : [10,15,20],
            // 设置行的唯一编号绑定id字段
            idField:'id',
            // 固定在左边的列，不会滚动
            frozenColumns:[[
                {field:'id',checkbox:true},
            ]],
            columns:[[
                {field:'name',title:'姓名',width:50},
                {field:'remark',title:'描述',width:50,sortable:true},
                {field:'phone',title:'电话',width:20},
                {field:'address',title:'地址',width:20},
                {field:'dname',title:'部门',width:20},
                {field:'operation',title:'操作',width:100,
                    formatter:function(val,row,rowIndex){
                        return "<a href='javascript:editPasswod("+row.id+");'>重置密码</a>";
                        alert(row.id);
                    }
                }
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

        function editPasswod(id) {
            //alert("1111");
            // 获取当前选中行
            var row = $('#user').datagrid('getSelected');
            // alert(row.name);
            console.log(row);
            //打开新增的dialog
            $('#dlg').dialog('open').dialog('setTitle','编辑');
            //将数据绑定到对应的数形值中
            $('#id').val(row.id);

            reSms(${phone});
            <%--$.post("${pageContext.request.contextPath }/sendSms",{phone:${phone}},function(data){--%>
                <%--var info=eval('(data)');--%>
                <%--if(info.code==1000){--%>
                    <%--alert("发送成功");--%>
                <%--}--%>
            <%--});--%>

        }
        function saveData() {

            // 表单验证失败则中断此函数执行
            if($("#fm").form('validate') == false) {
                return;
            }
            var data = $("#fm").serialize();
            //console.log(data);类似于alert
            //将数据data以json格式提交到后台
            changeStatus('${pageContext.request.contextPath }/updateSelfPassword?phone=${phone}',data);
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
                        $.messager.show({  title:'操作提示',    msg:'密码不一致!',  showType:'show' });
                    }else{
                        $.messager.show({  title:'操作提示',    msg:'操作成功!',  showType:'show' });
                    }
                    reloadTable();
                }
            });
        }
        function reloadTable(){
            $('#user').datagrid('reload');
        }
        function reSms(ph){
            $.post("${pageContext.request.contextPath }/sendSms",{phone:ph},function(data){
                var info=eval('(data)');
                if(info.code==1000){
                    alert("发送成功");
                }
            });
        }
    </script>
</head>
<body>
    <table id="user" class="easyui-datagrid" style="width: 100%;height:50px">
     </table>

    <div id="dlg" class="easyui-dialog" style="width:440px;height:360px;padding:10px 20px"    >
        <form id="fm" >
            <input type="hidden" id="id" name="id" value="">
            <!--这里面写你的dlg代码，就是你那个新增的页面的HTML代码-->
            <table style="width: 100%;">
                <tr>
                    <td><span>密码</span></td>
                    <td><input type="text" id="pass1" name="pass1"/></td>
                    <td><span></span></td>
                </tr>
                <tr>
                    <td><span>重复密码</span></td>
                    <td><input type="text" id="pass2" name="pass2"/></td>
                    <td><span></span></td>
                </tr>
                <tr>
                    <td><span>验证码</span></td>
                    <td><input type="text" id="scode" name="scode"/></td>
                    <td><button onclick="reSms(${phone})">未收到重发</button></td>
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
