package com.project.spring.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 데이터베이스 설정
 */
@Configuration
// 아노 테이션 기반 트랜잭션 관리를 사용  <tx:annotation-driven>
@EnableTransactionManagement
public class ContextDataSource {
	
	@Autowired
	ApplicationContext applicationContext;
	/**
	 * 데이터소스 등록
	 * @return
	 */
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mariadb://121.169.194.198:3306/spring");
		dataSource.setUsername("planty");
		dataSource.setPassword("Planty2020");
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}
	
	/**
	 * 트랜잭션 매니저 등록
	 * @return
	 */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}

/*
 * 	driverClassName = "org.mariadb.jdbc.Driver"
    회사
	url="jdbc:mariadb://121.169.194.198:3306/spring"	
	집
	url="jdbc:mariadb://localhost:3306/spring"
*/
