package tools.Interface.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.trc.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.EnvironmentUtil.接口;
import tools.EnvironmentUtil.接口地址;
import tools.EnvironmentUtil.接口地址.shop;
import tools.Interface.manage.ManagePlatform;
import tools.util.DateStringFactory;
import tools.util.DateUtil;
import tools.util.HttpUtil;

/** 
*@author  作者 : 陈达
*@date 创建时间：2017年3月17日 上午9:45:09
*@version 1.0 
*@parameter  
*@return 
*/
public class ShopThread {
	public  static final Logger logger = Logger.getLogger(ShopOperate.class);
	static Map<String,String> map2=new HashMap<>();
	static{
		map2.put("Connection","keep-alive");
		map2.put("Accept","*/*");
//		map2.put("Origin","http://view.trc.com");
		map2.put("X-Requested-With","XMLHttpRequest");
		map2.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
		map2.put("Referer","http://view.trc.com/member-idcard.html");
		map2.put("Accept-Encoding","gzip, deflate, sdch");
		map2.put("Accept-Language","zh-CN,zh;q=0.8");
		map2.put("Host", "view.trc.com");
	}
	public static void 登录VIEW(){
		String ShopUrl ="http://view.trc.com/"+shop.入口;
		HttpUtil.doGet(ShopUrl);
		String ShopLogin ="http://view.trc.com/"+ shop.登录;
		logger.info("登录开始");
//		System.out.println("你皮任你皮");
		Map<String, String> map1 = new HashMap<>();
		map1.put("login_account", "hulijuan");
		map1.put("login_password", "admin123");
		HttpUtil.doPost(ShopLogin, map1);
	}
	public static boolean login(String huanjing, String username, String password) {
		String ShopUrl;
		String ShopLogin;
		ShopUrl=huanjing+shop.入口;
		ShopLogin=huanjing+shop.登录;
		HttpUtil.doGet(ShopUrl);
		Map<String, String> map1 = new HashMap<>();
		map1.put("login_account", username);
		map1.put("login_password", password);
		logger.info("本次登录的环境地址："+username);
		logger.info("本次登录的店铺账号："+username);
		HttpUtil.doPost(ShopLogin, map1);
		// 已获取登录状态
		HttpUtil.printEntity();
		String code = HttpUtil.getStatus();
		if ("HTTP/1.1 200 OK".equals(code)) {
			return true;
		} else {
			return false;
		}
	}
	public static String AddSku(String huanjing,String username, String password,  String name,String type) {
		
		login(huanjing, username, password);
		String ShopStoreItem =huanjing+shop.发布商品;
		String goodsadd=huanjing+shop.发布商品页面;
		HttpUtil.doGet(goodsadd);
		String docss=HttpUtil.getEntitys();
		Document doc=Jsoup.parse(docss);
		Elements ss=doc.select("#act-selectdlytmpl option");
		String yunfei = "";
		
		if(0<ss.size()){
			for(Element e:ss){
				yunfei=e.attr("value");
			}
		}else{
			yunfei="7";
		}
		
		
		logger.info("ShopStoreItem:" +ShopStoreItem);
		Map<String,String> map0=new HashMap<>();
		switch (type) {
		case "1":
			map0.put("item[trade_type]","Domestic");
			map0.put("item[tax_rule_id]","");
			break;
		case "2":
			map0.put("item[trade_type]","Direct");
			map0.put("item[tax_rule_id]","1");
			break;
		case "3":
			map0.put("item[trade_type]","Bonded");
			map0.put("item[tax_rule_id]","1");
			break;
		case "4":
			map0.put("item[trade_type]","Overseas");
			map0.put("item[tax_rule_id]","1");
			break;

		default:
			map0.put("item[trade_type]","Domestic");
			map0.put("item[tax_rule_id]","");
			break;
		}
		
		
		map0.put("item[item_id]","");
		map0.put("return_to_url","");
		map0.put("isZero","");
		map0.put("cat_id","399");
		map0.put("item[sku]","{\"6602294be9\":{\"sku_id\":\"new\",\"spec_desc\":{\"spec_private_value_id\":{\"161\":\"\"},\"spec_value\":{\"161\":\"1\"},\"spec_value_id\":{\"161\":\"971\"}},\"price\":\"30\",\"store\":\"100000\"},\"4311359ed4\":{\"sku_id\":\"new\",\"spec_desc\":{\"spec_private_value_id\":{\"161\":\"\"},\"spec_value\":{\"161\":\"4\"},\"spec_value_id\":{\"161\":\"974\"}},\"price\":\"98\",\"store\":\"1000000\"},\"cc1aa43627\":{\"sku_id\":\"new\",\"spec_desc\":{\"spec_private_value_id\":{\"161\":\"\"},\"spec_value\":{\"161\":\"7\"},\"spec_value_id\":{\"161\":\"977\"}},\"price\":\"198\",\"store\":\"100000\"}}");
		map0.put("item[spec]","[null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,{\"spec_name\":\"规格\",\"spec_id\":\"161\",\"show_type\":\"text\",\"option\":{\"971\":{\"private_spec_value_id\":\"\",\"spec_value\":\"1\",\"spec_value_id\":\"971\"},\"974\":{\"private_spec_value_id\":\"\",\"spec_value\":\"4\",\"spec_value_id\":\"974\"},\"977\":{\"private_spec_value_id\":\"\",\"spec_value\":\"7\",\"spec_value_id\":\"977\"}}}]");
		map0.put("item[shop_cids][]","61");
		map0.put("item[title]",name);
		map0.put("item[sub_title]","1");
		map0.put("search_brand","");
		map0.put("item[brand_id]","12");
		map0.put("item[bn]","146465");
		map0.put("item[barcode]","");
		map0.put("item[supplier_name]","家具啊");
		map0.put("item[type]","Normal");
		map0.put("item[installment]","0");
		map0.put("item[installment_text]","");
		map0.put("item[invest]","0");
		map0.put("listimages[]","https://image.trc.com/Fozx-rRn7XWuykDubnr875JW07xc");
		map0.put("item[use_platform]","0");
		map0.put("item[price]","100");
		map0.put("item[store]","100000");
		map0.put("item[sub_stock]","0");
		map0.put("item[mkt_price]","111");
		map0.put("item[cost_price]","222");
		map0.put("item[weight]","1");
		map0.put("item[dlytmpl_id]",yunfei);
		map0.put("item[storehouse_id]","2");
		map0.put("item[installment_strategy]","");
		map0.put("item[invest_data][20][invest]","");
		map0.put("item[invest_data][20][profit]","");
		map0.put("item[invest_data][120][invest]","");
		map0.put("item[invest_data][120][profit]","");
		map0.put("item[invest_data][210][invest]","");
		map0.put("item[invest_data][210][profit]","");
		map0.put("item[nature_props][37]","");
		map0.put("item[nature_props][165]","");
		map0.put("spec_value[161_971]","1");
		map0.put("spec_value[161_972]","2");
		map0.put("spec_value[161_973]","3");
		map0.put("spec_value[161_974]","4");
		map0.put("spec_value[161_975]","5");
		map0.put("spec_value[161_976]","6");
		map0.put("spec_value[161_977]","7");
		map0.put("spec_value[161_978]","8");
		map0.put("spec_value[161_979]","9");
		map0.put("spec_value[161_980]","10");
		map0.put("spec_value[161_1012]","11");
		map0.put("spec_value[161_1013]","12");
		map0.put("spec_value[161_1014]","13");
		map0.put("spec_value[161_1015]","14");
		map0.put("spec_value[161_1016]","15");
		map0.put("spec_value[161_1017]","16");
		map0.put("spec_value[161_1018]","17");
		map0.put("spec_value[161_1019]","18");
		map0.put("spec_value[161_1020]","19");
		map0.put("spec_value[161_1021]","20");
		map0.put("spec_value[161_1022]","21");
		map0.put("spec_value[161_1023]","22");
		map0.put("6602294be9[sku_id]","new");
		map0.put("6602294be9[price]","30");
		map0.put("6602294be9[spec]","规格:1");
		map0.put("skuInput[0]","6602294be9");
		map0.put("6602294be9[store]","100000");
		map0.put("6602294be9[barcode]","");
		map0.put("6602294be9[mkt_price]","");
		map0.put("6602294be9[cost_price]","");
		map0.put("6602294be9[invest][20][invest]","");
		map0.put("6602294be9[invest][20][profit]","");
		map0.put("6602294be9[invest][120][invest]","");
		map0.put("6602294be9[invest][120][profit]","");
		map0.put("6602294be9[invest][210][invest]","");
		map0.put("6602294be9[invest][210][profit]","");
		map0.put("6602294be9[installment_strategy]","");
		map0.put("4311359ed4[sku_id]","new");
		map0.put("4311359ed4[price]","98");
		map0.put("4311359ed4[spec]","规格:4");
		map0.put("skuInput[1]","4311359ed4");
		map0.put("4311359ed4[store]","1000000");
		map0.put("4311359ed4[barcode]","");
		map0.put("4311359ed4[mkt_price]","");
		map0.put("4311359ed4[cost_price]","");
		map0.put("4311359ed4[invest][20][invest]","");
		map0.put("4311359ed4[invest][20][profit]","");
		map0.put("4311359ed4[invest][120][invest]","");
		map0.put("4311359ed4[invest][120][profit]","");
		map0.put("4311359ed4[invest][210][invest]","");
		map0.put("4311359ed4[invest][210][profit]","");
		map0.put("4311359ed4[installment_strategy]","");
		map0.put("cc1aa43627[sku_id]","new");
		map0.put("cc1aa43627[price]","198");
		map0.put("cc1aa43627[spec]","规格:7");
		map0.put("skuInput[2]","cc1aa43627");
		map0.put("cc1aa43627[store]","100000");
		map0.put("cc1aa43627[barcode]","");
		map0.put("cc1aa43627[mkt_price]","");
		map0.put("cc1aa43627[cost_price]","");
		map0.put("cc1aa43627[invest][20][invest]","");
		map0.put("cc1aa43627[invest][20][profit]","");
		map0.put("cc1aa43627[invest][120][invest]","");
		map0.put("cc1aa43627[invest][120][profit]","");
		map0.put("cc1aa43627[invest][210][invest]","");
		map0.put("cc1aa43627[invest][210][profit]","");
		map0.put("cc1aa43627[installment_strategy]","");
		map0.put("item[desc]","");
		map0.put("item[wap_desc]","");
		HttpUtil.doPost(ShopStoreItem, map0);
		HttpUtil.printStatus();
		HttpUtil.printEntity();
		return HttpUtil.getEntitys();

	}
	public static String AddNoSku(String huanjing,String username, String password, String price, String name, String store,String type) {
		
		login(huanjing, username, password);
		String ShopStoreItem =huanjing+shop.发布商品;
		String goodsadd=huanjing+shop.发布商品页面;
		HttpUtil.doGet(goodsadd);
		String docss=HttpUtil.getEntitys();
		Document doc=Jsoup.parse(docss);
		Elements ss=doc.select("#act-selectdlytmpl option");
		String yunfei = "";
		if(0<ss.size()){
			for(Element e:ss){
				yunfei=e.attr("value");
			}
		}else{
			yunfei="7";
		}
		
		
		logger.info("ShopStoreItem:" +ShopStoreItem);
		Map<String,String> map0=new HashMap<>();
		map0.put("item[item_id]","");
		map0.put("return_to_url","");
		map0.put("isZero","");
		map0.put("cat_id","77");
		map0.put("item[sku]","{}");
		map0.put("item[spec]","[]");
		map0.put("item[shop_cids][]","4");
		map0.put("item[sub_title]","111");
		map0.put("search_brand","");
		map0.put("item[brand_id]","757");
		map0.put("item[bn]","1001");
		map0.put("item[barcode]","");
		map0.put("item[supplier_name]","测试供应商");
		map0.put("item[type]","Normal");
		map0.put("item[installment]","0");
		map0.put("item[installment_text]","");
		map0.put("item[invest]","0");
		map0.put("listimages[]","https://image.trc.com/FlVSKryeGUOVaniabu36wf_ecumn");
		map0.put("item[use_platform]","0");
		map0.put("item[sub_stock]","0");
		map0.put("item[mkt_price]","1000");
		map0.put("item[cost_price]","");
		map0.put("item[weight]","1");
		
//		Map<String,String> map0=new HashMap<>();
//		map0.put("item[item_id]","");
//		map0.put("return_to_url","");
//		map0.put("isZero","");
//		map0.put("cat_id","67");
//		map0.put("item[sku]","{}");
//		map0.put("item[spec]","[]");
//		map0.put("item[shop_cids][]","4");
////		map0.put("item[title]","test");
//		map0.put("item[sub_title]","admi123");
//		map0.put("search_brand","");
//		map0.put("item[brand_id]","18");
//		map0.put("item[bn]","111");
//		map0.put("item[barcode]","");
//		map0.put("item[supplier_name]","test1122");
//		map0.put("item[type]","Normal");
//		map0.put("item[installment]","0");
//		map0.put("item[installment_text]","");
//		map0.put("item[invest]","0");
//		map0.put("listimages[]","https://image.trc.com/FjBqjdJg_BIbCK8WAFBqCdJ81ath");
//		map0.put("item[use_platform]","0");
////		map0.put("item[price]","100");
////		map0.put("item[store]","100000");
//		map0.put("item[sub_stock]","0");
//		map0.put("item[mkt_price]","200.88");
//		map0.put("item[cost_price]","111");
//		map0.put("item[weight]","1");
////		map0.put("item[trade_type]","Domestic");
//		map0.put("item[tax_rule_id]","");
//		map0.put("item[dlytmpl_id]","7");
//		map0.put("item[storehouse_id]","3");
//		map0.put("item[invest_data][20][invest]","");
//		map0.put("item[invest_data][20][profit]","");
//		map0.put("item[invest_data][120][invest]","");
//		map0.put("item[invest_data][120][profit]","");
//		map0.put("item[invest_data][210][invest]","");
//		map0.put("item[invest_data][210][profit]","");
//		map0.put("item[nature_props][35]","");
//		map0.put("item[desc]","");
//		map0.put("item[wap_desc]","");
//		map0.put("item[free_refund]","1");
//		
//		map0.put("item[title]",name);
//		map0.put("item[price]",price);
//		map0.put("item[store]",store);
		
		
		switch (type) {
		case "1":
			map0.put("item[trade_type]","Domestic");
			map0.put("item[tax_rule_id]","");
			break;
		case "2":
			map0.put("item[trade_type]","Direct");
			map0.put("item[tax_rule_id]","1");
			break;
		case "3":
			map0.put("item[trade_type]","Bonded");
			map0.put("item[tax_rule_id]","1");
			break;
		case "4":
			map0.put("item[trade_type]","Overseas");
			map0.put("item[tax_rule_id]","1");
			break;

		default:
			map0.put("item[trade_type]","Domestic");
			map0.put("item[tax_rule_id]","");
			break;
		}
		

		map0.put("item[dlytmpl_id]",yunfei);
		map0.put("item[storehouse_id]","417");
		map0.put("item[installment_strategy]","");
		map0.put("item[invest_data][20][invest]","");
		map0.put("item[invest_data][20][profit]","");
		map0.put("item[invest_data][120][invest]","");
		map0.put("item[invest_data][120][profit]","");
		map0.put("item[invest_data][210][invest]","");
		map0.put("item[invest_data][210][profit]","");
		map0.put("item[nature_props][165]","");
		map0.put("item[nature_props][37]","");
		map0.put("spec_value[161_1023]","22");
		map0.put("spec_value[161_1022]","21");
		map0.put("spec_value[161_1021]","20");
		map0.put("spec_value[161_1020]","19");
		map0.put("spec_value[161_1019]","18");
		map0.put("spec_value[161_1018]","17");
		map0.put("spec_value[161_1017]","16");
		map0.put("spec_value[161_1016]","15");
		map0.put("spec_value[161_1015]","14");
		map0.put("spec_value[161_1014]","13");
		map0.put("spec_value[161_1013]","12");
		map0.put("spec_value[161_1012]","11");
		map0.put("spec_value[161_980]","10");
		map0.put("spec_value[161_979]","9");
		map0.put("spec_value[161_978]","8");
		map0.put("spec_value[161_977]","7");
		map0.put("spec_value[161_976]","6");
		map0.put("spec_value[161_975]","5");
		map0.put("spec_value[161_974]","4");
		map0.put("spec_value[161_973]","3");
		map0.put("spec_value[161_972]","2");
		map0.put("spec_value[161_971]","1");
		map0.put("item[desc]","");
		map0.put("item[wap_desc]","");
		map0.put("item[title]",name);
		map0.put("item[price]",price);
		map0.put("item[store]",store);
		HttpUtil.doPost(ShopStoreItem, map0);
		HttpUtil.printStatus();
		HttpUtil.printEntity();
		return HttpUtil.getEntitys();

	}

