package com.orozco.gesterin.vista;

import com.orozco.gesterin.controller.UsuarioController;
import com.orozco.gesterin.model.Administrador;
import com.orozco.gesterin.model.Persona;
import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.model.Usuario;
import com.orozco.gesterin.vista.validations.CustomDocumentFilter;
import com.orozco.gesterin.utils.AppConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JDesktopPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public final class iFGestionUsuarios extends javax.swing.JInternalFrame {

    JFPrincipal ppal;
    JDesktopPane desktop;
    List<Persona> listaPersonas = new ArrayList<>();
    UsuarioController usuarioController;
    boolean update = false;
    Usuario usuarioSelected = null;

    /**
     * Creates new form GestionClientes
     *
     * @param esc
     * @param jppal
     */
    public iFGestionUsuarios(JDesktopPane esc, JFPrincipal jppal) {
        initComponents();
        this.ppal = jppal;
        this.desktop = esc;
        this.usuarioController = new UsuarioController();
        this.initialStatus();
        this.initFields();

        this.loadTableClientes();
    }

    public void initFields() {
        Pattern pattrenFullNames = Pattern.compile(AppConstants.PATTERN_NAME_LASTNAME_SLIM);
        ((AbstractDocument) this.txtName.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.NAME_LASTNAME_MAX,
                        pattrenFullNames)
                );
        ((AbstractDocument) this.txtLastName.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.NAME_LASTNAME_MAX,
                        pattrenFullNames)
                );
        ((AbstractDocument) this.txtTelephone.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.TELEPHONE_MAX,
                        Pattern.compile(AppConstants.PATTERN_TELEPHONE_SLIM))
                );
        ((AbstractDocument) this.txtUsuario.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.EMAIL_MAX,
                        Pattern.compile(AppConstants.PATTERN_EMAIL_VALID_CHARACTERS))
                );
    }

    public void loadForm(Persona persona, Usuario usuario) {
        this.txtName.setText(persona.getNombre() != null ? persona.getNombre() : "");
        this.txtLastName.setText(persona.getApellido()!= null ? persona.getApellido() : "");
        this.txtEmail.setText(persona.getTelefono() != null ? persona.getTelefono() : "");
        this.txtTelephone.setText(persona.getTelefono() != null ? persona.getTelefono() : "");
        this.txtUsuario.setText(persona.getEmail() != null ? persona.getEmail() : "");

        if (usuario.getEstado() != null) {
            if (usuario.getEstado()) {
                this.rBtnActive.setSelected(true);
            } else {
                this.rBtnInactive.setSelected(true);
            }
        } else {
            this.rBtnInactive.setSelected(true);
        }
    }
    
    private List<Persona> getPersonaUsuario(){
        List<Persona> listaPersonaUsuario = this.usuarioController.listAllPeopleUsers();
        return listaPersonaUsuario;
    }

    public void loadTableClientes() {
        String[] columnNames = {"ID", "NOMBRE", "APELLIDO", "EMAIL", "ROL"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.listaPersonas = this.getPersonaUsuario();
        for (Persona persona : this.listaPersonas) {
            Object[] data = new Object[columnNames.length];
            data[0] = persona.getId();
            data[1] = persona.getNombre();
            data[2] = persona.getApellido();
            data[3] = persona.getEmail();
            String rol = "";
            if (persona instanceof Administrador admin ){
                rol = admin.getUsuario().getNombre().toUpperCase();
            }else if (persona instanceof Profesional pro ){
                rol = pro.getUsuario().getNombre().toUpperCase();
            }
            data[4] = rol;
            model.addRow(data);
        }
        this.jTblUsuario.setModel(model);
    }

    public void initialStatus() {
        this.usuarioSelected = null;
        UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
        UtilGUI.borrarCamposDeComponentes(this.jPanFields);
        this.btnNuevo.setEnabled(true);
        this.btnGuardar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrupStatus = new javax.swing.ButtonGroup();
        jPopMnuTableOptions = new javax.swing.JPopupMenu();
        jMnuEdit = new javax.swing.JMenuItem();
        jPanGeneral = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jPanFields = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        txtTelephone = new javax.swing.JTextField();
        lblTelephone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        rBtnActive = new javax.swing.JRadioButton();
        rBtnInactive = new javax.swing.JRadioButton();
        cboRol = new javax.swing.JComboBox<>();
        lblRol = new javax.swing.JLabel();
        lblEspecialidad = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnCancel1 = new javax.swing.JButton();
        jpanButons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblUsuario = new javax.swing.JTable();
        jpanBtnList = new javax.swing.JPanel();
        btnClearFilter = new javax.swing.JButton();
        lblTitleListaClientes = new javax.swing.JLabel();

        jMnuEdit.setText("Editar");
        jMnuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuEditActionPerformed(evt);
            }
        });
        jPopMnuTableOptions.add(jMnuEdit);

        setClosable(true);
        setTitle("Gestión de Usuario");

        jPanGeneral.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 51, 51));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("FORMULARIO REGISTRO DE USUARIOS");
        lblTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanFields.setBackground(new java.awt.Color(204, 204, 204));

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(51, 51, 51));
        lblName.setText("Nombre");
        lblName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblLastName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLastName.setForeground(new java.awt.Color(51, 51, 51));
        lblLastName.setText("Apellido");
        lblLastName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTelephone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelephone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelephone.setText("Teléfono");
        lblTelephone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Correo Electrónico");
        lblEmail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(51, 51, 51));
        lblStatus.setText("Estado");
        lblStatus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnGrupStatus.add(rBtnActive);
        rBtnActive.setText("Activo");

        btnGrupStatus.add(rBtnInactive);
        rBtnInactive.setText("Inactivo");

        cboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "ADMINISTRADOR", "PROFESIONAL" }));
        cboRol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblRol.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRol.setForeground(new java.awt.Color(51, 51, 51));
        lblRol.setText("Rol");
        lblRol.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblEspecialidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEspecialidad.setForeground(new java.awt.Color(51, 51, 51));
        lblEspecialidad.setText("Especialidad");
        lblEspecialidad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(51, 51, 51));
        lblUsuario.setText("Nombre Usuario");
        lblUsuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(51, 51, 51));
        lblPassword.setText("Contraseña");
        lblPassword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtPassword.setText("jPasswordField1");

        btnCancel1.setText("Especialidad");
        btnCancel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanFieldsLayout = new javax.swing.GroupLayout(jPanFields);
        jPanFields.setLayout(jPanFieldsLayout);
        jPanFieldsLayout.setHorizontalGroup(
            jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanFieldsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanFieldsLayout.createSequentialGroup()
                                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanFieldsLayout.createSequentialGroup()
                                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblTelephone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                        .addComponent(lblLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                        .addComponent(lblName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                    .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanFieldsLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(rBtnActive, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rBtnInactive, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(jPanFieldsLayout.createSequentialGroup()
                        .addComponent(lblEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanFieldsLayout.setVerticalGroup(
            jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rBtnInactive, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rBtnActive, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpanButons.setBackground(new java.awt.Color(204, 204, 204));

        btnCancel.setText("Cancelar");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanButonsLayout = new javax.swing.GroupLayout(jpanButons);
        jpanButons.setLayout(jpanButonsLayout);
        jpanButonsLayout.setHorizontalGroup(
            jpanButonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanButonsLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(btnNuevo)
                .addGap(30, 30, 30)
                .addComponent(btnGuardar)
                .addGap(30, 30, 30)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanButonsLayout.setVerticalGroup(
            jpanButonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanButonsLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jpanButonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanButons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanButons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTblUsuario.setComponentPopupMenu(jPopMnuTableOptions);
        jTblUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTblUsuario);

        jpanBtnList.setBackground(new java.awt.Color(255, 255, 255));
        jpanBtnList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpanBtnList.setForeground(new java.awt.Color(255, 255, 255));

        btnClearFilter.setText("Quitar Filtros");
        btnClearFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClearFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanBtnListLayout = new javax.swing.GroupLayout(jpanBtnList);
        jpanBtnList.setLayout(jpanBtnListLayout);
        jpanBtnListLayout.setHorizontalGroup(
            jpanBtnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanBtnListLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClearFilter)
                .addContainerGap())
        );
        jpanBtnListLayout.setVerticalGroup(
            jpanBtnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanBtnListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClearFilter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTitleListaClientes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitleListaClientes.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleListaClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleListaClientes.setText("LISTA DE USUARIOS");
        lblTitleListaClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblTitleListaClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(jpanBtnList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleListaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jpanBtnList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanGeneralLayout = new javax.swing.GroupLayout(jPanGeneral);
        jPanGeneral.setLayout(jPanGeneralLayout);
        jPanGeneralLayout.setHorizontalGroup(
            jPanGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanGeneralLayout.setVerticalGroup(
            jPanGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.initialStatus();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.update = false;
        this.usuarioSelected = null;
        UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, true);
        UtilGUI.borrarCamposDeComponentes(this.jPanFields);
        this.btnNuevo.setEnabled(false);
        this.btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

//    private Persona setPersona(Persona persona) {
//        persona.setNombre(this.txtName.getText());
//        persona.setApellido(this.txtLastName.getText());
//        persona.setEmail(this.txtEmail.getText());
//        persona.setTelefono(this.txtTelephone.getText());
//        return persona;
//    }
//
//    private void newCliente() {
//        Cliente cliente = new Cliente();
//        cliente = this.setPersona(cliente);
//        boolean request = this.usuarioController.save(cliente);
//        if (request) {
//            JOptionPane.showMessageDialog(null,
//                    "Cliente Registrado con exito!!",
//                    "Exito",
//                    JOptionPane.INFORMATION_MESSAGE);
//            this.loadTableClientes();
//            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
//            this.btnNuevo.setEnabled(true);
//            this.btnGuardar.setEnabled(false);
//        }
//    }
//
//    private void updateCliente(Cliente cliente) {
//        if (cliente != null) {
//            cliente = this.setPersona(cliente);
//            boolean request = this.usuarioController.update(cliente);
//            if (request) {
//                JOptionPane.showMessageDialog(null,
//                        "Cliente actualizado con exito!!",
//                        "Exito",
//                        JOptionPane.INFORMATION_MESSAGE);
//                this.loadTableClientes();
//                UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
//                this.btnNuevo.setEnabled(true);
//                this.btnGuardar.setEnabled(false);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null,
//                    "Cliente No seleccionado",
//                    "Informacion",
//                    JOptionPane.INFORMATION_MESSAGE);
//        }
//    }

//    public boolean validateFields() {
//        boolean verificado = false;
//        try {
////------Datos Generales Cliente 
//            if (this.txtDNI.getText().equals("")) {
//                this.txtDNI.requestFocus();
//                throw new FieldEmptyException(4012, "Campo DNI vacío", "El dni no puede estar constituido por digitos repetidos.");
//            } else {
//                if (UtilGUI.validarNumerosRepetidos(this.txtDNI.getText(), "dni")) {
////                    this.jTabPanelCliente.setSelectedIndex(0);
//                    this.txtDNI.requestFocus();
//                    throw new FieldEmptyException(4012, "Campo DNI no válido", "El dni no puede estar constituido por digitos repetidos.");
//                } else {
//                    if (this.txtDNI.getText().length() < AppConstants.DNI_MIN) {
//                        this.txtDNI.requestFocus();
//                        throw new FieldEmptyException(4012, "Campo DNI no válido.", "El dni no puede tener una longitud menor a " + AppConstants.DNI_MIN + ".");
//                    } else {
//                        if (this.txtName.getText().equals("")) {
//                            this.txtName.requestFocus();
//                            throw new FieldEmptyException(4012, "Campo Nombre vacío", "Debe cargar NOMBRE del Cliente");
//                        } else {
//                            if (this.txtLastName.getText().equals("")) {
//                                this.txtLastName.requestFocus();
//                                throw new FieldEmptyException(4012, "Campo Apellido vacío", "Debe cargar APELLIDO del Cliente");
//                            } else {
//                                if (this.txtAddress.getText().equals("")) {
//                                    this.txtAddress.requestFocus();
//                                    throw new FieldEmptyException(4012, "Campo Dirección vacío", "Debe cargar DIRECCION del Cliente");
//                                } else {
//                                    if (this.txtSocialSecurity.getText().equals("")) {
//                                        this.txtSocialSecurity.requestFocus();
//                                        throw new FieldEmptyException(4012, "Campo Obra Social vacío", "Debe cargar OBRA SOCIAL del Cliente");
//                                    } else {
//                                        if (this.txtTelephone.getText().equals("")) {
//                                            this.txtTelephone.requestFocus();
//                                            throw new FieldEmptyException(4012, "Campo Teléfono vacío", "Debe cargar TELÉFONO del Cliente");
//                                        } else {
//                                            if (!this.rBtnActive.isSelected() && !this.rBtnInactive.isSelected()) {
//                                                this.rBtnActive.requestFocus();
//                                                throw new FieldEmptyException(4012, "Campo Estado no seleccionado.", "Debe seleccionar el ESTADO del Cliente");
//                                            } else {
//                                                if (this.txtUsuario.getText().equals("")) {
//                                                    this.txtUsuario.requestFocus();
//                                                    throw new FieldEmptyException(4012, "Campo Email vacío", "Debe cargar EMAIL del Cliente");
//                                                } else {
//                                                    if (!UtilGUI.validateEmail(this.txtUsuario.getText())) {
//                                                        this.txtUsuario.requestFocus();
//                                                        throw new FieldEmptyException(4012, "Campo Email no valido", """
//                                                            Verifique la informaci\u00f3n ingresada, el email debe tener el formato: 
//                                                            xxxxx...@xxxxx.xxx """);
//                                                    } else {
//                                                        verificado = true;
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (FieldEmptyException ex) {
//            ControllerExceptionHandler.handleError(ex, "Verificar Campos");
//        }
//
//        return verificado;
//    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
//        if (this.validateFields())
//            if (!this.update) {
//                this.newCliente();
//            } else {
//                this.updateCliente(this.usuarioSelected);
//            }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnClearFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearFilterActionPerformed

    private void jMnuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuEditActionPerformed

//        int row = this.jTblUsuario.getSelectedRow();
//        if (row != -1) {
//            Long idCliente = ((Long) this.jTblUsuario.getValueAt(row, 0));
//            Optional<Cliente> clienteOptional = this.listaPersonas.stream()
//                    .filter(cliente -> Objects.equals(cliente.getId(), idCliente))
//                    .findFirst();
//            if (clienteOptional.isPresent()) {
//                this.usuarioSelected = clienteOptional.get();
//                UtilGUI.borrarCamposDeComponentes(this.jPanFields);
//                UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, true);
//                this.loadForm(this.usuarioSelected);
//                this.btnNuevo.setEnabled(false);
//                this.btnGuardar.setEnabled(true);
//                this.update = true;
//            } else {
//                UtilGUI.borrarCamposDeComponentes(this.jPanFields);
//                UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
//                this.btnNuevo.setEnabled(true);
//                this.btnGuardar.setEnabled(false);
//                this.update = false;
//            }
//        }
    }//GEN-LAST:event_jMnuEditActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancel1ActionPerformed

    @Override
    public void dispose() {
        this.ppal.salirAlMnuPpal();
        super.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnClearFilter;
    private javax.swing.ButtonGroup btnGrupStatus;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboRol;
    private javax.swing.JMenuItem jMnuEdit;
    private javax.swing.JPanel jPanFields;
    private javax.swing.JPanel jPanGeneral;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopMnuTableOptions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblUsuario;
    private javax.swing.JPanel jpanBtnList;
    private javax.swing.JPanel jpanButons;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTelephone;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleListaClientes;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JRadioButton rBtnActive;
    private javax.swing.JRadioButton rBtnInactive;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelephone;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
