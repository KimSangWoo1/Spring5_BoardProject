package com.project.spring.board.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.board.service.UserService;


@Controller
public class LoginController {

	Logger log = Logger.getLogger(this.getClass().toGenericString());

	@Autowired
	private UserService userService;

	//0. �α��� ������ ȭ��
	@RequestMapping("/login.do")
	public ModelAndView LogingForm () {
		ModelAndView mv = new ModelAndView("/jsp/login/login");
		return mv;
	}
	
	// 1.�α��� �õ� ajax
	@RequestMapping(value = "/loginChk.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> LoginChk(HttpServletRequest request) {
		HashMap<String, String> result = new HashMap<String, String>();
		Map<String, Object> map = new HashMap<String, Object>();

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		map.put("id", id);
		map.put("pw", pw);
		// getSession ������ ���� �ϴµ� ���� ������ ������ ������ �ִٸ� ���� Session�� ��ȯ
		HttpSession session = request.getSession();
		boolean check = userService.LoginUserService(map, session);

		if (check) {
			String Msg = "�α��� ����";
			String Code = "0";
			session.setAttribute("userid", id);
			result.put("Msg", Msg);
			result.put("Code", Code);
			log.info("�α��� ����");
		} else {
			String Msg = "���̵�� ��й�ȣ�� Ȯ���ϼ���.";
			String Code = "1";

			result.put("Msg", Msg);
			result.put("Code", Code);
		}
		return result;
	}

	// 2.�α׾ƿ�

	@RequestMapping("/logout.do")
	public ModelAndView Logout(HttpSession session) throws Exception {
		
		userService.LogoutService(session);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
}
