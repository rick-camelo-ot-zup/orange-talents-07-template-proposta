package br.rickcm.proposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requisicao_bloqueio")
public class RequisicaoBloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime instante;
    private String ip;
    private String userAgent;
    @ManyToOne
    private Cartao cartao;

    @PrePersist
    protected void onCreate() {
        instante = LocalDateTime.now();
    }

    @Deprecated
    public RequisicaoBloqueioCartao() {
    }

    public RequisicaoBloqueioCartao(String ip, String userAgent, Cartao cartao) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
