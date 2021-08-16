package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.BloqueioCartao;

import java.time.LocalDateTime;

public class BloqueioCartaoDto {

    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public BloqueioCartao toModel(){
        return new BloqueioCartao(id, bloqueadoEm, sistemaResponsavel, ativo);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
