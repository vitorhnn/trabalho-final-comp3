package br.net.hnn.ufrrj.comp3_trabalho_final.dominio;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class StartupListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("context initialized");
        logger.info("adding table gateways to provider");

        ServiceLocator sl = ServiceLocator.getInstance();

        try {
            sl.provide(new DBMuseuTableGateway());
            sl.provide(new DBUsuarioTableGateway());
            sl.provide(new DBSolicitacaoMuseuTableGateway());
            sl.provide(new DBGestorTableGateway());
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
