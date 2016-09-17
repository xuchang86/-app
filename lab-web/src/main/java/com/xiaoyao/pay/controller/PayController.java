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
import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.login.model.IsPay;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.service.UserLoginService;
import com.xiaoyao.login.util.LoginUtil;
import com.xiaoyao.mall.model.GoodsOrder;
import com.xiaoyao.mall.service.MallService;
import com.xiaoyao.pay.alipay.util.AlipayNotify;
import com.xiaoyao.pay.model.Order;
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

	/** 注入 UserLoginService */
	@Autowired
	private UserLoginService userLoginService;

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
		LOGGER.info("支付宝通知一次：" + (new Date()));
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

			// 用户Id
			String userId = new String(request.getParameter("userId").getBytes(
					"ISO-8859-1"), "UTF-8");
			System.out.println("用户id：" + userId);
			// 邀请码
			String inviteCode = new String(request.getParameter("inviteCode")
					.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("邀请码:" + inviteCode);

			// 支付成功处理逻辑
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				// 新增订单信息
				Order order = new Order();
				order.setOrderCode(out_trade_no);
				order.setPayDate(new Date());
				order.setPayAmount(new BigDecimal(LoginUtil.getRegistAmount()));
				order.setUserId(Integer.valueOf(userId));
				payService.saveOrder(order);
				// 更新已付款
				this.updateIsPay(userId);
				// 付款并保存person信息反写金额等业务操作
				User user = userLoginService.queryUserByPrimaryKey(userId);
				userLoginService.saveUser(user, inviteCode);
				// 支付成功
				ResponseUtils.renderText(response, "success");
			}
		} else {
			// 验证失败
			LOGGER.info("参数验证失败");
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

		LOGGER.info("支付宝通知一次：" + (new Date()));
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
			LOGGER.info("参数验证失败");
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
	 * 更新已付款
	 * 
	 * @param userId
	 */
	private void updateIsPay(String userId) {
		User user = new User();
		user.setIspay(IsPay.IS_PAY.getValue());
		user.setId(Integer.valueOf(userId));
		userLoginService.updateByByPrimaryKey(user);
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
		// 校验参数是否为空
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空.");
		validateResult.put("inviteCode", "邀请码不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String aliapayURL = LoginUtil.getAliapayURL();
		String userId = request(request, "userId");
		String inviteCode = request(request, "inviteCode");
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
					classReqValue, methodReqValue, userId, inviteCode));
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
