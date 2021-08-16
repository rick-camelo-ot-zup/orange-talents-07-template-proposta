package br.rickcm.proposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "parcela")
public class ParcelaCartao {

    @Id
    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    @Deprecated
    public ParcelaCartao() {
    }

    public ParcelaCartao(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcelaCartao that = (ParcelaCartao) o;
        return Objects.equals(id, that.id) && Objects.equals(quantidade, that.quantidade) && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidade, valor);
    }
}
