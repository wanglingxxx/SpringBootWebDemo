package com.research.service;

import com.research.model.Car_CarInfo;

import java.util.List;

/**
 * @日期 2016年10月31日下午10:45:45
 * @作者: 陈鹏
 * @邮箱: 625733407@qq.com
 * @version: 1.0
 * @概要:
 */

public interface Car_CarInfoService {
	/**
	 * 查询前20条信息 limit 0,20
	 * @return
     */
	List<Car_CarInfo> queryAll();

	/**
	 * 根据传入条件 返回Model
	 * 后续会返回集合
	 * @param car_CarInfo
	 * @return
     */
	List<Car_CarInfo> queryByObject(Car_CarInfo car_CarInfo);
}
