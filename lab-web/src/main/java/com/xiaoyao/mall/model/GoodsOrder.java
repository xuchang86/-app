package com.xiaoyao.mall.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsOrder {
	private Integer id;

	private String number;

	/** 创建日期 */
	private Date createDate;

	/** 付款日期 */
	private Date payDate;

	private Integer userId;

	/** 物品ids */
	private String goodsId;

	/** 详细地址 */
	private String address;

	/** 联系人 */
	private String contacts;

	/** 联系电话 */
	private String phone;

	/** 订单状态 {@link com.xiaoyao.mall.model.State} */
	private String state;

	/** 商品型号 */
	private String goodsModel;

	/** 收货地址id */
	private Integer addressId;

	/** 地址对象 */
	private Address addressVO;

	/** 订单金额 */
	private BigDecimal amount;

	/** 订单物品 */
	private List<Goods> goods = new ArrayList<Goods>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number == null ? null : number.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts == null ? null : contacts.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	/**
	 * @return the goods
	 */
	public List<Goods> getGoods() {
		return goods;
	}

	/**
	 * @param goods
	 *            the goods to set
	 */
	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	/**
	 * @return the goodsId 物品ids
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId
	 *            the goodsId to set 物品ids
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return the goodsModel
	 */
	public String getGoodsModel() {
		return goodsModel;
	}

	/**
	 * @param goodsModel
	 *            the goodsModel to set
	 */
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	/**
	 * @return the addressId
	 */
	public Integer getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the addressVO
	 */
	public Address getAddressVO() {
		return addressVO;
	}

	/**
	 * @param addressVO
	 *            the addressVO to set
	 */
	public void setAddressVO(Address addressVO) {
		this.addressVO = addressVO;
	}
}