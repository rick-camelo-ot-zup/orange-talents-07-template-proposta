package br.rickcm.proposta.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Lob
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] fingerprint;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime instante;
    @ManyToOne
    private Cartao cartao;

    @PrePersist
    protected void onCreate() {
        instante = LocalDateTime.now();
    }

    @Deprecated
    public Biometria() {
    }

    public Biometria(@NotBlank String fingerprint, @NotNull Cartao cartao) {
        this.fingerprint = fingerprint.getBytes(StandardCharsets.UTF_8);
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria = (Biometria) o;
        return Objects.equals(id, biometria.id) && Objects.equals(fingerprint, biometria.fingerprint) && Objects.equals(instante, biometria.instante) && Objects.equals(cartao, biometria.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fingerprint, instante, cartao);
    }

    public Long getId() {
        return id;
    }

    public String getFingerprint() {
        try {
            return new String(this.fingerprint, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return this.fingerprint.toString();
        }
    }

    @Override
    public String toString() {
        return "Biometria{" +
                "id=" + id +
                ", fingerprint=" + getFingerprint() +
                ", instante=" + instante +
                ", cartao=" + cartao +
                '}';
    }
}
