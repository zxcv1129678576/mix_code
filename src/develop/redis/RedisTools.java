package develop.redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import tools.util.ListCheckUtil;

public class RedisTools {
	
	public static void CheckRedis(){
		Jedis jedis = new Jedis("172.30.248.241",6379);
        jedis.auth("Redistest@123098");   // 设置密码
        jedis.select(0);
        System.out.println("Connection to server sucessfully");
        List<String> aa=jedis.lrange("spellgroup",0,-1);
        
        ListCheckUtil.same(aa);
        
		
	}
	
	
	public static void lrem(){
		Jedis jedis = new Jedis("10.200.140.22",6379);
        jedis.auth("Redistest@123098");   // 设置密码
        jedis.select(0);
        System.out.println("Connection to server sucessfully");
        List<String> aa=jedis.lrange("outerOrderSyn",0,-1);
        for (int i = 0; i < aa.size(); i++) {
			
        	//long a=jedis.lrem("outerOrderSyn", 0,aa.get(i) );
        	System.out.println(aa.get(i));
        	//System.out.println("删除第"+i+"个！");
		}	
        
        
       // ListCheckUtil.same(aa);
        
        
        
		
	}
	
	public static void main(String[] args) {
		//CheckRedis();
		

		
		lrem();
	}
}
