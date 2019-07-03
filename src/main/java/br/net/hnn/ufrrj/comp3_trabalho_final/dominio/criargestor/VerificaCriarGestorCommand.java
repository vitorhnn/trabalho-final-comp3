package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.GestorInvalidoException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.UsuarioJaExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.UsuarioTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.IntStream;

public class VerificaCriarGestorCommand implements Command<Void, Exception> {
    private SolicitacaoMuseuDTO solicitacao;

    private UsuarioTableGateway usuarioTableGateway = ServiceLocator.getInstance().getUsuarioTableGateway();

    public VerificaCriarGestorCommand(SolicitacaoMuseuDTO solicitacao) {
        this.solicitacao = solicitacao;
    }

    @Override
    public Void execute() throws SQLException, UsuarioJaExisteException, GestorInvalidoException {
        Optional<UsuarioDTO> previamenteCadastrado = usuarioTableGateway.findUsuarioByCpf(solicitacao.getCpfGestor());

        if (previamenteCadastrado.isPresent()) {
            throw new UsuarioJaExisteException(previamenteCadastrado.get());
        }

        if (solicitacao.getNomeGestor().trim().isEmpty()) {
            throw new GestorInvalidoException(solicitacao, "Nome vazio!");
        }

        if (solicitacao.getCpfGestor().trim().isEmpty()) {
            throw new GestorInvalidoException(solicitacao, "CPF vazio!");
        }

        if (solicitacao.getSenhaGestor().trim().isEmpty()) {
            throw new GestorInvalidoException(solicitacao, "Senha vazia!");
        }

        if (!isValidCpf()) {
            throw new GestorInvalidoException(solicitacao, "CPF inválido!");
        }

        if (!isValidSenha()) {
            throw new GestorInvalidoException(solicitacao, "Senha inválida!");
        }
        return null;
    }

    protected boolean isValidCpf() {
        String cpf = solicitacao.getCpfGestor().trim();
        if (cpf.length() != 11) {
            return false;
        }

        int dv1 = Character.getNumericValue(cpf.codePointAt(9));
        int dv2 = Character.getNumericValue(cpf.codePointAt(10));

        String digitosSubStr = cpf.substring(0, 9);

        int dvCalculado1 = 0;
        int dvCalculado2 = 0;

        int[] digitosArr = digitosSubStr.codePoints()
                .map(Character::getNumericValue)
                .toArray();

        if (IntStream.of(digitosArr).allMatch(x -> x == digitosArr[0])) {
            return false;
        }

        for (int i = 0; i < digitosArr.length; i++) {
            int digito = digitosArr[i];

            dvCalculado1 += digito * (10 - i);
            dvCalculado2 += digito * (11 - i);
        }


        dvCalculado1 = (dvCalculado1 * 10) % 11;

        dvCalculado1 = dvCalculado1 == 10 ? 0 : dvCalculado1;

        dvCalculado2 += dvCalculado1 * 2;

        dvCalculado2 = (dvCalculado2 * 10) % 11;

        dvCalculado2 = dvCalculado2 == 10 ? 0 : dvCalculado2;

        return dv1 == dvCalculado1 && dv2 == dvCalculado2;
    }

    protected boolean isValidSenha() {
        String senha = solicitacao.getSenhaGestor().trim();
        return senha.length() == 6 && senha.matches("[a-zA-Z0-9]*");
    }
}
