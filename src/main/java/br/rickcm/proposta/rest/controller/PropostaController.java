package br.rickcm.proposta.rest.controller;

import br.rickcm.proposta.model.Proposta;
import br.rickcm.proposta.repository.PropostaRepository;
import br.rickcm.proposta.rest.dto.NovaPropostaRequest;
import br.rickcm.proposta.rest.dto.PropostaResponse;
import br.rickcm.proposta.rest.external.ProcessadorServicoAnalise;
import br.rickcm.proposta.validator.ProibeMaisDeUmaPropostaParaMesmoSolicitante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    private final PropostaRepository repository;
    private final ProibeMaisDeUmaPropostaParaMesmoSolicitante proibeMaisDeUmaPropostaParaMesmoSolicitante;
    private final ProcessadorServicoAnalise servicoAnalise;

    public PropostaController(PropostaRepository repository,
                              ProibeMaisDeUmaPropostaParaMesmoSolicitante proibeMaisDeUmaPropostaParaMesmoSolicitante, ProcessadorServicoAnalise servicoAnalise) {
        this.repository = repository;
        this.proibeMaisDeUmaPropostaParaMesmoSolicitante = proibeMaisDeUmaPropostaParaMesmoSolicitante;
        this.servicoAnalise = servicoAnalise;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeMaisDeUmaPropostaParaMesmoSolicitante);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                   UriComponentsBuilder uriBuilder){

        Proposta novaProposta = request.toModel();
        repository.save(novaProposta);
        servicoAnalise.realizaAnalise(novaProposta);

        logger.info("Proposta documento={} salário={} criada com sucesso!", request.getDocumento(), request.getSalario());

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPropostaPorId(@PathVariable("id") Long id){
        Optional<Proposta> possivelProposta = repository.findById(id);
        if (possivelProposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        PropostaResponse propostaResponse = new PropostaResponse(possivelProposta.get());
        return ResponseEntity.ok(propostaResponse);
    }
}
