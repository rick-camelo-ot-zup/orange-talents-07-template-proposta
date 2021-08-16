package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.ParcelaCartao;

import java.math.BigDecimal;

public class ParcelaCartaoDto {
    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public ParcelaCartao toModel(){
        return new ParcelaCartao(id, quantidade, valor);
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
}
