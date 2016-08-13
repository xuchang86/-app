/******************************************************************************
 * Copyright (C) 2016 ShenZhen Dream Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.home.model;

import java.io.Serializable;

/**
 * 酒馆所属城市
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月13日 许畅 新建
 */
public class City implements Serializable {

	/** 默认序列号 */
	private static final long serialVersionUID = 1L;

	/** 默认系列号 */
	private String id;

	/** 省份 */
	private String province;

	/** 城市名称 */
	private String name;

	/** 城市编码 */
	private String number;

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
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
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

}
