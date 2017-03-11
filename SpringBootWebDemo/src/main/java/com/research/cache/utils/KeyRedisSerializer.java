package com.research.cache.utils;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by Adm on 2016/12/12.
 */
public class KeyRedisSerializer implements RedisSerializer {
    public byte[] serialize(Object t) throws SerializationException {
        if(t instanceof String){
            return StringUtils.getBytesUtf8((String)t);
        }
        return SerializeUtils.serialize(t);
    }

    public Object deserialize(byte[] bytes) throws SerializationException {
        return new String(bytes);
    }
}
