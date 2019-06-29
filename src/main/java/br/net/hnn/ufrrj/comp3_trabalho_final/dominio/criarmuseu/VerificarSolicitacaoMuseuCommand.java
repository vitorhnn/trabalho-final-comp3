package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoInvalidaException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoNaoExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VerificarSolicitacaoMuseuCommand implements Command<SolicitacaoMuseuDTO, Exception> {
    private SolicitacaoMuseuTableGateway solicitacaoMuseuTableGateway = ServiceLocator.getInstance().getSolicitacaoMuseuTableGateway();

    private int solicitacaoId;

    public VerificarSolicitacaoMuseuCommand(int solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    @Override
    public SolicitacaoMuseuDTO execute() throws Exception {
        SolicitacaoMuseuDTO solicitacao = solicitacaoMuseuTableGateway
                .getSolicitacaoMuseuById(solicitacaoId)
                .orElseThrow(() -> new SolicitacaoNaoExisteException(solicitacaoId));

        if (solicitacao.getNome().trim().equals("")) {
            throw new SolicitacaoInvalidaException(solicitacao, "Nome do museu vazio!");
        }
        if (solicitacao.getNomeGestor().trim().equals("")) {
            throw new SolicitacaoInvalidaException(solicitacao, "Nome do gestor vazio!");
        }

        if (solicitacao.getCidade().trim().equals("")) {
            throw new SolicitacaoInvalidaException(solicitacao, "Nome da cidade vazio!");
        }

        if (solicitacao.getEstado().trim().equals("")) {
            throw new SolicitacaoInvalidaException(solicitacao, "Nome do estado vazio!");
        }

        try {
            LocalDate.parse(solicitacao.getDataCriacao(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException dtpe) {
            throw new SolicitacaoInvalidaException(solicitacao, "Data inválida!");
        }


        return solicitacao;
    }
}
