var host="http://localhost:8080/TrcTest";
var temp="";
var checked="";
var method_id_save;
window.onload=function(){
	defualtload();
	listnext(host,0,0);	
	checked="no";
	method_id_save="";
	showdataget(0);
}





//数据配置页面默认加载的信息
function defualtload(){
	$("div.tree-config").hide();
	$("div.tree-view").hide();
	$("div#tree-config-set").show();
	
}
//点击数据加载页面后展示数据预览信息
function configdata(){
	if("no"==checked){
		checked="yes";
		viewnext(host,0,0);
	}
	
	$("div.tree-config").hide();
	$("div.tree-view").hide();
	$("div#tree-config-view").show();
}
function viewnext(host,tagid,father){ 
	$.post(host+"/dataconfig/viewnext",{
		id:tagid
	},
	function(data,status){
//		alert("数据: \n" + data + "\n状态: " + status);
		var jso= eval("("+data+")");
/*		if(jso.isempty){
		 return false; 
		}*/
		
		var incode= "<ul id='DATAN"+tagid+"' style='height: 0px;'>";
		var datas=jso.data;
		 $(datas).each(function(index) {
		        var val = datas[index];
		        
		        incode+="<li id='"+val.id+ "'>";
		        incode+="<a id='"+val.id+ "'href='#DATAN"+val.id+"'>";
		        incode+='&nbsp;';
		        incode+="<span id='"+val.id+ "' class='view-tool-show' mycheck='false' isshow='false' onclick=viewsub(this)>+</span>";
		        incode+="<span  class='view-son' >";
		        incode+=val.son;
		        incode+="</span>";    
		        if("method"==val.son_type){
		        	incode+="<span class='view-operate' datainfo='"+val.id+ "' >查看数据</span>";
		        }
		        
		        
		        incode+="</a></li> ";
//		        alert(val.son);
//		        alert(val.parent_id);
//		        alert(val.son_type);
//		        alert(val.description);
//		        alert(val.id);   
		 });
		 	incode+="</ul>"
//		 		alert(incode);
		 		$("div[id='tree-config-view'] li[id='"+father+"']").append(incode);
//		 	alert(incode);
	});
} 

/**
 * 列出下一级元素
 */
function viewsub(obj){
	/*var myspan = obj.parentElement.parentElement;*/
	var id=obj.id;
	var chk=$(obj).attr("mycheck");
	if(chk=="false"){
		
		$(obj).attr("mycheck","true");
		if ((typeof(id)=="undefined")) {
			
		}else {
			
			viewnext(host,id,id);
		}
	}
	var isshow=$(obj).attr("isshow");
	if(isshow=="false"){
		$("span[id="+id+"]").html("-");;
		$(obj).attr("isshow","true");
		$("ul[id=DATAN"+id+"] li").show();
		
	}else {
		$("span[id="+id+"]").html("+");
		$(obj).attr("isshow","false");
		$("ul[id=DATAN"+id+"] li").hide();
	}
}
/*
 *列出下级目录 
 */
function listnext(host,tagid,father){ 
	$.post(host+"/dataconfig/listnext",{
		id:tagid
	},
	function(data,status){
//		alert("数据: \n" + data + "\n状态: " + status);
		var jso= eval("("+data+")"
		);
/*		if(jso.isempty){
		 return false; 
		}*/
		
		var incode= "<ul id='N"+tagid+"'class='nav nav-list collapse secondmenu' style='height: 0px;'>";
		var datas=jso.data;
		 $(datas).each(function(index) {
		        var val = datas[index];
		        
		        incode+="<li id='"+val.id+ "' class='active'>";
		        
		        incode+="<a id='"+val.id+ "' class='nav-header collapsed' data-toggle='collapse' >";
		        incode+='&nbsp;';
		        incode+="<span id='"+val.id+ "'href='#N"+val.id+"' class='tool tool-show ' mycheck='false' isshow='false' onclick=listsub(this)>+</span>";
		        incode+="<span id='son-type' class='tool'>类型：";
		        incode+=val.son_type;
		        incode+="</span>";
		        incode+="<span id='son"+val.id+ "' datainfo='"+val.id+ "'  class='tool son' onclick=tipsson(this)>名称：";
		        incode+=val.son;
		        incode+="</span>";
		        incode+="<span id='des"+val.id+ "' datainfo='"+val.id+ "'  class='tool des' onclick=tipsdes(this)>描述：";
		        incode+=val.description;
		        incode+="</span>";
		        incode+="<span id='set' class='operate' datainfo='"+val.id+ "'  onclick=modifypage(this)>编辑：</span>";
		        incode+="<span id='add' class='operate' datainfo='"+val.id+ "'  onclick=addpage(this) >新增：</span>";
		        incode+="<span id='delete' class='operate' datainfo='"+val.id+ "'  onclick=deletedata(this)>删除：</span>";
		        incode+="</a></li> ";
//		        alert(val.son);
//		        alert(val.parent_id);
//		        alert(val.son_type);
//		        alert(val.description);
//		        alert(val.id);   
		 });
		 	incode+="</ul>"
//		 		alert(incode);
			$("div[id='tree-config-set'] li[id='"+father+"']").append(incode);
//		 	alert(incode);
	});
} 
/**
 * 增加数据配置项
 */
