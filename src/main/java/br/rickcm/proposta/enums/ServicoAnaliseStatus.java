package br.rickcm.proposta.enums;

public enum ServicoAnaliseStatus {
    COM_RESTRICAO{
        @Override
        public StatusProposta getStatusAnalise() {
            return StatusProposta.NAO_ELEGIVEL;
        }
    },
    SEM_RESTRICAO {
        @Override
        public StatusProposta getStatusAnalise() {
            return StatusProposta.ELEGIVEL;
        }
    };

    ServicoAnaliseStatus() {
    }

    public abstract StatusProposta getStatusAnalise();
}
