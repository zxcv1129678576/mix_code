package PressTest.JDBCprepare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Milins {
	

	
	public static Connection getConnection(String ip,String username,String password,String dbname){
		
	     String url = "jdbc:mysql://"+ip+"/"+dbname; 
	     String name = "com.mysql.jdbc.Driver"; 
	     Connection conn = null; 
	     try {
			Class.forName(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//指定连接类型 
	     try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取连接 
		 return conn;
	}
	
	
public static void insertBatch() {
  int count[];
  int count1[];
  Boolean isinsert = false;
  Connection con = null;
  PreparedStatement pst = null;
  PreparedStatement pst1 = null;
  try {
   con = getConnection(null, null, null, null);
   
   String INSERT_SQL="";
   String INSERT_SQL1="";
   con.setAutoCommit(false);                                   // 需要用到事务，不能让他自动提交，需要手动提交
   pst = con.prepareStatement(INSERT_SQL);          // INSERT_SQL表示对一张表的插入记录
   pst1 = con.prepareStatement(INSERT_SQL1);      // INSERT_SQL表示对另一张表的插入记录

   pst.setString(1, "name1");
   pst.setInt(2, 26);
   pst.setString(3, "job1");
   pst.addBatch();
   
   pst.setString(1, "name2");
   pst.setInt(2, 12);
   pst.setString(3, "job2");
   pst.addBatch();
//   -------可以对pst进行更多的插入-----------
   pst1.setString(1, "name--1");
   pst1.setInt(2, 26);
   pst1.setString(3, "job--1");
   pst1.addBatch();
   
   pst1.setString(1, "name--2");
   pst1.setInt(2, 26);
   pst1.setString(3, "job--2");
   pst1.addBatch();
//   -------可以对pst1进行更多的插入-----------
   count = pst.executeBatch();
   count1 = pst1.executeBatch();
   con.commit();                 //提交事务，这个非常重要
   
   for(int i : count){
    if(i == 0) {
     con.rollback();              // 回滚，非常重要
     System.out.println("======出现异常，回滚=========");
    }
   }
   
   for(int i : count1){
    if(i == 0) {
     con.rollback();          // 回滚，非常重要
     System.out.println("==111====出现异常，回滚====111=====");
    }
   }
     
  } catch (SQLException e) {
   try {
    con.rollback();            // 回滚，非常重要
   } catch (SQLException e1) {
    e1.printStackTrace();
   }
   System.out.println("------出现异常，回滚----------");
   e.printStackTrace();
  } finally {
   //cloCon(con,pst);
  }
 }

}
