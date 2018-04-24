package develop.test;

import java.util.HashMap;
import java.util.Map;

import tools.Interface.shop.ShopOperate;
import tools.Interface.user.UserInit;
import tools.util.HttpUtil;

public class gongyinglian {
	
	public static void test(int a){
		String url="http://172.30.249.117/scm-web/category/brand";
		
		Map<String,String> map0=new HashMap<>();
		map0.put("name","石乐志"+a);
		map0.put("alise","123123");
		map0.put("webUrl","");
		map0.put("logo","");
		map0.put("isValid","1");
		HttpUtil.setCookieOfHeader("token=7D73ADF71E1A4FF8A3F5D0D3487F10A3.7F3F9CADB94419D259BFDAC586996035");
		HttpUtil.doPost(url, map0);
		HttpUtil.printEntity();
		
	}
	
	public static void test11(int a){
		String url="http://172.30.249.117/scm-web/category/category";
		
		Map<String,String> map1=new HashMap<>();
		map1.put("name","石乐志"+a);
		map1.put("classifyDescribe","1111111");
		map1.put("sort","0");
		map1.put("isValid","1");
		map1.put("level","3");
		map1.put("fullPathId","40");
		map1.put("parentId","41");
		HttpUtil.setCookieOfHeader("token=7D73ADF71E1A4FF8A3F5D0D3487F10A3.7F3F9CADB94419D259BFDAC586996035");
		HttpUtil.doPost(url, map1);
		HttpUtil.printEntity();
		
	}
	
	
	public static void test12(int a){
		String url="http://172.30.249.117/scm-web/goods/goods";
		Map<String,String> map2=new HashMap<>();
		map2.put("name","石乐志"+a);
		map2.put("categoryId","42");
		map2.put("brandId","17");
		map2.put("tradeType","normalTrade");
		map2.put("itemNo","1231231");
		map2.put("producer","");
		map2.put("remark","");
		map2.put("naturePropery","");
		map2.put("purchasePropery[]","133");
		map2.put("purchasePropery[]","134");
		map2.put("purchasePropery[]","139");
		map2.put("purchasePropery[]","140");
		map2.put("skusInfo","[{\"skuCode\":\"\",\"propertyValueId\":\"133,139\",\"propertyValue\":\"2012,箱装\",\"normName\":\"葡萄酒年份:2012 | 包装单位（api）:箱装\",\"barCode\":\"1231\",\"marketPrice2\":\"12312\",\"weight2\":\"1231\",\"picture\":\"goods/2527570536790318.png\",\"isValid\":\"1\",\"source\":1,\"status\":1,\"sortStatus\":0},{\"skuCode\":\"\",\"propertyValueId\":\"134,139\",\"propertyValue\":\"2013,箱装\",\"normName\":\"葡萄酒年份:2013 | 包装单位（api）:箱装\",\"barCode\":\"12312\",\"marketPrice2\":\"12312\",\"weight2\":\"1231\",\"picture\":\"goods/2527573233219652.png\",\"isValid\":\"1\",\"source\":1,\"status\":1,\"sortStatus\":0},{\"skuCode\":\"\",\"propertyValueId\":\"133,140\",\"propertyValue\":\"2012,瓶装\",\"normName\":\"葡萄酒年份:2012 | 包装单位（api）:瓶装\",\"barCode\":\"123\",\"marketPrice2\":\"132\",\"weight2\":\"3123\",\"picture\":\"goods/2527578597043718.png\",\"isValid\":\"1\",\"source\":1,\"status\":1,\"sortStatus\":0},{\"skuCode\":\"\",\"propertyValueId\":\"134,140\",\"propertyValue\":\"2013,瓶装\",\"normName\":\"葡萄酒年份:2013 | 包装单位（api）:瓶装\",\"barCode\":\"1312\",\"marketPrice2\":\"12312\",\"weight2\":\"312\",\"picture\":\"goods/2527564224669418.png\",\"isValid\":\"1\",\"source\":1,\"status\":1,\"sortStatus\":0}]");
		map2.put("salesPropertys","[{\"propertyId\":\"28\",\"propertyName\":\"葡萄酒年份\",\"propertySort\":\"2\",\"propertyValueId\":\"133\",\"propertyValue\":\"2012\",\"sort\":\"0\",\"picture\":\"null\"},{\"propertyId\":\"28\",\"propertyName\":\"葡萄酒年份\",\"propertySort\":\"2\",\"propertyValueId\":\"134\",\"propertyValue\":\"2013\",\"sort\":\"1\",\"picture\":\"null\"},{\"propertyId\":\"29\",\"propertyName\":\"包装单位（api）\",\"propertySort\":\"3\",\"propertyValueId\":\"139\",\"propertyValue\":\"箱装\",\"sort\":\"0\",\"picture\":\"null\"},{\"propertyId\":\"29\",\"propertyName\":\"包装单位（api）\",\"propertySort\":\"3\",\"propertyValueId\":\"140\",\"propertyValue\":\"瓶装\",\"sort\":\"1\",\"picture\":\"null\"}]");
		HttpUtil.setCookieOfHeader("token=7D73ADF71E1A4FF8A3F5D0D3487F10A3.7F3F9CADB94419D259BFDAC586996035");
		HttpUtil.doPost(url, map2);
		HttpUtil.printEntity();
	}
	
	public static void test13(){
		
		
		String url="http://view.trc.com/shop/item/storeItem.html";
		Map<String,String> map3=new HashMap<>();
		map3.put("supplyFlag","1");
		map3.put("spuCode","SPU2017091901064");
		HttpUtil.doPost(url, map3);
		HttpUtil.printEntity();
	}
	
	
	public class ADDgy extends Thread{
		
		private int start;
		private int end;
		
		public ADDgy(int start,int end){
			this.start=start;
			this.end=end;
			}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			for (int i = start; i <=end; i++) {
				
			//	int temp=i;
				try {
					test12(i);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
			}
		}
		
	}
	
	
	public class ADDgy1 extends Thread{
		
		public ADDgy1(){
		
			}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
						test13();
					}
		
	}
	
	
	public void threadtest(){
		for (int i = 0; i < 100; i++) {
			new ADDgy(i*100, (i+1)*100).start();
		}
	}
	
	
	public void threadtest1(){
		for (int i = 0; i < 10; i++) {
			new  ADDgy1().start();
		}
	}
	public static void main(String[] args) {
		ShopOperate.login("http://view.trc.com/", "hulijuan", "admin123");
		new gongyinglian().threadtest1();
		
	//	test13();
//		for (int i = 0; i < 10000; i++) {
//			test12(i);
//		}
		
	}
	
	
}
