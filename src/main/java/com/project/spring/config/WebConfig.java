package com.project.spring.config;

import javax.servlet.Filter;

import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//AbstractAnnotationConfigDispatcherServletInitializer 는  Dispatcher Servlet 과 ContextLoaderListner 생성 아피치 톰캣 7, 서블릿 3.0 이상에서만 지원

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer  {
 
	// ContextLoaderListner 매핑
    @Override 
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    
    }
 
    //Dispatcher Servlet 매핑
    @Override
    protected Class<?>[] getServletConfigClasses() {
        
        return new Class[] {ServletConfig.class} ;
    }
 
    @Override
    protected String[] getServletMappings() {
        
        return new String[] {"/"};
    }
    
    // encodingFilter  안하면 한글 깨짐 jsp-> controller
    @Override
	protected Filter[] getServletFilters() {
    	CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
    	encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}

}

/* AbstractAnnotationConfigDispatcherServletInitializer
 * 
	서블릿 3.0 환경에서 컨테이너는 클래스 패스내의 javax.servlet.ServletContainerInitializer 인터페이스를 구현(implement)한 
	모든 클래스를 찾아보도록 되어있다.	여기서 발견된 클래스들은 서블릿 컨테이너들을 설정하는 데 사용된다.
	스프링은 SpringServletContainerInitializer의 구현을 제공하고 이것을 순차적으로 WebApplicationInitialize의 구현 클래스를 찾아 위임한다.
	 스프링 3.2+ 에서는 AbstractAnnotationConfigDispatcherServletInitializer라고 하는
	WebApplicationInitializer의 편리한 기본 구현이 제공된다.
	서블릿 3.0 컨테이너에 배포될 때 자동적으로 발견되어 서블릿 컨텍스트를 자동 초기화한다.
*/