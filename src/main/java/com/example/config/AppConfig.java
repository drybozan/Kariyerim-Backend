package com.example.config;


import com.example.dataAcces.CityDao;
import com.example.entities.concretes.*;
import com.example.entities.dtos.*;
import com.example.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource(value= "classpath:hibernate.properties", encoding = "UTF-8")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.example"})
public class AppConfig {
	private Environment env;

	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}

	@Primary
	@Bean
	public LocalSessionFactoryBean getSessionFactory(){
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		props.put(DRIVER, env.getProperty("mysql.driver"));
		props.put(URL, env.getProperty("mysql.url"));
		props.put(USER, env.getProperty("mysql.user"));
		props.put(PASS, env.getProperty("mysql.password"));

		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		props.put(DIALECT, env.getProperty("hibernate.dialect"));

		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		props.put(C3P0_CONFIG_PREFIX + ".initialPoolSize", env.getProperty("hibernate.c3p0.initialPoolSize"));
		factoryBean.setHibernateProperties(props);

		factoryBean.setAnnotatedClasses(Candidate.class, City.class, Cv.class, Employer.class,
				Experiance.class,JobAd.class,JobAdFavorites.class,
				JobPosition.class,Language.class,School.class,Technology.class,User.class,
				WorkPlace.class,WorkTime.class, CandidateForGetDto.class, CandidateForRegisterDto.class,
				CvForSetDto.class, EmployerForRegisterDto.class, ExperianceForSetDto.class,
				JobAdDto.class,JobAdFilter.class,LanguageForSetDto.class,SchoolForSerDto.class,
				TechnologyForSerDto.class,UserLoginDto.class,UserLoginReturnDto.class, CityService.class, CityDao.class);

		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager(){
		LocalSessionFactoryBean localSessionFactoryBean = getSessionFactory();
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(localSessionFactoryBean.getObject());
		return transactionManager;
	}
}
