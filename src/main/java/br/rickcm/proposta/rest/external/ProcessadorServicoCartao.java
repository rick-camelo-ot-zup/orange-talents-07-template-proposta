package br.rickcm.proposta.rest.external;

import br.rickcm.proposta.enums.ResultadoCarteiraResponse;
import br.rickcm.proposta.model.AvisoCartao;
import br.rickcm.proposta.model.Cartao;
import br.rickcm.proposta.model.CarteiraDigital;
import br.rickcm.proposta.model.RequisicaoBloqueioCartao;
import br.rickcm.proposta.repository.AvisoRepository;
import br.rickcm.proposta.repository.CartaoRepository;
import br.rickcm.proposta.rest.dto.AvisoCartaoDto;
import br.rickcm.proposta.rest.dto.CarteiraDigitalRequest;
import br.rickcm.proposta.rest.dto.RetornoBloqueio;
import br.rickcm.proposta.rest.dto.RetornoCarteira;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProcessadorServicoCartao {

    private final Logger logger = LoggerFactory.getLogger(ProcessadorServicoCartao.class);
    private final CartaoClient cartaoClient;
    private final AvisoRepository avisoRepository;

    public ProcessadorServicoCartao(CartaoClient cartaoClient, AvisoRepository avisoRepository) {
        this.cartaoClient = cartaoClient;
        this.avisoRepository = avisoRepository;
    }

    public void bloqueiaCartao(Cartao cartao, RequisicaoBloqueioCartao requisicaoBloqueioCartao) {
        try {
            Map<String, String> apiRequest = new HashMap<>();
            apiRequest.put("sistemaResponsavel", "propostas");

            ResponseEntity<RetornoBloqueio> bloqueioLegado = cartaoClient.bloqueia(cartao.getId(), apiRequest);

            if(bloqueioLegado.getStatusCode().equals(HttpStatus.OK)){
                cartao.bloqueia();
                logger.info("Bloqueio realizado para o cartao={}", cartao.getId());
            }
        } catch (FeignException e) {
            if (e.status() == HttpStatus.BAD_REQUEST.value()){
                logger.error("HttpStatus 400 ao tentar notificar o sistema logado sobre a requisicao de bloqueio={}",
                        requisicaoBloqueioCartao.getId());
            }
            logger.error("Falha ao tentar notificar o sistema logado sobre a requisicao de bloqueio={}",
                    requisicaoBloqueioCartao.getId());
        }
    }

    @Transactional
    public ResponseEntity<?> notificaAviso(Cartao cartao, AvisoCartao avisoCartao) {
        try {
            Map<String, String> apiRequest = new HashMap<>();
            apiRequest.put("validoAte", avisoCartao.getValidoAte().toString());
            apiRequest.put("destino", avisoCartao.getDestino());

            ResponseEntity<AvisoCartaoDto> avisoLegado = cartaoClient.avisa(cartao.getId(), apiRequest);

            avisoRepository.save(avisoCartao);
            logger.info("Aviso de viagem={} cadastrado para o cartao={}",avisoCartao.getId(), cartao.getId());
            return ResponseEntity.ok().build();

        } catch (FeignException e) {
            if (e.status() == HttpStatus.BAD_REQUEST.value()){
                logger.error("HttpStatus 400 ao tentar notificar o sistema legado sobre a requisicao de aviso de viagem para o cartao={}",
                        cartao.getId());
                return ResponseEntity.badRequest().build();
            }
            logger.error("Falha ao tentar notificar o sistema legado sobre a requisicao de aviso de viagem para o cartao={}",
                    cartao.getId());
            return ResponseEntity.status(500).build();
        }
    }

    @Transactional
    public ResponseEntity<?> associaCarteira(CartaoRepository repository,
                                             Cartao cartao,
                                             CarteiraDigitalRequest request,
                                             UriComponentsBuilder uriBuilder){
        try {
            RetornoCarteira retornoCarteira = cartaoClient.associaCarteira(cartao.getId(), request);

            if(retornoCarteira.getResultado().equals(ResultadoCarteiraResponse.FALHA)){
                return ResponseEntity.unprocessableEntity().build();
            }

            CarteiraDigital carteira = request.toModel(retornoCarteira, cartao);

            cartao.associaCarteira(carteira);

            repository.save(cartao);

            logger.info("Cartao={} associado a carteira={} do tipo={}",cartao.getId(), carteira.getId(), carteira.getTipo());

            URI uri = uriBuilder.path("/cartoes/{id}/carteiras/{idCarteira}").buildAndExpand(cartao.getId(),carteira.getId()).toUri();

            return ResponseEntity.created(uri).build();

        } catch (FeignException e) {
            logger.error("Falha ao tentar associar o cartao={} a carteira={}",
                    cartao.getId(), request.getCarteira().name());
            return ResponseEntity.status(e.status()).build();
        }
    }

}