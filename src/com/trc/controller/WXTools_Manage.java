package com.trc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WXTools_Manage {
	
	@RequestMapping(value="/wx/autogrouptime",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String update_AutoGroup_Time(
			@RequestParam(value = "ip", required = false) String ip,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end
			){
			
			
		
		return null;
	}
}
