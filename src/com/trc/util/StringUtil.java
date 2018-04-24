package com.trc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {


	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static String formatLike(String str) {
		if(isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}

	public static List<String> filterWhite(List<String> list) {
		List<String> resultList = new ArrayList<String>();
		for(String l : list) {
			if(isNotEmpty(l)) {
				resultList.add(l);
			}
		}
		return resultList;
	}
	
	public static String removeLast(String s){
		return s.substring(0,s.length() - 1);
	}
	
	public static String encode(String url)     
	   
	{     
	   
	          try {     
	   
	               String encodeURL=URLEncoder.encode( url, "UTF-8" );     
	   
	               return encodeURL;     
	   
	          } catch (UnsupportedEncodingException e) {     
	   
	               return "Issue while encoding" +e.getMessage();     
	   
	          }     
	   
	}  
	
}
