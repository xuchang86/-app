/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyao.login.service.PersonManageService;

/**
 * 收徒系统Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月3日 许畅 新建
 */
@RequestMapping("/person")
public class PersonController extends BizBaseController {

	/** 注入PersonManageService */
	@Autowired
	private PersonManageService personManageService;

	/**
	 * 获取所有弟子信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAllChilds")
	public void getAllChilds(HttpServletRequest request,
			HttpServletResponse response) {

	}

}
