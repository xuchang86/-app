/******************************************************************************
 * Copyright (C) 2016 ShenZhen Dream Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.home.model;

/**
 * 任务或者服务发布方式枚举
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月13日 许畅 新建
 */
public enum PublishWay {

	/** 文字形式 */
	CHARACTER("character"),

	/** 图片形式 */
	IMAGE("image"),

	/** 视频形式 */
	VIDEO("video");

	private String value;

	private PublishWay(String value) {
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
