package com.rogrand.core.service;

import java.io.IOException;

import org.bson.types.ObjectId;

public interface MongoDbService {

    /**
     * 描述：〈上传文件〉 <br/>
     * 作者：tao.chen@rogrand.com <br/>
     * 生成日期：2014年9月24日 <br/>
     * 
     * @param filePath
     * @param mongoTemplate
     * @return
     * @throws IOException
     */
    ObjectId uploadFile(String filePath) throws IOException;

    /**
     * 描述：〈根据objID删除文件〉 <br/>
     * 作者：tao.chen@rogrand.com <br/>
     * 生成日期：2014年9月24日 <br/>
     * 
     * @param objectId
     * @param mongoTemplate
     */
    void delete(String objectId);

    /**
     * 描述：〈上传远程图片至mongodb〉 <br/>
     * 作者：tao.chen@rogrand.com <br/>
     * 生成日期：2014年9月25日 <br/>
     * 
     * @param remoteFilePath
     * @param localFilePath
     * @return
     * @throws IOException
     */
    ObjectId uploadRemoteFile(String remoteFilePath, String localFilePath) throws IOException;

    /**
     * 描述：〈根据objId获取Mongo服务器文件名〉 <br/>
     * 作者：tao.chen@rogrand.com <br/>
     * 生成日期：2014年9月28日 <br/>
     * 
     * @param objId
     * @return
     */
    String getFileName(String objId);
}
