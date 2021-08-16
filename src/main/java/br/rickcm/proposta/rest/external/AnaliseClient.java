package br.rickcm.proposta.rest.external;

import br.rickcm.proposta.rest.dto.ResultadoAnalise;
import br.rickcm.proposta.rest.dto.SolicitacaoAnaliseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analises", url = "${analises.url}")
public interface AnaliseClient {

    @PostMapping
    ResponseEntity<ResultadoAnalise> getAnalise(@RequestBody SolicitacaoAnaliseRequest request);
}