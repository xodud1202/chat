package com.xodud1202.chat.support.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Transaction Manager 설정
 * @author xodud1202
 * @since  2022.12.22
 */
@Configuration
@Lazy
@EnableTransactionManagement
public class TycJndiConfig {
	/**
	 * destroyMethod = "close" 속성은 apache commons DBCP와 BasicDataSource 등에서
	 * 즉시 DataSource를 종료하고자 할 때에 사용하는 것으로 DriverManagerDataSource에서는 필요하지 않다.
	 */
	@Bean(name = "xodudDataSource")
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		return this.getDataSource("jdbc/xodud");
	}

	@Bean(name = "xodudTxnManager")
	public PlatformTransactionManager transactionManager(@Qualifier("xodudDataSource") DataSource dataSource) throws NamingException {
		return this.getTransactionManager(dataSource);
	}

	private DataSource getDataSource(String jndiName) throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
        jndiBean.setJndiName(jndiName);
        jndiBean.setResourceRef(true);
        jndiBean.afterPropertiesSet();
        return (DataSource)jndiBean.getObject();
    }

    private PlatformTransactionManager getTransactionManager(DataSource dataSource) throws NamingException {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
}
