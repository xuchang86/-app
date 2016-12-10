/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.activity.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyao.activity.model.Activity;
import com.xiaoyao.activity.model.ActivityPerson;
import com.xiaoyao.activity.model.ActivityType;
import com.xiaoyao.activity.service.ActivityService;
import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.util.BeanUtils;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.login.model.Permission;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.service.PersonManageService;
import com.xiaoyao.login.service.UserLoginService;
import com.xiaoyao.upload.model.FileType;
import com.xiaoyao.upload.model.UploadFile;
import com.xiaoyao.upload.service.UploadFileService;
import com.xiaoyao.upload.util.UploadFileUtil;

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

	/** 注入PersonManageService */
	@Autowired
	private PersonManageService personManageService;

	/** 注入UploadFileService */
	@Autowired
	private UploadFileService uploadFileService;

	/** 注入 UserLoginService */
	@Autowired
	private UserLoginService userLoginService;

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
		validateResult.put("userId", "当前登录用户id不能为空.");
		validateResult.put("city", "城市不能为空");
		if (!validateParamBlank(request, response, validateResult))
			return;

		String userId = request(request, "userId");
		String type = request(request, "type");
		// 校验是否存在发布权限
		User user = userLoginService.queryUserByPrimaryKey(userId);
		if (user.getPermission() == null
				|| user.getPermission().equals(Permission.NORMAL.getValue())) {
			if (ActivityType.SCHOOL_ACTIVITY.getValue().equals(type)) {// 发布活动需要权限
				JSONUtils.ERROR(response, "当前用户没有发布活动权限,请分配权限后使用该功能.");
				return;
			}
		}

		// 文件id
		String fileId = request(request, "fileId");
		// 保存发布信息
		Activity activity = BeanUtils.mapConvertToBean(Activity.class, request);
		activity.setPersonId(getCurrentPerson(request).getId());
		if (StringUtils.isNotEmpty(fileId)) {
			activity.setUrls(fileId.split(","));
		}
		if (activityService.insertActivity(activity)) {
			JSONUtils.SUCCESS(response, activity);
		} else {
			JSONUtils.ERROR(response, "发布信息失败.");
		}
	}

	/**
	 * 我发布的信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("ipublish")
	public void ipublish(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户Id不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		Integer userId = Integer.parseInt(request(request, "userId"));
		Set<Activity> list = activityService.ipublish(userId);

		Map<Class<?>, String[]> excludes = new HashMap<Class<?>, String[]>();
		excludes.put(Activity.class, new String[] { "personId", "user",
				"activityPerson" });
		JSONUtils.SUCCESS(response, list, null, excludes);
	}

	/**
	 * 我参与的活动
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("ijoin")
	public void ijoin(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> validateResult = new HashMap<String, String>();
		validateResult.put("userId", "用户Id不能为空.");
		if (!validateParamBlank(request, response, validateResult))
			return;

		Integer userId = Integer.parseInt(request(request, "userId"));
		Set<Activity> list = activityService.ijoin(userId);

		Map<Class<?>, String[]> excludes = new HashMap<Class<?>, String[]>();
		excludes.put(Activity.class, new String[] { "personId", "user",
				"activityPerson" });
		JSONUtils.SUCCESS(response, list, null, excludes);
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
		JSONUtils.SUCCESS(response, person);
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
		String pageSize = request(request, "pageSize");
		String pageNo = request(request, "pageNo");
		String city = request(request, "city");
		String sortType = request(request, "sortType");
		// id, type, address, content, date, person_id, cost, city, way, payWay
		String sortField = request(request, "sortField");
		String orderByClause = null;
		if (StringUtils.isNotBlank(sortType)
				&& StringUtils.isNotBlank(sortField)) {
			orderByClause = sortField + " " + sortType;
		}
		JSONUtils.toJSONString(response, activityService.queryAllActivity(
				pageSize, pageNo, city, orderByClause));
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
		String pageSize = request(request, "pageSize");
		String pageNo = request(request, "pageNo");
		String city = request(request, "city");
		// desc(降序) asc(升序)
		String sortType = request(request, "sortType");
		// id, type, address, content, date, person_id, cost, city, way, payWay
		String sortField = request(request, "sortField");
		String orderByClause = null;
		if (StringUtils.isNotBlank(sortType)
				&& StringUtils.isNotBlank(sortField)) {
			orderByClause = sortField + " " + sortType;
		}
		List<Activity> result = activityService.queryAllTask(pageSize, pageNo,
				city, orderByClause);
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
		String pageSize = request(request, "pageSize");
		String pageNo = request(request, "pageNo");
		String city = request(request, "city");
		String sortType = request(request, "sortType");
		// id, type, address, content, date, person_id, cost, city, way, payWay
		String sortField = request(request, "sortField");
		String orderByClause = null;
		if (StringUtils.isNotBlank(sortType)
				&& StringUtils.isNotBlank(sortField)) {
			orderByClause = sortField + " " + sortType;
		}
		List<Activity> result = activityService.queryAllService(pageSize,
				pageNo, city, orderByClause);
		JSONUtils.toJSONString(response, result);
	}

	/**
	 * 上传图片
	 * 
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("uploadPicture")
	public void uploadPicture(
			@RequestParam(value = "file", required = false) MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		// 获取当前用户
		User user = getCurrentUser(request);
		// 获取upload上传文件真实路径
		String realPath = request.getSession().getServletContext()
				.getRealPath("upload");
		String fileName = multipartFile.getOriginalFilename();
		System.out.println("originalFilename文件名:" + fileName);
		// 校验文件必须是jpg等图片格式文件
		String contentType = multipartFile.getContentType();
		if (contentType.indexOf("image") < 0) {
			JSONUtils.ERROR(response, "上传的文件存在非法格式,不允许上传");
			return;
		}
		if (!UploadFileUtil.checkIsImage(multipartFile.getInputStream())) {
			JSONUtils.ERROR(response, "上传的文件非图片格式,不允许上传");
			return;
		}
		Integer index = uploadFileService.queryMaxIndex();
		fileName = UploadFileUtil.createFileName(fileName, index);

		// 新增uploadFile表数据
		UploadFile uploadFile = new UploadFile();
		uploadFile.setType(FileType.ACTIVITY.getValue());
		uploadFile.setName(fileName);
		uploadFile.setUserId(user.getId());
		uploadFileService.insertFile(uploadFile);

		// 文件上传至服务器
		File file = new File(realPath, fileName);
		if (!file.exists())
			file.mkdirs();
		multipartFile.transferTo(file);

		// 返回上传图片id
		JSONUtils.SUCCESS(response, uploadFile.getId());
	}

	/**
	 * 获取上传的图片的URL地址
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getFileURL")
	public void getFileURL(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request(request, "name");
		String id = request(request, "id");
		if (StringUtils.isBlank(id) && StringUtils.isBlank(name)) {
			JSONUtils.PARAM_ERROR(response, "文件名称或者id至少一个不能为空.");
			return;
		}

		UploadFile model = new UploadFile();
		model.setId(Integer.parseInt(id));
		if (StringUtils.isBlank(name))
			name = uploadFileService.loadModel(model).getName();

		String URL = UploadFileUtil.convertToFileHttpURL(request, name,
				"upload");
		JSONUtils.SUCCESS(response, URL);
	}
}
