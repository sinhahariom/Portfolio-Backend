package com.example.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ResumeWebsiteApplication  {
	

	@Bean
	public  WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{	
			public void addCorsMapping(CorsRegistry registry)
			{
				registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*").allowCredentials(true);

			}
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(ResumeWebsiteApplication.class, args);
	}

	public void run(String... args) 
	{
		

	}


	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.resume"))
				.paths(PathSelectors.any())
				.build();
	}


}
