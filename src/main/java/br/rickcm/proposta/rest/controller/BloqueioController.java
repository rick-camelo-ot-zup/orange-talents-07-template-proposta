package br.rickcm.proposta.rest.controller;

import br.rickcm.proposta.error.ApiErrorException;
import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.model.RequisicaoBloqueioCartao;
import br.rickcm.proposta.repository.CartaoRepository;
import br.rickcm.proposta.repository.RequisicaoBloqueioRepository;
import br.rickcm.proposta.rest.external.ProcessadorServicoCartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class BloqueioController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    private CartaoRepository cartaoRepository;
    private RequisicaoBloqueioRepository bloqueioRepository;
    private ProcessadorServicoCartao servicoCartao;

    public BloqueioController(CartaoRepository cartaoRepository, RequisicaoBloqueioRepository bloqueioRepository, ProcessadorServicoCartao servicoCartao) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueioRepository = bloqueioRepository;
        this.servicoCartao = servicoCartao;
    }

    @PostMapping("/cartoes/{id}/bloqueios")
    @Transactional
    public ResponseEntity<?> create(@PathVariable("id") String idCartao, HttpServletRequest request){
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = possivelCartao.get();
        if(cartao.isBloqueado()){
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Este cartão já está bloqueado.");
        }

        String userAgent = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        RequisicaoBloqueioCartao requisicaoBloqueioCartao = new RequisicaoBloqueioCartao(ip, userAgent, cartao);
        bloqueioRepository.save(requisicaoBloqueioCartao);

        logger.info("Adicionada requisição de bloqueio={} para o cartao={}", requisicaoBloqueioCartao.getId(), cartao.getId());

        servicoCartao.bloqueiaCartao(cartao, requisicaoBloqueioCartao);

        return ResponseEntity.ok().build();
    }
}
