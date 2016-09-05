/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.util;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

/**
 * spring容器中工具类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月21日 许畅 新建
 */
public final class SpringContextBeanUtil {

	/**
	 * 构造方法
	 */
	private SpringContextBeanUtil() {

	}

	/**
	 * 从spring容器中获取bean实例对象
	 * 
	 * @param cls
	 *            spring容器中的bean class
	 * @return bean实例
	 */
	public static <T> T getBean(Class<T> cls) {
		WebApplicationContext context = ContextLoaderListener
				.getCurrentWebApplicationContext();
		return context.getBean(cls);
	}

}
