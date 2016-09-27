/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.model;

import com.xiaoyao.base.model.Person;

/**
 * 人物查询模型对象
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月27日 许畅 新建
 */
public class PersonQuery extends Person {

	/** 排序字段 */
	private String sortField;

	/** 排序类型 DESC or ASC */
	private String sortType;

	/** 徒弟数量 */
	private Integer childs;

	/**
	 * @return the sortField 排序字段
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * @param sortField
	 *            the sortField to set 排序字段
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	/**
	 * @return the sortType 排序类型 DESC or ASC
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * @param sortType
	 *            the sortType to set 排序类型 DESC or ASC
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * @return the childs 徒弟数量
	 */
	public Integer getChilds() {
		return childs;
	}

	/**
	 * @param childs
	 *            the childs to set 徒弟数量
	 */
	public void setChilds(Integer childs) {
		this.childs = childs;
	}

}
