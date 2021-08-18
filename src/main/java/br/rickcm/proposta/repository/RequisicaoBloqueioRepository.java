package br.rickcm.proposta.repository;

import br.rickcm.proposta.model.RequisicaoBloqueioCartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoBloqueioRepository extends CrudRepository<RequisicaoBloqueioCartao, Long> {
}
