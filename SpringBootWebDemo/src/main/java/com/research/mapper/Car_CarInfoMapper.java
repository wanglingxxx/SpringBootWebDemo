package com.research.mapper;

import com.research.model.Car_CarInfo;

import java.util.List;

/**
 * Created by Adm on 2016/12/9.
 */
public interface Car_CarInfoMapper {
   /**
    *
    * @return
     */
   List<Car_CarInfo> queryAll();

   /**
    *
    * @param car_CarInfo
    * @return
     */
   List<Car_CarInfo> queryByObject(Car_CarInfo car_CarInfo);
}
