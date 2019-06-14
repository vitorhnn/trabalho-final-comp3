package br.net.hnn.ufrrj.comp3_trabalho_final.dominio;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db.DatabaseConnectionSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class StartupListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("context initialized");
        Class self = this.getClass();
        URL resource = self.getResource("startup.sql");
        try {
            String fileContents = new String(Files.readAllBytes(Paths.get(resource.toURI())));

            String[] statements = fileContents.split("🤔");
            Connection con = DatabaseConnectionSingleton.getInstance().getConnection();

            for (String statement : statements) {
                PreparedStatement ps = con.prepareStatement(statement);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
