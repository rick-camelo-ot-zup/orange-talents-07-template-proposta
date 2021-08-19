package br.rickcm.proposta.model;

import br.rickcm.proposta.enums.StatusCartao;
import br.rickcm.proposta.enums.TipoCarteira;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<CarteiraDigital> carteiras;

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
    @Enumerated(EnumType.STRING)
    private StatusCartao status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cartao_id")
    private List<RequisicaoBloqueioCartao> requisicoesBloqueios;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id,
                  LocalDateTime emitidoEm,
                  String titular,
                  List<BloqueioCartao> bloqueios,
                  List<AvisoCartao> avisos,
                  Set<CarteiraDigital> carteiras,
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
        this.status = StatusCartao.HABILITADO;
    }

    public String getId() {
        return id;
    }

    public void atrelaBiometria(Biometria biometria){
        this.biometrias.add(biometria);
    }

    public boolean isBloqueado(){
        List<BloqueioCartao> bloqueioAtivo = this.bloqueios.
                stream()
                .filter(bloqueioCartao -> bloqueioCartao.isAtivo() == true)
                .collect(Collectors.toList());
        return (status.equals(StatusCartao.BLOQUEADO) || !bloqueioAtivo.isEmpty());

    }

    public void associaCarteira(CarteiraDigital carteira) {
        this.carteiras.add(carteira);
    }

    public void bloqueia(){
        this.status = StatusCartao.BLOQUEADO;
    }

    public boolean isCarteiraAssociada(TipoCarteira carteira){
        CarteiraDigital carteiraDigital = new CarteiraDigital("idQualquer", carteira, "emailqualquer", this);
        return this.carteiras.contains(carteiraDigital);
    }
}
