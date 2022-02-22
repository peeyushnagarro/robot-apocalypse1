package com.robot.constraints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class GenderValidator implements ConstraintValidator<Gender, String>{
	
	@Value("${valid.gender.values}")
	private String validGenderValues;
	
	private List<String> validGenderValuesList;

	@Override
	public void initialize(Gender gender) {
		validGenderValuesList = Arrays.asList(validGenderValues.split(","));
	}

	@Override
	public boolean isValid(String valueToValidate, ConstraintValidatorContext context) {
		return validGenderValuesList.contains(valueToValidate.toLowerCase());
	}

}
