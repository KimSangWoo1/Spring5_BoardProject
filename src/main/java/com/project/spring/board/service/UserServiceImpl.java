package com.project.spring.board.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring.board.mapper.UserMapper;
import com.project.spring.board.vo.UserVO;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	// 1. ȸ����ȸ Repository�� ������ ���� ( ���� ���� ��ȣȭ �� ������ �����Ұ� ������ �����ϰ� �־����) ex: ��ȣȭ
	@Override
	public boolean CheckUserService(Map<String, Object> map) throws Exception {
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
				result = true;
			}	
		}
		return result;
	}
	//2. ȸ�� �α���
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
	
	//3. ȸ�� �ߺ� Ȯ��
	@Override
	public boolean DuplicateUserService(String userid) throws Exception {		
		return  userMapper.duplicateUser(userid);
	}
	
	//4. ȸ������
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

	//5. �α׾ƿ�
	@Override
	public void LogoutService(HttpSession session) throws Exception {
		session.invalidate();			
	}
}
