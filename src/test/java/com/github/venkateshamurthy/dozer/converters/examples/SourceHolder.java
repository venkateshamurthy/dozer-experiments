package com.github.venkateshamurthy.dozer.converters.examples;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain=true)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class SourceHolder {
	Source source;
	Integer age;
	Double salary;
	Long uan;
	String type;
	Date dob;
}
