package tools.Interface.manage;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import tools.Datas.ImageData;
import tools.util.HttpUtil;
import tools.util.RandomCharsUtil;
public class Manage_WX_Prepare {
	
	
	public  static final Logger logger = Logger.getLogger(Manage_WX_Prepare.class);

	/**
	 *  
	 * @param environment 环境地址
	 * @param type 0微商城 1小泰良品
	 * @param name 名称
	 * @param url	配置链接
	 * @param image	图片地址
	 * @param order	排序
	 * @return
	 */
	public static JSONObject BannerConfig(String environment,String type,String name,String url,String image ,String order){
		ManagePlatform.login(environment);
		String add_banner;
		if("1".equals(type)){
			add_banner=environment+"shopadmin?app=site&ctl=admin_wxdeploy&act=add&view=1";
		}else {
			add_banner=environment+"shopadmin?app=site&ctl=admin_wxdeploy&act=add&view=";

		}
		Map<String,String> map0=new HashMap<>();
		map0.put("name",name);
		map0.put("banner_img",image);
		map0.put("type","link");
		map0.put("add_link",url);
		map0.put("order",order);
		HttpUtil.doPost(add_banner, map0);
		logger.info(HttpUtil.getEntitys());
		return JSONObject.fromObject(HttpUtil.getEntitys());
	}
	
	/**
	 * 
	 * @param environment
	 * @param type 0或者1
	 * @param name
	 * @param style
	 * @param image
	 * @param des
	 * @param category
	 * @param order
	 * @return
	 */
	public static JSONObject ChannelCongfig_catagory(String environment,String type,String name,String style,String image ,String des,String category,String order){
		ManagePlatform.login(environment);
		String add_config=environment+"shopadmin?app=site&ctl=admin_channel&act=autoAdd&view="+type;
		Map<String,String> map2=new HashMap<>();
		map2.put("id","");
		map2.put("name",name);
		map2.put("style_show",style);
		map2.put("sort",order);
		map2.put("description",des);
		map2.put("channel_img",image);
		map2.put("category_id",category);
		HttpUtil.doPost(add_config, map2);	
		logger.info(HttpUtil.getEntitys());
		return JSONObject.fromObject(HttpUtil.getEntitys());
	}
	
	/**
	 * 
	 * @param environment
	 * @param type   0   1
 	 * @param name
	 * @param style  left  center  square
	 * @param image
	 * @param des
	 * @param from
	 * @param end
	 * @param order
	 * @return
	 */
	public static JSONObject ChannelCongfig_items(String environment,String type,String name,String style,String image ,String des,String from,String end,String order){
		ManagePlatform.login(environment);
		String add_config=environment+"shopadmin?app=site&ctl=admin_channel&act=manualAdd&view="+type;
		Map<String,String> map3=new HashMap<>();
		map3.put("id","");
		map3.put("name",name);
		map3.put("style_show",style);
		map3.put("sort",order);
		map3.put("description",des);
		map3.put("channel_img",image);
		int a=Integer.parseInt(from);
		int b=Integer.parseInt(end);
		for (int i = a; i <=b; i++) {
			map3.put("items["+i+"]",""+i);
		}
		
		HttpUtil.doPost(add_config, map3);	
		logger.info(HttpUtil.getEntitys());
		//return JSONObject.fromObject(HttpUtil.getEntitys());
		return new JSONObject();
	}

	/**
	 *  
	 * @param environment 环境地址
	 * @param name 名称
	 * @param url	配置链接
	 * @param image	图片地址
	 * @param order	排序
	 * @return
	 */
	public static JSONObject FunctionEntronceConfig(String environment,String name,String url,String image ,String order){
		
		ManagePlatform.login(environment);
		String add_entrence=environment+"shopadmin?app=site&ctl=admin_wxentrance&act=add";
		Map<String,String> map0=new HashMap<>();
		map0.put("name",name);
		map0.put("entrance_icon",image);
		map0.put("type","link");
		map0.put("add_link[1]",url);
		map0.put("add_link[2]",url);
		map0.put("order",order);
		HttpUtil.doPost(add_entrence, map0);
		logger.info(HttpUtil.getEntitys());
		return JSONObject.fromObject(HttpUtil.getEntitys());
		
		
	}
	
	
	
