/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.login.dao;

import java.util.List;

import com.xiaoyao.login.model.User;

/**
 * UserMapper继承扩展接口
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月21日 许畅 新建
 */
public interface UserMapperExt extends UserMapper {

	/**
	 * 校验是否已注册
	 * 
	 * @param user
	 * @return
	 */
	List<User> verifyRegist(User user);

	/**
	 * 登陆查询该用户是否已存在
	 * 
	 * @param user
	 * @return
	 */
	List<User> login(User user);

	/**
	 * 更新用户为已付款
	 * 
	 * @param user
	 * @return
	 */
	int updateisPay(User user);

	/**
	 * 更新人员名称
	 * 
	 * @param user
	 * @return
	 */
	int updatePerson(User user);

	/**
	 * 查询最大下标
	 * 
	 * @return
	 */
	int queryMaxIndex();

}
