<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
$(function(){
	//alert($("input[name='name']"));
	init();
	loadAddress();
});
function init(){
	var obj = $('#manageuser').dialog('options');
    var queryParams = obj.queryParams; 
    //alert(queryParams["name"]); 
    $("input[name='name']").attr("value",queryParams["name"]);
    $("input[name='birthday']").attr("value",queryParams["birthday"]);
    $("input[name='sex']").attr("value",queryParams["sex"]);
    $("input[name='address']").attr("value",queryParams["address"]);
    $("input[name='phone']").attr("value",queryParams["phone"]);
    $("input[name='school']").attr("value",queryParams["school"]);
    $("input[name='workyear']").attr("value",queryParams["workyear"]);
    $("input[name='regtime']").attr("value",queryParams["regtime"]);
    $("input[name='status']").attr("value",queryParams["status"]);
    $("input[name='password']").attr("value",queryParams["password"]);
}
var addressjson=null;
function loadAddress(){
    $.post("/manageschool1.2/ManageUserServlet",{"op":"loadAddress"},function(data){
		var d=eval("("+data+")");
		//alert(d);
		addressjson=d;
		for(var i=0;i<d.length;i++){
			$("select[id='address']").append("<option value='"+d[i].name+"'>"+d[i].name+"</option>");
		}
	});
}

function loadCitys(value){
	$("select[id='city']>option:gt(0)").remove();
	for(var i=0;i<addressjson.length;i++){
		if(value==addressjson[i].name){
			for(var j=0;j<addressjson[i].citys.length;j++){
				$("select[id='city']").append("<option value='"+addressjson[i].citys[j].name+"'>"+addressjson[i].citys[j].name+"</option>");
			}
		}
	}
}
</script>

<div>
     <form action="${pageContext.request.contextPath}/ManageUserServlet?op=updateUser" method="post">
		<table id="userinfodialog" border="1" width="100%" height="100%";>
			<tr >
				<td>
					姓名：<input type="text" name="name" value=""/>
				</td>
				<td>
					角色：
                    <select name="role" style="text-align:center">
					    <option value="校长">校长</option>
					    <option value="班主任">班主任</option>
					    <option value="项目经理">项目经理</option>
					</select>
				</td>
				<td>
					出生:<input type="text" name="birthday" class="easyui-datebox" required="required" value=""></input>  
				</td>
			</tr>
			<tr>
				<td>
					性别：
					<select name="sex" style="text-align:center">
					    <option value="男">男</option>
					    <option value="女">女</option>
					    <option value="半男半女">半男半女</option>
					</select>
				</td>
				<td colspan="1">
				    
					<select id="address" name="province" style="text-align:center" onchange="loadCitys(this.value);">
					    <option value="0">==选择==</option>
					 </select>
					 <select id="city" name="city" style="text-align:center" >
					    <option value="0">==选择==</option>
					 </select>
				</td>
				<td>
					详细地址：<input type="text" name="address" value=""/>
				</td>
			</tr>
			<tr>
				<td>
					电话：<input type="text" name="phone" value=""/>
				</td>
				<td>
					毕业学校：<input type="text" name="school" value=""/>
				</td>
				<td>
					学历：
					<select name="edu" style="text-align:center" >
					    <option value="专科">专科</option>
					    <option value="本科">本科</option>
					    <option value="硕士">硕士</option>
					    <option value="博士">博士</option>
					    <option value="博士后">博士后</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					工作年限：<input type="text" name="workyear" value=""/>
				</td>
				<td>
					注册时间：<input type="text" name="regtime" class="easyui-datebox" required="required" value=""></input>  
				</td>
				<td>
					密码：<input type="password" name="password " value=""/>
				</td>
			</tr>
		</table>
		<input type="submit" value="提交"/><br/>
	</form>
</div>