	/**
	 * 
	 * @param environment
	 * @param name
	 * @param type 1链接 2文本
	 * @param content
	 * @param order
	 * @return
	 */
	public static JSONObject NoticeConfig(String environment,String name,String type,String content,String order){
		
		ManagePlatform.login(environment);
		String add_notice=environment+"shopadmin?app=site&ctl=admin_wxnotice&act=save";
		Map<String,String> map1=new HashMap<>();
		map1.put("id","");
		map1.put("is_used","");
		map1.put("title",name);
		map1.put("order_by",order);
		map1.put("type",type);
		map1.put("content",content);
		if("2".equals(type)){
			map1.put("link","http://blog.csdn.net/lzj0470/article/details/30216145");
		}else {
			map1.put("link",content);
		}
		
		
		HttpUtil.doPost(add_notice, map1);
		logger.info(HttpUtil.getEntitys());
		return JSONObject.fromObject(HttpUtil.getEntitys());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		//添加5个拼团Banner
//		for (int i = 1; i < 5; i++) {
//			BannerConfig("http://pre.trc.com/", "0", "麻瓜a"+i, ImageData.获取一张随机Banner图片(), ImageData.获取一张随机Banner图片(), ""+i);
//		}
//		//添加五个良品Banner
//		for (int i = 1; i < 5; i++) {
//			BannerConfig("http://pre.trc.com/", "1", "良品啊"+i, ImageData.获取一张随机Banner图片(), ImageData.获取一张随机Banner图片(), ""+i);
//		}
		
		//配置入口
//		for (int i = 1; i < 9; i++) {
//			FunctionEntronceConfig("http://pre.trc.com/",  "瓜入口"+i, ImageData.获取一张随机Banner图片(), ImageData.获取一张随机Banner图片(), ""+i);
//		}
		
		
		//配置频道页
//		for (int i = 1; i < 4; i++) {
//			ChannelCongfig_catagory("http://pre.trc.com/", "0", "麻瓜"+i, "square",  ImageData.获取一张随机Banner图片(), "ssss", "3", ""+i);
//
//		}
//		
//		for (int i = 1; i < 4; i++) {
//			ChannelCongfig_catagory("http://pre.trc.com/", "1", "瓜皮"+i, "center",  ImageData.获取一张随机Banner图片(), "ssss", "3", ""+i);
//
//		}
//		
//		for (int i = 1; i < 4; i++) {
//			ChannelCongfig_items("http://pre.trc.com/", "0", "手动拼团"+i, "square",  ImageData.获取一张随机Banner图片(), "ssss", "2670","2750", ""+i);
//
//		}
//		
//		for (int i = 1; i < 3; i++) {
//			ChannelCongfig_items("http://view.trc.com/", "1", "手动小泰良品"+i, "left",  ImageData.获取一张随机Banner图片(), "ssss", "28700","28747", ""+i);
//		}
		
//		//拼团公告配置
//		for (int i = 1; i < 6; i++) {
//			NoticeConfig("http://pre.trc.com/", "链接"+i, "1", ImageData.获取一张随机Banner图片(), ""+i);
//
//		}
//		
//		for (int i = 1; i < 6; i++) {
//			NoticeConfig("http://pre.trc.com/", "文本"+i, "2", RandomCharsUtil.获取指定数量纯汉字(100), ""+i);
//
//		}
		
		
		//BannerConfig("http://view.trc.com/", "0", "麻瓜a", "http://view.trc.com/", ImageData.获取一张随机Banner图片(), "1");
		//FunctionEntronceConfig("http://view.trc.com/",  "麻瓜", "http://view.trc.com/", ImageData.获取一张随机Banner图片(), "1");
		//NoticeConfig("http://view.trc.com/", "麻瓜", "1", ImageData.获取一张随机Banner图片(), "1");
	//	ChannelCongfig_catagory("http://view.trc.com/", "0", "麻瓜", "left",  ImageData.获取一张随机Banner图片(), "ssss", "3", "1");
		//ChannelCongfig_items("http://view.trc.com/", "1", "麻瓜", "left",  ImageData.获取一张随机Banner图片(), "ssss", "23000","23050", "1");

	}
}
