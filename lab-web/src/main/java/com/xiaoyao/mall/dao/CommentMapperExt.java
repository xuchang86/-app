/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.mall.dao;

import java.util.List;

import com.xiaoyao.mall.model.Comment;
import com.xiaoyao.mall.model.CommentExample;

/**
 * 扩展评论接口
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年11月27日 许畅 新建
 */
public interface CommentMapperExt extends CommentMapper {

	List<Comment> selectByExampleByPage(CommentExample example);

}
