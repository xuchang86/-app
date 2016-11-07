/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.util;

import java.math.BigDecimal;

/**
 * 金额数字工具类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年11月8日 许畅 新建
 */
public final class NumberUtils {

	/**
	 * 构造方法
	 */
	private NumberUtils() {

	}

	/**
	 * BigDecimal 遇空则转零
	 * 
	 * @param amount
	 * @return
	 */
	public static BigDecimal releaseNull(BigDecimal amount) {
		return amount == null ? BigDecimal.ZERO : amount;
	}

}
