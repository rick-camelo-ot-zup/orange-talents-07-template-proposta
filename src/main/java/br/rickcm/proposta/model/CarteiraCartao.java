package br.rickcm.proposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "carteira")
public class CarteiraCartao {
    @Id
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @Deprecated
    public CarteiraCartao() {
    }

    public CarteiraCartao(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }
}
