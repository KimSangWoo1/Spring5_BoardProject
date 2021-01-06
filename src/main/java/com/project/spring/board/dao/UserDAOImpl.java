package com.project.spring.board.dao;


import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.spring.board.vo.UserVO;



//@Repository()
public class UserDAOImpl {
/*

implements UserMapper{
	
	private final String nameSpace ="com.project.spring.board.dao.UserMapper";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//1. 로그인 user (id,pw) 확인
	@Override
	public UserVO CheckUser(UserVO userVO) {		
		return sqlSession.selectOne(nameSpace+"CheckUser", userVO);
	}
	
	//2. 유저 중복 확인
	@Override
	public boolean DuplicateUser(String userid) {
		String resultId =sqlSession.selectOne(nameSpace+"DuplicateUser", userid);
		if(resultId==null ||resultId.trim().isEmpty()) {
			return false;
		}else {
			System.out.println("�ߺ� ���̵� : "+ resultId);
			return true;
		}	
	}
	
	//3. 회원가입
	@Override
	public int InsertUser(UserVO userVO) {
		return sqlSession.insert(nameSpace+"InsertUser", userVO);
	}
	
	/*
	//4. 로그아웃
	@Override
	public void Logout(HttpSession session) {
		
	}
	*/

}
