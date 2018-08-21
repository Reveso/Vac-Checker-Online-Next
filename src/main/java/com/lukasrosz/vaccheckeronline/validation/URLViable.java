package com.lukasrosz.vaccheckeronline.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=URLViableValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface URLViable {
	
	//define default error message
	public String message() default "Could not connect to given URL";
		
	//define default groups
	public Class<?>[] groups() default {};
		
	//define default payloads
	public Class<? extends Payload>[] payload() default {};
}
