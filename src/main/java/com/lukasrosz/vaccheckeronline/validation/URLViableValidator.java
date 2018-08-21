package com.lukasrosz.vaccheckeronline.validation;

import java.io.IOException;
import java.net.URL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class URLViableValidator implements ConstraintValidator<URLViable, String>{

	
	@Override
	public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
		boolean result = true;

		if(url == null) {
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate("is required")
			.addConstraintViolation();
			return false;
		}
		
		try {
			
			URL oracle = new URL(url);

			//TODO: checking using STEAM API and Json format if steam profile is correct
			
//			String steamProfileData = "<link href=\"https://steamcommunity-a.akamaihd.net/public/css/skin_1/profilev2.css?v=OuFIw5ir5MeZ\" rel=\"stylesheet\" type=\"text/css\" >";
//						
//			if(!source.contains(steamProfileData)) {
//				System.out.println("NOT A CORRECT STEAM PROFILE");
//				constraintValidatorContext.disableDefaultConstraintViolation();
//				constraintValidatorContext.buildConstraintViolationWithTemplate("Not a correct Steam Profile")
//				.addConstraintViolation();
//				return false;
//			}
			
		} catch (IllegalArgumentException | IOException e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
}
