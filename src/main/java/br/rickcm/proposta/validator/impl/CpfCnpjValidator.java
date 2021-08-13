package br.rickcm.proposta.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.rickcm.proposta.validator.CpfCnpj;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, CharSequence> {

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        CNPJValidator cnpjValidator = new CNPJValidator();
        CPFValidator cpfValidator = new CPFValidator();

        cnpjValidator.initialize(null);
        cpfValidator.initialize(null);

        return cnpjValidator.isValid(value, context) || cpfValidator.isValid(value, context);
    }

}
