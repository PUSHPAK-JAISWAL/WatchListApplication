package com.Watchlist.application.entity.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityAnnotationLogic implements ConstraintValidator<Priority, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value.trim().length() ==1 && "LHMlhm".contains(value.trim());
	}

}