function addpage(obj){
	var id=$(obj).attr("datainfo");
	setTemp(id);
	
	layer.open({
		  type: 2,
		  scrollbar: true,
		  resize:false,
		  fixed: false, //不固定
		  maxmin: false,
	      area: ['350px', '650px'],
	      closeBtn: 1,
		  content: 'add.html'});	
}
/**
 * 修改OBJ
 * @param obj
 */
function modifypage(obj){
	var id=$(obj).attr("datainfo");
	setTemp(id);
	
	layer.open({
		  type: 2,
		  scrollbar: true,
		  resize:false,
		  fixed: false, //不固定
		  maxmin: false,
	      area: ['350px', '650px'],
	      closeBtn: 1,
		  content: 'modify.html'});	
}

function getTemp(){
	return temp;
}
function setTemp(obj){
	temp=obj;
}

function addconfig(){
	 var son=$("#son").val();
	 var description=$("#description").val();
	 alert('son:'+son+'des'+description);
};

/**
 * 刷新自身
 */
 
function refreshself(id,son,description ){
	son="名称："+son;
	description="描述："+description;
	$("a[id='"+id+"'] span[id='son']").html(son);
	$("a[id='"+id+"'] span[id='des']").html(description);
}

/**
 * 刷新下级
 */
function refreshnext(host,id){
	$("ul[id=N"+id+"]").remove();
	listnext(host,id,id);	
}



/**
 * 列出下一级元素
 */
function listsub(obj){
	/*var myspan = obj.parentElement.parentElement;*/
	var id=obj.id;
	var chk=$(obj).attr("mycheck");
	if(chk=="false"){
		
		$(obj).attr("mycheck","true");
		if ((typeof(id)=="undefined")) {
			
		}else {
			
			listnext(host,id,id);
		}
	}
	var isshow=$(obj).attr("isshow");
	if(isshow=="false"){
		$("span[id="+id+"]").html("-");;
		$(obj).attr("isshow","true");
		$("ul[id=N"+id+"] li").show();
		
	}else {
		$("span[id="+id+"]").html("+");
		$(obj).attr("isshow","false");
		$("ul[id=N"+id+"] li").hide();
	}
}
/**
 * 删除
 */
function deletedata(obj){
	var id=$(obj).attr("datainfo");
	$.post(host+"/dataconfig/delete",{id:id},function(result){
		if(result.success){
			/* var result=eval('('+result+')'); */
		  layer.msg(result.des);
		  var xx=result.parent_id;
		  refreshnext(host,xx); 
	  
		}else{
			layer.msg(result.des);
		}
	},"json");
	
}
/**
 * 展示son
 */
function tipsson(obj){
	var id=$(obj).attr("datainfo");
	var t=$(obj).text();
	var aa="#son"+id;
	
	layer.tips(t, aa, {
		  tips: [4, '#78BA32'],
	 	  time: 3000
		});
}
/**
 * 展示des
 */
function tipsdes(obj){
	var id=$(obj).attr("datainfo");
	var t=$(obj).text();
	var aa="#des"+id;
	layer.tips(t, aa, {
		  tips: [4, '#78BA32'],
	 	  time: 3000
		});
}


/**
 * 展示数据
 */
function showdata(obj){
	var id =$(obj).attr("datainfo");
	
	
	showdataget(id);
	
	
}
/**
 * 生成展示区域
 */
function showdataget(id){
  $.post(host+"/dataconfig/get",{id:id},function(result){
			if(result.success){
	        	$("#deatil-m").val(result.son);
	        	$("#deatil-d").val(result.description);
				
			}else{
				parent.layer.msg(result.des);
				}
	},"json");	
 
  $.post(host+"/group/list",{method_id:id},function(result){
		if(result.success){
      	$("#deatil-m").val(result.son);
      	$("#deatil-d").val(result.description);
			
		}else{
			parent.layer.msg(result.des);
			}
   },"json");	 
  
 var incode="<table border='1'>";
 incode+="<tr>";
 incode+="<th>Header 1</th>";
 incode+="<th>Header 2</th>";
 incode+="</tr>";	 
 incode+="</table>";
 $('div#tables').append(incode);
}
function setmethod(method_id ){
	method_id_save=method_id;
}

