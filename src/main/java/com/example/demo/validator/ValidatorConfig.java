package com.example.demo.validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {
	
	public ReloadableResourceBundleMessageSource getErrorMessage() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.addBasenames("classpath:conf/settings/validation");
		source.setUseCodeAsDefaultMessage(false);
		source.setDefaultEncoding("UTF-8");
		source.setCacheMillis(60);
		return source;
	}
	@Bean
	public LocalValidatorFactoryBean getLocalValidatorFactoryBean() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setProviderClass(HibernateValidator.class);
		factory.setValidationMessageSource(getErrorMessage());
		return factory;
	}
}
