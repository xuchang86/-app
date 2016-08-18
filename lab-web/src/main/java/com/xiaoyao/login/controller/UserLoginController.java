/*************************(*****************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group.core.controller.BaseController;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.login.model.User;
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
public class UserLoginController extends BaseController {

	/** 注入 UserLoginService */
	@Autowired
	private UserLoginService userLoginService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("login in...");
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
		if (StringUtils.isBlank(phone)) {
			JSONUtils.PARAM_ERROR(response, "手机号不能为空.");
			return;
		}
		// 校验是否已注册
		if (isRegist(phone)) {
			JSONUtils.ERROR(response, "当前用户已注册,不能重复注册.");
			return;
		}
		// 校验验证码是否正确
		String code = params.get("code");
		if (StringUtils.isBlank(code)) {
			JSONUtils.PARAM_ERROR(response, "验证码不能为空.");
			return;
		}
		Object sessionCode = request.getSession().getAttribute("code");
		if (sessionCode == null) {
			JSONUtils.ERROR(response, "验证码已过期,请重新发送验证码.");
			return;
		}
		if (String.valueOf(sessionCode).equals(code)) {
			JSONUtils.SUCCESS(response, "第一步验证成功.");
		}
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
