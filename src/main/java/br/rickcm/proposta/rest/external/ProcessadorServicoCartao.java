package br.rickcm.proposta.rest.external;

import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.model.RequisicaoBloqueioCartao;
import br.rickcm.proposta.rest.dto.RetornoBloqueio;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProcessadorServicoCartao {

    private final Logger logger = LoggerFactory.getLogger(ProcessadorServicoCartao.class);
    private final CartaoClient cartaoClient;

    public ProcessadorServicoCartao(CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }

    public void bloqueiaCartao(Cartao cartao, RequisicaoBloqueioCartao requisicaoBloqueioCartao) {
        try {
            Map<String, String> apiRequest = new HashMap<>();
            apiRequest.put("sistemaResponsavel", "propostas");

            ResponseEntity<RetornoBloqueio> bloqueioLegado = cartaoClient.bloqueia(cartao.getId(), apiRequest);

            if(bloqueioLegado.getStatusCode().equals(HttpStatus.OK)){
                cartao.bloqueia();
                logger.info("Bloqueio realizado para o cartao={}", cartao.getId());
            }
        } catch (FeignException e) {
            if (e.status() == HttpStatus.BAD_REQUEST.value()){
                logger.error("HttpStatus 400 ao tentar notificar o sistema logado sobre a requisicao de bloqueio={}",
                        requisicaoBloqueioCartao.getId());
            }
            logger.error("Falha ao tentar notificar o sistema logado sobre a requisicao de bloqueio={}",
                    requisicaoBloqueioCartao.getId());
        }
    }

}