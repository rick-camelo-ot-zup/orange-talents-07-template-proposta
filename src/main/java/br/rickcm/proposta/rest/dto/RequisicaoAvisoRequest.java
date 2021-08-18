package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.model.RequisicaoAvisoCartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RequisicaoAvisoRequest {

    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate dataTermino;

    public RequisicaoAvisoCartao toModel(@NotNull Cartao cartao, String ip, String userAgent){
        return new RequisicaoAvisoCartao(destino, dataTermino, ip, userAgent, cartao);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }
}
