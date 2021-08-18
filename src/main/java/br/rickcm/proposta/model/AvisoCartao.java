package br.rickcm.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "aviso")
public class AvisoCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String destino;
    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate validoAte;
    private LocalDateTime instante;
    private String ip;
    private String userAgent;
    @ManyToOne
    private Cartao cartao;

    @PrePersist
    protected void onCreate(){
        instante = LocalDateTime.now();
    }

    @Deprecated
    public AvisoCartao() {
    }

    public AvisoCartao(@NotBlank String destino,
                                 @NotNull @Future LocalDate dataTermino,
                                 String ip,
                                 String userAgent,
                                 Cartao cartao) {
        this.destino = destino;
        this.validoAte = dataTermino;
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
