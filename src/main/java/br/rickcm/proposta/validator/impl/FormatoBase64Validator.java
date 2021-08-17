package br.rickcm.proposta.validator.impl;

import br.rickcm.proposta.validator.FormatoBase64;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FormatoBase64Validator implements ConstraintValidator<FormatoBase64, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return true;
        }
        return Base64.isBase64(s.getBytes());
    }
}
