package tools.EnvironmentUtil;

import java.util.Map;

import net.sf.json.JSONObject;

public class ConfigUtil {
	private static JSONObject js=new JSONObject();
	public  static void AddConfig(Map<String,String> map){
		
		for (String key : map.keySet()) {  
			  
		    System.out.println("Key = " + key);  
		    System.out.println("Value ="+map.get(key));
		    js.put(key, map.get(key));
		}  
		
	}
	
	public static JSONObject getJS(){
		return js;
	}
	
	public static void printJS(){
		for (Object key : js.keySet()){
		    System.out.println("Key = " + key.toString());  
		    System.out.println("Value ="+js.get(key).toString());
		}
	}
	
	
	
}
