package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.multidatasource.domain.User;

@Configuration
public class RedisConfig {
	@Bean
	LettuceConnectionFactory lettuceConnectionFactory() {
		return new LettuceConnectionFactory();
	}
	@Bean
	public RedisTemplate<String, User1> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String,User1> template = new RedisTemplate<String, User1>();
		template.setConnectionFactory(lettuceConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	@Bean("userTemplate")
	public RedisTemplate<String,User> redisTemplateSourceUser(RedisConnectionFactory factory){
		RedisTemplate<String,User> template = new RedisTemplate<String, User>();
		template.setConnectionFactory(lettuceConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
}
