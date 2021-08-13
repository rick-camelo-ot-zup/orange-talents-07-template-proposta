package br.rickcm.proposta.model;

import br.rickcm.proposta.validator.CpfCnpj;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Map;
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
        return "Proposta{"+
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }

    public Long getId() {
        return this.id;
    }
}
