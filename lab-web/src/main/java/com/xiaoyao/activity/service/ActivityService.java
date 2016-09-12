/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.activity.dao.ActivityMapper;
import com.xiaoyao.activity.dao.ActivityPersonMapper;
import com.xiaoyao.activity.model.Activity;
import com.xiaoyao.activity.model.ActivityExample;
import com.xiaoyao.activity.model.ActivityPerson;
import com.xiaoyao.activity.model.ActivityPersonExample;
import com.xiaoyao.activity.model.ActivityType;
import com.xiaoyao.base.model.Person;
import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.service.PersonManageService;
import com.xiaoyao.login.service.UserLoginService;
import com.xiaoyao.upload.model.UploadFile;
import com.xiaoyao.upload.service.UploadFileService;
import com.xiaoyao.upload.util.UploadFileUtil;

/**
 * 活动服务类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
@Service
public class ActivityService extends BaseService {

	/** 注入ActivityMapper */
	@Autowired
	private ActivityMapper activityMapper;

	/** 注入ActivityPersonMapper */
	@Autowired
	private ActivityPersonMapper activityPersonMapper;

	/** 注入Person */
	@Autowired
	private PersonManageService personManageService;

	/** 注入User */
	@Autowired
	private UserLoginService userLoginService;

	/** 注入UploadFileService */
	@Autowired
	private UploadFileService uploadFileService;

	/**
	 * 新增活动
	 * 
	 * @param activity
	 * @return
	 */
	public boolean insertActivity(Activity activity) {
		int count = activityMapper.insertSelective(activity);
		if (!ArrayUtils.isEmpty(activity.getUrls())) {
			uploadFileService.updateActivityId(activity.getId(),
					activity.getUrls());
		}
		return wrapperReturnVal(count);
	}

	/**
	 * 是否存在重复参与活动
	 * 
	 * @param personId
	 * @return
	 */
	public boolean isExsitActivityPerson(ActivityPerson person) {
		ActivityPersonExample example = new ActivityPersonExample();
		example.or().andPersonIdEqualTo(person.getPersonId())
				.andActivityIdEqualTo(person.getActivityId());
		List<ActivityPerson> lst = activityPersonMapper
				.selectByExample(example);
		return lst.size() > 0 ? true : false;
	}

	/**
	 * 新增活动申请人
	 * 
	 * @param person
	 * @return
	 */
	public boolean insertActivityPerson(ActivityPerson person) {
		return wrapperReturnVal(activityPersonMapper.insertSelective(person));
	}

	/**
	 * 获取所有活动信息
	 * 
	 * @return
	 */
	public List<Activity> queryAllActivity() {
		ActivityExample example = new ActivityExample();
		// 门派活动
		example.or().andTypeEqualTo(ActivityType.SCHOOL_ACTIVITY.getValue());
		return this.queryData(example);
	}

	/**
	 * 获取所有悬赏任务
	 * 
	 * @return
	 */
	public List<Activity> queryAllTask() {
		ActivityExample example = new ActivityExample();
		// 悬赏任务
		example.or().andTypeEqualTo(ActivityType.REWARD_TASK.getValue());
		return this.queryData(example);
	}

	/**
	 * 获取所有出售服务
	 * 
	 * @return
	 */
	public List<Activity> queryAllService() {
		ActivityExample example = new ActivityExample();
		// 出售服务
		example.or().andTypeEqualTo(ActivityType.SALE_SERVICE.getValue());
		return this.queryData(example);
	}

	/**
	 * 根据不同条件获取活动数据(性能存在些问题待优化)
	 * 
	 * @param example
	 * @return
	 */
	private List<Activity> queryData(ActivityExample example) {
		List<Activity> lst = activityMapper.selectByExample(example);
		for (Activity activity : lst) {
			ActivityPersonExample pExample = new ActivityPersonExample();
			pExample.or().andActivityIdEqualTo(activity.getId());
			List<ActivityPerson> persons = activityPersonMapper
					.selectByExample(pExample);
			for (ActivityPerson p : persons) {
				Person person = personManageService.queryPersonByPrimaryKey(p
						.getPersonId());
				p.setPerson(person);
				User user = userLoginService.queryUserByPrimaryKey(person
						.getUserId());
				p.setUser(user);
			}
			activity.setActivityPerson(persons);
			activity.setUrls(wrapperURL(activity));
		}
		return lst;
	}

	/**
	 * 包装URL为正确的图片路径
	 * 
	 * @param activity
	 * @return
	 */
	private String[] wrapperURL(Activity activity) {
		List<UploadFile> files = uploadFileService
				.queryFileByActivityId(activity.getId());
		List<String> url = new ArrayList<String>();
		for (UploadFile file : files) {
			url.add(UploadFileUtil.convertToFileHttpURL(file.getName(),
					"upload"));
		}
		return url.toArray(new String[] {});
	}
}
