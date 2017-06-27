$(function(){
	init();
	loadClass();
});
function init(){
	//alert("tianjia");
	var obj = $('#studenttb').dialog('options');
    var queryParams = obj.queryParams; 
    $("input[name='sid']").attr("value",queryParams["id"]);
	$("input[name='sname']").attr("value",queryParams["name"]);
	$("input[name='oldcid']").attr("value",queryParams["tclassid"]);
	$("input[name='oldcname']").attr("value",queryParams["tclassname"]);	
}

function loadClass(){
	 $.post("/manageschool1.2/ManageStudentServlet",{"op":"loadClass","loadcstatus":"正常"},function(data){
			var d=eval("("+data+")");
			for(var i=0;i<d.length;i++){
				$("select[id='newcid']").append("<option value='"+d[i].id+"'>"+d[i].name+"</option>");
			}
		});
}