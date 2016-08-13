package com.rogrand.core.file;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传实现类
 * @author 姜敏
 * @version 4.0
 */
public class FileUpLoad {
  private HttpServletRequest request;
  private String encoding = "UTF-8";
  private List<FileItem> fileItems;
  private int maxMemorySize;
  private int maxRequestSize;
  private File tempDirectory;
  private String destinationDirectory;
  private Map<String, List<String>> parameter;

  /**
   * 构造函数
   *
   * @param request 请求对象
   */
  public FileUpLoad(HttpServletRequest request) {
    this.request = request;
    this.fileItems = new ArrayList<FileItem>();
    this.parameter = new HashMap<String, List<String>>();
  }

  /**
   * 构造函数
   */
  public FileUpLoad() {
    this.fileItems = new ArrayList<FileItem>();
    this.parameter = new HashMap<String, List<String>>();
  }

  /**
   * 获得指定索引的上传文件对象
   *
   * @param index 索引
   * @return FileItem
   */
  public FileItem getFileItem(int index) {
    return (this.fileItems.size() > index) ? fileItems.get(index) : null;
  }

  /**
   * 获得上传文件的集合
   *
   * @return List
   */
  public List<FileItem> getFileItems() {
    return this.fileItems;
  }

  /**
   * 获得上传文件的个数
   *
   * @return int
   */
  public int getFileItemsCount() {
    return this.fileItems.size();
  }

  /**
   * 获得文件上传请求中的上传参数
   *
   * @param name 名称
   * @return String
   */
  public String getParameter(String name) {
    return (this.parameter.containsKey(name)) ? this.parameter.get(name).get(0) : null;
  }

  /**
   * 获得文件上传请求中的输入框字段名的迭代器
   *
   * @return Iterator
   */
  public Iterator<String> getParameterNames() {
    return this.parameter.keySet().iterator();
  }

  /**
   * 获得文件上传请求中的上传参数数组
   *
   * @param name 名称
   * @return String[]
   */
  public String[] getParameterValues(String name) {
    if (this.parameter.containsKey(name)) {
      List<String> values = this.parameter.get(name);
      String[] results = new String[values.size()];
      for (int i = 0; i < values.size(); i++) {
        results[i] = values.get(i);
      }
      return results;
    }
    else {
      return null;
    }
  }

  /**
   * 解析上传文件的请求对象
   *
   * @throws org.apache.commons.fileupload.FileUploadException
   * @throws java.io.UnsupportedEncodingException
   */
  public void parseRequest() throws FileUploadException, UnsupportedEncodingException {
    DiskFileItemFactory factory = new DiskFileItemFactory();
    if (this.maxMemorySize > 0) {
      factory.setSizeThreshold(maxMemorySize);
    }
    if (this.tempDirectory != null) {
      factory.setRepository(tempDirectory);
    }
    ServletFileUpload upload = new ServletFileUpload(factory);
    if (this.maxRequestSize > 0) {
      upload.setSizeMax(maxRequestSize);
    }
    List<?> items = upload.parseRequest(request);
    for (Object item1 : items) {
      FileItem item = (FileItem) item1;
      if (item.isFormField()) { //是表单字段
        processField(item.getFieldName(), item.getString(this.encoding));
      }
      else {//是文件
        processField(item.getFieldName(), item.getName());
        processFile(item);
      }
    }
  }

  /**
   * 解析上传文件的请求对象
   *
   * @param request 请求对象
   * @throws org.apache.commons.fileupload.FileUploadException
   *
   * @throws java.io.UnsupportedEncodingException
   *
   */
  public void parseRequest(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
    this.request = request;
    parseRequest();
  }

  /**
   * 设置上传请求的请求字符集,用于请求参数的转码
   *
   * @param encoding 字符集
   */
  public void setURIEncoding(String encoding) {
    this.encoding = encoding;
  }

  /**
   * 设置文件上传占用的最大内存数
   *
   * @param maxMemorySize 最大内存数
   */
  public void setMaxMemorySize(int maxMemorySize) {
    this.maxMemorySize = maxMemorySize * 1024;
  }

  /**
   * 设置上传文件的最大尺寸
   *
   * @param maxRequestSize 最大尺寸
   */
  public void setMaxRequestSize(int maxRequestSize) {
    this.maxRequestSize = maxRequestSize * 1024;
  }

  /**
   * 设置上传文件在服务器上的临时保存目录
   *
   * @param tempDirectory   临时保存目录
   */
  public void setTempDirectory(String tempDirectory) {
    this.tempDirectory = new File(tempDirectory);
    if (!this.tempDirectory.exists() || !this.tempDirectory.isDirectory()) {
      this.tempDirectory.mkdir();
    }

  }

