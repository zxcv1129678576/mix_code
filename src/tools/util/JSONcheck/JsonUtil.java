package tools.util.JSONcheck;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static Map<String,String> json_to_map(String json){
		Map<String,String> result=new HashMap<>();
		JSONObject temp= new JSONObject();
		try {
			temp=JSONObject.fromObject(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result;
		}
		Map<String,String> last=getKeyValue(temp, "$");
		getStrcture(last, result);
		return result;
		
	}
	
	public static void printMap(Map<String,String> map){
		System.out.println("sss");
		Iterator entries = map.entrySet().iterator(); 
		while (entries.hasNext()) { 
		  Map.Entry entry = (Map.Entry) entries.next(); 
		  String key = (String)entry.getKey(); 
		  String value = (String)entry.getValue(); 
		  System.out.println("Key = " + key + ", Value = " + value); 
		}
	}
	
	
	public static JSONObject getJsonObject(String json){
		JSONObject temp= new JSONObject();
		try {
			temp=JSONObject.fromObject(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return temp;
		}
		return temp;
	}
	public static JSONArray getJsonArray(String json){
		JSONArray temp= new JSONArray();
		try {
			temp=JSONArray.fromObject(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return temp;
		}
		return temp;
	}
	
	
	
	
	public static boolean isJsonObject(String data){
		JSONObject temp= new JSONObject();
		
		try {
			temp=JSONObject.fromObject(data);
			return  true;
		} catch (Exception e) {
			// TODO: handle exception
		//	e.printStackTrace();
			return false;
		}	
	}
	
	
	public static boolean isJsonArray(String data){
		JSONArray temp= new JSONArray();
		try {
			temp=JSONArray.fromObject(data);
			return  true;
		} catch (Exception e) {
			// TODO: handle exception
		//	e.printStackTrace();
			return  false;
		}
		
	}
	
	
	
	
	/**
	 *  
	 * @param last 上一次递归结果
	 * @param result 递归最终结果
	 */
	public static void getStrcture(Map<String,String> last,Map<String,String> result){
		//传入的data必定是JSON，直接进行处理。
		Iterator entries = last.entrySet().iterator(); 
		while (entries.hasNext()) { 
		  Map.Entry entry = (Map.Entry) entries.next(); 
		  String key=(String)entry.getKey();
		  String value=(String)entry.getValue();
		  if(isJsonObject(value)){//如果vaue是一个对象
				JSONObject test=JSONObject.fromObject(value);;
				Map<String,String> last1=getKeyValue(test,key);
				//继续递归
				getStrcture(last1,result);
		  }else if(isJsonArray(value)){//如果是一个数组
			  JSONObject test=JSONArray.fromObject(value).getJSONObject(0);
			  Map<String,String> last2=getKeyValue(test,key);
			//继续递归
			  getStrcture(last2,result);
		  }else{
			  //如果都不是，放入结果MAP里;
			  result.put(key, value);
		  }

		  
		}
		
		
	}
	
	
	
	
	public static Map<String,String>getKeyValue(JSONObject test,String head){
		Map<String,String> temp=new HashMap<String,String>();
		
		 for (Object o : test.keySet()) {  
			   String key = o.toString();  
			   String value = test.getString(key);  
			  // System.out.println("key=" + key + " value=" + value);  
			   temp.put(head+"."+""+key, ""+value);
			  }
	
		return temp;
	}
	
	
	
	
	public static int getDeep(String json){
		int a=0;
		int max=0;
		for (int i = 0; i < json.length(); i++) {
			
			char temp=json.charAt(i);
			//System.out.println(temp);
			if('{'==temp){
				a++;
				max=a>max?a:max;
			}
			if('}'==temp){
				a--;
				max=a>max?a:max;
			}
			
		}
		
		return max;
		
	}
	
	public static JSONObject putJSON(int i,JSONObject aa){
		JSONObject aaaa=new JSONObject();
		aaaa.put("test"+i,aa);
		aaaa.put("testaaa"+i,aa);
		return aaaa;
	}
	
	
	
	public static void main(String[] args) {
		
		JSONObject js=new JSONObject();
		js.put("sf", "fffaa");
		for (int i = 0; i < 3; i++) {
			js=putJSON(i,js);
		}
		String json="";
		System.out.println(js.toString());
		int a=getDeep(js.toString());
		System.out.println(a);
		Map<String,String> map=json_to_map(js.toString());
		printMap(map);
	}
	
}
