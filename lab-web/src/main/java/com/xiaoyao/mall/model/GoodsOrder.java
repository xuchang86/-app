package com.xiaoyao.mall.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsOrder {
	private Integer id;

	private String number;

	private Date createDate;

	private Date payDate;

	private Integer userId;

	/** 物品ids */
	private String goodsId;

	private String address;

	private String contacts;

	private String phone;

	/** 订单状态 {@link com.xiaoyao.mall.model.State} */
	private String state;

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
}