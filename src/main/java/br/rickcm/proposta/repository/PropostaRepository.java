package br.rickcm.proposta.repository;

import br.rickcm.proposta.model.Proposta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);
}
