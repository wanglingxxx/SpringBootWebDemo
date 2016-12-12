package com.chen.model;

import java.io.Serializable;

/**
 * @日期 2016年10月31日下午9:39:39
 * @作者: 陈鹏
 * @邮箱: 625733407@qq.com
 * @version: 1.0
 * @概要:车辆基本信息表
 */
public class Car_CarInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8393863648906113181L;

	/**
	 * id 主键 自增长
	 */
	private int id;
	/**
	 * 用户id 外键 对应car_userinfo 表
	 */
	private int user_id;
	
	/**
	 * 汽车名称
	 */
	private String name;
	/**
	 * 车辆品牌
	 */
	private String brand;
	/**
	 * 车辆车系
	 */
	private String audi;
	/**
	 * 车辆价格
	 */
	private String price;
	/**
	 * 车辆原价
	 */
	private String cost_price;
	/**
	 * 里程数
	 */
	private String mileage;
	/**
	 * 购买时间
	 */
	private String buy_date;
	/**
	 * 车辆图片
	 */
	private String photo;
	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getAudi() {
		return audi;
	}
	public void setAudi(String audi) {
		this.audi = audi;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCost_price() {
		return cost_price;
	}
	public void setCost_price(String cost_price) {
		this.cost_price = cost_price;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Car_CarInfo() {
		super();
	}
	
	/*public Car_CarInfo(int id, int user_id, String name, String brand,
			String audi, String price, String cost_price, String mileage,
			String buy_date, String photo) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.brand = brand;
		this.audi = audi;
		this.price = price;
		this.cost_price = cost_price;
		this.mileage = mileage;
		this.buy_date = buy_date;
		this.photo = photo;
	}*/
	public Car_CarInfo(int user_id, String name, String brand, String audi,
					   String price, String cost_price, String mileage, String buy_date,
					   String photo) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.brand = brand;
		this.audi = audi;
		this.price = price;
		this.cost_price = cost_price;
		this.mileage = mileage;
		this.buy_date = buy_date;
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Car_CarInfo [user_id=" + user_id + ", name="
				+ name + ", brand=" + brand + ", audi=" + audi + ", price="
				+ price + ", cost_price=" + cost_price + ", mileage=" + mileage
				+ ", buy_date=" + buy_date + ", photo=" + photo + "]";
	}
	

	
}
