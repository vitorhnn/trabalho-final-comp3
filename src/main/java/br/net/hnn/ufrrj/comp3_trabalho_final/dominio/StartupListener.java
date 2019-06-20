package br.net.hnn.ufrrj.comp3_trabalho_final.dominio;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db.DBSolicitacaoMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db.DatabaseConnectionSingleton;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.mock.MockMuseuTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.mock.MockUsuarioTableGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StartupListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("context initialized");
        URL resource = this.getClass().getResource("startup.sql");
        try {
            String fileContents = new String(Files.readAllBytes(Paths.get(resource.toURI())));

            String[] statements = fileContents.split("🤔");
            Connection con = DatabaseConnectionSingleton.getInstance().getConnection();

            for (String statement : statements) {
                PreparedStatement ps = con.prepareStatement(statement);
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            if (ex.getSQLState().equals("X0Y32")) {
                logger.info("some table already existed. not that it really matters");
            } else {
                logger.error("some sql problem");
                throw new RuntimeException(ex);
            }
        } catch (IOException | URISyntaxException ex) {
            logger.error("failed to seed initial db structure");
            throw new RuntimeException(ex);
        }

        logger.info("adding table gateways to provider");

        ServiceLocator sl = ServiceLocator.getInstance();

        try {
            sl.provide(new MockMuseuTableGateway());
            sl.provide(new MockUsuarioTableGateway());
            sl.provide(new DBSolicitacaoMuseuTableGateway());
        } catch (SQLException ex) {
            logger.error("failed to provide one of the table gateways");
            throw new RuntimeException(ex);
        }

        logger.info("startup done");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseConnectionSingleton.getInstance().shutdown();
    }
}
