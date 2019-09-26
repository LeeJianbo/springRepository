package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lee on 2019/09/25
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplateObject;

    /**
     * 通过key设置string值的缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, final String value) {
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        vo.set(key, value);
        return true;
    }

    /**
     * 通过key设置string值的缓存
     * @param key
     * @param value
     * @return
     */
    public boolean setObject(final Object key, final Object value) {
        ValueOperations<Object, Object> vo = redisTemplateObject.opsForValue();
        vo.set(key, value);
        return true;
    }

    /**
     * 通过key获取string值的缓存
     * @param key
     * @return
     */
    public String get(final String key) {
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    /**
     * 通过key获取string值的缓存
     * @param key
     * @return
     */
    public Object getObject(final Object key) {
        ValueOperations<Object, Object> vo = redisTemplateObject.opsForValue();
        return vo.get(key);
    }

    /**
     * 通过key设置map值的缓存
     * @param key
     * @param map
     * @return
     */
    public boolean hMSet(String key, Map<String, String> map) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        hash.putAll(key, map);
        return true;
    }

    /**
     * 通过key获取map值的缓存
     * @param key
     * @return
     */
    public Map hMGet(String key) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.entries(key);
    }

    /**
     * 通过key设置list值的缓存
     * @param key
     * @param list
     * @return
     */
    public boolean setList(String key, List<String> list) {
        ListOperations<String, String> operList = redisTemplate.opsForList();
        operList.trim(key, 1, 0);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                operList.rightPush(key, list.get(i));
            }
        }
        return true;
    }

    /**
     * 通过key获取list值的缓存
     * @param key
     * @return
     */
    public List<String> getList(String key) {
        ListOperations<String, String> operList = redisTemplate.opsForList();
        return operList.range(key, 0, -1);
    }

    /**
     * 设置key的缓存的过期时间
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * list操作左插入
     * @param key
     * @param obj
     * @return
     */
    public long lpush(final String key, Object obj) {
        final String value = JsonUtils.toJson(obj);
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return connection.lPush(serializer.serialize(key), serializer.serialize(value));
            }
        });
    }

    /**
     * list操作右插入
     * @param key
     * @param obj
     * @return
     */
    public long rpush(final String key, Object obj) {
        final String value = JsonUtils.toJson(obj);
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return connection.rPush(serializer.serialize(key), serializer.serialize(value));
            }
        });
    }

    /**
     * list操作从左获取值，获取后值被移出list
     * @param key
     * @return
     */
    public String lpop(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res = connection.lPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
        });
    }

    /**
     * /list操作从右获取值，获取后值被移出list
     * @param key
     * @return
     */
    public String rpop(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res = connection.rPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
        });
    }

    /**
     * 通过key删除缓存
     * @param key
     */
    public void removeByKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * /追加hash值
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean hset(String key, String hashKey, String value){
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
        return true;
    }

    /**
     * 删除hash值
     * @param key
     * @param hashKeys
     */
    public void hremove(String key, Object... hashKeys){
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        hash.delete(key, hashKeys);
    }

    /**
     * 获取list的Size
     * @param key
     * @return
     */
    public Integer getListSize(String key) {
        return redisTemplate.execute((RedisCallback<Integer>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            Long res = connection.lLen(serializer.serialize(key));
            return res.intValue();
        });
    }
}
