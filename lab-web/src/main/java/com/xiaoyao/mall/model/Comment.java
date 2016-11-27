package com.xiaoyao.mall.model;

import java.util.Date;

import com.xiaoyao.base.model.BaseVO;
import com.xiaoyao.login.model.User;

/**
 * 商品评论信息
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年11月27日 许畅 新建
 */
public class Comment extends BaseVO {
	private Integer id;

	private Integer userId;

	/** 用户信息 */
	private User user;

	private Date createDate;

	private String content;

	private Integer goodsId;

	private Integer orderId;

	/** 评分 */
	private Integer score;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}