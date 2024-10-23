package com.albert.toolkit.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testStoreString() {
        redisService.storeString("title", "once more");
        String title = redisService.readString("title");
        System.out.println(title);
    }

    @Test
    public void testStoreMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user1", "aaa");
        map.put("user2", "bbb");
        redisService.storeMap("titles", map);

        Map<String, Object> value = redisService.readMap("titles");
        System.out.println(value);
    }

    @Test
    public void testPutHash() {
        String key = "apps";
        String hashKey = "tiktok";

        Map<String, Object> map = new HashMap<>();
        map.put("name", "tiktok");
        map.put("users", "1billions");
        map.put("update_date", LocalDateTime.now());

        redisService.putHash(key, hashKey, map);

        System.out.println(redisService.readHashValue(key, hashKey));
    }

}
