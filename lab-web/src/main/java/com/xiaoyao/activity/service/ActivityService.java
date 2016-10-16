/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.activity.dao.ActivityMapperExt;
import com.xiaoyao.activity.dao.ActivityPersonMapper;
import com.xiaoyao.activity.model.Activity;
import com.xiaoyao.activity.model.ActivityExample;
import com.xiaoyao.activity.model.ActivityPerson;
import com.xiaoyao.activity.model.ActivityPersonExample;
import com.xiaoyao.activity.model.ActivityQuery;
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
public class ActivityService extends BaseService<Activity> {

	/** 注入ActivityMapper */
	@Autowired
	private ActivityMapperExt activityMapperExt;

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

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ActivityService.class);

	/**
	 * 新增活动
	 * 
	 * @param activity
	 * @return
	 */
	public boolean insertActivity(Activity activity) {
		int count = activityMapperExt.insertSelective(activity);
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
	public List<Activity> queryAllActivity(String pageSize, String pageNo) {
		ActivityExample example = new ActivityExample();
		setPaging(pageSize, pageNo, example);
		// 门派活动
		example.or().andTypeEqualTo(ActivityType.SCHOOL_ACTIVITY.getValue());
		return this.queryData(example);
	}

	/**
	 * 获取所有悬赏任务
	 * 
	 * @return
	 */
	public List<Activity> queryAllTask(String pageSize, String pageNo) {
		ActivityExample example = new ActivityExample();
		setPaging(pageSize, pageNo, example);
		// 悬赏任务
		example.or().andTypeEqualTo(ActivityType.REWARD_TASK.getValue());
		return this.queryData(example);
	}

	/**
	 * 获取所有出售服务
	 * 
	 * @return
	 */
	public List<Activity> queryAllService(String pageSize, String pageNo) {
		ActivityExample example = new ActivityExample();
		setPaging(pageSize, pageNo, example);
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
		List<Activity> lst = activityMapperExt.selectByExampleByPage(example);
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
	 * 我发布的活动
	 * 
	 * @param userId
	 * @return
	 */
	public Set<Activity> ipublish(Integer userId) {
		Person person = new Person();
		person.setUserId(userId);
		List<ActivityQuery> lst = activityMapperExt.ipublish(person);

		return this.convertData(lst);
	}

	/**
	 * 我参与的活动
	 * 
	 * @param userId
	 * @return
	 */
	public Set<Activity> ijoin(Integer userId) {
		Person person = new Person();
		person.setUserId(userId);
		List<ActivityQuery> lst = activityMapperExt.ijoin(person);

		return this.convertData(lst);
	}

	/**
	 * 发布图片urls平铺为urls数组数据转换<br>
	 * 出于性能考虑,否则可以简单处理直接后台数据库查询两遍,此处只查了一遍数据库.
	 * 
	 * @param lst
	 * @return
	 */
	private Set<Activity> convertData(List<ActivityQuery> lst) {
		Set<Activity> activitys = new HashSet<Activity>();
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		for (ActivityQuery ac : lst) {
			try {
				Activity obj = Activity.class.newInstance();
				Field[] fields = ac.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.getName().equals("urls")) {
						if (map.containsKey(ac.getId())) {
							List<String> urls = map.get(ac.getId());
							if (field.get(ac) != null) {
								urls.add(this.wrapperURL(field.get(ac)));
							}
							map.put(ac.getId(), urls);
						} else {
							List<String> urls = new ArrayList<String>();
							if (field.get(ac) != null) {
								urls.add(this.wrapperURL(field.get(ac)));
							}
							map.put(ac.getId(), urls);
						}
						continue;
					}
					String value = StringUtils.capitalize(field.getName());
					Method method = obj.getClass().getMethod("set" + value,
							field.getType());
					method.invoke(obj, field.get(ac));
				}
				activitys.add(obj);
			} catch (InstantiationException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (SecurityException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		for (Activity ac : activitys) {
			ac.setUrls(map.get(ac.getId()).toArray(new String[] {}));
		}
		return activitys;
	}

	/**
	 * 包装URL为绝对路径
	 * 
	 * @param obj
	 * @return
	 */
	private String wrapperURL(Object value) {
		return UploadFileUtil.convertToFileHttpURL(String.valueOf(value),
				"upload");
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
