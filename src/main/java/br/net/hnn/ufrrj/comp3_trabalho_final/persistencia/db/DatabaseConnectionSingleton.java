package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionSingleton {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionSingleton.class);

    private static DatabaseConnectionSingleton instance = new DatabaseConnectionSingleton();

    private String dbString;

    private DatabaseConnectionSingleton() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        } catch (Exception e) {
            logger.error("failed to initialize derby");
            throw new RuntimeException("failed to initialize derby");
        }

        try {
            logger.info("opening database properties file");
            InputStream propertiesStream = this.getClass().getResourceAsStream("db.properties");
            Properties dbConnProps = new Properties();
            dbConnProps.load(propertiesStream);

            dbString = dbConnProps.getProperty("dbString");
        } catch (IOException ex) {
            logger.error("failed to load db properties");
            throw new RuntimeException("failed to load db properties");
        }

        ensureDatabaseStructure();
    }

    public static DatabaseConnectionSingleton getInstance() {
        return instance;
    }

    public Connection getConnection() {
        logger.debug("acquiring connection");
        try {
            return DriverManager.getConnection(dbString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        logger.info("shutting down database");
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("XJ015")) {
                logger.info("derby shutdown ok");
                return;
            }

            logger.error("something happened, I guess");
            logger.error(ex.toString());
        }
    }

    private void ensureDatabaseStructure() {
        URL resource = this.getClass().getResource("startup.sql");
        try {
            String fileContents = new String(Files.readAllBytes(Paths.get(resource.toURI())));

            String[] statements = fileContents.split("🤔");
            Connection con = getConnection();

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
    }
}
