package com.lukasrosz.vaccheckeronline.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.lukasrosz.vaccheckeronline.steamapiintegration.responder.SteamApiResponder;
import com.lukasrosz.vaccheckeronline.steamapiintegration.responder.SteamApiResponderImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
public class AppConfig {

	@Autowired
	private Environment env;
	
	@Value("${steamAPIKey}")
	private String steamAPIKey;

	@Bean
	public DataSource mainDataSource() {
		MysqlDataSource mainDataSource = new MysqlDataSource();
//		try {
//			mainDataSource.setDriverClass(env.getProperty("suspect-jdbc.driver"));
//		} catch (PropertyVetoException e) {
//			throw new RuntimeException(e);
//		}

		System.out.println(">>> vacchecker-jdbc.url=" + env.getProperty("vacchecker-jdbc.url"));
		System.out.println(">>> vacchecker-jdbc.user=" + env.getProperty("vacchecker-jdbc.user"));
		mainDataSource.setUrl(env.getProperty("vacchecker-jdbc.url"));
//		mainDataSource.setJdbcUrl(env.getProperty("vacchecker-jdbc.url"));
		mainDataSource.setUser(env.getProperty("vacchecker-jdbc.user"));
		mainDataSource.setPassword(env.getProperty("vacchecker-jdbc.password"));

//		mainDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize", env));
//		mainDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize", env));
//		mainDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize", env));
//		mainDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime", env));

		return mainDataSource;
	}

	@Bean
	@Autowired
	public LocalSessionFactoryBean mainDbSessionFactory(DataSource mainDataSource) {
		LocalSessionFactoryBean suspectsSessionFactory = new LocalSessionFactoryBean();
		suspectsSessionFactory.setDataSource(mainDataSource);
		suspectsSessionFactory.setPackagesToScan(new String[] { "com.lukasrosz.vaccheckeronline" });

		suspectsSessionFactory.setHibernateProperties(hibernateProperties(env));
		return suspectsSessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager mainDBTransactionManager(SessionFactory mainDbSessionFactory) {
		HibernateTransactionManager mainDBTransactionManager = new HibernateTransactionManager();

		mainDBTransactionManager.setSessionFactory(mainDbSessionFactory);
		return mainDBTransactionManager;
	}
	
	private int getIntProperty(String propName, Environment env) {
		String propVal = env.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
	
	private Properties hibernateProperties(Environment env) {
		return new Properties() {
			private static final long serialVersionUID = 1L;

			{
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			}
		};
	}
	
	@Bean
	public SteamApiResponder steamApiResponder() {
		SteamApiResponder steamAPIUrlMaker = new SteamApiResponderImpl(steamAPIKey);
		return steamAPIUrlMaker;
	}

}
