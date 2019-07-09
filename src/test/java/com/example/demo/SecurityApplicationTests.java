package com.example.demo;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.redis.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	@Qualifier("template")
	private RedisTemplate<String,User> redisTemplate;
	@Test
	public void testStringRedisTemplate() throws Exception {
		stringRedisTemplate.opsForValue().set("aaaa", "111");
		Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaaa"));
	}
	
	@Test
	public void testRedisTemplate() throws Exception {
        User user = new User("csss", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("csss").getAge().longValue());
	}
}
