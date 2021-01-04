package com.project.spring.board.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface UserService {
	
	//1. ȸ�� ��ȸ
	boolean CheckUserService(Map<String, Object> map);	
	//2. ȸ�� �α���
	boolean LoginUserService(Map<String, Object> map, HttpSession session);
	
	//3 ȸ�� �ߺ� Ȯ��
	boolean DuplicateUserService(String userid);
	
	//4. ȸ�� ����
	boolean InsertUserService(Map<String, Object> map);
	
	//5. �α׾ƿ�
	void LogoutService(HttpSession session);
}
