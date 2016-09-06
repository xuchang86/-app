/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.upload.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.upload.dao.UploadFileMapperExt;
import com.xiaoyao.upload.model.UploadFile;
import com.xiaoyao.upload.model.UploadFileExample;

/**
 * 文件上传Service
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月1日 许畅 新建
 */
@Service
public class UploadFileService extends BaseService {

	/** 注入 uploadFileMapperExt */
	@Autowired
	private UploadFileMapperExt uploadFileMapperExt;

	/**
	 * 新增文件
	 * 
	 * @param file
	 * @return
	 */
	public boolean insertFile(UploadFile file) {
		return wrapperReturnVal(uploadFileMapperExt.insertSelective(file));
	}

	/**
	 * 查询当前文件名是否存在
	 * 
	 * @param file
	 * @return
	 */
	public boolean exists(UploadFile file) {
		UploadFileExample exp = new UploadFileExample();
		exp.or().andNameEqualTo(file.getName());
		List<UploadFile> lst = uploadFileMapperExt.selectByExample(exp);
		return lst.size() > 0 ? true : false;
	}

	/**
	 * 查询最大下标
	 * 
	 * @return Integer
	 */
	public Integer queryMaxIndex() {
		return uploadFileMapperExt.queryMaxIndex();
	}

	/**
	 * 通过活动id查询UploadFile
	 * 
	 * @param activityId
	 *            活动Id
	 * @return
	 */
	public List<UploadFile> queryFileByActivityId(Integer activityId) {
		UploadFileExample example = new UploadFileExample();
		example.or().andActivityIdEqualTo(activityId);
		return uploadFileMapperExt.selectByExample(example);
	}

	/**
	 * 通过id或者名称查询对象
	 * 
	 * @param uploadFile
	 * @return
	 */
	public UploadFile loadModel(UploadFile uploadFile) {
		return uploadFileMapperExt.loadModel(uploadFile);
	}

	/**
	 * 更新活动id
	 * 
	 * @param activityId
	 * @param ids
	 * @return
	 */
	public boolean updateActivityId(Integer activityId, String[] ids) {
		UploadFile record = new UploadFile();
		record.setActivityId(activityId);
		List<Integer> values = new ArrayList<Integer>();
		for (String id : ids) {
			values.add(Integer.parseInt(id));
		}
		UploadFileExample example = new UploadFileExample();
		example.or().andIdIn(values);
		return wrapperReturnVal(uploadFileMapperExt.updateByExampleSelective(
				record, example));
	}
}
