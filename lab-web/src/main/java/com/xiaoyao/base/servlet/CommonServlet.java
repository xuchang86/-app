/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaoyao.base.cache.GlobalCache;
import com.xiaoyao.base.service.RuleService;
import com.xiaoyao.base.util.SpringContextBeanUtil;

/**
 * 初始化容器中的缓存数据servlet
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月21日 许畅 新建
 */
public class CommonServlet extends HttpServlet {

	/** 默认序列 */
	private static final long serialVersionUID = 1L;

	/**
	 * CommonServlet初始化
	 * 
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println(getClass().getName() + ":init...");
		super.init(config);
		// 将会员成长规则算法放入全局缓存
		RuleService service = SpringContextBeanUtil.getBean(RuleService.class);
		GlobalCache.putRule(service.queryRule());
	}

	/**
	 * @return
	 *
	 * @see javax.servlet.GenericServlet#getServletConfig()
	 */
	@Override
	public ServletConfig getServletConfig() {
		System.out.println(getClass().getName() + ":getServletConfig");
		return super.getServletConfig();
	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 *
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(getClass().getName() + ":service");
		super.service(req, resp);
	}

	/**
	 * @return
	 *
	 * @see javax.servlet.GenericServlet#getServletInfo()
	 */
	@Override
	public String getServletInfo() {
		System.out.println(getClass().getName() + ":getServletInfo");
		return super.getServletInfo();
	}

	/**
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println(getClass().getName() + ":destroy");
		super.destroy();
	}

}
