package com.group.core.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.group.core.commons.uploads.UploadType;
import com.group.utils.FileUpDownUtil;

@Service
public class ImageService {
	
	@Value("${ylh.url}")
	private String ylhUrl;
	
	@Value("${ylh.port}")
	private String ylhPort;
	
	@Value("${ylhmanager.url}")
	private String ylhmanagerUrl;
	
	@Value("${ylhmanager.port}")
	private String ylhmanagerPort;

    private static DateFormat dateDirFormat = new SimpleDateFormat("yyyyMMdd");
    
    private final String sep = File.separator;

	/**
	 * 上传文件
	 * request 请求request
	 * uploadType,上传类型
	 * @return
	 * @throws FileUploadException 
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public List<String> upload(HttpServletRequest request,UploadType uploadType) throws IOException, FileUploadException{
		List<String> list = new ArrayList<String>();
		
		String webRoot = request.getRealPath("");
		String fileTepm = webRoot + sep + "upload" + sep + "fileTepm";//上传的临时缓存目录
		String fileDir = genNewFilePath(uploadType, webRoot);//文件实际上传的目录
		
		String uuidFilename = String.valueOf(UUID.randomUUID()).replace("-", "").toUpperCase();
		List<String> paths = FileUpDownUtil.fileUpload(request, fileTepm, fileDir,uuidFilename);
		System.out.println("上传图片地址======="+paths.toString());
		if(paths!=null && paths.size()>0){
			for(int i=0;i<paths.size();i++){
				String newPath = "/ylh/upload" + StringUtils.substringAfterLast(paths.get(i), "upload").replace("\\", "/");
				list.add(newPath);
			}
		}
		
		return list;
	}
	
	/**
     * 描述：〈生成上传文件名〉 <br/>
     * 作者：ling.dai <br/>
     * 生成日期：2014-4-2 <br/>
     * 
     * @param type 上传类型
     * @param fileName 原始文件名
     * @return 上传文件名
     */
    public String genNewFilePath(UploadType type,String webRoot) {
        String dateDir = dateDirFormat.format(new Date());
        if (UploadType.USER_PIC == type) {
            return webRoot + sep + "upload" + sep + "userpic" + sep +  dateDir;
        } else if (UploadType.IMAGE == type) {
        	return webRoot + sep + "upload" + sep + "images" + sep +  dateDir;
        } else if (UploadType.TEMP == type) {
        	return webRoot + sep + "upload" + sep + "temp" + sep +  dateDir;
        } else {
        	return webRoot + sep + "upload" + sep + "other" + sep +  dateDir;
        }
    }
    
    /**
     * 转换图片地址(访问*-manager上的图片)
     * @param imageUrl
     * @return
     */
    public String convertImageUrl(String imageUrl){
    	if(StringUtils.isEmpty(imageUrl))
    		return "";
    	if(StringUtils.contains(imageUrl, "http://")){
    		return imageUrl;
    	}else{
    		return "http://"+ylhmanagerUrl+":"+ylhmanagerPort+imageUrl;
    	}
    }
    
    /**
     * 转换富文本中的图片地址(访问*-manager上的图片)
     * @param content
     * @return
     */
    public String convertImageUrlHtml(String content){
    	if(!StringUtils.isEmpty(content)){
    		String[] ps = StringUtils.substringsBetween(content, "<p>", "</p>");
        	if(ps!=null && ps.length>0){
        		for(int i=0;i<ps.length;i++){
        			String[] images = StringUtils.substringsBetween(ps[i], "src=\"", "\"");
        			if(images!=null && images.length>0){
        				for(int j=0;j<images.length;j++){
        					content = StringUtils.replace(content, images[j], convertImageUrl(images[j]));
        				}
        			}
        		}
        	}
    	}
    	String css = "<style type='text/css'>img{text-align:center;} img{width:100%;}</style>";
    	return css+content;
    }
    
    /**
     * 获取富文本中的图片(访问*-manager上的图片)
     * @param content
     * @return
     */
    public List<String> convertImageUrlHtml2(String content){
    	List<String> list = new ArrayList<String>();
    	if(!StringUtils.isEmpty(content)){
    		String[] ps = StringUtils.substringsBetween(content, "<p>", "</p>");
        	if(ps!=null && ps.length>0){
        		for(int i=0;i<ps.length;i++){
        			String[] images = StringUtils.substringsBetween(ps[i], "src=\"", "\"");
        			if(images!=null && images.length>0){
        				for(int j=0;j<images.length;j++){
        					list.add(convertImageUrl(images[j]));
        				}
        			}
        		}
        	}
    	}
    	return list;
    }
    
    /**
     * 转换图片地址(访问*-web上 的图片)
     * @param imageUrl
     * @return
     */
    public String convertImageUrl2(String imageUrl){
    	if(StringUtils.isEmpty(imageUrl))
    		return "";
    	if(StringUtils.contains(imageUrl, "http://")){
    		return imageUrl;
    	}else{
    		return "http://"+ylhUrl+":"+ylhPort+imageUrl;
    	}
    }
}
