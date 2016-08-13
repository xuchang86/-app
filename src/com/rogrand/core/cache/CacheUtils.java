package com.rogrand.core.cache;

import java.io.Serializable;

import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-8-4 <br/>
 * 描述：〈缓存工具类〉
 */
public class CacheUtils {

    /**
     * 描述：〈通过Class得到Cache Key〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param clazz 类
     * @return 类前缀key
     */
    @SuppressWarnings("rawtypes")
    public static String keyOfClassPrefix(Class clazz) {
        Assert.notNull(clazz);
        return clazz.getName();
    }

    /**
     * 描述：〈通过Class获取CacheKey〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param clazz 类
     * @param id 类id
     * @return 类key
     */
    @SuppressWarnings("rawtypes")
    public static String keyOfClass(Class clazz, Serializable id) {
        Assert.notNull(clazz);
        Assert.notNull(id);
        return clazz.getName() + "_" + id;
    }

    /**
     * 描述：〈通过Class以及Serializable得到Cache Key〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param object 对象
     * @param id 对象id
     * @return 对象key
     */
    public static String keyOfObject(Object object, Serializable id) {
        Assert.notNull(object);
        Assert.notNull(id);
        return object.getClass().getName() + "_" + id;
    }

    public static void main(String[] args) {
        System.out.println(keyOfClassPrefix(CacheManager.class));
    }
}
