package develop.redis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import develop.JDBC.MyDbTools;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import tools.util.HttpUtil;
public class RedisStringJava {
	public  static  Logger logger = Logger.getLogger( RedisStringJava.class);
	public class addnotice extends Thread{
		private String name;
		private Jedis jedis;
		int size;
		int temp1;
		public addnotice(String name,Jedis jedis,int size,int temp1){
			this.name=name;
			this.jedis=jedis;
			this.size=size;
			this.temp1=temp1;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < size; i++) {
				System.out.println(Thread.currentThread().getName());
				System.out.println("当前的数据name是："+temp1*size+i);
		        JSONObject temp=new JSONObject();
//		        temp.put("noticeType", "opengroup");
//		        temp.put("productName", name+(temp1*size+i));
//		        temp.put("productPrice", 20.5);
//		        temp.put("needPerson", 3);
//		        temp.put("expireTime", 1501828033);
//		        temp.put("object_id", 3242);
//		        temp.put("openid", "ogyKG1vYxNaiskuxDgZgfg6j4fgU");

		        

		        
//		        temp.put("noticeType", "spellgroup");
//		        temp.put("tid", "测试数据AAA"+temp1);
//		        //temp.put("productName", name+(temp1*size+i));
//		        temp.put("productPrice", 20.5);
//		      //  temp.put("needPerson", 3);
//		      //  temp.put("expireTime", 1501828033);
//		       // temp.put("object_id", 3242);
//		        temp.put("openid", "ogyKG1vYxNaiskuxDgZgfg6j4fgU");	        
		        
		        
		        
		        
		        //ogyKG1vYxNaiskuxDgZgfg6j4fgU
		        //ogyKG1mXWlp8QYeDqY08asP-ORQs
		        //ogyKG1vYxNaiskuxDgZgfg6j4fgU
				//jedis.lpush("opengroup",temp.toString()); 
		        jedis.lpush("spellgroup",temp.toString()); 
			}
		}
		
	}
	
	public void addall(){
		String name="测试数据";
        Jedis jedis = new Jedis("172.30.249.140",6379);
        jedis.auth("R3edistest@123098");   // 设置密码
        System.out.println("Connection to server sucessfully");
        jedis.select(0);
		
//		for (int i = 0; i < 10; i++) {
//			String name="测试数据";
//	      //172.30.248.241
//			Jedis jedis = new Jedis("172.30.248.241",6379);
//	        jedis.auth("Redistest@123098");   // 设置密码
//	        System.out.println("Connection to server sucessfully");
//	        jedis.select(0);
//				int temp=(i+1)*100;
//				new addnotice(name, jedis,1,i).start();
//		
//		}
		for (int i = 0; i < 100; i++) {
			
			System.out.println("当前的数据name是："+name+i);
	        JSONObject temp=new JSONObject();
	        temp.put("noticeType", "opengroup");
	        temp.put("productName", name);
	        temp.put("productPrice", 20.5);
	        temp.put("needPerson", 3);
	        temp.put("expireTime", 1501828033);
	        temp.put("object_id", 3242);
	        temp.put("noticeType", "ogyKG1vYxNaiskuxDgZgfg6j4fgU");
	        jedis.lpush("openid",temp.toString());  
//				new addnotice(aa+i, jedis).start();
	        
		}
		}
	
	
	
		
		public static Jedis getJedis(String ip ,int port,String author,int db){
			Jedis jedis = new Jedis(ip,port);
	        jedis.auth(author);   // 设置密码
	        System.out.println("Connection to server sucessfully");
	        jedis.select(db);
			return jedis;	
		}
		
		public static String AddIDcard(){
//			Jedis jedis=getJedis("10.200.140.22",6379,"Redistest@123098",0);
//			// System.out.println(jedis.lrange("outerOrderSyn",0,-1));  
//			List<String> list=jedis.lrange("outerOrderSyn",0,-1);
//			List<JSONObject> aaa=new ArrayList<>();
//			for (int i = 0; i < list.size(); i++) {
//				JSONObject JS= JSONObject.fromObject(list.get(i));
//				//long aa=370323199401250816;
//				JS.getJSONObject("order").put("receiver_id_number",	"370323199401250816" );
//				aaa.add(JS);
//			}
//			
//			jedis.del("outerOrderSyn");
//			
//			for (int i = 0; i < aaa.size(); i++) {
//				jedis.lpush("outerOrderSyn",aaa.get(i).toString()); 
//				
//			}
			

			JSONObject re=new JSONObject();
			
		//	logger.info("已经为"+aaa.size()+"个麻瓜添加身份证");
			
			
			String aa="http://middle.trc.com/";
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Map<String,String> map0=new HashMap<>();
			map0.put("client","http");
			map0.put("topic","order.synSupply");
			map0.put("content","");
			map0.put("options","");
			map0.put("server","http://middle.trc.com/http.php");
			HttpUtil.doPost(aa, map0);
			String sss=HttpUtil.getEntitys();
			
			logger.info("中间件返回消息是："+sss);
			
			//re.put("resdult", "已经为"+aaa.size()+"个麻瓜添加身份证");
			re.put("middle", sss);
			return re.toString();
		}
		
		
	
    public static void main(String[] args) {
    	
    	//new RedisStringJava().addall();
    	AddIDcard();
    }
}
