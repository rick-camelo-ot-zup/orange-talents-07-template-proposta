package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.enums.ServicoAnaliseStatus;

public class ResultadoAnalise {
    private String documento;
    private String nome;
    private String idProposta;
    private ServicoAnaliseStatus resultadoSolicitacao;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public ServicoAnaliseStatus getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    @Override
    public String toString() {
        return "ResultadoAnalise{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta='" + idProposta + '\'' +
                ", resultadoSolicitacao=" + resultadoSolicitacao +
                '}';
    }
}