	public static String AddShopCoupon(String huanjing,String username,String password,String 优惠券名称, String 满, String 减, String 总数, String 每人可领取数量, int 领多少,
			int 持续多少, String 商品数组,String 是否显示入口) {

		login(huanjing, username, password);
		
		String API = huanjing+shop.发布优惠券;
		Map<String, String> map0 = new HashMap<>();
		map0.put("coupon_id", "");
		map0.put("coupon_name", 优惠券名称);
		map0.put("limit_money", 满);
		map0.put("deduct_money", 减);
		map0.put("send_quantity", 总数);
		map0.put("user_obtain_limit", 每人可领取数量);
		map0.put("show_entrance", 是否显示入口);

		Date date = new Date();
		// 当前时间加一分钟作为报名起始时间
		date = DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 1);
		Date date1 =date;
		String 可领取开始时间=DateUtil.formatDate("yyyy/MM/dd HH:mm", date);
		date = DateUtil.getTimeAfterAdd(date, Calendar.MINUTE, 领多少);
		String 可领取结束时间=DateUtil.formatDate("yyyy/MM/dd HH:mm", date);
		
		String 可使用开始时间=DateUtil.formatDate("yyyy/MM/dd HH:mm", date1);
		date1 = DateUtil.getTimeAfterAdd(date1, Calendar.MINUTE, 持续多少);
		String 可使用结束时间=DateUtil.formatDate("yyyy/MM/dd HH:mm", date1);
		
