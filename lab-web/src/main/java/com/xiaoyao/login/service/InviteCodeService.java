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

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.login.dao.InviteCodeMapper;
import com.xiaoyao.login.model.InviteCode;
import com.xiaoyao.login.model.InviteCodeExample;
import com.xiaoyao.login.model.InviteCodeExample.Criteria;
import com.xiaoyao.login.util.LoginUtil;

/**
 * 邀请码服务
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月20日 许畅 新建
 */
@Service
public class InviteCodeService extends BaseService {

	/** 注入InviteCodeMapper */
	@Autowired
	private InviteCodeMapper inviteCodeMapper;

	/**
	 * 邀请码持久化
	 * 
	 * @param code
	 * @return
	 */
	public boolean insert(InviteCode inviteCode) {
		return inviteCodeMapper.insert(inviteCode) == 1 ? true : false;
	}

	/**
	 * 批量创建邀请码
	 */
	public int batchInsert() {
		int success = 0;
		int count = LoginUtil.getInviteCodeCount();
		for (int i = 0; i < count; i++) {
			InviteCode code = new InviteCode();
			code.setNumber(String.valueOf(LoginUtil.getSixCode()));
			if (insert(code)) {
				success++;
			}
		}
		return success;
	}

	/**
	 * 获取邀请码
	 * 
	 * @return
	 */
	public List<InviteCode> queryInviteCodeList() {
		InviteCodeExample vo = new InviteCodeExample();
		vo.or().andIdIsNotNull();
		return inviteCodeMapper.selectByExample(vo);
	}

	/**
	 * 查询邀请码
	 * 
	 * @param userId
	 * @return
	 */
	public List<InviteCode> queryInviteCode(String userId) {
		InviteCodeExample vo = new InviteCodeExample();
		vo.or().andUserIdEqualTo(Integer.parseInt(userId));
		return inviteCodeMapper.selectByExample(vo);
	}

	/**
	 * 通用邀请码查询邀请码信息
	 * 
	 * @param code
	 *            邀请码
	 * @return
	 */
	public List<InviteCode> queryInviteCodeByNumber(String code) {
		InviteCodeExample vo = new InviteCodeExample();
		vo.or().andNumberEqualTo(code);
		return inviteCodeMapper.selectByExample(vo);
	}

	/**
	 * 校验当前邀请码是否存在
	 * 
	 * @param number
	 * @return
	 */
	public boolean isExsit(String number) {
		InviteCodeExample vo = new InviteCodeExample();
		vo.or().andNumberEqualTo(number);
		List<InviteCode> lst = inviteCodeMapper.selectByExample(vo);
		return (lst != null && lst.size() > 0) ? true : false;
	}

	/**
	 * 删除邀请码
	 * 
	 * @param code
	 * @return
	 */
	public boolean deleteByCode(String code) {
		InviteCodeExample vo = new InviteCodeExample();
		Criteria c = vo.or();
		c.andNumberEqualTo(code);
		return inviteCodeMapper.deleteByExample(vo) == 1 ? true : false;
	}

}
