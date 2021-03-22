package com.project.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;

import com.project.app.repository.DefectRepository;
@ComponentScan(basePackages = {"com.project.app"})
@SpringBootApplication
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = DefectRepository.class)
public class ProjectApplication {
	@Value("${cloudinary.cloud_name}")
	private String cloudName;

	@Value("${cloudinary.api_key}")
	private String apiKey;

	@Value("${cloudinary.api_secret}")
	private String apiSecret;

	public static void main(String[] args) {
		 
	      
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
	    return new ValidatingMongoEventListener(validator());
	}
	@Bean
	public Cloudinary cloudinaryConfig() {
		Cloudinary cloudinary = null;
		Map<String, String> config = new HashMap<String, String>();
		config.put("cloud_name", cloudName);
		config.put("api_key", apiKey);
		config.put("api_secret", apiSecret);
		cloudinary = new Cloudinary(config);
		return cloudinary;
	}
	@Bean
	public LocalValidatorFactoryBean validator() {
	    return new LocalValidatorFactoryBean();
	}
	
}
