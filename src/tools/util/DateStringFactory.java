package tools.util;

import java.util.Calendar;
import java.util.Date;

/** 
*@author  作者 : 陈达
*@date 创建时间：Apr 26, 2017 9:55:52 AM
*@version 1.0 
*@parameter  
*@return 
*/

public class DateStringFactory {
	/**
	 * 
	 * @param minutes 传入要比当前时间加几分钟
	 * @return  返回当前时间加上传入minutes之后格式化为yyyy-MM-dd HH:mm
	 */
	public static String getAfterMinnite年月日时分(int minutes){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, minutes);
		String result=DateUtil.formatDate("yyyy-MM-dd HH:mm", date);
		return result;
	}
	/**
	 * 
	 * @param minutes 传入要比当前时间加几分钟
	 * @return  返回当前时间加上传入minutes之后格式化为yyyy-MM-dd
	 */
	public static String getAfterMinnite年月日(int minutes){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, minutes);
		String result=DateUtil.formatDate("yyyy-MM-dd", date);
		return result;
	}
	
	public static String getAfterMinnite时(int minutes){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, minutes);
		String result=DateUtil.formatDate("HH", date);
		return result;
	}
	
	public static String getAfterMinnite分(int minutes){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, minutes);
		String result=DateUtil.formatDate("mm", date);
		return result;
	}
	
	/**
	 * 
	 * @param minutes 传入要比当前时间加几消失
	 * @return  返回当前时间加上传入hours之后格式化为yyyy-MM-dd HH:mm
	 */
	public static String getAfterHour年月日时分(int hours){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.HOUR, hours);
		String result=DateUtil.formatDate("yyyy-MM-dd HH:mm", date);
		return result;
	}
	/**
	 * 
	 * @param minutes 传入要比当前时间加几消失
	 * @return  返回当前时间加上传入hours之后格式化为yyyy-MM-dd
	 */
	public static String getAfterHour年月日(int hours){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.HOUR, hours);
		String result=DateUtil.formatDate("yyyy-MM-dd", date);
		return result;
	}
	
	/**
	 * 
	 * @param minutes1 传入要比当前时间加几分钟 时间点2比当前多几分钟
	 * @return  返回当前时间加上传入minutes之后格式化为yyyy-MM-dd HH:mm-yyyy-MM-dd HH:mm-；
	 */
	public static String get一段时间(int minutes1,int minutes2){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, minutes1);
		String result=DateUtil.formatDate("yyyy-MM-dd HH:mm", date);
		Date date2=new Date();
		date =DateUtil.getTimeAfterAdd(date2, Calendar.MINUTE, minutes2);
		String result2=DateUtil.formatDate("yyyy-MM-dd HH:mm", date);
		return result+"-"+result2;
	}
	
	public static String shop年月日时分(int minutes){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, minutes);
		String result=DateUtil.formatDate("yyyy/MM/dd HH:mm", date);
		return result;
	}
	public static String shop一段时间(int minutes1,int minutes2){
		Date date=new Date();
		date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, minutes1);
		String result=DateUtil.formatDate("yyyy/MM/dd HH:mm", date);
		Date date2=date;
		date =DateUtil.getTimeAfterAdd(date2, Calendar.MINUTE, minutes2);
		String result2=DateUtil.formatDate("yyyy/MM/dd HH:mm", date);
		return result+"-"+result2;
	}

	public static String 获取当前的月日时分秒(){
		Date date=new Date();
		String result=DateUtil.formatDate("MM/dd HH:mm", date);
		return result;
		
	}
	
	public static String 获取2017第一天到今天(){
		Date date=new Date();
		String result=DateUtil.formatDate("yyyy/MM/dd", date);
		return "2017/01/01"+"-"+result;
		
	}
	public static void main(String[] args) {
		System.out.println(获取2017第一天到今天());
	}
	
}
