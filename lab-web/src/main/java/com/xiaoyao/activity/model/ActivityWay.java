/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.model;

/**
 * 活动方式
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年11月8日 许畅 新建
 */
public enum ActivityWay {

	/** 打听 */
	ASK("ask_about"),

	/** 兼职 */
	PART_TIME("part_time"),

	/** 其他 */
	OTHER("other");

	/** 值 */
	private String value;

	/**
	 * 构造方法
	 * 
	 * @param value
	 */
	private ActivityWay(String value) {
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
