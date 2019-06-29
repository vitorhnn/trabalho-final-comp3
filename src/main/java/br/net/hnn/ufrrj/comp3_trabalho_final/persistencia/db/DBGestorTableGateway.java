package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.GestorTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DBGestorTableGateway implements GestorTableGateway {
    private Connection dbConn;

    private PreparedStatement findByCpfStmt;

    private PreparedStatement insertUsuarioStmt;

    private PreparedStatement insertFuncionarioStmt;

    private PreparedStatement insertGestorStmt;

    public DBGestorTableGateway() throws SQLException {
        this.dbConn = DatabaseConnectionSingleton.getInstance().getConnection();

        this.findByCpfStmt = dbConn.prepareStatement(
                "SELECT Usuario.id, cpf, nome, senha FROM Usuario INNER JOIN Funcionario ON Usuario.id = Funcionario.id INNER JOIN Gestor ON Funcionario.id = Gestor.id"
        );

        this.insertUsuarioStmt = dbConn.prepareStatement(
                "INSERT INTO Usuario (id, cpf, nome, senha) VALUES (DEFAULT, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS
        );

        this.insertFuncionarioStmt = dbConn.prepareStatement(
                "INSERT INTO FUNCIONARIO (id) VALUES (?)"
        );

        this.insertGestorStmt = dbConn.prepareStatement(
                "INSERT INTO Gestor (id) VALUES (?)"
        );
    }

    @Override
    public Optional<UsuarioDTO> findGestorByCpf(String cpf) throws SQLException {
        findByCpfStmt.clearParameters();

        findByCpfStmt.setString(1, cpf);

        ResultSet rs = findByCpfStmt.executeQuery();

        if (rs.next()) {
            UsuarioDTO returnDto = new UsuarioDTO.UsuarioDTOBuilder()
                    .setId(rs.getInt(1))
                    .setCpf(rs.getString(2))
                    .setNome(rs.getString(2))
                    .setSenha(rs.getString(3))
                    .build();

            return Optional.of(returnDto);
        }

        return Optional.empty();
    }

    @Override
    public int insert(UsuarioDTO gestor) throws SQLException {
        insertUsuarioStmt.clearParameters();
        insertUsuarioStmt.setString(1, gestor.getCpf());
        insertUsuarioStmt.setString(2, gestor.getNome());
        insertUsuarioStmt.setString(3, gestor.getSenha());
        insertUsuarioStmt.executeUpdate();

        ResultSet rs = insertUsuarioStmt.getGeneratedKeys();
        rs.next();
        int generatedId = rs.getInt(1);

        insertFuncionarioStmt.clearParameters();
        insertFuncionarioStmt.setInt(1, generatedId);
        insertFuncionarioStmt.executeUpdate();

        insertGestorStmt.clearParameters();
        insertGestorStmt.setInt(1, generatedId);
        insertGestorStmt.executeUpdate();

        return generatedId;
    }
}
