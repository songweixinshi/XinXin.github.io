package com.xinxin.test.serverTest;

import java.util.ArrayList;
import java.util.List;

import org.mortbay.util.ajax.JSON;

import com.xinxin.test.serverTest.entity.User;

public class MessageServiceImpl implements MessageService{

	@Override
	public String getName(String name) {
		// TODO Auto-generated method stub
		System.out.println("服务端调用成功");
		return "hello "+name+",服务端调用成功";
	}

	@Override
	public String getJson() {
		// TODO Auto-generated method stub
		return JSON.toString(new User("aaaa","xinxin",26));
	}
	@Override
	public List<User> getList(){
		int baseAge = 10;
		List<User> users = new ArrayList<User>();
		for(int i=0;i<10;i++){
			users.add(new User(String.valueOf(i),"xinxin",baseAge+i));
		}
		System.out.println("服务端调用成功");
		for(User u:users){
			System.out.println(u.toString());
		}
		return users;
	}
	@Override
	public User getOne(){
		return new User("111","111",111);
	}
	
}
