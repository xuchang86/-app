/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.pay.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.login.model.IsPay;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.service.PersonManageService;
import com.xiaoyao.login.service.UserLoginService;
import com.xiaoyao.login.util.LoginUtil;
import com.xiaoyao.pay.dao.OrderMapper;
import com.xiaoyao.pay.model.Order;

/**
 * 付款回调业务服务
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
@Service
public class PayService extends BaseService<Order> {

	/** 注入OrderMapper */
	@Autowired
	private OrderMapper orderMapper;

	/** 注入 UserLoginService */
	@Autowired
	private UserLoginService userLoginService;

	/** 注入 PersonManageService */
	@Autowired
	private PersonManageService personManageService;

	/**
	 * 保存订单信息
	 * 
	 * @param order
	 * @return
	 */
	public boolean saveOrder(Order order) {
		return wrapperReturnVal(orderMapper.insertSelective(order));
	}

	/**
	 * 支付宝注册业务回调
	 * 
	 * @param out_trade_no
	 * @param userId
	 * @param inviteCode
	 */
	public void notifyCallback(String out_trade_no, String userId,
			String inviteCode) {
		// 新增订单信息
		Order order = new Order();
		order.setOrderCode(out_trade_no);
		order.setPayDate(new Date());
		order.setPayAmount(new BigDecimal(LoginUtil.getRegistAmount()));
		order.setUserId(Integer.valueOf(userId));
		this.saveOrder(order);
		// 更新已付款
		this.updateIsPay(userId);
		// 付款并保存person信息反写金额等业务操作
		User user = userLoginService.queryUserByPrimaryKey(userId);
		userLoginService.saveUser(user, inviteCode);
	}

	/**
	 * 支付宝充值回调
	 * 
	 * @param out_trade_no
	 * @param userId
	 * @param amount
	 */
	public void rechargeCallback(String out_trade_no, String userId,
			String amount) {
		Order order = new Order();
		order.setOrderCode(out_trade_no);
		order.setPayDate(new Date());
		order.setPayAmount(new BigDecimal(amount));
		order.setUserId(Integer.valueOf(userId));
		this.saveOrder(order);
		personManageService.rechargeBill(Integer.parseInt(userId), amount);
	}

	/**
	 * 更新已付款
	 * 
	 * @param userId
	 */
	private void updateIsPay(String userId) {
		User user = new User();
		user.setIspay(IsPay.IS_PAY.getValue());
		user.setId(Integer.valueOf(userId));
		userLoginService.updateByByPrimaryKey(user);
	}

}
