package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
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
public class ConnectionMysqlImpl implements ConnectionMysql{

    private final String user = "root";
    private final String password = "Donna*103";
    private final String host = "localhost";
    private final String port = "3306";
    private final String database = "gesterindb";
    private final String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?serverTimezone=UTC";

    /**
     * Constructor libre (no abre la conexión al instanciar)
     */
    public ConnectionMysqlImpl() {
    }

    @Override
    public Connection getConn() {
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException ex) {
            System.out.println("Connection error: " + ex.getMessage());
            return null;
        }
    }
}
