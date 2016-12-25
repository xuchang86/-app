/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.model;

/**
 * 任务状态
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年12月25日 许畅 新建
 */
public enum TaskState {

	/** 未开始 */
	UNDO("undo", 1),

	/** 进行中 */
	DOING("doing", 2),

	/** 已完成 */
	END("end", 3);

	/** 名称 */
	private String name;

	/** 值 */
	private int value;

	/**
	 * 构造方法
	 * 
	 * @param name
	 * @param value
	 */
	private TaskState(String name, int value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
