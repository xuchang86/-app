/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.mall.dao.GoodsMapperExt;
import com.xiaoyao.mall.dao.GoodsOrderMapper;
import com.xiaoyao.mall.dao.TypeMapper;
import com.xiaoyao.mall.model.Goods;
import com.xiaoyao.mall.model.GoodsExample;
import com.xiaoyao.mall.model.GoodsOrder;
import com.xiaoyao.upload.util.UploadFileUtil;

/**
 * 商城service服务类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月12日 许畅 新建
 */
@Service
public class MallService extends BaseService<Goods> {

	/** 注入 GoodsMapper */
	@Autowired
	private GoodsMapperExt goodsMapper;

	/** 注入 TypeMapper */
	@Autowired
	private TypeMapper typeMapper;

	/** 注入 GoodsOrder */
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;

	/**
	 * 查询所有出售的商品
	 * 
	 * @return
	 */
	public List<Goods> queryAllGoods(String pageSize, String pageNo) {
		GoodsExample example = new GoodsExample();
		// 设置分页
		setPaging(pageSize, pageNo, example);
		example.or().andIsSaleEqualTo(true);
		List<Goods> goodses = goodsMapper.selectByExampleByPage(example);
		for (Goods goods : goodses) {
			goods.setUrl(UploadFileUtil.wrapperMallURL(goods.getUrl()));
			goods.setType(typeMapper.selectByPrimaryKey(goods.getTypeId()));
		}
		return goodses;
	}

	/**
	 * 保存商品订单信息
	 * 
	 * @param order
	 * @return
	 */
	public GoodsOrder saveGoodsOrder(GoodsOrder order) {
		if (order.getId() == null) {
			goodsOrderMapper.insertSelective(order);
		} else {
			goodsOrderMapper.updateByPrimaryKeySelective(order);
		}
		return order;
	}

	/**
	 * 更新商品订单信息
	 * 
	 * @param order
	 * @return
	 */
	public int updateGoodsOrder(GoodsOrder order) {
		return goodsOrderMapper.updateByPrimaryKeySelective(order);
	}
}
