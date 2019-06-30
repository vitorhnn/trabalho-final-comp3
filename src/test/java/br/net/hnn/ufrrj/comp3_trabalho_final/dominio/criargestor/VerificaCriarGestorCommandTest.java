package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.GestorInvalidoException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.GestorJaExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.mock.MockUsuarioTableGateway;
import org.junit.Before;
import org.junit.Test;

import static br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.VerificaCriarGestorCommand.isValidCpf;
import static br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.VerificaCriarGestorCommand.isValidSenha;
import static org.junit.Assert.*;

public class VerificaCriarGestorCommandTest {
    private MockUsuarioTableGateway mock;

    @Before
    public void setUp() throws Exception {
        mock = new MockUsuarioTableGateway();
        ServiceLocator.getInstance().provide(mock);
    }

    @Test(expected = GestorJaExisteException.class)
    public void testUsuarioExistente() throws Exception {
        SolicitacaoMuseuDTO solicitacao = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCpfGestor("63066661253")
                .build();

        new VerificaCriarGestorCommand(solicitacao).execute();
    }

    @Test
    public void testNomeVazio() throws Exception {
        SolicitacaoMuseuDTO solicitacao = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCpfGestor("23786695008")
                .setNomeGestor("         ")
                .build();

        try {
            new VerificaCriarGestorCommand(solicitacao).execute();
            fail("Era esperado uma exceção do tipo GestorInvalidoException");
        } catch (GestorInvalidoException gie) {
            assertEquals("Nome vazio!", gie.getMotivo());
        }
    }

    @Test
    public void testCpfVazio() throws Exception {
        SolicitacaoMuseuDTO solicitacao = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setNomeGestor("nome do gestor")
                .setCpfGestor("   ")
                .build();

        try {
            new VerificaCriarGestorCommand(solicitacao).execute();
            fail("Era esperado uma exceção do tipo GestorInvalidoException");
        } catch (GestorInvalidoException gie) {
            assertEquals("CPF vazio!", gie.getMotivo());
        }
    }

    @Test
    public void testSenhaVazia() throws Exception {
        SolicitacaoMuseuDTO solicitacao = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setNomeGestor("nome do gestor")
                .setCpfGestor("23786695008")
                .setSenhaGestor("  ")
                .build();

        try {
            new VerificaCriarGestorCommand(solicitacao).execute();
            fail("Era esperado uma exceção do tipo GestorInvalidoException");
        } catch (GestorInvalidoException gie) {
            assertEquals("Senha vazia!", gie.getMotivo());
        }
    }

    @Test
    public void testCpfInvalido() throws Exception {
        SolicitacaoMuseuDTO solicitacao = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCpfGestor("1234")
                .setNomeGestor("nome")
                .setSenhaGestor("senha")
                .build();

        try {
            new VerificaCriarGestorCommand(solicitacao).execute();
            fail("Era esperado uma exceção do tipo GestorInvalidoException");
        } catch (GestorInvalidoException gie) {
            assertEquals("CPF inválido!", gie.getMotivo());
        }
    }

    @Test
    public void testSenhaInvalida() throws Exception {
        SolicitacaoMuseuDTO solicitacao = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCpfGestor("23786695008")
                .setNomeGestor("nome")
                .setSenhaGestor("senha obviamente invalida")
                .build();

        try {
            new VerificaCriarGestorCommand(solicitacao).execute();
            fail("Era esperado uma exceção do tipo GestorInvalidoException");
        } catch (GestorInvalidoException gie) {
            assertEquals("Senha inválida!", gie.getMotivo());
        }
    }

    @Test
    public void testMundoFeliz() throws Exception {
        String cpf = "23786695008";
        SolicitacaoMuseuDTO solicitacao = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCpfGestor(cpf)
                .setNomeGestor("nome")
                .setSenhaGestor("111111")
                .build();

        assertNull(new VerificaCriarGestorCommand(solicitacao).execute());
    }

    @Test
    public void testValidarCpf() {
        assertTrue(isValidCpf("63066661253"));
        assertTrue(isValidCpf("23786695008"));
        assertTrue(isValidCpf("15858608086"));
        assertTrue(isValidCpf("56485336087"));

        assertFalse(isValidCpf("11111111111"));
        assertFalse(isValidCpf("12345678912"));
        assertFalse(isValidCpf("1234"));
    }

    @Test
    public void testValidarSenha() {
        assertTrue(isValidSenha("123456"));
        assertTrue(isValidSenha("123aaa"));

        assertFalse(isValidSenha(""));
        assertFalse(isValidSenha("123456aaaaaa"));
        assertFalse(isValidSenha("12345-"));
    }
}