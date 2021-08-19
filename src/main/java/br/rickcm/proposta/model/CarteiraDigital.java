package br.rickcm.proposta.model;

import br.rickcm.proposta.enums.TipoCarteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "carteira")
public class CarteiraDigital {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private TipoCarteira tipo;
    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(@NotNull String id,@NotNull TipoCarteira tipo,@NotBlank String email,@NotNull Cartao cartao) {
        this.id = id;
        this.tipo = tipo;
        this.email = email;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraDigital that = (CarteiraDigital) o;
        return tipo == that.tipo && Objects.equals(cartao, that.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, cartao);
    }

    public String getId() {
        return id;
    }

    public TipoCarteira getTipo() {
        return tipo;
    }
}
