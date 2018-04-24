package tools.Interface.manage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tools.EnvironmentUtil.接口地址;
import tools.EnvironmentUtil.接口地址.manage;
import tools.util.DateStringFactory;
import tools.util.DateUtil;
import tools.util.HttpUtil;
public class ManagePlatform {
	public  static final Logger logger = Logger.getLogger(ManagePlatform.class);
	  public static void login(String huanjing){
		  
		  HttpUtil.clearsession();
		  Map<String,String> map1=new HashMap<>();
		  
		  map1.put("redirect","aHR0cDovL3ZpZXcudHJjLmNvbS9zaG9wYWRtaW4=");
		  map1.put("uname","admin");
		  map1.put("password","admin123");
		  
		  	 /**
			  * 登录并获取获取cookie的s-
			  */
			  String login1=huanjing+manage.登录页面;

			  HttpUtil.doGet(login1);
			  HttpUtil.printStatus();
			  HttpUtil.printCookie();
			  HttpUtil.printEntity();
			 
			 /**
			  * 认证cookie中的s
			  */
			  String login=huanjing+manage.登录;

			  
			  HttpUtil.doPost(login, map1);
			  HttpUtil.printStatus();
			  HttpUtil.printCookie();
			  HttpUtil.printEntity();
		  
	  }
	  
	
	  public static String addTemai(String huanjing,int 报名持续时间,int 审核持续时间,int 审核后持续时间,int 活动持续时间 ) throws ParseException, IOException {
		  
		  login(huanjing);
		  
		  /**
		   * 发布特卖活动
		   */
		  String 发布=huanjing+manage.发布特卖;
		  Map<String,String> map2=new HashMap<>();
		  Date date = new Date();
		 //当前时间加二分钟作为报名起始时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 1);
		  String 年月日1=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 报名开始小时=DateUtil.formatDate("HH", date);
		  String 报名开始分钟=DateUtil.formatDate("mm", date); 
		  String 描述=DateUtil.formatDate("yyyy-MM-dd HH:mm", date);
		  String names="特卖工具"+描述;
		  logger.info("特卖开始时间："+描述);
		//时间加报名持续时间作为报名结束时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 报名持续时间);
		  String 年月日2=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 报名结束小时=DateUtil.formatDate("HH", date);
		  String 报名结束分钟=DateUtil.formatDate("mm", date); 
		  
		  logger.info("特卖报名结束时间："+年月日2+报名结束小时+报名结束分钟);
		  
		 //时间加审核时间作为活动发布时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 审核持续时间);
		  String 年月日3=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 活动发布小时=DateUtil.formatDate("HH", date);
		  String 活动发布分钟=DateUtil.formatDate("mm", date);   
		  logger.info("特卖审核开始时间："+年月日3+活动发布小时+活动发布分钟);
		//时间加开始前时间作为活动开始时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 审核后持续时间);
		  String 年月日4=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 活动开始小时=DateUtil.formatDate("HH", date);
		  String 活动开始分钟=DateUtil.formatDate("mm", date);  		  

		  logger.info("特卖活动开始时间："+年月日4+活动开始小时+活动开始分钟);
		//时间加开始前时间作为活动开始时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 活动持续时间);
		  String 年月日5=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 活动结束小时=DateUtil.formatDate("HH", date);
		  String 活动结束分钟=DateUtil.formatDate("mm", date);    
		  	
		  logger.info("特卖活动结束时间："+年月日5+活动结束小时+活动结束分钟);
		  
		  map2.put("apply_edit_status","");
		  map2.put("activity_id","");
		  map2.put("activity_name",names);
		  
		  map2.put("specialflashsale_desc","");
		  map2.put("activity_type","flashsale");
		  
		  map2.put("_DTYPE_TIME[]","apply_start_time");
		  map2.put("apply_start_time",年月日1);
		  map2.put("_DTIME_[H][apply_start_time]",报名开始小时);
		  map2.put("_DTIME_[M][apply_start_time]",报名开始分钟);
		  map2.put("_DTYPE_TIME[]","apply_end_time");
		  map2.put("apply_end_time",年月日2);
		  map2.put("_DTIME_[H][apply_end_time]",报名结束小时);
		  map2.put("_DTIME_[M][apply_end_time]",报名结束分钟);
		  map2.put("_DTYPE_TIME[]","release_time");
		  map2.put("release_time",年月日3);
		  map2.put("_DTIME_[H][release_time]",活动发布小时);
		  map2.put("_DTIME_[M][release_time]",活动发布分钟);
		  map2.put("_DTYPE_TIME[]","start_time");
		  map2.put("start_time",年月日4);
		  map2.put("_DTIME_[H][start_time]",活动开始小时);
		  map2.put("_DTIME_[M][start_time]",活动开始分钟);
		  map2.put("_DTYPE_TIME[]","end_time");
		  map2.put("end_time",年月日5);
		  map2.put("_DTIME_[H][end_time]",活动结束小时);
		  map2.put("_DTIME_[M][end_time]",活动结束分钟);		  
		  map2.put("user_buy_limit","5");
		  map2.put("shop_apply_limit","1000000");
		  map2.put("used_shop_type_list[]","all");
		  map2.put("used_category_list[]","all");
		  map2.put("discount_min","1");
		  map2.put("discount_max","99");
		  
		  map2.put("uploadimg","添加广告图");
		  map2.put("specialflashsale_image","https://image.trc.com/Fu8seohioJYBEuwDd7nzuPoW8yMo");
		  map2.put("specialflashsale_title","热门品牌专场特卖");
		  map2.put("specialflashsale_subtitle","爆款限时抢购中");
		  
		  
		  HttpUtil.doPostSSL(发布, map2);
		  HttpUtil.printStatus();
		  HttpUtil.printCookie();
		  HttpUtil.printEntity();
		  return HttpUtil.getEntitys();
	 	}
	  
