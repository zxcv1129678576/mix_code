package tools.EnvironmentUtil;
/** 
*@author  作者 : 陈达
*@date 创建时间：May 2, 2017 11:39:31 AM
*@version 1.0 
*@parameter  
*@return 
*/

public class 接口地址 {
	public static  user 用户=new user();
	public static  manage 管理平台=new manage();
	public static  shop 商家后台=new shop();
	public static class user{
		public static String 加入购物袋="cart-add.html";
		public static final String 登录 ="http://passport.trc.com/proxy/account/user/encrypt/new_login";
		public static String 购物袋下单="trade-init.html?mode=cart_buy";
		public static String 收货地址列表页="member-address.html";
		public static String 身份证列表页="member-idcard.html";
		public static String 购物袋下订单="trade-order-create.html";
	}
	
	public static class manage{
		public static  String  登录页面="shopadmin";
		public static  String	 登录="shopadmin?app=desktop&ctl=passport&act=login"; 
		public static  String	 发布特卖="shopadmin?app=syspromotion&ctl=admin_flashsale&act=save";
		public static  String	 平台优惠券="shopadmin?app=syspromotion&ctl=admin_coupon&act=save";
		public static  String	 优惠券礼包="shopadmin?app=syspromotion&ctl=admin_giftpackage&act=save";
		public static  String  获取特卖活动列表第一页="shopadmin?app=syspromotion&ctl=admin_flashsale&act=index&_finder%5Bfinder_id%5D=133f3a&page=1";
		public static  String  获取特卖报名列表地址="shopadmin?app=syspromotion&ctl=admin_flashsaleapply&act=index";
		public static  String  审核特卖="shopadmin?app=syspromotion&ctl=admin_flashsaleapply&act=saveApprove";
		public static  String  获取优惠券列表第一个="shopadmin?app=syspromotion&ctl=admin_coupon&act=index";
		public static  String  获取平台礼包报名第一个="shopadmin?app=syspromotion&ctl=admin_giftpackage&act=index";
		public static  String  获取礼包审核列表="shopadmin?app=syspromotion&ctl=admin_giftpackageapply&act=index";
		public static  String  获取优惠券审核列表="shopadmin?app=syspromotion&ctl=admin_couponapply&act=index";
		public static  String  审核礼包="shopadmin?app=syspromotion&ctl=admin_giftpackageapply&act=saveApprove";
		public static  String   审核优惠券="shopadmin?app=syspromotion&ctl=admin_couponapply&act=saveApprove"; 
		public static  String   拼团报名列表="shopadmin?app=syspromotion&ctl=admin_groupbuyapply&act=index";
		public static  String   添加微商城Banner="";
		public static  String   添加小泰良品Banner="";
		public static  String   手动配置微商城商品="";
		public static  String   添加红包="/shopadmin?app=syspromotion&ctl=admin_redPacket&act=save";
		
	}
	
	public static class shop{
		
		public static  String	 入口="shop";
		public static  String	 登录="shop/passport/signin.html"; 
		public static  String	 发布商品="shop/item/storeItem.html";
		public static  String	 发布商品页面="shop/item/add.html";
		public static  String	 上架商品="shop/item/setItemStatus.html";
		public static  String	 拉取直降商品="shop/get_direct_reduction_goods.html";
		public static  String	 拉取满减商品="shop/get_full_minus_goods.html";
		public static  String	 发布满减商品="shop/save_full_minus.html";
		public static  String	 发布直降="shop/save_direct_reduction.html";
		public static  String	 发布满折商品="shop/save_full_discount.html";
		public static  String	 拉取满折商品="shop/get_full_discount_goods.html";
		public static  String	 拉取拼团商品="shop/format-selected-goods.html?view_type=min_price";
		public static  String	 发布拼团="shop/save_groupbuy.html";
		public static  String	 商品列表页="shop/item/itemList.html";
		public static  String  发布优惠券="shop/save_coupon.html";
		public static  String  搜索商品地址="shop/item/search.html";
		public static  String 下架商品="shop/item/setItemStatus.html";
		public static  String  获取特卖商品="shop/get_flashsale_goods.html";
		public static  String  报名特卖活动="shop/save_flashsale_apply.html";
		public static  String   拉取平台优惠券商品="shop/format-selected-goods.html";
		public static  String   报名平台优惠券="shop/save_coupon_apply.html";
		public static  String   报名优惠券礼包="shop/save_giftpackage_apply.html";
		public static  String   拉取N元任选商品="shop/get_option_buy_goods.html";
		public static  String   发布N元任选活动="shop/save_option_buy.html";
		public static  String    获取秒杀商品信息="shop/seckill_item_list.html";
		public static  String    报名秒杀活动="shop/save_seckill.html";
		public static  String    发布免单券活动="/shop/save_free_single.html";
		
		
	}
}
