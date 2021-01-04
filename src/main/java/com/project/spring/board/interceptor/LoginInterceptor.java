package com.project.spring.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private String userid;
	//��
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//���� �޾ƿ���
		HttpSession session = request.getSession();
		//����� ���� �� ��������
		this.userid = (String) session.getAttribute("userid");
		//����� ���� ���� ���� --> �α��� ����
		if(userid==null ||userid.trim().isEmpty()) {	
			System.out.println("�� �α���");
			
			//�α��α⺻ �������� �̵�
			response.sendRedirect("/myapp/login.do");
			return false;
		}
		// �α����� �Ǿ�����
		//System.out.println("���� �α���");
		return true;
	}
	//��
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception{
		 super.postHandle(request, response, handler, mv);
		
	}
	//��
	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, handler, ex);
    }
	
	public String getUserid() {
		return userid;
	}
	
}

