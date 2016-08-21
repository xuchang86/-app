/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.exception;

/**
 * 逍遥派登陆异常
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月20日 许畅 新建
 */
public class LoginException extends RuntimeException {

	/** 默认序列 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法
	 */
	public LoginException() {
		super();
	}

	/**
	 * 构造方法
	 * 
	 * @param message
	 */
	public LoginException(String message) {
		super(message);
	}

	/**
	 * 构造方法
	 * 
	 * @param message
	 * @param cause
	 */
	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造方法
	 * 
	 * @param cause
	 */
	public LoginException(Throwable cause) {
		super(cause);
	}

}
