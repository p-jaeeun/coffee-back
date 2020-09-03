package com.example.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppConfiguration implements WebApplicationInitializer{
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext servletConfiguration = new AnnotationConfigWebApplicationContext();
		servletConfiguration.register(ServletConfiguration.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletConfiguration);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		AnnotationConfigWebApplicationContext rootConfiguration = new AnnotationConfigWebApplicationContext();
		rootConfiguration.register(RootConfiguration.class);
		ContextLoaderListener listener = new ContextLoaderListener(rootConfiguration);
		servletContext.addListener(listener);
		
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
		
	}

}
