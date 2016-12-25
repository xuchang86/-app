package com.xiaoyao.activity.model;

import java.math.BigDecimal;
import java.util.Date;

import com.xiaoyao.base.model.BaseVO;

public class ActivityQuery extends BaseVO {

	private Integer id;

	/** 活动类型 {@link com.xiaoyao.activity.model.ActivityType} */
	private String type;

	private String address;

	private String content;

	private Date date;

	private Integer personId;

	private BigDecimal cost;

	private String city;

	/** 活动参与人id */
	private int joinedId;

	/** 参与活动状态 */
	private String state;

	/** 发布的图片 */
	private String urls;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the urls
	 */
	public String getUrls() {
		return urls;
	}

	/**
	 * @param urls
	 *            the urls to set
	 */
	public void setUrls(String urls) {
		this.urls = urls;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the joinedId
	 */
	public int getJoinedId() {
		return joinedId;
	}

	/**
	 * @param joinedId
	 *            the joinedId to set
	 */
	public void setJoinedId(int joinedId) {
		this.joinedId = joinedId;
	}
}