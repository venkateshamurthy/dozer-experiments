package com.github.venkateshamurthy.dozer.converters.examples;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {
	int std;
	int phoneNumber;
}
