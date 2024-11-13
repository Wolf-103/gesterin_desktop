package com.orozco.gesterin.DAO;

import java.sql.Connection;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public interface ConnectionMysql {
    /**
     * Mètodo destinado a establecer la conexiòn, devuelve como resultado la clase Connection
     * si se logrò establecer la conexiòn o null sino 
     * @return Connection o null
     */
    Connection getConn();

    /**
     * La coneciòn debe cerrarse luego de ser utilizada para liberar recursos
     * tanto del lado del cliente como del lado del servidor
     * @param conn clase Connection.
     */
    void closeConnection(Connection conn);
}
