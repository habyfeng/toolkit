package com.albert.toolkit.service;

import com.albert.toolkit.template.MyJsonRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MyJsonRedisTemplate myJsonRedisTemplate;

    public String readString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void storeString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofMinutes(5));
    }

    public void storeMap(String key, Map<String, Object> map) {
        myJsonRedisTemplate.opsForValue().set(key, map, Duration.ofMinutes(5));

    }

    public Map<String, Object> readMap(String key) {
        return (Map<String, Object>) myJsonRedisTemplate.opsForValue().get(key);
    }

    public void putHash(String key, String hashKey, Object hashValue) {
        myJsonRedisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    public Map<String, Object> readHashValue(String key, String hashKey) {
        return (Map<String, Object>) myJsonRedisTemplate.opsForHash().get(key, hashKey);
    }

}
