package com.orozco.gesterin.vista;

import com.orozco.gesterin.controller.AuthenticateController;
import com.orozco.gesterin.exception.AuthenticateException;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.exception.FieldEmptyException;
import com.orozco.gesterin.model.Usuario;
import com.orozco.gesterin.service.Implement.AuhtenticateServiceImpl;
import com.orozco.gesterin.utils.AppConstants;
import com.orozco.gesterin.vista.validations.CustomDocumentFilter;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 5 oct. 2024
 * @description Sistema GESTERIN
 */
public class JFLoguin extends javax.swing.JFrame {

    ImageIcon logo;
    private boolean isPasswordVisible = false;
    private final AuthenticateController authenticateController;

    public JFLoguin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.loadImage();
        this.initFields();
        this.authenticateController = new AuthenticateController(new AuhtenticateServiceImpl());
    }

    private void loadImage() {
        try {
            InputStream inpStrIMG = getClass().getResourceAsStream("/img/FC logo HD.jpg");
            this.logo = new ImageIcon(ImageIO.read(inpStrIMG));
            this.setIconImage(new ImageIcon(getClass().getResource("/img/fc.jpg")).getImage());
        } catch (IOException ex) {
            System.out.println("""
                               Error al Cargar Imagen. 
                                Hubo un problema al tratar de cargar una imagen de sistema.""");
        }
        this.lblLogo.setIcon(new ImageIcon(logo.getImage().getScaledInstance(this.lblLogo.getWidth(),
                lblLogo.getHeight(), Image.SCALE_DEFAULT)));
    }

    public void initFields() {
        ((AbstractDocument) this.txtUsuario.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.NAME_LASTNAME_MAX,
                        AppConstants.PATTERN_USERNAME_COMPILE)
                );
        this.txtPassword.setText("");
    }

    private void hiddenPassword() {
        if (this.isPasswordVisible) {
            this.txtPassword.setEchoChar('*');
            this.btnHidenPasswword.setText("Mostrar");
        } else {
            this.txtPassword.setEchoChar((char) 0);
            this.btnHidenPasswword.setText("Ocultar");
        }
        this.isPasswordVisible = !this.isPasswordVisible;
    }

    public boolean validateField() {
        try {
            if (txtUsuario.getText().trim().isEmpty()) {
                txtUsuario.requestFocus();
                throw new FieldEmptyException(4012, "Campo NOMBRE DE USUARIO vacío", "Debe cargar el nombre de usuario para continuar.");
            } else if (new String(txtPassword.getPassword()).trim().isEmpty()) {
                txtPassword.requestFocus();
                throw new FieldEmptyException(4012, "Campo CONTRASEÑA vacío", "Debe cargar CONTRASEÑA del usuario para continuar");
            } else {
                if (!AppConstants.PATTERN_PASSWORD_COMPILLE.matcher(new String(this.txtPassword.getPassword())).matches()) {
                    txtPassword.requestFocus();
                    throw new FieldEmptyException(4012, "Campo CONTRASEÑA no tiene el formato correcto",
                            "La CONTRASEÑA puede contener letras mayusculas y minusculas, números y guiones: bajos y medios, tambien asteriscos.");
                }
                return true;
            }
        } catch (FieldEmptyException ex) {
            ControllerExceptionHandler.handleError(ex, "Inicio de Sesión");
            return false;
        }
    }

    private void login() {
//        Usuario us = this.authenticateController.autenticate("wolf103", "Donna103");
//        Usuario us = this.authenticateController.autenticate("geraProf", "Gerardo901");
//        new JFPrincipal(us).setVisible(true);
//        this.dispose();
        if (this.validateField()) {
            Usuario us = this.authenticateController.autenticate(this.txtUsuario.getText(), new String(this.txtPassword.getPassword()));
            if (us != null) {
                if (us.getEstado()) {
                    new JFPrincipal(us).setVisible(true);
                    this.dispose();
                } else {
                    ControllerExceptionHandler.handleError(
                            new AuthenticateException(403, "Usuario Inactivo",
                                    "El usuario se encuentra inactivo. Para más información contacte a su administrador."),
                            "Inicio de Sesión");
                }
            } else {
                ControllerExceptionHandler.handleError(
                        new AuthenticateException(403, "Credenciales Invalidas",
                                "La combinacion de nombre de usuario y contraseña no pertenecen a ningún usuario registrado, por favor, verifique los datos ingresados e intente nuevamente."),
                        "Inicio de Sesión");
            }
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

        jPanFields = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        btnHidenPasswword = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanFields.setBackground(new java.awt.Color(255, 255, 255));
        jPanFields.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanFields.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanFields.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 49, 450, 185));

        lblTitle.setBackground(new java.awt.Color(0, 0, 0));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 51, 51));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("INICIO DE SESIÓN");
        lblTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanFields.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 485, 37));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(51, 51, 51));
        lblUsuario.setText("Nombre Usuario");
        lblUsuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 17, 170, 30));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(51, 51, 51));
        lblPassword.setText("Contraseña");
        lblPassword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 60, 170, 30));

        txtPassword.setText("jPasswordField1");
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 61, 155, 30));

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 18, 261, 30));

        btnHidenPasswword.setBackground(new java.awt.Color(0, 0, 153));
        btnHidenPasswword.setForeground(new java.awt.Color(255, 255, 255));
        btnHidenPasswword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Consulta Verde 25x25.png"))); // NOI18N
        btnHidenPasswword.setText("Mostrar");
        btnHidenPasswword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHidenPasswwordActionPerformed(evt);
            }
        });
        jPanel1.add(btnHidenPasswword, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 61, -1, -1));

        jPanFields.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 490, 110));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIngresar.setBackground(new java.awt.Color(0, 0, 0));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Aprobar 25x25.png"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel2.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 6, 160, 46));

        jPanFields.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 359, 490, -1));

        getContentPane().add(jPanFields, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        this.login();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnHidenPasswwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHidenPasswwordActionPerformed
        this.hiddenPassword();
    }//GEN-LAST:event_btnHidenPasswwordActionPerformed

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        if (txtPassword.getPassword().length >= AppConstants.PASSWORD_MAX) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        evt.setSource((char) KeyEvent.VK_CLEAR);
        this.txtPassword.requestFocus();
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        evt.setSource((char) KeyEvent.VK_CLEAR);
        this.btnIngresar.doClick();
    }//GEN-LAST:event_txtPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHidenPasswword;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JPanel jPanFields;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
