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
import com.xiaoyao.mall.dao.AddressMapper;
import com.xiaoyao.mall.model.Address;
import com.xiaoyao.mall.model.AddressExample;

/**
 * 收货地址Service
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年11月26日 许畅 新建
 */
@Service
public class AddressService extends BaseService<Address> {

	/** 注入 addressMapper */
	@Autowired
	private AddressMapper addressMapper;

	/**
	 * 保存地址
	 * 
	 * @param vo
	 */
	public int save(Address vo) {
		return super.saveVO(vo, addressMapper);
	}

	/**
	 * 删除地址
	 * 
	 * @param id
	 * @return
	 */
	public int delete(Integer id) {
		return addressMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 查询我的收货地址
	 * 
	 * @param userId
	 * @return
	 */
	public List<Address> queryAddress(String userId) {
		AddressExample example = new AddressExample();
		example.or().andUserIdEqualTo(Integer.parseInt(userId));
		return addressMapper.selectByExample(example);
	}

	/**
	 * 查询地址通过id
	 * 
	 * @param id
	 * @return
	 */
	public Address queryAddressById(String id) {
		return addressMapper.selectByPrimaryKey(Integer.parseInt(id));
	}
}
