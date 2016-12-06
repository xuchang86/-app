/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.model;

/**
 * 用户权限
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年12月6日 许畅 新建
 */
public enum Permission {

	/** 普通权限 */
	NORMAL(0),

	/** 发布权限 */
	PUBLISH(1),

	/** 所有权限 */
	ALL(2);

	private Integer value;

	private Permission(Integer value) {
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
