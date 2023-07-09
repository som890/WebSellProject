package com.web.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.web.dao.UserInfoDAO;


@Configuration
@ComponentScan("com.web.*")
@EnableTransactionManagement
@PropertySource("classpath:datasource-cfg.properties")
public class ApplicationContextConfig {
 

   // Lưu trữ các giá thuộc tính load bởi @PropertySource.
   @Autowired
   private Environment env;

   @Autowired
   private UserInfoDAO userInfoDAO;

   @Bean
   public ResourceBundleMessageSource messageSource() {
       ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
       // Load property in message/validation.properties
       rb.setBasenames(new String[] { "validation" });
       return rb;
   }
   
   @Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}

   @Bean(name = "viewResolver")
   public InternalResourceViewResolver getViewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setSuffix(".jsp");
       return viewResolver;
   }

   @Bean(name = "dataSource")
   public DataSource getDataSource() {
       BasicDataSource dataSource = new BasicDataSource();
 
       // Xem: datasouce-cfg.properties
       dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
       dataSource.setUrl(env.getProperty("ds.url"));
       dataSource.setUsername(env.getProperty("ds.username"));
       dataSource.setPassword(env.getProperty("ds.password"));

       System.out.println("## getDataSource: " + dataSource);

       return dataSource;
   }

   @Autowired
   @Bean(name = "sessionFactory")
   public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
       System.out.println("## getSessionFactory .... ");
       try {
           Properties properties = new Properties();
 
           // Xem: ds-hibernate-cfg.properties
           properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
           properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
           properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

           LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

           // Package contain entity classes
           // Package chứa các entity class.
           factoryBean.setPackagesToScan(new String[] { "com.web.entity" });
           factoryBean.setDataSource(dataSource);
           factoryBean.setHibernateProperties(properties);
           factoryBean.afterPropertiesSet();
           //
           SessionFactory sf = factoryBean.getObject();
           System.out.println("## getSessionFactory: " + sf);
           return sf;
       } catch (Exception e) {
           System.out.println("Error getSessionFactory: " + e);
           e.printStackTrace();
           throw e;
       }

   }

   // Hibernate Transaction Manager
   @Autowired
   @Bean(name = "transactionManager")
   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
       HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

       return transactionManager;
   }
}
