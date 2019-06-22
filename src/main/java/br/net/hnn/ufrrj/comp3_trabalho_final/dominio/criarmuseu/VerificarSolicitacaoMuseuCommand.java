package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoInvalidaException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoNaoExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.MuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

public class VerificarSolicitacaoMuseuCommand implements Command<Void, Exception> {
    private SolicitacaoMuseuTableGateway solicitacaoMuseuTableGateway = ServiceLocator.getInstance().getSolicitacaoMuseuTableGateway();

    private MuseuTableGateway museuTableGateway = ServiceLocator.getInstance().getMuseuTableGateway();

    private int solicitacaoId;

    public VerificarSolicitacaoMuseuCommand(int solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    @Override
    public Void execute() throws Exception {
        SolicitacaoMuseuDTO solicitacao = solicitacaoMuseuTableGateway
                .getSolicitacaoMuseuById(solicitacaoId)
                .orElseThrow(() -> new SolicitacaoNaoExisteException(solicitacaoId));

        if (
                solicitacao.getNome().trim().equals("") ||
                solicitacao.getNomeGestor().trim().equals("") ||
                solicitacao.getCidade().trim().equals("") ||
                solicitacao.getEstado().trim().equals("")
        )
        {
            throw new SolicitacaoInvalidaException(solicitacao);
        }


        return null;
    }
}
