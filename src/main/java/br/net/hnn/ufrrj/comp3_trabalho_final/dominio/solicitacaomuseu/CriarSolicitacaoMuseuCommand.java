package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.solicitacaomuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

import java.sql.SQLException;
import java.time.LocalDate;

public class CriarSolicitacaoMuseuCommand implements Command<Void, SQLException> {
    private SolicitacaoMuseuTableGateway tableGateway = ServiceLocator.getInstance().getSolicitacaoMuseuTableGateway();

    private SolicitacaoMuseuDTO pendingInsert;

    public CriarSolicitacaoMuseuCommand(
            String nome,
            String dataCriacao,
            String cidade,
            String estado,
            String cpfGestor,
            String nomeGestor,
            String senhaGestor
    ) {
        pendingInsert = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setNome(nome)
                .setDataCriacao(dataCriacao)
                .setCidade(cidade)
                .setEstado(estado)
                .setCpfGestor(cpfGestor)
                .setNomeGestor(nomeGestor)
                .setSenhaGestor(senhaGestor)
                .build();
    }

    @Override
    public Void execute() throws SQLException {
        tableGateway.insert(pendingInsert);

        return null;
    }
}
