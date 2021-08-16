package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.Proposta;

import java.math.BigDecimal;

public class PropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private String status;
    private String cartao;

    public PropostaResponse() {
    }

    public PropostaResponse(Proposta proposta){
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus().name();
        if(proposta.getCartao() != null){
            this.cartao = proposta.getCartao().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getStatus() {
        return status;
    }

    public String getCartao() {
        return cartao;
    }
}
