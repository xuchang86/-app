/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.pay.model;

/**
 * 交易状态
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年12月11日 许畅 新建
 */
public enum TransferState {

	/** 已提交 */
	SUBMIT("submit"),

	/** 审核通过 */
	PASS("pass"),

	/** 审核不通过 */
	UNPASS("unpass"),

	/** 已付款 */
	PAY("pay");

	/** 值 */
	private String value;

	/**
	 * 构造方法
	 * 
	 * @param value
	 */
	private TransferState(String value) {
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
