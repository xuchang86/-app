/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.dao;

import java.util.List;

import com.xiaoyao.activity.model.ActivityPerson;
import com.xiaoyao.activity.model.ActivityPersonExample;

/**
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年12月25日 许畅 新建
 */
public interface ActivityPersonMapperExt extends ActivityPersonMapper {

	/** 分页查询 */
	List<ActivityPerson> selectByExampleByPage(ActivityPersonExample example);

}
