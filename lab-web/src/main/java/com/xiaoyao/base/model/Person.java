package com.xiaoyao.base.model;

import java.math.BigDecimal;
import java.util.Date;

public class Person {
	private Integer id;

	private Integer userId;

	/** {@link com.xiaoyao.base.model.Level} */
	private Integer level;

	private BigDecimal bill;

	private Integer parentId;

	private Date createDate;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public BigDecimal getBill() {
		return bill;
	}

	public void setBill(BigDecimal bill) {
		this.bill = bill;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}