  /**
   * 将上传文件写到服务器,指定文件名
   *
   * @param item  文件
   * @param saveAsName 文件名
   * @return String 文件保存在服务器上的相对文件名
   * @throws Exception
   */
  public String write(FileItem item, String saveAsName) throws Exception {
    String fileExtName = (saveAsName.lastIndexOf(".") == -1) ? getFileExt(item) : "";
    String relativeName = saveAsName.concat(fileExtName);
    String fileName = destinationDirectory.concat(File.separator).concat(relativeName);
    File uploadedFile = new File(fileName);
    item.write(uploadedFile);
    return relativeName;
  }

  /**
   * 设置上传文件在服务器上的最终保存目录
   *
   * @param destinationDirectory 目标目录
   */
  public void setDestinationDirectory(String destinationDirectory) {
    this.destinationDirectory = destinationDirectory;
    File uploadDir = new File(destinationDirectory);
    if (!uploadDir.exists() || !uploadDir.isDirectory()) {
      uploadDir.mkdirs();
    }
  }

  /**
   * 将上传文件写到服务器,按原文件名
   *
   * @param item 文件item
   * @throws Exception
   */
  public String write(FileItem item) throws Exception {
    File file = new File(item.getName());
    String relativeName = file.getName();
    String fileName = destinationDirectory.concat(File.separator).concat(relativeName);
    File uploadedFile = new File(fileName);
    item.write(uploadedFile);
    return relativeName;
  }


  /**
   * 将指定索引的上传文件写到服务器,指定文件名
   *
   * @param index 索引
   * @param saveAsName 另存名称
   * @throws Exception
   */
  public String write(int index, String saveAsName) throws Exception {
    FileItem item = getFileItem(index);
    if(item!=null){
      return write(item,saveAsName);
    }
    else{
      return null;
    }
  }

  /**
   * 将指定索引的上传文件写到服务器, 按原文件名
   *
   * @param index 索引
   * @throws Exception
   */
  public String write(int index) throws Exception {
    FileItem item = getFileItem(index);
    if(item!=null){
      return write(item);
    }
    else{
      return null;
    }
  }

  /**
   * 处理请求参数过程
   *
   * @param name 名称
   * @param value 值
   */
  private void processField(String name, String value) {
    List<String> values;
    if (this.parameter.containsKey(name)) {
      values = this.parameter.get(name);
      values.add(value);
    }
    else {
      values = new ArrayList<String>();
      values.add(value);
      this.parameter.put(name, values);
    }
  }

  /**
   * 处理上传文件过程
   *
   * @param item 文件Item
   */
  private void processFile(FileItem item) {
    this.fileItems.add(item);
  }

  /**
   * 获得文件扩展名
   *
   * @param item 文件Item
   * @return String
   */
  public String getFileExt(FileItem item) {
    if(item==null) return null;
    String name = item.getName();
    if(name==null) return null;
    int index = name.lastIndexOf(".");
    return (index == -1) ? "" : name.substring(index);
  }

  /**
   * 获得文件的相对名称(去除路径信息)
   *
   * @param item 文件Item
   * @return String
   */
  public String getFileRelative(FileItem item) {
    if(item==null) return null;
    String name = item.getName();
    if(name==null) return null;
    File file = new File(name);
    return file.getName();
  }

  /**
   * 获得指定索引的上传文件的后缀名
   *
   * @param index  索引
   * @return String
   */
  public String getFileExt(int index) {
    return getFileExt(getFileItem(index));
  }

  /**
   * 获得指定索引的上传文件的相对名称(去除路径信息)
   *
   * @param index 索引
   * @return String
   */
  public String getFileRelative(int index) {
    return getFileRelative(getFileItem(index));
  }

  /**
   * 创建实例
   * @param application 全局对象
   * @return FileUpLoad
   */
  public static FileUpLoad createInstance(ServletContext application){
	FileUpLoad fileUpLoad = new FileUpLoad();
	String tempDirectory=application.getRealPath("/temp");
	fileUpLoad.setTempDirectory(tempDirectory);
    fileUpLoad.setDestinationDirectory(tempDirectory);
	fileUpLoad.setMaxMemorySize(1024);
	fileUpLoad.setMaxRequestSize(10240);
	fileUpLoad.setURIEncoding("UTF-8");
	return fileUpLoad;
  }
  
  /**
   * 创建实例
   * @param application 全局对象
   * @param request 请求对象
   * @return FileUpLoad
   */
  public static FileUpLoad createInstance(ServletContext application,HttpServletRequest request){
	  FileUpLoad fileUpLoad = new FileUpLoad(request);
      String tempDirectory=application.getRealPath("/temp");
      fileUpLoad.setTempDirectory(tempDirectory);
      fileUpLoad.setDestinationDirectory(tempDirectory);
      fileUpLoad.setMaxMemorySize(1024);
      fileUpLoad.setMaxRequestSize(10240);
	  fileUpLoad.setURIEncoding("UTF-8");
	  return fileUpLoad;
  }
}
