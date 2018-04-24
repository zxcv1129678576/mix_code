/**
 * 
 */package com.trc.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.trc.util.StringUtil;

import net.sf.json.JSONObject;
import tools.Interface.manage.ManagePlatform;
import tools.Interface.shop.ShopOperate;

/** 
*@author  作者 : 陈达
*@date 创建时间：2017年3月17日 上午10:06:33
*@version 1.0 
*@parameter  
*@return 
*/

@Controller
public class Trctools {
	@RequestMapping(value="/mytools",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String temai(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			@RequestParam(value = "time1", required = false) String time1,
			@RequestParam(value = "time2", required = false) String time2,
			@RequestParam(value = "time3", required = false) String time3,
			@RequestParam(value = "time4", required = false) String time4,
			HttpServletResponse response){
		int mytime1=Integer.parseInt(time1);
		int mytime2=Integer.parseInt(time2);
		int mytime3=Integer.parseInt(time3);
		int mytime4=Integer.parseInt(time4);
		
		String res="";
		try {
			res=ManagePlatform.addTemai(huanjing,mytime1, mytime2, mytime3, mytime4);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject result1=new JSONObject();
		if("".equals(res)){
			result1.put("success", false);
		}else{
			result1.put("success", true);
			result1.put("des", res);
		}
		
		return result1.toString();
	}
	
	@RequestMapping(value="/addplatformcoupon",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String addcoupon(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "man", required = false) String man,
			@RequestParam(value = "jian", required = false) String jian,
			@RequestParam(value = "time1", required = false) String time1,
			@RequestParam(value = "time2", required = false) String time2,
			@RequestParam(value = "time3", required = false) String time3,
			@RequestParam(value = "time4", required = false) String time4,
			@RequestParam(value = "time5", required = false) String time5,
			HttpServletResponse response){
		
		int mytime1=Integer.parseInt(time1);
		int mytime2=Integer.parseInt(time2);
		int mytime3=Integer.parseInt(time3);
		int mytime4=Integer.parseInt(time4);
		int mytime5=Integer.parseInt(time5);
		String res="";
		try {
			res=ManagePlatform.PlatformCoupon(huanjing,name, man, jian, mytime1, mytime2, mytime3, mytime4, mytime5);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject result1=new JSONObject();
		if("".equals(res)){
			result1.put("success", false);
		}else{
			result1.put("success", true);
			result1.put("des", res);
		}
		
		return result1.toString();
	}
	
	@RequestMapping(value="/addplatform",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String addcouponAll(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "discount", required = false) String discount,
			@RequestParam(value = "wait", required = false) String wait,
			@RequestParam(value = "ling", required = false) String ling,
			@RequestParam(value = "youxiao", required = false) String youxiao,
			HttpServletResponse response){
		return ManagePlatform.addPlatCouponAll(huanjing, name, limit, discount, wait, ling, youxiao);
	}
	
	@RequestMapping(value="/additem",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String additem(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			@RequestParam(value = "time1", required = false) String time1,
			@RequestParam(value = "time2", required = false) String time2,
			@RequestParam(value = "time3", required = false) String time3,
			@RequestParam(value = "time4", required = false) String time4,
			@RequestParam(value = "maoyi", required = false) String maoyi,
			@RequestParam(value = "store", required = false) String store,
			HttpServletResponse response){
		
		String res="";
		try {
			res=ShopOperate.AddNoSku(huanjing,time1, time2, time4, time3, store,maoyi);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject result1=new JSONObject();
		if("".equals(res)){
			result1.put("success", false);
		}else{
			result1.put("success", true);
			result1.put("des", res);
		}
		
		return result1.toString();
	}

	
	
	
	@RequestMapping(value="/addskuitem",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String addskuitem(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			@RequestParam(value = "time1", required = false) String time1,
			@RequestParam(value = "time2", required = false) String time2,
			@RequestParam(value = "time3", required = false) String time3,
			@RequestParam(value = "maoyi", required = false) String maoyi,
			HttpServletResponse response){
		
		String res="";
		try {
			res=ShopOperate.AddSku(huanjing,time1, time2, time3, maoyi);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject result1=new JSONObject();
		if("".equals(res)){
			result1.put("success", false);
		}else{
			result1.put("success", true);
			result1.put("des", res);
		}
		
		return result1.toString();
	}
	
	
	
	@RequestMapping(value="/addgift",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String addgift(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "time1", required = false) String time1,
			@RequestParam(value = "time2", required = false) String time2,
			@RequestParam(value = "userlimit", required = false) String userlimit,
			@RequestParam(value = "shoplimit", required = false) String shoplimit,
			HttpServletResponse response){
		
		String res="";
		int mytime1=Integer.parseInt(time1);
		int mytime2=Integer.parseInt(time2);
		
		try {
			res=ManagePlatform.addGifts(huanjing,name,  mytime1,  mytime2,userlimit, shoplimit);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject result1=new JSONObject();
		if("".equals(res)){
			result1.put("success", false);
		}else{
			result1.put("success", true);
			result1.put("des", res);
		}
		
		return result1.toString();
	}
	
	@RequestMapping(value="/addmanjian",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String addmanjian(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "items", required = false) String items,

			HttpServletResponse response){
			
		
		JSONObject result1=new JSONObject();
		if (StringUtil.isEmpty(items)) {
			result1.put("success", false);
			result1.put("des", "大兄弟不能为空啊");
		}
		String[] ss=items.split(",");
		
		result1.put("success", false);
		result1.put("des", "待完善");
		
		return result1.toString();
	}
	
	@RequestMapping(value="/shenhetemai",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String shenhe(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			HttpServletResponse response){
		JSONObject result1=new JSONObject();
		Map<String,String> a=ManagePlatform.自动审核特卖(huanjing);
		
		result1.put("success", true);
		result1.put("des","审核总数为：" +a.get("count")+"个。<br/>"+a.get("result"));
		
		return result1.toString();
	}
	@RequestMapping(value="/shenheyouhuiquan",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String youhuiquan(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			HttpServletResponse response){
		JSONObject result1=new JSONObject();
		Map<String,String> a=ManagePlatform.审核优惠券(huanjing);
		
		result1.put("success", true);
		result1.put("des","审核总数为：" +a.get("count")+"个。<br/>"+a.get("result"));
		
		return result1.toString();
	}
	@RequestMapping(value="/shenhelibao",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String libao(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			HttpServletResponse response){
		JSONObject result1=new JSONObject();
		Map<String,String> a=ManagePlatform.审核礼包(huanjing);
		
		result1.put("success", true);
		result1.put("des","审核总数为：" +a.get("count")+"个。<br/>"+a.get("result"));
		
		return result1.toString();
	}
	
	@RequestMapping(value="/shenhepintuan",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String shenhepintuan(
			@RequestParam(value = "huanjing", required = false) String huanjing,
			HttpServletResponse response){
		JSONObject result1=new JSONObject();
		Map<String,String> a=ManagePlatform.审核拼团(huanjing);
		
		result1.put("success", true);
		result1.put("des","审核总数为：" +a.get("count")+"个。"+a.get("result"));
		
		return result1.toString();
	}
	
	
}
