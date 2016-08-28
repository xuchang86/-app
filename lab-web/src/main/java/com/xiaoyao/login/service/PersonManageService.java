/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.dao.PersonMapper;
import com.xiaoyao.base.model.Person;
import com.xiaoyao.base.model.PersonExample;
import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.login.model.User;

/**
 * 人员信息管理Service
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
@Service
public class PersonManageService extends BaseService {

	/** 注入PersonMapper */
	@Autowired
	private PersonMapper personMapper;

	/**
	 * 保存Person信息
	 * 
	 * @param person
	 * @return
	 */
	public boolean insertPerson(Person person) {

		return wrapperReturnVal(personMapper.insertSelective(person));
	}

	/**
	 * 通过User获取Person信息
	 * 
	 * @param user
	 * @return
	 */
	public List<Person> getPersonByUser(User user) {
		PersonExample example = new PersonExample();
		example.or().andUserIdEqualTo(user.getId());
		return personMapper.selectByExample(example);
	}

	public Person queryPersonByPrimaryKey(Integer pk) {
		return personMapper.selectByPrimaryKey(pk);
	}

}
