package br.rickcm.proposta.rest.controller;

import br.rickcm.proposta.model.Biometria;
import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.repository.BiometriaRepository;
import br.rickcm.proposta.repository.CartaoRepository;
import br.rickcm.proposta.rest.dto.BiometriaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class BiometriaController {

    private BiometriaRepository repository;
    private CartaoRepository cartaoRepository;

    public BiometriaController(BiometriaRepository repository, CartaoRepository cartaoRepository) {
        this.repository = repository;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/cartoes/{id}/biometrias")
    public ResponseEntity<?> create(@PathVariable("id") String id,
                                    @RequestBody @Valid BiometriaRequest request,
                                    UriComponentsBuilder uriBuilder){
        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = possivelCartao.get();
        Biometria biometria = request.toModel(cartao);
        repository.save(biometria);

        URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.ok().location(uri).build();
    }

    @GetMapping("/biometrias/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Biometria> possivelBiometria = repository.findById(id);
        if(possivelBiometria.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Biometria biometria = possivelBiometria.get();
        return ResponseEntity.ok(biometria.toString());
    }
}
