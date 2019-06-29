package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import org.junit.Test;

import static br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.VerificaCriarGestorCommand.isValidCpf;
import static org.junit.Assert.*;

public class VerificaCriarGestorCommandTest {
    @Test
    public void testValidCpf() {
        assertTrue(isValidCpf("63066661253"));
    }

    @Test
    public void testInvalidCpf() {
        assertFalse(isValidCpf("11111111111"));
        assertFalse(isValidCpf("12345678912"));
    }
}