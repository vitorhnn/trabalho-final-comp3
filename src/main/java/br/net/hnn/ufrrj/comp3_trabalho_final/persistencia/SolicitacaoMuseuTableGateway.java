package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

import java.sql.SQLException;
import java.util.Optional;

public interface SolicitacaoMuseuTableGateway {
    Optional<SolicitacaoMuseuDTO> getSolicitacaoMuseuById(int id) throws SQLException;

    void insert(SolicitacaoMuseuDTO dto) throws SQLException;
}
