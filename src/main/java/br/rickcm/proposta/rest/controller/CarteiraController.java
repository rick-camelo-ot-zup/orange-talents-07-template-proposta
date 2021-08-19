package br.rickcm.proposta.rest.controller;

import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.repository.CartaoRepository;
import br.rickcm.proposta.rest.dto.CarteiraDigitalRequest;
import br.rickcm.proposta.rest.external.ProcessadorServicoCartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CarteiraController {
    private final Logger logger = LoggerFactory.getLogger(AvisoController.class);

    private CartaoRepository cartaoRepository;
    private ProcessadorServicoCartao servicoCartao;

    public CarteiraController(CartaoRepository cartaoRepository, ProcessadorServicoCartao servicoCartao) {
        this.cartaoRepository = cartaoRepository;
        this.servicoCartao = servicoCartao;
    }

    @PostMapping("/cartoes/{id}/carteiras")
    public ResponseEntity<?> create(@PathVariable("id") String idCartao,
                                    @RequestBody @Valid CarteiraDigitalRequest request,
                                    UriComponentsBuilder uriBuilder){
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = possivelCartao.get();

        if(cartao.isCarteiraAssociada(request.getCarteira())) return ResponseEntity.unprocessableEntity().build();

        return servicoCartao.associaCarteira(cartaoRepository,cartao,request,uriBuilder);
    }
}
