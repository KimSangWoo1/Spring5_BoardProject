package com.project.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@ComponentScan(basePackages = {"com.project.spring"})
public class RootConfig {

	//DB 연결 소스
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
	
    //SqlSessionFacotry 사용
	@Bean
	public SqlSessionFactory sqlSessionFactory()throws Exception{
		SqlSessionFactoryBean sqlSessionFactory=new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
}

/*
 * 	driverClassName = "org.mariadb.jdbc.Driver"
    회사
	url="jdbc:mariadb://121.169.194.198:3306/spring"	
	집
	url="jdbc:mariadb://localhost:3306/spring"
*/
