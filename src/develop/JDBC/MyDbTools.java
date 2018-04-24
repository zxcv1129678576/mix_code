package develop.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class MyDbTools {
	
	
	public  static  Logger logger = Logger.getLogger( MyDbTools.class);
	
	public static void main(String[] args) {
		//logger.info("test");
		//Connection s=getConnection("10.200.140.46", "root", "Mysqltest@123098", "trc_order");
	
		try {
			ifCSDNSexist(587);
			//insertOrdersFast(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param ip 需要连接的IP
	 * @param username 数据库用户名
	 * @param dbname 数据库名称
	 * @param password 数据库名称
	 * @return jdbc Connection
	 */
	public static Connection getConnection(String ip,String username,String password,String dbname){
		
	     String url = "jdbc:mysql://"+ip+":3306/"+dbname+"?useUnicode=true&characterEncoding=UTF-8"; 
	     String name = "com.mysql.jdbc.Driver"; 
	     Connection conn = null; 
	     try {
			Class.forName(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//指定连接类型 
	     try {
	    	 logger.info(url);
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取连接 
		 return conn;
	}
	
	/**
	 * 
	 * @param start 拼团起始ID
	 * @param end	拼团结束ID
	 * @param conn 	数据库连接
	 * @return 是否执行成功过（executeUpdate（））
	 * @throws Exception SQL Exception
	 */
    public static Boolean updateAutoGruopTimeAll(int start,int end ,Connection conn) throws Exception{
    	StringBuffer sb= new StringBuffer();
    	for (int i = start; i <= end; i++) {
    		if(end==i){
    			sb.append(i);
    		}else{
    			sb.append(i);
    			sb.append(",");
    		}
		}
    	//构建更新拼团状态语句
    	String aa="UPDATE systrade_group_purchase_object SET open_time=(open_time-automatic_group_time) WHERE object_id in ("+sb.toString()+");";
    	// conn.setAutoCommit(false);
    	
    	String sql_order="INSERT INTO `trc_order`.`order` (`id`, `user_id`, `user_name`, `item_num`, `pay_type`, `payment`, `points_fee`, `total_fee`, `adjust_fee`, `post_fee`, `total_tax`, `need_invoice`, `invoice_name`, `invoice_type`, `invoice_main`, `receiver_state`, `receiver_city`, `receiver_district`, `receiver_address`, `receiver_zip`, `receiver_name`, `receiver_id_number`, `receiver_id_card_front`, `receiver_id_card_back`, `receiver_phone`, `receiver_mobile`, `buyer_area`, `ziti_memo`, `ziti_addr`, `anony`, `obtain_point_fee`, `real_point_fee`, `step_trade_status`, `step_paid_fee`, `is_clearing`, `cancel_reason`, `cancel_status`, `status`, `is_virtual`, `ip`, `type`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `shipping_type`, `platform`, `rate_status`, `coupon_code`, `group_buy_status`, `is_del`, `created_time`, `pay_time`, `consign_time`, `receive_time`, `modified_time`, `timeout_action_time`, `end_time`, `pay_bill_id`, `coupon_rule`, `coupon_type`, `buy_type`, `arguments`) VALUES (?, '345', '18758017446', '1', 'online', '100.000', '0.000', '100.000', '100.000', '0.000', '0.000', '0', '', '', '', '天津市', '和平区', '', '围雪昔施伍燥郡共淫磨炬桑管佃赣再崎奎岛甭榴帝随荧寄郴虽谷铃偶夸抬疲本常梦束倘警池', NULL, '江竟腹芳史瞧', NULL, NULL, NULL, '', '18758017446', '', '', '', '0', '0', '0', '', '0.000', '0', NULL, 'NO_APPLY_CANCEL', 'WAIT_BUYER_PAY', '0', '10.200.5.14', '0', '0.000', '0.000', '0.000', '0.000', 'express', 'pc', '0', '', 'NO_APPLY', '0', '1511839453', '0', '0', '0', '1511839453', '1515079453', '0', 'B1711281124132055RG0', '', '0', '0', '{\"red_packet\": {\"codes\": [], \"amount\": 0}}');";
    	String sql_shop="INSERT INTO `trc_order`.`shop_order` (`id`, `order_id`, `shop_id`, `shop_name`, `user_id`, `dlytmpl_ids`, `status`, `is_del`, `payment`, `total_fee`, `post_fee`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `title`, `buyer_message`, `adjust_fee`, `item_num`, `shop_memo`, `total_weight`, `trade_memo`, `rate_status`, `is_part_consign`, `group_buy_status`, `total_tax`, `created_time`, `consign_time`, `modified_time`, `arguments`) VALUES (?, ?, '2', '泰然直营1（自营店铺）', '345', '516', 'WAIT_BUYER_PAY', '0', '100.000', '100.000', '0.000', '0.000', '0.000', '0.000', '0.000', '', '', '100.000', '1', '', '1.000', '', '0', '0', 'NO_APPLY', '0.000', '1511839453', '0', '1511839453', '{\"red_packet\": {\"amount\": 0}}');";
    	String sql_store="INSERT INTO `trc_order`.`repo_order` (`id`, `shop_order_id`, `order_id`, `shop_id`, `repo_id`, `user_id`, `adjust_fee`, `post_discount`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_promotion`, `discount_fee`, `total_fee`, `payment`, `created_time`, `arguments`) VALUES (?, ?, ?, '2', '417', '345', '100.000', '0.000', '0.000', '0.000', '0.000', '0.000', '100.000', '100.000', '1511839453', '{\"red_packet\": {\"amount\": 0}}');";
    	String sql_good="INSERT INTO `trc_order`.`good_order` (`id`, `repo_order_id`, `shop_order_id`, `order_id`, `repo_id`, `repo_name`, `cat_id`, `cat_primary_name`, `cat_secondary_name`, `cat_tertiary_name`, `shop_id`, `shop_name`, `user_id`, `item_id`, `sku_id`, `art_no`, `barcode`, `title`, `spec_nature_info`, `price`, `market_price`, `promotion_price`, `customs_price`, `transaction_price`, `num`, `send_num`, `sku_properties_name`, `refund_id`, `is_oversold`, `shipping_type`, `bind_oid`, `logistics_company`, `invoice_no`, `post_discount`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `total_fee`, `payment`, `total_weight`, `adjust_fee`, `status`, `after_sales_status`, `complaints_status`, `refund_fee`, `cat_service_rate`, `pic_path`, `outer_iid`, `outer_sku_id`, `sub_stock`, `dlytmpl_id`, `supplier_name`, `price_tax`, `promotion_tags`, `obj_type`, `type`, `tax_rate`, `params`, `created_time`, `pay_time`, `consign_time`, `modified_time`, `timeout_action_time`, `end_time`, `arguments`) VALUES (?, ?, ?, ?, '417', '商家杭州市65号仓', '77', '美妆个护', '洗发/护发', '假发 ', '2', '泰然直营1（自营店铺）', '345', '30334', '56573', '1001', '', '图片太吓人了！', '', '100.000', '100000.000', '0.000', '100.000', '100.000', '1', '0', '', '', '0', 'express', '', '', '', '0.000', '0.000', '0.000', '0.000', '0.000', '100.000', '100.000', '1.000', '100.000', 'WAIT_BUYER_PAY', '', '', '0.000', '0.000', 'https://image.trc.com/FnxE0Q9ngElDA7OmLKd8wirIHYkN', '', '', '1', '516', '测试供应商aaaaaa', '0.000', '', 'item', '0', '0.00000', '', '1511839453', '0', '0', '1511839453', '0', '0', '{\"good_type\": \"Domestic\", \"red_packet\": {\"amount\": 0}, \"product_type\": \"Normal\"}');";
    	
    	
    	PreparedStatement  pst_order = (PreparedStatement) conn.prepareStatement("");//准备执行语句
    	int a=pst_order.executeUpdate(aa);
    	System.out.println(a);
    	
    	 //  conn.commit();
    	//释放低级资源
    	pst_order.close();
        conn.close();
    	return !(0==a);
    }
	
    public static boolean insertOrders(Connection conn)throws Exception{
    	
    	try{
            System.out.println("start insert data");  
            Long beginTime = System.currentTimeMillis();  
            conn.setAutoCommit(false);  
            
            
        	String sql_order="INSERT INTO `trc_order`.`order` (`id`, `user_id`, `user_name`, `item_num`, `pay_type`, `payment`, `points_fee`, `total_fee`, `adjust_fee`, `post_fee`, `total_tax`, `need_invoice`, `invoice_name`, `invoice_type`, `invoice_main`, `receiver_state`, `receiver_city`, `receiver_district`, `receiver_address`, `receiver_zip`, `receiver_name`, `receiver_id_number`, `receiver_id_card_front`, `receiver_id_card_back`, `receiver_phone`, `receiver_mobile`, `buyer_area`, `ziti_memo`, `ziti_addr`, `anony`, `obtain_point_fee`, `real_point_fee`, `step_trade_status`, `step_paid_fee`, `is_clearing`, `cancel_reason`, `cancel_status`, `status`, `is_virtual`, `ip`, `type`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `shipping_type`, `platform`, `rate_status`, `coupon_code`, `group_buy_status`, `is_del`, `created_time`, `pay_time`, `consign_time`, `receive_time`, `modified_time`, `timeout_action_time`, `end_time`, `pay_bill_id`, `coupon_rule`, `coupon_type`, `buy_type`, `arguments`) VALUES (?, '345', '18758017446', '1', 'online', '100.000', '0.000', '100.000', '100.000', '0.000', '0.000', '0', '', '', '', '天津市', '和平区', '', '围雪昔施伍燥郡共淫磨炬桑管佃赣再崎奎岛甭榴帝随荧寄郴虽谷铃偶夸抬疲本常梦束倘警池', NULL, '江竟腹芳史瞧', NULL, NULL, NULL, '', '18758017446', '', '', '', '0', '0', '0', '', '0.000', '0', NULL, 'NO_APPLY_CANCEL', 'WAIT_BUYER_PAY', '0', '10.200.5.14', '0', '0.000', '0.000', '0.000', '0.000', 'express', 'pc', '0', '', 'NO_APPLY', '0', '1511839453', '0', '0', '0', '1511839453', '1515079453', '0', 'B1711281124132055RG0', '', '0', '0', '{\"red_packet\": {\"codes\": [], \"amount\": 0}}');";
        	String sql_shop="INSERT INTO `trc_order`.`shop_order` (`id`, `order_id`, `shop_id`, `shop_name`, `user_id`, `dlytmpl_ids`, `status`, `is_del`, `payment`, `total_fee`, `post_fee`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `title`, `buyer_message`, `adjust_fee`, `item_num`, `shop_memo`, `total_weight`, `trade_memo`, `rate_status`, `is_part_consign`, `group_buy_status`, `total_tax`, `created_time`, `consign_time`, `modified_time`, `arguments`) VALUES (?, ?, '2', '泰然直营1（自营店铺）', '345', '516', 'WAIT_BUYER_PAY', '0', '100.000', '100.000', '0.000', '0.000', '0.000', '0.000', '0.000', '', '', '100.000', '1', '', '1.000', '', '0', '0', 'NO_APPLY', '0.000', '1511839453', '0', '1511839453', '{\"red_packet\": {\"amount\": 0}}');";
        	String sql_store="INSERT INTO `trc_order`.`repo_order` (`id`, `shop_order_id`, `order_id`, `shop_id`, `repo_id`, `user_id`, `adjust_fee`, `post_discount`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_promotion`, `discount_fee`, `total_fee`, `payment`, `created_time`, `arguments`) VALUES (?, ?, ?, '2', '417', '345', '100.000', '0.000', '0.000', '0.000', '0.000', '0.000', '100.000', '100.000', '1511839453', '{\"red_packet\": {\"amount\": 0}}');";
        	String sql_good="INSERT INTO `trc_order`.`good_order` (`id`, `repo_order_id`, `shop_order_id`, `order_id`, `repo_id`, `repo_name`, `cat_id`, `cat_primary_name`, `cat_secondary_name`, `cat_tertiary_name`, `shop_id`, `shop_name`, `user_id`, `item_id`, `sku_id`, `art_no`, `barcode`, `title`, `spec_nature_info`, `price`, `market_price`, `promotion_price`, `customs_price`, `transaction_price`, `num`, `send_num`, `sku_properties_name`, `refund_id`, `is_oversold`, `shipping_type`, `bind_oid`, `logistics_company`, `invoice_no`, `post_discount`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `total_fee`, `payment`, `total_weight`, `adjust_fee`, `status`, `after_sales_status`, `complaints_status`, `refund_fee`, `cat_service_rate`, `pic_path`, `outer_iid`, `outer_sku_id`, `sub_stock`, `dlytmpl_id`, `supplier_name`, `price_tax`, `promotion_tags`, `obj_type`, `type`, `tax_rate`, `params`, `created_time`, `pay_time`, `consign_time`, `modified_time`, `timeout_action_time`, `end_time`, `arguments`) VALUES (?, ?, ?, ?, '417', '商家杭州市65号仓', '77', '美妆个护', '洗发/护发', '假发 ', '2', '泰然直营1（自营店铺）', '345', '30334', '56573', '1001', '', '图片太吓人了！', '', '100.000', '100000.000', '0.000', '100.000', '100.000', '1', '0', '', '', '0', 'express', '', '', '', '0.000', '0.000', '0.000', '0.000', '0.000', '100.000', '100.000', '1.000', '100.000', 'WAIT_BUYER_PAY', '', '', '0.000', '0.000', 'https://image.trc.com/FnxE0Q9ngElDA7OmLKd8wirIHYkN', '', '', '1', '516', '测试供应商aaaaaa', '0.000', '', 'item', '0', '0.00000', '', '1511839453', '0', '0', '1511839453', '0', '0', '{\"good_type\": \"Domestic\", \"red_packet\": {\"amount\": 0}, \"product_type\": \"Normal\"}');";
        	String order="1711281124134510345";
            PreparedStatement pst_order = conn.prepareStatement(sql_order);   
            PreparedStatement pst_shop = conn.prepareStatement(sql_shop);   
            PreparedStatement pst_store = conn.prepareStatement(sql_store);   
            PreparedStatement pst_good = conn.prepareStatement(sql_good);   
            String head="17112811242";
            
            int a=1000000;
            for(int i=2000;i<1000000;i++){
            	//logger.info("进入循环");
            	String ss=head+(a+i);
            	pst_order.setString(1, ss);
            	
            	pst_shop.setString(1, ss);
            	pst_shop.setString(2, ss);
            	
            	pst_store.setString(1, ss);
            	pst_store.setString(2, ss);
            	pst_store.setString(3, ss);
            	
            	pst_good.setString(1, ss);
            	pst_good.setString(2, ss);
            	pst_good.setString(3, ss);
            	pst_good.setString(4, ss);
            	
            	pst_order.addBatch();
            	pst_shop.addBatch();
            	pst_store.addBatch();
            	pst_good.addBatch();
            	// logger.info("测试");
            }
            pst_order.executeBatch();
            pst_shop.executeBatch();
            pst_store.executeBatch();
            pst_good.executeBatch();
            logger.info("测试");
            conn.commit();
            logger.info("测试");
            
            Long beginTime1 = System.currentTimeMillis();  
            
            logger.info(beginTime1-beginTime);
            pst_order.close();
            pst_shop.close();
            pst_store.close();
            pst_good.close();
            conn.close();
            
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		
    	}
    	
		return false;
    	
    }
    

    
    
    
    public static boolean insertOrdersFast(Connection conn)throws Exception{
    	
    	try{
            System.out.println("start insert data");  
            Long beginTime = System.currentTimeMillis();  
            conn.setAutoCommit(false);  
            
            
        	String sql_order="INSERT INTO `trc_order`.`order` (`id`, `user_id`, `user_name`, `item_num`, `pay_type`, `payment`, `points_fee`, `total_fee`, `adjust_fee`, `post_fee`, `total_tax`, `need_invoice`, `invoice_name`, `invoice_type`, `invoice_main`, `receiver_state`, `receiver_city`, `receiver_district`, `receiver_address`, `receiver_zip`, `receiver_name`, `receiver_id_number`, `receiver_id_card_front`, `receiver_id_card_back`, `receiver_phone`, `receiver_mobile`, `buyer_area`, `ziti_memo`, `ziti_addr`, `anony`, `obtain_point_fee`, `real_point_fee`, `step_trade_status`, `step_paid_fee`, `is_clearing`, `cancel_reason`, `cancel_status`, `status`, `is_virtual`, `ip`, `type`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `shipping_type`, `platform`, `rate_status`, `coupon_code`, `group_buy_status`, `is_del`, `created_time`, `pay_time`, `consign_time`, `receive_time`, `modified_time`, `timeout_action_time`, `end_time`, `pay_bill_id`, `coupon_rule`, `coupon_type`, `buy_type`, `arguments`) VALUES";
        	// " (?, '345', '18758017446', '1', 'online', '100.000', '0.000', '100.000', '100.000', '0.000', '0.000', '0', '', '', '', '天津市', '和平区', '', '围雪昔施伍燥郡共淫磨炬桑管佃赣再崎奎岛甭榴帝随荧寄郴虽谷铃偶夸抬疲本常梦束倘警池', NULL, '江竟腹芳史瞧', NULL, NULL, NULL, '', '18758017446', '', '', '', '0', '0', '0', '', '0.000', '0', NULL, 'NO_APPLY_CANCEL', 'WAIT_BUYER_PAY', '0', '10.200.5.14', '0', '0.000', '0.000', '0.000', '0.000', 'express', 'pc', '0', '', 'NO_APPLY', '0', '1511839453', '0', '0', '0', '1511839453', '1515079453', '0', 'B1711281124132055RG0', '', '0', '0', '{\"red_packet\": {\"codes\": [], \"amount\": 0}}');";
        	String sql_shop="INSERT INTO `trc_order`.`shop_order` (`id`, `order_id`, `shop_id`, `shop_name`, `user_id`, `dlytmpl_ids`, `status`, `is_del`, `payment`, `total_fee`, `post_fee`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `title`, `buyer_message`, `adjust_fee`, `item_num`, `shop_memo`, `total_weight`, `trade_memo`, `rate_status`, `is_part_consign`, `group_buy_status`, `total_tax`, `created_time`, `consign_time`, `modified_time`, `arguments`) VALUES ";
        			// "(?, ?, '2', '泰然直营1（自营店铺）', '345', '516', 'WAIT_BUYER_PAY', '0', '100.000', '100.000', '0.000', '0.000', '0.000', '0.000', '0.000', '', '', '100.000', '1', '', '1.000', '', '0', '0', 'NO_APPLY', '0.000', '1511839453', '0', '1511839453', '{\"red_packet\": {\"amount\": 0}}');";
        	String sql_store="INSERT INTO `trc_order`.`repo_order` (`id`, `shop_order_id`, `order_id`, `shop_id`, `repo_id`, `user_id`, `adjust_fee`, `post_discount`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_promotion`, `discount_fee`, `total_fee`, `payment`, `created_time`, `arguments`) VALUES ";
        			//"(?, ?, ?, '2', '417', '345', '100.000', '0.000', '0.000', '0.000', '0.000', '0.000', '100.000', '100.000', '1511839453', '{\"red_packet\": {\"amount\": 0}}');";
        	String sql_good="INSERT INTO `trc_order`.`good_order` (`id`, `repo_order_id`, `shop_order_id`, `order_id`, `repo_id`, `repo_name`, `cat_id`, `cat_primary_name`, `cat_secondary_name`, `cat_tertiary_name`, `shop_id`, `shop_name`, `user_id`, `item_id`, `sku_id`, `art_no`, `barcode`, `title`, `spec_nature_info`, `price`, `market_price`, `promotion_price`, `customs_price`, `transaction_price`, `num`, `send_num`, `sku_properties_name`, `refund_id`, `is_oversold`, `shipping_type`, `bind_oid`, `logistics_company`, `invoice_no`, `post_discount`, `discount_promotion`, `discount_coupon_shop`, `discount_coupon_platform`, `discount_fee`, `total_fee`, `payment`, `total_weight`, `adjust_fee`, `status`, `after_sales_status`, `complaints_status`, `refund_fee`, `cat_service_rate`, `pic_path`, `outer_iid`, `outer_sku_id`, `sub_stock`, `dlytmpl_id`, `supplier_name`, `price_tax`, `promotion_tags`, `obj_type`, `type`, `tax_rate`, `params`, `created_time`, `pay_time`, `consign_time`, `modified_time`, `timeout_action_time`, `end_time`, `arguments`) VALUES ";
        			//+ "(?, ?, ?, ?, '417', '商家杭州市65号仓', '77', '美妆个护', '洗发/护发', '假发 ', '2', '泰然直营1（自营店铺）', '345', '30334', '56573', '1001', '', '图片太吓人了！', '', '100.000', '100000.000', '0.000', '100.000', '100.000', '1', '0', '', '', '0', 'express', '', '', '', '0.000', '0.000', '0.000', '0.000', '0.000', '100.000', '100.000', '1.000', '100.000', 'WAIT_BUYER_PAY', '', '', '0.000', '0.000', 'https://image.trc.com/FnxE0Q9ngElDA7OmLKd8wirIHYkN', '', '', '1', '516', '测试供应商aaaaaa', '0.000', '', 'item', '0', '0.00000', '', '1511839453', '0', '0', '1511839453', '0', '0', '{\"good_type\": \"Domestic\", \"red_packet\": {\"amount\": 0}, \"product_type\": \"Normal\"}');";
        	//String order="1711281124134510345";
            PreparedStatement pst_order = conn.prepareStatement("");   
            PreparedStatement pst_shop = conn.prepareStatement("");   
            PreparedStatement pst_store = conn.prepareStatement("");   
            PreparedStatement pst_good = conn.prepareStatement("");  
            
            int a=1000000;
            
            StringBuffer order= new StringBuffer();
            StringBuffer shop= new StringBuffer();
            StringBuffer store= new StringBuffer();
            StringBuffer good= new StringBuffer();
            
            String head="17112811242";
            for (int i = 201; i <= 300; i++) {  
                // 第次提交步长  
                for (int j = 1; j <= 10000; j++) {  
                	
                	String ss=head+(a+i*10000+j);
                    // 构建sql后缀  
                	order.append("('"+ss+"', '345', '18758017446', '1', 'online', '100.000', '0.000', '100.000', '100.000', '0.000', '0.000', '0', '', '', '', '天津市', '和平区', '', '围雪昔施伍燥郡共淫磨炬桑管佃赣再崎奎岛甭榴帝随荧寄郴虽谷铃偶夸抬疲本常梦束倘警池', NULL, '江竟腹芳史瞧', NULL, NULL, NULL, '', '18758017446', '', '', '', '0', '0', '0', '', '0.000', '0', NULL, 'NO_APPLY_CANCEL', 'WAIT_BUYER_PAY', '0', '10.200.5.14', '0', '0.000', '0.000', '0.000', '0.000', 'express', 'pc', '0', '', 'NO_APPLY', '0', '1511839453', '0', '0', '0', '1511839453', '1515079453', '0', 'B1711281124132055RG0', '', '0', '0', '{\"red_packet\": {\"codes\": [], \"amount\": 0}}'),");  
                	shop.append("('"+ss+"', '"+ss+"', '2', '泰然直营1（自营店铺）', '345', '516', 'WAIT_BUYER_PAY', '0', '100.000', '100.000', '0.000', '0.000', '0.000', '0.000', '0.000', '', '', '100.000', '1', '', '1.000', '', '0', '0', 'NO_APPLY', '0.000', '1511839453', '0', '1511839453', '{\"red_packet\": {\"amount\": 0}}'),");
                	store.append("('"+ss+"', '"+ss+"', '"+ss+"', '2', '417', '345', '100.000', '0.000', '0.000', '0.000', '0.000', '0.000', '100.000', '100.000', '1511839453', '{\"red_packet\": {\"amount\": 0}}'),");
                	good.append("('"+ss+"', "+ss+", '"+ss+"', '"+ss+"', '417', '商家杭州市65号仓', '77', '美妆个护', '洗发/护发', '假发 ', '2', '泰然直营1（自营店铺）', '345', '30334', '56573', '1001', '', '图片太吓人了！', '', '100.000', '100000.000', '0.000', '100.000', '100.000', '1', '0', '', '', '0', 'express', '', '', '', '0.000', '0.000', '0.000', '0.000', '0.000', '100.000', '100.000', '1.000', '100.000', 'WAIT_BUYER_PAY', '', '', '0.000', '0.000', 'https://image.trc.com/FnxE0Q9ngElDA7OmLKd8wirIHYkN', '', '', '1', '516', '测试供应商aaaaaa', '0.000', '', 'item', '0', '0.00000', '', '1511839453', '0', '0', '1511839453', '0', '0', '{\"good_type\": \"Domestic\", \"red_packet\": {\"amount\": 0}, \"product_type\": \"Normal\"}'),");
  
                    
                }  
                
              	pst_order.addBatch(sql_order+order.substring(0,order.length()-1));
            	pst_shop.addBatch(sql_shop+shop.substring(0,shop.length()-1));
            	pst_store.addBatch(sql_store+store.substring(0,store.length()-1));
            	pst_good.addBatch(sql_good+good.substring(0,good.length()-1));
            	
                pst_order.executeBatch();
                pst_shop.executeBatch();
                pst_store.executeBatch();
                pst_good.executeBatch();
                conn.commit();  
                 order= new StringBuffer();
                 shop= new StringBuffer();
                 store= new StringBuffer();
                 good= new StringBuffer();

            } 
            

            
            Long beginTime1 = System.currentTimeMillis();  
            logger.info(beginTime1-beginTime);
            pst_order.close();
            pst_shop.close();
            pst_store.close();
            pst_good.close();
            conn.close();
            
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		
    	}
    	
		return false;
    	
    }
    
    
    public static boolean CollectCSDNS( JSONObject temp) throws Exception{
    	Connection conn=getConnection("127.0.0.1", "root", "admin123", "csdnblogs");
    	PreparedStatement  mytest = conn.prepareStatement("");  	
    	String csdns="INSERT INTO `csdn_10000` VALUES (null, "+temp.getInt("rank")+", '"+temp.getString("urls").toString()+"', '"+temp.getString("rank_deatils").toString()+"', '"+temp.getString("static_deatil").toString()+"', '"+temp.getString("category1_deatil").toString()+"', '"+temp.getString("category2_deatil").toString()+"');";
    	int a=mytest.executeUpdate(csdns);
    	System.out.println(a);
    	 //  conn.commit();
    	//释放低级资源
    	mytest.close();
        conn.close();
    	return !(0==a);
    }
    
    
    public static int ifCSDNSexist( int aa) throws Exception{
    	Connection conn=getConnection("127.0.0.1", "root", "admin123", "csdnblogs");
    	PreparedStatement  mytest = conn.prepareStatement("");  	
    	String csdns="select count(*) as cnt from csdn_10000 where rank="+aa;
    	ResultSet result =mytest.executeQuery(csdns);
    	int id =0;
    	 while(result.next()){
             //rs.get+数据库中对应的类型+(数据库中对应的列别名)
              id = result.getInt("cnt");
    
         }
    	System.out.println(id);
    	 //  conn.commit();
    	//释放低级资源
    	mytest.close();
        conn.close();
    	return id;
    }
	

}
