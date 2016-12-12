package com.chen.controller;


import com.chen.cache.CacheUtil4Hash;
import com.chen.cache.ICacheKey;
import com.chen.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestRedisCache {

   @Autowired
   RedisTemplate redisTemplate;

	public static void main(String [] args){
		redisTest();
	}



   @SuppressWarnings("unchecked")
   @RequestMapping("/test")
   public String getSubResult(){
//	   iredisService.sub2(1, 2);
//	   iredisService.sub2(2, 1);
	   Map<byte[],byte[]> map=new HashMap<byte[],byte[]>();
	   
	   map.put("123".getBytes(), "123".getBytes());

	   HashOperations opsForHash = redisTemplate.opsForHash();
	   Object object = opsForHash.get("liucaijin".getBytes(), "123".getBytes());
//	   redisTemplate.execute(new RedisCallback<Boolean>() {
//
//		@Override
//		public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//			connection.hMSet("liucaijin".getBytes(), map); 
//			return true;
//		}
//		   
//	});
	   return "ok";
   }
   
   @RequestMapping("/test2")
   @ResponseBody
   public static String redisTest(){
	   System.out.println("进入 test 2");
	   List<User> users=new ArrayList<User>();
	   users.add(new User(1,11));
	   users.add(new User(2,12));
	   users.add(new User(3,13));
	   users.add(new User(5,14));

	   users.add(new User(4,11));
	   users.add(new User(6,12));
	   users.add(new User(7,13));
	   users.add(new User(8,14));

	   users.add(new User(9,13));
	   users.add(new User(10,14));

		if(isExists(users) != null){
			//System.out.println(users);
			return "从缓存中拿到数据 " + isExists(users);
		}

	   CacheUtil4Hash.cache4Hash(users, "users", new ICacheKey<User>() {

		public String getCacheKey(User t) {
			System.out.println("已存到Redis key: " + t.getId() + "  value: " + t);
			return t.getId()+"";
		}
		});

	   return "新建缓存成功!";
   }

	private static List<User> isExists(List<User> users){
		//一般key为主键 根据主键id判断是否存在
		//专门定义一个存放主键的list 根据这个list取得缓存
		List<String> keys=new ArrayList<String>();
		for(User u : users){
			keys.add(Long.toString(u.getId()));
		}
		//List<User> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("users", keys);
		List<User> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("users", null);
		//传入keys的大小必须与返回集合的size大小一致 否则将重新新建缓存
		if(get4HashFromRedis.size() != users.size() || get4HashFromRedis.size() == 0 ){
			return null;
		}

		return get4HashFromRedis;
	}

   @RequestMapping("/test3")
   public String redisTest2(){
	   List<User> users=new ArrayList<User>();
	   List<String> keys=new ArrayList<String>();
	   keys.add("1");
	   //List<User> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("users", keys);
	   List<User> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("users", keys);
	   if(get4HashFromRedis!=null)
	   System.out.println("大小为："+get4HashFromRedis.size());
	   return "ok";
   }
}
