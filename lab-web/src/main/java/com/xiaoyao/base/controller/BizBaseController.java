/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.controller;

import javax.servlet.http.HttpServletRequest;

import com.xiaoyao.login.exception.LoginException;
import com.xiaoyao.login.model.User;

/**
 * 业务基础Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月20日 许畅 新建
 */
public class BizBaseController extends BaseController {

	/**
	 * 获取当前登录用户信息
	 * 
	 * @param request
	 * @return
	 */
	public User getCurrentUser(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null) {
			throw new LoginException("用户session失效,请检查是否存在非法操作.");
		}

		return (User) request.getSession().getAttribute("user");
	}

	/**
	 * 设置登录用户到当前session中
	 * 
	 * @param request
	 * @param user
	 */
	public void setCurrentUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute("user", user);
	}
}
