/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orozco.gesterin.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author crist
 */
public class ConnectionMysql {

    private Connection conn;
    private String user = "wolf";
    private String password = "Donna103";
    private String host = "localhost";
    private String port = "3306";
    private String database = "db_gesterin";

    private String url = "jdbc:mysql;//" + this.host + ":" + this.port + "/" + this.database;
    private String driver = "com.mysql.jdbc.Driver";

    public ConnectionMysql() {
        try {
            Class.forName(this.driver);
            this.conn = DriverManager.getConnection(this.url, this.user, this.password);

            if (conn != null) {
                System.out.println("Connection success!!");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection error: " + ex.getMessage());
        }
    }

}
