/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.model;

import com.xiaoyao.base.mybatis.Page;

/**
 * 基础VO模型
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月16日 许畅 新建
 */
public abstract class BaseVO {

	/** 每页显示个数 */
	private Integer pageSize;

	/** 页码数 */
	private Integer pageNo;

	/** 分页对象 */
	private Page page = new Page();

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
		page.setPageSize(pageSize);
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
		page.setCurrentPage(pageNo);
	}

	/**
	 * @return the page 分页对象
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set 分页对象
	 */
	public void setPage(Page page) {
		this.page = page;
	}

}
