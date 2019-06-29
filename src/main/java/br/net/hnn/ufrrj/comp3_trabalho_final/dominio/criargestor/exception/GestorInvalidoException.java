package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

public class GestorInvalidoException extends Exception {
    private SolicitacaoMuseuDTO solicitacao;
    private String motivo;

    public GestorInvalidoException(SolicitacaoMuseuDTO solicitacao, String motivoj) {
        this.solicitacao = solicitacao;
        this.motivo = motivo;
    }

    public SolicitacaoMuseuDTO getSolicitacao() {
        return solicitacao;
    }

    public String getMotivo() {
        return motivo;
    }
}
