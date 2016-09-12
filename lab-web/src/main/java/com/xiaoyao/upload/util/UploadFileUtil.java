/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.upload.util;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoyao.base.cache.GlobalCache;
import com.xiaoyao.login.util.LoginUtil;

/**
 * 文件上传工具类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月3日 许畅 新建
 */
public final class UploadFileUtil {

	/** 日志记录 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UploadFileUtil.class);

	/**
	 * 构造方法
	 */
	private UploadFileUtil() {

	}

	/**
	 * 转换为图片URL http访问路径
	 * 
	 * @param ip
	 *            服务器ip
	 * @param port
	 *            服务器端口
	 * @param contextPath
	 *            contextPath
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static String convertToFileHttpURL(String ip, String port,
			String contextPath, String fileName, String type) {
		StringBuffer url = new StringBuffer();
		url.append("http://");
		url.append(ip);
		url.append(":");
		url.append(port);
		url.append(contextPath);
		url.append("/");
		url.append(type);
		url.append("/");
		url.append(fileName);
		return url.toString();
	}

	/**
	 * 包装图片路径
	 * 
	 * @param request
	 * @param fileName
	 * @param type
	 * @return
	 */
	public static String convertToFileHttpURL(HttpServletRequest request,
			String fileName, String type) {
		String ip = request.getServerName();
		String port = String.valueOf(request.getServerPort());
		String contextPath = request.getContextPath();
		return convertToFileHttpURL(ip, port, contextPath, fileName, type);
	}

	/**
	 * 包装商城的商品图片URL
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static String wrapperMallURL(String fileName) {
		String ip = GlobalCache.getServerIP() == "null" ? LoginUtil
				.getServerIP() : GlobalCache.getServerIP();
		String port = GlobalCache.getServerPort() == "null" ? LoginUtil
				.getServerPort() : GlobalCache.getServerPort();

		return convertToFileHttpURL(ip, port, GlobalCache.getContextPath(),
				fileName, "mall");
	}

	/**
	 * 转换为图片URL
	 * 
	 * @param fileName
	 * @return
	 */
	public static String convertToFileHttpURL(String fileName, String type) {
		return convertToFileHttpURL(GlobalCache.getServerIP(),
				GlobalCache.getServerPort(), GlobalCache.getContextPath(),
				fileName, type);
	}

	/**
	 * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
	 * 
	 * @param inputStream
	 * @return
	 */
	public static boolean checkIsImage(InputStream inputStream) {
		Image img;
		try {
			img = ImageIO.read(inputStream);
			if (img == null || img.getWidth(null) <= 0
					|| img.getHeight(null) <= 0) {
				return false;
			}
		} catch (IOException e) {
			LOGGER.error("读取图片失败:" + e.getMessage(), e);
		}
		return true;
	}

	/**
	 * 生成图片文件名
	 * 
	 * @param index
	 * @return
	 */
	public static String createFileName(String orginFileName, Integer index) {
		String suffix = orginFileName.split("\\.")[orginFileName.split("\\.").length - 1];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		index = index == null ? 1 : index + 1;
		return "IMG_" + sdf.format(new Date()) + "_" + index + "." + suffix;
	}

}
