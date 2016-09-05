/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.upload.model;

/**
 * 文件类型
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月1日 许畅 新建
 */
public enum FileType {

	/** 个人头像使用 */
	USER("user"),

	/** 发布活动的图片 */
	ACTIVITY("activity"),

	/** 其他用处图片 */
	OTHER("other");

	private String value;

	/**
	 * 构造方法
	 * 
	 * @param value
	 */
	private FileType(String value) {
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
