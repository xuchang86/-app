/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.mall.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.mall.dao.CommentMapper;
import com.xiaoyao.mall.dao.GoodsMapperExt;
import com.xiaoyao.mall.dao.GoodsOrderMapper;
import com.xiaoyao.mall.dao.TypeMapper;
import com.xiaoyao.mall.model.Comment;
import com.xiaoyao.mall.model.CommentExample;
import com.xiaoyao.mall.model.Goods;
import com.xiaoyao.mall.model.GoodsExample;
import com.xiaoyao.mall.model.GoodsOrder;
import com.xiaoyao.mall.model.GoodsOrderExample;
import com.xiaoyao.mall.model.State;
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

	/** 注入 CommentMapper */
	@Autowired
	private CommentMapper commentMapper;

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
	 * 添加评论
	 * 
	 * @param comment
	 * @return
	 */
	public int addComments(Comment comment) {
		GoodsOrder order = goodsOrderMapper.selectByPrimaryKey(comment
				.getOrderId());
		String[] goodsIds = order.getGoodsId().split(",");
		for (String goodsId : goodsIds) {
			Comment cmt = new Comment();
			cmt.setCreateDate(new Date());
			cmt.setContent(comment.getContent());
			cmt.setGoodsId(Integer.parseInt(goodsId));
			cmt.setOrderId(order.getId());
			cmt.setUserId(comment.getUserId());
			commentMapper.insertSelective(cmt);
		}
		return 0;
	}

	/**
	 * 通过订单id查询订单
	 * 
	 * @param id
	 * @return
	 */
	public GoodsOrder queryGoodsOrderById(String id) {
		return goodsOrderMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	/**
	 * 
	 * @param record
	 * @return
	 */
	public int updateGoodsOrderByPK(GoodsOrder record) {

		return goodsOrderMapper.updateByPrimaryKeySelective(record);
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
	 * 查询待收货订单信息
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public List<GoodsOrder> queryTodoGetGoods(String userId) {
		GoodsOrderExample example = new GoodsOrderExample();
		List<String> values = new ArrayList<String>();
		values.add(State.PAYING.getValue());
		values.add(State.TO.getValue());
		example.or().andUserIdEqualTo(Integer.parseInt(userId))
				.andStateIn(values);

		return queryTodoGoods(example);
	}

	/**
	 * 查询待发货的订单信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<GoodsOrder> queryTodoSendGoods(String userId) {
		GoodsOrderExample example = new GoodsOrderExample();
		example.or().andUserIdEqualTo(Integer.parseInt(userId))
				.andStateEqualTo(State.PAYING.getValue());

		return queryTodoGoods(example);
	}

	/**
	 * 查询待评价的订单
	 * 
	 * @param userId
	 * @return
	 */
	public List<GoodsOrder> queryTodoComments(String userId) {
		GoodsOrderExample example = new GoodsOrderExample();
		example.or().andUserIdEqualTo(Integer.parseInt(userId))
				.andStateEqualTo(State.GET.getValue());

		return queryTodoComments(example);
	}

	/**
	 * 查询是否已评价
	 * 
	 * @param example
	 * @return
	 */
	private List<GoodsOrder> queryTodoComments(GoodsOrderExample example) {
		List<GoodsOrder> todoComments = new ArrayList<GoodsOrder>();
		List<GoodsOrder> orders = goodsOrderMapper.selectByExample(example);
		for (GoodsOrder order : orders) {
			CommentExample cmtExample = new CommentExample();
			cmtExample.or().andOrderIdEqualTo(order.getId());
			if (commentMapper.countByExample(cmtExample) > 0)// 判断是否已评价
				continue;

			String[] goodsIds = order.getGoodsId().split(",");
			List<Goods> goodses = new ArrayList<Goods>();
			for (String goodsId : goodsIds) {
				Goods goods = goodsMapper.selectByPrimaryKey(Integer
						.parseInt(goodsId));
				goodses.add(goods);
			}
			order.setGoods(goodses);
			todoComments.add(order);
		}
		return todoComments;
	}

	/**
	 * 查询待办数据
	 * 
	 * @param example
	 * @return
	 */
	private List<GoodsOrder> queryTodoGoods(GoodsOrderExample example) {
		List<GoodsOrder> orders = goodsOrderMapper.selectByExample(example);
		for (GoodsOrder order : orders) {
			String[] goodsIds = order.getGoodsId().split(",");
			List<Goods> goodses = new ArrayList<Goods>();
			for (String goodsId : goodsIds) {
				Goods goods = goodsMapper.selectByPrimaryKey(Integer
						.parseInt(goodsId));
				goodses.add(goods);
			}
			order.setGoods(goodses);
		}
		return orders;
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
