package com.example.config;

import com.example.entities.concretes.*;
import com.example.entities.dtos.*;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;




@PropertySource(value="classpath:hibernate.properties",encoding = "UTF-8")
@EnableTransactionManagement
@Configuration
@EnableWebMvc
@ComponentScans(value = {@ComponentScan("com.example"),
						@ComponentScan("com/example/services/concretes"),
						@ComponentScan("com/example/dataAcces")})
public class AppConfig {
	@Autowired
	private Environment env;


	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}


	@Bean
	public HibernateTransactionManager getTransactionManager(){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}

	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean= new LocalSessionFactoryBean();
		//jdbc ayarları
		Properties prop= new Properties();
		prop.put(DRIVER,env.getProperties().getProperty("jdbc.driver"));
		prop.put(URL,env.getProperties().getProperty("jdbc.url"));
		prop.put(USER,env.getProperties().getProperty("jdbc.user"));
		prop.put(PASS,env.getProperties().getProperty("jdbc.password"));
		//hibernate ayarları
		prop.put(SHOW_SQL,env.getProperties().getProperty("hibernate.show_sql"));
		prop.put(HBM2DDL_AUTO,env.getProperties().getProperty("hibernate.hbm2ddl.auto"));
		prop.put(DIALECT,env.getProperties().getProperty("hibernate.dialect"));
		//c3p0 ayarları
		prop.put(C3P0_MIN_SIZE,env.getProperties().getProperty("hibernate.c3p0.min_size"));
		prop.put(C3P0_MAX_SIZE,env.getProperties().getProperty("hibernate.c3p0.max_size"));
		prop.put(C3P0_ACQUIRE_INCREMENT,env.getProperties().getProperty("hibernate.c3p0.acquire_increment"));
		prop.put(C3P0_TIMEOUT,env.getProperties().getProperty("hibernate.c3p0.timeout"));
		prop.put(C3P0_MAX_STATEMENTS,env.getProperties().getProperty("hibernate.c3p0.max_statements"));
		prop.put(C3P0_CONFIG_PREFIX + "initialPoolSize",env.getProperties().getProperty("hibernate.c3p0.initialPoolSize"));
		sessionFactoryBean.setHibernateProperties(prop);
		sessionFactoryBean.setAnnotatedClasses(Candidate.class, City.class, Cv.class, Employer.class,
				Experiance.class,JobAd.class,JobAdFavorites.class,
				JobPosition.class,Language.class,School.class,Technology.class,User.class,
				WorkPlace.class,WorkTime.class, CandidateForGetDto.class, CandidateForRegisterDto.class,
				CvForSetDto.class, EmployerForRegisterDto.class, ExperianceForSetDto.class,
				JobAdDto.class,JobAdFilter.class,LanguageForSetDto.class,SchoolForSerDto.class,
				TechnologyForSerDto.class,UserLoginDto.class,UserLoginReturnDto.class);
		return sessionFactoryBean;
	}

}

