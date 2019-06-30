package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.GestorTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.sql.SQLException;

public class TransformaUsuarioGestorCommand implements Command<Void, SQLException> {
    private GestorTableGateway gestorTableGateway = ServiceLocator.getInstance().getGestorTableGateway();

    private UsuarioDTO usuario;

    public TransformaUsuarioGestorCommand(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public Void execute() throws SQLException {
        gestorTableGateway.promoveUsuario(usuario.getId());
        return null;
    }
}
