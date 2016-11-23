package com.xiaoyao.mall.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaoyao.base.model.BaseVO;

/**
 * 商城商品
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月12日 许畅 新建
 */
public class Goods extends BaseVO {

	/** 商品主键 */
	private Integer id;

	/** 商品名称 */
	private String name;

	/** 商品描述 */
	private String description;

	/** 商品编码 */
	private String number;

	/** 商品类别id */
	private Integer typeId;

	/** 商品价格 */
	private BigDecimal price;

	/** 用户id */
	private Integer userId;

	/** 创建时间 */
	private Date createDate;

	/** 商品图片url(如果多个图片以逗号隔开) */
	private String url;

	/** 商品所在地 */
	private String area;

	/** 商品类别 */
	private Type type;

	/** 是否出售 */
	private Boolean isSale;

	/** 卖家id */
	private Integer sellerId;

	/** 卖家 */
	private Seller seller;

	/** 会员价格 */
	private BigDecimal vipPrice;

	/** 商品等级 {@link com.xiaoyao.mall.model.GoodsLevel} */
	private Integer level;
	
	/** 商品型号 */
	private String model;

	/** 商品评论 */
	private List<Comment> comments = new ArrayList<Comment>();

	/** 商品订单 */
	private List<GoodsOrder> orders = new ArrayList<GoodsOrder>();

	/**
	 * @return the vipPrice
	 */
	public BigDecimal getVipPrice() {
		return vipPrice;
	}

	/**
	 * @param vipPrice
	 *            the vipPrice to set
	 */
	public void setVipPrice(BigDecimal vipPrice) {
		this.vipPrice = vipPrice;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the type 商品类型
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set 商品类型
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the comments 商品评论
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set 商品评论
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the orders 商品订单
	 */
	public List<GoodsOrder> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set 商品订单
	 */
	public void setOrders(List<GoodsOrder> orders) {
		this.orders = orders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number == null ? null : number.trim();
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 商品图片 ,以逗号隔开 */
	public String getUrl() {
		return url;
	}

	/** 商品图片 ,以逗号隔开 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * @return the area 商品所在地
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set 商品所在地
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the isSale
	 */
	public Boolean getIsSale() {
		return isSale;
	}

	/**
	 * @param isSale
	 *            the isSale to set
	 */
	public void setIsSale(Boolean isSale) {
		this.isSale = isSale;
	}

	/**
	 * @return the sellerId
	 */
	public Integer getSellerId() {
		return sellerId;
	}

	/**
	 * @param sellerId
	 *            the sellerId to set
	 */
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * @return the seller 卖家信息
	 */
	public Seller getSeller() {
		return seller;
	}

	/**
	 * @param seller
	 *            the seller to set 卖家信息
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
}