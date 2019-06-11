package br.net.hnn.ufrrj.comp3_trabalho_final.dominio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StartupListener implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Class self = this.getClass();
        URL resource = self.getResource("startup.sql");
        try {
            String fileContents = new String(Files.readAllBytes(Paths.get(resource.toURI())));

            String[] statements = fileContents.split("🤔");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:derby:memory:teste;create=true");

            for (String statement : statements) {
                PreparedStatement ps = con.prepareStatement(statement);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            logger.error("Unhandled exception while reading startup sql");
            throw new RuntimeException();
        }
    }
}
