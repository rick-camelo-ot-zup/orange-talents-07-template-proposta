package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.AvisoCartao;

import java.time.LocalDate;

public class AvisoCartaoDto {
    private LocalDate validoAte;
    private String destino;

    public AvisoCartao toModel(){
        return new AvisoCartao(validoAte, destino);
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
