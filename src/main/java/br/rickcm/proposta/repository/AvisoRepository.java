package br.rickcm.proposta.repository;

import br.rickcm.proposta.model.AvisoCartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoRepository extends CrudRepository<AvisoCartao, Long> {
}
