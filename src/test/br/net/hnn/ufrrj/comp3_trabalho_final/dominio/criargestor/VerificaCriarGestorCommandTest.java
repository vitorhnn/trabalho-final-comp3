package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import org.junit.Test;

import static br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.VerificaCriarGestorCommand.isValidCpf;
import static br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.VerificaCriarGestorCommand.isValidSenha;
import static org.junit.Assert.*;

public class VerificaCriarGestorCommandTest {
    @Test
    public void testValidarCpf() {
        assertTrue(isValidCpf("63066661253"));

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