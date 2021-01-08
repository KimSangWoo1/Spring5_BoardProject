package com.project.spring.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.spring.board.common.Logging;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private String userid;
	//전
	@Logging
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//세션 가져오기
		HttpSession session = request.getSession();
		//세션 값에 id 저장
		this.userid = (String) session.getAttribute("userid");
		//세션 시간이 만료됐거나 비 로그인 이 경우
		if(userid==null ||userid.trim().isEmpty()) {	
			System.out.println("로그인 세션 만료/ 비로그인");
			
			//로그인 페이지로 이동시킨다.
			response.sendRedirect("/spring/login.do");
			return false;
		}
		//System.out.println("로그인 인터셉터");
		return true;
	}
	//후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception{
		 super.postHandle(request, response, handler, mv);
		
	}
	//결과
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

