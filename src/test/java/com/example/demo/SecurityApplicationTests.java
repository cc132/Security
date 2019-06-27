package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.d1.entity.UserDO;
import com.example.demo.d1.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
	
	@Autowired
	private UserRepository user;
	
	@Test
	public void contextLoads() {
		UserDO u = user.findByUsername("黄春超");
		System.out.println(u.toString());
	}

}
