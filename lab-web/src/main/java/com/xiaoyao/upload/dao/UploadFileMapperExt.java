/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.upload.dao;

import com.xiaoyao.upload.model.UploadFile;

/**
 * UploadFileMapper扩展接口
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月3日 许畅 新建
 */
public interface UploadFileMapperExt extends UploadFileMapper {

	/** 查询最大下标 */
	Integer queryMaxIndex();

	/** 根据id或者名称查询对象 */
	UploadFile loadModel(UploadFile file);

}
