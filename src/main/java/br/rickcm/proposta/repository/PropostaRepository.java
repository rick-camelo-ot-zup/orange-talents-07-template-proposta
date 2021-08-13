package br.rickcm.proposta.repository;

import br.rickcm.proposta.model.Proposta;
import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {
}
