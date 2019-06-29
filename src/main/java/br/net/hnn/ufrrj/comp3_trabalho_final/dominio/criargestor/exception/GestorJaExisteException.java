package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

public class GestorJaExisteException extends Exception {
    private UsuarioDTO gestor;

    public GestorJaExisteException(UsuarioDTO gestor) {
        this.gestor = gestor;
    }

    public UsuarioDTO getGestor() {
        return gestor;
    }
}
