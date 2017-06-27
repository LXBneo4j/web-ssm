function loadWestFuncTree(userRole){
	$.post("/manageschool/LoadFunctionServlet",{id:userRole},function(data){
		//{"func":{"id":1,"name":"用户信息","pid":0},"id":10,"role":{"id":3}}
		data=eval("("+data+")");
		for(var i=0;i<data.length;i++){
			//alert(data[i].func.id+"功能名称"+data[i].func.name);
			/**
			 * 生成类似页面：
			 * <div title="用户信息" data-options="selected:true" style="overflow: auto; padding: 10px;">
					<ul id="func">
						<a href="javascript:addTag('详细信息');" style="backgrond-color:"><li>查看详细信息</li></a>
					</ul>
				</div>
			 */
			var str=null;
			if(data[i].func.pid==0){
				str="<div  title='"+data[i].func.name+"'  data-options='selected:true' style='overflow: auto; padding: 10px;'>";
			    for(var j=0;j<data.length;j++){
			    	if(data[j].func.pid==data[i].func.id){
			    		str+="<ul name='func'>";
			    		str+="<a href=\"javascript:addTag('"+data[j].func.name+"');\" style=\"backgrond-color:\"><li>"+data[j].func.name+"</li></a>";
			    	    str+="</ul></div>";
			    	}
			    }
			   
			}
			if(str!=null){
				alert(str);
				 $("#aa").append(str);
				 //document.getElementById("aa").innerHTML(str);
			}
			
		}
	});
	
}