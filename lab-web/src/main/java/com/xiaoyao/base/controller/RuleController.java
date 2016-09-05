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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyao.base.model.Rule;
import com.xiaoyao.base.service.RuleService;
import com.xiaoyao.base.util.BeanUtils;
import com.xiaoyao.base.util.JSONUtils;

/**
 * 会员成长规则Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月21日 许畅 新建
 */
@Controller
@RequestMapping("/xiaoyao/rule")
public class RuleController extends BizBaseController {

	/** 注入RuleService */
	@Autowired
	private RuleService ruleService;

	/**
	 * 新增会员成长规则
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("addRule")
	public void addRule(HttpServletRequest request, HttpServletResponse response) {
		Rule rule = BeanUtils.mapConvertToBean(Rule.class, request);
		if (ruleService.insertRule(rule)) {
			JSONUtils.SUCCESS(response, "新增会员规则成功.");
		} else {
			JSONUtils.ERROR(response, "新增会员失败.");
		}
	}

}
