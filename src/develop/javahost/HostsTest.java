package develop.javahost;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import io.leopard.javahost.JavaHost;
import tools.EnvironmentUtil.ConfigUtil;

public class HostsTest {
	public  static  Logger logger = Logger.getLogger(HostsTest.class);
	
	//@PostConstruct
    public static void loadDns() throws IOException {
        Resource resource = new ClassPathResource("/javahosts/environment1.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        Map map=new HashMap<String,String>();
        Iterator it=props.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key +":"+value);
            map.put(key.toString(), value.toString());
            
            ConfigUtil.AddConfig(map);
            System.out.println(ConfigUtil.getJS().toString());
            ConfigUtil.printJS();
            
        }
        
        
        JavaHost.updateVirtualDns(props);
        JavaHost.printAllVirtualDns();// 打印所有虚拟DNS记录
        logger.info("virtualDNS startup!...............................");
    //	HBaseHosts.getJavaHost();
    }

    public static void main(String[] args) throws Exception {
        loadDns();
//        JavaHost.printAllVirtualDns();// 打印所有虚拟DNS记录
//        System.out.println("IP:" + InetAddress.getByName("wx.trc.com").getHostAddress());// 验证一下解析是否正确
//        
    }


}