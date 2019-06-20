package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

import java.sql.SQLException;
import java.util.List;

public class ListarSolicitacoesMuseuCommand implements Command<List<SolicitacaoMuseuDTO>, SQLException> {
    private SolicitacaoMuseuTableGateway tableGateway = ServiceLocator.getInstance().getSolicitacaoMuseuTableGateway();

    @Override
    public List<SolicitacaoMuseuDTO> execute() throws SQLException {
        return tableGateway.findAll();
    }
}
