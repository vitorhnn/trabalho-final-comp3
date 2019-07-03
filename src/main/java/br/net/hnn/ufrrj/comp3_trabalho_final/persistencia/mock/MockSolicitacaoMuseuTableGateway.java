package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.mock;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MockSolicitacaoMuseuTableGateway implements SolicitacaoMuseuTableGateway {
    private HashMap<Integer, SolicitacaoMuseuDTO> mockStorage;

    private int sequential = 2;

    public MockSolicitacaoMuseuTableGateway() {
        mockStorage = new HashMap<>();
        mockStorage.put(1, new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setId(1)
                .setNome("Departamento de Ciência da Computação")
                .setCidade("Nova Iguaçu")
                .setEstado("Rio de Janeiro")
                .setDataCriacao("1998-01-16")
                .setCpfGestor("111111")
                .setNomeGestor("Victor Hermann Chiletto")
                .setSenhaGestor("senhavaiaqui")
                .build()
        );
    }

    @Override
    public Optional<SolicitacaoMuseuDTO> getSolicitacaoMuseuById(int id) {
        return Optional.ofNullable(mockStorage.get(id));
    }

    @Override
    public @NotNull List<SolicitacaoMuseuDTO> findAll() throws SQLException {
        return new ArrayList<>(mockStorage.values());
    }

    @Override
    public int insert(SolicitacaoMuseuDTO dto) {
        SolicitacaoMuseuDTO toInsert = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder(dto)
                .setId(sequential)
                .build();

        mockStorage.put(sequential, toInsert);

        return sequential++;
    }
}
