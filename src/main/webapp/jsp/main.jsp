<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>事务管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.5/themes/default/easyui.css">

<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.5/themes/icon.css">

<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/main.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript">
function addTag(){
	if($("#tt").tabs("exists",arguments[0])){	
		$("#tt").tabs('select',arguments[0]);//选中
		
	}else{//创建
		$("#tt").tabs('add',{
			title:arguments[0],
			closable:true,
			content:"<iframe src="+arguments[1]+" frameborder=0 scrolling=no style='width: 100%;height: 100%'></iframe>",
			fit:true   //内容是否充满框架
		});
	}
}
</script>
</head>
<body >
	<div id="format" name="format" style="text-align: center;border:0px solid red;">
		<div id="cc" class="easyui-layout" style="width:100%;height:800px;margin:auto">   
		    <div data-options="region:'north',split:true" style="height:100px;background:#D1EEEE;"></div>
		    <div data-options="region:'south',title:'站点信息',split:true" style="height:100px;"></div>
		    <%--<div data-options="region:'east',iconCls:'icon-reload',title:'公告',split:true" style="width:0px;">--%>
		    <%----%>
		    <%--</div>   --%>
		    <div data-options="region:'west',title:'导航'" style="width:200px;">
		         <div id="aa" class="easyui-accordion" style="width:198px;height:100%;"> 
					<c:forEach items="${lists}" var="rf" varStatus="vs">
					  <c:if test="${rf.aid ==0}">
						  <div title="${rf.name }"  data-options="selected:true" style="overflow: auto; padding: 10px;">
							<c:forEach items="${lists}" var="rf1" varStatus="vs1">
								<c:if test="${rf1.aid ==rf.id}">
								  <ul name="func">
									<a href="javascript:addTag('${rf1.name }','${rf1.url }'+'?phone=${phone}');" ><li>${rf1.name }</li></a>
								  </ul>
								</c:if>
							</c:forEach>
						  </div> 
					  </c:if>  
					</c:forEach>
				</div> 
		    </div>   
		    <div data-options="region:'center',title:'${user.name}'" style="padding:5px;background:#eee;">
		      <div id="tt" class="easyui-tabs" style="width:500px;height:250px;" fit=true border=false >   
			      <div title="欢迎" style="padding:20px;display:none;background-image: url('../images/welcome.png');"></div>
		       </div> 
		    </div>   
	    </div>  
	</div>
</body>
</html>