package com.xinxin.test.serverTest;

import java.util.List;

import com.xinxin.test.serverTest.entity.User;

public interface MessageService {
	
	public String getName(String name);
	
	public String getJson();
	
	public User getOne();
	
	public List<User> getList();
}
