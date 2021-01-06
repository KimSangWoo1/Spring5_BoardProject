package com.project.spring.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.project.spring.board.vo.UserVO;

@Repository
@Mapper
public interface UserMapper {

	// 1.회원 아이디 비밀번호 체크
	//@Select("SELECT * FROM tbl_user WHERE userid = #{userid} AND userpw = #{userpw}")
	UserVO checkUser(UserVO userVO) throws Exception;

	// 2. 회원 중복 아이디 체크
	boolean duplicateUser(@Param("userid")String userid) throws Exception;

	// 3. 회원 가입
	int insertUser(UserVO userVO) throws Exception;

	/*
	 * //4.로그아웃
	 * void logpout(HttpSession session){}
	 */
}
