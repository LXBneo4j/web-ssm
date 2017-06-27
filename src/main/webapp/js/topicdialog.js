$(function(){
	
	init();//初始化数据
	loadCourse();
	loadChoise();//从数据库加载该题目下选项
});
function init(){
	var obj = $('#topic').dialog('options');
    var queryParams = obj.queryParams; 
    $("input[name='id']").attr("value",queryParams["id"]);
    $("input[name='tpid']").attr("value",queryParams["tpid"]);
    $("input[name='number']").attr("value",queryParams["number"]);
    $("select[name='mold']").attr("value",queryParams["mold"]);
    $("input[name='content']").attr("value",queryParams["content"]);
    $("input[name='answer']").attr("value",queryParams["answer"]);
    $("input[name='paser']").attr("value",queryParams["paser"]);
    $("select[name='courseid']").attr("value",queryParams["course"]);
}
function loadCourse(){
   $.post("/manageschool1.2/ManageTestPaperServlet?op=getAllCourse",function(data){
   	var d=eval("("+data+")");
   	for(var i=0;i<d.length;i++){
   		$("select[id='courseid']").append("<option value='"+d[i].id+"'>"+d[i].name+"</option>");
   	}
   });
}
/**
 * ajax数据库加载题目下得所有选项
 */
var i=0;
var number=null;
function loadChoise(){
	//alert("dkkd");
	$.post("/manageschool1.2/ManageTestPaperServlet",{"op":"loadChoise","id":$("input[name='id']").val()},function(data){
		var d=eval("("+data+")");
		//alert(d);
		for(;i<d.length;i++){
			number =String.fromCharCode(i+65);
			var str="<tr>";
			str+="<td><input type='hidden' name='cid'"+i+"  value='"+d[i].id+"'/></td>";
			str+="<td><input type='hidden' name='tid'"+i+"  value='"+d[i].tid+"'/></td></td>";
			str+="：<td>"+number+"</td>";
			str+="：<td><input type='text' name='content'"+i+" class='easyui-validatebox' value='"+d[i].content+"'/><input style='width:40px;background:#99CC99' type='button' onclick='addChoise();' value='添加'></a></td>";
			str+="</tr>";
			$("table[id='topicdialog']").append(str);
		}
	});	
}

function addChoise(){
	number =String.fromCharCode(i+65);
	var str="<tr>";
	str+="<td><input type='hidden' name='cid'"+i+"  /></td>";
	str+="<td><input type='hidden' name='tid'"+i+"  /></td></td>";
	str+="：<td>"+number+"</td>";
	str+="：<td><input type='text' name='content'"+i+" class='easyui-validatebox' /><input style='width:40px;background:#99CC99' type='button' onclick='addChoise();' value='添加'></input></td>";
	str+="</tr>";
	$("table[id='topicdialog']").append(str);
	i++;
}












