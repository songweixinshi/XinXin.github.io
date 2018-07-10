package com.xinxin.test.serverTest.entity;

/**
 * 测试实体，这里无论是否序列化，xfire都会对xfire序列化传输，且客户端实体必须和服务端实体包结构一致
 * @author Administrator
 *
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
