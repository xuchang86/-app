/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登录工具类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月18日 许畅 新建
 */
public final class LoginUtil {

	/** 日志 */
	private static Logger LOGGER = LoggerFactory.getLogger(LoginUtil.class);

	/**
	 * 构造方法
	 */
	private LoginUtil() {

	}

	/**
	 * 生成随机6位数手机验证并发送给手机
	 * 
	 * @param phone
	 *            手机号
	 * @return 手机验证码
	 */
	public static int generatePhoneCode(HttpServletRequest request, String phone) {
		// 产生验证码
		int code = getSixCode();
		// 调用服务器发短信服务器
		sendSMS(phone, code);
		// 将验证码放入session中
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		return code;
	}

	/**
	 * 调用远端服务器发送验证码给手机
	 * 
	 * @param phone
	 *            手机号
	 * @param code
	 *            验证码
	 */
	public static void sendSMS(String phone, int code) {
		LOGGER.info("start sendSMS phone :" + phone + " 验证码:" + code);

		LOGGER.info("end sendSMS...");
	}

	/**
	 * 获取随机6位数验证码
	 * 
	 * @return 获取随机6位数验证码
	 */
	public static int getSixCode() {
		String code = String.valueOf(RandomUtils.nextInt(1000000));
		if (code.length() != 6) {
			return getSixCode();
		}
		return Integer.parseInt(code);
	}

	public static void main(String[] args) {
		System.out.println("验证码:" + getSixCode());
	}

}
