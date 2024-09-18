package com.orozco.gesterin.gui;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class JFPrincipal extends javax.swing.JFrame {

    public JFPrincipal() {
        initComponents();
          this.setLocationRelativeTo(null);
        //iniciar en modo maximizado
        this.setExtendedState(JFPrincipal.MAXIMIZED_BOTH);
    }

    public void ingresarAUnMnu() {
        this.jMnuInicio.setEnabled(false);
        this.jMnuGestionPaciente.setEnabled(false);
    }

    public void salirAlMnuPpal() {
        this.jMnuInicio.setEnabled(true);
        this.jMnuGestionPaciente.setEnabled(true);
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
        jMnuExit = new javax.swing.JMenuItem();
        jMnuGestionPaciente = new javax.swing.JMenu();
        jMniGestionPaciente = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 711, Short.MAX_VALUE)
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );

        jMnuInicio.setText("Inicio");

        jMnuExit.setText("Salir");
        jMnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuExitActionPerformed(evt);
            }
        });
        jMnuInicio.add(jMnuExit);

        jMenuBar1.add(jMnuInicio);

        jMnuGestionPaciente.setText("Gestion Pacientes");

        jMniGestionPaciente.setText("Gestión");
        jMniGestionPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMniGestionPacienteActionPerformed(evt);
            }
        });
        jMnuGestionPaciente.add(jMniGestionPaciente);

        jMenuBar1.add(jMnuGestionPaciente);

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

    private void jMniGestionPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMniGestionPacienteActionPerformed

        iFGestionPacientes gestionPacientes = new iFGestionPacientes(this.jDesktop, this);
        UtilGUI.openInternalFrame(this.jDesktop, gestionPacientes);
        this.ingresarAUnMnu();
    }//GEN-LAST:event_jMniGestionPacienteActionPerformed

    private void jMnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMnuExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMniGestionPaciente;
    private javax.swing.JMenuItem jMnuExit;
    private javax.swing.JMenu jMnuGestionPaciente;
    private javax.swing.JMenu jMnuInicio;
    // End of variables declaration//GEN-END:variables
}
