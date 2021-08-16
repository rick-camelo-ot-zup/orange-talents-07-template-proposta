package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.RenegociacaoCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoCartaoDto {
    private String id;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    public RenegociacaoCartao toModel(){
        return new RenegociacaoCartao(id,quantidade, valor, dataDeCriacao);
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
