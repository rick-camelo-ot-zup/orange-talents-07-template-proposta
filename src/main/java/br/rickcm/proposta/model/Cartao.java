package br.rickcm.proposta.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Cartao {
    @Id
    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cartao_id")
    private List<BloqueioCartao> bloqueios;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cartao_id")
    private List<AvisoCartao> avisos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cartao_id")
    private List<CarteiraCartao> carteiras;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cartao_id")
    private Set<ParcelaCartao> parcelas;

    private BigDecimal limite;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private RenegociacaoCartao renegociacao;

    @OneToOne(mappedBy = "cartao", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private VencimentoCartao vencimento;

    @OneToOne(mappedBy = "cartao",
            fetch = FetchType.EAGER)
    private Proposta proposta;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cartao_id")
    private Set<Biometria> biometrias;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id,
                  LocalDateTime emitidoEm,
                  String titular,
                  List<BloqueioCartao> bloqueios,
                  List<AvisoCartao> avisos,
                  List<CarteiraCartao> carteiras,
                  Set<ParcelaCartao> parcelas,
                  BigDecimal limite,
                  RenegociacaoCartao renegociacao,
                  VencimentoCartao vencimento,
                  Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public String getId() {
        return id;
    }

    public void atrelaBiometria(Biometria biometria){
        this.biometrias.add(biometria);
    }
}
