package develop.test;

import java.util.HashMap;
import java.util.Map;

import sun.util.logging.resources.logging;
import tools.util.HttpUtil;

public class Jenetertest {
	
	public static void test(){
		Map<String,String> sss=new HashMap<>();
		
		sss.put("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundarynp78yA8DTTQdC3Wv");
		sss.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		sss.put("Upgrade-Insecure-Requests", "1");
		sss.put("Referer", "http://chenda.date/member.php?mod=register");
		sss.put("Accept-Encoding", "gzip, deflate");
		sss.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		sss.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");

		Map<String,String> map0=new HashMap<>();
		map0.put("Content-Disposition: form-datame=\"referer\"","http://chenda.date/");
		map0.put("Content-Disposition: form-data; name=\"activationauth\"","");
		map0.put("Content-Disposition: form-data; name=\"a1LZH6\"","chenda2221");
		map0.put("Content-Disposition: form-data; name=\"Xi8ESZ\"","admin123");
		map0.put("Content-Disposition: form-data; name=\"XP6h7D\"","admin123");
		map0.put("Content-Disposition: form-data; name=\"iK2mA5\"","55447785224@qq.com");
		String aaa="http://chenda.date/member.php?mod=register&inajax=1";
		HttpUtil.doPost(aaa, map0,sss);
		HttpUtil.printEntity();
	
	}
	
	public static String test2(){
		
		String login="http://chenda.date//member.php?mod=logging&action=login&loginsubmit=yes&handlekey=login&loginhash=LYwWC&inajax=1";
		Map<String,String> map0=new HashMap<>();
		map0.put("formhash","53de0421");
		map0.put("referer","http://chenda.date/forum.php");
		map0.put("loginfield","username");
		map0.put("username","admin");
		map0.put("password","admin123");
		map0.put("questionid","0");
		map0.put("answer","");
		
		HttpUtil.doPost(login, map0);
		HttpUtil.printEntity();
		
		return null;
		
	}
	
	public static void main(String[] args) {
		test2();
	}
	
}
