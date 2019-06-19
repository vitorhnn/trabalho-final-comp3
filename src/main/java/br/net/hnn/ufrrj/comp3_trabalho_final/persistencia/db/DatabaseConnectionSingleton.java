package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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
}
