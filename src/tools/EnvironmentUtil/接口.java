package tools.EnvironmentUtil;
public class 接口 {
	private static String base = "http://view.trc.com/";

	public static user 用户 = new user();
	public static manage 管理平台 = new manage();
	public static shop 商家后台 = new shop();

	public static class user {
		public static String 单SKU="23302";
		public static String 多SKU="23303";
		public static String 加入购物袋=base+"cart-add.html";
		public static String 登录 = "http://passport.trc.com/proxy/account/user/encrypt/new_login";
		public static String 购物袋购买初始化=base+"trade-init.html?mode=cart_buy";
		public static String 收货地址列表页=base+"member-address.html";
		public static String 身份证列表页=base+"member-idcard.html";
		public static String 购物袋下订单=base+"trade-order-create.html";
		public static String 获取商品评价=base+"wapapi/rate.api?item_id=";
		public static String 获取商品SKU信息=base+"item-getSpecSku.html?item_id=";
		public static String 快速购买初始化=base+"trade-init.html?mode=fast_buy";
		public static String 收藏商品=base+"member_fav.html";
		public static String 取消收藏=base+"member-collectdel.html";
		public static String 添加身份证=base+"member-saveidcard.html";
		public static String 删除身份证=base+"member-deleteidcard.html";
		public static String 添加收货地址=base+"member-address.html";
		public static String 删除收货地址=base+"member-deladdr.html";
		public static String 创建收银台支付单=base+"create.html";
		//public static String 支付接口="https://pay.trc.com/api/v1/funds/pay/ecard";
		public static String 支付接口="https://pay.trc.com/api/v3/funds/pay/ecard";
//		https://pay.trc.com/api/v3/funds/pay/ecard 
		public static String 获取订单信息=base+"trade-detail.html?tid=";
		public static String 回调订单状态=base+"finish.html?payment_id=";
		public static String 确认收货=base+"confirm-buyer.html";
		public static String 取消订单=base+"cancel-buyer.html";
		public static String  申请售后=base+"member-aftersales-commit.html";
		public static String  评价订单=base+"wapapi/doCreateRate.api";
		public static String  订单评价数据初始化=base+"wapapi/createRate.api?tid=";
		
	}
	

	public static class manage {
		public static String 登录页面 = base + "shopadmin";
		public static String 登录 = base + "shopadmin?app=desktop&ctl=passport&act=login";
		public static String 发布特卖 = base + "shopadmin?app=syspromotion&ctl=admin_flashsale&act=save";
		public static String 平台优惠券 = base + "shopadmin?app=syspromotion&ctl=admin_coupon&act=save";
		public static String 优惠券礼包 = base + "shopadmin?app=syspromotion&ctl=admin_giftpackage&act=save";
		public static String 获取特卖活动列表第一页 = base
				+ "shopadmin?app=syspromotion&ctl=admin_flashsale&act=index&_finder%5Bfinder_id%5D=133f3a&page=1";
		public static String 获取特卖报名列表地址 = base + "shopadmin?app=syspromotion&ctl=admin_flashsaleapply&act=index";
		public static String 审核特卖 = base + "shopadmin?app=syspromotion&ctl=admin_flashsaleapply&act=saveApprove";
	}

	public static class shop {

		public static String 入口 = base + "shop";
		public static String 登录 = base + "shop/passport/signin.html";
		public static String 发布商品 = base + "shop/item/storeItem.html";
		public static String 发布商品页面 = base + "shop/item/add.html";
		public static String 上架商品 = base + "shop/item/setItemStatus.html";
		public static String 拉取直降商品 = base + "shop/get_direct_reduction_goods.html";
		public static String 拉取满减商品 = base + "shop/get_full_minus_goods.html";
		public static String 发布满减商品 = base + "shop/save_full_minus.html";
		public static String 发布直降 = base + "shop/save_direct_reduction.html";
		public static String 发布满折商品 = base + "shop/save_full_discount.html";
		public static String 拉取满折商品 = base + "shop/get_full_discount_goods.html";
		public static String 拉取拼团商品 = base + "shop/format-selected-goods.html?view_type=min_price";
		public static String 发布拼团 = base + "shop/save_groupbuy.html";
		public static String 商品列表页 = base + "shop/item/itemList.html";
		public static String 发布优惠券 = base + "shop/save_coupon.html";
		public static String 搜索商品地址 = base + "shop/item/search.html";
		public static String 下架商品 = base + "shop/item/setItemStatus.html";
		public static String 获取特卖商品 = base + "shop/get_flashsale_goods.html";
		public static String 报名特卖活动 = base + "shop/save_flashsale_apply.html";
		public static String 拉取发货包裹信息=base+"shop/trade/packgeItems.html?tid=";
		public static String  拉取包裹页面=base+"shop/delivery.html?tid=";
		public static String  初始化包裹=base+"trcapi/v1/multiPackage/packages?tid=";
		public static String  查询店铺订单编号=base+"shop/trade/search.html";
		public static String   发货=base+"shop/trade/dopackage.html";
	}
}