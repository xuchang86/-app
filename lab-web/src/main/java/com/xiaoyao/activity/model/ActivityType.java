/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.model;

/**
 * 活动类型
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
public enum ActivityType {

	/** 门派活动 */
	SCHOOL_ACTIVITY("school_activity"),

	/** 悬赏任务 */
	REWARD_TASK("reward_task"),

	/** 出售服务 */
	SALE_SERVICE("sale_service");

	private String value;

	private ActivityType(String value) {
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
