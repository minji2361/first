package com.kg.myapp.service;

import com.kg.myapp.vo.UsersVO;

public interface IUsersService {
	
	public void insertUsers(UsersVO users);
	String getPassword(String userid);
	UsersVO getUsers(String userid);
	String idCheck(String userid);

}
