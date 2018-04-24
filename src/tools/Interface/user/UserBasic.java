package tools.Interface.user;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.cookie.Cookie;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.EnvironmentUtil.接口.user;
import tools.EnvironmentUtil.接口地址;
import tools.util.HttpTools;
import tools.util.HttpUtil;
import tools.util.RandomCharsUtil;
import tools.util.UrlParametersUtil;
/** 
*@author  作者 : 陈达
*@date 创建时间：May 10, 2017 4:04:19 PM
*@version 1.0 
*@parameter  
*@return 
*/

public class UserBasic {
	
	HttpTools htools=new HttpTools();
	public  static  Logger logger = Logger.getLogger( UserBasic.class);
	static Map<String,String> map2=new HashMap<>();
	static{
		map2.put("Connection","keep-alive");
		map2.put("Accept","*/*");
		map2.put("Origin","http://view.trc.com");
		map2.put("X-Requested-With","XMLHttpRequest");
		map2.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
		map2.put("Referer","http://view.trc.com/member-idcard.html");
		map2.put("Accept-Encoding","gzip, deflate");
		map2.put("Accept-Language","zh-CN,zh;q=0.8");
}


	/**
	 * 获取登录返回报文
	 * @param username
	 * @return
	 */
	public static String GetLoginResponse(String username){
		HttpUtil.clearsession();
		Map<String,String> map2=new HashMap<>();
		map2.put("phone",username);
		map2.put("rememberMe","true");
		map2.put("password","U17nPIZhFbmXMGCJlO21H0py8vMW1RdfadwooPj4FYquYD8GJMKVmZKhufMTjJYhhMikWuOJHBfYq/Bw/da1Cf9Vq68EAdId+cK03QpddQjfOZ7ewtxLAFXfMCc5uFlqy/wVOgy0HH4gCZ9pL/W3ZGDng66Zn+hFBzpMyr8vjA8=");
		HttpUtil.doPost(user.登录, map2);
		System.out.println(HttpUtil.getEntitys());
		List<Cookie> aa=HttpUtil.getMyCookie();
		String token="";
		for (Cookie aaa:aa) {
			if("token".equals(aaa.getName())){
				 token="token="+aaa.getValue()+";";
				 System.out.println(token);
				}
		}
		return HttpUtil.getEntitys();
		}
	/**
	 * 获取商品评价
	 * @param id
	 * @return
	 */
	public static String GetItemRate(String id){
		HttpUtil.clearsession();
		HttpUtil.doGet(user.获取商品评价+id);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
	}
	/**
	 * 获取商品的SKU
	 * @param id
	 * @return
	 */
	public static String GetItemSKU(String id){
		HttpUtil.clearsession();
		HttpUtil.doGet(user.获取商品SKU信息+id);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
	}
	public static String GetItemSKU(String huanjing,String id){
		HttpUtil.clearsession();
		HttpUtil.doGet(huanjing+"item-getSpecSku.html?item_id="+id);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
	}
	

	public static String 登录获取cookie1(String username){
		Map<String,String> map2=new HashMap<>();
		map2.put("phone",username);
		map2.put("rememberMe","true");
		map2.put("password","admin123");
		String test="https://passport.trc.com/proxy/account/user/new_login";
		HttpUtil.doPost(test, map2);
		System.out.println(HttpUtil.getEntitys());
		List<Cookie> aa=HttpUtil.getMyCookie();
		String token="";
		for (Cookie aaa:aa) {
			if("token".equals(aaa.getName())){
				 token="token="+aaa.getValue()+";";
				 System.out.println(token);
				}
		}
		if(token.isEmpty()){
			System.out.println("未登陆成功");
			return "未登陆成功";
		}
		HttpUtil.setCookieOfHeader(token);
		return token;
		
	}	
	
	
	public static String 登录获取cookie(String username){
		Map<String,String> map2=new HashMap<>();
		map2.put("phone",username);
		map2.put("rememberMe","true");
		map2.put("password","U17nPIZhFbmXMGCJlO21H0py8vMW1RdfadwooPj4FYquYD8GJMKVmZKhufMTjJYhhMikWuOJHBfYq/Bw/da1Cf9Vq68EAdId+cK03QpddQjfOZ7ewtxLAFXfMCc5uFlqy/wVOgy0HH4gCZ9pL/W3ZGDng66Zn+hFBzpMyr8vjA8=");
		HttpUtil.doPost(user.登录, map2);
		System.out.println(HttpUtil.getEntitys());
		List<Cookie> aa=HttpUtil.getMyCookie();
		String token="";
		for (Cookie aaa:aa) {
			if("token".equals(aaa.getName())){
				 token="token="+aaa.getValue()+";";
				 System.out.println(token);
				}
		}
		if(token.isEmpty()){
			System.out.println("未登陆成功");
			return "未登陆成功";
		}
		HttpUtil.setCookieOfHeader(token);
		return token;
	}	
	
	
	
