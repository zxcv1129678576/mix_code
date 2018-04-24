package com.trc.controller;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
import tools.util.InterFaceEXEC;
/**
 * 远程执行类
 * @author Administrator
 *
 */
@Controller
public class RemoteEXEC {
	
	@RequestMapping(value="/exec",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String exec(
			@RequestParam(value = "classname", required = false) String classname,
			@RequestParam(value = "themethod", required = false) String themethod,
			HttpServletResponse response){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result=new JSONObject();
		if(classname.isEmpty()||themethod.isEmpty()){
			result.put("classname", classname);
			result.put("themethod", themethod);
			result.put("data","null");
			result.put("des", "类名和方法名不能为空！");
			result.put("Success", false);
		}
		result=InterFaceEXEC.exec(classname, themethod);
		return result.toString();
	}
	
	@RequestMapping(value="/initexec",produces = "text/html;charset=UTF-8")
	public  @ResponseBody String initexec(
			HttpServletResponse response){
		JSONObject result=new JSONObject();
		result=InterFaceEXEC.ScanList();
//		result=InterFaceEXEC.exec(classname, themethod);
		return result.toString();
	}
	
}
