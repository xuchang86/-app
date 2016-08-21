/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.xiaoyao.base.model.Rule;

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
	private static final Map<String, Object> cache = new HashMap<String, Object>();

	/** 会员成长规则key */
	private static final String RULE_KEY = "rule";

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
	public static void put(String key, Object value) {
		cache.put(key, value);
	}

	/**
	 * 从缓存中取得会员成长算法规则
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Rule> getRule() {
		return getCache(RULE_KEY) == null ? new ArrayList<Rule>()
				: (List<Rule>) getCache(RULE_KEY);
	}

	/**
	 * 存放会员成长规则缓存
	 * 
	 * @param rules
	 */
	public static void putRule(List<Rule> rules) {
		put(RULE_KEY, rules);
	}
}
