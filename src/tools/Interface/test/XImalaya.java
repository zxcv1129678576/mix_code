package tools.Interface.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import tools.util.HttpUtil;

public class XImalaya {
	public  static final Logger logger = Logger.getLogger(XImalaya.class);
	
	public static void main(String[] args) {
		
		Map<String , String> aa=new HashMap<>();
		Map<String , String> test=new HashMap<>();
		aa.put("Connection", "keep-alive");
		aa.put("Cache-Control", "max-age=0");
		aa.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
		aa.put("Upgrade-Insecure-Requests", "1");
		aa.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		aa.put("Accept-Encoding", "gzip, deflate");
		aa.put("Accept-Language", " zh-CN,zh;q=0.9");
		aa.put("Cookie", "_xmLog=xm_1511351829016_jaazuuqgj3nw69; _gat=1; trackType=web; x_xmly_traffic=utm_source%3A%26utm_medium%3A%26utm_campaign%3A%26utm_content%3A%26utm_term%3A%26utm_from%3A;Hm_lvt_4a7d8ec50cfd6af753c4f8aee3425070=1511351830; Hm_lpvt_4a7d8ec50cfd6af753c4f8aee3425070=1511352512; _ga=GA1.2.1709977172.1511351830");
		//aa.put("Connection", "keep-alive");
		//aa.put("Connection", "keep-alive");
	
		
		HttpUtil.doGet("http://www.ximalaya.com/8889234/album/3703879/");
		HttpUtil.doGet("http://www.ximalaya.com/8889234/album/3703879/", test, aa);
		System.out.println(HttpUtil.getEntitys());
		System.out.println(HttpUtil.getStatus());
		logger.info(HttpUtil.getEntitys());
		
	}
	
	
}
