/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.model;

/**
 * 支付方式
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月20日 许畅 新建
 */
public enum PayWay {

	/** 微信支付 */
	WECHART("wechart"),

	/** 支付宝 */
	ALIPAY("alipay"),

	/** 其他方式 */
	OTHER("other");

	private String value;

	private PayWay(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
