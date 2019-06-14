package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.util.Optional;


public interface UsuarioTableGateway {
    Optional<UsuarioDTO> findUsuarioByCpf(String cpf);
}
