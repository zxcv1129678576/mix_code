package tools.Datas;

import java.util.HashMap;
import java.util.Map;

/** 
*@author  作者 : 陈达
*@date 创建时间：Apr 26, 2017 10:27:54 AM
*@version 1.0 
*@parameter  
*@return 
*/

public class Datas {
	
	Map<String,Map<String,String>> datas;
	
	public Map<String,String> get(String group){
		 Map<String,String> ss=new HashMap<>();
		
		
		return datas.get(group);
	}
	
	
	
}
