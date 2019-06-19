package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.mock;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

import java.time.LocalDate;
import java.util.HashMap;
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
                .setDataCriacao(LocalDate.now())
                .setCpfGestor("111111")
                .setCpfGestor("Victor Hermann Chiletto")
                .setSenhaGestor("senhavaiaqui")
                .build()
        );
    }
    @Override
    public Optional<SolicitacaoMuseuDTO> getSolicitacaoMuseuById(int id) {
        return Optional.ofNullable(mockStorage.get(id));
    }

    @Override
    public void insert(SolicitacaoMuseuDTO dto) {
        SolicitacaoMuseuDTO toInsert = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder(dto)
                .setId(sequential)
                .build();

        mockStorage.put(sequential, toInsert);
        sequential++;
    }
}
