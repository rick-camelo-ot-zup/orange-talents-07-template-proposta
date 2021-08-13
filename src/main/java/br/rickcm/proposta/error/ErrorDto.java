package br.rickcm.proposta.error;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ErrorDto {

    private Collection<String> mensagens;

    public ErrorDto(Collection<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }
}
