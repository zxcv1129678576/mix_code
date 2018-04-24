package tools.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListCheckUtil {
	
	public static void same(List<String> list) {
	    String [] indexArr ;
	    Map<String, String> map = new HashMap<String, String>();
	    for (int i = 0; i < list.size(); i++) {
	      String key = list.get(i);
	      String old = map.get(key);
	      if (old != null) {
	        map.put(key, old + "," + (i + 1));
	      } else {
	        map.put(key, "" + (i + 1));
	      }
	    }
	    Iterator<String> it = map.keySet().iterator();
	    int index = -1;
	    while (it.hasNext()) {
	      String key = it.next();
	      String value = map.get(key);
	      if (value.indexOf(",") != -1) {
	        System.out.println(key + " 重复,行： " + value);
	        indexArr = value.split(",");
	 
	        for (int i = 0; i < indexArr.length; i++) {
	          index = Integer.parseInt(indexArr[i])-1;
	          list.set(index, list.get(index)+(1+i));
	        }
	      }
	    }
	 
//	    for (String val : list) {
//	      System.out.println("val = "+val);
//	    }
	    System.out.println("..................");
	 
	  }
}
