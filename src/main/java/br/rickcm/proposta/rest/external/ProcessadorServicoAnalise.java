package br.rickcm.proposta.rest.external;

import br.rickcm.proposta.enums.StatusProposta;
import br.rickcm.proposta.model.Proposta;
import br.rickcm.proposta.rest.controller.PropostaController;
import br.rickcm.proposta.rest.dto.ResultadoAnalise;
import br.rickcm.proposta.rest.dto.SolicitacaoAnaliseRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProcessadorServicoAnalise {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
    private final AnaliseClient analiseClient;

    public ProcessadorServicoAnalise(AnaliseClient analiseClient) {
        this.analiseClient = analiseClient;
    }

    public void realizaAnalise(Proposta novaProposta) {
        SolicitacaoAnaliseRequest solicitacaoAnaliseRequest = new SolicitacaoAnaliseRequest(novaProposta);
        try {
            ResponseEntity<ResultadoAnalise> resultadoAnalise = analiseClient.getAnalise(solicitacaoAnaliseRequest);
            ResultadoAnalise analise = resultadoAnalise.getBody();
            novaProposta.atualizaStatus(analise);
            logger.info("Solicitante da proposta={} sem restricao!", novaProposta.getId());

        } catch (FeignException.FeignClientException e) {
            if (e.status() == 422) {
                novaProposta.atualizaStatus(StatusProposta.NAO_ELEGIVEL);
                logger.info("Solicitante da proposta={} com restricao!", novaProposta.getId());
            } else {
                logger.warn("Falha na analise da proposta={}!", novaProposta.getId());
            }
        }
    }
}
