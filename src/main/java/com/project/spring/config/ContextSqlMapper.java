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
	@Autowired
	ContextDataSource datasource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(datasource.dataSource());
        Resource[] res=new PathMatchingResourcePatternResolver()
        		.getResources("classpath*:mybatis/mapper/*.xml");
        factoryBean.setMapperLocations(res);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
        //factoryBean.setTypeAliasesPackage("com.project.spring.board.vo");
        return factoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
}
