/**
 * 
 */package tools.Datas;

import java.util.HashMap;
import java.util.Map;

/** 
*@author  作者 : 陈达
*@date 创建时间：Apr 26, 2017 10:15:16 AM
*@version 1.0 
*@parameter  
*@return 
*/
public class ShopData {
	static Map<String,Map<String,String>> datas=new HashMap<>();
	
	public Map<String,String> get(String group){
		return datas.get(group);
	}
	static {
		Map<String, String> 登录 = new HashMap<>();
		登录.put("login_account", "hulijuan");
		登录.put("login_password", "admin123");
		datas.put("登录", 登录);
		
		Map<String,String> map0=new HashMap<>();
		map0.put("item[item_id]","");
		map0.put("return_to_url","");
		map0.put("isZero","");
		map0.put("cat_id","77");
		map0.put("item[sku]","{}");
		map0.put("item[spec]","[]");
		map0.put("item[shop_cids][]","4");

		map0.put("item[sub_title]","111");
		map0.put("search_brand","");
		map0.put("item[brand_id]","757");
		map0.put("item[bn]","1001");
		map0.put("item[barcode]","");
		map0.put("item[supplier_name]","");
		map0.put("item[type]","Normal");
		map0.put("item[installment]","0");
		map0.put("item[installment_text]","");
		map0.put("item[invest]","0");
		map0.put("listimages[]","https://image.trc.com/FlVSKryeGUOVaniabu36wf_ecumn");
		map0.put("item[use_platform]","0");
		map0.put("item[sub_stock]","0");
		map0.put("item[mkt_price]","");
		map0.put("item[cost_price]","");
		map0.put("item[weight]","1");
		map0.put("item[trade_type]","Domestic");
		map0.put("item[tax_rule_id]","");
		map0.put("item[dlytmpl_id]","7");
		map0.put("item[storehouse_id]","417");
		map0.put("item[installment_strategy]","");
		map0.put("item[invest_data][20][invest]","");
		map0.put("item[invest_data][20][profit]","");
		map0.put("item[invest_data][120][invest]","");
		map0.put("item[invest_data][120][profit]","");
		map0.put("item[invest_data][210][invest]","");
		map0.put("item[invest_data][210][profit]","");
		map0.put("item[nature_props][165]","");
		map0.put("item[nature_props][37]","");
		map0.put("spec_value[161_1023]","22");
		map0.put("spec_value[161_1022]","21");
		map0.put("spec_value[161_1021]","20");
		map0.put("spec_value[161_1020]","19");
		map0.put("spec_value[161_1019]","18");
		map0.put("spec_value[161_1018]","17");
		map0.put("spec_value[161_1017]","16");
		map0.put("spec_value[161_1016]","15");
		map0.put("spec_value[161_1015]","14");
		map0.put("spec_value[161_1014]","13");
		map0.put("spec_value[161_1013]","12");
		map0.put("spec_value[161_1012]","11");
		map0.put("spec_value[161_980]","10");
		map0.put("spec_value[161_979]","9");
		map0.put("spec_value[161_978]","8");
		map0.put("spec_value[161_977]","7");
		map0.put("spec_value[161_976]","6");
		map0.put("spec_value[161_975]","5");
		map0.put("spec_value[161_974]","4");
		map0.put("spec_value[161_973]","3");
		map0.put("spec_value[161_972]","2");
		map0.put("spec_value[161_971]","1");
		map0.put("item[desc]","");
		map0.put("item[wap_desc]","");
		
		
		
	}
}
