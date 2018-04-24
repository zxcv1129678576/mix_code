package develop.javahost;

import java.util.Properties;

import io.leopard.javahost.JavaHost;

public class HBaseHosts {

    private static Properties props = new Properties();
    static {
        props.put("baidu1.com", "10.200.140.9");
        props.put("chenda1.date", "10.200.140.10");
        props.put("view1.trc.com", "10.200.140.22");
        props.put("wx1.trc.com", "10.200.140.12");    
    }

    public static void getJavaHost(){
        JavaHost.updateVirtualDns(props);
    }


}