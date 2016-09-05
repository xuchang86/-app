/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.event.service.EventService;

/**
 * 门派事件Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月28日 许畅 新建
 */
@Controller
@RequestMapping(value = "/event")
public class EventController extends BizBaseController {

	/** 注入 EventService */
	@Autowired
	private EventService eventService;

	/**
	 * 查询门派中所有通知事件
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("queryAllEvent")
	public void queryAllEvent(HttpServletRequest request,
			HttpServletResponse response) {

		JSONUtils.toJSONString(response, eventService.queryAllEvent());
	}

}
