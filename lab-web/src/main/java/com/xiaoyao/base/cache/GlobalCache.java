/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.xiaoyao.base.model.Level;
import com.xiaoyao.base.model.Rule;
import com.xiaoyao.login.util.LoginUtil;

/**
 * 逍遥派全局缓存对象
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月21日 许畅 新建
 */
public final class GlobalCache {

	/**
	 * 构造方法
	 */
	private GlobalCache() {
		super();
	}

	/** 缓存对象 */
	private static final Map<Object, Object> cache = new HashMap<Object, Object>();

	/** 服务器ip */
	public static final String IP = "ip";

	/** 服务器端口 */
	public static final String PORT = "port";

	/** 服务器上下文 */
	public static final String CONTEXT_PATH = "contextPath";

	/**
	 * 获取缓存对象中的值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		if (CollectionUtils.isEmpty(cache)) {
			return null;
		}
		return cache.get(key);
	}

	/**
	 * 存放缓存内容
	 * 
	 * @param key
	 * @param value
	 */
	public static void put(Object key, Object value) {
		cache.put(key, value);
	}

	/**
	 * 从缓存中取得会员成长算法规则
	 * 
	 * @return
	 */
	public static Rule getRule(Level level) {
		if (!cache.containsKey(level.getValue()))
			return null;

		return (Rule) cache.get(level.getValue());
	}

	/**
	 * 从缓存中取得会员成长算法规则
	 * 
	 * @return
	 */
	public static Rule getRule(Integer level) {
		if (!cache.containsKey(level))
			return null;

		return (Rule) cache.get(level);
	}

	/**
	 * 获取服务器ip地址
	 * 
	 * @return
	 */
	public static String getServerIP() {
		if (getCache(IP) == null)
			return LoginUtil.getServerIP();

		return String.valueOf(getCache(IP));
	}

	/**
	 * 获取服务器端口号
	 * 
	 * @return
	 */
	public static String getServerPort() {
		if (getCache(PORT) == null) {
			return LoginUtil.getServerPort();
		}
		return String.valueOf(getCache(PORT));
	}

	/**
	 * 服务器上下文路径
	 * 
	 * @return
	 */
	public static String getContextPath() {
		return String.valueOf(getCache(CONTEXT_PATH));
	}

	/**
	 * 存放会员成长规则缓存
	 * 
	 * @param rules
	 */
	public static void putRule(List<Rule> rules) {
		for (Rule rule : rules) {
			put(rule.getLevel(), rule);
		}
	}
}
