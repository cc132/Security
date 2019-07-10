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

import com.example.demo.multidatasource.service.UserService;
import com.example.demo.mybatis.domain.City;
import com.example.demo.mybatis.service.CityService;
import com.example.demo.redis.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	@Qualifier("template")
	private RedisTemplate<String,User> template;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private UserService userService;
	@Test
	public void testInternalRedisTemplate() throws Exception {
		redisTemplate.opsForValue().set("s黄", new User("你好", 2012));
		//System.out.println(redisTemplate.opsForValue().get("s黄"));
	}
	@Test
	public void testStringRedisTemplate() throws Exception {
		stringRedisTemplate.opsForValue().set("aaaa", "111");
		Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaaa"));
	}
	
	@Test
	public void testRedisTemplate() throws Exception {
        User user = new User("你好", 20);
        template.opsForValue().set(user.getUsername(), user);

        Assert.assertEquals(20, template.opsForValue().get("csss").getAge().longValue());
	}
	
	@Test
	public void testCityService() throws Exception {
		City city = cityService.findCityByName("盐城市");
		System.out.println(city);
	}
	
	@Test
	public void testMultiDataSource() throws Exception {
		com.example.demo.multidatasource.domain.User user = userService.findByName("黄春超");
		System.out.println(user.toString());
	}
}
