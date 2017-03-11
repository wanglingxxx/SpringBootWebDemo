package com.research.config.redisconfig;

import com.research.cache.utils.KeyRedisSerializer;
import com.research.cache.utils.ServerRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;

/**
 * Created by Adm on 2016/12/12.
 */
@Configuration
@PropertySource(value = "application.properties")
@EnableCaching
public class CacheConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int db;

    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {


            public Object generate(Object arg0, Method arg1, Object... arg2) {
                StringBuilder sb=new StringBuilder();
                sb.append(arg0.getClass().getName());
                sb.append(arg1.getName());
                for(Object obj:arg2){
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory factory=new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setDatabase(db);
        return factory;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0)
        cacheManager.setDefaultExpiration(10); //设置key-value超时时间
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String,String> redisTemplate(JedisConnectionFactory factory){
        StringRedisTemplate template=new StringRedisTemplate(factory);
        setSerializer(template);//设置序列化工具，这样ReportBean不需要实现Serializable接口
        template.afterPropertiesSet();
        return template;
    }


    private void setSerializer(StringRedisTemplate template){
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om=new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setKeySerializer(new KeyRedisSerializer());
        template.setValueSerializer(new ServerRedisSerializer());
        template.setHashKeySerializer(new KeyRedisSerializer());
        template.setHashValueSerializer(new ServerRedisSerializer());
//		template.setHashKeySerializer(jackson2JsonRedisSerializer);
//		template.setHashValueSerializer(jackson2JsonRedisSerializer);
        //ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);
        //mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
