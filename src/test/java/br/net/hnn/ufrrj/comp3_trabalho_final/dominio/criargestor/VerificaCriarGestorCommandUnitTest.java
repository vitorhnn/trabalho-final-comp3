package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class VerificaCriarGestorCommandUnitTest {
    @Test
    public void testCpfValido() {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCpfGestor("63066661253");
        assertTrue(new VerificaCriarGestorCommand(builder.build()).isValidCpf());

        builder.setCpfGestor("23786695008");
        assertTrue(new VerificaCriarGestorCommand(builder.build()).isValidCpf());

        builder.setCpfGestor("15858608086");
        assertTrue(new VerificaCriarGestorCommand(builder.build()).isValidCpf());

        builder.setCpfGestor("56485336087");
        assertTrue(new VerificaCriarGestorCommand(builder.build()).isValidCpf());
    }

    @Test
    public void testCpfInvalido() {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCpfGestor("11111111111");
        assertFalse(new VerificaCriarGestorCommand(builder.build()).isValidCpf());

        builder.setCpfGestor("12345678912");
        assertFalse(new VerificaCriarGestorCommand(builder.build()).isValidCpf());

        builder.setCpfGestor("1234");
        assertFalse(new VerificaCriarGestorCommand(builder.build()).isValidCpf());
    }

    @Test
    public void testSenhaValida() {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setSenhaGestor("123456");

        assertTrue(new VerificaCriarGestorCommand(builder.build()).isValidSenha());

        builder.setSenhaGestor("123aaa");
        assertTrue(new VerificaCriarGestorCommand(builder.build()).isValidSenha());
    }

    @Test
    public void testSenhaInvalida() {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setSenhaGestor("");

        assertFalse(new VerificaCriarGestorCommand(builder.build()).isValidSenha());

        builder.setSenhaGestor("senha bem longa");
        assertFalse(new VerificaCriarGestorCommand(builder.build()).isValidSenha());

        builder.setSenhaGestor("senha com um caractere zoado ##");
        assertFalse(new VerificaCriarGestorCommand(builder.build()).isValidSenha());
    }

    @Test
    public void testNomeValido() {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setNomeGestor("João da Silva");

        assertTrue(new VerificaCriarGestorCommand(builder.build()).isValidNome());
    }

    @Test
    public void testNomeInvalido() {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setNomeGestor("   ");

        assertFalse(new VerificaCriarGestorCommand(builder.build()).isValidNome());
    }
}
