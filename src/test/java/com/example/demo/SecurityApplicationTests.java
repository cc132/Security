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
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
	}
	
	@Test
	public void testRedisTemplate() throws Exception {
        User user = new User("超人", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("cc", 40);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
        Assert.assertEquals(40, redisTemplate.opsForValue().get("cc").getAge().longValue());
	}
}
