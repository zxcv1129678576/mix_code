package PressTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import tools.Interface.manage.Manage_WX_Prepare;
import tools.util.HttpTools;

public class ImagePressTest {
	public  static final Logger logger = Logger.getLogger(ImagePressTest.class);
	class imageThread extends Thread{
		private String base64="";
		private int aa;
		
		public imageThread(String image,int aa) {
			// TODO Auto-generated constructor stub
			this.base64=image;
			this.aa=aa;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//1503975793182
			//1503975882500

			String url="http://wx.trc.com/wxapi/uploadImagesCompress.api?_="+System.currentTimeMillis();
			HttpTools a= new HttpTools();
			Map<String,String> map0=new HashMap<>();
			map0.put("img","data:image/jpeg;base64,"+base64);
			a.doPost(url, map0);
			String s=a.getEntitys();
			if(s.contains("true")){
				System.out.println("通过");
				logger.info("当前第"+aa+"条");
				logger.info(a.getEntitys());
			}
		
			//a.printEntity();
			super.run();
		}
		
	}
	public  void test(int a){
		final String image=Base64Img.GetImageStrFromPath("G:/imges/test.png");
		for (int i = 0; i < a; i++) {
			new imageThread(image,i).start();
		}
		
	}
	public static void main(String[] args) {
		new ImagePressTest().test(10);
		
	}
}
