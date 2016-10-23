/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.event.dao.EventMapper;
import com.xiaoyao.event.model.Event;
import com.xiaoyao.event.model.EventExample;
import com.xiaoyao.event.model.EventType;

/**
 * 门派事件Service
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月28日 许畅 新建
 */
@Service
public class EventService extends BaseService<Event> {

	/** 注入EventMapper */
	@Autowired
	private EventMapper eventMapper;

	/**
	 * 查询门派中的事件
	 * 
	 * @return
	 */
	public List<Event> querySchoolEvent() {
		EventExample example = new EventExample();
		example.or().andIdIsNotNull()
				.andTypeEqualTo(EventType.SCHOOL.getValue());
		return eventMapper.selectByExample(example);
	}

	/**
	 * 查询系统事件
	 * 
	 * @return
	 */
	public List<Event> querySystemEvent() {
		EventExample example = new EventExample();
		example.or().andIdIsNotNull()
				.andTypeEqualTo(EventType.SYSTEM.getValue());
		return eventMapper.selectByExample(example);
	}

}
