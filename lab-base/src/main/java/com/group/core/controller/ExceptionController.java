package com.group.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.group.core.commons.Body;
import com.group.core.commons.SysCode;
import com.group.core.exception.ServiceException;

/**
 * 异常处理controller
 * @author Administrator
 *
 */
@ControllerAdvice
public class ExceptionController extends BaseController{
	Logger logger = LoggerFactory.getLogger(HandlerExceptionResolver.class);
	
	@ExceptionHandler(ServiceException.class)
	public ModelAndView handleAllException(HttpServletRequest request,
			HttpServletResponse response, ServiceException e) {
		Body body = new Body();
		body.setCode(e.getErrorCode());
		body.setMessage(e.getErrorDesc());
		renderJson(request, response, body);
		logger.warn("业务异常-"+e.getErrorDesc());
		return null;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(HttpServletRequest request,
			HttpServletResponse response, Exception e) {
		Body body = new Body();
		body.setCode(SysCode.SYS_ERR.getCode());
		body.setMessage(SysCode.SYS_ERR.getMessage());
		body.setResult(e);
		renderJson(request, response, body);
		logger.error("操作错误-"+e.getMessage(),e);
		return null;
	}
	
}
