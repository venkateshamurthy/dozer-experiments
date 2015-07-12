package com.github.venkateshamurthy.dozer.converters;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Data
@FieldDefaults(level=AccessLevel.PRIVATE,makeFinal=true)
class Utils {
	@Getter
	static ApplicationContext applicationContext= new ClassPathXmlApplicationContext("classpath:beans.xml");
}
