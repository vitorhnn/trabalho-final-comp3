package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionSingleton {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionSingleton.class);

    private static DatabaseConnectionSingleton instance;

    public static DatabaseConnectionSingleton getInstance() {
        if (instance == null) {
            try {
                instance = new DatabaseConnectionSingleton();
            } catch (Exception ex) {
                throw new RuntimeException("Failed to initialize database singleton");
            }
        }

        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:derby:memory:teste;create=true");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
