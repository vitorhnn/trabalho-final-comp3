package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.mock;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.MuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.MuseuDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class MockMuseuTableGateway implements MuseuTableGateway {
    private HashMap<Integer, MuseuDTO> mockStorage;

    private int sequential = 2;

    public MockMuseuTableGateway() {
        mockStorage = new HashMap<>();
        mockStorage.put(1, new MuseuDTO.MuseuDTOBuilder()
                .setId(1)
                .setNome("Departamento de Ciência da Computação")
                .setCidade("Nova Iguaçu")
                .setEstado("Rio de Janeiro")
                .setDataCriacao(LocalDate.now())
                .setIdGestor(1)
                .build()
        );
    }

    @Override
    public Optional<MuseuDTO> findMuseuById(int id) {
        return Optional.ofNullable(mockStorage.get(id));
    }

    @Override
    public int insert(MuseuDTO museu) throws SQLException {
        MuseuDTO toInsert = new MuseuDTO.MuseuDTOBuilder()
                .setId(sequential)
                .setNome(museu.getNome())
                .setCidade(museu.getCidade())
                .setEstado(museu.getEstado())
                .setDataCriacao(museu.getDataCriacao())
                .setIdGestor(museu.getIdGestor())
                .build();

        mockStorage.put(sequential, toInsert);

        sequential++;

        return sequential;
    }
}
