package com.rogrand.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：文件操作类
 */
public class FileUtil {

	/**
	 * 对数字类型的文件大小转换为以对应单位显示的字符
	 * 
	 * @param length
	 * @author zrh
	 * @return
	 */
	public static String length2size(long length) {
		if (length <= 0)
			return "0 KB";
		DecimalFormat df = new DecimalFormat("0.00");

		if (length < 1024) {
			return length + " byte";
		} else if (length < (1024 * 1024)) {
			return df.format(length / 1024.0) + " KB";
		} else if (length < (1024 * 1024 * 1024)) {
			return df.format(length / (1024.0 * 1024)) + " MB";
		} else {
			return df.format(length / (1024.0 * 1024 * 1024)) + " GB";
		}
	}
	
	/**
	 * 根据文件二进制流获取文件类型
	 * @param is
	 * @return
	 * @throws IOException
	 * @author zrh
	 */
	public static String fileType(InputStream is) throws IOException{
		if(is==null) {
			return "";
		}
		byte[] b = new byte[3];
		int len = is.read(b);
		if(len==3){
			return fileType(b);
		}
		return "";
	}

	/**
	 * 根据文件得到的前3个字节二进制流内容判断文件类型
	 * 
	 * @param b 长度一定为3
	 * @return 如返回 exe、jpg、xls等等，如果无法判断类型返回空
	 * @author zrh
	 */
	public static String fileType(byte[] b) {
		if (b == null || b.length < 3) {
			return "";
		}
		
		String hex = "";
		for (byte bi : new byte[]{b[0], b[1], b[2]}) {
			String s = Integer.toHexString(bi);
			if (s.length() == 8) {
				s = s.substring(6);
			}
			if (s.length() == 1) {
				s = "0" + s;
			}
			hex  += (s.toLowerCase());
		}
		
		
		if ("255044".equals(hex)) {
			return "pdf";
		} else if ("d0cf11".equals(hex)) {
			return "ppt";
		} else if ("4d5aee".equals(hex) || "e93b03".equals(hex)) {
			return "com";
		} else if ("4d5a90".equals(hex)) {
			return "exe";
		} else if ("424d3e".equals(hex)) {
			return "bmp";
		} else if ("ffd8ff".equals(hex)) {
			return "jpg";
		} else if ("89504e".equals(hex)) {
			return "png";
		} else if ("474946".equals(hex)) {
			return "gif";
		} else if ("000001".equals(hex)) {
			return "ico";
		} else if ("504b03".equals(hex)) {
			return "zip";
		} else if ("1f9d8c".equals(hex)) {
			return "z";
		} else if ("524946".equals(hex)) {
			return "wav";
		} else if ("435753".equals(hex)) {
			return "swf";
		} else if ("3026b2".equals(hex)) {
			return "wmv";
		} else if ("2e524d".equals(hex)) {
			return "rm";
		} else if ("fffb50".equals(hex)) {
			return "mp3";
		} else if ("3c2144".equals(hex)) {
			return "htm";
		} else if ("fffe3c".equals(hex)) {
			return "xsl";
		} else if ("3c3f78".equals(hex)) {
			return "xml";
		} else if ("495453".equals(hex)) {
			return "chm";
		} else if ("d0cf11".equals(hex)) {
			return "xls";
		} else if ("7b5c72".equals(hex)) {
			return "rtf";
		}
		return "";
	}

	/**
	 * 拷贝文件
	 * 
	 * @param from_name
	 *            源文件名
	 * @param to_name
	 *            目标文件
	 * @throws java.io.IOException
	 *             异常
	 */
	public static void copy(String from_name, String to_name)
			throws IOException {
		copy(new File(from_name), to_name);
	}

	/**
	 * 拷贝文件
	 * 
	 * @param file
	 *            源文件
	 * @param to_name
	 *            目标文件
	 * @throws IOException
	 *             异常
	 */
	public static void copy(File file, String to_name) throws IOException {
		// File from_file = new File(from_name);
		File to_file = new File(to_name);
		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(file); // 创建输入流
			to = new FileOutputStream(to_file); // 创建输出流
			byte[] buffer = new byte[1024]; // 保存文件内容到buffer
			int bytes_read; // 缓冲区大小
			while ((bytes_read = from.read(buffer)) != -1) {
				to.write(buffer, 0, bytes_read);
			}

		} finally { // 关闭流（永远不要忘记）
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
				}
			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
				}
		}
	}

	public static void createDirectory(String destinationDirectory) {
		File uploadDir = new File(destinationDirectory);
		if (!uploadDir.exists() || !uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
	}

	public static void deleteFile(String path) {
		File delFile = new File(path);
		if (delFile.exists() && delFile.isFile()) {
			delFile.delete();
		}
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	public static String read(File file) {
		return read(file, "UTF-8");
	}

	public static String read(File file, String charset) {
		FileInputStream inputStream = null;
		InputStreamReader reader = null;
		StringBuffer stringBuffer;
		char[] chars = new char[1024];
		try {
			stringBuffer = new StringBuffer();
			inputStream = new FileInputStream(file);
			reader = new InputStreamReader(inputStream, charset);
			int bytes_read;
			while ((bytes_read = reader.read(chars)) != -1) {
				stringBuffer.append(chars, 0, bytes_read);
			}
			return stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			chars = null;
			stringBuffer = null;
			reader = null;
			inputStream = null;
		}
	}

	public static byte[] readByte(File file) throws Exception {
		FileInputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		byte[] bytes = new byte[1024];
		try {
			inputStream = new FileInputStream(file);
			outputStream = new ByteArrayOutputStream();
			int bytes_read;
			while ((bytes_read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, bytes_read);
			}
			return outputStream.toByteArray();
		} catch (IOException e) {
			throw new Exception(e);
			// e.printStackTrace();
			// return null;
		} finally {
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bytes = null;
			outputStream = null;
			inputStream = null;
		}
	}

	private static void printPath(String path) throws IOException {
		File file = new File(path);
		File[] fs = file.listFiles();
		// for(File f:fs){
		// if(f.isHidden()) continue;
		// if(f.isFile()){
		// System.out.println(f.getCanonicalPath().substring(25));
		// }
		// }

		for (File f : fs) {
			if (f.isHidden())
				continue;
			if (f.isDirectory()) {
				System.out.println(f.getCanonicalPath().substring(25));
				printPath(f.getCanonicalPath());
			}
		}

	}

	public static int getFileRownum(File file) {
		Integer rownum = 0;
		if (file == null) {
			return rownum;
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			while (br.readLine() != null) {
				rownum++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rownum;
	}
}