	  public static String PlatformCoupon(
			  String huanjing,
			  String name,
			  String 满的金额,
			  String 减的金额,
			  int 报名持续时间,
			  int 发布前持续时间,
			  int 发布后持续时间,
			  int 领取持续时间,
			  int 使用的持续时间
			  
			  ){
		 login(huanjing);
		 String couponadd=huanjing+manage.平台优惠券;
		 Map<String,String> map0=new HashMap<>();
		 
		  Date date = new Date();
		 //当前时间加一分钟作为报名起始时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 1);
		  String 年月日1=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 报名开始小时=DateUtil.formatDate("HH", date);
		  String 报名开始分钟=DateUtil.formatDate("mm", date); 
		  String 描述=DateUtil.formatDate("yyyy-MM-dd:HH:mm", date);
		  String names=name+描述;
		//时间加报名持续时间作为报名结束时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 报名持续时间);
		  String 年月日2=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 报名结束小时=DateUtil.formatDate("HH", date);
		  String 报名结束分钟=DateUtil.formatDate("mm", date); 

		 //时间加发布前持续时间等于活动发布时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 发布前持续时间);
		  String 年月日3=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 活动发布小时=DateUtil.formatDate("HH", date);
		  String 活动发布分钟=DateUtil.formatDate("mm", date);   

		  
		  
		  
		//时间加开始前时间作为领取开始时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 发布后持续时间);
		  String 年月日4=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 领取开始小时=DateUtil.formatDate("HH", date);
		  String 领取开始分钟=DateUtil.formatDate("mm", date);  		  
		  Date date1=date;
		//时间加开始前时间作为领取结束时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 领取持续时间);
		  String 年月日5=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 领取结束小时=DateUtil.formatDate("HH", date);
		  String 领取结束分钟=DateUtil.formatDate("mm", date);   
		  
//		  优惠券生效开始时间=优惠券可领取时间+1分钟
		  date1=DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 1);
		//时间加开始前时间作为活动开始时间
//		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 领取持续时间);
		  String 年月日6=DateUtil.formatDate("yyyy-MM-dd", date1);
		  String 使用开始小时=DateUtil.formatDate("HH", date1);
		  String 使用开始分钟=DateUtil.formatDate("mm", date1);  		  

		//时间加开始前时间作为活动开始时间
		  date1 =DateUtil.getTimeAfterAdd(date1, Calendar.MINUTE, 使用的持续时间);
		  String 年月日7=DateUtil.formatDate("yyyy-MM-dd", date1);
		  String 使用结束小时=DateUtil.formatDate("HH", date1);
		  String 使用结束分钟=DateUtil.formatDate("mm", date1);  
		  
		  map0.put("channel_shopmall","ShopMall");
		  map0.put("shopmall_undertake_percent","100");
		  map0.put("finance_undertake_percent","");
		  map0.put("integral_undertake_percent","");
		  
