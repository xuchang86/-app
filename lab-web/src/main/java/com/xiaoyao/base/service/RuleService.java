/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.dao.RuleMapper;
import com.xiaoyao.base.model.Rule;
import com.xiaoyao.base.model.RuleExample;

/**
 * 会员成长规则Service
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月21日 许畅 新建
 */
@Service
public class RuleService {

	/** 注入 RuleMapper */
	@Autowired
	private RuleMapper ruleMapper;

	/**
	 * 查询会员成长规则
	 * 
	 * @return
	 */
	public List<Rule> queryRule() {
		RuleExample example = new RuleExample();
		example.or().andIdIsNotNull();
		return ruleMapper.selectByExample(example);
	}

	/**
	 * 新增会员成长规则
	 * 
	 * @param rule
	 */
	public boolean insertRule(Rule rule) {
		return ruleMapper.insertSelective(rule) == 1 ? true : false;
	}
}
