package com.project.spring.board.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//예외처리 @Controller에서 발생하는 예외를 모두 잡아줌
@ControllerAdvice
public class CommonExceptionAdvice {

	private final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	//예외 종류 핸들러
	@ExceptionHandler({Exception.class, NullPointerException.class})
	public ModelAndView commonException(Exception e) {
	
		logger.info("exception 핸들러 : "+e.toString());
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception",e);
		mv.setViewName("/commons/common_error");
		return mv;
	}
}
