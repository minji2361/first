package com.kg.myapp.dao;

import org.springframework.stereotype.Repository;

import com.kg.myapp.vo.UsersVO;

@Repository
public interface IUsersRepository {

	void insertUsers(UsersVO users);
	String getPassword(String userid);
	UsersVO getUsers(String userid);
	int idCheck(String userid);
	
}
