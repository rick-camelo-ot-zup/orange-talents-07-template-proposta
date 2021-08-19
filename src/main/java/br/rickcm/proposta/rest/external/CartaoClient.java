package br.rickcm.proposta.rest.external;

import br.rickcm.proposta.rest.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "cartoes", url = "${cartoes.url}")
public interface CartaoClient {

    @GetMapping("?idProposta={id}")
    RetornoCartao getCartao(@PathVariable("id") String idProposta);

    @PostMapping("/{id}/bloqueios")
    ResponseEntity<RetornoBloqueio> bloqueia(@PathVariable String id, @RequestBody Map<String, String> body);

    @PostMapping("/{id}/avisos")
    ResponseEntity<AvisoCartaoDto> avisa(@PathVariable String id, @RequestBody Map<String, String> body);

    @PostMapping("/{id}/carteiras")
    RetornoCarteira associaCarteira(@PathVariable String id, @RequestBody CarteiraDigitalRequest body);
}
