/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

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
		super.init(config);
		// 将会员成长规则算法放入全局缓存
		RuleService service = SpringContextBeanUtil.getBean(RuleService.class);
		GlobalCache.putRule(service.queryRule());
	}

}
