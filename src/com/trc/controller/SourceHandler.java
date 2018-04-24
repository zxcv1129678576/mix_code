package com.trc.controller;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
import tools.EnvironmentUtil.ConfigUtil;
@Controller
public class SourceHandler {
	
 	@RequestMapping(value="/util/getenv",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String addcart(
			HttpServletResponse response){
		JSONObject result1=new JSONObject();
		result1=ConfigUtil.getJS();
		return result1.toString();
	}

}
