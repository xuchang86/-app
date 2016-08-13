/******************************************************************************
 * Copyright (C) 2016 ShenZhen Dream Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.home.model;

import java.io.Serializable;
import java.util.List;

/**
 * 任务信息
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月13日 许畅 新建
 */
public class Task implements Serializable {

	/** 默认序列 */
	private static final long serialVersionUID = 1L;

	/** 任务id */
	private String id;

	/** 任务类型 {@link com.xiaoyao.home.model.TaskType} */
	private String taskType;

	/**
	 * 任务明细 (如
	 * 出售服务任型分为：兼职，提供资源，招待，陪游，提供住宿，谋划，跑腿，排队，陪吃饭，陪看电影，等等，可自己填写任务，选择好出售服务的价格。)
	 */
	private List<TaskDetail> taskDetails;

	/**
	 * 发布形式 (发布任务或者服务可以以文字，图片，视频的方式。)
	 * {@link com.xiaoyao.home.model.PublishWay.PublishWay}
	 */
	private String publishWay;

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

	/**
	 * @return the taskDetails
	 */
	public List<TaskDetail> getTaskDetails() {
		return taskDetails;
	}

	/**
	 * @param taskDetails
	 *            the taskDetails to set
	 */
	public void setTaskDetails(List<TaskDetail> taskDetails) {
		this.taskDetails = taskDetails;
	}

	/**
	 * @return the publishWay
	 */
	public String getPublishWay() {
		return publishWay;
	}

	/**
	 * @param publishWay
	 *            the publishWay to set
	 */
	public void setPublishWay(String publishWay) {
		this.publishWay = publishWay;
	}

}
