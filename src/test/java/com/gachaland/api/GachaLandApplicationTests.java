package com.gachaland.api;

import org.junit.Test;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@SpringBootTest(classes = GachaLandApplication.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
public class GachaLandApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("TEST");
	}

}
