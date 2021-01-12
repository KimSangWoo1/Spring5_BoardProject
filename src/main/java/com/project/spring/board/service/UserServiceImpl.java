package com.project.spring.board.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.spring.board.mapper.UserMapper;
import com.project.spring.board.vo.UserVO;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	//1. 로그인 ID/PW 확인 및 세션 값 저장
	@Override
	public boolean LoginUserService(Map<String, Object> map, HttpSession session) throws Exception {
		// �����
		boolean result = false;

		UserVO userVO = new UserVO();
		String userid = map.get("id").toString();
		String userpw = map.get("pw").toString();
		userVO.setUserid(userid);
		userVO.setUserpw(userpw);

		userVO = userMapper.checkUser(userVO);
		if(userVO!=null) {
			int no = userVO.getUserno();
			if (no != 0) {
				session.setAttribute("userid", userVO.getUserid());
				result = true;
			}	
		}
		return result;
	}
	
	//2. 중복 유저 확인
	@Override
	public boolean DuplicateUserService(String userid) throws Exception {		
		return  userMapper.duplicateUser(userid);
	}
	
	//3. 회원가입
	@Transactional
	@Override
	public boolean InsertUserService(Map<String, Object> map) throws Exception {
		boolean result = false;
		
		UserVO userVO = new UserVO();
		String userid = map.get("id").toString();
		String userpw = map.get("pw").toString();
		userVO.setUserid(userid);
		userVO.setUserpw(userpw);
		
		int insertResult = userMapper.insertUser(userVO);
		
		if(insertResult>0) {
			result= true;
		}else {
			result = false;
		}
		return result;
	}

	//4. 로그아웃
	@Override
	public void LogoutService(HttpSession session) throws Exception {
		session.invalidate();			
	}
}
