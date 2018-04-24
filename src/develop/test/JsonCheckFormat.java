package develop.test;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonCheckFormat {
	
		public static void test(){
			JSONObject ss=new JSONObject();
			ss.put("test", "	  ");
			
			if("".equals(ss.getString("test").trim()));
			System.out.println("没翻车");
			
			String aa="{\"phone\":\"18758017446\",\"keepOnline\":true,\"userId\":\"20151223131945790f7bc689934ad42b395c672ccd507ceb5\",\"passwordInfo\":{\"code\":100,\"description\":\"密码有效期开关已关闭\"}}";
			JSONObject sss=JSONObject.fromObject(aa);
			System.out.println(sss.toString());
			String aaa="{\"phone\":\"\",\"keepOnline\":true,\"userId\":\"\",\"passwordInfo\":{\"code\":\"\",\"description\":\"1111\"}}";
		
			JSONObject sss1=JSONObject.fromObject(aaa);
			System.out.println(sss1.toString());
			
			  Iterator iterator = sss.keys();
			  while(iterator.hasNext()){
			          String    key = (String) iterator.next();
			         String value = sss.getString(key);
			         
			         System.out.println("key:"+key+","+"value:"+value);
			  }
			//sss1.entrySet();
		}
		
	
	
		
		public  static String Check(String json){
			try {
				//JSONObject aa=JSONObject.fromObject(json);
				JSONArray aa1=JSONArray.fromObject(json);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			System.out.println("1111");
			return "asdasdasd";
			
		}
		
		
		public static void main(String[] args) {
		//	test();
		System.out.println(	Check(""));
		}

}
