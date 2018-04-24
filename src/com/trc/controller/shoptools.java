package com.trc.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;
import org.apache.http.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
import tools.Interface.shop.ShopOperate;
import tools.util.DateStringFactory;

/** 
*@author  作者 : 陈达
*@date 创建时间：Apr 7, 2017 2:21:15 PM
*@version 1.0 
*@parameter  
*@return 
*/
 @Controller
public class shoptools {
	 @RequestMapping(value="/addcoupon",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String addcoupon(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "name", required = false) String name,
				@RequestParam(value = "man", required = false) String man,
				@RequestParam(value = "jian", required = false) String jian,
				@RequestParam(value = "all", required = false) String all,
				@RequestParam(value = "one", required = false) String one,
				@RequestParam(value = "ling", required = false) String ling,
				@RequestParam(value = "yong", required = false) String yong,
				@RequestParam(value = "items", required = false) String items,
				@RequestParam(value = "isshow", required = false) String  isshow,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 
		 	System.out.println(isshow);
		 	int 领多少=Integer.parseInt(ling);
		 	int 持续多少=Integer.parseInt(yong);
		 	String entitys=null;
		 	
			try {
				entitys=ShopOperate.AddShopCoupon(huanjing,username,password,name, man, jian, all,one, 领多少, 持续多少, items,isshow);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 @RequestMapping(value="/shop/precoupon",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String couponprepare(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
			JSONObject result1=new JSONObject();
		 	String name1=DateStringFactory.获取当前的月日时分秒()+"优惠券单品1";
		 	String no1=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
//		如果不包含成功则直接失败 
		 	if(!no1.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个优惠券单品失败");
				return result1.toString();
		 	}
		 			 	
		 	String name3=DateStringFactory.获取当前的月日时分秒()+"优惠券SKU";
		 	String no3=ShopOperate.AddSku(huanjing, username, password, name3, "1");
		 	
		 	if(!no3.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个优惠券SKU商品失败");
				return result1.toString();
		 	}
		 		
		 	String items=ShopOperate.获取前几个商品(huanjing, username, password, 2, "3", "优惠券");
		 	if(!items.contains(",")){
				result1.put("success", false);
				result1.put("des", "查询到的添加数组异常，不包含英文逗号！");
				return result1.toString();
		 	}
		 	String[] s=items.split(",");
		 	String statuss=ShopOperate.上下架指定商品(huanjing, username, password, "tosale", s);
	
		 	if(!statuss.contains("true")){
				result1.put("success", false);
				result1.put("des", "上架商品失败！");
				return result1.toString();
		 	}		 
		 	String entitys=items;
			
			

			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 @RequestMapping(value="/shop/addmanjian",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String manjian(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,				
				@RequestParam(value = "time1", required = false) String time1,
				@RequestParam(value = "time2", required = false) String time2,
				@RequestParam(value = "man", required = false) String man,
				@RequestParam(value = "jian", required = false) String jian,

				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 
		 	int 开始=Integer.parseInt(time1);
		 	int 结束=Integer.parseInt(time2);
		 	String entitys=null;
		 	String[] s=items.split(",");
			try {
				entitys=ShopOperate.发布满减活动(huanjing, username, password, items, 开始, 结束, man, jian);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 
	 @RequestMapping(value="/shop/addmiandan",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String miandan(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "itemid", required = false) String itemid,
				@RequestParam(value = "quality", required = false) int quality,
				@RequestParam(value = "limit", required = false) int limit,				
				@RequestParam(value = "time1", required = false) int time1,
				@RequestParam(value = "time2", required = false) int time2,
				@RequestParam(value = "works", required = false) String works,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 	String entitys=null;
			try {
				entitys=ShopOperate.addMianDan(huanjing, username, password, itemid, quality, limit, time1, time2, works);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			return result1.toString();
		}
	 
	 @RequestMapping(value="/shop/premiandan",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String miandanprepare(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				HttpServletResponse response){
			JSONObject result1=new JSONObject();
		 	String name1=DateStringFactory.获取当前的月日时分秒()+"免单单品";
		 	String no1=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
//		如果不包含成功则直接失败 
		 	if(!no1.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个免单单品失败");
				return result1.toString();
		 	}
		 		
		 	String items=ShopOperate.获取前几个商品(huanjing, username, password, 1, "3", "免单单品");
		 	String[] s=items.split(",");
		 	String statuss=ShopOperate.上下架指定商品(huanjing, username, password, "tosale", s);
	
		 	if(!statuss.contains("true")){
				result1.put("success", false);
				result1.put("des", "上架商品失败！");
				return result1.toString();
		 	}		 
		 	String entitys=items;
			
			

			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			return result1.toString();
		}
	 
	 
	 @RequestMapping(value="/shop/premanjian",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String manjianprepare(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
			JSONObject result1=new JSONObject();
		 	String name1=DateStringFactory.获取当前的月日时分秒()+"满减单品1";
		 	String no1=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
//		如果不包含成功则直接失败 
		 	if(!no1.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个满减单品失败");
				return result1.toString();
		 	}
		 	
		 	
		 	
		 	String name3=DateStringFactory.获取当前的月日时分秒()+"满减SKU1";
		 	String no3=ShopOperate.AddSku(huanjing, username, password, name3, "1");
		 	
		 	if(!no3.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个满减SKU商品失败");
				return result1.toString();
		 	}
		 	String items=ShopOperate.获取前几个商品(huanjing, username, password, 2, "3", "满减");
		 	if(!items.contains(",")){
				result1.put("success", false);
				result1.put("des", "查询到的添加数组异常，不包含英文逗号！");
				return result1.toString();
		 	}
		 	String[] s=items.split(",");
		 	String statuss=ShopOperate.上下架指定商品(huanjing, username, password, "tosale", s);
	
		 	if(!statuss.contains("true")){
				result1.put("success", false);
				result1.put("des", "上架商品失败！");
				return result1.toString();
		 	}		 
		 	String entitys=items;
			
			

			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 
	 
	 @RequestMapping(value="/shop/addmanzhe",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String manzhe(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,				
				@RequestParam(value = "time1", required = false) String time1,
				@RequestParam(value = "time2", required = false) String time2,
				@RequestParam(value = "man", required = false) String man,
				@RequestParam(value = "zhe", required = false) String zhe,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 	String[] s=items.split(",");
		 	int 开始=Integer.parseInt(time1);
		 	int 结束=Integer.parseInt(time2);
		 	String entitys=null;
		 	
			try {
				entitys=ShopOperate.发布满折活动(huanjing, username, password, items, 开始, 结束, man, zhe);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 @RequestMapping(value="/shop/premanzhe",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String manzheprepare(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
			JSONObject result1=new JSONObject();
		 	String name1=DateStringFactory.获取当前的月日时分秒()+"满折单品1";
		 	String no1=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
//		如果不包含成功则直接失败 
		 	if(!no1.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个满折单品失败");
				return result1.toString();
		 	}
		 			 	
		 	String name3=DateStringFactory.获取当前的月日时分秒()+"满折SKU";
		 	String no3=ShopOperate.AddSku(huanjing, username, password, name3, "1");
		 	
		 	if(!no3.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个满折SKU商品失败");
				return result1.toString();
		 	}
		 		
		 	String items=ShopOperate.获取前几个商品(huanjing, username, password, 2, "3", "满折");
		 	if(!items.contains(",")){
				result1.put("success", false);
				result1.put("des", "查询到的添加数组异常，不包含英文逗号！");
				return result1.toString();
		 	}
		 	String[] s=items.split(",");
		 	String statuss=ShopOperate.上下架指定商品(huanjing, username, password, "tosale", s);
	
		 	if(!statuss.contains("true")){
				result1.put("success", false);
				result1.put("des", "上架商品失败！");
				return result1.toString();
		 	}		 
		 	String entitys=items;
			
			

			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 @RequestMapping(value="/shop/prenyuan",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String N元任选(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "num", required = false) String num,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 	int test=Integer.parseInt(num);
			return ShopOperate.准备活动商品(huanjing, username, password,test, "N元任选", "0");
		}
	 
	 @RequestMapping(value="/shop/addnyuan",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String addnyuan(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,		
				@RequestParam(value = "price", required = false) String price,	
				@RequestParam(value = "num", required = false) int num,	
				@RequestParam(value = "time1", required = false) int time1,
				@RequestParam(value = "time2", required = false) int time2,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 	String[] s=items.split(",");
//		 	int 开始=Integer.parseInt(time1);
//		 	int 结束=Integer.parseInt(time2);
		 	
		 	String entitys=null;
		 	
			try {
				entitys=ShopOperate.发布N元任选活动(huanjing, username, password, s, time1, time2, price, num);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 @RequestMapping(value="/shop/addzhijiang",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String zhijiang(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,				
				@RequestParam(value = "time1", required = false) String time1,
				@RequestParam(value = "time2", required = false) String time2,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 	String[] s=items.split(",");
		 	int 开始=Integer.parseInt(time1);
		 	int 结束=Integer.parseInt(time2);
		 	String entitys=null;
		 	
			try {
				entitys=ShopOperate.发布直降活动(huanjing, username, password, s, 开始, 结束);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 
	 
	 @RequestMapping(value="/shop/prezhijiang",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String zhijiangprepare(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
			JSONObject result1=new JSONObject();
		 	String name1=DateStringFactory.获取当前的月日时分秒()+"直降单品1";
		 	String no1=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
//		如果不包含成功则直接失败 
		 	if(!no1.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个直降单品失败");
				return result1.toString();
		 	}
		 			 	
		 	String name3=DateStringFactory.获取当前的月日时分秒()+"直降SKU";
		 	String no3=ShopOperate.AddSku(huanjing, username, password, name3, "1");
		 	
		 	if(!no3.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个直降SKU商品失败");
				return result1.toString();
		 	}
		 		
		 	String items=ShopOperate.获取前几个商品(huanjing, username, password, 2, "3", "直降");
		 	if(!items.contains(",")){
				result1.put("success", false);
				result1.put("des", "查询到的添加数组异常，不包含英文逗号！");
				return result1.toString();
		 	}
		 	String[] s=items.split(",");
		 	String statuss=ShopOperate.上下架指定商品(huanjing, username, password, "tosale", s);
	
		 	if(!statuss.contains("true")){
				result1.put("success", false);
				result1.put("des", "上架商品失败！");
				return result1.toString();
		 	}		 
		 	String entitys=items;
			
			

			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 @RequestMapping(value="/shop/addpintuan",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String pintuan(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "number", required = false) String number,
				@RequestParam(value = "price", required = false) String price,
				@RequestParam(value = "limit", required = false) String  limit,
				@RequestParam(value = "id", required = false) String id,
				@RequestParam(value = "time1", required = false) String time1,
				@RequestParam(value = "time2", required = false) String time2,
				@RequestParam(value = "types", required = false) String types,
				@RequestParam(value = "numbersss", required = false) String numbersss,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 
		 	int 开始=Integer.parseInt(time1);
		 	int	结束=Integer.parseInt(time2);
		 	String entitys=null;
		 	
			try {
				entitys=ShopOperate.发布拼团活动(huanjing, username, password, number, price, limit, id, 开始, 结束,types,numbersss);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 @RequestMapping(value="/shop/prepintuan",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String pintuanprepare(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
			JSONObject result1=new JSONObject();
		 	String name1=DateStringFactory.获取当前的月日时分秒()+"拼团单品1";
		 	String no1=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
//		如果不包含成功则直接失败 
		 	if(!no1.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个拼团单品失败");
				return result1.toString();
		 	}
		 		
		 	String items=ShopOperate.获取前几个商品(huanjing, username, password, 1, "3", "拼团");
		 	String[] s=items.split(",");
		 	String statuss=ShopOperate.上下架指定商品(huanjing, username, password, "tosale", s);
	
		 	if(!statuss.contains("true")){
				result1.put("success", false);
				result1.put("des", "上架商品失败！");
				return result1.toString();
		 	}		 
		 	String entitys=items;
			
			

			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 @RequestMapping(value="/shop/itemsearch",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String itemsearch(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "number", required = false) String number,
				@RequestParam(value = "status", required = false) String status,
				@RequestParam(value = "itemname", required = false) String  itemname,

				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 
		 	int n=Integer.parseInt(number);

		 	String entitys=ShopOperate.获取前几个商品(huanjing, username, password, n, status, itemname);	
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 @RequestMapping(value="/shop/itemstatus",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String itemstatus(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,
				@RequestParam(value = "type", required = false) String type,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 
		 	String[] s=items.split(",");

		 	String entitys=ShopOperate.上下架指定商品(huanjing, username, password, type, s);
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 
	 @RequestMapping(value="/shop/enlisttemai",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String enlisttemai(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,
				@RequestParam(value = "price", required = false) String price,
				@RequestParam(value = "store", required = false) String store,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 
		 	String[] s=items.split(",");

		 	String entitys=ShopOperate.报名特卖活动(huanjing, username, password, price, store, s);
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 @RequestMapping(value="/shop/pretemai",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String temaiprepare(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
			JSONObject result1=new JSONObject();
		 	String name1=DateStringFactory.获取当前的月日时分秒()+"特卖单品1";
		 	String no1=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
//		如果不包含成功则直接失败 
		 	if(!no1.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个特卖单品失败");
				return result1.toString();
		 	}
		 			 	
		 	String name3=DateStringFactory.获取当前的月日时分秒()+"特卖SKU";
		 	String no3=ShopOperate.AddSku(huanjing, username, password, name3, "1");
		 	
		 	if(!no3.contains("true")){
				result1.put("success", false);
				result1.put("des", "添加第一个特卖SKU商品失败");
				return result1.toString();
		 	}
		 		
		 	String items=ShopOperate.获取前几个商品(huanjing, username, password, 2, "3", "特卖");
		 	if(!items.contains(",")){
				result1.put("success", false);
				result1.put("des", "查询到的添加数组异常，不包含英文逗号！");
				return result1.toString();
		 	}
		 	String[] s=items.split(",");
		 	String statuss=ShopOperate.上下架指定商品(huanjing, username, password, "tosale", s);
	
		 	if(!statuss.contains("true")){
				result1.put("success", false);
				result1.put("des", "上架商品失败！");
				return result1.toString();
		 	}		 
		 	String entitys=items;
			
			

			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 
	 @RequestMapping(value="/shop/youhui",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String baomingyouhui(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 
		 	String[] s=items.split(",");

		 	String entitys=ShopOperate.报名平台优惠券(huanjing, username, password, s);
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 @RequestMapping(value="/shop/libao",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String baominglibao(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 	String[] s=items.split(",");
		 	String entitys=ShopOperate.报名礼包(huanjing, username, password, s);
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			return result1.toString();
		}
	 
	 
	 @RequestMapping(value="/shop/addmiaosha",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String miaosha(
				@RequestParam(value = "huanjing", required = false) String huanjing,
				@RequestParam(value = "username", required = false) String username,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "items", required = false) String items,				
				@RequestParam(value = "time1", required = false) int time1,
				@RequestParam(value = "time2", required = false) int time2,
				@RequestParam(value = "time3", required = false) int time3,			
				@RequestParam(value = "price", required = false) String price,
				@RequestParam(value = "store", required = false) String store,

				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
		 String[] s=items.split(",");
		 Arrays.sort(s);
		 	String entitys=null;
		 	
			try {
				entitys=ShopOperate.发布秒杀活动(huanjing, username, password,s, time1, time2, time3, price,store);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			JSONObject result1=new JSONObject();
			if("".equals(entitys)){
				result1.put("success", false);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}
	 
	 
	 @RequestMapping(value="/shop/premiaosha",produces = "text/html;charset=UTF-8")
			public  @ResponseBody String miaoshaprepare(
					@RequestParam(value = "huanjing", required = false) String huanjing,
					@RequestParam(value = "username", required = false) String username,
					@RequestParam(value = "password", required = false) String password,
					HttpServletResponse response){
	//		 	减少工作量，所有字段校验只做前端校验。
				JSONObject result1=new JSONObject();
			 	String name1=DateStringFactory.获取当前的月日时分秒()+"秒杀单品1";
			 	String no1=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
	//		如果不包含成功则直接失败 
			 	if(!no1.contains("true")){
					result1.put("success", false);
					result1.put("des", "添加第一个秒杀单品1失败");
					return result1.toString();
			 	}
			 			 	
			 	String name3=DateStringFactory.获取当前的月日时分秒()+"秒杀单品2";
			 	String no3=ShopOperate.AddNoSku(huanjing, username, password, "80", name1, "10000", "1");
			 	
			 	if(!no3.contains("true")){
					result1.put("success", false);
					result1.put("des", "添加第一个秒杀单品2商品失败");
					return result1.toString();
			 	}
			 		
			 	String items=ShopOperate.获取前几个商品(huanjing, username, password, 2, "3", "秒杀单品");
			 	if(!items.contains(",")){
					result1.put("success", false);
					result1.put("des", "查询到的添加数组异常，不包含英文逗号！");
					return result1.toString();
			 	}
			 	String[] s=items.split(",");
			 	String statuss=ShopOperate.上下架指定商品(huanjing, username, password, "tosale", s);
		
			 	if(!statuss.contains("true")){
					result1.put("success", false);
					result1.put("des", "上架商品失败！");
					return result1.toString();
			 	}		 
			 	String entitys=items;
				
				
	
				if("".equals(entitys)){
					result1.put("success", false);
				}else{
					result1.put("success", true);
					result1.put("des", entitys);
				}
				
				return result1.toString();
			}
	 
	 
}
