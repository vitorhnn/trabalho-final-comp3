package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.sql.SQLException;
import java.util.Optional;

public interface GestorTableGateway {
    Optional<UsuarioDTO> findGestorByCpf(String cpf) throws SQLException;

    int insert(UsuarioDTO gestor) throws SQLException;
}
