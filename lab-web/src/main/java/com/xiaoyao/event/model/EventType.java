/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.event.model;

/**
 * 事件类别
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年10月23日 许畅 新建
 */
public enum EventType {

	/** 系统事件 */
	SYSTEM("system"),

	/** 门派事件 */
	SCHOOL("school"),

	/** 其他事件 */
	OTHER("other");

	/** String值 */
	private String value;

	/**
	 * 构造方法
	 * 
	 * @param value
	 */
	private EventType(String value) {
		this.setValue(value);
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
