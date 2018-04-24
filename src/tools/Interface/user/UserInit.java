package tools.Interface.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.apache.log4j.Logger;

import com.trc.util.StringUtil;

import net.sf.json.JSONObject;
import tools.EnvironmentUtil.接口;
import tools.EnvironmentUtil.接口.user;
import tools.util.HttpTools;
import tools.util.HttpUtil;

public class UserInit {
	
	public  static  Logger logger = Logger.getLogger( UserInit.class);
	
	public String register(String username){
		
		
		
		Map<String,String> map0=new HashMap<>();
		map0.put("phone",username);
		map0.put("smsCode","1311");
	//	map0.put("imgCode","aavvbb");
		
		
		
	//	map0.put("password","VsgUFSutH0Pp6BwtHyYH3XyXA7Hc4qpzG2fKJweq7Fu/zhZ3/Rptt3vS5Y0xVk5+OCZmJ+mu/GggkPYl1vNhzFvOaLtcWmaUJgtr1tmZgdzXZTRRySGylkSU2n3Y7zH468/wQn2ALjBKIcFl+XBLFUDGbTSN9BCSmF1NHOEf9+E=");
		map0.put("password","admin123");

		map0.put("inviteCode","");
		String url ="https://passport.trc.com/proxy/account/user/register";
	//	String url1 ="https://passport.trc.com/proxy/account/user/encrypt/register";
		
		HttpTools httptest =new HttpTools();
		httptest.doPost(url, map0);
		String ss=httptest.getEntitys();
		System.out.println("返回报文是："+ss);
		JSONObject aa=JSONObject.fromObject(ss);
		String userid=aa.getString("userId");
		if (StringUtil.isEmpty(userid)) {
			logger.info("注册账号："+username+"失败！");
			return "null";
		}else{
			logger.info("注册账号："+username+"成功！用户取得userid为："+userid);
			return userid;
		}
		
		
	}
	
	public void addAddress( String username){
		
	System.out.println("当前运行的手机号是："+username);
		
		
		Map<String,String> map0=new HashMap<>();
		map0.put("phone",username);
		map0.put("rememberMe","true");
		map0.put("password","VsgUFSutH0Pp6BwtHyYH3XyXA7Hc4qpzG2fKJweq7Fu/zhZ3/Rptt3vS5Y0xVk5+OCZmJ+mu/GggkPYl1vNhzFvOaLtcWmaUJgtr1tmZgdzXZTRRySGylkSU2n3Y7zH468/wQn2ALjBKIcFl+XBLFUDGbTSN9BCSmF1NHOEf9+E=");
		HttpTools httptest =new HttpTools();
		httptest.doPost(user.登录, map0);
	//	System.out.println(httptest.getEntitys());
		List<Cookie> aa=httptest.getMyCookie();
		String token="";
		for (Cookie aaa:aa) {
			if("token".equals(aaa.getName())){
				 token="token="+aaa.getValue()+";";
			//	 System.out.println(token);
				}
		}
		if(token.isEmpty()){
		//	System.out.println("未登陆成功");
			logger.info("账号未登录成功！:"+username);
		}else{
			
		}
		httptest.setCookieOfHeader(token);
		
			Map<String,String> map2=new HashMap<>();
			map2.put("Connection","keep-alive");
			map2.put("Accept","*/*");
			map2.put("Origin","http://view.trc.com");
			map2.put("X-Requested-With","XMLHttpRequest");
			map2.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
			map2.put("Referer","http://view.trc.com/member-idcard.html");
			map2.put("Accept-Encoding","gzip, deflate");
			map2.put("Accept-Language","zh-CN,zh;q=0.8");
			map2.put("Cookie", token);
		
		Map<String,String> map3=new HashMap<>();
		map3.put("addr_id","");
		map3.put("area[]","120100,120101");
		map3.put("addr","石乐志"+username);
		map3.put("token","a149091eeb8d487d28fd5"+username);
		map3.put("name","陈达");
		map3.put("mobile","18758017446");
		map3.put("def_addr","1");
		map3.put("response_json","true");
		httptest.doPost(user.添加收货地址, map3,map2);
		
		System.out.println(httptest.getEntitys());
	
		String ss=httptest.getEntitys();
		System.out.println("返回报文是："+ss);
		JSONObject dddd=JSONObject.fromObject(ss);
		String ifsuccess="null";
		try {
			 ifsuccess=dddd.getString("success");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("账号为:"+username+"返回报文异常！");
		}
	
		
		if ("true".equals(ifsuccess)){
			
		}else{
			logger.info("账号为:"+username+"添加收货地址失败");
		}
		
		httptest.doGet(user.获取商品评价+"28561");
		System.out.println(httptest.getEntitys());
		httptest.clearsession();
		httptest.shutdown();
		//return HttpUtil.getEntitys();
		}
	
