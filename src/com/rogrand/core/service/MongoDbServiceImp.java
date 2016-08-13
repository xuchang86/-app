package com.rogrand.core.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.cxf.common.util.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service("MongoDbService")
public class MongoDbServiceImp implements MongoDbService {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

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
    @Override
    public ObjectId uploadFile(String filePath) throws IOException {
        ObjectId objectId = null;
        try {
            GridFS gridFS = new GridFS(mongoTemplate.getDb());
            File file = new File(filePath);
            GridFSInputFile mongofile = gridFS.createFile(file);
            mongofile.save();
            objectId = (ObjectId) mongofile.getId();
            if (objectId == null) {
                throw new RuntimeException("ObjectId为null");
            }
            file.delete();
        } catch (IOException e) {
            throw new RuntimeException("mongoUploadErr", e);
        }
        return objectId;
    }

    /**
     * 描述：〈根据objID删除文件〉 <br/>
     * 作者：tao.chen@rogrand.com <br/>
     * 生成日期：2014年9月24日 <br/>
     * 
     * @param objectId
     * @param mongoTemplate
     */
    @Override
    public void delete(String objectId) {
        new GridFS(mongoTemplate.getDb()).remove(new ObjectId(objectId));
    }

    /**
     * 描述：〈上传远程图片至mongodb〉 <br/>
     * 作者：tao.chen@rogrand.com <br/>
     * 生成日期：2014年9月24日 <br/>
     * 
     * @param remoteFilePath
     * @param localFilePath
     */
    public ObjectId uploadRemoteFile(String remoteFilePath, String localFilePath) throws IOException {
        ObjectId objectId = null;
        HttpURLConnection connection = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            // 1.下载远程图片至本地
            URL url = new URL(remoteFilePath);
            connection = (HttpURLConnection) url.openConnection();
            in = new DataInputStream(connection.getInputStream());
            out = new DataOutputStream(new FileOutputStream(localFilePath));
            byte[] buffer = new byte[4096];
            int count = 0;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
            // 2.上传图片至mongodb
            objectId = uploadFile(localFilePath);
        } catch (IOException e) {
            throw new RuntimeException("mongoUploadErr", e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return objectId;
    }

    @Override
    public String getFileName(String objId) throws IllegalArgumentException {
        if (StringUtils.isEmpty(objId)) {
            throw new IllegalArgumentException("the paramter objectId is empty");
        }
        ObjectId objectId = new ObjectId(objId);
        GridFS gridFS = new GridFS(mongoTemplate.getDb());
        GridFSDBFile gfsdbFile = gridFS.findOne(objectId);
        String fileName = gfsdbFile.getFilename();
        if (StringUtils.isEmpty(fileName)) {
            throw new RuntimeException("FileName is null,check objeciIt is or right");
        }
        return gfsdbFile.getFilename();
    }

}
