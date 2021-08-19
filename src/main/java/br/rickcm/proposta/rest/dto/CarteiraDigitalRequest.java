package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.enums.TipoCarteira;
import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.model.CarteiraDigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TipoCarteira carteira;

    public CarteiraDigital toModel(RetornoCarteira retornoCarteira, Cartao cartao){
        return new CarteiraDigital(retornoCarteira.getId(),carteira,email,cartao);
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
