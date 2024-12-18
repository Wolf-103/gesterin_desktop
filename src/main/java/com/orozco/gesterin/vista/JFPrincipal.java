package com.orozco.gesterin.vista;

import com.orozco.gesterin.model.Usuario;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class JFPrincipal extends javax.swing.JFrame {

    private Usuario usaurioLog = null;

    public JFPrincipal(Usuario us) {
        initComponents();
        this.usaurioLog = us;
        this.setLocationRelativeTo(null);
        //iniciar en modo maximizado
        this.setExtendedState(JFPrincipal.MAXIMIZED_BOTH);
        this.initMnu();
    }

    public void ingresarAUnMnu() {
        this.jMnuInicio.setEnabled(false);
        this.jMnuCliente.setEnabled(false);
        this.jMnuUsuarios.setEnabled(false);
    }

    public void salirAlMnuPpal() {
        this.jMnuInicio.setEnabled(true);
        this.jMnuCliente.setEnabled(true);
        this.jMnuUsuarios.setEnabled(true);
    }

    private void loadIconMnuBar() {
        try {
            InputStream inpStrIMG = getClass().getResourceAsStream("/img/Inicio 25x25.png");
            this.jMnuInicio.setIcon(new ImageIcon(ImageIO.read(inpStrIMG)));
            inpStrIMG = getClass().getResourceAsStream("/img/Usuarios 25x25.png");
            this.jMnuUsuarios.setIcon(new ImageIcon(ImageIO.read(inpStrIMG)));
            inpStrIMG = getClass().getResourceAsStream("/img/Clientes 25x25.png");
            this.jMnuCliente.setIcon(new ImageIcon(ImageIO.read(inpStrIMG)));
            inpStrIMG = getClass().getResourceAsStream("/img/Administrar us.png");
            this.jMnuGestionUsuarios.setIcon(new ImageIcon(ImageIO.read(inpStrIMG)));
            inpStrIMG = getClass().getResourceAsStream("/img/Administrar us.png");
            this.jMniGestionCliente.setIcon(new ImageIcon(ImageIO.read(inpStrIMG)));
            inpStrIMG = getClass().getResourceAsStream("/img/Salir.png");
            this.jMnuExit.setIcon(new ImageIcon(ImageIO.read(inpStrIMG)));
            inpStrIMG = getClass().getResourceAsStream("/img/Cerrar sesion.png");
            this.jMnuLogout.setIcon(new ImageIcon(ImageIO.read(inpStrIMG)));
            this.setIconImage(new ImageIcon(getClass().getResource("/img/fc.jpg")).getImage());

        } catch (IOException ex) {
            System.out.println("""
                               Error al Cargar Imagen. 
                                Hubo un problema al tratar de cargar una imagen de sistema.""");
        }
    }

    private void initMnu() {
        this.loadIconMnuBar();

        if (!this.usaurioLog.getRol().getNombre().endsWith("ADMINISTRADOR")) {
            this.jMenuBar1.remove(this.jMnuUsuarios);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnuInicio = new javax.swing.JMenu();
        jMnuLogout = new javax.swing.JMenuItem();
        jMnuExit = new javax.swing.JMenuItem();
        jMnuUsuarios = new javax.swing.JMenu();
        jMnuGestionUsuarios = new javax.swing.JMenuItem();
        jMnuCliente = new javax.swing.JMenu();
        jMniGestionCliente = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 711, Short.MAX_VALUE)
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );

        jMnuInicio.setText("Inicio");

        jMnuLogout.setText("Cerrar Sesión");
        jMnuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuLogoutActionPerformed(evt);
            }
        });
        jMnuInicio.add(jMnuLogout);

        jMnuExit.setText("Salir");
        jMnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuExitActionPerformed(evt);
            }
        });
        jMnuInicio.add(jMnuExit);

        jMenuBar1.add(jMnuInicio);

        jMnuUsuarios.setText("Usuarios");

        jMnuGestionUsuarios.setText("Gestión");
        jMnuGestionUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuGestionUsuariosActionPerformed(evt);
            }
        });
        jMnuUsuarios.add(jMnuGestionUsuarios);

        jMenuBar1.add(jMnuUsuarios);

        jMnuCliente.setText("Clientes");

        jMniGestionCliente.setText("Gestión");
        jMniGestionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMniGestionClienteActionPerformed(evt);
            }
        });
        jMnuCliente.add(jMniGestionCliente);

        jMenuBar1.add(jMnuCliente);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMniGestionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMniGestionClienteActionPerformed
        iFGestionClientes gestionPacientes = new iFGestionClientes(this.jDesktop, this);
        UtilGUI.openInternalFrame(this.jDesktop, gestionPacientes);
        this.ingresarAUnMnu();
    }//GEN-LAST:event_jMniGestionClienteActionPerformed

    private void jMnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMnuExitActionPerformed

    private void jMnuGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuGestionUsuariosActionPerformed
        iFGestionUsuarios gestionUsuarios = new iFGestionUsuarios(this.jDesktop, this);
        UtilGUI.openInternalFrame(this.jDesktop, gestionUsuarios);
        this.ingresarAUnMnu();
    }//GEN-LAST:event_jMnuGestionUsuariosActionPerformed

    private void jMnuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuLogoutActionPerformed
        JFLoguin loguin = new JFLoguin();
        loguin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMnuLogoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMniGestionCliente;
    private javax.swing.JMenu jMnuCliente;
    private javax.swing.JMenuItem jMnuExit;
    private javax.swing.JMenuItem jMnuGestionUsuarios;
    private javax.swing.JMenu jMnuInicio;
    private javax.swing.JMenuItem jMnuLogout;
    private javax.swing.JMenu jMnuUsuarios;
    // End of variables declaration//GEN-END:variables
}
