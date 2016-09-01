/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyao.base.service.BaseService;
import com.xiaoyao.upload.dao.UploadFileMapper;
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

	/** 注入 UploadFileMapper */
	@Autowired
	private UploadFileMapper uploadFileMapper;

	/**
	 * 新增文件
	 * 
	 * @param file
	 * @return
	 */
	public boolean insertFile(UploadFile file) {
		return wrapperReturnVal(uploadFileMapper.insertSelective(file));
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
		List<UploadFile> lst = uploadFileMapper.selectByExample(exp);
		return lst.size() > 0 ? true : false;
	}
}
