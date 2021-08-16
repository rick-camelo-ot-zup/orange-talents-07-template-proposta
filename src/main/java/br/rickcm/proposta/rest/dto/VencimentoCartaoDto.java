package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.VencimentoCartao;

import java.time.LocalDateTime;

public class VencimentoCartaoDto {

    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoCartao toModel(){
        return new VencimentoCartao(id, dia, dataDeCriacao);
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
