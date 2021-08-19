package br.rickcm.proposta.rest.controller;

import br.rickcm.proposta.model.AvisoCartao;
import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.repository.CartaoRepository;
import br.rickcm.proposta.rest.dto.RequisicaoAvisoRequest;
import br.rickcm.proposta.rest.external.ProcessadorServicoCartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoController {
    private final Logger logger = LoggerFactory.getLogger(AvisoController.class);

    private CartaoRepository cartaoRepository;
    private ProcessadorServicoCartao servicoCartao;

    public AvisoController(CartaoRepository cartaoRepository, ProcessadorServicoCartao servicoCartao) {
        this.cartaoRepository = cartaoRepository;
        this.servicoCartao = servicoCartao;
    }

    @PostMapping("/cartoes/{id}/avisos")
    public ResponseEntity<?> criarAviso(@PathVariable("id") String idCartao,
                                    @RequestBody @Valid RequisicaoAvisoRequest avisoRequest,
                                    HttpServletRequest request){
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = possivelCartao.get();

        String userAgent = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        AvisoCartao avisoCartao = avisoRequest.toModel(cartao, ip, userAgent);

        ResponseEntity<?> response = servicoCartao.notificaAviso(cartao, avisoCartao);

        return response;
    }
}
