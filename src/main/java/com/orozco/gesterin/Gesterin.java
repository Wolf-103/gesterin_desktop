package com.orozco.gesterin;

import com.orozco.gesterin.gui.JFPrincipal;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author crist
 */
public class Gesterin {

    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("""
                               ERROR AL TRATAR DE CAMBIAR EL ASPECTO DEL SISTEMA. 
                                ERROR: """ + e.getMessage());
        }
//        ConnectionMysql conn = new ConnectionMysql();

//        setLogLevel(Level.ERROR);
//        LOGGER.trace("Log level trace");
//        LOGGER.debug("Log level debug");
//        LOGGER.info("Log level info");
//        LOGGER.warn("Log level warn");
//        LOGGER.error("Log level error");
//        LOGGER.fatal("Log level fatal");
        JFPrincipal principal = new JFPrincipal();
        principal.setVisible(true);
    }
}
