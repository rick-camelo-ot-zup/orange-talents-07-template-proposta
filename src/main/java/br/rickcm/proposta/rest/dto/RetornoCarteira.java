package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.enums.ResultadoCarteiraResponse;

public class RetornoCarteira {

    private ResultadoCarteiraResponse resultado;
    private String id;

    public ResultadoCarteiraResponse getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
