package com.research.cache;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;


/**
 * Created by Adm on 2016/12/12.
 */
@Component
public class CacheUtil4Hash {
    static RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    public static <T> void cache4Hash(final List<T> list, final String tableName, ICacheKey t1) {

        RedisSerializer keySerializer = redisTemplate.getKeySerializer();

        final Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();

        for (T o : list) {
            map.put(keySerializer.serialize(t1.getCacheKey(o)), keySerializer.serialize(o));
        }

        redisTemplate.execute(new RedisCallback<Boolean>() {


            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.hMSet(tableName.getBytes(), map);
                return true;
            }
        },false,true);

        try {

        } catch (Exception e) {
            System.out.println("缓存到redis失败");
        }

    }

    public static<T> List<T> get4HashFromRedis(final String tableName,List keys){
        List<T> result = new ArrayList<T>();
        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        HashOperations opsForHash = redisTemplate.opsForHash();
        List<T> keystemp = new ArrayList<T>();
        if(keys == null){
            return (List<T>)opsForHash.values(tableName);
        }
        for(Object t : keys){
            keystemp.add((T) keySerializer.serialize(t));
        }


        @SuppressWarnings("unchecked")
        List<T> multiGet = opsForHash.values(tableName);
        System.out.println("start size : " + multiGet.size());
        if(multiGet.size()>0){
            for(T o : multiGet){
                if(o != null){
                    result.add(o);
                }
            }
        }
        try{

        }catch(Exception e){
            System.out.println("从缓存中取数据失败");
        }
        System.out.println("从缓存中得到数据大小: " +  result.size());
        return result;
    }




}
