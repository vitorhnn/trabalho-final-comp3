package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.UsuarioTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DBUsuarioTableGateway implements UsuarioTableGateway {
    public static final Logger logger = LoggerFactory.getLogger(DBUsuarioTableGateway.class);

    private Connection dbConn;

    private PreparedStatement findByCpfStmt;

    public DBUsuarioTableGateway() throws SQLException {
        dbConn = DatabaseConnectionSingleton.getInstance().getConnection();

        findByCpfStmt = dbConn.prepareStatement(
                "SELECT id, cpf, nome, senha FROM Usuario WHERE cpf = ?"
        );
    }

    @Override
    public Optional<UsuarioDTO> findUsuarioByCpf(String cpf) throws SQLException {
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
}
