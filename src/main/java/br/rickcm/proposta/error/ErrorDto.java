package br.rickcm.proposta.error;

import java.util.List;
import java.util.Map;

public class ErrorDto {

    private Map<String, List<String>> errors;

    public ErrorDto(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
