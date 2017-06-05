package com.gachaland.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Configuration
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.kakao.order.api", "com.kakao.order.core"})
public class GachaLandApplication {

	public static void main(String[] args) {
		SpringApplication.run(GachaLandApplication.class, args);
	}
}
