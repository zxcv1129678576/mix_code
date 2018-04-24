package tools.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.Interface.user.UserCheck;
public class InterFaceEXEC {
		/**
		 * 传入类名和方法名直接执行。并抛回JSONObject对象。
		 * @param name
		 * @param method
		 * @return
		 */
		public static  JSONObject exec(String name,String method){
			JSONObject result=new JSONObject();
			try {
				Class Classc1 = Class.forName(name);
				Object o=Classc1.newInstance();  
				Method m=Classc1.getDeclaredMethod(method);
//				System.out.println(m.getName());
				result=(JSONObject) m.invoke(0);
				result.put("classname", name);
				result.put("themethod", method);
				System.out.println(result.getString("Success"));
				
			} catch (Exception e) {
				result.put("classname", name);
				result.put("themethod", method);
				result.put("data",e.toString());
				result.put("des", "代理执行异常！");
				result.put("Success", false);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			return result;
		}
		public static  JSONObject ScanList(){
			List<String> li=new ArrayList<>();
			li.add("wait");
			li.add("equals");
			li.add("toString");
			li.add("hashCode");
			li.add("getClass");
			li.add("notify");
			li.add("notifyAll");
			li.add("main");
			JSONObject reslut=new JSONObject();
			String test= "tools.Interface.user.UserCheck";
			JSONArray arrays=new JSONArray();
			try {
				Class Classc1 = Class.forName(test);
				Method[] array=Classc1.getMethods();
				for (int i = 0; i < array.length; i++) {
					if(!li.contains(array[i].getName())){
						JSONObject temp=new JSONObject();
						temp.put("method", array[i].getName());
						temp.put("classname",test);
						arrays.add(temp);	
						}
					

				}
				
				reslut.put("user", arrays);
				
			} catch (Exception e) {
				
//				result.put("target", "类名："+name+"方法名："+method);
//				result.put("data",e.toString());
//				result.put("des", "代理执行异常！");
//				result.put("Success", false);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			return reslut;
			
		}
		
		
	public static void main(String[] args) {
		System.out.println(UserCheck.class.getName());
	//	exec("tools.Interface.user.UserCheck","登录报文校验");
	}
}
