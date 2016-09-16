/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.model;

/**
 * 基础VO模型
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月16日 许畅 新建
 */
public abstract class BaseVO {

	/** 主键id */
	private Integer _id;

	/** 每页显示个数 */
	private Integer pageSize;

	/** 页码数 */
	private Integer pageNo;

	/**
	 * @return the pageSize 每页显示个数
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set 每页显示个数
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageNo 页码数
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set 页码数
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the _id
	 */
	public Integer get_id() {
		return _id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public void set_id(Integer _id) {
		this._id = _id;
	}

}
