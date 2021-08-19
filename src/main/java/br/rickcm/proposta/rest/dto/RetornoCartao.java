package br.rickcm.proposta.rest.dto;

import br.rickcm.proposta.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RetornoCartao {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueioCartaoDto> bloqueios;
    private List<ParcelaCartaoDto> parcelas;
    private BigDecimal limite;
    private RenegociacaoCartaoDto renegociacao;
    private VencimentoCartaoDto vencimento;
    private String idProposta;

    public Cartao toModel(Proposta proposta) {
        List<BloqueioCartao> bloqueios = this.bloqueios.stream().map(BloqueioCartaoDto::toModel).collect(Collectors.toList());
        Set<ParcelaCartao> parcelas = this.parcelas.stream().map(ParcelaCartaoDto::toModel).collect(Collectors.toSet());
        RenegociacaoCartao renegociacao = null;
        VencimentoCartao vencimento = null;;
        if(this.renegociacao != null){
            renegociacao = this.renegociacao.toModel();
        }
        if(this.vencimento != null){
            vencimento = this.vencimento.toModel();
        }
        return new Cartao(id,
                emitidoEm,
                titular,
                bloqueios,
                null,
                null,
                parcelas,
                limite,
                renegociacao,
                vencimento,
                proposta);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<?> getBloqueios() {
        return bloqueios;
    }

    public List<?> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public RenegociacaoCartaoDto getRenegociacao() {
        return renegociacao;
    }

    public VencimentoCartaoDto getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
