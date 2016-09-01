/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.xiaoyao.base.model.Person;
import com.xiaoyao.login.exception.LoginException;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.service.PersonManageService;
import com.xiaoyao.login.service.UserLoginService;

/**
 * 业务基础Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月20日 许畅 新建
 */
public class BizBaseController extends BaseController {

	/**
	 * 注入 PersonManageService
	 */
	@Autowired
	private PersonManageService personManageService;

	/** 注入UserLoginService */
	@Autowired
	private UserLoginService userLoginService;

	/**
	 * 获取当前登录用户信息
	 * 
	 * @param request
	 * @return
	 */
	public User getCurrentUser(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null) {
			this.queryCurrentUser(request);
		}

		return (User) request.getSession().getAttribute("user");
	}

	/**
	 * 获取当前Person信息
	 * 
	 * @param request
	 * @return
	 */
	public Person getCurrentPerson(HttpServletRequest request) {
		if (request.getSession().getAttribute("person") == null) {
			this.queryCurrentUser(request);
		}
		return (Person) request.getSession().getAttribute("person");
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

	/**
	 * 设置当前登录用户信息和人物信息
	 * 
	 * @param request
	 * @param user
	 */
	public void setCurrentUserAndPerson(HttpServletRequest request, User user) {
		this.setCurrentUser(request, user);
		if (user.getId() != null) {
			List<Person> lst = personManageService.getPersonByUser(user);
			if (!CollectionUtils.isEmpty(lst)) {
				setCurrentPerson(request, lst.get(0));
			}
		}
	}

	/**
	 * 设置当前人员信息到session中
	 * 
	 * @param request
	 * @param person
	 */
	public void setCurrentPerson(HttpServletRequest request, Person person) {
		request.getSession().setAttribute("person", person);
	}

	/**
	 * 后台查询当前用户信息
	 * 
	 * @param request
	 */
	private void queryCurrentUser(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		if (StringUtils.isNotEmpty(userId)) {
			User user = userLoginService.queryUserByPrimaryKey(Integer
					.valueOf(userId));
			setCurrentUserAndPerson(request, user);
		} else {
			throw new LoginException("用户session失效,请重新登录.");
		}
	}
}
