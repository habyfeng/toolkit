package com.albert.toolkit.template;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 自定义RedisTemplate
 */
@Component
public class MyJsonRedisTemplate extends RedisTemplate<String, Object> {

    public MyJsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        super.setConnectionFactory(redisConnectionFactory);

        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
        jsonSerializer.configure(objectMapper -> {
            objectMapper.registerModule(new JavaTimeModule());
        });

        // String类型的key/value序列化
        super.setKeySerializer(StringRedisSerializer.UTF_8);
        super.setValueSerializer(jsonSerializer);
        // Hash类型的key/value序列化
        super.setHashKeySerializer(StringRedisSerializer.UTF_8);
        super.setHashValueSerializer(jsonSerializer);

    }
}
