package develop.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.util.HttpUtil;

public class BaiduImageSpider {
	
	public static List<String>  GetImageUrl (String keyword,String pn,String rn){
		List<String> list=new ArrayList<>();
		String url="https://image.baidu.com/search/acjson";
		Map<String,String> map0=new HashMap<>();
		map0.put("tn","resultjson_com");
		map0.put("ipn","rj");
		map0.put("ct","201326592");
		map0.put("is","");
		map0.put("fp","result");
		map0.put("queryWord",keyword);
		map0.put("cl","2");
		map0.put("lm","-1");
		map0.put("ie","utf-8");
		map0.put("oe","utf-8");
		map0.put("adpicid","");
		map0.put("st","-1");
		map0.put("z","");
		map0.put("ic","0");
		map0.put("word",keyword);
		map0.put("s","");
		map0.put("se","");
		map0.put("tab","");
		map0.put("width","");
		map0.put("height","");
		map0.put("face","0");
		map0.put("istype","2");
		map0.put("qc","");
		map0.put("nc","1");
		map0.put("fr","");
		map0.put("pn",pn);
		map0.put("rn",rn);
		map0.put("gsm","5a");
		map0.put("1501666561330","");
		HttpUtil.doGet(url, map0);
		HttpUtil.printEntity();
		JSONObject re=JSONObject.fromObject(HttpUtil.getEntitys());
		JSONArray data=re.getJSONArray("data");
		System.out.println(data.size());
		List<String> aa=new ArrayList<String>();
		for (int i = 0; i < data.size()-1; i++) {
			aa.add((data.getJSONObject(i).getString("middleURL")));
			System.out.println(data.getJSONObject(i).getString("middleURL"));
			list.add(data.getJSONObject(i).getString("middleURL"));
		}
		
		
		return list;
	}
	public static void main(String[] args) {
		GetImageUrl("160x275", "30", "30");
	}
}
