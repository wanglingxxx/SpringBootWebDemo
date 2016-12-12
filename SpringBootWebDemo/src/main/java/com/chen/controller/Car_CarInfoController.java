package com.chen.controller;

import com.chen.cache.CacheUtil4Hash;
import com.chen.cache.ICacheKey;
import com.chen.domain.User;
import com.chen.model.Car_CarInfo;
import com.chen.service.Car_CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adm on 2016/12/11.
 */
@Controller
@RequestMapping("/car_carinfo")
public class Car_CarInfoController {
    @Autowired
    Car_CarInfoService car_CarInfoService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Object welcome() throws Exception {
        /**
         * 从缓存中取出结果集 首先判断是否存在
         */
        List<Car_CarInfo> allList = null;

        if((allList = isExistsForAll()) != null){
            //System.out.println(users);
            System.out.println("从缓存中取得数据!! "+allList.size());
            return allList;
        }

        List<Car_CarInfo> car_CarInfo = car_CarInfoService.queryAll();
        CacheUtil4Hash.cache4Hash(car_CarInfo, "car_carinfo", new ICacheKey<Car_CarInfo>() {

            public String getCacheKey(Car_CarInfo t) {
                System.out.println("已存到Redis key: " + t.getId() + "  value: " + t);
                return t.getId()+"";
            }
        });

        return car_CarInfo;
    }

    @RequestMapping("/queryByObject")
    @ResponseBody
    public Object queryByObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /**
         * 从缓存中取出结果集 首先判断是否存在
         */
        List<Car_CarInfo> allList = null;
        Car_CarInfo car_CarInfo = new Car_CarInfo();
        if(!"".equals(request.getParameter("user_id"))){
            car_CarInfo.setUser_id(Integer.valueOf(request.getParameter("user_id")));
        }
        if(!"".equals(request.getParameter("name"))){
            car_CarInfo.setName(request.getParameter("name"));
        }
        if(!"".equals(request.getParameter("brand"))){
            car_CarInfo.setBrand(request.getParameter("brand"));
        }
        if(!"".equals(request.getParameter("audi"))){
            car_CarInfo.setAudi(request.getParameter("audi"));
        }

        System.out.println("测试POJO  : " + car_CarInfo);

        if((allList = isExistsByObject(car_CarInfo)) != null){
            //System.out.println(users);
            System.out.println("从缓存中取得数据!! "+allList.size());
            return allList;
        }

        List<Car_CarInfo> car_carInfo = car_CarInfoService.queryByObject(car_CarInfo);
        System.out.println("即将存到缓存中的数据 :" +car_carInfo);
        CacheUtil4Hash.cache4Hash(car_carInfo, car_CarInfo.toString(), new ICacheKey<Car_CarInfo>() {

            public String getCacheKey(Car_CarInfo t) {
                System.out.println("已存到Redis key: " + t.getId() + "  value: " + t);
                return t.getId()+"";
            }
        });

        return car_carInfo;
    }


    private static List<Car_CarInfo> isExistsForAll(){
        //一般key为主键 根据主键id判断是否存在
        //专门定义一个存放主键的list 根据这个list取得缓存
        /*List<String> keys=new ArrayList<String>();
        if(car_carinfo != null){
            for(Car_CarInfo u : car_carinfo){
                keys.add(Long.toString(u.getId()));
            }
        }else{
            keys = null;
        }*/
        //List<User> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("users", keys);
        List<Car_CarInfo> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("car_carinfo", null);
        //传入keys的大小必须与返回集合的size大小一致 否则将重新新建缓存
        if(get4HashFromRedis.size() == 0 ){
            return null;
        }

        return get4HashFromRedis;
    }

    /**
     *
     * 传入一个T 判断满足该T的所有数据
     * 返回  List<T>
     * @param car_CarInfo
     * @return
     */
    private static List<Car_CarInfo> isExistsByObject(Car_CarInfo car_CarInfo){
        //一般key为主键 根据主键id判断是否存在
        //专门定义一个存放主键的list 根据这个list取得缓存
        /*List<String> keys=new ArrayList<String>();
        if(car_CarInfo != null){
            //将car_CarInfo.toString() 作为 keys  保证 只有满足条件下keys值才相同
            keys.add(car_CarInfo.toString());
        }else{
            keys = null;
        }*/
        //List<User> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis("users", null);
        List<Car_CarInfo> get4HashFromRedis = CacheUtil4Hash.get4HashFromRedis(car_CarInfo.toString(), null);
        //传入keys的大小必须与返回集合的size大小一致 否则将重新新建缓存
        if(get4HashFromRedis.size() == 0 ){
            return null;
        }

        return get4HashFromRedis;
    }

}
