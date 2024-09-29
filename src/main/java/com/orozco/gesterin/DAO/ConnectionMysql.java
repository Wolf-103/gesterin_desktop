package com.orozco.gesterin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class ConnectionMysql {

    private Connection conn;
    private String user = "root";
    private String password = "Donna*103";
    private String host = "localhost";
    private String port = "3306";
    private String database = "gesterindb";

    private String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?serverTimezone=UTC";

    public ConnectionMysql() {
        // El constructor no abre la conexión
    }

    public Connection getConn() {
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException ex) {
            System.out.println("Connection error: " + ex.getMessage());
            return null; // Devuelve null si no puede obtener la conexión
        }
    }
}
