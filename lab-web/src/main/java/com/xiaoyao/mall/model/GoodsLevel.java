/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.mall.model;

/**
 * 商品等级
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年11月8日 许畅 新建
 */
public enum GoodsLevel {

	/** 正常 */
	NORMAL(0),

	/** 精品 */
	NICE(1),

	/** 完美 */
	PERFECT(2),

	/** 差 */
	BAD(3);

	/** 值 */
	private int value;

	/**
	 * 构造方法
	 * 
	 * @param value
	 */
	private GoodsLevel(int value) {
		this.setValue(value);
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
