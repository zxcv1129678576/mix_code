package date.chenda.AutoTest.utils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientForREST {
	public  static  Logger logger = Logger.getLogger(HttpClientForREST.class);
	private  RequestConfig requestConfig;
	private  final int MAX_TIMEOUT = 30000;
	private  SSLConnectionSocketFactory sslsf;
	private  PoolingHttpClientConnectionManager connectionManager;
	private  HttpClient httpClient;
	private  CookieStore cookieStore = new BasicCookieStore();
	private  HttpResponse httpResponse = null;
	private  HeaderIterator iterator = null;
	private String status = null;
	private  List<Cookie> myCookie= new ArrayList<Cookie>();
	private boolean IFLOGGING=false;
	
	public  void OpenLogs(){
		IFLOGGING=true;
	}
	public  void OffLogs(){
		IFLOGGING=false;
	}
	
	public  List<Cookie> getMyCookie() {
		myCookie=cookieStore.getCookies();
		return myCookie;
	}
	public  void clear(){
		cookieStore.clear();
	}
	public  String getStatus() {
		return status;
	}
	public  void setStatus(String status) {
		this.status = status;
	}
	public  String getEntitys() {
		return entitys;
	}
	public  void setEntitys(String entitys) {
		this.entitys = entitys;
	}

	private  String entitys = null;;
	private  String CookieOfHeader = null;
	public  void setCookieOfHeader(String cookieOfHeader) {
		CookieOfHeader = cookieOfHeader;
	}

	public  HttpResponse getHttpResponse() {
		return httpResponse;
	}

	
	
	public  void setHttpResponse(HttpResponse httpResponse) throws ParseException, IOException {
		status = httpResponse.getStatusLine().toString();
		iterator = httpResponse.headerIterator();
		entitys = EntityUtils.toString(httpResponse.getEntity());

	}

	private  TrustManager manager = new X509TrustManager() {

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public HttpClientForREST(){
		try {
			SSLContext context1 = SSLContext.getInstance("TLS");
			context1.init(null, new TrustManager[] { manager }, null);
			sslsf = new SSLConnectionSocketFactory(context1, NoopHostnameVerifier.INSTANCE);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		configBuilder.setCookieSpec(CookieSpecs.STANDARD_STRICT);
		// connectionManager.setMaxTotal(200);
		// //DefaultMaxPerRoute
		// 是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。
		// //设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for
		// connection from pool)，路由是对maxTotal的细分。
		// connectionManager.setDefaultMaxPerRoute(connectionManager.getMaxTotal());
		// 在提交请求之前 测试连接是否可用
		// configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
		RegistryBuilder<ConnectionSocketFactory> socketFactoryRegistor = RegistryBuilder
				.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", sslsf);
		connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistor.build());
		// 设置连接池大小
		// connectionManager.setMaxTotal(100);
		// connectionManager.setDefaultMaxPerRoute(connectionManager.getMaxTotal());
		// 初始化httpClient实例，
		httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).setDefaultRequestConfig(requestConfig)
				.setConnectionManager(connectionManager).build();
	//	logger.info("创建HttpClinet!");
		
		
	}
	
	public void shutdown(){
		httpClient.getConnectionManager().shutdown();
	}
	/**
	 * 发送 GET 请求（HTTP），不带输入数据
	 * @param url
	 * @return
	 */
	public  void doGet(String url) {
		 doGet(url, new HashMap<String, String>(), new HashMap<String, String>());
	}
	

	/**
	 * 发送 GET 请求（HTTP），K-V形式
	 * 
	 * @param url
	 * @param params
	 * @param identity
	 * @return
	 */
	public  void doGet(String url, Map<String, String> params,Map<String, String> headers) {
		String apiUrl = url;
		//logger.info("当前的请求是"+url);
		StringBuffer param = new StringBuffer();
		int i = 0;
		//注入query String 
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		HttpResponse response = null;
		//拼接get语句
		apiUrl += param;
		String result = null;
		//创建get方法
		HttpGet httpGet = new HttpGet(apiUrl);
		httpGet.setHeader("Cookie", CookieOfHeader);
		
		//注入头部
		for (String key : headers.keySet()) {
			 httpGet.setHeader(key, headers.get(key));
		}
		try {

			response = httpClient.execute(httpGet);
			setHttpResponse(response);
			httpGet.abort();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public  void doGet(String url, Map<String, String> params) {
		String apiUrl = url;
		logger.info("当前的请求是"+url);
		StringBuffer param = new StringBuffer();
		int i = 0;
		//注入query String 
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		HttpResponse response = null;
		//拼接get语句
		apiUrl += param;
		//创建get方法
		HttpGet httpGet = new HttpGet(apiUrl);
		try {
			response = httpClient.execute(httpGet);
			setHttpResponse(response);
			httpGet.abort();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发送 POST 请求（HTTP），不带输入数据
	 * 
	 * @param apiUrl
	 * @return
	 */
	public  void doPost(String apiUrl) {
		 doPost(apiUrl, new HashMap<String, String>());
	}

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 * 
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return
	 */
	public  void doPost(String apiUrl, Map<String, String> params, Map<String, String> headers) {
	
		HttpPost httpPost = new HttpPost(apiUrl);
		//logger.info("当前的请求是"+apiUrl);
		HttpResponse response = null;
		
		try {
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Cookie", CookieOfHeader);
			List<NameValuePair> pairList = new ArrayList<>(params.size());
//			注入body
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
//			注入头部
			for (String key : headers.keySet()) {
				 httpPost.setHeader(key, headers.get(key));
			}

			response = httpClient.execute(httpPost);
			setHttpResponse(response);
			httpPost.abort();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		
	}
	public void doPost(String apiUrl, Map<String, String> params) {
		
		HttpPost httpPost = new HttpPost(apiUrl);
		httpPost.setHeader("Cookie", CookieOfHeader);
		//logger.info("当前的请求是"+apiUrl);
		HttpResponse response = null;
		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<>(params.size());
//			注入body
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
			response = httpClient.execute(httpPost);
			setHttpResponse(response);
			httpPost.abort();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		
	}
	
	 public  void doPostJSON(String apiUrl, Object json) {  
	        HttpPost httpPost = new HttpPost(apiUrl);    
	        httpPost.setHeader("Cookie", CookieOfHeader);
	    //    logger.info("当前的请求是"+apiUrl);
	        HttpResponse response = null; 	  
	        try {  
	            httpPost.setConfig(requestConfig);  
	            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题  
	            stringEntity.setContentEncoding("UTF-8");  
	            stringEntity.setContentType("application/json");  
	            httpPost.setEntity(stringEntity);  
	            response = httpClient.execute(httpPost);
				setHttpResponse(response);
				httpPost.abort();
	            
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  

	        }  
	    
	    }  	 
	 public  void doPostJSON(String apiUrl, Object json,Map<String, String> headers) {  
	        HttpPost httpPost = new HttpPost(apiUrl);    
	    //    logger.info("当前的请求是"+apiUrl);
	        HttpResponse response = null; 	  
	        try {  
	        	httpPost.setHeader("Cookie", CookieOfHeader);
	            httpPost.setConfig(requestConfig);  
	            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题  
	            stringEntity.setContentEncoding("UTF-8");  
	            stringEntity.setContentType("application/json");  
	            httpPost.setEntity(stringEntity);  
				for (String key : headers.keySet()) {
					 httpPost.setHeader(key, headers.get(key));
				}
	            response = httpClient.execute(httpPost);
				setHttpResponse(response);
				httpPost.abort();
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  

	        }  
	    
	    }  
	
	// 打印当前持有的所有cookie;
	public void printCookie() {
		List<Cookie> cookies = cookieStore.getCookies();
		for (int i = 0; i < cookies.size(); i++) {
			int temp = i + 1;
			System.out.println("Cookies:第" + temp + "cookie;");
			System.out.println("Name:" + cookies.get(i).getName());
			System.out.println("Value:" + cookies.get(i).getValue());
		}
	}

	/**
	 * 打印响应状态
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public void printStatus() {
		// 响应状态
		logger.info("打印响应值："+status);
	}
/**
 * 打印头部
 */
	public  void printHeaders() {

		logger.info("打印头部信息");
		while (iterator.hasNext()) {
			logger.info("\t" + iterator.next());
		}
	}
	public  void UpdateCookie(){
		List<Cookie> cookies = cookieStore.getCookies();
		String temp="";
		for (int i = 0; i < cookies.size(); i++) {
			temp+=cookies.get(i).getName()+"="+cookies.get(i).getName()+"; ";
		}
		setCookieOfHeader(temp);
	}
	/**
	 * 打印相应实体
	 */
	public  void clearsession(){
		cookieStore.clear();
	}
	public void printEntity() {
		// 获取响应消息实体
		 logger.info("打印响应实体：" );
		 logger.info("/t response length:" + entitys.length());
		 logger.info("/t response content:" );
		 logger.info( entitys);
	}
}

