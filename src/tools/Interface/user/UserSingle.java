package tools.Interface.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import tools.EnvironmentUtil.接口;
import tools.EnvironmentUtil.接口.user;
import tools.EnvironmentUtil.接口地址;
import tools.util.HttpUtil;

public class UserSingle {
	
	
	public  static  Logger logger = Logger.getLogger( UserSingle.class);
	/**
	 * 获取登录状态
	 * @param username
	 * @return
	 */
	public static String GetLoginCookie(String username){
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
		
		return token;
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
		HttpUtil.doGet(user.获取商品评价+id);
		logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
	}
	
	public static String AddCart(String items,String username){
		
		String token=GetLoginCookie(username);
		if(token.isEmpty()){
			System.out.println("未登陆成功");
			return "未登陆成功";
		}
		int count=1;
		String result="";
		String[] array=items.split(",");
		List<Map<String, String>> newthingslist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < array.length; i++) {
			String url=user.获取商品SKU信息+array[i];
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
				map0.put("mode","cart_buy");
				map0.put("item[quantity]","1");
				map0.put("item[sku_id]",temp.getString("sku_id"));
				map0.put("email","");
				HttpUtil.setCookieOfHeader(token);
				HttpUtil.doPost(tools.EnvironmentUtil.接口地址.user.加入购物袋,map0);
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
	
	
	
	
	public static void main(String[] args) {
//		GetItemRate("23303");
//		GetItemSKU("23303");
	}
	
	
	
		
}
