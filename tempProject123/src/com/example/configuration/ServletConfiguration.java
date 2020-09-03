package com.example.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.beans.AdminBean;
import com.example.interceptor.ExampleInterceptor;
import com.example.mapper.ParkingMapper;

@Configuration
@EnableWebMvc
@ComponentScan("com.example")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletConfiguration implements WebMvcConfigurer{
	
	@Value("${db.classname}")
	private String db_classname;
	@Value("${db.url}")
	private String db_url;
	@Value("${db.username}")
	private String db_username;
	@Value("${db.password}")
	private String db_password;

	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/views/" , ".jsp");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/resource/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ExampleInterceptor(new AdminBean())).addPathPatterns("/admin/**");
	}
	
	@Bean
	public BasicDataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(db_classname);
		basicDataSource.setUrl(db_url);
		basicDataSource.setUsername(db_username);
		basicDataSource.setPassword(db_password);
		return basicDataSource;
	}
	

	
	@Bean
	public SqlSessionFactory sqlSessionFactory(BasicDataSource basicDataSource) throws Exception {
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(basicDataSource);
		SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
		return sqlSessionFactory;
		
	}
	@Bean
	public MapperFactoryBean<ParkingMapper> mapperFactoryBean(SqlSessionFactory sqlSessionFactory) {
		MapperFactoryBean<ParkingMapper> factoryBean = new MapperFactoryBean<>(ParkingMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactory);
		return factoryBean;
	}
	
	@Bean
	@SessionScope   //세션이 움직이는 만큼 살아서 움직인다.
	public AdminBean adminBean() {
		return new AdminBean();
	}
	
	
}
