package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.Biometria;
import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.validator.FormatoBase64;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    @FormatoBase64
    private String fingerprint;

    public Biometria toModel(Cartao cartao){
        return new Biometria(fingerprint,cartao);
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
