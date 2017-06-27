<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"  
            href="${pageContext.request.contextPath}/css/userui.css"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/userui.js"></script>
<title>用户信息</title>
</head>
<body>
 <table id="userinfoui"   style="width:800;height:800" >
 <tr>
 <td rowspan="4" colspan="2"><img alt="*图片加载中*" src="${pageContext.request.contextPath }/img/ibm.png" ></td>
 <td>姓名：${user.name }</td>
 <td>身份：${user.role }</td>
 <td>出生：${user.birthday }</td>
 </tr>
 <tr>
 <td>性别：${user.sex }</td>
 <td>地址：<br/>${user.address }</td>
 <td>电话：<br/>${user.phone }</td>
 </tr>
 <tr>
 <td>毕业院校：${user.school }</td>
 <td>学历：${user.edu }</td>
 <td>工作年限：${user.workyear }</td>
 </tr>
 <tr>
 
 <td>注册时间：<br/>${user.regtime }</td>
 <td></td>
 <td>状态：${user.status }</td>
 </tr>
 <tr>
 <td></td>
 <td></td>
 <td></td>
 <td><input type="button" onclick="updateUserInfo();" value="修改"/></td>
 <td></td>
 </tr>
 </table>
</body>
</html>