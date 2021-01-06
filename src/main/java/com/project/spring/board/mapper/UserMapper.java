package com.project.spring.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.project.spring.board.vo.UserVO;

@Mapper
public interface UserMapper {

	// 1.회원 아이디 비밀번호 체크
	//@Select("SELECT * FROM tbl_user WHERE userid = #{userid} AND userpw = #{userpw}")
	UserVO CheckUser(UserVO userVO) throws Exception;

	// 2. ȸ�� �ߺ� Ȯ��
	boolean DuplicateUser(String userid) throws Exception;

	// 3. ȸ�� ����
	int InsertUser(UserVO userVO) throws Exception;

	/*
	 * //4.�α׾ƿ� void Logout(HttpSession session);
	 */
}