	public static String 登录获取cookie111(String username){
		Map<String,String> map2=new HashMap<>();
		map2.put("phone",username);
		map2.put("rememberMe","true");
		map2.put("password","U17nPIZhFbmXMGCJlO21H0py8vMW1RdfadwooPj4FYquYD8GJMKVmZKhufMTjJYhhMikWuOJHBfYq/Bw/da1Cf9Vq68EAdId+cK03QpddQjfOZ7ewtxLAFXfMCc5uFlqy/wVOgy0HH4gCZ9pL/W3ZGDng66Zn+hFBzpMyr8vjA8=");
		HttpUtil.doPost(user.登录, map2);
		System.out.println(HttpUtil.getEntitys());
		List<Cookie> aa=HttpUtil.getMyCookie();
		String token="";
		for (Cookie aaa:aa) {
			if("token".equals(aaa.getName())){
				 token=aaa.getValue();
				 System.out.println(token);
				}
		}
		if(token.isEmpty()){
			System.out.println("未登陆成功");
			return "未登陆成功";
		}
		HttpUtil.setCookieOfHeader(token);
		return token;
	}	
	
	public static JSONObject 添加到购物袋(String huanjing,String items,String username){
	//	HttpUtil.clearsession();
		JSONObject myresult=new JSONObject();
		JSONObject temp1=new JSONObject();
		String token=登录获取cookie(username);
//		if(token.isEmpty()){
//			System.out.println("未登陆成功");
//			myresult.put("success", false);
//			myresult.put("des", "未登陆成功");
//			return myresult;
//		}
		int count=1; 
		String result="";
		String[] array=items.split(",");
		List<Map<String, String>> newthingslist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < array.length; i++) {
			String url=huanjing+"item-getSpecSku.html?item_id="+array[i];
//			String ll="http://view.trc.com/item-getSpecSku.html?item_id=23011";
			HttpUtil.doGet(url);
			String ss=HttpUtil.getEntitys();
			
			JSONObject aa=JSONObject.fromObject(ss);
			
			JSONObject resull=new JSONObject();
			Iterator it = aa.keys();  
			while(it.hasNext()){
				String key = (String) it.next();
				JSONObject temp=aa.getJSONObject(key);
				Map<String,String> map0=new HashMap<>();
				map0.put("item[item_id]",temp.getString("item_id"));
				map0.put("mode","cart_buy");
				map0.put("item[quantity]","1");
				map0.put("item[sku_id]",temp.getString("sku_id"));
				map0.put("email","");
		//	HttpUtil.setCookieOfHeader(token);
				HttpUtil.doPost(huanjing+接口地址.用户.加入购物袋,map0);
				System.out.println(HttpUtil.getEntitys());
				List<Cookie> aaaa=HttpUtil.getMyCookie();
//				System.out.println("打印cookie");
//				for (Cookie aaa:aaaa) {
//					String sss=aaa.getName()+"="+aaa.getValue()+";";
//					System.out.println(sss);
//				}
		//		HttpUtil.clearsession();
				
				temp1.put("当前是第"+count+"个SKU，"+"返回报文是：", JSONObject.fromObject(HttpUtil.getEntitys()));
			//	result="当前是第"+count+"个SKU，"+"返回报文是："+HttpUtil.getEntitys()+"\n";
			
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		myresult.put("status", "true");
		myresult.put("deatil", temp1);
		return myresult;
		}
	public static JSONObject 添加到购物袋(String items,String username){
		JSONObject myresult=new JSONObject();
		JSONObject temp1=new JSONObject();
		String token=登录获取cookie(username);
		if(token.isEmpty()){
			System.out.println("未登陆成功");
			myresult.put("success", false);
			myresult.put("des", "未登陆成功");
			return myresult;
		}
		int count=1;
		String result="";
		String[] array=items.split(",");
		List<Map<String, String>> newthingslist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < array.length; i++) {
			String url="http://view.trc.com/item-getSpecSku.html?item_id="+array[i];
//			String ll="http://view.trc.com/item-getSpecSku.html?item_id=23011";
			HttpUtil.doGet(url);
			String ss=HttpUtil.getEntitys();
			
			JSONObject aa=JSONObject.fromObject(ss);
			
			JSONObject resull=new JSONObject();
			Iterator it = aa.keys();  
			while(it.hasNext()){
				String key = (String) it.next();
				JSONObject temp=aa.getJSONObject(key);
				Map<String,String> map0=new HashMap<>();
				map0.put("item[item_id]",temp.getString("item_id"));
				map0.put("mode","cart_buy");
				map0.put("item[quantity]","1");
				map0.put("item[sku_id]",temp.getString("sku_id"));
				map0.put("email","");
				HttpUtil.setCookieOfHeader(token);
				HttpUtil.doPost(user.加入购物袋,map0);
				System.out.println(HttpUtil.getEntitys());
				List<Cookie> aaaa=HttpUtil.getMyCookie();
//				System.out.println("打印cookie");
//				for (Cookie aaa:aaaa) {
//					String sss=aaa.getName()+"="+aaa.getValue()+";";
//					System.out.println(sss);
//				}
				HttpUtil.clearsession();
				
				temp1.put("当前是第"+count+"个SKU，"+"返回报文是：", JSONObject.fromObject(HttpUtil.getEntitys()));
			//	result="当前是第"+count+"个SKU，"+"返回报文是："+HttpUtil.getEntitys()+"\n";
			
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		myresult.put("status", "true");
		myresult.put("deatil", temp1);
		return myresult;
		}
	public static String 快速购买添加到购物袋(String items,String username){
		
		String token=登录获取cookie(username);
		if(token.isEmpty()){
			System.out.println("未登陆成功");
			return "未登陆成功";
		}
		int count=1;
		String result="";
		String[] array=items.split(",");
		List<Map<String, String>> newthingslist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < array.length; i++) {
			String url="http://view.trc.com/item-getSpecSku.html?item_id="+array[i];
//			String ll="http://view.trc.com/item-getSpecSku.html?item_id=23011";
			HttpUtil.doGet(url);
			String ss=HttpUtil.getEntitys();
			
			JSONObject aa=JSONObject.fromObject(ss);
			Iterator it = aa.keys();  
			while(it.hasNext()){
				String key = (String) it.next();
				JSONObject temp=aa.getJSONObject(key);
				Map<String,String> map0=new HashMap<>();
				map0.put("item[item_id]",temp.getString("item_id"));
				map0.put("mode","fast_buy");
				map0.put("item[quantity]","1");
				map0.put("item[sku_id]",temp.getString("sku_id"));
				map0.put("email","");
				HttpUtil.setCookieOfHeader(token);
				HttpUtil.doPost(user.加入购物袋,map0);
				System.out.println(HttpUtil.getEntitys());
				List<Cookie> aaaa=HttpUtil.getMyCookie();
				System.out.println("打印cookie");
				for (Cookie aaa:aaaa) {
					String sss=aaa.getName()+"="+aaa.getValue()+";";
					System.out.println(sss);
				}
				HttpUtil.clearsession();
				result="当前是第"+count+"个SKU，"+"返回报文是："+HttpUtil.getEntitys()+"\n";
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
		}
	
	
	public static Map<String,String>  快速购买获取购物袋MD5(String user){
		String token=登录获取cookie(user);
		if(token.isEmpty()){
			System.out.println("未登陆成功");
//			return "未登陆成功";
		}
		Map<String,String> map1=new HashMap<>();
		String url=tools.EnvironmentUtil.接口.user.快速购买初始化;
		HttpUtil.setCookieOfHeader(token);
		HttpUtil.doGet(url);
		String result=HttpUtil.getEntitys();
		System.out.println(result);
		JSONObject aa=JSONObject.fromObject(result);
		JSONObject aa1=aa.getJSONObject("data");
		JSONArray aa2=aa1.getJSONArray("addrList");
		JSONObject aa3=aa1.getJSONObject("idCard");
		JSONArray aa4=aa3.getJSONArray("data");
		String md5=aa1.getString("md5CartInfo");
		System.out.println("购物袋的MD5是："+md5);
		map1.put("md5_cart_info", md5);
		map1.put("addr_id", aa2.getJSONObject(0).getString("addr_id"));
		map1.put("card_id", aa4.getJSONObject(0).getString("card_id"));
		System.out.println(aa2.getJSONObject(0).getString("addr_id"));
		System.out.println(aa4.getJSONObject(0).getString("card_id"));
		HttpUtil.clearsession();
		return map1;
	}
	
	public static Map<String,String>  获取购物袋MD5(String user){
		String token=登录获取cookie(user);
		if(token.isEmpty()){
			System.out.println("未登陆成功");
//			return "未登陆成功";
		}
		Map<String,String> map1=new HashMap<>();
		String url=tools.EnvironmentUtil.接口.user.购物袋购买初始化;
		HttpUtil.setCookieOfHeader(token);
		HttpUtil.doGet(url);
		String result=HttpUtil.getEntitys();
		System.out.println(result);
		JSONObject aa=JSONObject.fromObject(result);
		JSONObject aa1=aa.getJSONObject("data");
		JSONArray aa2=aa1.getJSONArray("addrList");
		JSONObject aa3=aa1.getJSONObject("idCard");
		JSONArray aa4=aa3.getJSONArray("data");
		String md5=aa1.getString("md5CartInfo");
		System.out.println("购物袋的MD5是："+md5);
		map1.put("md5_cart_info", md5);
		map1.put("addr_id", aa2.getJSONObject(0).getString("addr_id"));
		map1.put("card_id", aa4.getJSONObject(0).getString("card_id"));
		System.out.println(aa2.getJSONObject(0).getString("addr_id"));
		System.out.println(aa4.getJSONObject(0).getString("card_id"));
		HttpUtil.clearsession();
		return map1;
	}
	
	
	
	
	
	public static List<String> 获取收货地址(String username){
		List<String> s=new ArrayList<>();
		HttpUtil.clearsession();
		String token=登录获取cookie(username);
		String target=user.收货地址列表页;
		HttpUtil.setCookieOfHeader(token);
		HttpUtil.doGet(target);
		String ss=HttpUtil.getEntitys();
//		System.out.println(ss);
		Document doc= Jsoup.parse(ss);
		Elements nodes=doc.select("a:contains(编辑)");
		for (Element e:nodes) {
			String[] ssss=e.attr("data-ajax-data").split("=");
			System.out.println("获取到的收货地址编号是："+ssss[1]);
			s.add(ssss[1]);
		}
		return s;
	} 
	
	public static List<String> 获取身份证(String username){
		List<String> s=new ArrayList<>();
		HttpUtil.clearsession();
		String token=登录获取cookie(username);
		String target=user.身份证列表页;
		HttpUtil.setCookieOfHeader(token);
		HttpUtil.doGet(target);
		String ss=HttpUtil.getEntitys();
//		System.out.println(ss);
		Document doc= Jsoup.parse(ss);
		Elements nodes=doc.select("a:contains(查看)");
		for (Element e:nodes) {
			String[] ssss=e.attr("data-ajax-data").split("=");
			System.out.println("获取到的身份证地址编号是："+ssss[1]);
			s.add(ssss[1]);
		}
		return s;
	}
	
	
	public static String test购物袋(String items,String username){
		
//		String zzxx="http://view.trc.com/";
//		HttpUtil.doGet(zzxx);
////		System.out.println(HttpUtil.getEntitys());
////		HttpUtil.printEntity();
//		HttpUtil.printCookie();
//		
//		String zzxx1="http://passport.trc.com/";
//		HttpUtil.doGet(zzxx1);
////		System.out.println(HttpUtil.getEntitys());
////		HttpUtil.printEntity();
//		HttpUtil.printCookie();
		
		String token=登录获取cookie(username);
		if(token.isEmpty()){
			System.out.println("未登陆成功");
			return "未登陆成功";
		}
//		HttpUtil.setCookieOfHeader(token);
		
//		HttpUtil.doGet(zzxx);
//		System.out.println("***************");
//		HttpUtil.printCookie();
		int count=1;
		String result="";
		String[] array=items.split(",");
		List<Map<String, String>> newthingslist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < array.length; i++) {
			String url="http://view.trc.com/item-getSpecSku.html?item_id="+array[i];
			String ll="http://view.trc.com/item-getSpecSku.html?item_id=23011";
			HttpUtil.doGet(url);
			String ss=HttpUtil.getEntitys();
			
			JSONObject aa=JSONObject.fromObject(ss);
			Iterator it = aa.keys();  
			while(it.hasNext()){
				String key = (String) it.next();
				JSONObject temp=aa.getJSONObject(key);
				Map<String,String> map0=new HashMap<>();
				map0.put("item[item_id]",temp.getString("item_id"));
				map0.put("mode","cart_buy");
				map0.put("item[quantity]","1");
				map0.put("item[sku_id]",temp.getString("sku_id"));
				map0.put("email","");
				HttpUtil.setCookieOfHeader(token);
				System.out.println("token是："+token);
				HttpUtil.doPost(user.加入购物袋,map0);
				System.out.println(HttpUtil.getEntitys());
				List<Cookie> aaaa=HttpUtil.getMyCookie();
				System.out.println("打印cookie");
//				token="";
				for (Cookie aaa:aaaa) {
					String sss=aaa.getName()+"="+aaa.getValue()+";";
					System.out.println(sss);
//					token+=sss;
				}
				HttpUtil.clearsession();
				result="当前是第"+count+"个SKU，"+"返回报文是："+HttpUtil.getEntitys()+"\n";
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	
	
	
	public static String 购物袋下单(String username){
		HttpUtil.clearsession();
		String token=登录获取cookie(username);

		Map<String,String> a=获取购物袋MD5(username);	
//		if(token.isEmpty()){
//			System.out.println("未登陆成功");
////			return "未登陆成功";
//		}
		
		Map<String,String> map0=new HashMap<>();
		map0.put("mode","cart_buy");
		map0.put("addr_id",a.get("addr_id"));
		map0.put("card_id",a.get("card_id"));
		map0.put("md5_cart_info",a.get("md5_cart_info"));
		map0.put("buyer_message[0]","1");
		map0.put("buyer_message[1]","2");
		map0.put("payment_type","online");
		//HttpUtil.setCookieOfHeader(token);
		HttpUtil.doPost(user.购物袋下订单, map0);
		String re=HttpUtil.getEntitys();
		System.out.println(re);
		return re;
	};
	
	public static String 立即购买(String username,String id){
		logger.info("test！！！！");
			快速购买添加到购物袋(id, username);
			HttpUtil.clearsession();
			Map<String,String> a=快速购买获取购物袋MD5(username);
			HttpUtil.clearsession();
			String token=登录获取cookie(username);
			if(token.isEmpty()){
				System.out.println("未登陆成功");
//				return "未登陆成功";
			}
			Map<String,String> map0=new HashMap<>();
			map0.put("mode","fast_buy");
			map0.put("addr_id",a.get("addr_id"));
			map0.put("card_id",a.get("card_id"));
			map0.put("md5_cart_info",a.get("md5_cart_info"));
			map0.put("buyer_message[0]","1");
			map0.put("buyer_message[1]","2");
			map0.put("payment_type","online");
			HttpUtil.setCookieOfHeader(token);
			HttpUtil.doPost(user.购物袋下订单, map0);
			String re=HttpUtil.getEntitys();
			System.out.println(re);
			return re;
	}
	/**
	 * 未实现
	 * @param username
	 * @param id
	 * @return
	 */
	public static String 添加收藏(String username,String id){
		String token=登录获取cookie(username);
		Map<String,String> map0=new HashMap<>();
		map0.put("item_id",id);
		map2.put("Cookie", token);
		HttpUtil.doPost(user.收藏商品, map0,map2);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
		}
/**
 *  因为session态的问题，需要从session里获取。
 * @param username
 * @param id
 * @return
 */

	public static String 取消收藏(String username,String id){
		String token=登录获取cookie(username);
		Map<String,String> map0=new HashMap<>();
		map0.put("item_id",id);
		map2.put("Cookie", token);
		HttpUtil.doPost(user.取消收藏, map0,map2);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
		
	}
	
	public static String 添加身份证(String username){
		HttpUtil.clearsession();
		登录获取cookie(username);
		Map<String,String> map0=new HashMap<>();
		map0.put("card_id","");
		map0.put("token","");
		map0.put("platform", "PC");
		map0.put("name","陈达");
		map0.put("idnumber","370323199401250816");
		map0.put("idcard[0]","https://image.trc.com/FtvQgxKR-99xDuqa9tsifC7BNiOy");
		map0.put("idcard[1]","https://image.trc.com/FiVRYbIjpVh2yTmiKOfGX0QTixdv");
		HttpUtil.doPost(user.添加身份证, map0);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
	}
	/**
	 * 未实现
	 * @param username
	 * @return
	 */
	public static String 删除身份证(String username){
		HttpUtil.clearsession();
		List<String> s=获取身份证(username);
		if(s.size()<=1){
			JSONObject check=new JSONObject();
			check.put("success", false);
			check.put("des","没有可以删除的身份证");
			return check.toString();
		}
		Map<String,String> map1=new HashMap<>();
		map1.put("card_id",s.get(0));
		HttpUtil.clearsession();
		String token=登录获取cookie(username);
//		String cookie="origin=android; phone=18200000000; token=4CC3006E503E40AE8A17C51868D987A8.A8510914F76B30E77C3DCB78C5417B98; s=9e65ae8da529173f28e00283c998609d1ef5eb80; UNAME=18200000000";
//		HttpUtil.setCookieOfHeader(cookie);
		map2.put("Cookie",token);
		System.out.println("111");
		HttpUtil.doPost(user.删除身份证, map1,map2);
		System.out.println("111");
		logger.info(HttpUtil.getEntitys());
		System.out.println("111");
		return HttpUtil.getEntitys();
	}
	
	
	
	
	public static String 添加收货地址1(String username,String name,String address){
		HttpUtil.clearsession();
		map2.put("Cookie", 登录获取cookie(username));
		
		Map<String,String> map3=new HashMap<>();
		map3.put("addr_id","");
		map3.put("area[]","120100,120101");
		map3.put("addr",address);
		map3.put("token","a149091eeb8d487d28fd52d183571d84");
		map3.put("name",name);
		map3.put("mobile","18758017446");
		map3.put("def_addr","1");
		map3.put("response_json","true");
		HttpUtil.doPost(user.添加收货地址, map3,map2);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
	}
	
	
	public static String 添加收货地址(String username){
		HttpUtil.clearsession();
		map2.put("Cookie", 登录获取cookie(username));
		
		Map<String,String> map3=new HashMap<>();
		map3.put("addr_id","");
		map3.put("area[]","120100,120101");
		map3.put("addr","哈哈哈哈哈");
		map3.put("token","a149091eeb8d487d28fd52d183571d84");
		map3.put("name","陈达");
		map3.put("mobile","18758017446");
		map3.put("def_addr","1");
		map3.put("response_json","true");
		HttpUtil.doPost(user.添加收货地址, map3,map2);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
	}
	
	
	public static String 删除收货地址(String username){
		HttpUtil.clearsession();
		List<String> s=获取收货地址(username);
		if(s.size()<=1){
			JSONObject check=new JSONObject();
			check.put("success", false);
			check.put("des","没有可以删除的收货地址");
			return check.toString();
		
		}
		Map<String,String> map1=new HashMap<>();
		map1.put("addr_id",s.get(s.size()-1));
		HttpUtil.clearsession();
		String token=登录获取cookie(username);
		map2.put("Cookie",token);
		System.out.println("111");
		HttpUtil.doPost(user.删除收货地址, map1,map2);
		System.out.println("111");
		logger.info(HttpUtil.getEntitys());
		System.out.println("111");
		return HttpUtil.getEntitys();
	}
	
	public static String 支付订单(String phone,String itemid){
		HttpUtil.clearsession();
		String order=立即购买("18200000000", "143");
		logger.info(HttpUtil.getEntitys());
		JSONObject order1=JSONObject.fromObject(HttpUtil.getEntitys());
		String orderid=order1.getString("data");
		HttpUtil.clearsession();
		登录获取cookie("18200000000");
		Map<String,String> map0=new HashMap<>();
		map0.put("order_id",orderid);
		map0.put("merge","false");
		HttpUtil.doPost(user.创建收银台支付单, map0);
		logger.info(HttpUtil.getEntitys());
		JSONObject check=JSONObject.fromObject(HttpUtil.getEntitys());
		String redirt=check.getString("redirect");
		logger.info(redirt);
		String payid=UrlParametersUtil.ParamsGet(redirt,"payId");

	//	String payid=redirt.substring(38, 62);
		
		logger.info("payid:"+payid);
		
		HttpUtil.clearsession();
		String token=登录获取cookie("18200000000");
		HttpUtil.doGet(user.获取订单信息+orderid);
		String ss=HttpUtil.getEntitys();
//		System.out.println(ss);
		Document doc= Jsoup.parse(ss);
		Element node=doc.select("em.price").get(0);
		String price=node.text();
		String price1=price.substring(1, price.length());
		
		Map<String,String> map4=new HashMap<>();
		map4.put("payId",payid);
		map4.put("payPassword","admin123");
		map4.put("amount",price1);
		map4.put("cardList","[{\"cardId\":\"DCFAF2BCF12A4AFDAE5B6337582378AB\",\"isLast\":true}]");
		
		map2.put("Cookie",token);
//		Map<String,String> map1=new HashMap<>();
		HttpUtil.doPostSSL(user.支付接口,map4,map2);
		
		logger.info(HttpUtil.getEntitys());
		
		
		HttpUtil.clearsession();
		登录获取cookie("18200000000");
		HttpUtil.doPost(user.创建收银台支付单, map0);
		logger.info(HttpUtil.getEntitys());
		JSONObject re=JSONObject.fromObject(HttpUtil.getEntitys());
		String back=re.getString("redirect");
		
		String back2=UrlParametersUtil.ParamsGet(back,"payId");
		System.out.println("back2:"+back2);
//		String back2=back.substring(back.length()-20, back.length());
		HttpUtil.doGet(user.回调订单状态+back2);
//		logger.info(HttpUtil.getEntitys());
		HttpUtil.clearsession();
		登录获取cookie(phone);
		HttpUtil.doGet(user.获取订单信息+orderid);
		String resss=HttpUtil.getEntitys();
//		System.out.println(ss);
		Document doc11= Jsoup.parse(resss);
		Element node11=doc11.select("span.order-status").get(0);
		String result=node11.text();
		logger.info(result);	
		
		JSONObject info=new JSONObject();
		info.put("orderid", orderid);
		info.put("payid", payid);
		info.put("backid", back2);
		info.put("data", result);
		return info.toString();
	}
	public static String 支付订单11(String phone,String orderid){

//		HttpUtil.clearsession();
		登录获取cookie(phone);
		Map<String,String> map0=new HashMap<>();
		map0.put("order_id",orderid);
		map0.put("merge","false");
		HttpUtil.doPost(user.创建收银台支付单, map0);
		logger.info(HttpUtil.getEntitys());
		JSONObject check=JSONObject.fromObject(HttpUtil.getEntitys());
		String redirt=check.getString("redirect");
		logger.info(redirt);
		String payid=UrlParametersUtil.ParamsGet(redirt,"payId");

	//	String payid=redirt.substring(38, 62);
		logger.info("payid:"+payid);
		
//		HttpUtil.clearsession();
//		String token=登录获取cookie(phone);
		HttpUtil.doGet(user.获取订单信息+orderid);
		String ss=HttpUtil.getEntitys();
//		System.out.println(ss);
		Document doc= Jsoup.parse(ss);
		Element node=doc.select("em.price").get(0);
		String price=node.text();
		String price1=price.substring(1, price.length());
		
		Map<String,String> map4=new HashMap<>();
		map4.put("payId",payid);
		map4.put("payPassword","admin123");
		map4.put("amount",price1);
		map4.put("cardList","[{\"ecardType\":\"ECARD_ACTIVITY\",\"subAmount\":"+price1+"}]");
		
//		map2.put("Cookie",token);
//		Map<String,String> map1=new HashMap<>();
		HttpUtil.doPostSSL(user.支付接口,map4);

		logger.info(HttpUtil.getEntitys());
		

//		HttpUtil.clearsession();
//		登录获取cookie(phone);
		HttpUtil.doPost(user.创建收银台支付单, map0);
		logger.info(HttpUtil.getEntitys());
		JSONObject re=JSONObject.fromObject(HttpUtil.getEntitys());
		String back=re.getString("redirect");
		System.out.println("*************");
		System.out.println(back);
	//	String back2=UrlParametersUtil.ParamsGet(back,"payment_id");
		String back2=back.substring(back.length()-20, back.length());

		System.out.println("back2:"+back2);
		

//		String back2=back.substring(back.length()-20, back.length());
		HttpUtil.doGet(user.回调订单状态+back2);
//		logger.info(HttpUtil.getEntitys());
		
//		HttpUtil.clearsession();
//		登录获取cookie("18200000000");
//		HttpUtil.doGet(user.获取订单信息+orderid);
//		String resss=HttpUtil.getEntitys();
//		System.out.println(resss);
//		Document doc11= Jsoup.parse(resss);
//		
//		//
//		Element node11=doc11.select("span.order-status").get(0);
//		String result=node11.text();
//		logger.info(result);	
		
		JSONObject info=new JSONObject();
		info.put("orderid", orderid);
		info.put("payid", payid);
		info.put("backid", back2);
		info.put("data", "ssss");
		return info.toString();
	}
	

	public static String 确认收货(String phone, String orderid){
		登录获取cookie(phone);
		Map<String,String> map0=new HashMap<>();
		map0.put("tid",orderid);
		map0.put("response_json","true");
		HttpUtil.doPost(user.确认收货, map0);
		String reString =HttpUtil.getEntitys();
		logger.info(reString);	
		return reString;
		
	}
	public static String 取消订单(String phone, String orderid){
		登录获取cookie(phone);
		Map<String,String> map1=new HashMap<>();
		map1.put("tid",orderid);
		map1.put("cancel_reason","2");
		map1.put("other_reason","");
		map1.put("response_json","true");
		HttpUtil.doPost(user.取消订单, map1);
		String reString =HttpUtil.getEntitys();
		logger.info(reString);	
		return reString;
	}
	public static String 获取订单金额(String phone, String orderid){
		HttpUtil.clearsession();
		String token=登录获取cookie("18200000000");
		HttpUtil.doGet(user.获取订单信息+orderid);
		String ss=HttpUtil.getEntitys();
//		System.out.println(ss);
		Document doc= Jsoup.parse(ss);
		Element node=doc.select("em.price").get(0);
		String price=node.text();
		String price1=price.substring(1, price.length());
		return price1;
	}
	
	public static String 获取订单的店铺级订单(String phone, String orderid){
		HttpUtil.clearsession();
		String token=登录获取cookie("18200000000");
		HttpUtil.doGet(user.获取订单信息+orderid);
		String ss=HttpUtil.getEntitys();
//		System.out.println(ss);
		Document doc= Jsoup.parse(ss);
		Element node=doc.select("a:contains(申请售后)").get(0);
		String oids=node.attr("href");
		
		String oid=oids.substring(oids.length()-43, oids.length()-24);
		logger.info(oid);
		return oid;
	}
	
	public static String 申请退款(String phone, String orderid){
		String price=获取订单金额(phone, orderid);
		String oid =获取订单的店铺级订单(phone, orderid);
		Map<String,String> map3=new HashMap<>();
		map3.put("aftersales_type","ONLY_REFUND");
		map3.put("tid",orderid);
		map3.put("oid",oid);
		map3.put("num","1");
		map3.put("shop_id","2");
		map3.put("reason","商品与描述不符");
		map3.put("refund_amount",price);
		map3.put("description","10000啊实打实的");
		map3.put("evidence_pic[]","https://image.trc.com/FhcGIIprSvVztwu631v0Eh4DOaYv");
		map3.put("response_json","true");
		map2.put("Cookie", 登录获取cookie(phone));
		HttpUtil.doPost(user.申请售后,map3,map2);
		String re = HttpUtil.getEntitys();
		logger.info(re);
		return re;
	}
	public static String 申请退货退款(String phone, String orderid){
		String price=获取订单金额(phone, orderid);
		String oid =获取订单的店铺级订单(phone, orderid);
		Map<String,String> map3=new HashMap<>();
		map3.put("aftersales_type","REFUND_GOODS");
		map3.put("tid",orderid);
		map3.put("oid",oid);
		map3.put("num","1");
		map3.put("shop_id","2");
		map3.put("reason","商品与描述不符");
		map3.put("refund_amount",price);
		map3.put("description","10000啊实打实的");
		map3.put("evidence_pic[]","https://image.trc.com/FhcGIIprSvVztwu631v0Eh4DOaYv");
		map3.put("response_json","true");
		map2.put("Cookie", 登录获取cookie(phone));
		HttpUtil.doPost(user.申请售后,map3,map2);
		String re = HttpUtil.getEntitys();
		logger.info(re);
		return re;
	}
	

	
	public static String 评价订单(String phone, String orderid){
		String init=user.订单评价数据初始化+orderid;
		登录获取cookie(phone);
		HttpUtil.doGet(init);
		String re1=HttpUtil.getEntitys();
		logger.info(re1);
		JSONObject jo=JSONObject.fromObject(re1);
		JSONObject data=jo.getJSONObject("data");
		JSONObject shop=data.getJSONArray("shop_orders").getJSONObject(0);
		JSONObject good=shop.getJSONArray("good_orders").getJSONObject(0);
		String oid=good.getString("id");
		logger.info(oid);
		Map<String,String> map4=new HashMap<>();
		map4.put("id",orderid);
		map4.put("anonymous","1");
		map4.put("rates[0][images][]","https://image.trc.com/FqL5nqgTP_pgvdYeax3IsG4BOiHb");
		map4.put("rates[0][order_id]",oid);
		map4.put("rates[0][content]","11213啊大大说多三大大滴");
		map4.put("rates[0][experience]","Good");
		HttpUtil.doPost(user.评价订单, map4);
		String res11=HttpUtil.getEntitys();
		logger.info(res11);
		return res11;
	}
	
	
	public static String 购物袋支付(String user,String itemid){
		
//	添加到购物袋("http://view.trc.com/",itemid, user);
		
	//	登录获取cookie1(user);
		添加到购物袋("http://view.trc.com/",itemid, user);
//		添加到购物袋("http://view.trc.com/","28751", user);
//		添加到购物袋("http://view.trc.com/","28771", user);
//		添加到购物袋("http://view.trc.com/","28779", user);
//		添加到购物袋("http://view.trc.com/","28776", user);
//		添加到购物袋("http://view.trc.com/","28777", user);
//		添加到购物袋("http://view.trc.com/","28778", user);
		
		/**
		 * 店铺3
		 */
	//	添加到购物袋("http://view.trc.com/","28792", user)
//		添加到购物袋("http://view.trc.com/","28793", user);
//		添加到购物袋("http://view.trc.com/","28794", user);
//		添加到购物袋("http://view.trc.com/","28795", user);

		JSONObject js= JSONObject.fromObject(购物袋下单(user));

		String aaa=js.getString("data");
		

		支付订单11(user, aaa);
		return user;
		
	}
	
	public static String  Red(String phone){
		String token=登录获取cookie("18392081082");
		//String token="";
		String token1="token="+token+";";
		
		Map<String,String> map0=new HashMap<>();
		//Map<String,String> map1=new HashMap<>();
		Map<String,String> map1=new HashMap<>();
//		map1.put("Accept","*/*");
//		map1.put("Connection","keep-alive");
//		map1.put("screenwidth","1242");
//		map1.put("mobilefac","Apple");
//		map1.put("ostype","I");
//		map1.put("screenheight","2208");
//		map1.put("Accept-Language",":zh-Hans;q=1");
//		map1.put("Accept-Encoding","gzip, deflate");
		map1.put("token",token);
//		map1.put("deviceToken","91172d1ef3fb071a7ada663a8fe9fa045b974b45fa43d3bfa9051f94933d30b9");
//		map1.put("versions-info","5.1.0.0");
//		map1.put("mobilemod","iPhone");
//		map1.put("User-Agent","OS/iOS AppChannel/AppStore PhoneBrand/Apple OSVersion/11.1.2 Product/trc PhoneModel/iPhone IMEI/6BA4725C-CFD0-4E12-A3E4-21B321778C42 AppVersionName/5.1.0 AppVersionCode/5.1.0.0 ScreenHeight/736 ScreenWidth/414");
//		map1.put("Connection","keep-alive");
		map1.put("Cookie",token1+"version=5.1.0.0");
//		map1.put("osversion","1.1.2");
//		map1.put("version-state","0");
		
		//map0.put("Connection","keep-alive");
		HttpUtil.doGet("https://appapi.trc.com/redRain/conditionLevel/get", map0, map1);
		//HttpUtil.doGet("http://app-api.trc.com/redRain/conditionLevel/get");
		logger.info(HttpUtil.getEntitys());
		logger.info(HttpUtil.getStatus());
		
		//Map<String,String> map=new HashMap<>();
		return token;
	}
	
	
	
	public static void GetPrice(String token){
		
		
	String token1="token="+token+";";
		
		Map<String,String> map0=new HashMap<>();
		//Map<String,String> map1=new HashMap<>();
		Map<String,String> map1=new HashMap<>();
//		map1.put("Accept","*/*");
//		map1.put("Connection","keep-alive");
//		map1.put("screenwidth","1242");
//		map1.put("mobilefac","Apple");
//		map1.put("ostype","I");
//		map1.put("screenheight","2208");
//		map1.put("Accept-Language",":zh-Hans;q=1");
//		map1.put("Accept-Encoding","gzip, deflate");
		map1.put("token",token);
//		map1.put("deviceToken","91172d1ef3fb071a7ada663a8fe9fa045b974b45fa43d3bfa9051f94933d30b9");
//		map1.put("versions-info","5.1.0.0");
//		map1.put("mobilemod","iPhone");
//		map1.put("User-Agent","OS/iOS AppChannel/AppStore PhoneBrand/Apple OSVersion/11.1.2 Product/trc PhoneModel/iPhone IMEI/6BA4725C-CFD0-4E12-A3E4-21B321778C42 AppVersionName/5.1.0 AppVersionCode/5.1.0.0 ScreenHeight/736 ScreenWidth/414");
//		map1.put("Connection","keep-alive");
		map1.put("Cookie",token1+"version=5.1.0.0");
//		map1.put("osversion","1.1.2");
//		map1.put("version-state","0");
		Map<String,String> map2=new HashMap<>();
		//Map<String,String> map0=new HashMap<>();
		map2.put("amount","2");
		map2.put("content","63d200eca66d395f72fc5eb3d3cc4d56");
		map2.put("randCode","DbYlqd");
		HttpUtil.doPost("https://appapi.trc.com/redRain/getPrice/get",map2,map1);		
		logger.info(HttpUtil.getEntitys());
		logger.info(HttpUtil.getStatus());
		
	}
	public static void main(String[] args) {
		
		//支付订单11("18758017446", "1708311832513570345");
		//1708311828553370345
	//	购物袋支付("18758017446","28773");
		//运费测试
	//	购物袋支付("18758017446","28776,28777,28771,28773,28751,72852");
//		添加到购物袋("http://view.trc.com/","28773,28792,28793,28794", "18758017446");
		
	//	购物袋支付("18758017446","28751");
//		购物袋支付("18758017446","28771");
//		购物袋支付("18758017446","28759");
//		添加到购物袋("");
		登录获取cookie1("18758017446");
	//	Red("18758017446");
		
		//GetPrice("850732EFF07A47AD9A491BDEEDFF0F2D.216F1F1045E1F52EA0F18F54D4991ABC");
		//添加到购物袋("23311,22869,20047,23003,22980,22998,23000,22992,22995,22997","18200000000");

		for (int i =28000; i <=30602; i++) {
			try {
				添加到购物袋("http://view.trc.com/",""+i, "18758017446");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
		 }
		}
		//添加到购物袋("http://view.trc.com/","28771", "18758017446");

////		
//		}
		//		test();
//		获取购物袋MD5("18200000000");
//		for (int i = 0; ic < 15; i++) {
//			//获取收货地址("18200000000");
//			添加收货地址1("18758017446",RandomCharsUtil.获取指定数量纯汉字(6),RandomCharsUtil.获取指定数量纯汉字(40));
//		}
		
//		获取身份证("18200000000");
//		获取购物袋MD5("18392081082");
	//购物袋下单("18758017446");
//		test购物袋("23250,23251,23252","18200000000");
	//	立即购买("18758017446", "28771");
		
//		for (int i = 30510; i < 30525; i++) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			  添加收藏("18392081082",""+i);
//	}
		  
//	 	添加身份证("18200000000");
//		获取身份证("18200000000");
//		删除身份证("18200000000");
//		删除收货地址("18200000000");	
//		GetLoginResponse("18200000000");
//		GetItemRate("23302");
//		GetItemSKU("143");
//		添加收货地址("18200000000");
//		删除收货地址("18200000000");
//		支付订单("18200000000","23268");
//		确认收货("18200000000","1705240711452728625");
//		取消订单("18200000000","1705240833541108625");
//		获取订单的店铺级订单("18200000000","1705240700083498625");
//		申请退款("18200000000","1705240702202258625");
//		评价订单("18200000000","1705240711452728625");
//		申请退货退款("18200000000","1705240715561948625");
//		for (int i = 0; i < 1; i++) {
//			System.out.println("*********这是第"+i+"个瓜皮");
//			logger.info("test");
//			立即购买("18200000000", "143");
//		}
		
		//登录获取cookie1("18758017446");
		
	}
}
