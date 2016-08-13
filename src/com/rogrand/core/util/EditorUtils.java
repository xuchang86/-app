package com.rogrand.core.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rogrand.core.service.MongoDbService;
import com.rogrand.core.system.SystemParameter;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-7-6 <br/>
 * 描述：〈描述〉
 */
public class EditorUtils {

    private static final Logger logger = Logger.getLogger(EditorUtils.class);

    /**
     * 描述：〈将html文本中图片标签的内容上传至ftp服务器，并修改为图片服务器对应的Url〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-7-7 <br/>
     * 
     * @param request 用户请求
     * @param imageBaseUrl 图片基础路径，如：http://img2.kkmaiyao.com/kkmy/upload/
     * @param html html内容
     * @return 转换后的html内容
     */
    public static String convertImageUrl(HttpServletRequest request, String imageBaseUrl, String html) {
        if (StringUtils.isNotEmpty(html)) {
            Document doc = Jsoup.parse(html);
            Elements imgs = doc.getElementsByTag("img");
            for (Element img : imgs) {
                String src = img.attr("src");
                // 值处理"/"开头src路径（新上传图片）
                if (StringUtils.isNotEmpty(src) && src.startsWith("/")) {
                    // 去掉相对路径前面的 /kkmy_manager
                    src = src.substring(src.indexOf("ueditor") - 1);
                    String realPath = request.getSession().getServletContext().getRealPath(src);
                    FtpUploader.upload(FtpConfig.getIp(), FtpConfig.getPort(), FtpConfig.getUsername(),
                            FtpConfig.getPassword(), new File(realPath), FilenameUtils.getName(src), "/kkmy/upload/"
                                    + FilenameUtils.getPath(src));
                    img.attr("src", imageBaseUrl + src);

                    // 删除WEB服务器上的附件
                    FileUtils.deleteQuietly(new File(realPath));
                }
            }
            return doc.select("body").html();
        }
        return null;
    }

    /**
     * 描述：〈将html文本中图片标签的内容上传至ftp服务器，并修改为图片服务器对应的Url〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-7-7 <br/>
     * 
     * @param request 用户请求
     * @param html html内容
     * @return 转换后的html内容
     */
    public static String convertImageUrl(HttpServletRequest request, String html) {
        String imageBaseUrl = SystemParameter.get("remoteImageUploadPath") + "kkmy/upload/";
        return convertImageUrl(request, imageBaseUrl, html);
    }

    public static void main(String[] args) {
        FileUtils.deleteQuietly(new File("C:\\Users\\Administrator\\Desktop\\test\\activeHtml.zip"));
    }

    /**
     * 描述：〈富文本中图片上传mongodb〉 <br/>
     * 作者：tao.chen@rogrand.com <br/>
     * 生成日期：2014年9月26日 <br/>
     * 
     * @param request
     * @param html
     * @param mongoDbService
     * @return
     * @throws IOException
     */
    public static String convertImageByMongoDb(HttpServletRequest request, String html, MongoDbService mongoDbService)
            throws IOException {
        if (StringUtils.isNotEmpty(html)) {
            String imageBaseUrl = SystemParameter.get("mongoDbUploadPath");
            Document doc = Jsoup.parse(html);
            Elements imgs = doc.getElementsByTag("img");
            for (Element img : imgs) {
                String src = img.attr("src");
                // 值处理"/"开头src路径（新上传图片）
                if (StringUtils.isNotEmpty(src) && src.startsWith("/")) {
                    // 去掉相对路径前面的 /kkmy_manager
                    src = src.substring(src.indexOf("ueditor") - 1);
                    String extName = "."+StringUtils.substringAfterLast(src, ".");
                    String realPath = request.getSession().getServletContext().getRealPath(src);
                    String objId = mongoDbService.uploadFile(realPath).toString();
                    img.attr("src", imageBaseUrl + objId + extName);
                    
                    // 删除WEB服务器上的附件
                    FileUtils.deleteQuietly(new File(realPath));
                }
            }
            return doc.select("body").html();
        }
        return null;
    }

}
