/*************************(*****************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.util.BeanUtils;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.base.util.MD5Util;
import com.xiaoyao.login.model.IsPay;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.service.InviteCodeService;
import com.xiaoyao.login.service.UserLoginService;
import com.xiaoyao.login.util.LoginUtil;

/**
 * 逍遥派用户登录Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月17日 许畅 新建
 */
@Controller
@RequestMapping(value = "/xiaoyao")
public class UserLoginController extends BizBaseController {

	/** 注入 UserLoginService */
	@Autowired
	private UserLoginService userLoginService;

	/** 注入 inviteCodeService */
	@Autowired
	private InviteCodeService inviteCodeService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request(request, "username");// 用户名或者手机号
		String password = request(request, "password");// 密码
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("username", "用户名或者手机号不能为空.");
		validateResult.put("password", "密码不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		List<User> users = userLoginService.login(username, password);
		if (CollectionUtils.isEmpty(users)) {
			JSONUtils.ERROR(response, "当前用户没有注册,请先注册.");
		} else {
			// 未付款不能登录
			User user = users.get(0);
			if (IsPay.UN_PAY.getValue().equals(user.getIspay())) {
				JSONUtils.ERROR(response, "当前用户注册时未付款,不能登录.");
			} else {
				// 将当前用户信息放入session中
				setCurrentUser(request, user);
				JSONUtils.SUCCESS(response, "登录成功.");
				System.out.println("用户:" + user.getName() + " login in...");
			}
		}
	}

	/**
	 * 用户注册(先注册 再付款 最后完善个人信息)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("register")
	public void register(HttpServletRequest request,
			HttpServletResponse response) {
		// 手机号,密码,验证码
		Map<String, String> params = parseParams(request, "register",
				new String[] { "phone", "password", "code" });
		String phone = params.get("phone");
		String password = params.get("password");
		String code = params.get("code");

		// 校验参数是否为空
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("phone", "手机号不能为空.");
		validateResult.put("password", "密码不能为空.");
		validateResult.put("code", "验证码不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		// 校验是否已注册
		if (isRegist(phone)) {
			JSONUtils.ERROR(response, "当前用户已注册,不能重复注册.");
			return;
		}

		Object sessionCode = request.getSession().getAttribute("code");
		if (sessionCode == null) {
			JSONUtils.ERROR(response, "验证码已过期,请重新发送验证码.");
			return;
		}
		if (String.valueOf(sessionCode).equals(code)) {
			JSONUtils.SUCCESS(response, "第一步验证成功.");
			User user = new User();
			user.setPhone(phone);
			user.setPassword(password);
			request.getSession().setAttribute("user", user);
		} else {
			JSONUtils.PARAM_ERROR(response, "验证码不匹配请重新输入验证码.");
		}
	}

	/**
	 * 付款操作(先注册 再付款 最后完善个人信息)
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("payment")
	public void payment(HttpServletRequest request, HttpServletResponse response) {
		String payAmount = request(request, "payAmount");// 付款金额
		String inviteCode = request(request, "inviteCode");// 邀请码
		String payWay = request(request, "payWay");// 付款方式
		// 校验参数是否为空
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("payAmount", "付款金额不能为空.");
		validateResult.put("inviteCode", "邀请码不能为空.");
		validateResult.put("payWay", "付款方式不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;
		// 校验邀请码是否有效
		if (!inviteCodeService.isExsit(inviteCode)) {
			JSONUtils.ERROR(response, "当前邀请码不存在,请检查邀请码的正确性.");
			return;
		}
		// 付款并保存用户信息并删除验证码
		User user = getCurrentUser(request);
		user.setIspay(1);
		Object code = request.getSession().getAttribute("code");
		Map<String, String> result = userLoginService.payAndSaveUser(user,
				String.valueOf(code), payWay, payAmount);
		if (CollectionUtils.isEmpty(result)) {
			JSONUtils.SUCCESS(response, "付款成功.");
		} else {
			JSONUtils.ERROR(response, result.get("error"));
		}
	}

	/**
	 * 确认提交(注册完善个人信息)
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("confirmSubmit")
	public void confirmSubmit(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("name", "姓名不能为空.");
		validateResult.put("sex", "性别不能为空.");
		validateResult.put("birthday", "出生年月不能为空.");
		validateResult.put("address", "收货地址不能为空.");
		validateResult.put("phone", "手机号码不能为空.");
		validateResult.put("city", "所在城市不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String phone = request(request, "phone");// 手机号
		User user = getCurrentUser(request);
		if (!phone.equals(user.getPhone())) {
			JSONUtils.PARAM_ERROR(response, "当前手机号与注册手机号不一致,请检查手机号码是否正确.");
			return;
		}
		User u = BeanUtils.mapConvertToBean(User.class, request);
		u.setPassword(user.getPassword());
		setCurrentUser(request, u);
		// 更新User信息
		if (userLoginService.updateUser(u)) {
			JSONUtils.SUCCESS(response, "完善个人信息成功.");
		} else {
			JSONUtils.ERROR(response, "个人信息更新失败.");
		}
	}

	/**
	 * 生成邀请码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("generateInviteCode")
	public void generateInviteCode(HttpServletRequest request,
			HttpServletResponse response) {
		int success = inviteCodeService.batchInsert();
		JSONUtils.SUCCESS(response, "批量生成邀请码:" + success + "个.");
	}

	/**
	 * MD5加密
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("encodePassWord")
	public void encodePassWord(HttpServletRequest request,
			HttpServletResponse response) {
		String password = request.getParameter("password");
		if (StringUtils.isBlank(password)) {
			JSONUtils.PARAM_ERROR(response, "传入密码不能为空");
			return;
		}

		JSONUtils.SUCCESS(response, MD5Util.encodeToMD5(password));
	}

	/**
	 * 获取用户注册验证码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getRegistCode")
	public void getRegistCode(HttpServletRequest request,
			HttpServletResponse response) {
		String phone = request.getParameter("phone");
		if (StringUtils.isBlank(phone)) {
			JSONUtils.PARAM_ERROR(response, "手机号不能为空.");
			return;
		}
		// 检查是否已注册
		if (this.isRegist(phone)) {
			JSONUtils.ERROR(response, "当前用户已注册,不能重复注册.");
			return;
		}
		// 随机参数6位数验证码
		int code = LoginUtil.generatePhoneCode(request, phone);
		JSONUtils.SUCCESS(response, code);
	}

	/**
	 * 判断当前用户是否已注册
	 * 
	 * @return
	 */
	private boolean isRegist(String phone) {
		User user = new User();
		user.setPhone(phone);
		return userLoginService.verifyRegist(user);
	}

}
