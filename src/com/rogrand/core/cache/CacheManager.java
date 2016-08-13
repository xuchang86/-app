package com.rogrand.core.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-8-4 <br/>
 * 描述：〈缓存管理统一接口〉
 */
public interface CacheManager {

    /**
     * 描述：〈是否存在缓存〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @return true/false
     */
    boolean hasKey(String key);

    /**
     * 描述：〈设置缓存过期〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param timeout 过期时间，单位：微秒
     */
    void expire(String key, long timeout);
    
    /**
     * 描述：〈设置缓存过期〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param timeout 时间间隔
     * @param unit 时间单位
     */
    void expire(String key, long timeout, TimeUnit unit);
    
    /**
     * 描述：〈指定缓存过期时间〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param date 缓存过期时间
     */
    void expireAt(String key, Date date);

    /**
     * 描述：〈设置缓存〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param value 缓存值
     */
    void setValue(String key, Object value);

    /**
     * 描述：〈设置缓存（* 该方法目前存在问题，尚未查明原因，请使用 expire 方法代替缓存失效功能*）〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param value 缓存值
     * @param seconds 缓存时间，单位：秒
     */
    @Deprecated
    void setValue(String key, Object value, long seconds);

    /**
     * 描述：〈设置缓存（* 该方法目前存在问题，尚未查明原因，请使用 expire 方法代替缓存失效功能*）〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param value 缓存值
     * @param time 缓存时间
     * @param unit 缓存时间单位
     */
    @Deprecated
    void setValue(String key, Object value, long time, TimeUnit unit);

    /**
     * 描述：〈获取缓存〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @return 缓存值
     */
    Object getValue(String key);
    
    /**
     * 描述：〈获取多个缓存〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-5 <br/>
     * 
     * @param keys 缓存key集合
     * @return 多个缓存集合
     */
    List<Object> getValues(Collection<String> keys);

    /**
     * 描述：〈删除缓存〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     */
    void delete(String key);

    /**
     * 描述：〈批量删除缓存〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param keys 缓存keys
     */
    void delete(Collection<String> keys);

    /**
     * 描述：〈Hash缓存中是否存在hash-key〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param k hash-key
     * @return true/false
     */
    boolean hasHashKey(String key, String k);

    /**
     * 描述：〈设置Hash缓存值〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param k hash-key
     * @param v hash-value
     */
    void putHashValue(String key, String k, Object v);

    /**
     * 描述：〈获取Hash缓存中的值〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param k hash-key
     * @return hash-value
     */
    Object getHashValue(String key, String k);

    /**
     * 描述：〈删除Hash缓存中的值〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-4 <br/>
     * 
     * @param key 缓存key
     * @param k hash-key
     */
    void delHashKey(String key, String k);

    /**
     * 描述：〈获取Hash缓存中所有的hash-key〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-5 <br/>
     * 
     * @param key 缓存key
     * @return 所有hash-key
     */
    Set<Object> getHashKeys(String key);
    
    /**
     * 描述：〈获取Hash缓存中的所有数据〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-5 <br/>
     * 
     * @param key 缓存key
     * @return Hash缓存中的数据
     */
    Map<Object, Object> getHashMap(String key);
    
    /**
     * 描述：〈存储Hash缓存〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-5 <br/>
     * 
     * @param key 缓存key
     * @param map Hash缓存中的数据
     */
    void putHashMap(String key, Map<Object, Object> map);
    
    /**
     * 描述：〈获取缓存失效时间〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014年10月14日 <br/>
     * 
     * @param key 缓存key
     * @return 剩余时间（单位：秒）
     */
    Long getExpire(String key);
    
    /**
     * 描述：〈获取缓存失效时间〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014年10月14日 <br/>
     * 
     * @param key 缓存key
     * @param timeUnit 时间单位
     * @return 剩余时间
     */
    Long getExpire(String key, TimeUnit timeUnit);
}
