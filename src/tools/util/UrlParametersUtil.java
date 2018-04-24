package tools.util;

public class UrlParametersUtil {
	/**
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	  public static String ParamsGet(String source,String target){
		  String temp="";
		  try {
			  String s[] =new String[2];
			 s=source.split(target+"=");
			  
			  if(s[1].isEmpty()){
				  temp= "null";
			  }else{
				  String[] ss1=s[1].split("&");
				  temp= ss1[0];  
			  }
			  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			temp="exception";
		}
		//logger.info("从连接：*《"+source+"中取到的目标："+target+"是："+temp);
		  
		return temp;
		  
	  }
	
	
}
