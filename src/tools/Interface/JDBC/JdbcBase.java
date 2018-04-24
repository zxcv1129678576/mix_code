package tools.Interface.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcBase {
	
   private static String url = "/trc_promotion"; 
   private static String name = "com.mysql.jdbc.Driver"; 
   private static String user = "root"; 
   private static String password = "Mysqltest@123098"; 
   public static Connection getConnection(String ip) throws Exception{
			url="jdbc:mysql://"+ip+url;
		    Connection conn = null; 
		    Class.forName(name);//指定连接类型 
		    conn = DriverManager.getConnection(url, user, password);//获取连接 
	
		    return conn;
	}
   
   private static void freeConnection(Connection conn){  
       try {  
           conn.close();  
       } catch (SQLException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       }  
   }  
	
}
