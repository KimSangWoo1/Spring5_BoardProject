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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource(value = {"classpath:config/database.properties"}, ignoreResourceNotFound=true)
public class ContextDataSource {
	
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	Environment env;
	
	@Bean("dataSource")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driverClass"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.userName"));
		dataSource.setPassword(env.getProperty("db.password"));
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}
	
	/**
	 * 트랜잭션 매니저 등록
	 * dataSource 프로퍼티를 통해 전달받은 Connection으로 commit, rollback을 수행하면서 관리
	 * @return
	 */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
/*
 * Application -> JDBC Interface -> JDBC Implemnetations -> Persistence Layer
 * 	  DAO          JDBC Template        JDBC Driver 			Database
 					 ^-- DataSource
 */

/*
 * #DataSource
 * 1. BasicDataSource :
   2. PoolingDataSource :  제공된 ObjectPool을 사용하여 DataSource 인터페이스를 구현합니다. PoolingDatasource는 연결 (캐스팅, 유효성 검사, 속성 설정 등)과 관련된 모든 작업을 처리하고 ObjectPool은이 모든 유형의 개체를 유지하고 계산합니다.
   3. SingleConnectionDataSource : SmartDataSource사용 후 닫히지 않는 단일 JDBC 연결을 래핑합니다. 분명히 이것은 멀티 스레딩이 가능하지 않습니다. 종료시 누군가가 close()메서드 를 통해 기본 연결을 닫아야합니다 . 
   4. DriverManagerDataSource :실제 Connection pool이 아니고 모든 호출에 대해 새로운 연결을 생성해 본격적인 연결 풀의 간단한 대체 역할을합니다. 테스트 또는 독립형 환경에 유용
 */

/*
 * 	driverClassName = "org.mariadb.jdbc.Driver"
    회사
	url="jdbc:mariadb://121.169.194.198:3306/spring"	
	집
	url="jdbc:mariadb://localhost:3306/spring"
*/
