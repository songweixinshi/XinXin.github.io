package com.xinxin.test.serverTest.entity;

/**
 * 测试实体
 * @author Administrator
 * 注意：针对目前常用jdk版本，传递对象和list集合时不用配置aegis.xml文件
 * 客户端实体类目录必须和服务端实体类目录一致，否则序列化将出错，对象匹配不到属性，全是null或0
 */
public class User{
	private String id;
	private String name;
	private int age;
	public User() {
	}
	
	public User(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
