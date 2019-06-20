package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.mock;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.UsuarioTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.util.HashMap;
import java.util.Optional;

public class MockUsuarioTableGateway implements UsuarioTableGateway {
    private HashMap<String, UsuarioDTO> mockStorage;

    public MockUsuarioTableGateway() {
        mockStorage = new HashMap<>();
        mockStorage.put("11111111111", new UsuarioDTO.UsuarioDTOBuilder()
                .setId(1)
                .setCpf("11111111111")
                .setNome("Victor Hermann Chiletto")
                .setSenha("hnn")
                .build()
        );
    }

    @Override
    public Optional<UsuarioDTO> findUsuarioByCpf(String cpf) {
        return Optional.ofNullable(mockStorage.get(cpf));
    }
}
