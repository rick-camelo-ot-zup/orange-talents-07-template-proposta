package br.rickcm.proposta.model;

import br.rickcm.proposta.enums.StatusProposta;
import br.rickcm.proposta.rest.dto.ResultadoAnalise;
import br.rickcm.proposta.util.Encryptador;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String documento;
    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @Column(nullable = false)
    private String endereco;
    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private StatusProposta status;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @PrePersist
    protected void onCreate(){
        Encryptador encryptador = new Encryptador();
        this.documento = encryptador.encrypt(this.documento);
    }

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank String documento,
                    @NotBlank String email,
                    @NotBlank String nome,
                    @NotBlank String endereco,
                    @NotNull BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return Objects.equals(id, proposta.id)
                && Objects.equals(documento, proposta.documento)
                && Objects.equals(email, proposta.email)
                && Objects.equals(nome, proposta.nome)
                && Objects.equals(endereco, proposta.endereco)
                && Objects.equals(salario, proposta.salario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documento, email, nome, endereco, salario);
    }

    @Override
    public String toString() {
        return "Proposta{" +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                ", status=" + status.name() +
                ", cartao=" + cartao +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void atualizaStatus(ResultadoAnalise resultadoAnalise) {
        this.status = resultadoAnalise.getResultadoSolicitacao().getStatusAnalise();
    }

    public void atualizaStatus(StatusProposta status) {
        this.status = status;
    }

    public void atrelaCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
