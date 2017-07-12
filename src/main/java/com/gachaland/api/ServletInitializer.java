package com.gachaland.api;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Properties;

@EnableAutoConfiguration
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GachaLandApplication.class).properties(getProperties());
	}

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.name", "application");
        return props;
    }

}
