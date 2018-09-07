package com.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {
		"com.api.activity"
})
public class ActivityApplication extends SpringBootServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ActivityApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ActivityApplication.class, args);
	}
}
