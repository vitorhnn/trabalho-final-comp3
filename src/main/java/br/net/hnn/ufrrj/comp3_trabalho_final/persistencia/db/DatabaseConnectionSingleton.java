package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionSingleton {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionSingleton.class);

    private static DatabaseConnectionSingleton instance = new DatabaseConnectionSingleton();

    private DatabaseConnectionSingleton() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        } catch (Exception e) {
            logger.error("failed to initialize derby");
            throw new RuntimeException("failed to initialize derby");
        }
    }

    public static DatabaseConnectionSingleton getInstance() {
        return instance;
    }

    public Connection getConnection() {
        logger.debug("acquiring connection");
        try {
            return DriverManager.getConnection("jdbc:derby:memory:teste;create=true");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