	public class RegitserThread extends Thread{
		
		private int start;
		private int end;
		
		public RegitserThread(int start,int end){
			this.start=start;
			this.end=end;
			}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int a=160000;
			for (int i = start; i <=end; i++) {
				
				int temp=a+i;
				try {
					new UserInit().register("18866"+temp);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
				
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	
	
	public class AddAddressThread extends Thread{
		
		private int start;
		private int end;
		
		public AddAddressThread(int start,int end){
			this.start=start;
			this.end=end;
			}
		 UserInit aa=new UserInit();
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int a=60000;
			for (int i = start; i <=end; i++) {
				
				int temp=a+i;
				aa.addAddress("131574"+temp);
			//	aa.添加收藏("131775"+temp, "28351");
//				 if (i%10==9) {
//					 
//					 logger.info("睡会儿，让瓜皮缓口气"+i);
//					 try {
//						Thread.sleep(20000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				 
				
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//				 aa.addAddress("131775"+temp);
				 

				
			}
		}
		
	}
	
	
	
	
	public void 添加一万个瓜皮(){
		for (int i = 0; i <5; i++) {
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int start=i*20;
			int end=i*20+20;
			
			
//			int start =0;
//			int end=9999;
			
			new AddAddressThread(start, end).start();
			
		}
	};
	
	
	
	
	
	
	public void 注册一万个瓜皮(){
		for (int i = 0; i <5; i++) {
			
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int start=i*2000;
			int end=i*2000+1999;
			new RegitserThread(start, end).start();
			
		}
	}	
	
	
	public  String 添加收藏(String username,String id){
	System.out.println("当前运行的手机号是："+username);	
		Map<String,String> map0=new HashMap<>();
		map0.put("phone",username);
		map0.put("rememberMe","true");
		map0.put("password","VsgUFSutH0Pp6BwtHyYH3XyXA7Hc4qpzG2fKJweq7Fu/zhZ3/Rptt3vS5Y0xVk5+OCZmJ+mu/GggkPYl1vNhzFvOaLtcWmaUJgtr1tmZgdzXZTRRySGylkSU2n3Y7zH468/wQn2ALjBKIcFl+XBLFUDGbTSN9BCSmF1NHOEf9+E=");
		HttpTools httptest =new HttpTools();
		httptest.doPost(user.登录, map0);
	//	System.out.println(httptest.getEntitys());
		List<Cookie> aa=httptest.getMyCookie();
		String token="";
		for (Cookie aaa:aa) {
			if("token".equals(aaa.getName())){
				 token="token="+aaa.getValue()+";";
			//	 System.out.println(token);
				}
		}
		if(token.isEmpty()){
		//	System.out.println("未登陆成功");
			logger.info("账号未登录成功！:"+username);
		}else{
			
		}
		httptest.setCookieOfHeader(token);
		
		Map<String,String> map1=new HashMap<>();
		map1.put("item_id",id);
		Map<String,String> map2=new HashMap<>();
		map2.put("Connection","keep-alive");
		map2.put("Accept","*/*");
		map2.put("Origin","http://view.trc.com");
		map2.put("X-Requested-With","XMLHttpRequest");
		map2.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
		map2.put("Referer","http://view.trc.com/member-idcard.html");
		map2.put("Accept-Encoding","gzip, deflate");
		map2.put("Accept-Language","zh-CN,zh;q=0.8");
		map2.put("Cookie", token);	
		httptest.doPost(user.收藏商品, map1,map2);
		logger.info(httptest.getEntitys());
		return httptest.getEntitys();
		}
	
	public class AuthorCheckADD extends Thread{
		private String phone;
		
		public AuthorCheckADD(String phone) {
			// TODO Auto-generated constructor stub
			this.phone=phone;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("当前手机号是："+phone);
			Map<String,String> map0=new HashMap<>();
			map0.put("phone",phone);
			map0.put("rememberMe","true");
			map0.put("password","VsgUFSutH0Pp6BwtHyYH3XyXA7Hc4qpzG2fKJweq7Fu/zhZ3/Rptt3vS5Y0xVk5+OCZmJ+mu/GggkPYl1vNhzFvOaLtcWmaUJgtr1tmZgdzXZTRRySGylkSU2n3Y7zH468/wQn2ALjBKIcFl+XBLFUDGbTSN9BCSmF1NHOEf9+E=");
			HttpTools httptest =new HttpTools();
			httptest.doPost(user.登录, map0);
			System.out.println(httptest.getEntitys());
			List<Cookie> aa=httptest.getMyCookie();
			String token="";
			for (Cookie aaa:aa) {
				if("token".equals(aaa.getName())){
					 token="token="+aaa.getValue()+";";
				//	 System.out.println(token);
					}
			}
			if(token.isEmpty()){
			//	System.out.println("未登陆成功");
				logger.info("账号未登录成功！:"+phone);
			}else{
				
			}
			httptest.setCookieOfHeader(token);
			
//			Map<String,String> map1=new HashMap<>();
//			map1.put("item_id","23355");
//			Map<String,String> map2=new HashMap<>();
//			map2.put("Connection","keep-alive");
//			map2.put("Accept","*/*");
//			map2.put("Origin","http://view.trc.com");
//			map2.put("X-Requested-With","XMLHttpRequest");
//			map2.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
//			map2.put("Referer","http://view.trc.com/member-idcard.html");
//			map2.put("Accept-Encoding","gzip, deflate");
//			map2.put("Accept-Language","zh-CN,zh;q=0.8");
//			map2.put("Cookie", token);	
//			httptest.doPost(user.收藏商品, map1,map2);
			
			httptest.doGet("http://wx.trc.com/wxapi/getCartCount.api");
			logger.info(httptest.getEntitys());
		}		
	}
	
	
	public class CheckCartInfo extends Thread{
		private String phone;
		
		public CheckCartInfo(String phone) {
			// TODO Auto-generated constructor stub
			this.phone=phone;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("当前手机号是："+phone);
			Map<String,String> map0=new HashMap<>();
			map0.put("phone",phone);
			map0.put("rememberMe","true");
			map0.put("password","VsgUFSutH0Pp6BwtHyYH3XyXA7Hc4qpzG2fKJweq7Fu/zhZ3/Rptt3vS5Y0xVk5+OCZmJ+mu/GggkPYl1vNhzFvOaLtcWmaUJgtr1tmZgdzXZTRRySGylkSU2n3Y7zH468/wQn2ALjBKIcFl+XBLFUDGbTSN9BCSmF1NHOEf9+E=");
			HttpTools httptest =new HttpTools();
			httptest.doPost(user.登录, map0);
			System.out.println(httptest.getEntitys());
			List<Cookie> aa=httptest.getMyCookie();
			String token="";
			for (Cookie aaa:aa) {
				if("token".equals(aaa.getName())){
					 token="token="+aaa.getValue()+";";
				//	 System.out.println(token);
					}
			}
			if(token.isEmpty()){
			//	System.out.println("未登陆成功");
				logger.info("账号未登录成功！:"+phone);
			}else{
				
			}
			httptest.setCookieOfHeader(token);
			
			String initcart="http://wx.trc.com/wxapi/initCart.api";
			httptest.doGet(initcart);
			System.out.println(httptest.getEntitys());
			String ss=httptest.getEntitys();
			JSONObject aa1=JSONObject.fromObject(ss);
			
			if("401".equals(aa1.getString("biz_code"))){
				logger.info(ss);
				logger.info("翻车了！");
			}
/*			Map<String,String> map1=new HashMap<>();
			map1.put("item_id","23355");
			Map<String,String> map2=new HashMap<>();
			map2.put("Connection","keep-alive");
			map2.put("Accept","*");
			map2.put("Origin","http://view.trc.com");
			map2.put("X-Requested-With","XMLHttpRequest");
			map2.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
			map2.put("Referer","http://view.trc.com/member-idcard.html");
			map2.put("Accept-Encoding","gzip, deflate");
			map2.put("Accept-Language","zh-CN,zh;q=0.8");
			map2.put("Cookie", token);	
			httptest.doPost(user.收藏商品, map1,map2);*/
			logger.info(httptest.getEntitys());
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void AddCollect(){
		String phone="188661";
		for (int i = 0; i < 10; i++) {
			for (int j = 1; j <=5; j++) {
				int temp=60000+i*5+j;
				String thephone=phone+temp;
				new AuthorCheckADD(thephone).start();
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			try {
				Thread.sleep(200);
				System.out.println("休息下"+phone);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void AddTest(){
		String phone="188887";
		for (int i = 0; i < 10; i++) {
			for (int j = 1; j <=5; j++) {
				int temp=50000+i*5+j;
				String thephone=phone+temp;
				new CheckCartInfo(thephone).start();
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			try {
				Thread.sleep(200);
				System.out.println("休息下"+phone);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		
		
		//new UserInit().注册一万个瓜皮();
	//	new UserInit().register("13177561999");
//		new UserInit().addAddress("13177560000");
//	new UserInit().AddCollect();
		//new UserInit().AddTest();
	new UserInit().添加一万个瓜皮();
//		new UserInit().添加收藏("18758017446", "28351");

	}


}


