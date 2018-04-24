//******************
//全局变量区域
//******************
var host = "http://10.200.140.54:8080/TRCTools";
var shoplogin="http://view.trc.com/shop/passport/signin.html";
var temp1="#test5";


//******************
//全局工具方法
//******************

//格式化日期 	使用时候直接调用方法 Format(date,"yyyy-MM-dd HH:mm");输出格式为 "2015-10-14 16:50"；第一个参数为时间，第二个参数为输出格式。
//格式中代表的意义：
//d:日期天数；dd:日期天数（2位，不够补0）；ddd:星期（英文简写）；dddd:星期（英文全拼）；
//M:数字月份；MM:数字月份（2位，不够补0）；MMM:月份（英文简写）；MMMM:月份（英文全拼）；
//yy:年份（2位）；yyyy:年份；
//h:小时（12时计时法）；hh:小时（2位，不足补0；12时计时法）；
//H:小时（24时计时法）；HH:小时（2位，不足补0；24时计时法）；
//m:分钟；mm:分钟（2位，不足补0）；
//s:秒；ss:秒（2位，不足补0）；
//l:毫秒数（保留3位）；
//tt: 小时（12时计时法，保留am、pm）；TT: 小时（12时计时法，保留AM、PM）；
function DateFormat(now,mask)
{
    var d = now;
    var zeroize = function (value, length)
    {
        if (!length) length = 2;
        value = String(value);
        for (var i = 0, zeros = ''; i < (length - value.length); i++)
        {
            zeros += '0';
        }
        return zeros + value;
    };
 
    return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
    {
        switch ($0)
        {
            case 'd': return d.getDate();
            case 'dd': return zeroize(d.getDate());
            case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
            case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
            case 'M': return d.getMonth() + 1;
            case 'MM': return zeroize(d.getMonth() + 1);
            case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
            case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
            case 'yy': return String(d.getFullYear()).substr(2);
            case 'yyyy': return d.getFullYear();
            case 'h': return d.getHours() % 12 || 12;
            case 'hh': return zeroize(d.getHours() % 12 || 12);
            case 'H': return d.getHours();
            case 'HH': return zeroize(d.getHours());
            case 'm': return d.getMinutes();
            case 'mm': return zeroize(d.getMinutes());
            case 's': return d.getSeconds();
            case 'ss': return zeroize(d.getSeconds());
            case 'l': return zeroize(d.getMilliseconds(), 3);
            case 'L': var m = d.getMilliseconds();
                if (m > 99) m = Math.round(m / 10);
                return zeroize(m);
            case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
            case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
            case 'Z': return d.toUTCString().match(/[A-Z]+$/);
            // Return quoted strings with the surrounding quotes removed
            default: return $0.substr(1, $0.length - 2);
        }
    });
};

function DateAdd(now,aday,ahour,amin,asecond){
	var t=now.getTime();
	t+=aday*24*60*60*1000+ahour*60*60*1000+ascond*1000;
	d=new Date(t);
	return d;
}
//判断一个对象是否为空，如果是空则用layer在页面的中央打印出message。并返回false。message持续默认的时间长度，如需修改，请自行定义。
function checknull( mytemp,mymessage){
	if (mytemp == "" || mytemp == null) {
		layer.msg(mymessage);
		return false;
	}
}

function getenv(temp1){
	var docsaa="";
	var count=1;
	$.get(host+"/util/getenv", function(data){
		//var a=JSON.stringify(data);
		
		
		
		var jsonaa = eval('(' + data + ')');
		//alert(jsonaa);
		for(var p in jsonaa){//遍历json对象的每个key/value对,p为key
			docsaa+="<label>\n";
			docsaa+=("<input type='radio' name='optionsRadios' id=test"+count+" value='http://"+p+"/'>"+p+"\n");
			docsaa+="</label>\n";
			count++;
			}
		//alert(count);
		$("#type").append(docsaa);
	//	alert(temp1);
		$(temp1).prop("checked",true);
		});
	
	return docsaa;
}
/**
 *  把一个字符串转换成JSON对象
 * @param str
 * @returns
 */
function strToJson(str){ 
	var json = eval('(' + str + ')'); 
	return json; 
	} 



