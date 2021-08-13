package br.rickcm.proposta.rest.external;

import br.rickcm.proposta.model.Proposta;
import br.rickcm.proposta.rest.dto.ResultadoAnalise;
import br.rickcm.proposta.rest.dto.SolicitacaoAnaliseRequest;
import org.springframework.stereotype.Component;

@Component
public class ProcessadorServicoAnalise {

    private final AnaliseClient analiseClient;

    public ProcessadorServicoAnalise(AnaliseClient analiseClient) {
        this.analiseClient = analiseClient;
    }

    public void realizaAnalise(Proposta novaProposta) {
        SolicitacaoAnaliseRequest solicitacaoAnaliseRequest = new SolicitacaoAnaliseRequest(novaProposta);
        ResultadoAnalise analise = analiseClient.getAnalise(solicitacaoAnaliseRequest);
        novaProposta.atualizaStatus(analise);
    }
}
