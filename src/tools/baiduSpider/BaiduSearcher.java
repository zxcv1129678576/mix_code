package tools.baiduSpider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.trc.util.StringUtil;

import develop.JDBC.MyDbTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.util.HttpTool;
import tools.util.HttpTools;
import tools.util.HttpUtil;

public class BaiduSearcher {
	public  static  Logger logger = Logger.getLogger( BaiduSearcher.class);
	final static String  baiduSearch="https://www.baidu.com/s";
	
	public  JSONArray Search(int rank){
		HttpTools httptool=new HttpTools();
		//String key="\"排名:第"+rank+"名.原创  转载 评论\" site:blog.csdn.net";
		String key="\"排名:"+rank+" 积分: 阅读排名 在线课程\" site:blog.csdn.net";
		
		key=StringUtil.encode(key);
		logger.info(key);
		
		Map<String,String> map0=new HashMap<>();
		map0.put("wd",key);
		map0.put("pn","0");
		map0.put("oq",key);
		map0.put("ie","utf-8");
		map0.put("rsv_idx","1");
		map0.put("rsv_pq","fb7dd2a200034b82");
		map0.put("rsv_t","dfd7Ld2EteTj+4yocQGzGN+ZEBO7weiuQhi/D4+3J6qsL9bDCmlsK3+DTXc");
		map0.put("rsv_page","-1");
		
		Map<String,String> map1=new HashMap<>();
		map1.put("Connection: keep-alive","");
		map1.put("Cache-Control: max-age=0","");
		map1.put("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36","");
		map1.put("Upgrade-Insecure-Requests: 1","");
		map1.put("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8","");
		map1.put("Accept-Encoding: gzip, deflate, br","");
		map1.put("Accept-Language: zh-CN,zh;q=0.9","");
		map1.put("Cookie: BAIDUID=EE9193A9082F13A61F8AC27E9928A627:FG=1; PSTM=1512620128; BIDUPSID=88E24CB604622FBB4B553913C013BE08; plus_cv=1::m:21732389; BD_CK_SAM=1; PSINO=5; H_PS_PSSID=25248_1456_24867_21103_25177_20929; BD_UPN=12314353; H_PS_645EC=b131cgxdfshB0F4uA6y2r%2BEiZhasEyYqzKbTiiVAasoycY2IZwvI7p%2BFkGY; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598","");

		httptool.doGet(baiduSearch, map0,map1);
		//HttpUtil.doGet(baiduSearch, map0,map1);
	//	logger.info(httptool.getEntitys());
		
		String s=httptool.getEntitys();
		
		Document doc=Jsoup.parse(s);
		Elements eles =doc.select("h3.t a");
		logger.info("是否水电费水电费水电费");
		logger.info(eles.size());
		List<String> ss=new ArrayList<String>();
		for (int i = 0; i < eles.size(); i++) {
			String urls=eles.get(i).attr("href");
			logger.info("爬取到第一页的链接地址"+urls);
			//System.out.println();
			ss.add(urls);	
		}
		
		JSONArray js=new JSONArray();
		//List<String> tagets=new ArrayList<String>();
		for (int i = 0; i < ss.size(); i++) {
			httptool.doGet(ss.get(i));
			logger.info("本次请求"+ss.get(i));
			//logger.info("返回正文"+httptool.getEntitys());
			JSONObject Alink_content=new JSONObject();
			
			
			try {
				String test=httptool.getEntitys();
				Document csdns=Jsoup.parse(test);
				String pagetype="";
				
				Elements flag1=csdns.select("ul[id=blog_rank] li span");
				
				Elements flag2=csdns.select("div.gradeAndbadge span:nth-child(2)");
				
				if(flag1.size()==4&&flag2.size()!=3){
					pagetype="style1";
				}else if(flag1.size()!=4&&flag2.size()==3){
					pagetype="style2";
				}else{
					pagetype="style3";
				}
				JSONObject ranks_js=new JSONObject();
				JSONObject statics_js=new JSONObject();
				JSONArray category1_js=new JSONArray();
				JSONArray category2_js=new JSONArray();
				

				int a=0;
				int counterrors=0;
				
				String urls="null";
				String the_rank="null";
				
				
				switch (pagetype) {
				case "style1":
				{
					logger.info("样式1111111");
					Elements the_links=csdns.select("head link[rel=canonical]");
					Elements ranks =csdns.select("ul[id=blog_rank] li span");
					Elements statics =csdns.select("ul[id=blog_statistics] li span");
					//博客地址;
					Elements category1 =csdns.select("ul[id=sp_column] table tbody td:nth-child(2)");
					Elements category2 =csdns.select("div[id=panel_Category] ul.panel_body li");
					

					
					if(the_links.size()==0){
						logger.info("URLS获取异常");
						logger.info(the_links.size());
						counterrors++;
					}else{
						
						urls=the_links.get(0).attr("href");
					}
					
					
					if(ranks.size()!=4){
						logger.info("Rank获取异常");
						logger.info(ranks.size());
						counterrors++;
						
					}else{
						the_rank=ranks.get(3).text().substring(1, ranks.get(3).text().length()-1);
						ranks_js.put("rank", the_rank);
						ranks_js.put("fangwen", ranks.get(0).text());
						ranks_js.put("jifen", ranks.get(1).text());
						//等级传的是图片没意义
						//	ranks_js.put("dengji", ranks.get(0).text());
						
					}
					
					if(statics.size()!=4){
						logger.info("statics获取异常");
						logger.info(statics.size());
						counterrors++;
					}else{
						statics_js.put("yuanchuang", statics.get(0).text().substring(0,statics.get(0).text().length()-1));
						statics_js.put("zhuanzai", statics.get(1).text().substring(0,statics.get(1).text().length()-1));
						statics_js.put("yiwen", statics.get(2).text().substring(0,statics.get(2).text().length()-1));
						statics_js.put("pinglun", statics.get(3).text().substring(0,statics.get(3).text().length()-1));
						
					}
					
					if(category1.size()==0){
						logger.info("category1获取异常");
						logger.info(category1.size());
						counterrors++;
					}else{
						
						for (int j = 0; j < category1.size(); j++) {
							JSONObject temp=new JSONObject();
							temp.put("name",category1.get(j).select("a").get(0).text() );
							temp.put("article",category1.get(j).select("p").get(0).text() );
							temp.put("read",category1.get(j).select("span").get(0).text() );
							category1_js.add(temp);
						}
						
					}
					if(category2.size()==0){
						logger.info("category2获取异常");
						logger.info(category2.size());
						counterrors++;
					}else{
						
						for (int j = 0; j <category2.size(); j++) {
							JSONObject temp=new JSONObject();
							temp.put("name",category2.get(j).select("a").get(0).text() );
							temp.put("nums",category2.get(j).select("span").get(0).text() );
							category2_js.add(temp);
							
						}
						
					}
				}
					break;

				case "style2":
				{
					logger.info("样式222222");
					Elements the_links=csdns.select("head link[rel=canonical]");
					Elements ranks =csdns.select("div.gradeAndbadge span:nth-child(2)");
					
					Elements statics =csdns.select("ul[id=blog_statistics] li span");
					//博客地址;
					Elements category1 =csdns.select("ul.csdn-tracking-statistics li");
					Elements category2 =csdns.select("ul.hotArticle-list  li.clearfix");
					

					
					if(the_links.size()==0){
						logger.info("URLS获取异常");
						logger.info(the_links.size());
						counterrors++;
					}else{
						
						urls=the_links.get(0).attr("href");
					}
					
					
					if(ranks.size()!=3){
						logger.info("Rank获取异常");
						logger.info(ranks.size());
						counterrors++;
						
					}else{
						the_rank=ranks.get(1).text();
						ranks_js.put("rank", the_rank);
						ranks_js.put("fangwen", ranks.get(0).text());
						ranks_js.put("jifen", ranks.get(2).text());
						//等级传的是图片没意义
						//	ranks_js.put("dengji", ranks.get(0).text());
						
					}
					
					if(statics.size()!=4){
						logger.info("statics获取异常");
						logger.info(statics.size());
						counterrors++;
					}else{
						statics_js.put("yuanchuang", statics.get(0).text().substring(0,statics.get(0).text().length()-1));
						statics_js.put("zhuanzai", statics.get(1).text().substring(0,statics.get(1).text().length()-1));
						statics_js.put("yiwen", statics.get(2).text().substring(0,statics.get(2).text().length()-1));
						statics_js.put("pinglun", statics.get(3).text().substring(0,statics.get(3).text().length()-1));
						
					}
					
					if(category1.size()==0){
						logger.info("category1获取异常");
						logger.info(category1.size());
						counterrors++;
					}else{
						
						for (int j = 0; j < category1.size(); j++) {
							JSONObject temp=new JSONObject();
							temp.put("name",category1.get(j).select("div.content h4 a").get(0).text() );
							temp.put("article",category1.get(j).select("div.img a div").get(0).text() );
							temp.put("read",category1.get(j).select("div.content div span").get(0).text() );
							category1_js.add(temp);
						}
						
					}
					if(category2.size()==0){
						logger.info("category2获取异常");
						logger.info(category2.size());
						counterrors++;
					}else{
						
						for (int j = 0; j <category2.size(); j++) {
							JSONObject temp=new JSONObject();
							temp.put("name",category2.get(j).select("a").get(0).text() );
							temp.put("nums",category2.get(j).select("div span").get(0).text() );
							category2_js.add(temp);
							
						}
						
					}
					
					
				}
					break;
					
				case "style3":
				{
					
				}
					break;
					
				default:
				{
					
				}
					break;
				}
				
				
				
				

				
				
				try {
					a=Integer.parseInt(the_rank);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					a=1000000;
				}
				
				Alink_content.put("rank",a);
				Alink_content.put("urls",urls );
				Alink_content.put("rank_deatils",ranks_js );
				Alink_content.put("static_deatil",statics_js );
				Alink_content.put("category1_deatil",category1_js );
				Alink_content.put("category2_deatil",category2_js );
				
				if(counterrors<4){
					if (MyDbTools.ifCSDNSexist(a)>0){
						logger.info("第"+a+"名已经存在了");
					}else{
						logger.info("插入第"+a+"名！");
						MyDbTools.CollectCSDNS(Alink_content);
					}
					
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				
			}
			
//			String locations=httptool.getHttpResponse().getHeaders("Location")[0].getValue();
//			logger.info("链接地址"+locations);
//			tagets.add(locations);
			
			
			//加入返货结果
			js.add(Alink_content);
			
		}
		
		
		
		
		
		
		return js;
		
	}
	
	public static void test(){
		
			String ss="http://www.baidu.com/link?url=u0hUspQSaKNeFTP72Dgj22X--CzpYngWUFEDkCyFo5nA0MZWaQ_C4X_rWb8O0D9p-V5gieCR_h-EoRr6UF_7G7Ugqiyd6ikLcywgxKKXi7_";
			
			HttpUtil.doGet(ss);
			logger.info(HttpUtil.getEntitys());
			String test=HttpUtil.getEntitys();
			Document csdns=Jsoup.parse(test);
			Elements the_links=csdns.select("head link[rel=canonical]");
			Elements ranks =csdns.select("ul[id=blog_rank] li span");
			Elements statics =csdns.select("ul[id=blog_statistics] li span");
			//博客地址;
			Elements category1 =csdns.select("ul[id=sp_column] table tbody td:nth-child(2)");
			Elements category2 =csdns.select("div[id=panel_Category] ul.panel_body li");
			
			logger.info(the_links.size());
			logger.info(ranks.size());
			logger.info(statics.size());
			logger.info(category1.size());
			logger.info(category2.size());
			
	}
	
	
	class Threadss extends Thread{
		
		int aaa;
		int bbb;
		BaiduSearcher ss=new BaiduSearcher();
		
		public Threadss(int aaa,int bbb){
			this.aaa=aaa;
			this.bbb=bbb;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			
			for (int j = 0; j < bbb; j++) {
				
				logger.info("当前是线程："+(aaa+1));
				int ssss=aaa*bbb+j;
				
				String aaa=ss.Search(ssss).toString();
						
				logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&一锤定音当前是"+ssss);
				
				
			}
			
		}
		
		
		
	}
	
	public void 批量采取(){
		for (int i = 20; i < 30; i++) {
			
			new Threadss(i,1000).start();

		}
	}
	
	
	public static void main(String[] args) {
		

		
		new BaiduSearcher().批量采取();
		//System.out.println(new BaiduSearcher().Search(1367));
		

	//	test();
		
	}

}
