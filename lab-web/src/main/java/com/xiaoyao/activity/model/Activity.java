package com.xiaoyao.activity.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaoyao.base.model.BaseVO;
import com.xiaoyao.login.model.User;

public class Activity extends BaseVO {

	private Integer id;

	/** 活动类型 {@link com.xiaoyao.activity.model.ActivityType} */
	private String type;

	/** 活动方式 {@link com.xiaoyao.activity.model.ActivityWay} */
	private String way;

	/** 付款方式 {@link com.xiaoyao.activity.model.ActivityPayWay} */
	private String payway;

	private String address;

	private String content;

	private Date date;

	private Integer personId;

	/** 用户信息 */
	private User user;

	private BigDecimal cost;

	private String city;

	/** 活动参与人 */
	private List<ActivityPerson> activityPerson = new ArrayList<ActivityPerson>();

	/** 发布的图片 */
	private String[] urls;

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
	 * @return the activityPerson
	 */
	public List<ActivityPerson> getActivityPerson() {
		return activityPerson;
	}

	/**
	 * @param activityPerson
	 *            the activityPerson to set
	 */
	public void setActivityPerson(List<ActivityPerson> activityPerson) {
		this.activityPerson = activityPerson;
	}

	/**
	 * @return the urls
	 */
	public String[] getUrls() {
		return urls;
	}

	/**
	 * @param urls
	 *            the urls to set
	 */
	public void setUrls(String[] urls) {
		this.urls = urls;
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the way
	 */
	public String getWay() {
		return way;
	}

	/**
	 * @param way
	 *            the way to set
	 */
	public void setWay(String way) {
		this.way = way;
	}

	/**
	 * @return the payway
	 */
	public String getPayway() {
		return payway;
	}

	/**
	 * @param payway
	 *            the payway to set
	 */
	public void setPayway(String payway) {
		this.payway = payway;
	}
}