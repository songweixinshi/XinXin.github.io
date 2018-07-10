package src;

import java.util.List;

import com.xinxin.test.serverTest.entity.User;



public interface MessageService {
	
	public String getName(String name);
	
	public String getJson();
	
	public List<User> getList();
	
	public User getOne();
}
