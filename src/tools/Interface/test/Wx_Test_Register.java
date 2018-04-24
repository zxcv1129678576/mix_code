package tools.Interface.test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.apache.log4j.Logger;
import com.trc.util.StringUtil;
import net.sf.json.JSONObject;
import tools.EnvironmentUtil.接口.user;
import tools.util.HttpTools;
import tools.util.HttpUtil;

public class Wx_Test_Register {
	public  static  Logger logger = Logger.getLogger( Wx_Test_Register.class);
	

	public void register(String username,	HttpTools httptest1){
		final HttpTools httptest=httptest1;
		
		JSONObject mmm=new JSONObject();
		mmm.put("phone",username);
		mmm.put("code", "1311");
		String url ="http://wx.trc.com/account/auth/simple";
	//	String url1 ="https://passport.trc.com/proxy/account/user/encrypt/register";
		System.out.println(mmm.toString());
	//	 HttpTools httptest =new HttpTools();
		httptest.doPostxxx(url, mmm);
		String ss=httptest.getEntitys();
		List<Cookie> ssss=HttpUtil.getMyCookie();
		String token="";
		for (Cookie aaa:ssss) {
			if("token".equals(aaa.getName())){
				 token="token="+aaa.getValue()+";";
				 System.out.println(token);
				}
		}
		System.out.println("返回报文是："+ss);
		JSONObject aa=JSONObject.fromObject(ss);
		String userid=aa.getString("userId");
		if (StringUtil.isEmpty(userid)) {
			logger.info("注册账号："+username+"失败！");
			//return "null";
		}else{
			logger.info("注册账号："+username+"成功！用户取得userid为："+userid);
			//return userid;
		}
		httptest.setCookieOfHeader(token);
		final String get1="http://wx.trc.com/wxapi/getMoreCount.api?count_no_use=1";
		final String get2="http://wx.trc.com/wxapi/getCartCount.api";

//		JSONObject test1=JSONObject.fromObject(info1);
//		String code1=aa.getString("code");
//		if ("200".equals(code1)){		
//			logger.info("手机号："+username+"获取数据1成功");	
//		}	
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String info1=	httptest.doGet1(get1);
				//String info1=httptest.getEntitys();
				logger.info("info1"+"**********************");
				logger.info(info1);
			}
		}).start();				
		
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				String info3=	httptest.doGet(get1);
//				//String info1=httptest.getEntitys();
//				logger.info("info3"+"**********************");
//				logger.info(info3);
//			}
//		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String info2=httptest.doGet1(get2);
				logger.info("info2"+"**********************");
				logger.info(info2);
			}
		}).start();
	}
	
	
	
	public class mythread extends Thread{
		private String username="";
		HttpTools httptest1;
		public mythread (String username,	HttpTools httptest){
			System.out.println(username);
			this.username=username;
			this.httptest1=httptest;
		}
		@Override
		public void run(){
		//	new Wx_Test_Register().register(username);
		//	final String username=username1;
			//	{"phone":"18633330001","code":"1311"}
				
//				Map<String,String> map0=new HashMap<>();
//				map0.put("phone",username);
////				map0.put("smsCode","1311");
////			//	map0.put("imgCode","aavvbb");
////			//	map0.put("password","VsgUFSutH0Pp6BwtHyYH3XyXA7Hc4qpzG2fKJweq7Fu/zhZ3/Rptt3vS5Y0xVk5+OCZmJ+mu/GggkPYl1vNhzFvOaLtcWmaUJgtr1tmZgdzXZTRRySGylkSU2n3Y7zH468/wQn2ALjBKIcFl+XBLFUDGbTSN9BCSmF1NHOEf9+E=");
////				map0.put("password","admin123");
		//
//				map0.put("code","1311");
				
				final HttpTools httptest=httptest1;
				
				JSONObject mmm=new JSONObject();
				mmm.put("phone",username);
				mmm.put("code", "1311");
				String url ="http://wx.trc.com/account/auth/simple";
			//	String url1 ="https://passport.trc.com/proxy/account/user/encrypt/register";
				System.out.println(mmm.toString());
			//	 HttpTools httptest =new HttpTools();
				httptest.doPostxxx(url, mmm);
				String ss=httptest.getEntitys();
				List<Cookie> ssss=HttpUtil.getMyCookie();
				String token="";
				for (Cookie aaa:ssss) {
					if("token".equals(aaa.getName())){
						 token="token="+aaa.getValue()+";";
						 System.out.println(token);
						}
				}
				System.out.println("返回报文是："+ss);
				JSONObject aa=JSONObject.fromObject(ss);
				String userid=aa.getString("userId");
				if (StringUtil.isEmpty(userid)) {
					logger.info("注册账号："+username+"失败！");
					//return "null";
				}else{
					logger.info("注册账号："+username+"成功！用户取得userid为："+userid);
					//return userid;
				}
				httptest.setCookieOfHeader(token);
				final String get1="http://wx.trc.com/wxapi/getMoreCount.api?count_no_use=1";
				final String get2="http://wx.trc.com/wxapi/getCartCount.api";

//				JSONObject test1=JSONObject.fromObject(info1);
//				String code1=aa.getString("code");
//				if ("200".equals(code1)){		
//					logger.info("手机号："+username+"获取数据1成功");	
//				}	
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String info1=	httptest.doGet1(get1);
						//String info1=httptest.getEntitys();
						logger.info("info1"+"**********************");
						logger.info(info1);
					}
				}).start();				
				
				
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						String info3=	httptest.doGet(get1);
//						//String info1=httptest.getEntitys();
//						logger.info("info3"+"**********************");
//						logger.info(info3);
//					}
//				}).start();
				
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String info2=httptest.doGet1(get2);
						logger.info("info2"+"**********************");
						logger.info(info2);
					}
				}).start();
				
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						String info4=httptest.doGet(get2);
//						logger.info("info4"+"**********************");
//						logger.info(info4);
//					}
//				}).start();
			
			
			
			}
	}
	
//
	
	public  void test2(){
		int start=1000;
			
		for (int i = 910; i < 920; i++) {
			System.out.println(i);
		final	int  a=start+i;
		HttpTools aa=new HttpTools();
		
			new mythread("1350844"+a,aa).start();
		}
		
		
	}
	
	
	
	public  void  test1(){
		
		
		
		
		HttpTools aa=new HttpTools();
		new mythread("13577440030",aa).start();
	}
	
	
	
	public static void main(String[] args) {
		
		//new Wx_Test_Register().test2();
		new Wx_Test_Register().test1();

		
	}

	
}
