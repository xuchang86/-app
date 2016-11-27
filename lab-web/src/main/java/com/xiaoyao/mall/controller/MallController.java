/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.mall.controller;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.model.Person;
import com.xiaoyao.base.util.BeanUtils;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.login.service.PersonManageService;
import com.xiaoyao.login.util.LoginUtil;
import com.xiaoyao.mall.model.Address;
import com.xiaoyao.mall.model.Comment;
import com.xiaoyao.mall.model.Goods;
import com.xiaoyao.mall.model.GoodsOrder;
import com.xiaoyao.mall.model.State;
import com.xiaoyao.mall.service.AddressService;
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

	/** 注入PersonManageService */
	@Autowired
	private PersonManageService personManageService;

	/** 注入AddressService */
	@Autowired
	private AddressService addressService;

	/**
	 * 查询所有商品(支持分页查询)
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryAllGoods")
	public void queryAllGoods(HttpServletRequest request,
			HttpServletResponse response) {
		String pageSize = request(request, "pageSize");
		String pageNo = request(request, "pageNo");
		List<Goods> lst = mallService.queryAllGoods(pageSize, pageNo);
		JSONUtils.SUCCESS(response, lst);
	}

	/**
	 * 查询商品分类型
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryGoodsType")
	public void queryGoodsType(HttpServletRequest request,
			HttpServletResponse response) {
		JSONUtils.SUCCESS(response, mallService.queryGoodsType());
	}

	/**
	 * 通过商品类型获取商品
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryGoodsByTypeId")
	public void queryGoodsByTypeId(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("typeId", "类型id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String sortField = request(request, "sortField");// 排序字段:sales,createDate,price,vipPrice
		String sortType = request(request, "sortType");// 排序类型: desc,asc

		String pageSize = request(request, "pageSize");
		String pageNo = request(request, "pageNo");
		String typeId = request(request, "typeId");

		if (StringUtils.isNotEmpty(sortField)
				&& StringUtils.isNotEmpty(sortType)
				&& "sales".equals(sortField)) {
			JSONUtils.SUCCESS(response, mallService.querySalesByType(pageSize,
					pageNo, typeId, sortField, sortType));
			return;
		}

		JSONUtils.SUCCESS(response, mallService.queryGoodsByType(pageSize,
				pageNo, typeId, sortField, sortType));
	}

	/**
	 * 查询精选商品
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryNiceGoods")
	public void queryNiceGoods(HttpServletRequest request,
			HttpServletResponse response) {
		JSONUtils.SUCCESS(response, mallService.queryNiceGoods());
	}

	/**
	 * 查询我的收货地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryAddress")
	public void queryAddress(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String userId = request(request, "userId");
		List<Address> addresses = addressService.queryAddress(userId);
		JSONUtils.SUCCESS(response, addresses);
	}

	/**
	 * 新增收货地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("addAddress")
	public void addAddress(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		validateResult.put("contracts", "联系人不能为空");
		validateResult.put("phone", "联系电话不能为空");
		validateResult.put("city", "城市不能为空");
		validateResult.put("address", "详细住址不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		Address address = BeanUtils.mapConvertToBean(Address.class, request);
		addressService.save(address);
		JSONUtils.SUCCESS(response, address.getId());
	}

	/**
	 * 删除收货地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("deleteAddress")
	public void deleteAddress(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("addressId", "地址id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String id = request(request, "addressId");
		addressService.delete(Integer.parseInt(id));
		JSONUtils.SUCCESS(response, "删除地址成功");
	}

	/**
	 * 修改收货地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("modifyAddress")
	public void modifyAddress(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("id", "地址id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		Address address = BeanUtils.mapConvertToBean(Address.class, request);
		addressService.save(address);
		JSONUtils.SUCCESS(response, address.getId());
	}

	/**
	 * 批量确认订单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("batchConfirmOrder")
	public void batchConfirmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("orders", "订单集合不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String orders = request(request, "orders");
		List<Integer> orderIds = new ArrayList<Integer>();
		// 设置请求参数:goodsId,userId,amount,goodsModel,addressId
		JSONArray array = JSONArray.parseArray(orders);
		for (Object obj : array) {
			JSONObject json = (JSONObject) obj;
			GoodsOrder order = JSON.toJavaObject(json, GoodsOrder.class);
			this.confirmOrder(order);
			orderIds.add(order.getId());
		}

		JSONUtils.SUCCESS(response, orderIds);
	}

	/**
	 * 批量确认支付
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("batchConfirmPayment")
	public void batchConfirmPayment(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("orders", "订单集合不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String orders = request(request, "orders");
		List<Integer> orderIds = new ArrayList<Integer>();
		// 设置请求参数:orderId,userId,amount
		JSONArray array = JSONArray.parseArray(orders);
		for (Object obj : array) {
			JSONObject json = (JSONObject) obj;
			GoodsOrder order = this.confirmPayment(json);
			if (order != null) {
				orderIds.add(order.getId());
			}
		}
		JSONUtils.SUCCESS(response, orderIds);
	}

	/**
	 * 确认订单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("confirmOrder")
	public void confirmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("goodsId", "商品id不能为空.");
		validateResult.put("userId", "用户id不能为空");
		validateResult.put("amount", "付款金额不能为空.");
		validateResult.put("goodsModel", "商品型号不能为空.");
		validateResult.put("addressId", "联系地址不能为空id");
		if (!validateParamBlank(request, response, validateResult))
			return;

		GoodsOrder goodsOrder = this.confirmOrder(request);
		if (goodsOrder.getId() == null) {
			JSONUtils.ERROR(response, "保存商品订单失败.");
			return;
		}

		JSONUtils.SUCCESS(response, goodsOrder.getId());
	}

	/**
	 * 确认订单
	 * 
	 * @param request
	 * @return
	 */
	protected GoodsOrder confirmOrder(HttpServletRequest request) {
		// 生成订单
		GoodsOrder goodsOrder = BeanUtils.mapConvertToBean(GoodsOrder.class,
				request);
		String addressId = request(request, "addressId");
		confirmOrder(goodsOrder, addressId);
		return goodsOrder;
	}

	/**
	 * 确认订单
	 * 
	 * @param goodsOrder
	 */
	protected void confirmOrder(GoodsOrder goodsOrder) {
		// 生成订单
		String addressId = String.valueOf(goodsOrder.getAddressId());
		confirmOrder(goodsOrder, addressId);
	}

	/**
	 * 确认订单
	 * 
	 * @param goodsOrder
	 * @param addressId
	 */
	private void confirmOrder(GoodsOrder goodsOrder, String addressId) {
		Address address = addressService.queryAddressById(addressId);
		goodsOrder.setAddress(address.getAddress());
		goodsOrder.setContacts(address.getContracts());
		goodsOrder.setPhone(address.getPhone());
		goodsOrder.setCreateDate(new Date());
		goodsOrder.setState(State.TODO.getValue());// 待付款
		mallService.saveGoodsOrder(goodsOrder);
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
		validateResult.put("orderId", "订单Id不能为空");
		validateResult.put("userId", "用户id不能为空");
		validateResult.put("amount", "付款金额不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		GoodsOrder goodsOrder = confirmPayment(request);
		if (goodsOrder == null) {
			JSONUtils.ERROR(response, "当前的逍遥币不足以支付当前商品.");
			return;
		}

		if (goodsOrder.getId() == null) {
			JSONUtils.ERROR(response, "保存商品订单失败.");
			return;
		}

		JSONUtils.SUCCESS(response, goodsOrder.getId());
	}

	/**
	 * 确认支付逻辑
	 * 
	 * @param userId
	 * @param amount
	 * @param orderId
	 * @return
	 */
	private GoodsOrder confirmPayment(String userId, String amount,
			String orderId) {
		List<Person> persons = personManageService.queryPersonByUserId(Integer
				.parseInt(userId));
		if (CollectionUtils.isNotEmpty(persons)) {
			Person person = persons.get(0);
			if ((new BigDecimal(amount)).compareTo(person.getBill()) > 0) {
				return null;
			}
			// 扣除逍遥币
			person.setBill(person.getBill().subtract(new BigDecimal(amount)));
			personManageService.updatePersonByPrimaryKey(person);
		}
		// 生成订单
		GoodsOrder goodsOrder = mallService.queryGoodsOrderById(orderId);
		goodsOrder.setPayDate(new Date());
		goodsOrder.setState(State.PAYING.getValue());// 订单已付款
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		goodsOrder.setNumber("XYP-" + sdf.format(new Date()));
		mallService.saveGoodsOrder(goodsOrder);
		return goodsOrder;
	}

	/**
	 * 确认付款
	 * 
	 * @param param
	 * @return
	 */
	protected GoodsOrder confirmPayment(Map<String, Object> param) {
		// 扣减逍遥币
		String userId = String.valueOf(param.get("userId"));
		// 付款金额
		String amount = String.valueOf(param.get("amount"));
		// 订单Id
		String orderId = String.valueOf(param.get("orderId"));

		return this.confirmPayment(userId, amount, orderId);
	}

	/**
	 * 确认付款
	 * 
	 * @param request
	 * @return
	 */
	protected GoodsOrder confirmPayment(HttpServletRequest request) {
		// 扣减逍遥币
		String userId = request(request, "userId");
		// 付款金额
		String amount = request(request, "amount");
		// 订单Id
		String orderId = request(request, "orderId");

		return this.confirmPayment(userId, amount, orderId);
	}

	/**
	 * 待收货的订单信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("todoGetGoods")
	public void todoGetGoods(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String userId = request(request, "userId");
		JSONUtils.SUCCESS(response, mallService.queryTodoGetGoods(userId));
	}

	/**
	 * 待发货
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("todoSendGoods")
	public void todoSendGoods(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String userId = request(request, "userId");
		JSONUtils.SUCCESS(response, mallService.queryTodoSendGoods(userId));
	}

	/**
	 * 获取售后订单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("querySellAfterOrders")
	public void querySellAfterOrders(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String userId = request(request, "userId");
		JSONUtils.SUCCESS(response, mallService.querySellAfterGoods(userId));
	}

	/**
	 * 获取退货订单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryReturnOrders")
	public void queryReturnOrders(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String userId = request(request, "userId");
		JSONUtils.SUCCESS(response, mallService.queryReturnGoods(userId));
	}

	/**
	 * 待评价
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("todoComments")
	public void todoComments(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String userId = request(request, "userId");
		JSONUtils.SUCCESS(response, mallService.queryTodoComments(userId));
	}

	/**
	 * 评价
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("comments")
	public void comments(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		validateResult.put("content", "评论内容不能为空.");
		validateResult.put("orderId", "订单id不能为空.");
		validateResult.put("score", "评分不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String content = request(request, "content");
		if (content.length() > 500) {
			JSONUtils.PARAM_ERROR(response, "评论内容不能大于500字.");
			return;
		}

		Comment comment = BeanUtils.mapConvertToBean(Comment.class, request);
		mallService.addComments(comment);
		JSONUtils.SUCCESS(response, "评论成功.");
	}

	/**
	 * 获取评论商品评论
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getComments")
	public void getComments(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("goodsId", "用户id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String goodsId = request(request, "goodsId");
		JSONUtils.SUCCESS(response, mallService.getComments(goodsId));
	}

	/**
	 * 退货
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("returnSale")
	public void returnSale(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户id不能为空");
		validateResult.put("orderId", "订单id不能为空.");
		validateResult.put("state", "订单状态不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String orderId = request(request, "orderId");
		String state = request(request, "state");// 值必须为sales_return,after_sale(退货和售后)

		GoodsOrder order = new GoodsOrder();
		order.setState(state);
		order.setId(Integer.parseInt(orderId));
		mallService.updateGoodsOrderByPK(order);
		JSONUtils.SUCCESS(response, "申请退货成功.");
	}

	/**
	 * 删除订单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("deleteOrder")
	public void deleteOrder(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("orderId", "订单id不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String orderId = request(request, "orderId");
		mallService.deleteOrder(orderId);
		JSONUtils.SUCCESS(response, "删除订单成功");
	}

	/**
	 * 查询订单通过状态
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryOrderByState")
	public void queryOrderByState(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("state", "订单状态不能为空");
		validateResult.put("userId", "用户id不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String state = request(request, "state");// 订单状态多个以逗号隔开
		Integer userId = Integer.parseInt(request(request, "userId"));
		List<String> states = Arrays.asList(state.split(","));
		List<GoodsOrder> orders = mallService.queryGoodsOrderByState(states,
				userId);
		JSONUtils.SUCCESS(response, orders);
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
