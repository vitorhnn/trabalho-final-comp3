package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SolicitacaoMuseuTableGateway {
    Optional<SolicitacaoMuseuDTO> getSolicitacaoMuseuById(int id) throws SQLException;

    @NotNull List<SolicitacaoMuseuDTO> findAll() throws SQLException;

    void insert(SolicitacaoMuseuDTO dto) throws SQLException;
}
