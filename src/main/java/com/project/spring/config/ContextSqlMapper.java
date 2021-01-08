package com.project.spring.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
//Mybatis의 Mapper 인터페이스를 검색함
@MapperScan(basePackages = {"com.project.spring.board.mapper"})
public class ContextSqlMapper {
	
	@Autowired
	ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource((DataSource) applicationContext.getBean("dataSource"));
        Resource[] res=new PathMatchingResourcePatternResolver()
        		.getResources("classpath*:mybatis/mapper/*.xml");
        factoryBean.setMapperLocations(res);
        factoryBean.setTypeAliasesPackage("com.project.spring.board.vo"); //factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
        return factoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    // classpath: classes 폴더와 jar 폴더를 순환하면서 첫번째로 발견되는 파일을 가져옵니다. 즉즉, 단일 파일이 로딩되는 형태가 됩니다.
    // classpath*: 	classes 폴더와 jar 폴더를 순환하면서 conf 폴더의 spring-context.xml 파일을 모두 찾아냅니다.   즉, 복수개의 파일이 로딩되는 형태가 됩니다.

}
