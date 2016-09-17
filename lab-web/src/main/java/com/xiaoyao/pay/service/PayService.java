/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.pay.dao.OrderMapper;
import com.xiaoyao.pay.model.Order;

/**
 * 付款服务
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
@Service
public class PayService extends BaseService<Order> {

	/** 注入OrderMapper */
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 保存订单信息
	 * 
	 * @param order
	 * @return
	 */
	public boolean saveOrder(Order order) {

		return wrapperReturnVal(orderMapper.insertSelective(order));
	}

}
