package com.research.service.impl;

import com.research.mapper.Car_CarInfoMapper;
import com.research.model.Car_CarInfo;
import com.research.service.Car_CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adm on 2016/12/10.
 */
@Service
public class Car_CarInfoServiceImpl implements Car_CarInfoService {
    @Autowired
    Car_CarInfoMapper car_CarInfoMapper;
    public List<Car_CarInfo> queryAll() {

        return car_CarInfoMapper.queryAll();
    }

    public List<Car_CarInfo>  queryByObject(Car_CarInfo car_CarInfo) {
        /**
         * 条件查询 先判断redis key是否存在 如果存在 返回结果
         * 如果不存在 从数据库中查询 把返回结果放到redis key中
         * 然后返回结果
         *1 先返回单个对象试试
         *2 然后返回集合
         *      Redis Hash集合(特别适合于存储对象):
         *          HMSET keys value1 value2 value3 value4 value5
         *          HMGETALL keys
         *
         *      Redis List(按照插入顺序排序 同双向队列)
         *          lpush keys values(多个value 用空格隔开)   左插入
         *          rpush keys values   右插入
         *          lrange keys 0 10 (类似于limit 0 , 10)
         *
         *      Redis Set(通过哈希表实现的 添加 删除 查找的复杂度O(1))
         *          sadd keys values(如果存在keys相同 但类型不同时 需要del keys'删除原来的key')
         *          sadd runoob test1
         *          smembers keys(同一key对应的value是唯一的 set集合元素唯一)
         *
         *      zset(sorted set : 有序集合)
         *          zset和set string类型元素集合 不允许重复成员
         *          但是每个元素关联一个double类型的分数 redis根据分数升序排序
         *          zadd key score values value必须唯一
         *
         */
        return car_CarInfoMapper.queryByObject(car_CarInfo);
    }
}
