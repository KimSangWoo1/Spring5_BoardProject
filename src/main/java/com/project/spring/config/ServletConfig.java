package com.project.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.project.spring.board.interceptor.LoginInterceptor;

// spring 5.0에서는 WebMvcConfiurer 를 제공하지 않는다.
/*
 * 이것은 Java 8이 WebMvcConfigurerAdapater클래스 의 기능을 다루는 인터페이스에 기본 메소드를 도입했기 때문이다.
  만약 그래도 사용하고자 한다면 Spring 5부터는 WebMvcConfigurer 인터페이스를 구현할 필요가 있다.
 */
@EnableWebMvc  //스프링 MVC 활성화
@ComponentScan(basePackages= {"com.project.spring.board.controller"}) //.controller
public class ServletConfig implements WebMvcConfigurer{
	
	//뷰 리졸버 생성
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController("/").setViewName("forward:login");
		// registry.addViewController("/").setViewName("forward:/WEB-INF/views/jsp/login/login");

	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
	}
	
	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Override
	 public void addInterceptors(InterceptorRegistry registry) {
	     registry.addInterceptor(loginInterceptor)
         .addPathPatterns("/**/board/*.do");
	}
}
