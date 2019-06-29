package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.GestorInvalidoException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.GestorJaExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.GestorTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.UsuarioTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.sql.SQLException;
import java.util.Optional;

public class VerificaCriarGestorCommand implements Command<Void, Exception> {
    private SolicitacaoMuseuDTO solicitacao;

    private UsuarioTableGateway usuarioTableGateway = ServiceLocator.getInstance().getUsuarioTableGateway();

    private GestorTableGateway gestorTableGateway = ServiceLocator.getInstance().getGestorTableGateway();

    public VerificaCriarGestorCommand(SolicitacaoMuseuDTO solicitacao) {
        this.solicitacao = solicitacao;
    }

    @Override
    public Void execute() throws SQLException, GestorJaExisteException, GestorInvalidoException {
        Optional<UsuarioDTO> previamenteCadastrado = usuarioTableGateway.findUsuarioByCpf(solicitacao.getCpfGestor());

        if (previamenteCadastrado.isPresent()) {
            throw new GestorJaExisteException(previamenteCadastrado.get());
        }

        if (solicitacao.getNomeGestor().trim().isEmpty()) {
            throw new GestorInvalidoException(solicitacao, "Nome vazio!");
        }

        if (solicitacao.getCpfGestor().trim().isEmpty()) {
            throw new GestorInvalidoException(solicitacao, "CPF vazio!");
        }

        if (solicitacao.getSenhaGestor().trim().isEmpty()) {
            throw new GestorInvalidoException(solicitacao, "Senha vazia");
        }

        return null;
    }
}
