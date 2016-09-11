/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.service;

/**
 * 基础服务
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
public abstract class BaseService {

	/**
	 * 包装返回值
	 * 
	 * @param count
	 *            DAO返回的值
	 * @return
	 */
	public boolean wrapperReturnVal(int count) {
		return count == 1 ? true : false;
	}

}
