/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.pay.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.pay.dao.CashPoolMapper;
import com.xiaoyao.pay.model.CashPool;

/**
 * 资金池Service
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月3日 许畅 新建
 */
@Service
public class CashPoolService extends BaseService<CashPool> {

	/** 注入CashPoolMapper */
	@Autowired
	private CashPoolMapper cashPoolMapper;

	/** 资金池主键 */
	private static final Integer CASH_POOL_PRAMRYKEY = 1;

	/**
	 * 新增资金池资金
	 * 
	 * @param cashPool
	 * @return
	 */
	public boolean insertCashPool(CashPool cashPool) {

		return wrapperReturnVal(cashPoolMapper.insertSelective(cashPool));
	}

	/**
	 * 初始化资金池
	 */
	public boolean initCashPool() {
		CashPool pool = new CashPool();
		pool.setMoney(BigDecimal.ZERO);
		pool.setUserId(1);
		pool.setCreatedate(new java.sql.Date(new Date().getTime()));
		return insertCashPool(pool);
	}

	/**
	 * 增加资金池金额和平台收入金额
	 * 
	 * @param cash
	 *            资金池金额
	 * @param platform
	 *            平台收入金额
	 * @return
	 */
	public boolean addCashPool(BigDecimal cash, BigDecimal platform) {

		return wrapperReturnVal(updateCashPool(cash, platform, true));
	}

	/**
	 * 减少资金池资金 和平台收入金额
	 * 
	 * @param cash
	 *            资金池金额
	 * @param platform
	 *            平台收入金额
	 * @return
	 */
	public boolean reduceCashPool(BigDecimal cash, BigDecimal platform) {

		return wrapperReturnVal(updateCashPool(cash, platform, false));
	}

	/**
	 * 更新资金池资金
	 * 
	 * @param cash
	 *            资金池
	 * @param platform
	 *            平台收入
	 * @param isAdd
	 *            是否增加
	 * @return
	 */
	private int updateCashPool(BigDecimal cash, BigDecimal platform,
			boolean isAdd) {
		CashPool cashPool = queryCashPool(null);
		BigDecimal currentCash = cashPool.getMoney();// 资金池收入
		BigDecimal currentPlatform = cashPool.getPlatform();// 平台收入
		CashPool record = new CashPool();
		record.setId(CASH_POOL_PRAMRYKEY);
		if (isAdd) {
			record.setMoney(currentCash.add(cash));
			record.setPlatform(currentPlatform.add(platform));
		} else {
			record.setMoney(currentCash.subtract(cash));
			record.setPlatform(currentPlatform.subtract(platform));
		}
		return cashPoolMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 查询当前资金池对象
	 * 
	 * @param id
	 * @return
	 */
	public CashPool queryCashPool(Integer id) {
		if (id == null) {
			id = CASH_POOL_PRAMRYKEY;
		}
		return cashPoolMapper.selectByPrimaryKey(id);
	}
}
