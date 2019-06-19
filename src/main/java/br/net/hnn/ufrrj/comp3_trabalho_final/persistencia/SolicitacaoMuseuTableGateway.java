package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

import java.util.Optional;

public interface SolicitacaoMuseuTableGateway {
    Optional<SolicitacaoMuseuDTO> getSolicitacaoMuseuById(int id);

    void insert(SolicitacaoMuseuDTO dto);
}
