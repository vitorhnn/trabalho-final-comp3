package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.mock.MockSolicitacaoMuseuTableGateway;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VerificarSolicitacaoMuseuCommandUnitTest {
    private MockSolicitacaoMuseuTableGateway mock;

    @Before
    public void setUp() {
        mock = new MockSolicitacaoMuseuTableGateway();
        ServiceLocator.getInstance().provide(mock);
    }

    @Test
    public void testValidNome() throws Exception {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setNome("Museu do hnn");

        int id = mock.insert(builder.build());

        VerificarSolicitacaoMuseuCommand cmd = new VerificarSolicitacaoMuseuCommand(id);
        cmd.buscarSolicitacao();

        assertTrue(cmd.isValidNome());
    }

    @Test
    public void testInvalidNome() throws Exception {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setNome("       ");

        int id = mock.insert(builder.build());

        VerificarSolicitacaoMuseuCommand cmd = new VerificarSolicitacaoMuseuCommand(id);
        cmd.buscarSolicitacao();

        assertFalse(cmd.isValidNome());
    }

    @Test
    public void testValidCidade() throws Exception {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCidade("Nova Iguaçu");

        int id = mock.insert(builder.build());

        VerificarSolicitacaoMuseuCommand cmd = new VerificarSolicitacaoMuseuCommand(id);
        cmd.buscarSolicitacao();

        assertTrue(cmd.isValidCidade());
    }

    @Test
    public void testInvalidCidade() throws Exception {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setCidade("   ");

        int id = mock.insert(builder.build());

        VerificarSolicitacaoMuseuCommand cmd = new VerificarSolicitacaoMuseuCommand(id);
        cmd.buscarSolicitacao();

        assertFalse(cmd.isValidCidade());
    }

    @Test
    public void testValidEstado() throws Exception {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setEstado("Rio de Janeiro");

        int id = mock.insert(builder.build());

        VerificarSolicitacaoMuseuCommand cmd = new VerificarSolicitacaoMuseuCommand(id);
        cmd.buscarSolicitacao();

        assertTrue(cmd.isValidEstado());
    }

    @Test
    public void testInvalidEstado() throws Exception {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setEstado("           ");

        int id = mock.insert(builder.build());

        VerificarSolicitacaoMuseuCommand cmd = new VerificarSolicitacaoMuseuCommand(id);
        cmd.buscarSolicitacao();

        assertFalse(cmd.isValidEstado());
    }

    @Test
    public void testValidDataCriacao() throws Exception {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setDataCriacao("2019-05-06");

        int id = mock.insert(builder.build());

        VerificarSolicitacaoMuseuCommand cmd = new VerificarSolicitacaoMuseuCommand(id);
        cmd.buscarSolicitacao();

        assertTrue(cmd.isValidDataCriacao());
    }

    @Test
    public void testInvalidDataCriacao() throws Exception {
        SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder builder = new SolicitacaoMuseuDTO.SolicitacaoMuseuDTOBuilder()
                .setDataCriacao("ontem");

        int id = mock.insert(builder.build());

        VerificarSolicitacaoMuseuCommand cmd = new VerificarSolicitacaoMuseuCommand(id);
        cmd.buscarSolicitacao();

        assertFalse(cmd.isValidDataCriacao());
    }
}