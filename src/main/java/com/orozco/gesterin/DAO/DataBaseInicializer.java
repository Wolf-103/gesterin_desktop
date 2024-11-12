package com.orozco.gesterin.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 25 oct. 2024
 * @description Sistema GESTERIN
 */
public class DataBaseInicializer {

    private final ConnectionMysql connection;

    public DataBaseInicializer(ConnectionMysql connectionMysql) {
        this.connection = connectionMysql;
    }

    public void initializeDatabase() {
        if (!areTablesCreated()) {
            createTablesFromSQLFile("/database/crear_tablas.sql");
            insertTestDataFromSQLFile("/database/insertar_datos.sql");
        }
    }

    /**
     * Verificamos si existen tablas intentando acceder a una de las tablas
     * principales la de usuario
     *
     * @return
     */
    private boolean areTablesCreated() {
        try (Statement statement = connection.getConn().createStatement()) {
            statement.executeQuery("SELECT 1 FROM usuarios LIMIT 1");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private void createTablesFromSQLFile(String filePath) {
        executeSQLFile(filePath);
    }

    private void insertTestDataFromSQLFile(String filePath) {
        executeSQLFile(filePath);
    }

    private void executeSQLFile(String filePath) {
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath))); 
                    Statement statement = connection.getConn().createStatement();) {
                StringBuilder sql = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty() || line.startsWith("--")) {
                        continue;
                    }
                    sql.append(line);
                    if (line.endsWith(";")) {
                        statement.executeUpdate(sql.toString());
                        sql.setLength(0);
                    }
                }
            }
        } catch (IOException | SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

}
