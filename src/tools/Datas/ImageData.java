package tools.Datas;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import develop.test.BaiduImageSpider;
import tools.util.RandomCharsUtil;

public class ImageData {
	static List<String> list =new ArrayList<>();
	public static String 获取一张随机Banner图片(){
		list=BaiduImageSpider.GetImageUrl(RandomCharsUtil.获取指定数量纯汉字(2), "30", "30");	
		return list.get((int)(Math.random()*29));
	}
	
}
