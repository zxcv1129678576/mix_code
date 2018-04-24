package com.trc.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import tools.Interface.user.UserBasic;

@Controller
public class UserTools {
	 	@RequestMapping(value="/user/addcart",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String addcart(
				@RequestParam(value = "items", required = false) String items,
				@RequestParam(value = "phone", required = false) String phone,
				@RequestParam(value = "huanjing", required = false) String huanjing,
				HttpServletResponse response){
//		 	减少工作量，所有字段校验只做前端校验。
			JSONObject result1=new JSONObject();
		 	if(items.isEmpty()||phone.isEmpty()){
		 		result1.put("success", false);
		 		result1.put("des", "用户名和数组不能为空！");
		 	}

		 	JSONObject entitys=UserBasic.添加到购物袋(huanjing,items, phone);
		
			if("".equals(entitys.toString())){
				result1.put("success", false);
				result1.put("des", entitys);
			}else{
				result1.put("success", true);
				result1.put("des", entitys);
			}
			
			return result1.toString();
		}

}
