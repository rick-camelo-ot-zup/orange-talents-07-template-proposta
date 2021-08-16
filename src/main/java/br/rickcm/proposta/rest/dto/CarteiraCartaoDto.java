package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.CarteiraCartao;

import java.time.LocalDateTime;

public class CarteiraCartaoDto {
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public CarteiraCartao toModel(){
        return new CarteiraCartao(id, email, associadaEm, emissor);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
