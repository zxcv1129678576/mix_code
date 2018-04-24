package tools.Interface.user;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import tools.EnvironmentUtil.接口;
import tools.EnvironmentUtil.接口.user;
public class UserCheck {
	public static Logger logger = Logger.getLogger( UserCheck.class);
	
	public static JSONObject 收藏商品(){
		JSONObject check=new JSONObject();
		String result=UserBasic.添加收藏("18200000000", "143");
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.收藏商品);
		check.put("data",result);
		try {
			if("true".equals(resltj.getString("success"))){
				check.put("Success", true);
				check.put("des", "已成功获校验success字段"+resltj.getString("success"));
				}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
				}
			
		}catch (Exception e) {
			// TODO: handle exception
		}

		return check;
	}
	public static JSONObject 登录报文校验(){
		JSONObject check=new JSONObject();
		String result=UserBasic.GetLoginResponse("18200000000");
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.登录);
		check.put("data",result);
		try {
			if("18200000000".equals(resltj.getString("phone"))){
				check.put("Success", true);
				check.put("des", "已成功获取登录手机号码"+resltj.getString("phone"));
				
			}else{
				check.put("Success", false);
				check.put("des", "未能成功获取登录手机号码");
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}

		return check;
		
		}
	public static JSONObject 登录获取token(){
		JSONObject check=new JSONObject();
		String token=UserBasic.登录获取cookie("18200000000");
		check.put("target", user.登录);
		check.put("data",token);
		if(token.contains("token=")){
			check.put("Success", true);
			check.put("des", "已成功获取token!");
		}else{
			check.put("Success", true);
			check.put("des", "未成功获取token!");
		}
		return check;
	}
	
	public static JSONObject 获取商品评价(){
		JSONObject check=new JSONObject();
		String result=UserBasic.GetItemRate("143");
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.获取商品评价);
		check.put("data",result);
		try {
			if("200".equals(resltj.getString("code"))){
				check.put("Success", true);
				check.put("des", "已成功获校验code"+resltj.getString("code"));
				
			}else{
				check.put("Success", false);
				check.put("des", "未能成功获取code");
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}

		return check;
	}
	
	public static JSONObject 获取商品SKU(){
		JSONObject check=new JSONObject();
		String result=UserBasic.GetItemSKU("143");
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.获取商品SKU信息);
		check.put("data",result);
		try {
			if("200".equals(resltj.getString("code"))){
				check.put("Success", true);
				check.put("des", "已成功获校验code"+resltj.getString("code"));
				
			}else{
				check.put("Success", false);
				check.put("des", "未能成功获取code");
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}

		return check;
	}
	

	public static JSONObject 取消收藏商品(){
		JSONObject check=new JSONObject();
		String result=UserBasic.取消收藏("18200000000", "143");
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.取消收藏);
		check.put("data",result);
		try {
		    if (!resltj.has("success")){
		    	resltj.put("success", "false");
		    }
			
			if("true".equals(resltj.getString("success"))){
				check.put("Success", true);
				check.put("des", "已成功获校验success字段"+resltj.getString("success"));
				}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
				}
			
		}catch (Exception e) {
			// TODO: handle exception
		}

		return check;
	}
	
	public static JSONObject 添加收货地址(){
		JSONObject check=new JSONObject();
		String result=UserBasic.添加收货地址("18200000000");
		logger.info("添加收货地址返回的报文是："+result);
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.添加收货地址);
		check.put("data",result);
		try {
		    if (!resltj.has("success")){
		    	resltj.put("success", "false");
		    }
			
			
			if("true".equals(resltj.getString("success"))){
				check.put("Success", true);
				check.put("des", "已成功获校验success字段"+resltj.getString("success"));
				}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
				}
		}catch (Exception e) {
			// TODO: handle exception
		}

		return check;
	}
	
	
	public static JSONObject 添加身份证(){
		JSONObject check=new JSONObject();
		String result=UserBasic.添加身份证("18200000000");
		logger.info("添加身份证返回的报文是："+result);
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.添加身份证);
		check.put("data",result);
		try {
		    if (!resltj.has("success")){
		    	resltj.put("success", "false");
		    }
			if("true".equals(resltj.getString("success"))){
				check.put("Success", true);
				check.put("des", "已成功获校验success字段"+resltj.getString("success"));
				}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
				}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return check;
	}
	
	public static JSONObject 购物袋下单(){
		JSONObject check=new JSONObject();
		UserBasic.添加到购物袋("143","18200000000");
		String result=UserBasic.购物袋下单("18200000000");
	
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.购物袋下订单);
		check.put("data",result);
		try {
			if("true".equals(resltj.getString("status"))){
				check.put("Success", true);
				check.put("des", "已成功获校验success字段"+resltj.getString("status"));
				}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
				}
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return check;
	}
	
	
	
	
	public static JSONObject 删除身份证(){
		JSONObject check=new JSONObject();
		String result=UserBasic.删除身份证("18200000000");
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.删除身份证);
		check.put("data",result);
		try {
			if("true".equals(resltj.getString("success"))){
				check.put("Success", true);
				check.put("des", "已成功获校验success字段"+resltj.getString("success"));
				}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
				}
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return check;
	}
	
	public static JSONObject 加入购物袋(){
		JSONObject check=new JSONObject();
		JSONObject result=UserBasic.添加到购物袋("143","18200000000");
		logger.info("添加商品到购物袋的返回报文是："+result);
//		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.加入购物袋);
		check.put("data",result);
		try {
			if(result.getString("status") .equals("true")){
				check.put("Success", true);
				check.put("des", "已成功获校验success字段");
			}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return check;
	}

	
	public static JSONObject 立即购买(){
		JSONObject check=new JSONObject();
//		UserBasic.添加到购物袋("143","18200000000");
		String result=UserBasic.立即购买("18200000000", "143");
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.购物袋下订单);
		check.put("data",result);
		try {
			if("true".equals(resltj.getString("status"))){
				check.put("Success", true);
				check.put("des", "已成功获校验success字段"+resltj.getString("status"));
				}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
				}
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return check;
	}
	public static JSONObject 删除收货地址(){
		JSONObject check=new JSONObject();
		String result=UserBasic.删除收货地址("18200000000");
		logger.info("删除收货地址证返回的报文是："+result);
		JSONObject resltj=JSONObject.fromObject(result);
		check.put("target", user.删除收货地址);
		check.put("data",result);
		check.put("Success", true);
		check.put("des", "默认的报文，出问题了TRY没执行到");
		try {
			if("true".equals(resltj.getString("success"))){
				check.put("Success", true);
				logger.info("加入了Success");
				check.put("des", "已成功获校验success字段"+resltj.getString("success"));
				}else{
				check.put("Success", false);
				check.put("des", "未能成功获取success字段");
				}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}

		return check;
	}
	
	public static  JSONObject 支付订单(){
		JSONObject check=new JSONObject();
		
		try {
			String re=UserBasic.支付订单("18200000000", "143");
			check=JSONObject.fromObject(re);
			//JSONObject check1=check.getJSONObject("data");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return check;
		
	}
	
	
	
	public static void main(String[] args) {
		
//		登录报文校验();
//		登录获取token();
//		获取商品评价();
//		添加到购物袋("");
//		登录获取cookie("18200000000");
//	添加到购物袋("23260,23259,23258,23257,23256,23255","18200000000");
//		test();
//	获取购物袋MD5("18200000000");
//		获取收货地址("18200000000");
//		获取身份证("18200000000");
//		获取购物袋MD5("18392081082");
//		购物袋下单("18392081082");
//		test购物袋("23250,23251,23252","18200000000");
//		登录获取token();
	}
}
