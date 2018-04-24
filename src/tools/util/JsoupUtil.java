/**
 * 
 */package tools.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/** 
*@author  作者 : 陈达
*@date 创建时间：Apr 8, 2017 2:29:08 PM
*@version 1.0 
*@parameter  
*@return 
*/

public class JsoupUtil {
	
	private static Document doc;
	/**
	 * 传入一段html文本。将当前类中的doc对象初始化。后续操作皆建立在这个doc对象之上。
	 * 静态方法，直接调用。
	 * @param html 一段完整的html代码
	 */
	public static void initDOC(String html){
		doc=Jsoup.parse(html);
	}
	
	
}
