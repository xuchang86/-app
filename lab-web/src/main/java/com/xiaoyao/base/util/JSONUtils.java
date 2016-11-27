/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.group.core.commons.SysCode;
import com.group.utils.ResponseUtils;
import com.group.utils.fastjsonUtils.JsonUtils;
import com.xiaoyao.base.model.BaseVO;

/**
 * JSON工具类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月18日 许畅 新建
 */
public final class JSONUtils {

	/**
	 * 构造方法
	 */
	private JSONUtils() {
		super();
	}

	/**
	 * 发送消息传递至客户端
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param sysCode
	 *            系统状态
	 * @param content
	 *            消息内容
	 * @param includes
	 *            包括的字段
	 * @param excludes
	 *            排除的字段
	 */
	private static void sendMsgToClient(HttpServletRequest request,
			HttpServletResponse response, SysCode sysCode, Object content,
			Map<Class<?>, String[]> includes, Map<Class<?>, String[]> excludes) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", sysCode.getCode());
		result.put("message", sysCode.getMessage());
		result.put("result", content);

		String callback = request == null ? "" : request
				.getParameter("callback");
		if (!StringUtils.isEmpty(callback)) {
			ResponseUtils.renderText(response,
					callback + "(" + JSON.toJSON(result) + ");");
		} else {
			ResponseUtils.renderJson(response,
					JsonUtils.toJsonString(result, includes, excludes, false));
		}
	}

	/**
	 * 对象转为JSON字符串传递到前端
	 * 
	 * @param response
	 * @param result
	 */
	public static void toJSONString(HttpServletResponse response, Object result) {
		Map<Class<?>, String[]> excludes = new HashMap<Class<?>, String[]>();
		excludes.put(BaseVO.class, new String[] { "pageSize", "pageNo", "page",
				"sortField", "sortType" });
		toJSONString(response, result, null, excludes);
	}

	/**
	 * 对象转为JSON字符串传递到前端
	 * 
	 * @param response
	 * @param result
	 */
	public static void toJSONString(HttpServletResponse response,
			Object result, Map<Class<?>, String[]> includes,
			Map<Class<?>, String[]> excludes) {

		ResponseUtils.renderJson(response,
				JsonUtils.toJsonString(result, includes, excludes, false));
	}

	/**
	 * 发送消息传递至客户端
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param sysCode
	 *            系统状态
	 * @param content
	 *            消息内容
	 */
	protected static void sendMsgToClient(HttpServletRequest request,
			HttpServletResponse response, SysCode sysCode, Object content) {
		sendMsgToClient(request, response, sysCode, content, null, null);
	}

	/**
	 * 系统成功发送消息传递至客户端
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param content
	 *            消息内容
	 */
	public static void SUCCESS(HttpServletResponse response, Object content) {
		// 去除父类的分页属性
		Map<Class<?>, String[]> excludes = new HashMap<Class<?>, String[]>();
		excludes.put(BaseVO.class, new String[] { "pageSize", "pageNo", "page",
				"sortField", "sortType" });
		sendMsgToClient(null, response, SysCode.SUCCESS, content, null,
				excludes);
	}

	/**
	 * JSON序列化
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param content
	 *            消息内容
	 * @param includes
	 *            包括
	 * @param excludes
	 *            排除
	 */
	public static void SUCCESS(HttpServletResponse response, Object content,
			Map<Class<?>, String[]> includes, Map<Class<?>, String[]> excludes) {
		excludes.put(BaseVO.class, new String[] { "pageSize", "pageNo", "page",
				"sortField", "sortType" });
		sendMsgToClient(null, response, SysCode.SUCCESS, content, includes,
				excludes);
	}

	/**
	 * 系统错误发送消息
	 * 
	 * @param response
	 * @param content
	 */
	public static void ERROR(HttpServletResponse response, Object content) {
		sendMsgToClient(null, response, SysCode.SYS_ERR, content, null, null);
	}

	/**
	 * 系统参数错误发送消息
	 * 
	 * @param response
	 * @param content
	 */
	public static void PARAM_ERROR(HttpServletResponse response, Object content) {
		sendMsgToClient(null, response, SysCode.PARAM_IS_ERROR, content, null,
				null);
	}

	/**
	 * string字符串转换为json对象
	 * 
	 * @param text
	 *            字符串
	 * @return JSONObject
	 */
	public static JSONObject convertToJSONObj(String text) {
		return JSONObject.parseObject(text);
	}

}
