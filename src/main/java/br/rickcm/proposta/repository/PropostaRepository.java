package br.rickcm.proposta.repository;

import br.rickcm.proposta.enums.StatusProposta;
import br.rickcm.proposta.model.Proposta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);
    List<Proposta> findByCartaoIsNullAndStatus(StatusProposta proposta);
}
