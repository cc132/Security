package com.example.demo;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.multidatasource.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
	
	@Autowired
	private UserService userService;
	@Test
	public void testMultiDataSource() throws Exception {
		com.example.demo.multidatasource.domain.User user = userService.findByName("cc2s11");
		System.out.println(user.toString());
	}
	
}
