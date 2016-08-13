/******************************************************************************
 * Copyright (C) 2016 ShenZhen Dream Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.home.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 个人中心
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月13日 许畅 新建
 */
public class Person implements Serializable {

	/** 默认序列 */
	private static final long serialVersionUID = 1L;

	/** id */
	private String id;

	/** 上级id */
	private String parentId;

	/** 姓名 */
	private String name;

	/** 编码 */
	private String number;

	/** 头像编码 */
	private String heaPortrait;

	/** 性别 */
	private int sex;

	/** 所在城市 */
	private String cityId;

	/** 职务 */
	private String job;

	/** 逍遥币 */
	private BigDecimal money;

	/** 弟子 */
	private List<Person> brothers;

	/** 会员等级(总共18级) */
	private int level;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the heaPortrait
	 */
	public String getHeaPortrait() {
		return heaPortrait;
	}

	/**
	 * @param heaPortrait
	 *            the heaPortrait to set
	 */
	public void setHeaPortrait(String heaPortrait) {
		this.heaPortrait = heaPortrait;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @return the brothers
	 */
	public List<Person> getBrothers() {
		return brothers;
	}

	/**
	 * @param brothers
	 *            the brothers to set
	 */
	public void setBrothers(List<Person> brothers) {
		this.brothers = brothers;
	}

	/**
	 * @return the level 会员等级 满级18级
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set 会员等级
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