		map0.put("cansend_time", 可领取开始时间+"-"+可领取结束时间);
		map0.put("valid_time", 可使用开始时间+"-"+可使用结束时间);
		ArrayList<String> 使用平台 = new ArrayList();
		使用平台.add("all");
		使用平台.add("wap");
		使用平台.add("pc");
		
		ArrayList<String> 商品数组列表 = new ArrayList();
		String[] 商品编号=商品数组.split(",");
		for (int i = 0; i < 商品编号.length; i++) {
			商品数组列表.add(商品编号[i]);
		}
		
		Map<String, List<String>> map2 = new HashMap();
		map2.put("used_platform[]", 使用平台);
		map2.put("item_id[]", 商品数组列表);
		HttpUtil.doPost(map2, API, map0);
		HttpUtil.printStatus();
		HttpUtil.printEntity();
		return HttpUtil.getEntitys();

	}

	public static String  上架所有未上架的商品(){
		String 上架接口="http://view.trc.com/shop/item/setItemStatus.html";
		Map<String,String> my=new HashMap<>();
		my.put("type", "tosale");
		Map<String,List<String>> my1=new HashMap<>();
		List<String> ss=test0408();
		if(0==ss.size()){
			return "没东西可上架！";
		}
		my1.put("item_ids[]", ss);
		HttpUtil.doPost(my1, 上架接口, my);
		String sss=HttpUtil.getEntitys();
		System.out.println(sss);
		return sss;
		
	}
	
	public static List<String> test0408() {

		ShopAuthentication.店铺登录("hz1703201400", "admin123");

		String start = "http://view.trc.com/shop/item/itemList.html?pages=1&status=Stock&item_cat=0";
		HttpUtil.doGet(start);
		Document doc = Jsoup.parse(HttpUtil.getEntitys());
		String aaa = doc.select("em.text-aqua").text();
		System.out.println(aaa);

		int a = getPageNumber(aaa);
		List<String> list = new ArrayList<String>();

		for (int i = 1; i <= a; i++) {
			String target = "http://view.trc.com/shop/item/itemList.html?pages=" + i + "&status=Stock&item_cat=0";
			HttpUtil.doGet(start);
			Document docs = Jsoup.parse(HttpUtil.getEntitys());
			Elements ids = doc.select("[name=item_ids]");

			for (Element e : ids) {
				list.add(e.attr("value"));
			}

		}

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		return list;

	}

	public static int getPageNumber(String s) {
		if (StringUtil.isEmpty(s)) {
			return 0;
		}

		int a = Integer.parseInt(s);
		if (1 <= a && a <= 10) {
			return 1;
		}
		if (0 == a % 10) {
			return a / 10;
		} else {
			return a / 10 + 1;
		}
	}
	

	public  static String  发布直降活动(String huanjing,String username,String password,String[] s,int time1,int time2){
		login(huanjing, username, password);
		Map<String,String> map1=new HashMap<>();
		map1.put("textcol","title,price");
		
		for (int i = 0; i < s.length; i++) {
			map1.put("item_id["+i+"]",s[i]);
		}
		
//		map1.put("item_id[0]","21059");
//		map1.put("item_id[2]","20999");
		map1.put("promotion_price","");
		HttpUtil.doPost(huanjing+shop.拉取直降商品, map1);
		String docss=HttpUtil.getEntitys();
		HttpUtil.printEntity();
		Document doc=Jsoup.parse(docss);
		Elements ss=doc.select("input");
		Map<String,String> map2=new HashMap<>();
		map2.put("promotion_id", "");
		int a=0;
		for (Element e:ss) {
			System.out.println("key:"+e.attr("name"));
			System.out.println("valye:"+e.attr("value"));
//			判断是不是价格
			if(e.attr("name").contains("price")){
				map2.put(e.attr("name"), "1");
				System.out.println("发现价格");
			}
			if(e.attr("name").contains("item_id")){
				map2.put(e.attr("name"), e.attr("value"));
			}
			if(e.attr("name").contains("skuids[]")){
				map2.put("skuids["+a+"]", e.attr("value"));
				a++;
			}
			if(e.attr("name").contains("valid_status_")){
				map2.put(e.attr("name"), e.attr("value"));
			}
			
		}
//		"2017/04/25 00:00-2017/04/25 23:59"
		map2.put("activity_time", DateStringFactory.shop一段时间(time1, time2));
//		for(String key : map2.keySet()){
//			System.out.println("key:"+key);
//			System.out.println("value:"+map2.get(key));
//		}
		
		HttpUtil.doPost(huanjing+shop.发布直降, map2);
		HttpUtil.printEntity();
		return HttpUtil.getEntitys();
		}
	
	public  static String  发布满减活动(String huanjing,String username,String password,String[] s,int time1,int time2,String man,String jian){
		login(huanjing, username, password);
		Map<String,String> map1=new HashMap<>();
		map1.put("textcol","title,price");
		
		for (int i = 0; i < s.length; i++) {
			map1.put("item_id["+i+"]",s[i]);
		}
		HttpUtil.doPost(huanjing+shop.拉取满减商品, map1);
		String docss=HttpUtil.getEntitys();
		HttpUtil.printEntity();
		Document doc=Jsoup.parse(docss);
		Elements ss=doc.select("input");
		Map<String,String> map2=new TreeMap<>();
		List<String> sss =new ArrayList();
		map2.put("promotion_id", "");
		map2.put("promotion_name", "满减"+DateStringFactory.getAfterMinnite年月日(1));
		map2.put("limit_money[]", "100");
		map2.put("deduct_money[]", "35");
//		map2.put("limit_money[2]", "200");
//		map2.put("deduct_money[2]", "35");
		map2.put("no_capped", "1");
		
		int itemscount=0;
		int skuscount=0;
		
		
		for (Element e:ss) {

//			判断是不是价格
			if(e.attr("name").contains("skuids[]")){
				
				map2.put("skuids["+skuscount+"]", e.attr("value"));
				skuscount++;
			}
			if(e.attr("name").contains("valid_status")){
				
				map2.put(e.attr("name"), e.attr("value"));
			}
			if(e.attr("name").contains("item_ids[]")){
				
				map2.put("item_ids["+itemscount+"]", e.attr("value"));
				itemscount++;
			}
			
		}
//		"2017/04/25 00:00-2017/04/25 23:59"
		map2.put("activity_time", DateStringFactory.shop一段时间(time1, time2));
		for(String key : map2.keySet()){
			System.out.println("key:"+key);
			System.out.println("value:"+map2.get(key));
		}
		
		HttpUtil.doPost(huanjing+shop.发布满减商品, map2);
		HttpUtil.printEntity();
		return HttpUtil.getEntitys();
		}
	
	public  static String  发布满折活动(String huanjing,String username,String password,String[] s,int time1,int time2,String man,String zhe){
		login(huanjing, username, password);
		Map<String,String> map1=new HashMap<>();
		map1.put("textcol","title,price");
		
		for (int i = 0; i < s.length; i++) {
			map1.put("item_id["+i+"]",s[i]);
		}
		HttpUtil.doPost(huanjing+shop.拉取满折商品, map1);
		String docss=HttpUtil.getEntitys();
		HttpUtil.printEntity();
		Document doc=Jsoup.parse(docss);
		Elements ss=doc.select("input");
		Map<String,String> map2=new TreeMap<>();
		map2.put("promotion_id", "");
		map2.put("promotion_name", "满折"+DateStringFactory.getAfterMinnite年月日(1));
		map2.put("full[]", man);
		map2.put("discount[]", zhe);

		map2.put("no_capped", "1");
		
		int itemscount=0;
		int skuscount=0;
		
		
		for (Element e:ss) {

//			判断是不是价格
			if(e.attr("name").contains("skuids[]")){
				
				map2.put("skuids["+skuscount+"]", e.attr("value"));
				skuscount++;
			}
			if(e.attr("name").contains("valid_status")){
				
				map2.put(e.attr("name"), e.attr("value"));
			}
			if(e.attr("name").contains("item_ids[]")){
				
				map2.put("item_ids["+itemscount+"]", e.attr("value"));
				itemscount++;
			}
			
		}
//		"2017/04/25 00:00-2017/04/25 23:59"
		map2.put("activity_time", DateStringFactory.shop一段时间(time1, time2));
		for(String key : map2.keySet()){
			System.out.println("key:"+key);
			System.out.println("value:"+map2.get(key));
		}
		
		HttpUtil.doPost(huanjing+shop.发布满折商品, map2);
		HttpUtil.printEntity();
		return HttpUtil.getEntitys();
		}
	
	public  static String  发布拼团活动(String huanjing ,String username,String password,String 人数,String 团价,String 限制,String 商品ID,int time1,int time2){
		login(huanjing, username, password);
		Map<String,String> map2=new TreeMap<>();
		map2.put("activity_time", DateStringFactory.shop一段时间(time1, time2));
		map2.put("group_persons", 人数);
		map2.put("group_price", 团价);
		map2.put("user_buy_limit", 限制);
		map2.put("item_id[]", 商品ID);
		map2.put("error_message","");
		map2.put("is_auto","1");
		map2.put("automatic_group_time[1]","3");
		map2.put("automatic_group_time[2]","20");
		HttpUtil.doPost(huanjing+shop.发布拼团, map2);
		HttpUtil.printEntity();
		return HttpUtil.getEntitys();
		}
	
	public static String[] 获取指定名称商品(String huanjing,String name,String status){
		
		
		return null;
		
	}
	
	public  static String[]  获取前几个数组(int a,String tager){	
		Document doc=Jsoup.parse(tager);
		Elements ss=doc.select("input[name=item_ids]");
		String[] aa=new String[10];
		int as=0;
		for (Element e:ss) {
//			System.out.println(e.attr("value"));
//				System.out.println(e.attr("value"));
				aa[as]=e.attr("value");
				as++;
			}
//		System.out.println("%%%%%%%%%");
		if(as>a){
			return Arrays.copyOfRange(aa, 0, a);
		}else{
			return Arrays.copyOfRange(aa, 0,as);
		}
}

