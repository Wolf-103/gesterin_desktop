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

    /**
     * Método que establece y retorna la conexión a la base de datos.
     * Utiliza try-with-resources para asegurar el cierre de la conexión en caso de error.
     */
    @Override
    public Connection getConn() {
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException ex) {
            System.out.println("Error al establecer conexiòn. Detalle: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Método que cierra la conexión para liberar recursos.
     * @param conn Conexión a cerrar.
     */
    @Override
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException ex) {
                System.out.println("Error al intentar cerrar la conexiòn. Detalle: " + ex.getMessage());
            }
        }
    }
}
