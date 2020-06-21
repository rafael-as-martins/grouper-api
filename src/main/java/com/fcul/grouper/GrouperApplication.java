package com.fcul.grouper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fcul.grouper.config.StorageProperties;

@EnableWebMvc
@SpringBootApplication
@ComponentScan
@EnableConfigurationProperties(StorageProperties.class)
@EnableJpaRepositories("com.fcul.grouper.repository")
public class GrouperApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrouperApplication.class, args);
	}

}
