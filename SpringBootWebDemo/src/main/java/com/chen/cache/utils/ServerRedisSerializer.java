package com.chen.cache.utils;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by Adm on 2016/12/12.
 */
public class ServerRedisSerializer implements RedisSerializer {

    /**
     * 序列化
     * @param o
     * @return
     * @throws SerializationException
     */
    public byte[] serialize(Object o) throws SerializationException {
        return SerializeUtils.serialize(o);
    }

    /**
     * 反序列化
     * @param o
     * @return
     * @throws SerializationException
     */
    public Object deserialize(byte[] bytes) throws SerializationException {
        return SerializeUtils.deserialize(bytes);
    }
}
