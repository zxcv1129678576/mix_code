package tools.Interface.shop;

import java.util.HashMap;
import java.util.Map;
import tools.util.HttpUtil;
public class ShopAuthentication {
	/**
	 * 默认参数为空时，填写hzcd111店铺
	 *  @param username 指定用户
	 *  @param password 指定密码
	 */
public static void 店铺登录(String username ,String password){
	String ShopUrl="http://view.trc.com"+"/shop";
	HttpUtil.doGet(ShopUrl);
	String ShopLogin="http://view.trc.com"+"/shop/passport/signin.html";
	
	Map<String,String> map1=new HashMap<>();
	if(null==username||null==password){
		map1.put("login_account","hzcd");
		map1.put("login_password","admin123");
	}else{
		map1.put("login_account",username);
		map1.put("login_password",password);

	}
	HttpUtil.doPost(ShopLogin, map1);
	//已获取登录状态
}
	
}
