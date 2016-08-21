/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xiaoyao.login.dao.UserMapperExt;
import com.xiaoyao.login.model.PayWay;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.model.UserExample;

/**
 * 用户登录服务
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月18日 许畅 新建
 */
@Service
public class UserLoginService {

	/** 注入UserMapper */
	@Autowired
	private UserMapperExt userMapperExt;

	/** 注入InviteCodeService */
	@Autowired
	private InviteCodeService inviteCodeService;

	/**
	 * 校验当前用户是否已注册
	 * 
	 * @param user
	 *            注册用户
	 * @return
	 */
	public boolean verifyRegist(User user) {
		List<User> lst = userMapperExt.verifyRegist(user);
		return CollectionUtils.isEmpty(lst) ? false : true;
	}

	/**
	 * 付款并保存User并删除验证码
	 * 
	 * @param user
	 *            用户信息
	 * @param code
	 *            邀请码
	 * @param payWay
	 *            付款方式
	 * @param payAmount
	 *            付款金额
	 * @return
	 */
	public Map<String, String> payAndSaveUser(User user, String code,
			String payWay, String payAmount) {
		Map<String, String> result = new HashMap<String, String>();

		if (PayWay.WECHART.getValue().equals(payWay)) {// 微信支付
			if (!_wechartpay(payAmount)) {
				result.put("error", "微信支付失败.");
				return result;
			}
		} else if (PayWay.ALIPAY.getValue().equals(payWay)) {// 支付宝支付
			if (!_alipay(payAmount)) {
				result.put("error", "支付宝支付失败.");
				return result;
			}
		} else {
			result.put("error", "当前支付方式不支持,请选择其他支付方式.");
			return result;
		}
		// 保存用户
		int success = userMapperExt.insertSelective(user);
		if (success != 1) {
			result.put("error", "保存用户失败.");
			// 回滚付款金额
			return result;
		}
		// 删除邀请码
		inviteCodeService.deleteByCode(code);
		return result;
	}

	/**
	 * 更新User信息
	 * 
	 * @param user
	 *            user
	 * @return
	 */
	public boolean updateUser(User user) {
		UserExample example = new UserExample();
		example.or().andPhoneEqualTo(user.getPhone());
		int result = userMapperExt.updateByExampleSelective(user, example);
		System.out.println("更新结果集:" + result);
		return result == 1 ? true : false;
	}

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public List<User> login(String username, String password) {
		// 查询是否已注册付款
		User user = new User();
		user.setUsername(username);
		user.setPhone(username);
		user.setPassword(password);
		return userMapperExt.login(user);
	}

	/**
	 * 微信支付
	 * 
	 * @param payAmount
	 * @return
	 */
	protected boolean _wechartpay(String payAmount) {
		return true;
	}

	/**
	 * 支付宝支付
	 * 
	 * @param payAmount
	 * @return
	 */
	protected boolean _alipay(String payAmount) {
		return true;
	}
}
