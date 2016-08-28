/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyao.activity.model.Activity;
import com.xiaoyao.activity.model.ActivityPerson;
import com.xiaoyao.activity.service.ActivityService;
import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.util.BeanUtils;
import com.xiaoyao.base.util.JSONUtils;

/**
 * 活动Controller
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivityController extends BizBaseController {

	/** 注入ActivityService */
	@Autowired
	private ActivityService activityService;

	/**
	 * 发布活动
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("publish")
	public void publish(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("type", "活动类型不能为空.");
		validateResult.put("address", "活动地址不能为空.");
		validateResult.put("content", "活动内容不能为空");
		validateResult.put("date", "活动时间不能为空.");
		validateResult.put("cost", "发布费用不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		// 保存发布信息
		Activity activity = BeanUtils.mapConvertToBean(Activity.class, request);
		activity.setPersonId(getCurrentPerson(request).getId());
		if (activityService.insertActivity(activity)) {
			JSONUtils.SUCCESS(response, "发布信息成功.");
		} else {
			JSONUtils.ERROR(response, "发布信息失败.");
		}
	}

	/**
	 * 申请加入活动
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("applyToJoin")
	public void applyToJoin(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("personId", "申请人id不能为空.");
		validateResult.put("activityId", "活动id不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String personId = request(request, "personId");
		String activityId = request(request, "activityId");

		ActivityPerson person = new ActivityPerson();
		person.setActivityId(Integer.valueOf(activityId));
		person.setPersonId(Integer.valueOf(personId));
		// 校验当前申请人不能重复申请该活动
		if (activityService.isExsitActivityPerson(person)) {
			JSONUtils.ERROR(response, "当前申请人不能重复参加该活动.");
			return;
		}
		// 新增活动参与人
		activityService.insertActivityPerson(person);
		JSONUtils.SUCCESS(response, "申请活动成功.");
	}

	/**
	 * 查询所有的门派活动
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryAllActivity")
	public void queryAllActivity(HttpServletRequest request,
			HttpServletResponse response) {
		JSONUtils.toJSONString(response, activityService.queryAllActivity());
	}

	/**
	 * 查询所有悬赏任务
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryAllTask")
	public void queryAllTask(HttpServletRequest request,
			HttpServletResponse response) {
		List<Activity> result = activityService.queryAllTask();
		JSONUtils.toJSONString(response, result);
	}

	/**
	 * 查询所有的出售服务
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryAllService")
	public void queryAllService(HttpServletRequest request,
			HttpServletResponse response) {
		long start = System.currentTimeMillis();
		List<Activity> result = activityService.queryAllService();
		JSONUtils.toJSONString(response, result);
		long end = System.currentTimeMillis();
		System.out.println("total time:" + (end - start) / 1000 + "秒");
	}
}
