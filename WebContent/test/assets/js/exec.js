function appendText()  
{  
var txt1="<p>Text.</p>";              // 以 HTML 创建新元素  
var txt2=$("<p></p>").text("Text.");  // 以 jQuery 创建新元素  
var txt3=document.createElement("p");  
txt3.innerHTML="Text";               // 通过 DOM 来创建文本  
$("body").append(txt1,txt2,txt3);        // 追加新元素  
}  

window.onload=function(){
		initdata();
		$("input#selectuser").prop("checked",false);
		boxclick();
	}


function test1(){
	var text1="<label class=label-list><input type=checkbox id=no1 class=user1 >接口场次大家啊施蒂利克拉实打实角度考虑拉萨的静安寺看来得卡仕达</label>"
	$("div#user1").append(text1);
}

function writeselect(text1,text2){
	var text="<label class=label-list><input type=checkbox class=user1 name='"+text1+"' value='"+text2+"'>"+text2+"</label>";
	$("div#user1").append(text);
}

function test2(){

	
	var aaa="<a class=myown1>执行成功的报文为绿色</a><br/>";
	aaa+="<a class=myown2>执行失败的报文为红色</a><br/>";
	var count=0;
	$("div#alllist").html(aaa);
	$("div#usercount").html("");
	
	
	$('input.user1:checkbox:checked').each(function (i) {
		//写入代码
		
		var total =$('input.user1:checkbox:checked').length;
		if ($(this).prop('checked') !=true) {
		//	alert("此节点未选中！");
			}else{
		var classname=$(this).attr("name");
		var themethod=$(this).attr("value");
		
		setTimeout(function(){
			
			
			$.post(host + "/exec", {
				classname : classname,
				themethod : themethod
				}, function(result) {
					if (result.Success) {
					//	$("#description").val(JSON.stringify(result.des));
					//	alert(JSON.stringify(result.data));
						layer.msg("成功！");
						//$("div.simplearea").delay(5000).append(JSON.stringify(result.data));
						test3(JSON.stringify(result.Success),JSON.stringify(result.classname),JSON.stringify(result.themethod),JSON.stringify(result.data),JSON.stringify(result.des));
						updatenum(total,i,classname,themethod,count);
						
						
					//	$("#description").val(JSON.stringify(result.des));
					} else {
						layer.msg("失败!");
						test3(JSON.stringify(result.Success),JSON.stringify(result.classname),JSON.stringify(result.themethod),JSON.stringify(result.data),JSON.stringify(result.des));
						useraddfailure(JSON.stringify(result.Success),JSON.stringify(result.classname),JSON.stringify(result.themethod),JSON.stringify(result.data),JSON.stringify(result.des));
						updatenum(total,i,classname,themethod,count);
						count+=1;
						
						//alert(JSON.stringify(result.data));
						//$("#description").val(result.des);
						
				}
				}, "json");
			
			
		},i*5000);
		
		
		
	
/**
		$.ajax({
			url : host + "/exec",
			type : 'post',
			async: false,//使用同步的方式,true为异步方式
			data : {'classname':classname, 'themethod':themethod},//这里使用json对象
			success : function(result){
			//code here...
			if (result.Success) {
			//	$("#description").val(JSON.stringify(result.des));
			//	alert(JSON.stringify(result.data));
			layer.msg("成功！");
				//$("div.simplearea").delay(5000).append(JSON.stringify(result.data));
				test3(JSON.stringify(result.Success),JSON.stringify(result.classname),JSON.stringify(result.themethod),JSON.stringify(result.data),JSON.stringify(result.des));
				
			//	$("#description").val(JSON.stringify(result.des));
			} else {
				layer.msg("失败!");
				test3(JSON.stringify(result.Success),JSON.stringify(result.classname),JSON.stringify(result.themethod),JSON.stringify(result.data),JSON.stringify(result.des));

				//alert(JSON.stringify(result.data));
				//$("#description").val(result.des);
			}
			
			},
			fail:function(){
				
			//code here...
			}
			});

**/


		
			}
		
		});
	
	
}

function sleep(numberMillis) { 
	var now = new Date(); 
	var exitTime = now.getTime() + numberMillis; 
	while (true) { 
	now = new Date(); 
	if (now.getTime() > exitTime) 
	return; 
	} 
	}

function boxclick(){
	
	$("input#selectuser").click(function(){
				if ($("input#selectuser").prop('checked') ==true) {
				$('input.user1').each(function() {
			
					$(this).prop("checked",true);
					
				});
		}else{	
			$('input.user1').each(function() {
			$(this).prop("checked",false);
			});
			}
	 }); 
	
	
	
}
function initdata(){
	  $.get(host+"/initexec", function(data){
	  	
	  	var jso= eval("("+data+")");
	  	var datas=jso.user;
	  	$(datas).each(function(index) {
  			//获取单个JSON
  			var val = datas[index];
  			//写入列表中
  			writeselect(val.classname,val.method);	
  			
  	});
  	
  	});	
	
}



function test3(Success,classname,themethod,data,des){
	var thetext="<div class=resultlist>";
	var thecolor;
		if("true"==Success){
			thecolor="myown1";
		}else{
			thecolor="myown2";
		}		
		thetext+= "<a class="+thecolor+">"+"是否通过:"+Success+"</a><br />";
		thetext+= "<a class="+thecolor+">"+"类名:"+classname+"方法名:"+themethod+"</a><br />";
		thetext+= "<a class="+thecolor+">"+"执行描述是:"+des+"</a><br />";
		thetext+= "<a class="+thecolor+">"+"执行返回报文是:"+data+"</a><br />";
		thetext+= "</div>";
		$("div#alllist").append(thetext);
}
function useraddfailure(Success,classname,themethod,data,des){
	var thetext="<div class=resultlist>";
	var thecolor;
		if("true"==Success){
			thecolor="myown1";
		}else{
			thecolor="myown2";
		}		
		thetext+= "<a class="+thecolor+">"+"是否通过:"+Success+"</a><br />";
		thetext+= "<a class="+thecolor+">"+"类名:"+classname+"方法名:"+themethod+"</a><br />";
		thetext+= "<a class="+thecolor+">"+"执行描述是:"+des+"</a><br />";
		thetext+= "<a class="+thecolor+">"+"执行返回报文是:"+data+"</a><br />";
		thetext+= "</div>";
		$("div#usercount").append(thetext);
}


function updatenum(total,current,classname,themethod,count){
	var thetext="当前选择的总数是:"+total+"个！<br />";
	current+=1;
	thetext+= "当前已执行到第:"+current+"个！<br />";
	thetext+= "当前执行的类名为:"+classname+"<br />";
	thetext+= "当前执行的方法名为:"+themethod+"<br />";
	thetext+= "当前失败的数量为:"+count+"个！<br />";
	//alert(thetext);
	$("div#usertimely").html(thetext);
	
}
