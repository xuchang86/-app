/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.util;

import com.xiaoyao.base.cache.GlobalCache;
import com.xiaoyao.base.model.Level;
import com.xiaoyao.base.model.Rule;

/**
 * 逍遥派等级会员操作类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月4日 许畅 新建
 */
public final class RuleOperator {

	/**
	 * 构造方法
	 */
	private RuleOperator() {

	}

	/** 弟子数量临界值,如果超过了该值则将成为副掌门 */
	public static final Integer TOP_LEVEL_COUNT = GlobalCache.getRule(
			Level.DA_ZHANG_LAO).getLevelCount();

	/**
	 * 升级算法:通过弟子数量获取将要获得的级别 <br>
	 * 
	 * (如果返回null则不满足升级条件)
	 * 
	 * @param childCount
	 *            弟子数量
	 */
	public static Rule upgrade(Integer childCount) {
		if (childCount < 1) {// 见习弟子
			return GlobalCache.getRule(Level.JIAN_XI_DIZI);
		}

		if (childCount >= TOP_LEVEL_COUNT) {// 副掌门
			return GlobalCache.getRule(Level.FU_ZHANG_MENG);
		}

		Level[] levels = Level.values();
		for (Level level : levels) {
			Rule rule = GlobalCache.getRule(level);
			if (rule.getLevelCount() == childCount) {
				return GlobalCache.getRule(rule.getLevel() + 1);
			}
		}
		return null;
	}

}
