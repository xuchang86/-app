/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyao.base.model.Person;
import com.xiaoyao.base.util.BeanUtils;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.login.model.PersonQuery;
import com.xiaoyao.login.service.PersonManageService;

/**
 * 收徒系统Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月3日 许畅 新建
 */
@Controller
@RequestMapping("/person")
public class PersonController extends BizBaseController {

	/** 注入PersonManageService */
	@Autowired
	private PersonManageService personManageService;

	/**
	 * 逍遥币排行榜
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryTopBill")
	public void queryTopBill(HttpServletRequest request,
			HttpServletResponse response) {
		// 参数 pageSize 页数,pageNo 页码,sortField 排序字段,sortType 排序类型
		PersonQuery query = BeanUtils.mapConvert2ToBean(PersonQuery.class,
				request);
		JSONUtils.SUCCESS(response, personManageService.queryTopBill(query));
	}

	/**
	 * 弟子数量排行榜
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryTopChild")
	public void queryTopChild(HttpServletRequest request,
			HttpServletResponse response) {
		// 参数 pageSize 页数,pageNo 页码,sortField 排序字段,sortType 排序类型
		PersonQuery query = BeanUtils.mapConvertToBean(PersonQuery.class,
				request);
		query.setSortField("childs");

		Map<Class<?>, String[]> excludes = new HashMap<Class<?>, String[]>();
		excludes.put(PersonQuery.class,
				new String[] { "sortField", "sortType" });
		excludes.put(Person.class, new String[] { "id", "userId", "bill",
				"parentId", "createDate" });
		JSONUtils.SUCCESS(response, personManageService.queryTopChild(query),
				null, excludes);
	}
}
