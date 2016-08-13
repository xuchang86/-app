/******************************************************************************
 * Copyright (C) 2016 ShenZhen Dream Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.home.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 门派消息通知和事件通知
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月13日 许畅 新建
 */
public class Message implements Serializable {

	/** 默认序列号 */
	private static final long serialVersionUID = 1L;

	/** id */
	private String id;

	/** 消息内容 */
	private String content;

	/** 消息发送时间 */
	private Timestamp date;

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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

}