		 map0.put("coupon_id","");
		 map0.put("used_brand_list",",6,7,8,9,10,11,12,13,14,15");
		 map0.put("apply_edit_status","");
		 map0.put("coupon_name",name);
		 map0.put("exchange_code",描述);
		 map0.put("limit_money",满的金额);
		 map0.put("deduct_money",减的金额);
		 map0.put("shop_undertake_percent","1");
		 map0.put("send_quantity","2");
		 map0.put("user_obtain_limit","2");
		 map0.put("show_entrance","1");
		 map0.put("used_platform_list[]","all");
//		 map0.put("used_platform_list[]","wap");
//		 map0.put("used_platform_list[]","pc");
		 map0.put("used_shop_type_list[]","all");
//		 map0.put("used_shop_type_list[]","flag");
//		 map0.put("used_shop_type_list[]","brand");
//		 map0.put("used_shop_type_list[]","cat");
//		 map0.put("used_shop_type_list[]","self");
//		 map0.put("used_shop_type_list[]","store");
		 map0.put("used_category_list_all","all");
//		 map0.put("used_category_list[]","48");
//		 map0.put("used_category_list[]","49");
//		 map0.put("used_category_list[]","44");
//		 map0.put("used_category_list[]","45");
//		 map0.put("used_category_list[]","47");
//		 map0.put("used_category_list[]","51");
//		 map0.put("used_category_list[]","54");
//		 map0.put("used_category_list[]","57");
//		 map0.put("used_category_list[]","62");
//		 map0.put("used_category_list[]","63");
		 map0.put("used_brand_list_all","all");
//		 map0.put("brand_list[]","6");
//		 map0.put("brand_list[]","7");
//		 map0.put("brand_list[]","8");
//		 map0.put("brand_list[]","9");
//		 map0.put("brand_list[]","10");
//		 map0.put("brand_list[]","11");
//		 map0.put("brand_list[]","12");
//		 map0.put("brand_list[]","13");
//		 map0.put("brand_list[]","14");
//		 map0.put("brand_list[]","15");
		 map0.put("_DTYPE_TIME[0]","apply_start_time");
		 map0.put("apply_start_time",年月日1);
		 map0.put("_DTIME_[H][apply_start_time]",报名开始小时);
		 map0.put("_DTIME_[M][apply_start_time]",报名开始分钟);
		 map0.put("_DTYPE_TIME[1]","apply_end_time");
		 map0.put("apply_end_time",年月日2);
		 map0.put("_DTIME_[H][apply_end_time]",报名结束小时);
		 map0.put("_DTIME_[M][apply_end_time]",报名结束分钟);
		 map0.put("_DTYPE_TIME[2]","release_time");
		 map0.put("release_time",年月日3);
		 map0.put("_DTIME_[H][release_time]",活动发布小时);
		 map0.put("_DTIME_[M][release_time]",活动发布分钟);
		 map0.put("_DTYPE_TIME[3]","obtain_start_time");
		 map0.put("obtain_start_time",年月日4);
		 map0.put("_DTIME_[H][obtain_start_time]",领取开始小时);
		 map0.put("_DTIME_[M][obtain_start_time]",领取开始分钟);
		 map0.put("_DTYPE_TIME[4]","obtain_end_time");
		 map0.put("obtain_end_time",年月日5);
		 map0.put("_DTIME_[H][obtain_end_time]",领取结束小时);
		 map0.put("_DTIME_[M][obtain_end_time]",领取结束分钟);
		 map0.put("_DTYPE_TIME[5]","use_start_time");
		 map0.put("use_start_time",年月日6);
		 map0.put("_DTIME_[H][use_start_time]",使用开始小时);
		 map0.put("_DTIME_[M][use_start_time]",使用开始分钟);
		 map0.put("_DTYPE_TIME[6]","use_end_time");
		 map0.put("use_end_time",年月日7);
		 map0.put("_DTIME_[H][use_end_time]",使用结束小时);
		 map0.put("_DTIME_[M][use_end_time]",使用结束分钟);

