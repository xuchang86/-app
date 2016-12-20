/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.taobao.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.util.IOUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 短信发送代理工具类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年12月12日 许畅 新建
 */
public final class MessageUtil {

	/**
	 * 构造方法
	 */
	private MessageUtil() {
		super();
	}

	/** 日志输出 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageUtil.class);

	/** 短信配置文件 */
	private static final String PROPERTIES = "config/message.properties";

	/** 短信服务器配置 */
	private static final Properties p = new Properties();

	/** 短信类型 */
	private static final String SMSTYPE = "normal";

	static {
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(PROPERTIES);
		try {
			if (in != null)
				p.load(in);
		} catch (IOException e) {
			LOGGER.error("读取config/message.properties配置文件失败:" + e.getMessage(),
					e);
		} finally {
			IOUtils.close(in);
		}
	}

	/**
	 * 调用阿里平台发送短信
	 * 
	 * @param phone
	 *            手机号
	 * 
	 * @param smsParam
	 *            传参规则{"key":"value"}
	 * @return
	 * @throws ApiException
	 */
	public static String sendMessage(String phone, String smsParam)
			throws ApiException {
		TaobaoClient client = new DefaultTaobaoClient(getURL(), getAppKey(),
				getSecret());
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(SMSTYPE);
		req.setSmsFreeSignName(getSmsFreeSignName());
		req.setSmsParamString(smsParam);
		req.setRecNum(phone);
		req.setSmsTemplateCode(getSmsTemplateCode());
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		LOGGER.info("send message result:" + rsp.getBody());
		return rsp.getBody();
	}

	/**
	 * 发送短信
	 * 
	 * @param phone
	 * @return
	 * @throws ApiException
	 */
	public static String sendMessage(String phone) throws ApiException {
		return sendMessage(phone, "{product:'逍遥派'}");
	}

	/**
	 * 短信签名
	 * 
	 * @return
	 */
	public static String getSmsFreeSignName() {
		return getPropertyValue("smsFreeSignName");
	}

	/**
	 * 短信模板id
	 * 
	 * @return
	 */
	public static String getSmsTemplateCode() {
		return getPropertyValue("smsTemplateCode");
	}

	/**
	 * @return
	 */
	public static String getSecret() {
		return getPropertyValue("appSecret");
	}

	/**
	 * @return
	 */
	public static String getAppKey() {
		return getPropertyValue("appkey");
	}

	/**
	 * @return
	 */
	public static String getURL() {
		return getPropertyValue("serverUrl");
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

}
