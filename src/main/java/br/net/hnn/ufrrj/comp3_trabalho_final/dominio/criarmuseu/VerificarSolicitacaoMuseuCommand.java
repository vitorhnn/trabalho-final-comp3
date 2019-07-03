package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoInvalidaException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoNaoExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VerificarSolicitacaoMuseuCommand implements Command<SolicitacaoMuseuDTO, Exception> {
    private SolicitacaoMuseuTableGateway solicitacaoMuseuTableGateway = ServiceLocator.getInstance().getSolicitacaoMuseuTableGateway();

    private int solicitacaoId;

    private SolicitacaoMuseuDTO solicitacao;

    public VerificarSolicitacaoMuseuCommand(int solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    @Override
    public SolicitacaoMuseuDTO execute() throws Exception {
        solicitacao = solicitacaoMuseuTableGateway
                .getSolicitacaoMuseuById(solicitacaoId)
                .orElseThrow(() -> new SolicitacaoNaoExisteException(solicitacaoId));

        if (!isValidNome()) {
            throw new SolicitacaoInvalidaException(solicitacao, "Nome do museu inválido!");
        }

        if (!isValidCidade()) {
            throw new SolicitacaoInvalidaException(solicitacao, "Nome da cidade inválido!");
        }

        if (!isValidEstado()) {
            throw new SolicitacaoInvalidaException(solicitacao, "Nome do estado inválido!");
        }

        if (!isValidDataCriacao()) {
            throw new SolicitacaoInvalidaException(solicitacao, "Data inválida!");
        }

        return solicitacao;
    }

    protected void buscarSolicitacao() throws SQLException, SolicitacaoNaoExisteException {
        solicitacao = solicitacaoMuseuTableGateway
                .getSolicitacaoMuseuById(solicitacaoId)
                .orElseThrow(() -> new SolicitacaoNaoExisteException(solicitacaoId));
    }

    protected boolean isValidNome() {
        String nome = solicitacao.getNome().trim();
        return !nome.isEmpty();
    }

    protected boolean isValidCidade() {
        String cidade = solicitacao.getCidade().trim();
        return !cidade.isEmpty();
    }

    protected boolean isValidEstado() {
        String estado = solicitacao.getEstado().trim();
        return !estado.isEmpty();
    }

    protected boolean isValidDataCriacao() {
        String dataCriacao = solicitacao.getDataCriacao();

        try {
            LocalDate.parse(dataCriacao, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }
}
