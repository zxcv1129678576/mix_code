package tools.Interface.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.org.apache.regexp.internal.recompile;
import com.trc.util.StringUtil;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import tools.util.HttpUtil;

public class WxUserGroup {
	
	public  static  Logger logger = Logger.getLogger( WxUserGroup.class);
	
	
	static Map<String,String> map2=new HashMap<>();
	static{
		map2.put("Connection","keep-alive");
		map2.put("Accept","*/*");
		map2.put("Origin","http://view.trc.com");
		map2.put("X-Requested-With","XMLHttpRequest");
		map2.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
		map2.put("Referer","http://view.trc.com/member-idcard.html");
		map2.put("Accept-Encoding","gzip, deflate");
		map2.put("Accept-Language","zh-CN,zh;q=0.8");
	}
	
	
	public static boolean check401(String temp){
		if(StringUtil.isEmpty(temp)){
			return false;
		}
		if(temp.contains("biz_code")){
			return false;
		}
		
		return true;
		
	}
	
	public static String 拼团并支付(String huanjing,String username,String itemid){
		
		UserBasic.登录获取cookie(username);
		//s
		String sku;
		if(check401(UserBasic.GetItemSKU(huanjing,itemid))){
			JSONObject skus=JSONObject.fromObject(UserBasic.GetItemSKU(huanjing,itemid));
			sku=skus.getJSONObject("_").getString("sku_id");
		}else{
			sku="nulllllllllllllll";
			logger.info("异常中断！");
			return "异常";
		}
//修改		
//		JSONObject skus=JSONObject.fromObject(UserBasic.GetItemSKU(huanjing,itemid));
//		String sku=skus.getJSONObject("_").getString("sku_id");
		
		logger.info(sku);
		//获取SKU
		//	String sku=UserBasic.GetItemSKU(itemid);
		String wx_add_cart="http://wx.trc.com/wxapi/cart-add.html";
		Map<String,String> map0=new HashMap<>();
		map0.put("item[item_id]",itemid);
		map0.put("item[quantity]","1");
		map0.put("item[sku_id]",sku);
		map0.put("mode","fast_buy");
		map0.put("obj_type","groupbuy");
	
		
		Map<String,String> map11=new HashMap<>();
		map11.put("Content-Type","application/x-www-form-urlencoded");
		map11.put("X-Requested-With","XMLHttpRequest");
		map11.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
		
		HttpUtil.doPost(wx_add_cart, map0,map11);
		if(check401(HttpUtil.getEntitys())){
			
		}else{
			logger.info("异常中断！");
			return "异常";
		}
		
		logger.info(HttpUtil.getEntitys());
		//获取购买信息
		String wx_buy="http://wx.trc.com/wxapi/trade-init.html?mode=fast_buy";
		HttpUtil.doGet(wx_buy);
		logger.info(HttpUtil.getEntitys());
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String md5;
		String addr_id;
		if(check401(HttpUtil.getEntitys())){
			JSONObject deatils=JSONObject.fromObject(HttpUtil.getEntitys());
			 md5=deatils.getJSONObject("data").getJSONObject("order").getString("md5CartInfo");
			 addr_id=deatils.getJSONObject("data").getJSONArray("addrList").getJSONObject(0).getString("addr_id");
		}else{
			md5="";
			addr_id="";
			logger.info("异常中断！");
			return "异常中断";			
		}
		
		
		//创建订单
//		JSONObject deatils=JSONObject.fromObject(HttpUtil.getEntitys());
//		String md5=deatils.getJSONObject("data").getJSONObject("order").getString("md5CartInfo");
//		String addr_id=deatils.getJSONObject("data").getJSONArray("addrList").getJSONObject(0).getString("addr_id");

		String create_order="http://wx.trc.com/wxapi/trade-order-create.html";
		JSONObject order_info=new JSONObject();
		order_info.put("action", "1");
		order_info.put("addr_id", addr_id);
		order_info.put("buyer_messsage", new JSONObject());
		order_info.put("card_id", "");
		order_info.put("invoice_name", "个人");
		order_info.put("invoice_type", "NORMAL");
		order_info.put("md5_cart_info", md5);
		order_info.put("mode", "fast_buy");
		order_info.put("need_invoice", "0");
		HttpUtil.doPostJSON(create_order, order_info);
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(HttpUtil.getEntitys());
		String order_id;
		
		if(check401(HttpUtil.getEntitys())){
			 order_id=JSONObject.fromObject(HttpUtil.getEntitys()).getString("id");

		}else{
			logger.info("异常中断！");
			return "异常中断";		
		}
		
		//String order_id=JSONObject.fromObject(HttpUtil.getEntitys()).getString("id");
		logger.info(order_id);
		JSONObject orderid=new JSONObject();
		orderid.put("order_id", order_id);
		String get_pay_op="http://wx.trc.com/wxapi/payment/getPayOptions";
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpUtil.doPostJSON(get_pay_op, orderid);
		String pay_id;
		logger.info(HttpUtil.getEntitys());
		if(check401(HttpUtil.getEntitys())){
			if(JSONObject.fromObject(HttpUtil.getEntitys()).containsKey("msg")){
				 pay_id=JSONObject.fromObject(HttpUtil.getEntitys()).getJSONObject("msg").getString("payId");

			}else{
				logger.info("缺少msg异常中断！");
				return "异常中断";		
			}
		}else{
			logger.info("异常中断！");
			return "异常中断";		
		}
		
//		String pay_id=JSONObject.fromObject(HttpUtil.getEntitys()).getJSONObject("msg").getString("payId");
		logger.info(pay_id);
		//String getinfo="http://pay.trc.com/api/v3/funds//bill/transfer/consume/detail/DD2017081510115639215550?payId=DD2017081510115639215550";
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String getPay="http://pay.trc.com/api/v3/funds//bill/transfer/consume/detail/"+pay_id+"?payId="+pay_id;
		HttpUtil.doGet(getPay);
		logger.info(HttpUtil.getEntitys());
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String qiguai="http://pay.trc.com/api/v3/funds//bill/freeze/amount/list";
		HttpUtil.doGet(qiguai);
		logger.info(HttpUtil.getEntitys());
		
		
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sss="http://pay.trc.com/api/v3/funds//account/have";
		HttpUtil.doGet(sss);
		logger.info(HttpUtil.getEntitys());
		
		
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pay="http://pay.trc.com/api/v3/funds//pay/ecard";
		Map<String,String> map1=new HashMap<>();
		map1.put("payId",pay_id);
		map1.put("amount","0.02");
		map1.put("payPassword","admin123");
	//	map0.put("cardList","[{\"ecardType\":\"ECARD_ACTIVITY\",\"subAmount\":0.02}]");
		
		Map<String,String> map12=new HashMap<>();
		map12.put("Content-Type","application/x-www-form-urlencoded");
		map12.put("X-Requested-With","XMLHttpRequest");
		map12.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
		map12.put("Accept", "application/json, text/plain, */*");
		
		
		JSONObject AA=new JSONObject();
		AA.put("ecardType", "ECARD_ACTIVITY");
		AA.put("subAmount", 0.02);
		map1.put("cardList","["+AA.toString()+"]");
		
		HttpUtil.doPost(pay, map1);
		logger.info(HttpUtil.getEntitys());
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String callback="http://wx.trc.com/wxapi/payment/query?payment_id="+pay_id;
		HttpUtil.doGet(callback);
		logger.info(HttpUtil.getEntitys());
		
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String getObject_id="http://wx.trc.com/wxapi/trade-query-object.html?order_id="+order_id;
		HttpUtil.doGet(getObject_id);
		logger.info(HttpUtil.getEntitys());
		
		return itemid;
		
	}
	
	public static void test(int start,int end){
		
		for (int i = start; i <= end; i++) {
			
			try {
				logger.info("Test当前是第"+i+"次");
				拼团并支付("http://view.trc.com/", "18758017446", ""+i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
	
		for (int j = 0; j <1; j++) {
			logger.info("当前是第"+j+"次");
			test(30241,30245);
	}
		
	//	test(28913,28914);
	}
}
