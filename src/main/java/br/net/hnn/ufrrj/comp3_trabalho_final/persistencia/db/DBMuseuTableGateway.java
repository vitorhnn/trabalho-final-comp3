package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.MuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.MuseuDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;

public class DBMuseuTableGateway implements MuseuTableGateway {
    private static final Logger logger = LoggerFactory.getLogger(DBMuseuTableGateway.class);

    private Connection dbConn;

    private PreparedStatement findByIdStmt;

    private PreparedStatement insertStmt;

    public DBMuseuTableGateway() throws SQLException {
        dbConn = DatabaseConnectionSingleton.getInstance().getConnection();

        findByIdStmt = dbConn.prepareStatement(
                "SELECT id, nome, cidade, estado, idGestor FROM Museu WHERE id = ?"
        );

        insertStmt = dbConn.prepareStatement(
                "INSERT INTO MUSEU (id, nome, cidade, estado, dataCriacao, idGestor) VALUES (DEFAULT, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS
        );
    }

    @Override
    public Optional<MuseuDTO> findMuseuById(int id) throws SQLException {
        findByIdStmt.clearParameters();
        findByIdStmt.setInt(1, id);

        ResultSet rs = findByIdStmt.executeQuery();

        if (rs.next()) {
            MuseuDTO returnDto = new MuseuDTO.MuseuDTOBuilder()
                    .setId(rs.getInt(1))
                    .setNome(rs.getString(2))
                    .setCidade(rs.getString(3))
                    .setEstado(rs.getString(4))
                    .setIdGestor(rs.getInt(5))
                    .build();

            return Optional.of(returnDto);
        }

        return Optional.empty();
    }

    @Override
    public int insert(MuseuDTO museu) throws SQLException {
        insertStmt.clearParameters();
        insertStmt.setString(1, museu.getNome());
        insertStmt.setString(2, museu.getCidade());
        insertStmt.setString(3, museu.getEstado());
        insertStmt.setDate(4, Date.valueOf(museu.getDataCriacao()));
        insertStmt.setInt(5, museu.getIdGestor());
        insertStmt.executeUpdate();

        ResultSet rs = insertStmt.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }
}
