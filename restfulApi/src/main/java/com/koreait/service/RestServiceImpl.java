package com.koreait.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.domain.UserVO;
import com.koreait.mapper.RestMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class RestServiceImpl implements RestService {
	
	@Setter(onMethod_ = @Autowired)
	private RestMapper mapper;
	private static final int KEY = 3;

	@Override
	public int join(UserVO user) {
		return mapper.insert(user);
	}

	@Override
	public int login(UserVO user) {
		return mapper.login(user);
	}
	
	@Override
	public void editToken(UserVO user) {
		mapper.editToken(user);
	}
	
	@Override
	public UserVO get(String token) {
		return mapper.get(token);
	}
	
	@Override
	public int checkId(String loginId) {
		
		return mapper.checkId(loginId);
	}
	
	@Override
	public String encrypt(String pw) {
		String en_pw = "";
		for (int i = 0; i < pw.length(); i++) {
			en_pw += (char) (pw.charAt(i) * KEY);
		}

		return en_pw;
	}
	
	@Override
	public String decrypt(String en_pw) {
		String de_pw = "";
		for (int i = 0; i < en_pw.length(); i++) {
			de_pw += (char) (en_pw.charAt(i) / KEY);
		}

		return de_pw;
	}

}
