package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db.DBSolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db.DatabaseConnectionSingleton;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;

import java.io.InputStream;
import java.sql.Connection;

public class VerificaCriarGestorCommandIntegrationTest extends DatabaseTestCase {
    private DBSolicitacaoMuseuTableGateway gateway;

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        return new DatabaseConnection(DatabaseConnectionSingleton.getInstance().getConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        InputStream stream = this.getClass().getResourceAsStream("dataset.xml");
        return new XmlDataSet(stream);
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        gateway = new DBSolicitacaoMuseuTableGateway();
        return super.getSetUpOperation();
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        gateway.shutdown();

        Connection con = getConnection().getConnection();
        con.createStatement().execute("ALTER TABLE Solicitacao ALTER COLUMN id RESTART WITH 1");

        return DatabaseOperation.NONE;
    }

    public void testCpfValido() throws Exception {
        SolicitacaoMuseuDTO solicitacao = gateway.getSolicitacaoMuseuById(1).orElseThrow(RuntimeException::new);

        assertTrue(new VerificaCriarGestorCommand(solicitacao).isValidCpf());
    }
}
