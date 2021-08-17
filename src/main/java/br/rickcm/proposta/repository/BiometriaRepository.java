package br.rickcm.proposta.repository;

import br.rickcm.proposta.model.Biometria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends CrudRepository<Biometria, Long> {
}
