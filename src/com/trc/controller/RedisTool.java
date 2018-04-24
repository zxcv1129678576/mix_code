package com.trc.controller;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import develop.redis.RedisStringJava;
@Controller
public class RedisTool {
	
	@RequestMapping(value="/changeid",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String exec(
			HttpServletResponse response){
		return RedisStringJava.AddIDcard();
	}
}
