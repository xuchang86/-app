package com.rogrand.core.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rogrand.core.file.FileDownLoad;
import com.rogrand.core.file.FileUpLoad;
import com.rogrand.core.image.ImageScale;
import com.rogrand.core.image.ImageScaleImpl;
import com.rogrand.core.system.SystemParameter;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.FtpConfig;
import com.rogrand.core.util.FtpUploader;
import com.rogrand.core.util.StringUtil;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-11-18 <br/>
 * 描述：文件上传下载控制器
 */
@Controller("fileController")
@RequestMapping({ "/file/download4fullpath.do", "/file/download.do", "/file/upload.do", "/file/jsUpload.do", "/file/swfUpload.do", "/file/delete.do", "/file/ftpUpload.do" })
public class FileController extends BaseController {
    
    /**
     * 上传文件
     * @param request 请求对象
     * @param response 响应对象
     * @return ModelAndView
     * @throws Exception 异常
     */
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String maxRequestSize =request.getParameter("maxRequestSize");
    	String width =request.getParameter("width");
    	String height =request.getParameter("height");
    	String path = request.getParameter("path");
    	if(StringUtil.isEmpty(maxRequestSize)){
    		maxRequestSize=SystemParameter.get("maxRequestSize");
    	}
        FileUpLoad fileUpLoad = FileUpLoad.createInstance(getServletContext(), request);
        fileUpLoad.setMaxMemorySize(Integer.parseInt(SystemParameter.get("maxMemorySize")));
        fileUpLoad.setMaxRequestSize(Integer.parseInt(maxRequestSize));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateDir = sdf.format(new Date());
        String temp = getServletContext().getRealPath(path+"/"+dateDir);
        fileUpLoad.setTempDirectory(temp);
        fileUpLoad.setDestinationDirectory(temp);
        try {
            fileUpLoad.parseRequest();
        } catch (Exception e) {
            return responseText(response, "outofsize");
        }
        FileItem fileItem = fileUpLoad.getFileItem(0);
        
        String key = StringUtil.getPK();
        fileUpLoad.write(fileItem, key);
        
        path = "/lab-manager/" + path + "/" + dateDir + "/" + key + fileUpLoad.getFileExt(fileItem);
        boolean w=StringUtil.isEmpty(width);
        boolean h=StringUtil.isEmpty(height);
        if(!w||!h){
            ImageScale scale=new ImageScaleImpl();
            File file = new File(temp+"/" + key + fileUpLoad.getFileExt(fileItem));
            int[] size= scale.dimensionImage(file);
            if(!w&&size[0]!=Integer.parseInt(width)){
            	file.delete();
            	return responseText(response, "errorDimension");
            }
            if(!h&&size[1]!=Integer.parseInt(height)){
            	file.delete();
            	return responseText(response, "errorDimension");
            }
        }
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", "success");
        model.put("path", path);
        String fn = fileItem.getName();
        int index = fn.lastIndexOf('\\');
        if (index == -1) {
            model.put("name", fn);
        } else {
            model.put("name", fn.substring(index + 1));
        }
        Long size = fileItem.getSize();
        model.put("size", size);
        
        String json = BeanUtil.toJsonString(model);
        return responseText(response, json);
    }
    
    /**
     * 上传文件
     * @param request 请求对象
     * @param response 响应对象
     * @return ModelAndView
     * @throws Exception 异常
     */
    public ModelAndView ftpUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        FileUpLoad fileUpLoad = FileUpLoad.createInstance(getServletContext(), request);
        fileUpLoad.setMaxMemorySize(Integer.parseInt(SystemParameter.get("maxMemorySize")));
        fileUpLoad.setMaxRequestSize(Integer.parseInt(SystemParameter.get("maxRequestSize")));
        String temp = getServletContext().getRealPath(SystemParameter.get("tempDirectory"));
        fileUpLoad.setTempDirectory(temp);
        fileUpLoad.setDestinationDirectory(temp);
        try {
            fileUpLoad.parseRequest();
        } catch (Exception e) {
            return responseText(response, "outofsize");
        }
        
        FileItem fileItem = fileUpLoad.getFileItem(0);
        
        // 前台过来的参数
        String account = fileUpLoad.getParameter("account");
        String type = fileUpLoad.getParameter("type");
        int imgWidth = Integer.parseInt(StringUtils.defaultString(fileUpLoad.getParameter("imgWidth"), "640"));
        int imgHeight = Integer.parseInt(StringUtils.defaultString(fileUpLoad.getParameter("imgHeight"), "640"));
        
        // 组装FTP参数
        String saveAsName = "";
        String removeDir = "";
        if ("0".equals(type)) {
            saveAsName = account + fileUpLoad.getFileExt(fileItem);
            removeDir = SystemParameter.get("userHeadImgSavePath");
        } else {
            saveAsName = account + "_a" + type + fileUpLoad.getFileExt(fileItem);
            removeDir = SystemParameter.get("userFileImgSavePath");
        }
        
        {// 文件压缩并上传处理
            File srcFile = new File(temp + File.separator + StringUtil.getPK() + fileUpLoad.getFileExt(fileItem));
            File destFile = new File(temp + File.separator + StringUtil.getPK() + fileUpLoad.getFileExt(fileItem));
            
            fileItem.write(srcFile);
            
            ImageScale scale = new ImageScaleImpl();
            scale.resizeFix(srcFile, destFile, imgWidth, imgHeight);
            
            FtpUploader.upload(FtpConfig.getIp(), FtpConfig.getPort(), FtpConfig.getUsername(), FtpConfig.getPassword(), destFile, saveAsName, removeDir);
            
            srcFile.delete();
            destFile.delete();
        }
        
        // 组装返回结果
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", "success");
        model.put("path", saveAsName);
        String fn = fileItem.getName();
        int index = fn.lastIndexOf('\\');
        if (index == -1) {
            model.put("name", fn);
        } else {
            model.put("name", fn.substring(index + 1));
        }
        String json = BeanUtil.toJsonString(model);
        
        return responseText(response, json);
    }
    
    /**
     * 下载文件
     * @param request 请求对象
     * @param response 响应对象
     * @return ModelAndView
     * @throws Exception 异常
     */
    public ModelAndView download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getParameter("path");
        if (!path.startsWith("/"))
            path = "/" + path;
        String name = request.getParameter("name");
        FileDownLoad fileDownLoad = new FileDownLoad(response);
        File file = new File(getServletContext().getRealPath(path));
        fileDownLoad.write(file, name);
        return null;
    }
    
    public ModelAndView download4fullpath(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getParameter("path");
        String name = request.getParameter("name");
        
        FileDownLoad fileDownLoad = new FileDownLoad(response);
        File file = new File(path);
        fileDownLoad.write(file, name);
        return null;
    }
    
    /**
     * 删除附件
     *
     * @param request  请求对象
     * @param response 响应对象
     * @return ModelAndView
     * @throws Exception 异常
     */
    @SuppressWarnings("rawtypes")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map params = BeanUtil.wrapBean(HashMap.class, request);
        String filePath = getServletContext().getRealPath("/upload");
        filePath = filePath + File.separator + params.get("sf_id").toString() + params.get("sf_type").toString();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        return responseText(response, "1");
    }
}
