package com.rogrand.core.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：对象序列化和克隆
 */
public abstract class SerializeCloneable implements Serializable, Cloneable {
    
	private static final long serialVersionUID = 5855390870165750563L;
	
	protected Log logger = LogFactory.getLog(getClass());

    @Override
    public final Object clone() throws CloneNotSupportedException {
        ByteArrayOutputStream byteOut = null; //二进制输出流
        ObjectOutputStream objectOut = null;  //对象输出流
        ByteArrayInputStream byteIn = null;   //二进制输入流
        ObjectInputStream objectIn = null;    //对象输入流
        try {
            byteOut = new ByteArrayOutputStream();
            objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(this);
            byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            objectIn = new ObjectInputStream(byteIn);
            return objectIn.readObject();
        }
        catch (IOException e) {
            logger.error(e.getMessage());
            throw new CloneNotSupportedException(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new CloneNotSupportedException(e.getMessage());
        }
        finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
            }
            catch (IOException e) {
                logger.error(e.getMessage());
            }
            try {
                if (byteIn != null) {
                    byteIn.close();
                }
            }
            catch (IOException e) {
                logger.error(e.getMessage());
            }
            try {
                if (objectOut != null) {
                    objectOut.close();
                }
            }
            catch (IOException e) {
                logger.error(e.getMessage());
            }
            try {
                if (byteOut != null) {
                    byteOut.close();
                }
            }
            catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

}
