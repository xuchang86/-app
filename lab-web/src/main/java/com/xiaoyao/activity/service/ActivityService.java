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
import java.math.BigDecimal;
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
import com.xiaoyao.activity.dao.ActivityPersonMapperExt;
import com.xiaoyao.activity.model.Activity;
import com.xiaoyao.activity.model.ActivityExample;
import com.xiaoyao.activity.model.ActivityPayWay;
import com.xiaoyao.activity.model.ActivityPerson;
import com.xiaoyao.activity.model.ActivityPersonExample;
import com.xiaoyao.activity.model.ActivityPersonExample.Criteria;
import com.xiaoyao.activity.model.ActivityQuery;
import com.xiaoyao.activity.model.ActivityType;
import com.xiaoyao.activity.model.TaskState;
import com.xiaoyao.base.model.Person;
import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.login.model.Sex;
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
	private ActivityPersonMapperExt activityPersonMapper;

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
	 * 查询参加活动人
	 * 
	 * @param activityId
	 * @return
	 */
	public List<ActivityPerson> queryJoinedPersons(int activityId,
			String personId, String state, String pageSize, String pageNo) {
		ActivityPersonExample example = new ActivityPersonExample();
		Criteria c = example.or();
		c.andActivityIdEqualTo(activityId);
		if (StringUtils.isNotBlank(state)) {
			c.andStateEqualTo(state);
		}

		if (StringUtils.isNotBlank(personId)) {
			c.andPersonIdEqualTo(Integer.parseInt(personId));
		}

		setPaging(pageSize, pageNo, example);
		List<ActivityPerson> lst = activityPersonMapper
				.selectByExampleByPage(example);
		for (ActivityPerson acPerson : lst) {
			Person person = personManageService
					.queryPerson4ByPrimaryKey(acPerson.getPersonId());
			acPerson.setUser(person.getUser());
			person.setUser(null);
			acPerson.setPerson(person);
		}
		return lst;
	}

	/**
	 * 更新参与人任务状态
	 * 
	 * @param ids
	 * @param state
	 */
	public void updateJoinedPersonState(String[] ids, TaskState state) {
		for (String id : ids) {
			ActivityPerson acPerson = activityPersonMapper
					.selectByPrimaryKey(Integer.parseInt(id));
			acPerson.setState(state.getName());
			activityPersonMapper.updateByPrimaryKeySelective(acPerson);
		}
	}

	/**
	 * 查询活动信息
	 * 
	 * @param id
	 * @return
	 */
	public Activity queryActivityByPK(String id) {
		return activityMapperExt.selectByPrimaryKey(Integer.parseInt(id));
	}

	/**
	 * 查询参与人
	 * 
	 * @param id
	 * @return
	 */
	public ActivityPerson queryActivityPersonByPk(String id) {
		return activityPersonMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	/**
	 * 悬赏任务付款
	 * 
	 * @param publishPerson
	 * @param joinedPersonIds
	 * @param cost
	 *            悬赏任务单个成本
	 */
	public void payRewardTask(Person publishPerson, String[] joinedPersonIds,
			BigDecimal cost) {
		for (String joinedPersonId : joinedPersonIds) {
			ActivityPerson acPerson = activityPersonMapper
					.selectByPrimaryKey(Integer.parseInt(joinedPersonId));
			// 悬赏参与人逍遥币
			Person person = personManageService
					.queryPersonByPrimaryKey(acPerson.getPersonId());
			person.setBill(person.getBill().add(cost));
			personManageService.updatePersonByPrimaryKey(person);

			// 更新参与人状态
			acPerson.setState(TaskState.END.getName());
			activityPersonMapper.updateByPrimaryKeySelective(acPerson);
		}

		// 扣减发布人逍遥币
		publishPerson.setBill(publishPerson.getBill().subtract(
				cost.multiply(BigDecimal.valueOf(joinedPersonIds.length))));
		personManageService.updatePersonByPrimaryKey(publishPerson);
	}

	/**
	 * 付款服务
	 * 
	 * @param activity
	 * @param acPerson
	 */
	public void payService(Activity activity, ActivityPerson acPerson) {
		BigDecimal cost = activity.getCost();

		// 更新参与人状态
		this.updateEndState(acPerson);

		// 减少参与人逍遥币
		Person buyer = acPerson.getPerson();
		buyer.setBill(buyer.getBill().subtract(cost));
		personManageService.updatePersonByPrimaryKey(buyer);

		// 增加卖家逍遥币
		Person seller = personManageService.queryPersonByPrimaryKey(activity
				.getPersonId());
		seller.setBill(seller.getBill().add(cost));
		personManageService.updatePersonByPrimaryKey(seller);
	}

	/**
	 * 支付门派活动费用
	 * 
	 * @param activity
	 * @param acPerson
	 * @param payway
	 */
	public void payShoolActivity(Activity activity, ActivityPerson acPerson,
			String payway) {
		if (ActivityPayWay.AA.getValue().equals(payway)) {
			this.payService(activity, acPerson);
			return;
		} else if (ActivityPayWay.MAN_A_WOMAN_FREE.getValue().equals(payway)) {
			if (acPerson.getPerson().getUser().getSex()
					.equals(Sex.MAN.getValue())) {
				this.payService(activity, acPerson);
				return;
			}
		} else if (ActivityPayWay.WOMAN_A_MAN_FREE.getValue().equals(payway)) {
			if (acPerson.getPerson().getUser().getSex()
					.equals(Sex.WOMAN.getValue())) {
				this.payService(activity, acPerson);
				return;
			}
		}
		this.updateEndState(acPerson);
	}

	/**
	 * 更新任务状态为已完成
	 * 
	 * @param acPerson
	 */
	private void updateEndState(ActivityPerson acPerson) {
		// 更新参与人状态
		acPerson.setState(TaskState.END.getName());
		activityPersonMapper.updateByPrimaryKeySelective(acPerson);
	}

	/**
	 * 获取所有活动信息
	 * 
	 * @return
	 */
	public List<Activity> queryAllActivity(String pageSize, String pageNo,
			String city, String orderByClause) {
		ActivityExample example = new ActivityExample();
		setPaging(pageSize, pageNo, example);
		if (StringUtils.isBlank(city)) {
			// 门派活动
			example.or()
					.andTypeEqualTo(ActivityType.SCHOOL_ACTIVITY.getValue());
		} else {
			// 门派活动
			example.or()
					.andTypeEqualTo(ActivityType.SCHOOL_ACTIVITY.getValue())
					.andCityEqualTo(city);
		}
		example.setOrderByClause(orderByClause);
		return this.queryData(example);
	}

	/**
	 * 获取所有悬赏任务
	 * 
	 * @return
	 */
	public List<Activity> queryAllTask(String pageSize, String pageNo,
			String city, String orderByClause) {
		ActivityExample example = new ActivityExample();
		setPaging(pageSize, pageNo, example);
		if (StringUtils.isBlank(city)) {
			// 悬赏任务
			example.or().andTypeEqualTo(ActivityType.REWARD_TASK.getValue());
		} else {
			// 悬赏任务
			example.or().andTypeEqualTo(ActivityType.REWARD_TASK.getValue())
					.andCityEqualTo(city);
		}
		example.setOrderByClause(orderByClause);
		return this.queryData(example);
	}

	/**
	 * 获取所有出售服务
	 * 
	 * @return
	 */
	public List<Activity> queryAllService(String pageSize, String pageNo,
			String city, String orderByClause) {
		ActivityExample example = new ActivityExample();
		setPaging(pageSize, pageNo, example);
		if (StringUtils.isBlank(city)) {
			// 出售服务
			example.or().andTypeEqualTo(ActivityType.SALE_SERVICE.getValue());
		} else {
			// 出售服务
			example.or().andTypeEqualTo(ActivityType.SALE_SERVICE.getValue())
					.andCityEqualTo(city);
		}
		example.setOrderByClause(orderByClause);
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
			activity.setUser(userLoginService.queryUserByPerson(activity
					.getPersonId()));
		}
		return lst;
	}

	/**
	 * 我发布的活动
	 * 
	 * @param userId
	 * @return
	 */
	public List<Activity> ipublish(Integer userId) {
		Person person = new Person();
		person.setUserId(userId);
		List<ActivityQuery> lst = activityMapperExt.ipublish(person);

		// 去除发布的活动重复数据
		List<Activity> activitys = this.convertData(lst);
		List<Activity> reallyActivitys = new ArrayList<Activity>();
		Map<Integer, Activity> acMap = new HashMap<Integer, Activity>();
		for (Activity ac : activitys) {
			if (!acMap.containsKey(ac.getId())) {
				acMap.put(ac.getId(), ac);
				reallyActivitys.add(ac);
			}
		}
		return reallyActivitys;
	}

	/**
	 * 我参与的活动
	 * 
	 * @param userId
	 * @return
	 */
	public List<Activity> ijoin(Integer userId) {
		Person person = new Person();
		person.setUserId(userId);
		List<ActivityQuery> lst = activityMapperExt.ijoin(person);
		List<Activity> activitys = this.convertData(lst);

		// 去除参与人中重复数据
		List<ActivityPerson> acpersons = new ArrayList<ActivityPerson>();
		Map<Integer, ActivityQuery> acQueryMap = new HashMap<Integer, ActivityQuery>();
		for (ActivityQuery acQuery : lst) {
			if (!acQueryMap.containsKey(acQuery.getJoinedId())) {
				acQueryMap.put(acQuery.getJoinedId(), acQuery);

				ActivityPerson acperson = new ActivityPerson();
				acperson.setId(acQuery.getJoinedId());
				acperson.setState(acQuery.getState());
				acperson.setActivityId(acQuery.getId());
				acpersons.add(acperson);
			}
		}

		// 建立映射
		Map<Integer, List<ActivityPerson>> map = new HashMap<Integer, List<ActivityPerson>>();
		for (ActivityPerson p : acpersons) {
			if (map.containsKey(p.getActivityId())) {
				map.get(p.getActivityId()).add(p);
			} else {
				List<ActivityPerson> acs = new ArrayList<ActivityPerson>();
				acs.add(p);
				map.put(p.getActivityId(), acs);
			}
		}

		// 去除活动中重复数据
		List<Activity> reallyAcititys = new ArrayList<Activity>();
		Map<Integer, Activity> acMap = new HashMap<Integer, Activity>();
		// 设置活动参与人信息
		for (Activity ac : activitys) {
			if (!acMap.containsKey(ac.getId())) {
				acMap.put(ac.getId(), ac);
				ac.setActivityPerson(map.get(ac.getId()));
				reallyAcititys.add(ac);
			}
		}

		return reallyAcititys;
	}

	/**
	 * 发布图片urls平铺为urls数组数据转换
	 * <p>
	 * 出于性能考虑,否则可以简单处理直接后台数据库查询两遍,此处只查了一遍数据库.
	 * 
	 * @param lst
	 * @return
	 */
	private List<Activity> convertData(List<ActivityQuery> lst) {
		List<Activity> activitys = new ArrayList<Activity>();
		Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
		for (ActivityQuery ac : lst) {
			try {
				Activity obj = Activity.class.newInstance();
				Field[] fields = ac.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					// urls特殊处理
					if (field.getName().equals("urls")) {
						if (map.containsKey(ac.getId())) {
							if (field.get(ac) != null) {
								map.get(ac.getId()).add(
										this.wrapperURL(field.get(ac)));
							}
						} else {
							Set<String> urls = new HashSet<String>();
							if (field.get(ac) != null) {
								urls.add(this.wrapperURL(field.get(ac)));
							}
							map.put(ac.getId(), urls);
						}
					}

					// state, joinedId ,urls特殊处理
					if (field.getName().equals("state")
							|| field.getName().equals("joinedId")
							|| field.getName().equals("urls")) {
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
			} catch (NoSuchMethodException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (SecurityException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (IllegalArgumentException e) {
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
