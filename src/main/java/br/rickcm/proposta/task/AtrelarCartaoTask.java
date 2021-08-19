package br.rickcm.proposta.task;

import br.rickcm.proposta.enums.StatusProposta;
import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.model.Proposta;
import br.rickcm.proposta.repository.PropostaRepository;
import br.rickcm.proposta.rest.dto.RetornoCartao;
import br.rickcm.proposta.rest.external.CartaoClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AtrelarCartaoTask {

    private final Logger logger = LoggerFactory.getLogger(AtrelarCartaoTask.class);

    private PropostaRepository repository;
    private CartaoClient cartaoClient;

    public AtrelarCartaoTask(PropostaRepository repository, CartaoClient cartaoClient) {
        this.repository = repository;
        this.cartaoClient = cartaoClient;
    }

    @Scheduled(fixedDelayString = "${periodicidade.atrela-cartao}")
    @Transactional
    protected void executaOperacao() {
        List<Proposta> propostasCartaoNull = repository.findByCartaoIsNullAndStatus(StatusProposta.ELEGIVEL);
        propostasCartaoNull.stream().forEach(proposta -> {
            try {
                RetornoCartao retornoCartao = cartaoClient.getCartao(String.valueOf(proposta.getId()));
                Cartao cartao = retornoCartao.toModel(proposta);
                proposta.atrelaCartao(cartao);
                logger.info("Cartão {} atrelado a proposta documento={}!", retornoCartao.getId(), proposta.getDocumento());
            }catch (FeignException e){
                logger.error("Falha ao atrelar cartão a proposta documento={}!", proposta.getDocumento());
            }
        });
        repository.saveAll(propostasCartaoNull);
    }

}
