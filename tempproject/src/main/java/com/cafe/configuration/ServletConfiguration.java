package com.cafe.configuration;


import com.cafe.admin.beans.AdminConnection;

import com.cafe.admin.interceptor.AdminInterceptor;
import com.cafe.admin.mapper.CafeMapper;
import com.cafe.admin.mapper.UserMapper;
import com.cafe.mypage.mapper.MypageMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
@ComponentScan("com.cafe")
@PropertySource("/WEB-INF/properties/db.properties")
@PropertySource("/WEB-INF/properties/Admin.properties")
public class ServletConfiguration implements WebMvcConfigurer {

    @Value("${db.classname}")
    private String db_classname;
    @Value("${db.url}")
    private String db_url;
    @Value("${db.username}")
    private String db_username;
    @Value("${db.password}")
    private String db_password;

    @Value("${Admin_id}")
    private String admin_id;
    @Value("${Admin_pw}")
    private String admin_pw;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/resources/");
    }

    @Bean("adminLoginBean")
    @SessionScope
    public AdminConnection adminLoginBean(){
        return new AdminConnection();
    }


    @Resource(name = "adminLoginBean")
    private AdminConnection adminConnection;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor(adminConnection)).addPathPatterns("/admin/**").excludePathPatterns("/admin");
    }

    @Bean
    public BasicDataSource basicDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(db_classname);
        basicDataSource.setUrl(db_url);
        basicDataSource.setUsername(db_username);
        basicDataSource.setPassword(db_password);
        return basicDataSource;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
        return multipartResolver;
    }



    @Bean
    public SqlSessionFactory sqlSessionFactory(BasicDataSource basicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(basicDataSource);
        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
        return sqlSessionFactory;
    }

    @Bean
    public MapperFactoryBean<UserMapper> userMapperFactoryBean(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }
    @Bean
    public MapperFactoryBean<CafeMapper> cafeMapperFactoryBean(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<CafeMapper> factoryBean = new MapperFactoryBean<>(CafeMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }



    @Bean
    public MapperFactoryBean<MypageMapper> mypageMapperFactoryBean(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<MypageMapper> factoryBean = new MapperFactoryBean<>(MypageMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }



    @Bean(name="adminlogin")
    public AdminConnection adminConnection(){
        AdminConnection adminConnection = new AdminConnection();
        adminConnection.setAdmin_id(admin_id);
        adminConnection.setAdmin_pw(admin_pw);
        return adminConnection;
    }



}
