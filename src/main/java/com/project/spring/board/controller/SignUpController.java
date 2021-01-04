package com.project.spring.board.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.board.service.UserService;



@Controller
public class SignUpController {
	Logger log = Logger.getLogger(this.getClass().toGenericString());

	@Autowired
	private UserService userService;

	// 1. ȸ������ �������� �̵�
	@RequestMapping("/signup.do")
	public ModelAndView SignUpForm() throws Exception {
		ModelAndView mv = new ModelAndView("/jsp/login/signup");
		return mv;
	}

	// 2. ȸ������ ����
	@RequestMapping(value = "/signUpChk.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> SignUpChk(HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<String, String> result = new HashMap<String, String>();

		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");

		String Msg;
		String Code;

		if (id == null || id.trim().isEmpty()) {
			log.info("���̵� �� �Է� ");
			Msg = "���̵� �Է��ϼ���";
			Code = "1";
		} else if (pw == null || pw.trim().isEmpty()) {
			log.info("��й�ȣ �� �Է�  ");
			Msg = "��й�ȣ�� �Է��ϼ���";
			Code = "1";
		} else {
			map.put("id", id);
			map.put("pw", pw);

			// 1. �ߺ� Ȯ��
			boolean userCheck = userService.DuplicateUserService(id);
			if (userCheck) {
				log.info("ȸ�� �ߺ�");
				Msg = "ȸ���ߺ�";
				Code = "1";
			} else {
				// 2. ȸ������ ó�� ����
				boolean insertCheck = userService.InsertUserService(map);

				if (insertCheck) {
					log.info("ȸ�� ���� ����");
					Msg = "ȸ������ ����";
					Code = "0";
				} else {
					log.info("ȸ������ ���� ");
					Msg = "����";
					Code = "1";
				}
			}
		}

		result.put("Msg", Msg);
		result.put("Code", Code);

		return result;
	}

}
