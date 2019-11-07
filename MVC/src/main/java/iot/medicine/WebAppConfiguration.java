package iot.medicine;


import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("iot.medicine")
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@PropertySource("classpath:database.properties")
@PropertySource("classpath:hibernate.properties")
public class WebAppConfiguration {

    @Autowired
    Environment env;

    @Bean
    InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();

        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    @Bean
    CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver
                = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(2500000);
        return resolver;
    }

    @Bean
    BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(env.getProperty("connection.url"));
        dataSource.setUsername(env.getProperty("connection.username"));
        dataSource.setPassword(env.getProperty(("connection.password")));
        dataSource.setDriverClassName(env.getProperty(("connection.driver_class")));
        dataSource.setMaxTotal(Integer.parseInt(env.getProperty("connection.max_total")));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("connection.initialSize")));

        return dataSource;
    }

//    @Bean("dataSource_sugar")
//    BasicDataSource dataSource2(){
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setUrl(env.getProperty("connection.url.sugar"));
//        dataSource.setUsername(env.getProperty("connection.username"));
//        dataSource.setPassword(env.getProperty(("connection.password")));
//        dataSource.setDriverClassName(env.getProperty(("connection.driver_class")));
//        dataSource.setMaxTotal(Integer.parseInt(env.getProperty("connection.max_total")));
//        dataSource.setInitialSize(Integer.parseInt(env.getProperty("connection.initialSize")));
//
//        return dataSource;
//    }

    @Bean
    @Autowired
    LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(createHibernateProperties());
        sessionFactoryBean.setPackagesToScan("my.entity.mvc");
        return sessionFactoryBean;
    }

//    @Bean("sessionFactory_sugar")
//    @Autowired
//    @Qualifier("dataSource_sugar")
//    LocalSessionFactoryBean sessionFactory_sugar(DataSource dataSource){
//        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setHibernateProperties(createHibernateProperties());
//        sessionFactoryBean.setPackagesToScan("my.entity");
//        return sessionFactoryBean;
//    }

    private Properties createHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        properties.put("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        return properties;
    }

    @Bean
    @Autowired
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }

//    @Bean("transactionManager_sugar")
//    @Autowired
//    @Qualifier("sessionFactory_sugar")
//    HibernateTransactionManager transactionManager_sugar(SessionFactory sessionFactory){
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
//
//        return transactionManager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