		  HttpUtil.doPostSSL(couponadd, map0);
		  HttpUtil.printStatus();
		  HttpUtil.printCookie();
		  HttpUtil.printEntity();
		  return HttpUtil.getEntitys();
	  }
	  
	  public static String addGifts(
			  String huanjing,
			  String 礼包名称,
			  int 报名持续时间,
			  int 领取持续时间,
			  String 每人领取数量,
			  String 店铺报名数量
			  ){
		  
		  String giftapi=huanjing+manage.优惠券礼包;
		  login(huanjing);
		  
		  Date date = new Date();
		 //当前时间加一分钟作为报名开始时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 1);
		  String 年月日1=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 报名开始小时=DateUtil.formatDate("HH", date);
		  String 报名开始分钟=DateUtil.formatDate("mm", date); 
		  String 描述=DateUtil.formatDate("yyyy-MM-dd:HH:mm", date);
		 
		//时间加报名持续时间作为领取开始时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 报名持续时间);
		  String 年月日2=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 领取开始小时=DateUtil.formatDate("HH", date);
		  String 领取开始分钟=DateUtil.formatDate("mm", date); 

		 //时间加领取持续时间等于领取结束时间
		  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 领取持续时间);
		  String 年月日3=DateUtil.formatDate("yyyy-MM-dd", date);
		  String 领取结束小时=DateUtil.formatDate("HH", date);
		  String 领取结束分钟=DateUtil.formatDate("mm", date);   
		  
		  Map<String,String> map1=new HashMap<>();
		  map1.put("package_id","");
		  map1.put("package_name",礼包名称);
		  map1.put("send_quantity","10");
		  map1.put("user_obtain_limit",每人领取数量);
		  map1.put("shop_apply_limit",店铺报名数量);
		  map1.put("rand_obtain","0");
		  map1.put("_DTYPE_TIME[0]","apply_start_time");
		  map1.put("apply_start_time",年月日1);
		  map1.put("_DTIME_[H][apply_start_time]",报名开始小时);
		  map1.put("_DTIME_[M][apply_start_time]",报名开始分钟);
		  map1.put("_DTYPE_TIME[1]","obtain_start_time");
		  map1.put("obtain_start_time",年月日2);
		  map1.put("_DTIME_[H][obtain_start_time]",领取开始小时);
		  map1.put("_DTIME_[M][obtain_start_time]",领取开始分钟);
		  map1.put("_DTYPE_TIME[2]","obtain_end_time");
		  map1.put("obtain_end_time",年月日3);
		  map1.put("_DTIME_[H][obtain_end_time]",领取结束小时);
		  map1.put("_DTIME_[M][obtain_end_time]",领取结束分钟);

		  
		  
		  HttpUtil.doPostSSL(giftapi, map1);
		  HttpUtil.printStatus();
		  HttpUtil.printCookie();
		  HttpUtil.printEntity();
		  return HttpUtil.getEntitys();
		  
	  }
	  public static String getFirstTemaiID(String huanjing){
		login(huanjing);
		String api=huanjing+manage.获取特卖活动列表第一页;
		Map<String,String> map1=new HashMap<>();
		map1.put("_finder[finder_id]","133f3a");
		map1.put("_finder[in_pager]","1");
		map1.put("search_status","3");
		HttpUtil.doPost(api, map1);
		String docs=HttpUtil.getEntitys();
		Document doc=Jsoup.parse(docs);
		Elements ss1=doc.select("tr[item-id]");
		if(0==ss1.size()){
			return "未找到特卖活动";
		}
		Element ss=doc.select("tr[item-id]").get(0);
		String result=ss.attr("item-id");		
		return result;
	  }
	  public static Map<String,String> 自动审核特卖(String huanjing){
		  login(huanjing);
		  String 获取列表=huanjing+manage.获取特卖报名列表地址;
		  HttpUtil.doPost(获取列表);
		  String docs1=HttpUtil.getEntitys();
		
		  Document doc=Jsoup.parse(docs1);
//		  logger.info(doc);
//		  Elements ss=doc.select("a:contains(审核)");
		  Elements ss=doc.select("a:contains(审核)");
//	  Elements ss=doc.select("[target=_blank]");
//		  Elements ss=doc.select("a");  
		  List<String> target= new ArrayList<>();
		  logger.info("当前获取到的数量为："+ss.size());
		  int i=0;
		  for (Element e:ss) {
			  target.add(e.attr("href"));
			logger.info("获取到的链接：第"+i+"个，链接为："+e.attr("href"));
		}
		  Map<String,String> map1=new HashMap<>();
		  map1.put("count", ""+(target.size()));
		  String result="";
		  if(0!=target.size()){
			  for (int j = 0; j < target.size(); j++) {
					String aaaa=huanjing+"/shopadmin"+target.get(j);
					HttpUtil.doGet(aaaa);
					String docs=HttpUtil.getEntitys();
//					logger.info(docs);
					  Document doc1=Jsoup.parse(docs);
//					  Elements sss=doc1.select("[name*=shop_id]");
//					  Elements sss=doc1.select("input");
//					  System.out.println(sss.size());		
					  String activity_id=doc1.select("[name=activity_id]").get(0).attr("value");
					  String regster_id=doc1.select("[name=register_id]").get(0).attr("value");
					  String shop_id=doc1.select("[name=shop_id]").get(0).attr("value");
					  Elements aaaass=doc1.select("[name=itemchk]");
					  String items="";
					  for (int k = 0; k < aaaass.size(); k++) {
						if(k==(aaaass.size()-1)){
							items+=aaaass.get(k).attr("value");
						}else{
							items+=aaaass.get(k).attr("value");
							items+=",";
						}
					  }
						Map<String,String> map3=new HashMap<>();
						map3.put("activity_id",activity_id);
						map3.put("item_id",items);
						map3.put("register_id",regster_id);
						map3.put("shop_id",shop_id);
						map3.put("verify_status","agree");
						String shenhe=huanjing+manage.审核特卖;
						HttpUtil.doPost(shenhe, map3);
						result+=("活动号为："+activity_id);
						result+=("items为："+items+"的审核结果为：");
						
						result+=HttpUtil.getEntitys();
						result+="<br/>";
						logger.info("第"+j+"次审核结果：");
						logger.info(HttpUtil.getEntitys());
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					  
				}
		  }else{
			  result="没有待审核的东东";
		  }

		  map1.put("result", result);
		  return map1;
		  
	  }
	  
	  public static String get第一个优惠券报名的ID(String huanjing){
		login(huanjing);
		Map<String,String> map1=new HashMap<>();
		String api=huanjing+manage.获取优惠券列表第一个;
		HttpUtil.doPost(api);
		String docs=HttpUtil.getEntitys();
//		logger.info(docs);
		Document doc=Jsoup.parse(docs);
		Elements aa=doc.select("tr[item-id]");
		
		System.out.println(aa.size());
		Element ss=doc.select("tr[item-id]").get(0);
		String result=ss.attr("item-id");		
		return result;
	  }
	  public static String get第一个礼包报名的ID(String huanjing){
		login(huanjing);
		String api=huanjing+manage.获取平台礼包报名第一个;
		HttpUtil.doPost(api);
		String docs=HttpUtil.getEntitys();
		Document doc=Jsoup.parse(docs);
		Element ss=doc.select("tr[item-id]").get(0);
		String result=ss.attr("item-id");		
		return result;
	  }
	  
	  public static Map<String, String> 审核礼包(String huanjing){
		  login(huanjing);
		  String  url=huanjing+manage.获取礼包审核列表;
		  HttpUtil.doPost(url);
		  String docs1=HttpUtil.getEntitys();
//		  logger.info(docs1);
		  Document doc=Jsoup.parse(docs1);
//		  logger.info(doc);
//		  Elements ss=doc.select("a:contains(审核)");
		  Elements ss=doc.select("a:contains(审核)");
		  List<String> target= new ArrayList<>();
		  logger.info("当前获取到的数量为："+ss.size());
		  int i=0;
		  for (Element e:ss) {
			  target.add(e.attr("href"));
			logger.info("获取到的链接：第"+i+"个，链接为："+e.attr("href"));
		}
		  Map<String,String> map1=new HashMap<>();
		  map1.put("count", ""+(target.size()));
		  String result="";
		  if(0!=target.size()){
			  for (int j = 0; j < target.size(); j++) {
					String aaaa=huanjing+"/shopadmin"+target.get(j);
					HttpUtil.doGet(aaaa);
					String docs=HttpUtil.getEntitys();
//					logger.info(docs);
					  Document doc1=Jsoup.parse(docs);
//					  Elements sss=doc1.select("[name*=shop_id]");
//					  Elements sss=doc1.select("input");
//					  System.out.println(sss.size());		
					  String activity_id=doc1.select("[name=activity_id]").get(0).attr("value");
					  String regster_id=doc1.select("[name=register_id]").get(0).attr("value");
					  String shop_id=doc1.select("[name=shop_id]").get(0).attr("value");
					  Elements aaaass=doc1.select("[name=itemchk]");
					  String items="";
					  for (int k = 0; k < aaaass.size(); k++) {
						if(k==(aaaass.size()-1)){
							items+=aaaass.get(k).attr("value");
						}else{
							items+=aaaass.get(k).attr("value");
							items+=",";
						}
					  }
						Map<String,String> map3=new HashMap<>();
						map3.put("activity_id",activity_id);
						map3.put("coupon_ids",items);
						map3.put("register_id",regster_id);
						map3.put("shop_id",shop_id);
						map3.put("verify_status","agree");
						String shenhe=huanjing+manage.审核礼包;
						HttpUtil.doPost(shenhe, map3);
						result+=("活动号为："+activity_id);
						result+=("items为："+items+"的审核结果为：");
						result+=HttpUtil.getEntitys();
						result+="<br/>";
						logger.info("第"+j+"次审核结果：");
						logger.info(HttpUtil.getEntitys());
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					  
				}
		  }else{
			  result="没有待审核的东东";
		  }

		  map1.put("result", result);
		  return map1;
	  }
	  
	  public static Map<String, String> 审核拼团(String huanjing){
		  login(huanjing);
		  String  url=huanjing+manage.拼团报名列表;
		  HttpUtil.doPost(url);
		  String docs1=HttpUtil.getEntitys();
//		  logger.info(docs1);
		  Document doc=Jsoup.parse(docs1);
//		  logger.info(doc);
//		  Elements ss=doc.select("a:contains(审核)");
		  Elements ss=doc.select("a:contains(审核通过)");
		  List<String> target= new ArrayList<>();
		  logger.info("当前获取到的数量为："+ss.size());
		  int i=0;
		  for (Element e:ss) {
			  target.add(e.attr("href"));
			logger.info("获取到的链接：第"+i+"个，链接为："+e.attr("href"));
		}
		  Map<String,String> map1=new HashMap<>();
		  map1.put("count", ""+(target.size()));
		  String result="";
		  if(0!=target.size()){
			  for (int j = 0; j < target.size(); j++) {
					String aaaa=huanjing+"/shopadmin"+target.get(j);
					String group_id=paramsTest(target.get(j), "group_id");
					String item_id=paramsTest(target.get(j), "item_id");
					String sku_ids=paramsTest(target.get(j), "sku_ids");
					
					Map<String,String> map0=new HashMap<>();
					map0.put("act","approve");
					map0.put("app","syspromotion");
					map0.put("ctl","admin_groupbuyapply");
					map0.put("group_id",group_id);
					map0.put("item_id",item_id);
					map0.put("refuse_reason","testaaaa");
					map0.put("sku_ids",sku_ids);
					map0.put("verify_status","agree");
					
					HttpUtil.doGet(huanjing+manage.登录页面, map0);
						HttpUtil.printEntity();
						result+=("团号为："+group_id);
						result+=("item_id为："+item_id+"的审核结果为：");
						result+=HttpUtil.getEntitys();
						result+="********";
						logger.info("第"+j+"次审核结果：");
						logger.info(HttpUtil.getEntitys());
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					  
				}
		  }else{
			  result="没有待审核的东东";
		  }

		  map1.put("result", result);
		  return map1;
	  }
	  
	  public static String addPlatCouponAll(String huanjing,String name,String limit,String discount,
			  String wait,String ling,String youxiao){
		  login(huanjing);
		  String url=huanjing+"shopadmin?app=syspromotion&ctl=admin_activitycoupon&act=save";
		  Map<String,String> map1=new HashMap<>();
		  map1.put("coupon_id","");
		  map1.put("used_brand_list","");
		  map1.put("coupon_name",name);
		
				  Date date = new Date();
			 //当前时间加一分钟作为报名开始时间
			  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, Integer.parseInt(wait));
			  String 年月日1=DateUtil.formatDate("yyyy-MM-dd", date);
			  String 领取开始小时=DateUtil.formatDate("HH", date);
			  String 领取开始分钟=DateUtil.formatDate("mm", date); 
			  String 描述=DateUtil.formatDate("yyyy-MM-dd:HH:mm", date);
			  String code="测试"+描述;
			  
			  date =DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, Integer.parseInt(ling));
			  String 年月日2=DateUtil.formatDate("yyyy-MM-dd", date);
			  String 领取结束小时=DateUtil.formatDate("HH", date);
			  String 领取结束分钟=DateUtil.formatDate("mm", date); 
			  
		  map1.put("exchange_code",code);
		  map1.put("limit_money",limit);
		  map1.put("deduct_money",discount);
		  map1.put("user_obtain_limit",ling);
		  map1.put("used_platform_list[]","all");
		  map1.put("used_category_list_all","all");
		  map1.put("used_brand_list_all","all");
		  map1.put("_DTYPE_TIME[]","obtain_start_time");
		  map1.put("obtain_start_time",年月日1);
		  map1.put("_DTIME_[H][obtain_start_time]",领取开始小时);
		  map1.put("_DTIME_[M][obtain_start_time]",领取开始分钟);
		  map1.put("_DTYPE_TIME[]","obtain_end_time");
		  map1.put("obtain_end_time",年月日2);
		  map1.put("_DTIME_[H][obtain_end_time]",领取结束小时);
		  map1.put("_DTIME_[M][obtain_end_time]",领取结束小时);
		  map1.put("expire_time",youxiao);
		  HttpUtil.doPost(url, map1);
		  HttpUtil.printEntity();
		  
		  
		return HttpUtil.getEntitys();
		  
	  }
	  
	  
	  public static Map<String, String> 审核优惠券(String huanjing){
		  login(huanjing);
		  String url=huanjing+manage.获取优惠券审核列表;
		  HttpUtil.doPost(url);
		  String docs1=HttpUtil.getEntitys();
//		  logger.info(docs1);
		  Document doc=Jsoup.parse(docs1);
//		  logger.info(doc);
//		  Elements ss=doc.select("a:contains(审核)");
		  Elements ss=doc.select("a:contains(审核)");
		  List<String> target= new ArrayList<>();
		  logger.info("当前获取到的数量为："+ss.size());
		  int i=0;
		  for (Element e:ss) {
			  target.add(e.attr("href"));
			logger.info("获取到的链接：第"+i+"个，链接为："+e.attr("href"));
		}
		  Map<String,String> map1=new HashMap<>();
		  map1.put("count", ""+(target.size()));
		  String result="";
		  if(0!=target.size()){
			  for (int j = 0; j < target.size(); j++) {
					String aaaa=huanjing+"/shopadmin"+target.get(j);
					HttpUtil.doGet(aaaa);
					String docs=HttpUtil.getEntitys();
//					logger.info(docs);
					  Document doc1=Jsoup.parse(docs);
//					  Elements sss=doc1.select("[name*=shop_id]");
//					  Elements sss=doc1.select("input");
//					  System.out.println(sss.size());		
					  String activity_id=doc1.select(".division tbody tr td").get(0).text();
					  String regster_id=getparams(target.get(j));
					  String shop_id=getparams1(target.get(j));
					  Elements aaaass=doc1.select("[name=item_list]");
					  String items="";
					  for (int k = 0; k < aaaass.size(); k++) {
						if(k==(aaaass.size()-1)){
							items+=aaaass.get(k).attr("value");
						}else{
							items+=aaaass.get(k).attr("value");
							items+=",";
						}
					  }
						Map<String,String> map3=new HashMap<>();
						map3.put("coupon_id",activity_id);
						map3.put("item_id",items);
						map3.put("register_id",regster_id);
						map3.put("shop_id",shop_id);
						map3.put("verify_status","agree");
						String shenhe=huanjing+manage.审核优惠券;
						HttpUtil.doPost(shenhe, map3);
						result+=("活动号为："+activity_id);
						result+=("items为："+items+"的审核结果为：");
						result+=HttpUtil.getEntitys();
						result+="<br/>";
						logger.info("第"+j+"次审核结果：");
						logger.info(HttpUtil.getEntitys());
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					  
				}
		  }else{
			  result="没有待审核的东东";
		  }

		  map1.put("result", result);
		  return map1;	  }
	  
	  public String 新增Banner(String position, String name,String imageURL,String type,String content,String order){
		  
		  
		return null;
		  
	  }
	  
	  
	  
	  
	  
	  
	  
	  public static String paramsTest(String source,String target){
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
		logger.info("从连接：*《"+source+"中取到的目标："+target+"是："+temp);
		return temp;
		  
	  }
	  
	  
	  public static String getparams(String source){
		  
		 String s[]=source.split("register_id=");
		  if(s[1].isEmpty()){
			  return "null";
		  }else{
			  String[] ss1=s[1].split("&");
			  return ss1[0];  
		  }

	}
	  
	  public static String getparams1(String source){
		  
		  String s[]=source.split("shop_id=");
		  if(s[1].isEmpty()){
			  return "null";
		  }else{
			 
			  return s[1];  
		  }

	}
	  
	  public static String 添加红包(String huanjing,String name,String a,String am,String lm,String plat,String type,int time1,int time2,int time3,int time4){
		 
		  login(huanjing);
		  String url=huanjing+接口地址.manage.添加红包;
		  
		  Map<String,String> map0=new HashMap<>();
		  map0.put("id","");
		  
		  map0.put("packet_name",name);
		  map0.put("limit_money",a);
		  map0.put("send_quantity",am);
		  map0.put("user_obtain_limit",lm);
		  map0.put("used_platform_list[]",plat);
//		  map0.put("used_platform_list[]","all");
//		  map0.put("used_platform_list[]","wap");
//		  map0.put("used_platform_list[]","pc");
		  map0.put("used_item_type_list[]",type);
//		  map0.put("used_item_type_list[]","all");
//		  map0.put("used_item_type_list[]","Normal");
//		  map0.put("used_item_type_list[]","Virtual");
//		  map0.put("used_item_type_list[]","Internal");

		  map0.put("_DTYPE_TIME[]","obtain_start_time");
		  map0.put("obtain_start_time",DateStringFactory.getAfterMinnite年月日(time1));
		  map0.put("_DTIME_[H][obtain_start_time]",DateStringFactory.getAfterMinnite时(time1));
		  map0.put("_DTIME_[M][obtain_start_time]",DateStringFactory.getAfterMinnite分(time1));
		  map0.put("_DTYPE_TIME[]","obtain_end_time");
		  map0.put("obtain_end_time",DateStringFactory.getAfterMinnite年月日(time2));
		  map0.put("_DTIME_[H][obtain_end_time]",DateStringFactory.getAfterMinnite时(time2));
		  map0.put("_DTIME_[M][obtain_end_time]",DateStringFactory.getAfterMinnite分(time2));
		  map0.put("_DTYPE_TIME[]","use_start_time");
		  map0.put("use_start_time",DateStringFactory.getAfterMinnite年月日(time3));
		  map0.put("_DTIME_[H][use_start_time]",DateStringFactory.getAfterMinnite时(time3));
		  map0.put("_DTIME_[M][use_start_time]",DateStringFactory.getAfterMinnite分(time3));
		  map0.put("_DTYPE_TIME[]","use_end_time");
		  map0.put("use_end_time",DateStringFactory.getAfterMinnite年月日(time4));
		  map0.put("_DTIME_[H][use_end_time]",DateStringFactory.getAfterMinnite时(time4));
		  map0.put("_DTIME_[M][use_end_time]",DateStringFactory.getAfterMinnite分(time4));
		  HttpUtil.doPost(url, map0);
		  logger.info(HttpUtil.getEntitys());
		return HttpUtil.getEntitys();
		  
	  }
	  

	  
	  
	  
	  public static void main(String[] args) throws IOException {
		try {
//			addTemai(1,1,1,1);
			//PlatformCoupon("http://view.trc.com/", "111", "150", "120", 3, 1, 1, 10000, 10000);
			 login("http://www.tairanmall.com/");
			PlatformCoupon("http://www.tairanmall.com/", "111", "150", "120", 3, 1, 1, 10000, 10000);
			//Internal
			//Normal
			//Virtual
//			for (int i = 0; i < 1; i++) {
//				添加红包("http://pre.trc.com/", "普通100", "100", "10000", "100","all", "all0222", 1,99999, 1,99999 );
//			//	添加红包("http://pre.trc.com/", "50元", "50", "10000", "100","all", "all", 1,99999, 1,99999 );
//			
//			}
			
//			String ure="http://view.trc.com/obtain-redPacket.html?packet_id=1379";
//			
//			HttpUtil.doPost(ure);
			
			
//			addPlatCouponAll("http://vie22022w.trc.com/", "测试环境222222", "", "35", "2", "99999", "3");
//			addGifts("图片太吓人了", 1, 1, "1", "1");"
//			System.out.println(getFirstTemaiID("http://view.trc.com/"));
//			Map<String,String> aa= 自动审核特卖("http://view.trc.com/");
//			System.out.println(aa.get("result"));
//			System.out.println(get第一个优惠券报名的ID("http://view.trc.com/"));
//			System.out.println(get第一个礼包报名的ID("http://view.trc.com/"));
//			审核礼包("http://view.trc.com/");
//		String ss="?app=syspromotion&ctl=admin_couponapply&act=show&_finder[finder_id]=17a59f&coupon_id=354&register_id=2776&shop_id=2";
//		System.out.println(getparams(ss));
//		System.out.println(getparams1(ss));
			
//			审核优惠券("http://view.trc.com/");
//			String source="?app=syspromotion&ctl=admin_groupbuyapply&act=approveConfirm&group_id=3382&item_id=28478&sku_ids=54586&verify_status=agree&_finder[finder_id]=f5b93e";
//			String target="item_id";
//			paramsTest(source, target);
			//审核拼团("http://view.trc.com/");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
