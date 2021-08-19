package br.rickcm.proposta.rest.controller;

import br.rickcm.proposta.model.Biometria;
import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.repository.BiometriaRepository;
import br.rickcm.proposta.repository.CartaoRepository;
import br.rickcm.proposta.rest.dto.BiometriaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class BiometriaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    private BiometriaRepository repository;
    private CartaoRepository cartaoRepository;

    public BiometriaController(BiometriaRepository repository, CartaoRepository cartaoRepository) {
        this.repository = repository;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/cartoes/{id}/biometrias")
    @Transactional
    public ResponseEntity<?> criarBiometria(@PathVariable("id") String id,
                                    @RequestBody @Valid BiometriaRequest request,
                                    UriComponentsBuilder uriBuilder){
        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = possivelCartao.get();
        Biometria biometria = request.toModel(cartao);
        repository.save(biometria);

        logger.info("Biometria criada com sucesso para o cartao={}!", cartao.getId());

        URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.ok().location(uri).build();
    }

    @GetMapping("/biometrias/{id}")
    public ResponseEntity<?> buscaBiometriaPeloId(@PathVariable("id") Long id){
        Optional<Biometria> possivelBiometria = repository.findById(id);
        if(possivelBiometria.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Biometria biometria = possivelBiometria.get();
        return ResponseEntity.ok(biometria.toString());
    }
}
