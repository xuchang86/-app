package com.rogrand.core.cache.redis;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.rogrand.core.cache.CacheManager;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-8-4 <br/>
 * 描述：〈Redis缓存管理实现〉
 */
@Component("cacheManager")
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }
    
    @Override
    public void expireAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setValue(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds);
    }

    @Override
    public void setValue(String key, Object value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    @Override
    public List<Object> getValues(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public boolean hasHashKey(String key, String k) {
        return redisTemplate.opsForHash().hasKey(key, k);
    }

    @Override
    public void putHashValue(String key, String k, Object v) {
        redisTemplate.opsForHash().put(key, k, v);
    }

    @Override
    public Object getHashValue(String key, String k) {
        return redisTemplate.opsForHash().get(key, k);
    }

    @Override
    public void delHashKey(String key, String k) {
        redisTemplate.opsForHash().delete(key, k);
    }
    
    @Override
    public Set<Object> getHashKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }
    
    @Override
    public Map<Object, Object> getHashMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void putHashMap(String key, Map<Object, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }
    
    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }
    
    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }
    
}
