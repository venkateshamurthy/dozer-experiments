package com.github.venkateshamurthy.dozer.converters.examples;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Target {
	String name;
	String firstName;
	String lastName;
	String street1;
	String street2;
	String city;
	String province;
	String pin;
	Phone phone;
}