/**
 * 
 * @param huanjing
 * @param username
 * @param password
 * @param type  tosale上架  tostock 下架
 * @param s
 * @return
 */
	public static String 上下架指定商品(String huanjing,String username,String password,String type ,String s[]){
		login(huanjing, username, password);
		String api=huanjing+shop.上架商品;
		Map<String,String> my=new HashMap<>();
		my.put("type", type);
		for (int i = 0; i < s.length; i++) {
			my.put("item_ids["+i+"]", s[i]);
		}
		HttpUtil.doPost(api, my);
		return HttpUtil.getEntitys();
		
	}
	
	public static String 获取前几个商品(String huanjing,String username,String password,int n,String Status,String itemname){
		login(huanjing, username, password);
		String result="";
		String sousuo="";
		String[] s;
		if("默认名称".equals(itemname)){
			
			switch (Status) {
			case "1":
//				默认是全部商品
				sousuo=huanjing+"shop/item/itemList.html";
				break;
			case "2":
//				默认是上架中商品
				sousuo=huanjing+"shop/item/itemList.html?status=Shelves";
				break;
			case "3":
//				默认是下架商品
				sousuo=huanjing+"shop/item/itemList.html?status=Stock";
				break;
			default:
				break;
			}
			HttpUtil.doGet(sousuo);
			
			s=获取前几个数组(n, HttpUtil.getEntitys());
			for (int i = 0; i < s.length; i++) {
				if(i==(s.length-1)){
						result+=s[i];	
				}else{
					
					result+=(s[i]+",");
				}
			}
			return result;
		}else{
			sousuo=huanjing+shop.搜索商品地址;
			Map<String,String> map1=new HashMap<String,String>();
			map1.put("dlytmpl_id","-1");
			map1.put("item_cat","-1");
			map1.put("item_no","");
			map1.put("item_title",itemname);
			map1.put("max_price","");
			map1.put("min_price","");						
			switch (Status) {
			case "1":
//				默认是全部商品
				HttpUtil.doPost(sousuo, map1);
				s=获取前几个数组(n, HttpUtil.getEntitys());
				for (int i = 0; i < s.length; i++) {
					if(i==(s.length-1)){
						
							result+=s[i];
					
					}else{
						
						result+=(s[i]+",");
					}
				}
				
				break;
			case "2":
//				默认是上架中商品
				sousuo=huanjing+"shop/item/search.html?min_price=&max_price=&item_title="+itemname+"&item_cat=-1&item_no=&dlytmpl_id=-1&status=Shelves";
				HttpUtil.doGet(sousuo);
//				HttpUtil.doPost(sousuo, map1);
				s=获取前几个数组(n, HttpUtil.getEntitys());
				for (int i = 0; i < s.length; i++) {
					if(i==(s.length-1)){
							result+=s[i];
					
					}else{
						result+=(s[i]+",");
					}
				}
				break;
			case "3":
//				默认是下架商品
				sousuo=huanjing+"shop/item/search.html?min_price=&max_price=&item_title="+itemname+"&item_cat=-1&item_no=&dlytmpl_id=-1&status=Stock";
				HttpUtil.doGet(sousuo);
//				HttpUtil.doPost(sousuo, map1);
				s=获取前几个数组(n, HttpUtil.getEntitys());
				for (int i = 0; i < s.length; i++) {
					if(i==(s.length-1)){
						result+=s[i];
					}else{
						result+=(s[i]+",");
					}
				}
				break;
			default:
				break;
			}
			
			return result;
		}
		
		
		
	}
	/**
	 * 
	 * @param huanjing
	 * @param username
	 * @param password
	 * @param price1
	 * @param price2
	 * @param items
	 * @return
	 */
	public static String 报名特卖活动(String huanjing,String username,String password,String price1,String store,String[] items){
		String no=ManagePlatform.getFirstTemaiID(huanjing);
		if("未找到特卖活动".equals(no)){
			return no;
		}
		
		logger.info("要报名的特卖活动的编号是："+no);
		login(huanjing, username, password);
		String sss=huanjing+shop.获取特卖商品;
		Map<String,String> map0=new HashMap<>();
		map0.put("discount_max","99");
		map0.put("discount_min","1");
		map0.put("promotion_price","");
		map0.put("promotion_store","");
		map0.put("textcol","title,price");
		for (int i = 0; i < items.length; i++) {
			map0.put("item_id["+i+"]", items[i]);
		}
		HttpUtil.doPost(sss, map0);
		String docs=HttpUtil.getEntitys();
		Document doc=Jsoup.parse(docs);
		Elements ss=doc.select("input");	
		String baoming=huanjing+shop.报名特卖活动;
		Map<String,String> map2=new HashMap<>();
		map2.put("activity_id", no);
		int a=0;
		int b=0;
		for (Element e:ss) {
			System.out.println("key:"+e.attr("name"));
			System.out.println("valye:"+e.attr("value"));
//			判断是不是价格
			if(e.attr("id").contains("price")){
				map2.put(e.attr("id"), price1);
			}
			if(e.attr("name").contains("item_id_")){
				map2.put(e.attr("name"), e.attr("value"));
			}
			if(e.attr("name").contains("skuids[]")){
				map2.put("skuids["+a+"]", e.attr("value"));
				a++;
			}
			if(e.attr("name").contains("item_ids[]")){
				map2.put("item_ids["+b+"]", e.attr("value"));
				b++;
			}
			
			if(e.attr("id").contains("store")){
				map2.put(e.attr("id"), store);
			}
			
			
		}
		HttpUtil.doPost(baoming, map2);
		String aa=HttpUtil.getEntitys();
		
		return aa;
	}
	
	
	
	public static String 报名平台优惠券(String huanjing,String username,String password,String[] items){
		String no=ManagePlatform.get第一个优惠券报名的ID(huanjing);
		logger.info("要报名的特卖活动的编号是："+no);
		login(huanjing, username, password);
		String baoming=huanjing+shop.报名平台优惠券;
		Map<String,String> map2=new HashMap<>();
		map2.put("coupon_id", no);
		for (int i = 0; i < items.length; i++) {
			map2.put("item_id["+i+"]", items[i]);
		}
		HttpUtil.doPost(baoming, map2);
		String aa=HttpUtil.getEntitys();
		return aa;
	}
	
	public static String 报名礼包(String huanjing,String username,String password,String[] couponIds){
		String no=ManagePlatform.get第一个礼包报名的ID(huanjing);
		logger.info("要报名的特卖活动的编号是："+no);
		login(huanjing, username, password);
		String sss=huanjing+shop.报名优惠券礼包;
		Map<String,String> map0=new HashMap<>();
		map0.put("package_id",no);
		map0.put("register_id","");
		for (int i = 0; i < couponIds.length; i++) {
			map0.put("couponIds["+i+"]",couponIds[i]);
		}
		HttpUtil.doPost(sss, map0);
		String aa=HttpUtil.getEntitys();
		return aa;
	}	
	
	public static String 发货(String orderid){
		
//		String result=UserBasic.支付订单("18200000000", "143");
//		JSONObject order=JSONObject.fromObject(result);
//		String myid=order.getString("orderid");
//		String myid="1705240719401808625";		
		String myid=根据平台级订单查询店铺级订单编号(orderid);	
//		登录VIEW();		
		if("empty".equals(myid)){
			JSONObject result1=new JSONObject();
			result1.put("success", false);
			result1.put("店铺订单", myid);
			result1.put("message", "查询到的店铺级订单结果为空！");
			logger.info("查询的店铺级订单为空！");
			return result1.toString();
		}
		Map<String,String> map6=new HashMap<>();
		map2.put("Referer","http://view.trc.com/shop/list.html");
		
		HttpUtil.doGet(tools.EnvironmentUtil.接口.shop.拉取包裹页面+myid,map6,map2);
		map2.put("Referer",tools.EnvironmentUtil.接口.shop.拉取包裹页面+myid);
		HttpUtil.doGet(tools.EnvironmentUtil.接口.shop.初始化包裹+myid,map6,map2);
		String temp2=HttpUtil.getEntitys();
		logger.info(temp2);
		HttpUtil.doGet(tools.EnvironmentUtil.接口.shop.拉取发货包裹信息+myid,map6,map2);
		String temp1=HttpUtil.getEntitys();
		logger.info(temp1);
		JSONObject order=JSONObject.fromObject(temp1);
		JSONArray aaa=order.getJSONArray("data");
		JSONObject data=aaa.getJSONObject(0);
		JSONObject packge=new JSONObject();
		JSONObject delivery=new JSONObject();
		packge.put("sku_id", data.getString("sku_id"));
		packge.put("id", data.getString("id"));
		packge.put("number", data.getString("num"));
		delivery.put("tid",  data.getString("shop_order_id"));
		delivery.put("logi_in", "3612003ss");
		delivery.put("corp_code", "YTO");
		
		Map<String,String> map8=new HashMap<>();
		map8.put("package","["+packge.toString()+"]");
		map8.put("inner_delivery",delivery.toString());
		
		HttpUtil.doPost(tools.EnvironmentUtil.接口.shop.发货, map8);
		String ssss=HttpUtil.getEntitys();
		JSONObject result1=new JSONObject();
		result1.put("manageid", orderid);
		result1.put("shopid", myid);
		result1.put("result", ssss);
		logger.info(ssss);
		return ssss;
		
	}
	
	public static String 根据平台级订单查询店铺级订单编号(String id){
		登录VIEW();
//		String test="1705240711452728625";
		Map<String,String> map7=new HashMap<>();
		map7.put("constOrderType","");
		map7.put("group_status","");
		map7.put("tid","");
		map7.put("user_name","");
		map7.put("receiver_name","");
		map7.put("pid", id);
		map7.put("status","2");
		map7.put("pay_time",DateStringFactory.获取2017第一天到今天());
		map7.put("create_time",DateStringFactory.获取2017第一天到今天());
		HttpUtil.doPost(tools.EnvironmentUtil.接口.shop.查询店铺订单编号, map7);
		Document doc=Jsoup.parse(HttpUtil.getEntitys());
//		logger.info(HttpUtil.getEntitys());
		Elements ss=doc.select("span:contains(店铺订单号)");	
		if(0==ss.size()){
			return "empty";
		}
		String result=ss.get(0).text();
		logger.info(result);
		String shoporderid=result.substring(6, result.length());
		logger.info(shoporderid);
		return shoporderid;
		
	}
	
	
	public static void main(String[] args) {
//		上架所有未上架的商品();
//		AddNoSku("http://view.trc.com/","11", "22", "33", "你皮任你皮", "sss"); 
//	String[] aa={"21068","21067","21069"};
//	发布满减活动(aa);
//	发布默认的多SKU商品();
//		String[] aaa={"23","24"};
//		发布满折活动(aaa);
//		for (int i = 0; i < 100; i++) {
//			System.out.println("第"+i+"个");
//			AddNoSku("http://pre.trc.com/", "hufen", "admin123", "100", "石乐志"+i, "1000", "Domestic");
//		}
		
		/****
		 * 
		 */
		int a=1;
		for (int i = 28305; i < 28405; i++) {
			
			System.out.println("第"+i+"个"+"ssssss");
			a++;
			发布拼团活动("http://pre.trc.com/", "hufen", "admin123", "2", "2", "2", i+"", 15, 9000000);
		}
//		for(String s:获取前几个数组(10)){
//			System.out.println(s);
//		}
//		String s=获取前几个商品("http://view.trc.com/", "hulijuan", "admin123", 10, "1", "默认名称");
//		System.out.println(s);
//		String aa="22991,22990,22989";
//		String[] sa=aa.split(",");
//		String ss=报名特卖活动("http://view.trc.com/", "hulijuan", "admin123", "5", "1", sa);
//		System.out.println(ss);
//		String aa="22991,22990,22989";
//		String[] sa=aa.split(",");
//		String ss=报名平台优惠券("http://view.trc.com/", "hulijuan",  "admin123", sa);
//		System.out.println(ss);		
//		String aa="1244,1245,1246";
//		String[] sa=aa.split(",");
//		String ss=报名礼包("http://view.trc.com/", "hulijuan",  "admin123", sa);
//		System.out.println(ss);
//		发货("1705240715561948625");
//		根据平台级订单查询店铺级订单编号("");
	}

}
