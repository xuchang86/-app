/******************************************************************************
 * Copyright (C) 2016 ShenZhen Dream Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.home.model;

import java.io.Serializable;

/**
 * 任务明细
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月13日 许畅 新建
 */
public class TaskDetail implements Serializable {
	
	/** 默认序列 */
	private static final long serialVersionUID = 1L;

	/** 明细id */
	private String id;

	/** 明细名称 */
	private String name;

	/** 明细编码 */
	private String number;

	/** 任务类型 {@link com.xiaoyao.home.model.TaskType} */
	private String taskType;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the taskType
	 */
	public String getTaskType() {
		return taskType;
	}

	/**
	 * @param taskType
	 *            the taskType to set
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

}
