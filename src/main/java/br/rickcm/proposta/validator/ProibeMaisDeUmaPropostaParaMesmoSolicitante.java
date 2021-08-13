package br.rickcm.proposta.validator;

import br.rickcm.proposta.error.ApiErrorException;
import br.rickcm.proposta.model.Proposta;
import br.rickcm.proposta.repository.PropostaRepository;
import br.rickcm.proposta.rest.dto.NovaPropostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeMaisDeUmaPropostaParaMesmoSolicitante implements Validator {
    @Autowired
    private PropostaRepository propostaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaPropostaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaPropostaRequest request = (NovaPropostaRequest) o;

        Optional<Proposta> possivelDocumento = propostaRepository.findByDocumento(request.getDocumento());
        if (possivelDocumento.isPresent()){
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Este solicitante já solicitou uma proposta.");
        }
    }
}
