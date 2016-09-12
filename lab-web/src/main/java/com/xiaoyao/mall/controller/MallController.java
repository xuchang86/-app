/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.mall.controller;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.util.BeanUtils;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.login.util.LoginUtil;
import com.xiaoyao.mall.model.GoodsOrder;
import com.xiaoyao.mall.service.MallService;
import com.xiaoyao.pay.controller.PayController;

/**
 * 商城Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月12日 许畅 新建
 */
@Controller
@RequestMapping("/mall")
public class MallController extends BizBaseController {

	/** 注入MallService */
	@Autowired
	private MallService mallService;

	/**
	 * 查询所有商品(支持分页查询)
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryAllGoods")
	public void queryAllGoods(HttpServletRequest request,
			HttpServletResponse response) {
		JSONUtils.SUCCESS(response, mallService.queryAllGoods());
	}

	/**
	 * 确认支付(先调用该接口保存商品订单相关信息)
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("confirmPayment")
	public void confirmPayment(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("goodsId", "商品id不能为空.");
		validateResult.put("address", "联系地址不能为空.");
		validateResult.put("userId", "用户id不能为空");
		validateResult.put("contacts", "联系人不能为空.");
		validateResult.put("phone", "联系电话不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		GoodsOrder goodsOrder = BeanUtils.mapConvert2ToBean(GoodsOrder.class,
				request);
		goodsOrder.setCreateDate(new Date());
		mallService.saveGoodsOrder(goodsOrder);
		if (goodsOrder.getId() == null) {
			JSONUtils.ERROR(response, "保存商品订单失败.");
			return;
		}

		JSONUtils.SUCCESS(response, goodsOrder.getId());
	}

	/**
	 * 获取商城的支付宝回调URL(付款第二步)
	 * 
	 * @param request
	 * @param response
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@RequestMapping("getMallAliaPayURL")
	public void getMallAliaPayURL(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchMethodException,
			SecurityException {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("goodsOrderId", "商品订单id不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String goodsOrderId = request(request, "goodsOrderId");
		String aliapayURL = LoginUtil.getMallAliapayURL();

		RequestMapping req = PayController.class
				.getAnnotation(RequestMapping.class);
		String classReqValue = req.value().length > 0 ? req.value()[0] : "";
		Method method = PayController.class.getMethod("mallAliapayNotify",
				HttpServletRequest.class, HttpServletResponse.class);
		RequestMapping reqMethod = method.getAnnotation(RequestMapping.class);
		String methodReqValue = reqMethod.value().length > 0 ? reqMethod
				.value()[0] : "";
		String url = MessageFormat.format(aliapayURL, classReqValue,
				methodReqValue, goodsOrderId);
		JSONUtils.SUCCESS(response, url);
	}
}
