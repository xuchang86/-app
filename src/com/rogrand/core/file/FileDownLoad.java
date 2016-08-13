package com.rogrand.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件下载实现
 *
 * @author 姜敏
 * @version 4.0
 */

public class FileDownLoad {

    private HttpServletResponse response;
    private String encoding = "UTF-8";
    private int blockSize = 1024;
    private final Log logger = LogFactory.getLog(getClass());

    /**
     * 构造方法
     *
     * @param response
     */
    public FileDownLoad(HttpServletResponse response) {
        this.response = response;
    }

    public void setURIEncoding(String encoding) {
        this.encoding = encoding;
    }


    /* (non-Javadoc)
      * @see com.framework.file.FileDownLoad#write(byte[], java.lang.String)
      */
    public void write(byte[] bytes, String destFileName) throws IOException {
        this.response.setContentType("application/octet-stream;charset=".concat(this.encoding));
        String downLoad = "";
        if (destFileName != null) {
            downLoad = new String(destFileName.getBytes("GBK"), "ISO-8859-1");
        }
        this.response.setHeader("Content-disposition", "attachment;filename=\"" + downLoad + "\"");
        OutputStream output = null;
        try {
            output = this.response.getOutputStream();
            output.write(bytes);
            output.flush();
        }
        catch (Exception ex) {
            if (this.response.isCommitted()) {
                logger.error("exception class:" + ex.getCause().getClass().getName() + "\nexception message:" + ex.getCause().getMessage());
            }
            throw new IOException(ex.getMessage());
        }
        finally {
            if (output != null) {
                try{
                    output.close();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            response.flushBuffer();
        }

    }


    /**
     * 输出流到客户端
     *
     * @param inputStream 输入流
     * @param destFileName 另存名
     * @throws java.io.IOException
     */
    public void write(InputStream inputStream, String destFileName) throws IOException {
        this.response.setContentType("application/octet-stream");
        this.response.setCharacterEncoding(this.encoding);
        String downLoad = "";
        if (destFileName != null) {
            downLoad = new String(destFileName.getBytes("GBK"), "ISO-8859-1");
        }
        this.response.setHeader("Content-disposition", "attachment;filename=\"" + downLoad + "\"");
        OutputStream output = null;
        try {
            byte[] bytes = new byte[blockSize];
            output = this.response.getOutputStream();
            int byteRead;
            while ((byteRead = inputStream.read(bytes)) != -1) {
                output.write(bytes, 0, byteRead);
            }
            output.flush();
        }
        catch (Exception ex) {
            if (this.response.isCommitted()) {
                logger.error("exception class:" + ex.getCause().getClass().getName() + "\nexception message:" + ex.getCause().getMessage());

            }
            throw new IOException(ex.getMessage());
        }
        finally {
            if (inputStream != null){
				try{
					inputStream.close();
				}
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (output != null) {
                try{
                    output.close();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            response.flushBuffer();
        }
    }

    /**
     * 将文件通过response响应输出流写到客户端
     *
     * @param sourceFileName 源文件
     * @param saveAsName     下载保存文件
     * @throws java.io.IOException
     */
    public void write(String sourceFileName, String saveAsName) throws IOException {
        File file = new File(sourceFileName);
        if (!file.exists()) {
            throw new FileNotFoundException(sourceFileName + " is not exists");
        }
        if (!file.isFile()) {
            throw new FileNotFoundException(sourceFileName + " is not file");
        }
        InputStream inputStream = null;
        String fileExt = getFileExt(file);
        String downFileName = "";
        int index = saveAsName.lastIndexOf(".");
        if (index == -1) { //文件下载别名中没有后缀名
            downFileName = saveAsName.concat(fileExt);
        } else {
            String saveFileExt = saveAsName.substring(index);
            if (!fileExt.equals(saveFileExt)) {//判断物理文件和下载别名的后缀名是否相同
                downFileName = saveAsName.concat(fileExt);
            } else {
                downFileName = saveAsName;
            }
        }
        try {
            inputStream = new FileInputStream(file);
            write(inputStream, downFileName);
        }
        catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
        finally {
            if (inputStream != null){
				try{
					inputStream.close();
				}
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 将文件通过response响应输出流写到客户端
     *
     * @param sourceFileName 源文件
     * @throws java.io.IOException
     */
    public void write(String sourceFileName) throws IOException {
        File file = new File(sourceFileName);
        if (!file.exists()) {
            throw new FileNotFoundException(sourceFileName + " is not exists");
        }
        if (!file.isFile()) {
            throw new FileNotFoundException(sourceFileName + " is not file");
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            write(inputStream, file.getName());
        }
        catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
        finally {
            if (inputStream != null){
				try{
					inputStream.close();
				}
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 获得上传文件的后缀名
     *
     * @param file 文件
     * @return String
     */
    private String getFileExt(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        return (index == -1) ? "" : name.substring(index);
    }

    /**
     * 设置下载时读取文件流的内存块
     *
     * @param blockSize
     */
    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

/*
  private static String toUtf8String(String sourceFilePathName) {
    StringBuffer sb = new StringBuffer();
    for (int blockSize = 0; blockSize < sourceFilePathName.length(); blockSize++) {
      char c = sourceFilePathName.charAt(blockSize);
      if (c >= 0 && c <= 255) {
        sb.append(c);
      }
      else {
        byte[] b;
        try {
          b = Character.toString(c).getBytes("utf-8");
        }
        catch (Exception ex) {
          b = new byte[0];
        }
        for (int j = 0; j < b.length; j++) {
          int k = b[j];
          if (k < 0) k += 256;
          sb.append("%" + Integer.toHexString(k).
              toUpperCase());
        }
      }
    }
    return sb.toString();
  }

*/

    /**
     * 将文件输出到客户端
     *
     * @param file
     * @throws java.io.IOException
     */
    public void write(File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            write(inputStream, file.getName());
        }
        catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
        finally {
            if (inputStream != null){
				try{
					inputStream.close();
				}
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 将文件输出到客户端
     *
     * @param file         文件对象
     * @param saveAsName 另存名
     * @throws java.io.IOException
     */
    public void write(File file, String saveAsName) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            String fileExt = getFileExt(file);
            String downFileName = "";
            int index = saveAsName.lastIndexOf(".");
            if (index == -1) { //文件下载别名中没有后缀名
                downFileName = saveAsName.concat(fileExt);
            } else {
                String saveFileExt = saveAsName.substring(index);
                if (!fileExt.equals(saveFileExt)) {//判断物理文件和下载别名的后缀名是否相同
                    downFileName = saveAsName.concat(fileExt);
                } else {
                    downFileName = saveAsName;
                }
            }
            write(inputStream, downFileName);
        }
        catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
        finally {
            if (inputStream != null){
				try{
					inputStream.close();
				}
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
