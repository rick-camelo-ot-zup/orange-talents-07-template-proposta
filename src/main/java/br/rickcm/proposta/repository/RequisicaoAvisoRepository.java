package br.rickcm.proposta.repository;

import br.rickcm.proposta.model.RequisicaoAvisoCartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoAvisoRepository extends CrudRepository<RequisicaoAvisoCartao, Long> {
}
