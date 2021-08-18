package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.AvisoCartao;
import br.rickcm.proposta.model.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RequisicaoAvisoRequest {

    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate validoAte;

    public AvisoCartao toModel(@NotNull Cartao cartao, String ip, String userAgent){
        return new AvisoCartao(destino, validoAte, ip, userAgent, cartao);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
