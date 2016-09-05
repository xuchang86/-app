/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.model;

/**
 * 性别枚举
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月21日 许畅 新建
 */
public enum Sex {

	/** 男性 */
	MAN(0),
	/** 女性 */
	WOMAN(1);

	/** 性别值 */
	private Integer value;

	private Sex(Integer value) {
		this.setValue(value);
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

}
