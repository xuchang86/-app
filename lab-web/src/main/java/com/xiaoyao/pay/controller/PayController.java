package com.xiaoyao.pay.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.sign.AlipaySignUtil;
import com.alipay.util.AlipayNotify;
import com.group.utils.CommonUtils;
import com.group.utils.ResponseUtils;
import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.login.util.LoginUtil;
import com.xiaoyao.mall.model.GoodsOrder;
import com.xiaoyao.mall.service.MallService;
import com.xiaoyao.pay.service.CashPoolService;
import com.xiaoyao.pay.service.PayService;

/**
 * 付款Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
@Controller
@RequestMapping("pay")
public class PayController extends BizBaseController {

	/** LOGGER日志 */
	private Logger LOGGER = LoggerFactory.getLogger(PayController.class);

	/** 注入PayService */
	@Autowired
	private PayService payService;

	/** 注入 CashPoolService */
	@Autowired
	private CashPoolService cashPoolService;

	/** 注入 MallService */
	@Autowired
	private MallService mallService;

	/**
	 * 支付宝支付成功反馈信息
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("apilypayNotify")
	public void apilypayNotify(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		LOGGER.info("支付宝apilypayNotify通知一次：" + (new Date()));
		Map<String, String> params = aliapayNotifyBefore(request, response);
		LOGGER.info("支付宝通知apilypayNotify_params：" + params);

		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");
		LOGGER.info("商户订单号：" + out_trade_no);
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");
		LOGGER.info("交易状态：" + trade_status);

		// 支付成功处理逻辑
		if (trade_status.equals("TRADE_FINISHED")
				|| trade_status.equals("TRADE_SUCCESS")) {
			// 业务回调参数 userId=23&inviteCode=121
			String passback_params = params.get("passback_params");
			String biz_params = URLDecoder.decode(passback_params, "UTF-8");
			LOGGER.info("业务参数:" + biz_params);
			String[] ps = biz_params.split("&");
			String[] userIds = ps[0].split("=");
			String userId = userIds[1];
			String[] inviteCodes = ps[1].split("=");
			String inviteCode = inviteCodes[1];
			LOGGER.info("userId:" + userId + ";inviteCode:" + inviteCode);
			// 业务回调
			payService.notifyCallback(out_trade_no, userId, inviteCode);
			// 支付成功
			ResponseUtils.renderText(response, "success");
		}
	}

	/**
	 * 充值付款通知接口
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("rechargeNotify")
	public void rechargeNotify(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		LOGGER.info("rechargeNotify支付宝通知一次：" + (new Date()));
		Map<String, String> params = aliapayNotifyBefore(request, response);
		// 验证参数
		LOGGER.info("支付宝rechargeNotify params：" + params);
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("商户订单号：" + out_trade_no);
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("交易状态：" + trade_status);

		// 支付成功处理逻辑
		if (trade_status.equals("TRADE_FINISHED")
				|| trade_status.equals("TRADE_SUCCESS")) {
			// 业务回调参数 userId=23&amount=121
			String passback_params = params.get("passback_params");
			String biz_params = URLDecoder.decode(passback_params, "UTF-8");
			LOGGER.info("业务参数2:" + biz_params);
			String[] ps = biz_params.split("&");
			String[] userIds = ps[0].split("=");
			String userId = userIds[1];
			String[] amounts = ps[1].split("=");
			String amount = amounts[1];
			LOGGER.info("userId:" + userId + ";amount:" + amount);
			// 业务回调
			payService.rechargeCallback(out_trade_no, userId, amount);
			// 支付成功
			ResponseUtils.renderText(response, "success");
		}
	}

	/**
	 * 微信支付反馈结果
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws
	 * @throws Exception
	 */
	@RequestMapping("wechatNotify")
	public void wechatNotify(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("微信通知一次：" + new Date());
		InputStream in = request.getInputStream();
		byte b[] = new byte[1024];
		int len = 0;
		int temp = 0;
		while ((temp = in.read()) != -1) {
			b[len] = (byte) temp;
			len++;
		}
		in.close();
		String xmlParam = new String(b, 0, len);

		String return_code = CommonUtils.readStringXmlOut(xmlParam,
				"return_code");
		System.out.println("交易状态：" + return_code);
		if ("SUCCESS".equals(return_code)) {
			// 说明微信支付成功
			String out_trade_no = CommonUtils.readStringXmlOut(xmlParam,
					"out_trade_no");
			System.out.println("商户订单号：" + out_trade_no);
			// 用户Id
			String userId = new String(request.getParameter("userId").getBytes(
					"ISO-8859-1"), "UTF-8");
			System.out.println("用户id：" + userId);
			// 邀请码
			String inviteCode = new String(request.getParameter("inviteCode")
					.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("邀请码:" + inviteCode);
			// 业务回调
			payService.notifyCallback(out_trade_no, userId, inviteCode);
			// 支付成功处理逻辑
			ResponseUtils.renderText(response, "success");
		}
	}

	/**
	 * 商城支付宝支付通知回调
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("mallAliapayNotify")
	public void mallAliapayNotify(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		LOGGER.info("支付宝mallAliapayNotify通知一次：" + (new Date()));
		Map<String, String> params = aliapayNotifyBefore(request, response);
		// 验证参数
		if (AlipayNotify.verify(params)) {
			// 商户订单号
			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("商户订单号：" + out_trade_no);
			// 交易状态
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("交易状态：" + trade_status);

			// 商品订单id
			String goodsOrderId = new String(request.getParameter(
					"goodsOrderId").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("用户id：" + goodsOrderId);

			// 支付成功处理逻辑
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				// 更新商品订单付款日期和订单号
				GoodsOrder order = new GoodsOrder();
				order.setId(Integer.parseInt(goodsOrderId));
				order.setNumber(out_trade_no);
				order.setPayDate(new Date());
				mallService.updateGoodsOrder(order);
				// 支付成功
				ResponseUtils.renderText(response, "success");
			}
		} else {
			// 验证失败
			LOGGER.info("参数验证失败:mallAliapayNotify");
		}
	}

	/**
	 * 支付宝付款通知之前
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> aliapayNotifyBefore(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		return params;
	}

	/**
	 * 注册时支付宝支付签名
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("alipaySign")
	public void alipaySign(HttpServletRequest request,
			HttpServletResponse response) {
		// 校验参数是否为空
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空.");
		validateResult.put("inviteCode", "邀请码不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;
		String userId = request(request, "userId");
		String inviteCode = request(request, "inviteCode");
		String notify_url = this.buildNotifyURL();
		String extral_param = null;
		try {
			extral_param = URLEncoder.encode("userId=" + userId
					+ "&inviteCode=" + inviteCode, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("编码extral_param失败:" + e.getMessage(), e);
		}

		String orderInfo = AlipaySignUtil.buildOrderSign(notify_url, "0.01", extral_param);
		ResponseUtils.renderText(response, orderInfo);
	}

	/**
	 * 获取支付宝付款接口参数URL
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAliaPayURL")
	public void getAliaPayURL(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseUtils.renderText(response, buildNotifyURL());
	}

	/**
	 * 获取充值通知URL
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getRechargeURL")
	public void getRechargeURL(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseUtils.renderText(response, buildRechargeNotifyURL());
	}

	/**
	 * 构建通知URL
	 * 
	 * @param userId
	 * @param inviteCode
	 * @return
	 */
	private String buildNotifyURL() {
		String aliapayURL = LoginUtil.getAliapayURL();
		RequestMapping req = this.getClass()
				.getAnnotation(RequestMapping.class);
		String classReqValue = req.value().length > 0 ? req.value()[0] : "";
		try {
			Method method = this.getClass().getMethod("apilypayNotify",
					HttpServletRequest.class, HttpServletResponse.class);
			RequestMapping reqMethod = method
					.getAnnotation(RequestMapping.class);
			String methodReqValue = reqMethod.value().length > 0 ? reqMethod
					.value()[0] : "";
			return MessageFormat.format(aliapayURL, classReqValue,
					methodReqValue);
		} catch (NoSuchMethodException e) {
			LOGGER.error("找不到apilypayNotify方法:" + e.getMessage(), e);
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取微信支付接口参数URL
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getWechatPayURL")
	public void getWechatPayURL(HttpServletRequest request,
			HttpServletResponse response) {
		// 校验参数是否为空
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空.");
		validateResult.put("inviteCode", "邀请码不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String wechatURL = LoginUtil.getWechatURL();
		String userId = request(request, "userId");
		String inviteCode = request(request, "inviteCode");
		RequestMapping req = this.getClass()
				.getAnnotation(RequestMapping.class);
		String classReqValue = req.value().length > 0 ? req.value()[0] : "";
		try {
			Method method = this.getClass().getMethod("wechatNotify",
					HttpServletRequest.class, HttpServletResponse.class);
			RequestMapping reqMethod = method
					.getAnnotation(RequestMapping.class);
			String methodReqValue = reqMethod.value().length > 0 ? reqMethod
					.value()[0] : "";
			ResponseUtils.renderText(response, MessageFormat.format(wechatURL,
					classReqValue, methodReqValue, userId, inviteCode));
		} catch (NoSuchMethodException e) {
			LOGGER.error("找不到wechatPayURL方法:" + e.getMessage(), e);
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取注册付款金额
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getRegistAmount")
	public void getRegistAmount(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseUtils.renderText(response, LoginUtil.getRegistAmount());
	}

	/**
	 * 充值时支付宝支付签名
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("rechargeSign")
	public void rechargeSign(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空.");
		validateResult.put("amount", "充值金额不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String userId = request(request, "userId");
		String amount = request(request, "amount");
		String notify_url = this.buildRechargeNotifyURL();
		String extral_param = null;
		try {
			extral_param = URLEncoder.encode("userId=" + userId + "&amount="
					+ amount, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("编码extral_param失败:" + e.getMessage(), e);
		}

		String orderInfo = AlipaySignUtil.buildOrderSign(notify_url, amount,
				extral_param);
		ResponseUtils.renderText(response, orderInfo);
	}

	/**
	 * 构建充值通知URL
	 * 
	 * @param userId
	 * @param amount
	 * @return
	 */
	private String buildRechargeNotifyURL() {
		String aliapayURL = LoginUtil.getRechargeURL();
		RequestMapping req = this.getClass()
				.getAnnotation(RequestMapping.class);
		String classReqValue = req.value().length > 0 ? req.value()[0] : "";
		try {
			Method method = this.getClass().getMethod("rechargeNotify",
					HttpServletRequest.class, HttpServletResponse.class);
			RequestMapping reqMethod = method
					.getAnnotation(RequestMapping.class);
			String methodReqValue = reqMethod.value().length > 0 ? reqMethod
					.value()[0] : "";
			return MessageFormat.format(aliapayURL, classReqValue,
					methodReqValue);
		} catch (NoSuchMethodException e) {
			LOGGER.error("找不到apilypayNotify方法:" + e.getMessage(), e);
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 初始化资金池
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("initCashPool")
	public void initCashPool(HttpServletRequest request,
			HttpServletResponse response) {
		if (cashPoolService.initCashPool()) {
			JSONUtils.SUCCESS(response, "资金池初始化成功");
		}
	}

}
