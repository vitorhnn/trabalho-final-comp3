package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBSolicitacaoMuseuTableGateway implements SolicitacaoMuseuTableGateway {
    private static final Logger logger = LoggerFactory.getLogger(DBSolicitacaoMuseuTableGateway.class);

    private Connection dbConn;

    private PreparedStatement findByIdStatement;

    private PreparedStatement findAllStatement;

    private PreparedStatement insertStatement;


    public DBSolicitacaoMuseuTableGateway() throws SQLException {
        dbConn = DatabaseConnectionSingleton.getInstance().getConnection();

        findByIdStatement = dbConn.prepareStatement(
                "SELECT id, nome, dataCriacao, cidade, estado, cpfGestor, nomeGestor, senhaGestor FROM Solicitacao WHERE id = ?"
        );

        findAllStatement = dbConn.prepareStatement(
                "SELECT id, nome, dataCriacao, cidade, estado, cpfGestor, nomeGestor, senhaGestor FROM Solicitacao"
        );

        insertStatement = dbConn.prepareStatement(
                "INSERT INTO Solicitacao (nome, dataCriacao, cidade, estado, cpfGestor, nomeGestor, senhaGestor) VALUES (?, ?, ?, ?, ?, ?, ?)"
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
                    .setDataCriacao(rs.getString(3))
                    .setCidade(rs.getString(4))
                    .setEstado(rs.getString(5))
                    .setCpfGestor(rs.getString(6))
                    .setNomeGestor(rs.getString(7))
                    .setSenhaGestor(rs.getString(8))
                    .build();

            return Optional.of(returnDto);
        }

        return Optional.empty();
    }

    @Override
    public @NotNull List<SolicitacaoMuseuDTO> findAll() throws SQLException {
        ArrayList<SolicitacaoMuseuDTO> collector = new ArrayList<>();
        ResultSet rs = findAllStatement.executeQuery();

        while (rs.next()) {
            SolicitacaoMuseuDTO returnDto = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                    .setId(rs.getInt(1))
                    .setNome(rs.getString(2))
                    .setDataCriacao(rs.getString(3))
                    .setCidade(rs.getString(4))
                    .setEstado(rs.getString(5))
                    .setCpfGestor(rs.getString(6))
                    .setNomeGestor(rs.getString(7))
                    .setSenhaGestor(rs.getString(8))
                    .build();

            collector.add(returnDto);
        }

        return collector;
    }

    @Override
    public void insert(SolicitacaoMuseuDTO dto) throws SQLException {
        insertStatement.clearParameters();

        insertStatement.setString(1, dto.getNome());
        insertStatement.setDate(2, Date.valueOf(dto.getDataCriacao()));
        insertStatement.setString(3, dto.getCidade());
        insertStatement.setString(4, dto.getEstado());
        insertStatement.setString(5, dto.getCpfGestor());
        insertStatement.setString(6, dto.getNomeGestor());
        insertStatement.setString(7, dto.getSenhaGestor());
        insertStatement.executeUpdate();
    }
}
