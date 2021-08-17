package br.rickcm.proposta.validator;

import br.rickcm.proposta.validator.impl.FormatoBase64Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {FormatoBase64Validator.class})
@Target({ FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface FormatoBase64 {

    String message() default "Não é um formato Base64 válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}