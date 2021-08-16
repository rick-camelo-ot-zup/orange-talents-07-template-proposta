package br.rickcm.proposta.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "aviso")
public class AvisoCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate validoAte;
    private String destino;

    @Deprecated
    public AvisoCartao() {
    }

    public AvisoCartao(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }
}
