package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

@Configuration
public class RedisConfig {
	@Autowired
	private RedisConnectionFactory factory;

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		ObjectMapper om = new ObjectMapper();
	    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	    om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, 
	        ObjectMapper.DefaultTyping.NON_FINAL,
	        JsonTypeInfo.As.PROPERTY);
	    
	    // 使用建構子傳入 ObjectMapper
	    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = 
	        new Jackson2JsonRedisSerializer<>(om, Object.class);

		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer()); // 指定Redis的Key序列化方式
		template.setValueSerializer(jackson2JsonRedisSerializer); // 指定Value的序列化方式
		template.setHashKeySerializer(jackson2JsonRedisSerializer); // 執行Hash的Key的序列化方式
		template.setHashValueSerializer(jackson2JsonRedisSerializer); // 指定Hash的Value的序列化方式
		template.setDefaultSerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForValue();
	}

}