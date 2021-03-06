package com.group.lab.model;


public class FashionTaobao {
	private static final long serialVersionUID = 6341259527162494935L;

	//商品叶子类目
	private Long cid;

	//是否淘客商品
	private Boolean istk;

	private Long juEnd;

	private Boolean juItem;

	private Long juPrice;

	private Long juStart;

	//是否天猫宝贝. true 是, false 不是
	private Boolean mall;

	//卖家nick
	private String nick;

	//库存数量
	private Long num;

	//混淆的商品ID(准备废弃，由open_iid代替)
	private String openAuctionIid;

	//商品混淆ID
	private String openIid;

	//主图链接
	private String picUrl;

	//平邮邮费. 单位:元,精确到分
	private String postFee;

	//商品优惠价格(PC端),可能为空. 单位:元,精确到分。当PC端访问,且当前时间落在price_start_time到price_end_time区间内时使用该价格
	private String price;

	//PC端商品优惠价格开始时间。如果当前没有PC端优惠，该字段为空
	private String priceEndTime;

	//PC端商品优惠价格结束时间。如果当前没有PC端优惠，该字段为空
	private String priceStartTime;

	//手机端商品优惠价格. 可能为空。单位:元,精确到分。当手机端访问且当前时间落在price_wap_start_time到price_wap_end_time之间的话，使用该价格。如果改价格为空，请使用reserve_price.
	private String priceWap;

	//手机端商品优惠价格开始时间。如果当前没有手机端优惠，该字段为空
	private String priceWapEndTime;

	//手机端商品优惠价格结束时间。如果当前没有手机端优惠，该字段为空
	private String priceWapStartTime;

	//消保类型，多个类型以,分割。可取以下值： 2：假一赔三；4：7天无理由退换货；
	private String promotedService;

	//商品的一口价
	private String reservePrice;

	//店铺名称
	private String shopName;

	//商品标题
	private String title;

	//淘客佣金比例，比如：750 表示 7.50%
	private String tkRate;

	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Boolean getIstk() {
		return this.istk;
	}

	public void setIstk(Boolean istk) {
		this.istk = istk;
	}

	public Long getJuEnd() {
		return this.juEnd;
	}

	public void setJuEnd(Long juEnd) {
		this.juEnd = juEnd;
	}

	public Boolean getJuItem() {
		return this.juItem;
	}

	public void setJuItem(Boolean juItem) {
		this.juItem = juItem;
	}

	public Long getJuPrice() {
		return this.juPrice;
	}

	public void setJuPrice(Long juPrice) {
		this.juPrice = juPrice;
	}

	public Long getJuStart() {
		return this.juStart;
	}

	public void setJuStart(Long juStart) {
		this.juStart = juStart;
	}

	public Boolean getMall() {
		return this.mall;
	}

	public void setMall(Boolean mall) {
		this.mall = mall;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getOpenAuctionIid() {
		return this.openAuctionIid;
	}

	public void setOpenAuctionIid(String openAuctionIid) {
		this.openAuctionIid = openAuctionIid;
	}

	public String getOpenIid() {
		return this.openIid;
	}

	public void setOpenIid(String openIid) {
		this.openIid = openIid;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPostFee() {
		return this.postFee;
	}

	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceEndTime() {
		return this.priceEndTime;
	}

	public void setPriceEndTime(String priceEndTime) {
		this.priceEndTime = priceEndTime;
	}

	public String getPriceStartTime() {
		return this.priceStartTime;
	}

	public void setPriceStartTime(String priceStartTime) {
		this.priceStartTime = priceStartTime;
	}

	public String getPriceWap() {
		return this.priceWap;
	}

	public void setPriceWap(String priceWap) {
		this.priceWap = priceWap;
	}

	public String getPriceWapEndTime() {
		return this.priceWapEndTime;
	}

	public void setPriceWapEndTime(String priceWapEndTime) {
		this.priceWapEndTime = priceWapEndTime;
	}

	public String getPriceWapStartTime() {
		return this.priceWapStartTime;
	}

	public void setPriceWapStartTime(String priceWapStartTime) {
		this.priceWapStartTime = priceWapStartTime;
	}

	public String getPromotedService() {
		return this.promotedService;
	}

	public void setPromotedService(String promotedService) {
		this.promotedService = promotedService;
	}

	public String getReservePrice() {
		return this.reservePrice;
	}

	public void setReservePrice(String reservePrice) {
		this.reservePrice = reservePrice;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTkRate() {
		return this.tkRate;
	}

	public void setTkRate(String tkRate) {
		this.tkRate = tkRate;
	}
}
