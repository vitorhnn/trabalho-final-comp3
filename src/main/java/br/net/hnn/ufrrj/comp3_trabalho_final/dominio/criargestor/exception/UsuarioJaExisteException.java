package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

public class UsuarioJaExisteException extends Exception {
    private UsuarioDTO usuario;

    public UsuarioJaExisteException(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }
}
