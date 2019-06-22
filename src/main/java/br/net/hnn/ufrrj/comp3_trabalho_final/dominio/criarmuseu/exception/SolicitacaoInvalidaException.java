package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

public class SolicitacaoInvalidaException extends Exception {
    private SolicitacaoMuseuDTO solicitacao;

    public SolicitacaoInvalidaException(SolicitacaoMuseuDTO solicitacao) {
        this.solicitacao = solicitacao;
    }

    public SolicitacaoMuseuDTO getSolicitacao() {
        return solicitacao;
    }
}
