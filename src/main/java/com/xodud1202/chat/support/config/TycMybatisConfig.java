package com.xodud1202.chat.support.config;

import com.xodud1202.chat.support.annotaion.xodudDs;
import com.xodud1202.chat.support.env.TycConstants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * xodudDs용 Mybatis Configuration
 * @author xodud1202
 * @since  2022.12.22
 */
@Configuration
@MapperScan(basePackages = TycConstants.BASE_PACKAGE, annotationClass = xodudDs.class, sqlSessionFactoryRef = "xodudSqlSessionFactory")
public class TycMybatisConfig {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean(name = "xodudSqlSessionFactory")
	public SqlSessionFactory shopSqlSessionFactory(@Qualifier("xodudDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeAliasesPackage(TycConstants.DOMAIN_PACKAGE);
		sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:persistence/mybatis-shop-config.xml"));
		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/*.xml"));

		return sessionFactoryBean.getObject();
	}

	@Bean(name = "xodudSqlSessionTemplate")
	public SqlSessionTemplate shopSqlSessionTemplate(@Qualifier("xodudSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
