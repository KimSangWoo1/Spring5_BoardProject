package com.project.spring.board.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface UserService {
	

	//1. 로그인 ID/PW 확인 및 세션 값 저장
	boolean LoginUserService(Map<String, Object> map, HttpSession session) throws Exception;
	
	//2. 중복 유저 확인
	boolean DuplicateUserService(String userid) throws Exception;
	
	//3. 회원가입
	boolean InsertUserService(Map<String, Object> map) throws Exception;
	
	//4. 로그아웃
	void LogoutService(HttpSession session) throws Exception;
}
