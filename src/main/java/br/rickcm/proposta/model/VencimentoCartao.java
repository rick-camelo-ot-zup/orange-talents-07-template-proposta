package br.rickcm.proposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vencimento")
public class VencimentoCartao {
    @Id
    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public VencimentoCartao() {
    }

    public VencimentoCartao(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}
