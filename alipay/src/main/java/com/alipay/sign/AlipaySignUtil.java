/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.alipay.sign;

import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.alipay.sdk.pay.demo.util.OrderInfoUtil2_0;

/**
 * 支付宝签名
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年10月31日 许畅 新建
 */
public final class AlipaySignUtil {

	/** 支付宝账户登录授权业务：入参pid值 */
	private static final String PID = "";

	/** 支付宝支付业务：入参app_id */
	private static final String APPID = AlipayConfig.APPID;

	/** 支付宝账户登录授权业务：入参target_id值 */
	private static final String TARGET_ID = "";

	/** 商户私钥，pkcs8格式 */
	private static final String RSA_PRIVATE = AlipayConfig.RSA_PRIVATE;

	/**
	 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
	 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
	 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
	 * 
	 * orderInfo的获取必须来自服务端；
	 */
	public static String buildOrderSign(String notify_url, String total_amount) {
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,
				notify_url, total_amount);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
		final String orderInfo = orderParam + "&" + sign;
		return orderInfo;
	}

	/**
	 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
	 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
	 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
	 * 
	 * authInfo的获取必须来自服务端；
	 */
	public static String buildAuthSign() {
		Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(
				PID, APPID, TARGET_ID);
		String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
		String sign = OrderInfoUtil2_0.getSign(authInfoMap, RSA_PRIVATE);
		final String authInfo = info + "&" + sign;
		return authInfo;
	}

}
