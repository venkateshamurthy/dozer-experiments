package com.github.venkateshamurthy.dozer.converters.examples;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain=true)
@FieldDefaults(level=AccessLevel.PRIVATE)
public class TargetHolder {
	Target target;
	int age;
	double salary;
	long uan;
	String type;
	Date dob;
}
