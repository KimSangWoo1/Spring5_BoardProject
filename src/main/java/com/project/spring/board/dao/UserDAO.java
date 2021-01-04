package com.project.spring.board.dao;

import com.project.spring.board.vo.UserVO;

public interface UserDAO {

	//1.ȸ�� ���̵� ��й�ȣ ��ȸ
	UserVO CheckUser(UserVO userVO);

	//2. ȸ�� �ߺ� Ȯ��
	boolean DuplicateUser(String userid);
	
	//3. ȸ�� ����
	int InsertUser(UserVO userVO);

	/*
	//4.�α׾ƿ�
	void Logout(HttpSession session);
	 */
}
