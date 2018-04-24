package tools.Datas;

import java.util.Map;

/** 
*@author  作者 : 陈达
*@date 创建时间：Apr 26, 2017 10:15:07 AM
*@version 1.0 
*@parameter  
*@return 
*/

public class ManageData{

	Map<String,Map<String,String>> datas;
	
	public Map<String,String> get(String group){
		return datas.get(group);
	}
	static {
		
		
		
	}
	
}
