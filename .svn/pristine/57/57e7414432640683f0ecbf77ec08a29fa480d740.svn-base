package com.project.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@Import({
	ContextDataSource.class,
	ContextSqlMapper.class
})
// basePacakges에 지정된 패키지를 검색해 @Component 붙은 빈들을 등록 시켜준다
// 계층형 구조로 만들기 위해 @Controller @RestController 어노테이션이 붙은 빈들은 빈 등록에서 제외 시켜준다.
@ComponentScan(basePackages = {"com.project.spring"})// ,excludeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION, value = Controller.class)})
public class RootConfig {

}


