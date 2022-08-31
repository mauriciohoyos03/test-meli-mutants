package com.meli.test.mutants.validations;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DnaValidation implements ConstraintValidator<DnaValId, String[]>{

    private String pattern;

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        for(String secuence: value) {
            if (Pattern.compile(this.pattern).matcher(secuence).find()) {
                return false;
            }
        }
        return true;
    }

    public void initialize(DnaValId constraint) {
        this.pattern = constraint.pattern();
    }
    
}
