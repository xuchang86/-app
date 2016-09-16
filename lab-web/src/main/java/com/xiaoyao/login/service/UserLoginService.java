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

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easemob.server.example.comm.utils.EmchatOperator;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.xiaoyao.base.model.Level;
import com.xiaoyao.base.model.Person;
import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.login.dao.UserMapperExt;
import com.xiaoyao.login.model.InviteCode;
import com.xiaoyao.login.model.IsPay;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.model.UserExample;
import com.xiaoyao.login.util.LoginUtil;
import com.xiaoyao.pay.service.CashPoolService;

/**
 * 用户登录服务
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月18日 许畅 新建
 */
@Service
public class UserLoginService extends BaseService<User> {

	/** 注入UserMapper */
	@Autowired
	private UserMapperExt userMapperExt;

	/** 注入InviteCodeService */
	@Autowired
	private InviteCodeService inviteCodeService;

	/** 注入PersonManageService */
	@Autowired
	private PersonManageService personManageService;

	/** 注入 CashPoolService */
	@Autowired
	private CashPoolService cashPoolService;

	/** 日志记录 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserLoginService.class);

	/**
	 * 校验是已注册和已付款
	 * 
	 * @param user
	 *            注册用户
	 * @return
	 */
	public boolean verifyRegist(User user) {
		List<User> lst = userMapperExt.verifyRegist(user);
		if (CollectionUtils.isNotEmpty(lst)
				&& lst.get(0).getIspay() == IsPay.IS_PAY.getValue()) {
			return true;
		}
		return false;
	}

	/**
	 * 校验当前用户是否已注册
	 * 
	 * @param user
	 * @return
	 */
	public boolean isRegist(User user) {
		List<User> lst = userMapperExt.verifyRegist(user);

		return CollectionUtils.isNotEmpty(lst) ? true : false;
	}

	/**
	 * 根据条件查询VO对象
	 * 
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public List<User> queryUserByCondition(User condition) {
		List<User> users = queryVOByCondition(condition, userMapperExt);
		return users;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 *            用户信息
	 * @return
	 */
	public User saveUser(User user) {
		if (user.getId() == null) {
			userMapperExt.insertSelective(user);
		} else {
			UserExample example = new UserExample();
			example.or().andIdEqualTo(user.getId());
			userMapperExt.updateByExampleSelective(user, example);
		}
		return user;
	}

	/**
	 * 保存person信息，增加资金池等
	 * 
	 * @param user
	 *            用户信息
	 * @param inviteCode
	 *            邀请码
	 * @param parentId
	 *            师傅id
	 * @return
	 */
	public Person saveUser(User user, String inviteCode) {
		// 组装个人信息
		Person person = new Person();
		person.setBill(LoginUtil.getRegistCashPoolAmt());// 逍遥币
		person.setCreateDate(new Date());
		person.setName(user.getName());
		person.setLevel(Level.JIAN_XI_DIZI.getValue());// 见习弟子
		person.setUserId(user.getId());
		// 注册时的平台固定收入金额
		BigDecimal platformAmt = LoginUtil.getRegistPlatformAmt();

		// 设置师父信息并加入师父的聊天室
		this.saveParentInfo(user, person, inviteCode);

		// 1.新增个人信息
		personManageService.savePerson(person);

		// 2.增加资金池资金
		cashPoolService.addCashPool(person.getBill(), platformAmt);

		// 3.更新师傅等级,逍遥币,减少平台收入等信息
		personManageService.updateParentAndCashPool(person);

		return person;
	}

	/**
	 * 设置师父id并加入师父的聊天室
	 * 
	 * @param person
	 * @param inviteCode
	 */
	private void saveParentInfo(User user, Person person, String inviteCode) {
		List<InviteCode> lst = inviteCodeService
				.queryInviteCodeByNumber(inviteCode);
		if (!CollectionUtils.isEmpty(lst)) {
			InviteCode inviteInfo = lst.get(0);
			Integer parentUserId = inviteInfo.getUserId();// 师父userId
			List<Person> persons = personManageService
					.queryPersonByUserId(parentUserId);
			if (persons.size() > 0) {
				// 加入聊天室
				ResponseWrapper response = EmchatOperator
						.addBatchUsersToChatRoom(inviteInfo.getChatroomId(),
								user.getPhone());
				LOGGER.info("setParentInfo:" + response.getResponseBody());
				person.setParentId(persons.get(0).getId());
			}
		}
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
	 * 通过条件更新User
	 * 
	 * @param user
	 *            User
	 * @param example
	 *            条件
	 * @return
	 */
	public boolean updateByExampleSelective(User user, UserExample example) {
		int result = userMapperExt.updateByExampleSelective(user, example);
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

	/**
	 * 根据主键更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateByByPrimaryKey(User user) {

		return wrapperReturnVal(userMapperExt.updateByPrimaryKeySelective(user));
	}

	/**
	 * 根据用户主键查询User信息
	 * 
	 * @param pk
	 * @return
	 */
	public User queryUserByPrimaryKey(Integer pk) {
		return userMapperExt.selectByPrimaryKey(pk);
	}

	/**
	 * 根据用户主键查询User信息
	 * 
	 * @param pk
	 * @return
	 */
	public User queryUserByPrimaryKey(String pk) {
		return queryUserByPrimaryKey(Integer.parseInt(pk));
	}

}
