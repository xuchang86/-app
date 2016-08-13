package com.rogrand.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 版权：融贯资讯 <br/>
 * 作者：yong.li@rogrand.com <br/>
 * 生成日期：2013-11-28 <br/>
 * 描述：ftp上传工具类
 */

public class FtpUploader {

    private static String ENCODING = "UTF-8";

    /**
     * 
     * 将指定文件上传至ftp <br/>
     * 
     * @param ip ftp服务器ip <br/>
     * @param port ftp服务器端口 <br/>
     * @param user ftp用户名 <br/>
     * @param pwd ftp密码 <br/>
     * @param fileName 新文件名<br/>
     * @param localFile 需要上传的文件,含路径<br/>
     * @param remoteDir 上传至ftp服务器上的路径<br/>
     * 
     * @return true 成功 false 失败 <br/>
     */
    public static boolean FTPUpload(String ip, int port, String user, String pwd, String fileName, String localFile,
            String remoteDir) {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;

        boolean ans = false;
        try {
            ftpClient.connect(ip, port);

            ftpClient.setControlEncoding(ENCODING);
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");

            ftpClient.login(user, pwd);
            ftpClient.setBufferSize(1024);

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return ans;
            }

            File srcFile = new File(localFile);
            String _filename = new String(fileName.getBytes(ENCODING), "ISO-8859-1");
            String path = new String(remoteDir.getBytes(ENCODING), "ISO-8859-1");
            createDir(ftpClient, path);
            ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            fis = new FileInputStream(srcFile);
            if (ftpClient.storeFile(_filename, fis)) {
                ans = true;
            } else {
                ans = false;
            }
            fis.close();
            ftpClient.logout();
        } catch (IOException e) {
            throw new RuntimeException("FTP", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("ftp", e);
            }
        }
        return ans;
    }

    public static boolean upload(String ip, int port, String username, String password, File localFile,
            String saveAsName, String remoteDir) {
        FTPClient ftpClient = new FTPClient();

        FileInputStream fis = null;

        boolean ans = false;
        try {
            ftpClient.connect(ip, port);

            ftpClient.setControlEncoding(ENCODING);
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");

            ftpClient.login(username, password);
            ftpClient.setBufferSize(1024);

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return ans;
            }

            String _filename = new String(saveAsName.getBytes(ENCODING), "ISO-8859-1");
            String path = new String(remoteDir.getBytes(ENCODING), "ISO-8859-1");
            createDir(ftpClient, path);
            ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            fis = new FileInputStream(localFile);
            if (ftpClient.storeFile(_filename, fis)) {
                ans = true;
            } else {
                ans = false;
            }
            fis.close();
            ftpClient.logout();
        } catch (IOException e) {
            throw new RuntimeException("FTP", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("ftp", e);
            }
        }
        return ans;
    }

    private static void createDir(FTPClient ftpClient, String dir) throws IOException {
        if (dir == null || dir.length() == 0) {
            return;
        }

        if (!isDirExist(ftpClient, dir)) {
            String path = dir.substring(0, dir.lastIndexOf("/"));
            createDir(ftpClient, path);
            ftpClient.makeDirectory(dir);
        }
    }

    private static boolean isDirExist(FTPClient ftpClient, String dir) {
        try {
            int retCode = ftpClient.cwd(dir);
            return FTPReply.isPositiveCompletion(retCode);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 描述：〈上传文件夹〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-7-3 <br/>
     * 
     * @param ip ftp服务器IP
     * @param port ftp服务器端口
     * @param user ftp账号
     * @param pwd ftp账号密码
     * @param localPath 本地文件夹
     * @param remotePath 远程目录
     * @return 是否上传成功
     */
    public static boolean uploadDir(String ip, int port, String user, String pwd, String localPath, String remotePath) {
        FTPClient ftpClient = new FTPClient();

        boolean ans = false;
        try {
            ftpClient.connect(ip, port);
            ftpClient.setControlEncoding(ENCODING);

            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");

            ftpClient.login(user, pwd);
            ftpClient.setBufferSize(1024);

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return ans;
            }

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            File localDir = new File(localPath);
            if (localDir != null && localDir.exists() && localDir.isDirectory()) {
                String dirName = new String(localDir.getName().getBytes(ENCODING), "ISO-8859-1");
                processDir(ftpClient, localDir, remotePath.concat(dirName).concat("/"));
            }

            ftpClient.logout();
        } catch (IOException e) {
            throw new RuntimeException("FTP", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("ftp", e);
            }
        }
        return ans;
    }

    /**
     * 描述：〈递归处理文件夹〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-7-3 <br/>
     * 
     * @param ftpClient FTPClient
     * @param dir 本地目录
     * @param remotePath 远程路径
     * @return
     * @throws IOException 
     */
    private static boolean processDir(FTPClient ftpClient, File dir, String remotePath) throws IOException {
        if (dir != null && dir.exists() && dir.isDirectory()) {
            createDir(ftpClient, remotePath);
            
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {// 是文件夹
                        processDir(
                                ftpClient,
                                file,
                                remotePath.concat(new String(file.getName().getBytes(ENCODING), "ISO-8859-1")).concat(
                                        "/"));
                    } else {// 是文件
                        processFile(ftpClient, file, remotePath);
                    }
                }
            }
            return true;
        } else {
            System.out.println("指定文件夹不存在.");
            return false;
        }
    }

    /**
     * 描述：〈处理文件〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-7-3 <br/>
     * 
     * @param ftpClient FTPClient
     * @param file 本地文件
     * @param remotePath 远程路径
     * @return
     * @throws IOException 
     */
    private static boolean processFile(FTPClient ftpClient, File file, String remotePath) throws IOException {
        String name = new String(file.getName().getBytes(ENCODING), "ISO-8859-1");
        String remoteFilePath = remotePath.concat(name);

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return ftpClient.storeFile(remoteFilePath, in);
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("D:\\Users\\Administrator\\Desktop\\kkmy-common-provider-2.0.1-SNAPSHOT.jar");

        System.out.println("file.getPath() -> " + file.getPath());
        System.out.println("file.getName() -> " + file.getName());

        System.out.println("File.separator -> " + File.separator);

        uploadDir("img2.kkmaiyao.com", 21, "kkmytest", "kkmytest20131202",
                "D:\\Users\\Administrator\\Desktop\\模板", "/kkmy/upload/activity/html/");
    }
}
