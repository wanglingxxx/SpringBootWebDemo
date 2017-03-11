package com.research.domain;

import java.io.Serializable;

public class User implements Serializable{
	
	 private Long id;
	
      private String name="liucaijin";
      
      public Integer age; 
      
      public User(int age) {
    	  this.age=age;
      }

      public User(long id, int age) {
    	  this.age=age;
    	  this.id=id;
      }
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
