//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//
//import develop.JDBC.MyDbTools;
///**
// * 数据库连接工具
// * @author LiYun<br>
// * 创建日期：2016年9月30日
// */
//public class DBUtil {
//	
//	private Connection conn;
//	private int timeoutSeconds = 0;
//	private Map<String,String> dbmap;
//	public  static  Logger logger = Logger.getLogger( DBUtil.class);
//	//private static ExtLogger logger = ExtLogger.create(DBUtil.class);
//	
//	/**
//	 * 解析数据库连接信息
//	 * @param dbname db.xml文件中的dbname或者表qa_auto_test.bas_connect_db中的dbname
//	 * @param seconds sql查询超时时间，零表示没有限制
//	 */
//	public DBUtil(String dbname,int seconds){
//		timeoutSeconds = seconds;
//		dbmap = XMLUtil.parseDBXML(dbname);
//		if(dbmap.isEmpty()){
//			dbmap = XMLUtil.parseDBXML("qa_auto_test");
//			List<Map<String, String>> list = executeQueryGetList("select url,username,password from bas_connect_db where dbname = '"+dbname+"'");
//			if(list.size() != 1){
//				throw new IllegalArgumentException("dbname为"+dbname+"的行不存在，或存在多行");
//			}else{
//				dbmap.remove("url");
//				dbmap.remove("username");
//				dbmap.remove("password");
//				dbmap.put("dbname", dbname);
//				dbmap.putAll(list.get(0));
//			}
//		}
//	}
//	
//	/**
//	 * 解析数据库连接信息
//	 * @param dbname db.xml文件中的dbname或者表qa_auto_test.bas_connect_db中的dbname
//	 */
//	public DBUtil(String dbname){
//		this(dbname,0);
//	}
//	
//	/**
//	 * 连接数据库
//	 */
//	public void connect(){
//		String url = dbmap.get("url");
//		String username = dbmap.get("username");
//		String password = dbmap.get("password");
//		String driver = "com.mysql.cj.jdbc.Driver";
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			logger.error("数据库连接，类加载出错",e);
//		}   
//		try {
//			conn = DriverManager.getConnection(url,username, password);
//		} catch (SQLException e) {
//			logger.error("连接数据库出错",e);
//		}
//	}
//
//	/**
//	 * 资源释放
//	 */
//	public void close(){
//		if(conn != null){
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				logger.error("数据库连接关闭出错",e);
//			}
//		}
//	}
//	
//	
//	/**
//	 * 建立sql语句发送通道
//	 * @param sql 执行语句
//	 * @return PreparedStatement实例
//	 */
//	public PreparedStatement getPreparedStatement(String sql){
//		PreparedStatement ps = null;
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setQueryTimeout(timeoutSeconds);
////			logger.info("建立SQL语句发送通道：sql["+sql+"]");
//		} catch (SQLException e) {
//			logger.error("建立SQL语句发送通道出错",e);
//		}
//		return ps;
//	}
//	
//	/**
//	 * 执行查询SQL
//	 * @param sql sql查询语句
//	 * @return Map的KEY为列名，VALUE为该列值的List&lt;String&gt;集合
//	 */
//	public Map<String, ArrayList<String>> executeQueryGetMap(String sql){
//		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
//		connect();
//		try{
//            PreparedStatement preStat = getPreparedStatement(sql);
//            logger.info("执行SQL语句："+sql);
//            ResultSet rs = preStat.executeQuery();
//            ResultSetMetaData rsMeta = rs.getMetaData();
//            for(int i = 0; i < rsMeta.getColumnCount(); ++i){
//			    map.put(rsMeta.getColumnName(i+1), new ArrayList<String>());
//		  	}
//            while(rs.next()){
//	            for(int i = 0; i < rsMeta.getColumnCount(); ++i){
//	            	String columnName = rsMeta.getColumnName(i+1);
//	            	//System.out.println(rs.getString(columnName));
//	            	map.get(columnName).add(rs.getString(columnName));
//	            }
//            }
//            rs.close();
//            preStat.close();
//            close();
//            return map;
//        }catch(SQLException e)
//        {
//            logger.error("执行查询SQL出错",e);
//        }
//		close();
//		return map;
//	}
//	
//	/**
//	 * 执行查询SQL
//	 * @param sql sql查询语句
//	 * @return List中的每个map元素代表每行记录，map的KEY值为列名
//	 */
//	public List<Map<String,String>> executeQueryGetList(String sql){
//		List<Map<String,String>> list = new ArrayList<>();
//		List<String> collist = new ArrayList<String>();		//列名集合
//		connect();
//		PreparedStatement preStat = getPreparedStatement(sql);
//        ResultSet rs;
//		try {
//			rs = preStat.executeQuery();
//			ResultSetMetaData rsMeta = rs.getMetaData();
//			for(int i = 1; i <= rsMeta.getColumnCount(); i++){
//				collist.add(rsMeta.getColumnName(i));
//			}
//			Map<String,String> map = null;
//			while(rs.next()){
//				map = new HashMap<>();
//				for(String colname : collist){
//					map.put(colname, rs.getString(colname));
//				}
//				list.add(map);
//			}
//		} catch (SQLException e) {
//			logger.error("执行查询SQL出错",e);
//		}
//		close(); 
//		return list;
//	}
//	
//	/**
//	 * <br>Executes the SQL statement in this PreparedStatement object, 
//	 * which must be an SQL Data Manipulation Language (DML) statement, such as INSERT, UPDATE or DELETE; 
//	 * or an SQL statement that returns nothing, such as a DDL statement.
//	 * @param sqls 要执行的SQL语句，如果有多条SQL，用英文分号【;】隔开
//	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements 
//	 * or (2) 0 for SQL statements that return nothing 
//	 */
//	public int[] executeUpdateSQL(String sqls){
//		String[] sqlarr = sqls.split(";");
//		//连接数据库
//		connect();
////		PreparedStatement statement = this.getPreparedStatement(sqls);
//		Statement stmt = null;
//		int[] result = null;							//the row count for DML SQL or 0 for SQL statements that return nothing
//		String log = "";
//		try {
//			conn.setAutoCommit(false);		//关闭事务自动提交
//			stmt = conn.createStatement();
//			for(String sql : sqlarr){
//				log = log + sql + "\n";
//				stmt.addBatch(sql);
//			}
//			result = stmt.executeBatch();
////			i =statement.executeUpdate();
//			logger.info("执行SQL语句：\n"+log.substring(0, log.length()-1));
//			conn.commit();
//		} catch (SQLException e) {
//			logger.error("执行SQL出错：\n", e);
//			try {
//				logger.info("尝试进行数据库事务回滚");
//				conn.rollback();			//如果出现异常，回滚事务
//				conn.setAutoCommit(true);
//			} catch (SQLException e1) {
//				logger.error("数据库回滚事务出错", e1);
//			}
//		}
//		finally{
//			close();
//		}
//		return result;						//返回执行结果数组
//	}
//
//}
