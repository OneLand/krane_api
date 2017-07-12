package com.gachaland.api;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

@SpringBootApplication
@EnableSwagger2
@Configuration
@EnableAutoConfiguration
public class GachaLandApplication {

	public static void main(String[] args) {
//		SpringApplication.run(GachaLandApplication.class, args);
		new SpringApplicationBuilder(GachaLandApplication.class)
				.properties(getProperties())
				.build()
				.run(args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(GachaLandApplication.class).properties(getProperties());
//	}

	private static Properties getProperties() {
		Properties props = new Properties();
		props.put("spring.config.name", "application");
		return props;
	}
}
