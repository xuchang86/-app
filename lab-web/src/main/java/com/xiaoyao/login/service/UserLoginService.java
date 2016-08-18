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
import org.springframework.util.CollectionUtils;

import com.xiaoyao.login.dao.UserMapper;
import com.xiaoyao.login.model.User;

/**
 * 用户登录服务
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月18日 许畅 新建
 */
@Service
public class UserLoginService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 校验当前用户是否已注册
	 * 
	 * @param user
	 *            注册用户
	 * @return
	 */
	public boolean verifyRegist(User user) {
		List<User> lst = userMapper.verifyRegist(user);
		return CollectionUtils.isEmpty(lst) ? false : true;
	}

}
