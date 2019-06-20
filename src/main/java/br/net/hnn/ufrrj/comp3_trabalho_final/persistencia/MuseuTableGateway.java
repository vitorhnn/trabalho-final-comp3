package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.MuseuDTO;

import java.sql.SQLException;
import java.util.Optional;

public interface MuseuTableGateway {
    Optional<MuseuDTO> findMuseuById(int id) throws SQLException;
}
