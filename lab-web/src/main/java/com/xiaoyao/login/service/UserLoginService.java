/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.xiaoyao.base.model.Level;
import com.xiaoyao.base.model.Person;
import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.login.dao.UserMapperExt;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.model.UserExample;

/**
 * 用户登录服务
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月18日 许畅 新建
 */
@Service
public class UserLoginService extends BaseService {

	/** 注入UserMapper */
	@Autowired
	private UserMapperExt userMapperExt;

	/** 注入InviteCodeService */
	@Autowired
	private InviteCodeService inviteCodeService;

	/** 注入PersonManageService */
	@Autowired
	private PersonManageService personManageService;

	/**
	 * 校验当前用户是否已注册
	 * 
	 * @param user
	 *            注册用户
	 * @return
	 */
	public boolean verifyRegist(User user) {
		List<User> lst = userMapperExt.verifyRegist(user);
		return CollectionUtils.isEmpty(lst) ? false : true;
	}

	/**
	 * 保存User信息并删除验证码
	 * 
	 * @param user
	 *            用户信息
	 * @param code
	 *            邀请码
	 * @return
	 */
	public Person saveUser(User user, String code) {
		// 保存用户
		if (StringUtils.isEmpty(user.getId())) {
			userMapperExt.insertSelective(user);
		} else {
			UserExample example = new UserExample();
			example.or().andIdEqualTo(user.getId());
			userMapperExt.updateByExampleSelective(user, example);
		}
		// 删除邀请码
		inviteCodeService.deleteByCode(code);
		// 新增Person信息
		Person person = new Person();
		person.setBill(BigDecimal.ZERO);
		person.setCreateDate(new Date());
		person.setName(user.getName());
		person.setLevel(Level.JIAN_XI_DIZI.getValue());// 见习弟子
		person.setUserId(user.getId());
		personManageService.insertPerson(person);
		return person;
	}

	/**
	 * 更新User信息
	 * 
	 * @param user
	 *            user
	 * @return
	 */
	public boolean updateUser(User user) {
		UserExample example = new UserExample();
		example.or().andPhoneEqualTo(user.getPhone());
		// 更新User表信息
		int result = userMapperExt.updateByExampleSelective(user, example);
		// 更新人员信息名称
		userMapperExt.updatePerson(user);
		return wrapperReturnVal(result);
	}

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public List<User> login(String username, String password) {
		// 查询是否已注册付款
		User user = new User();
		user.setUsername(username);
		user.setPhone(username);
		user.setPassword(password);
		return userMapperExt.login(user);
	}

	/**
	 * 更新已付款
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateIsPay(User user) {

		return wrapperReturnVal(userMapperExt.updateisPay(user));
	}

	public User queryUserByPrimaryKey(Integer pk) {
		return userMapperExt.selectByPrimaryKey(pk);
	}

}
