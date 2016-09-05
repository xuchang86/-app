package com.group.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.group.core.commons.ResponseJSON;
import com.group.core.commons.SysCode;
import com.group.utils.ResponseUtils;
import com.group.utils.fastjsonUtils.JsonUtils;

public class BaseController {

	protected static Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	/**
	 * 准备参数
	 * 
	 * @param request
	 * @param method
	 * @param paramKey
	 * @return
	 */
	protected Map<String, String> parseParams(HttpServletRequest request,
			String method, String[] paramKey) {
		logger.info("请求接口：" + method);
		Map<String, String> params = new HashMap<String, String>();
		for (int i = 0; i < paramKey.length; i++) {
			String param = request.getParameter(paramKey[i]);
			if (param != null) {
				params.put(paramKey[i], param);
				logger.info("【text-- the key:" + paramKey[i] + " ,value :"
						+ param + "】");
			} else {
				logger.info("【warn-- the key:" + paramKey[i]
						+ " is empty or is null!】");
			}
		}
		return params;
	}

	/**
	 * 描述：〈响应输出内容，包含跨域处理〉 <br/>
	 * 作者：xuan.zhou@rogrand.com <br/>
	 * 生成日期：2014-03-13 <br/>
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param sysCode
	 *            系统代码
	 * @param content
	 *            JSON对象
	 */
	protected void renderJson(HttpServletRequest request,
			HttpServletResponse response, SysCode sysCode, Object content) {
		renderJson(request, response, sysCode, content, null);
	}

	protected void renderJson(HttpServletRequest request,
			HttpServletResponse response, SysCode sysCode, Object content,
			Map<Class<?>, String[]> includes) {
		renderJson(request, response, sysCode, content, includes, null);
	}

	protected void renderJson(HttpServletRequest request,
			HttpServletResponse response, SysCode sysCode, Object content,
			Map<Class<?>, String[]> includes, Map<Class<?>, String[]> excludes) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", sysCode.getCode());
		result.put("message", sysCode.getMessage());
		result.put("result", content);

		String callback = request.getParameter("callback");
		if (!StringUtils.isEmpty(callback)) {
			ResponseUtils.renderText(response,
					callback + "(" + JSON.toJSON(result) + ");");
		} else {
			ResponseUtils.renderJson(response,
					JsonUtils.toJsonString(result, includes, excludes, false));
		}
	}

	/**
	 * 描述：〈响应输出内容，包含跨域处理〉 <br/>
	 * 作者：xuan.zhou@rogrand.com <br/>
	 * 生成日期：2014-03-13 <br/>
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param content
	 *            JSON对象
	 */
	protected void renderJson(HttpServletRequest request,
			HttpServletResponse response, Object content) {
		renderJson(request, response, content, null, null);
	}

	protected void renderJson(HttpServletRequest request,
			HttpServletResponse response, Object content,
			Map<Class<?>, String[]> includes, Map<Class<?>, String[]> excludes) {
		String callback = request.getParameter("callback");
		if (StringUtils.isNotEmpty(callback)) {
			ResponseUtils.renderText(
					response,
					callback
							+ "("
							+ JsonUtils.toJsonString(content, includes,
									excludes, true) + ");");
		} else {
			ResponseUtils.renderJson(response,
					JsonUtils.toJsonString(content, includes, excludes, false));
		}
	}

	/**
	 * 描述：〈生成响应的Json内容〉 <br/>
	 * 作者：xuan.zhou@rogrand.com <br/>
	 * 生成日期：2014-6-6 <br/>
	 * 
	 * @param response
	 *            请求
	 * @param responseJson
	 *            ResponseJSON对象
	 */
	protected void renderResponseJson(HttpServletResponse response,
			ResponseJSON responseJson) {
		ResponseUtils.renderJson(response,
				JsonUtils.toJsonString(responseJson, false));
	}

	/**
	 * 描述：〈生成响应的Json内容〉 <br/>
	 * 作者：xuan.zhou@rogrand.com <br/>
	 * 生成日期：2014-6-6 <br/>
	 * 
	 * @param response
	 *            请求
	 * @param responseJson
	 *            响应JSON对象
	 * @param includes
	 *            包含属性
	 */
	protected void renderResponseJson(HttpServletResponse response,
			ResponseJSON responseJson, Map<Class<?>, String[]> includes) {
		renderResponseJson(response, responseJson, includes, null);
	}

	/**
	 * 描述：〈生成响应的Json内容〉 <br/>
	 * 作者：xuan.zhou@rogrand.com <br/>
	 * 生成日期：2014-6-6 <br/>
	 * 
	 * @param response
	 *            请求
	 * @param responseJson
	 *            响应JSON对象
	 * @param includes
	 *            包含属性
	 * @param excludes
	 *            排除属性
	 */
	protected void renderResponseJson(HttpServletResponse response,
			ResponseJSON responseJson, Map<Class<?>, String[]> includes,
			Map<Class<?>, String[]> excludes) {
		ResponseUtils
				.renderJson(response, JsonUtils.toJsonString(responseJson,
						includes, excludes, false));
	}

	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 请求参数
	 * 
	 * @param request
	 * @param param
	 * @return
	 */
	protected String request(HttpServletRequest request, String param) {
		return request.getParameter(param);
	}

}
