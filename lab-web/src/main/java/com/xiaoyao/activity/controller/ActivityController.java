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
import com.xiaoyao.activity.service.ActivityService;
import com.xiaoyao.base.controller.BizBaseController;
import com.xiaoyao.base.util.BeanUtils;
import com.xiaoyao.base.util.JSONUtils;
import com.xiaoyao.login.model.User;
import com.xiaoyao.login.service.PersonManageService;
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
		if (!validateParamBlank(request, response, validateResult))
			return;

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

	/**
	 * 收弟子
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("addChildPerson")
	public void addChildPerson(HttpServletRequest request,
			HttpServletResponse response) {
		request(request, "parentId");
		request(request, "");
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

		String URL = UploadFileUtil.convertToFileHttpURL(request, name, "upload");
		JSONUtils.SUCCESS(response, URL);
	}
}
