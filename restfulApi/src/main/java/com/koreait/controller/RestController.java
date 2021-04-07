package com.koreait.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.domain.UserVO;
import com.koreait.service.RestService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
@Log4j
public class RestController {
	
	@Setter(onMethod_ = @Autowired)
	private RestService service;
	
	
	@PostMapping(value = "/user",consumes="application/json", 
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String, Boolean>> user(@RequestBody UserVO user) {
		
		//아이디 중복 검사 
		int checkCnt = service.checkId(user.getLoginId());
		
		Map<String, Boolean> result = new HashMap<String, Boolean>(); 
		
		if(checkCnt == 0) {
			//비밀번호 암호화 
			user.setPassword(service.encrypt(user.getPassword()));
			service.join(user);
			result.put("success", true);
		}else {
			result.put("success", false);
			
		}
		
		return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/login", consumes = "application/json", 
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserVO user){

		Map<String, Object> result = new HashMap<String, Object>();
		
		//토큰 생성
		String token = "123456";
		
		//비밀번호 암호화
		user.setPassword(service.encrypt(user.getPassword()));
		if(service.login(user) == 1) {
			
			user.setToken(token);
			service.editToken(user);
			result.put("success", true);
			result.put("token", token);
			
		}else {
			result.put("success", false);
		}
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user", 
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String, String>> get(@RequestParam("token") String token){
		
		Map<String, String> result = new HashMap<String, String>();
		UserVO user = service.get(token);
		
		result.put("loginId", user.getLoginId());
		result.put("nickname", user.getNickname());
		
		return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
	}

}













