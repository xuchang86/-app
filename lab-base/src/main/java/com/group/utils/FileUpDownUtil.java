package com.group.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

/**
 * 文件上传下载工具类
 * @author v_lingdai
 *
 */
public class FileUpDownUtil {
	
	/**
	 * 上传文件
	 * @param request 
	 * @param fileTepm 缓存临时目录
	 * @param fileDir 上传文件存放目录
	 * @param fileName 文件名称（可为空，若为空则和上传的文件同名）
	 * @return path 上传文件成功后返回的全路径
	 * @throws IOException 
	 * @throws FileUploadException 
	 */
	public static List<String> fileUpload(HttpServletRequest request,String fileTepm,String fileDir,String fileName) 
			throws IOException, FileUploadException{
		
		// 创建文件处理工厂，它用于生成FileItem对象。
		DiskFileItemFactory difactory = new DiskFileItemFactory();
		// 设置缓存大小，如果上传文件超过缓存大小，将使用临时目录做为缓存。
		difactory.setSizeThreshold(2048 * 2048);

		//设置缓存临时目录
		File filedirs = new File(fileTepm);
		if (!filedirs.exists())
			filedirs.mkdirs();//创建文件夹
		difactory.setRepository(filedirs);

		// 设置文件实际保存的目录
		File fudir = new File(fileDir);
		if (!fudir.exists())
			fudir.mkdirs();

		// 创建request的解析器，它会将数据封装到FileItem对象中。
		ServletFileUpload sfu = new ServletFileUpload(difactory);
		// 解析保存在request中的数据并返回list集合
		List<FileItem> list = sfu.parseRequest(request);
		// 图片上传成功后图片的全路径
		List<String> paths = new ArrayList<String>();
		
		// 遍历list集合，取出每一个输入项的FileItem对象，并分别获取数据
		for (Iterator<FileItem> it = list.iterator(); it.hasNext();) {
			FileItem fi = (FileItem) it.next();
			String sep = File.separator;
			if (fi.isFormField()) {
				System.out.println(fi.getFieldName());
				System.out.println(fi.getString());
			} else {
				// 获取客户端上传的文件名
				String filename = fi.getName();
				if(StringUtils.isEmpty(filename))
					continue;
				// 向服务器写出文件
				InputStream in = fi.getInputStream();
				fileName = StringUtils.isEmpty(fileName)?filename:fileName+"."+StringUtils.substringAfterLast(filename, ".");
				FileOutputStream fos = new FileOutputStream(fudir + sep + fileName);
				byte[] buf = new byte[1024];
				int len = -1;
				while ((len = in.read(buf)) != -1) {
					fos.write(buf, 0, len);
				}

				// 关闭流
				if (in != null) {
					try {
						in.close();
					} finally {
						if (fos != null)
							fos.close();
					}
				}
				
				paths.add(fudir + sep + fileName);
			}
		}
		
		return paths;
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param filePath 下载源文件，带路径
	 * @throws IOException 
	 */
	public static void fileDown(HttpServletResponse response,String filePath) throws IOException{
		if(StringUtils.isEmpty(filePath))
			return;
		String seq = File.separator;
		String fileName = StringUtils.substringAfterLast(filePath, seq);
		
		response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));//设置下载的文件名
		response.setContentType("application/x-download;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		File file = new File(filePath);
		InputStream is = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		byte[] b = new byte[1024];
		int len;
		while((len=is.read(b))>0){
			os.write(b, 0, len);
		}
		is.close();
	}
	
	/**
	 * 删除文件
	 * @param filePath 文件的全名称
	 */
	public static void fileDel(String filePath){
		if(StringUtils.isEmpty(filePath))
			return;
		File file = new File(filePath);
		if(file.exists())
			file.delete();
	}
}
