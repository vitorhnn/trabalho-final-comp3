package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DBSolicitacaoMuseuTableGateway implements SolicitacaoMuseuTableGateway {
    private static final Logger logger = LoggerFactory.getLogger(DBSolicitacaoMuseuTableGateway.class);

    private Connection dbConn;

    private PreparedStatement findByIdStatement;

    private PreparedStatement insertStatement;

    public DBSolicitacaoMuseuTableGateway() throws SQLException {
        dbConn = DatabaseConnectionSingleton.getInstance().getConnection();

        findByIdStatement = dbConn.prepareStatement(
                "SELECT id, nome, cidade, estado, cpfGestor, nomeGestor, senhaGestor FROM Solicitacao WHERE id = ?"
        );

        insertStatement = dbConn.prepareStatement(
                "INSERT INTO Solicitacao (nome, cidade, estado, cpfGestor, nomeGestor, senhaGestor) VALUES (?, ?, ?, ?, ?, ?)"
        );
    }

    @Override
    public Optional<SolicitacaoMuseuDTO> getSolicitacaoMuseuById(int id) throws SQLException {
        findByIdStatement.clearParameters();
        findByIdStatement.setInt(1, id);

        ResultSet rs = findByIdStatement.executeQuery();

        if (rs.next()) {
            SolicitacaoMuseuDTO returnDto = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                    .setId(rs.getInt(1))
                    .setNome(rs.getString(2))
                    .setCidade(rs.getString(3))
                    .setEstado(rs.getString(4))
                    .setCpfGestor(rs.getString(5))
                    .setNomeGestor(rs.getString(6))
                    .setSenhaGestor(rs.getString(7))
                    .build();

            return Optional.of(returnDto);
        }

        return Optional.empty();
    }

    @Override
    public void insert(SolicitacaoMuseuDTO dto) throws SQLException {
        insertStatement.clearParameters();

        insertStatement.setString(1, dto.getNome());
        insertStatement.setString(2, dto.getCidade());
        insertStatement.setString(3, dto.getEstado());
        insertStatement.setString(4, dto.getCpfGestor());
        insertStatement.setString(5, dto.getNomeGestor());
        insertStatement.setString(6, dto.getSenhaGestor());
        insertStatement.executeUpdate();
    }
}
