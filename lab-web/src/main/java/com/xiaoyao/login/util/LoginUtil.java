/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.util.IOUtils;

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

	/** 登陆配置文件 */
	private static final String LOGIN_PROP = "config/login.properties";

	/** 邀请码默认创建数量 */
	private static final String INVITE_CODE_COUNT = "10";

	/** 登陆配置文件 */
	private static final Properties p = new Properties();

	/**
	 * 构造方法
	 */
	private LoginUtil() {

	}

	/** 读取登陆配置文件 */
	static {
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(LOGIN_PROP);
		try {
			if (in != null)
				p.load(in);
		} catch (IOException e) {
			LOGGER.error("读取config/login.properties配置文件失败:" + e.getMessage(), e);
		} finally {
			IOUtils.close(in);
		}
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

	/**
	 * 获取配置文件值
	 * 
	 * @param key
	 * @return
	 */
	protected static String getPropertyValue(String key) {
		return p.getProperty(key);
	}

	/**
	 * 获取配置文件值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected static String getPropertyValue(String key, String defaultValue) {
		return p.getProperty(key, defaultValue);
	}

	/**
	 * 获取邀请码数量
	 * 
	 * @return
	 */
	public static int getInviteCodeCount() {
		int count = Integer.valueOf(getPropertyValue("invite_code_count",
				INVITE_CODE_COUNT));
		return count;
	}

	/**
	 * 获取首次注册金额
	 * 
	 * @return
	 */
	public static String getRegistAmount() {
		return getPropertyValue("regist_amount", "100");
	}

	/**
	 * 获取首次注册支付宝URL
	 * 
	 * @return
	 */
	public static String getAliapayURL() {
		return getPropertyValue("aliapay_url");
	}

	/**
	 * 获取人民币兑换逍遥币的汇率(人民币:逍遥币=1:100)
	 * 
	 * @return
	 */
	public static BigDecimal getRate() {
		String rate = getPropertyValue("rate", "100");
		return new BigDecimal(rate);
	}

	/**
	 * 获取首次注册的逍遥币(通过人民币兑换)
	 * 
	 * @return
	 */
	public static BigDecimal getRegistXyAmount() {
		BigDecimal rmb = new BigDecimal(getRegistAmount());
		return rmb.multiply(getRate());
	}
}
