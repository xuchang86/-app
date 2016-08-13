package com.rogrand.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：压缩文件处理类
 */
public class ZipUtil {
	
	/**
	 * 压缩文件
	 * 
	 * @param srcPathname 待压缩的文件
	 * @param descPathname 压缩后的目标文件
	 * @return 压缩成功返回true，否则返回false
	 * 
	 * @author ZhangRenhua
	 */
    public static boolean zip(String srcPathname, String descPathname) {

        File zipFile = new File(descPathname);
        File srcFile = new File(srcPathname);
        
        ZipOutputStream zos = null;
        InputStream is = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            ZipEntry ze = new ZipEntry(srcFile.getName());
            ze.setSize(srcFile.length());
            ze.setTime(srcFile.lastModified());
            zos.putNextEntry(ze);
            is = new BufferedInputStream(new FileInputStream(srcFile));
            
            byte[] buf = new byte[1024];
            int readLen = -1;
            while ((readLen = is.read(buf)) != -1) {
                zos.write(buf, 0, readLen);
            }
            zos.flush();
        } catch (IOException ex) {
        	ex.printStackTrace();
            return false;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ex) {}
            try {
            	if (zos != null) zos.close();
            } catch (IOException ex) {}
        }
        return true;
    }
	
    /**
     * 解压文件
     * 
     * @param zipPathname 待解压的zip文件
     * @param destDir 解压目录
     * @return 解压成功返回true,否则返回false
     * 
     * @author ZhangRenhua
     */
    @SuppressWarnings("unchecked")
	public static boolean unzip(String zipPathname, String destDir) {
    	destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
    	byte b[] = new byte[512];
		int length;
		org.apache.tools.zip.ZipFile zipFile = null;
		try {
			zipFile = new org.apache.tools.zip.ZipFile(new File(zipPathname));
			Enumeration enumeration = zipFile.getEntries();
			org.apache.tools.zip.ZipEntry zipEntry = null;
			while (enumeration.hasMoreElements()) {
				zipEntry = (org.apache.tools.zip.ZipEntry) enumeration.nextElement();
				File loadFile = new File(destDir + zipEntry.getName());
				if (zipEntry.isDirectory()) {
					loadFile.mkdirs();
				} else {
					if (!loadFile.getParentFile().exists()){
						loadFile.getParentFile().mkdirs();
					}
					OutputStream outputStream = null;
					InputStream inputStream = null;
					try{
						outputStream = new FileOutputStream(loadFile);
						inputStream = zipFile.getInputStream(zipEntry);
						while ((length = inputStream.read(b)) > 0){
							outputStream.write(b,0,length);
						}
						outputStream.flush();
					}finally{
						try {
			                if (inputStream != null) inputStream.close();
			            } catch (IOException ex) {}
			            try {
			            	if (outputStream != null) outputStream.close();
			            } catch (IOException ex) {}
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally{
			if(zipFile!=null){
				try{
					zipFile.close();
				}catch(Exception e){}
			}
		}
    	return true;
    }
    
    

    /**
     * 压缩成zip
     * @param outfile
     * @param infile
     * @return
     */
    public static boolean reduceZIP(String outfile, String infile) {
    	FileOutputStream fout = null;
    	ZipOutputStream out = null;
    	try {
            // 文件输出流
            fout = new FileOutputStream(outfile);
            // zip文件输出流
            out = new ZipOutputStream(fout);
            // 要打包的文件
            File file = new File(infile);
            // 进行打包zip操作,第一次打包不指定文件夹,因为程序接口中指定了一级文件夹
            makeFile_Zip_Do(out, file, "");
            // 关闭zip输出流
            out.close();
            //返回成功
            return true;
        } catch (Exception e) {
            return false;
        } finally{
        	if(fout!=null){
        		try {
					fout.close();
				} catch (IOException e) {
				}
        	}
        	if(out!=null){
        		try {
					out.close();
				} catch (IOException e) {
				}
        	}
        }
    }

    /**
     * 打包文件操作类
     * @param out  zip输出流
     * @param file  打包文件
     * @param dir   下一级的目录
     * @throws IOException
     */
    public static void makeFile_Zip_Do(ZipOutputStream out, File file, String dir) throws IOException {
        //如果当前打包的是目录
        if (file.isDirectory()) {
            //输出进度信息
            //文件列表
            File[] files = file.listFiles();
            //添加下一个打包目录文件
            out.putNextEntry(new ZipEntry(dir + "/"));
            dir = dir.length() == 0 ? "" : dir + "/";
            for (int i = 0; i < files.length; i++) {
                makeFile_Zip_Do(out, files[i], dir + files[i].getName());
            }
        }
        //如果当前打包文件
        else {
            //输出进度信息
            out.putNextEntry(new ZipEntry(dir));
            //文件输入流
            FileInputStream in = new FileInputStream(file);
            int i;
            //进行写入
            while ((i = in.read()) != -1) {
                out.write(i);
            }
            //关闭输入流
            in.close();
        }
    }

    /**
     * 解压zip文件操作类
     * @param zipfile  zip文件
     * @param savedir  输出文件夹
     * @return
     */
    public static boolean openFile_Zip(String zipfile, String savedir) {
        try {
            //文件输入流
            FileInputStream file = new FileInputStream(zipfile);
            //zip输入流
            ZipInputStream in = new ZipInputStream(file);
            ZipEntry z;
            while ((z = in.getNextEntry()) != null) {
                //如果是文件夹
                if (z.isDirectory()) {
                    System.out.println("正在解压文件夹:" + z.getName());
                    File tempfile = new File(savedir + File.separator + z.getName().substring(0, z.getName().length() - 1));
                    //创建目录
                    tempfile.mkdir();
                    System.out.println("已经创建目录:" + savedir + File.separator + z.getName().substring(0, z.getName().length() - 1));
                }
                //如果是文件
                else {
                    System.out.println("正在解压文件:" + z.getName());
                    File tempfile = new File(savedir + File.separator + z.getName());
                    //创建新文件
                    tempfile.createNewFile();
                    //文件输出流
                    FileOutputStream out = new FileOutputStream(tempfile);
                    int i;
                    while ((i = in.read()) != -1) {
                        out.write(i);
                    }
                    //文件输出流关闭
                    out.close();
                }
            }
            //文件输入流关闭
            in.close();
            return true;
        } catch (Exception e) {
            System.out.println("文件" + zipfile + "解压失败…");
            return false;
        }
    }

    /**
     * 压缩成zip
     *
     * @param filePath
     * @param file
     * @return
     */
	public static File reduceZIP(String filePath, File file) {
		byte[] buf = new byte[1024];
		// 压缩文件名
		File zipFile = new File(filePath);
		ZipOutputStream zos = null;
		InputStream is = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(zipFile));
			ZipEntry ze = new ZipEntry(file.getName());
			ze.setSize(file.length());
			ze.setTime(file.lastModified());
			zos.putNextEntry(ze);
			is = new BufferedInputStream(new FileInputStream(file));
			int readLen = -1;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				zos.write(buf, 0, readLen);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (is != null)
					is.close();
				if (zos != null)
					zos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		return zipFile;
	}
	
	public static void main(String[] args) {
        String zipPath = "D:\\Users\\Administrator\\Desktop\\kkmy-common-provider-2.0.1-SNAPSHOT.jar";
        String unzipPath = "D:\\Users\\Administrator\\Desktop\\" + System.currentTimeMillis();
        System.out.println(unzip(zipPath, unzipPath));
        
        System.out.println("File.separator ->" + File.separator);
    }

}
