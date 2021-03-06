package com.restaurant.fancy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.restaurant.fancy.controller.OrderController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceApplicationTests {
	
	@Autowired
	private OrderController orderController;


	@Test
	public void contextLoads() {
		assertThat(orderController).isNotNull();
	}

}
