/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaoyao.base.dao.PersonMapperExt;
import com.xiaoyao.base.model.Person;
import com.xiaoyao.base.model.PersonExample;
import com.xiaoyao.base.model.Rule;
import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.login.model.PersonQuery;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.util.RuleOperator;
import com.xiaoyao.pay.service.CashPoolService;

/**
 * 人员信息管理Service
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
@Service
public class PersonManageService extends BaseService<Person> {

	/** 注入PersonMapper */
	@Autowired
	private PersonMapperExt personMapper;

	/** 注入CashPoolService */
	@Autowired
	private CashPoolService cashPoolService;

	/**
	 * 保存Person信息
	 * 
	 * @param person
	 * @return
	 */
	public boolean savePerson(Person person) {
		if (person.getId() == null) {
			personMapper.insertSelective(person);
		} else {
			personMapper.updateByPrimaryKeySelective(person);
		}

		return true;
	}

	/**
	 * 更新师傅信息(包括级别和逍遥币)以及资金池资金
	 * 
	 * @param person
	 *            个人信息
	 * @return
	 */
	public void updateParentAndCashPool(Person person) {
		Integer parentId = person.getParentId();
		if (!StringUtils.isEmpty(parentId)) {
			// 弟子数
			int childCount = queryChildCount(parentId);
			// 调用升级算法
			Rule rule = RuleOperator.upgrade(childCount);
			if (rule != null) {
				Person parent = queryPersonByPrimaryKey(parentId);
				parent.setLevel(rule.getLevel() + 1);// 等级
				BigDecimal bill = parent.getBill().add(
						rule.getUpgradeAwards().add(rule.getPacket()));// 升级奖励+徒弟红包

				// 查询当前弟子是否有徒弟,如果有徒弟扣取他们的逍遥币
				List<Person> childs = queryChildsByParent(person.getId());
				if (CollectionUtils.isNotEmpty(childs)) {
					for (Person child : childs) {
						// 扣减徒孙的逍遥币
						child.setBill(child.getBill().subtract(
								rule.getChildPacket()));
						this.updatePersonByPrimaryKey(child);
						// 增加徒孙红包
						bill = bill.add(rule.getChildPacket());
					}
				}

				// 更新师傅信息
				parent.setBill(bill);
				this.updatePersonByPrimaryKey(parent);
				// 减少徒弟的逍遥币
				person.setBill(person.getBill().subtract(rule.getPacket()));
				this.updatePersonByPrimaryKey(person);
				// TODO 升级奖励 :从平台收入中扣除?
				cashPoolService.reduceCashPool(BigDecimal.ZERO,
						rule.getUpgradeAwards());
			}
		}
	}

	/**
	 * 查询师傅的弟子数量
	 * 
	 * @param parentId
	 *            师傅id
	 * @return
	 */
	public int queryChildCount(Integer parentId) {
		PersonExample example = new PersonExample();
		example.or().andParentIdEqualTo(parentId);
		return personMapper.countByExample(example);
	}

	/**
	 * 查询弟子的数据集
	 * 
	 * @param parentId
	 *            师傅id
	 * @return
	 */
	public List<Person> queryChildsByParent(Integer parentId) {
		PersonExample example = new PersonExample();
		example.or().andParentIdEqualTo(parentId);
		return personMapper.selectByExample(example);
	}

	/**
	 * 通过User获取Person信息
	 * 
	 * @param user
	 * @return
	 */
	public List<Person> queryPersonByUser(User user) {
		return this.queryPersonByUserId(user.getId());
	}

	/**
	 * 通过userId查询person信息
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public List<Person> queryPersonByUserId(Integer userId) {
		PersonExample example = new PersonExample();
		example.or().andUserIdEqualTo(userId);
		return personMapper.selectByExample(example);
	}

	/**
	 * 根据主键更新个人信息
	 * 
	 * @param person
	 * @return
	 */
	public boolean updatePersonByPrimaryKey(Person person) {
		return wrapperReturnVal(personMapper
				.updateByPrimaryKeySelective(person));
	}

	/**
	 * 根据PersonId查询Person信息
	 * 
	 * @param pk
	 * @return
	 */
	public Person queryPersonByPrimaryKey(Integer pk) {
		return personMapper.selectByPrimaryKey(pk);
	}

	/**
	 * 逍遥币排行榜查询
	 * 
	 * @param pageSize
	 *            页数
	 * @param pageNo
	 *            页码
	 * @return
	 */
	public List<Person> queryTopBill(PersonQuery query) {
		return personMapper.queryTopBillByPage(query);
	}

	/**
	 * 弟子数排行榜查询
	 * 
	 * @param pageSize
	 *            页数
	 * @param pageNo
	 *            页码
	 * @return
	 */
	public List<PersonQuery> queryTopChild(PersonQuery query) {
		return personMapper.queryTopChildByPage(query);
	}

}
