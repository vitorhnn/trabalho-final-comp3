package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception;

public class SolicitacaoNaoExisteException extends Exception {
    private int id;

    public SolicitacaoNaoExisteException(int id) {
        this.id = id;
    }
}
