package com.wisesoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BigServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BigServerApplication.class, args);
	}

	/**
	 * 若打包成war包,则需要继承 SpringBootServletInitializer类,
	 * 覆盖其config(SpringApplicationBuilder)方法
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BigServerApplication.class);
	}

}
