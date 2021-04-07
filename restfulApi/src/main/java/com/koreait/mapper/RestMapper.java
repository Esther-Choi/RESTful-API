package com.koreait.mapper;

import com.koreait.domain.UserVO;

public interface RestMapper {
	
	public int insert(UserVO user);
	
	public int login(UserVO user);
	
	public void editToken(UserVO user);
	
	public UserVO get(String token);
	
	public int checkId(String loginId);
	

}

