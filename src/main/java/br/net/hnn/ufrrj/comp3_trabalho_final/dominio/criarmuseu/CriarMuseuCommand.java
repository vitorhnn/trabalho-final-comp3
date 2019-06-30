package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.MuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.MuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CriarMuseuCommand implements Command<Void, Exception> {
    private MuseuTableGateway museuTableGateway = ServiceLocator.getInstance().getMuseuTableGateway();

    private SolicitacaoMuseuDTO solicitacao;

    private UsuarioDTO gestor;

    public CriarMuseuCommand(SolicitacaoMuseuDTO solicitacao, UsuarioDTO gestor) {
        this.solicitacao = solicitacao;
        this.gestor = gestor;
    }

    @Override
    public Void execute() throws Exception {
        MuseuDTO museu = new MuseuDTO.MuseuDTOBuilder()
                .setNome(solicitacao.getNome())
                .setCidade(solicitacao.getCidade())
                .setEstado(solicitacao.getEstado())
                .setDataCriacao(LocalDate.parse(solicitacao.getDataCriacao(), DateTimeFormatter.ISO_LOCAL_DATE))
                .setIdGestor(gestor.getId())
                .build();

        museuTableGateway.insert(museu);

        return null;
    }
}
