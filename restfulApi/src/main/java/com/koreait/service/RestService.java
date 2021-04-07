package com.koreait.service;

import com.koreait.domain.UserVO;

public interface RestService {
	
	public int join(UserVO user);
	
	public int login(UserVO user);
	
	public void editToken(UserVO user);
	
	public UserVO get(String token);
	
	public int checkId(String loginId);
	
	public String encrypt(String loginId);
	
	public String decrypt(String pw);

}

