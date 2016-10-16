/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.dao;

import java.util.List;

import com.xiaoyao.activity.model.Activity;
import com.xiaoyao.activity.model.ActivityExample;
import com.xiaoyao.activity.model.ActivityQuery;
import com.xiaoyao.base.model.Person;

/**
 * ActivityMapper继承接口
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月17日 许畅 新建
 */
public interface ActivityMapperExt extends ActivityMapper {

	/**
	 * 分页查询
	 * 
	 * @param example
	 * @return
	 */
	List<Activity> selectByExampleByPage(ActivityExample example);

	/**
	 * 我发布的信息
	 * 
	 * @param person
	 * @return
	 */
	List<ActivityQuery> ipublish(Person person);

	/**
	 * 我参与的活动信息
	 * 
	 * @param person
	 * @return
	 */
	List<ActivityQuery> ijoin(Person person);
}
