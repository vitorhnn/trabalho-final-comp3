package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.GestorTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.sql.SQLException;

public class CriarGestorCommand implements Command<UsuarioDTO, SQLException> {
    private GestorTableGateway gestorTableGateway = ServiceLocator.getInstance().getGestorTableGateway();

    private SolicitacaoMuseuDTO solicitacao;

    public CriarGestorCommand(SolicitacaoMuseuDTO solicitacao) {
        this.solicitacao = solicitacao;
    }

    @Override
    public UsuarioDTO execute() throws SQLException {
        UsuarioDTO dto = new UsuarioDTO.UsuarioDTOBuilder()
                .setNome(solicitacao.getNomeGestor())
                .setCpf(solicitacao.getCpfGestor())
                .setSenha(solicitacao.getSenhaGestor())
                .build();

        int id = gestorTableGateway.insert(dto);

        return new UsuarioDTO.UsuarioDTOBuilder()
                .setId(id)
                .setNome(solicitacao.getNomeGestor())
                .setCpf(solicitacao.getCpfGestor())
                .setSenha(solicitacao.getSenhaGestor())
                .build();
    }
}
