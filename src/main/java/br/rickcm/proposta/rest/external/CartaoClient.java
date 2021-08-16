package br.rickcm.proposta.rest.external;

import br.rickcm.proposta.rest.dto.RetornoCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartoes", url = "${cartoes.url}")
public interface CartaoClient {

    @GetMapping("?idProposta={id}")
    ResponseEntity<RetornoCartao> getCartao(@PathVariable("id") String idProposta);
}
