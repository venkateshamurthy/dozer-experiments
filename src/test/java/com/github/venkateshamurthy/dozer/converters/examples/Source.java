package com.github.venkateshamurthy.dozer.converters.examples;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Source {
	String name;
	String address1;
	String address2;
	String city;
	String state;
	String zip;
	Phone phone;
}
