package com.xiaoyao.pay.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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

import com.group.utils.ResponseUtils;
import com.xiaoyao.base.alipay.util.AlipayNotify;
import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.login.util.LoginUtil;
import com.xiaoyao.pay.model.Order;
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

	/**
	 * 支付宝支付成功反馈信息
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("apilypayNotify")
	public void apilypayNotify(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		LOGGER.info("支付宝通知一次：" + (new Date()));
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

			// 支付成功处理逻辑
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				Order order = new Order();
				order.setOrderCode(out_trade_no);
				order.setPayDate(new Date());
				order.setPayAmount(new BigDecimal(LoginUtil.getRegistAmount()));
				payService.saveOrder(order);
				// 支付成功
				ResponseUtils.renderText(response, "success");
			}
		} else {
			// 验证失败
			LOGGER.info("参数验证失败");
		}
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
			ResponseUtils.renderText(response, MessageFormat.format(aliapayURL,
					classReqValue, methodReqValue));
		} catch (NoSuchMethodException e) {
			LOGGER.error("找不到apilypayNotify方法:" + e.getMessage(), e);
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

}
