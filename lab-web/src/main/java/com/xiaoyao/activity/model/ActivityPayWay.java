/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.model;

/**
 * 活动付款方式
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年11月8日 许畅 新建
 */
public enum ActivityPayWay {

	/** AA付款 */
	AA("aa"),

	/** 男A女免费 */
	MAN_A_WOMAN_FREE("man_a_woman_free"),

	/** 女A男免费 */
	WOMAN_A_MAN_FREE("woman_a_man_free"),

	/** 全部免费 */
	ALL_FREE("all_free");

	/** 值 */
	private String value;

	/**
	 * 构造方法
	 * 
	 * @param value
	 */
	private ActivityPayWay(String value) {
		setValue(value);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
