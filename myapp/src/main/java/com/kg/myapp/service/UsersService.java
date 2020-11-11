package com.kg.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kg.myapp.dao.IUsersRepository;
import com.kg.myapp.vo.UsersVO;

@Service
public class UsersService implements IUsersService {
	
	@Autowired
	IUsersRepository usersRepository;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	
	public void insertUsers(UsersVO users) {
		users.setPassword(bpe.encode(users.getPassword()));
		usersRepository.insertUsers(users);
		//bpe.matches(rawPassword, encodedPassword)
		//bpe.matches(입력한 비밀번호, 입력되어 있던 비밀번호) -> controller에서 비교 하는 게 좋다
		//혹은 서비스에서 체크해서 true,false로 controller로 보내도 된다
	}

	@Override
	public String getPassword(String user) {
		return usersRepository.getPassword(user);
	}

	@Override
	public UsersVO getUsers(String userid) {
		return usersRepository.getUsers(userid);
	}

	@Override
	public String idCheck(String userid) {
		return usersRepository.idCheck(userid);
	}

	
	

}
