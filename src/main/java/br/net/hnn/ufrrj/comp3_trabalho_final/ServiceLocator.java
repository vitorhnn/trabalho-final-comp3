package br.net.hnn.ufrrj.comp3_trabalho_final;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.MuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.SolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.UsuarioTableGateway;
import org.jetbrains.annotations.NotNull;

public class ServiceLocator {
    private static ServiceLocator instance = new ServiceLocator();

    private MuseuTableGateway museuTableGateway;

    private UsuarioTableGateway usuarioTableGateway;

    private SolicitacaoMuseuTableGateway solicitacaoMuseuTableGateway;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        return instance;
    }

    public void provide(@NotNull MuseuTableGateway museuTableGateway) {
        this.museuTableGateway = museuTableGateway;
    }

    public void provide(@NotNull UsuarioTableGateway usuarioTableGateway) {
        this.usuarioTableGateway = usuarioTableGateway;
    }

    public void provide(@NotNull SolicitacaoMuseuTableGateway solicitacaoMuseuTableGateway) {
        this.solicitacaoMuseuTableGateway = solicitacaoMuseuTableGateway;
    }

    public MuseuTableGateway getMuseuTableGateway() {
        return museuTableGateway;
    }

    public UsuarioTableGateway getUsuarioTableGateway() {
        return usuarioTableGateway;
    }

    public SolicitacaoMuseuTableGateway getSolicitacaoMuseuTableGateway() {
        return solicitacaoMuseuTableGateway;
    }
}
