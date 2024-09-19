package com.orozco.gesterin.gui;

import com.orozco.gesterin.model.Paciente;
import com.orozco.gesterin.controller.PacienteController;
import com.orozco.gesterin.gui.validations.CustomDocumentFilter;
import com.orozco.gesterin.utils.AppConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public final class iFGestionPacientes extends javax.swing.JInternalFrame {

    JFPrincipal ppal;
    JDesktopPane desktop;
    List<Paciente> listPacientes = new ArrayList<>();
    PacienteController pacienteController;
    boolean update = false;
    Paciente pacienteSelected = null;

    /**
     * Creates new form GestionPacientes
     *
     * @param esc
     * @param jppal
     */
    public iFGestionPacientes(JDesktopPane esc, JFPrincipal jppal) {
        initComponents();
        this.ppal = jppal;
        this.desktop = esc;
        this.pacienteController = new PacienteController();
        this.initialStatus();
        this.initFields();

        this.loadTablePacientes();
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
    }

    public void loadForm(Paciente paciente) {
        this.txtName.setText(paciente.getFirstName() != null ? paciente.getFirstName() : "");
        this.txtLastName.setText(paciente.getLastName() != null ? paciente.getLastName() : "");
        this.txtDNI.setText(paciente.getDni() != null ? paciente.getDni() : "");
        this.txtAddress.setText(paciente.getAddress() != null ? paciente.getAddress() : "");
        this.txtSocialSecurity.setText(paciente.getSocialSecurity() != null ? paciente.getSocialSecurity() : "");
        this.txtEmail.setText(paciente.getEmail() != null ? paciente.getEmail() : "");
        this.txtTelephone.setText(paciente.getTelephone() != null ? paciente.getTelephone() : "");
        if (paciente.getStatus() != null) {
            if (paciente.getStatus().equals("ACTIVE")) {
                this.rBtnActive.setSelected(true);
            } else {
                this.rBtnInactive.setSelected(true);
            }
        } else {
            this.rBtnInactive.setSelected(true);
        }
    }

    public void loadTablePacientes() {
        String[] columnNames = {"ID", "NOMBRE", "APELLIDO", "DNI", "STATUS"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.listPacientes = this.pacienteController.getAll();
        for (Paciente paciente : this.listPacientes) {
            Object[] data = new Object[columnNames.length];
            data[0] = paciente.getId();
            data[1] = paciente.getFirstName();
            data[2] = paciente.getLastName();
            data[3] = paciente.getDni();
            data[4] = paciente.getStatus();
            model.addRow(data);
        }
        this.jTblPacientes.setModel(model);
    }

    public void initialStatus() {
        this.pacienteSelected = null;
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
        lblDNI = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtSocialSecurity = new javax.swing.JTextField();
        lblSocialSecurity = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtTelephone = new javax.swing.JTextField();
        lblTelephone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        rBtnActive = new javax.swing.JRadioButton();
        rBtnInactive = new javax.swing.JRadioButton();
        jpanButons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblPacientes = new javax.swing.JTable();
        jpanBtnList = new javax.swing.JPanel();
        btnClearFilter = new javax.swing.JButton();
        lblTitleListaPacientes = new javax.swing.JLabel();

        jMnuEdit.setText("Editar");
        jMnuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuEditActionPerformed(evt);
            }
        });
        jPopMnuTableOptions.add(jMnuEdit);

        setClosable(true);
        setTitle("Gestión de Pacientes");

        jPanGeneral.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 51, 51));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("FORMULARIO REGISTRO DE PACIENTE");
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

        lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDNI.setForeground(new java.awt.Color(51, 51, 51));
        lblDNI.setText("DNI");
        lblDNI.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSocialSecurity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSocialSecurity.setForeground(new java.awt.Color(51, 51, 51));
        lblSocialSecurity.setText("Obra Social");
        lblSocialSecurity.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(51, 51, 51));
        lblAddress.setText("Dirección");
        lblAddress.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        javax.swing.GroupLayout jPanFieldsLayout = new javax.swing.GroupLayout(jPanFields);
        jPanFields.setLayout(jPanFieldsLayout);
        jPanFieldsLayout.setHorizontalGroup(
            jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanFieldsLayout.createSequentialGroup()
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanFieldsLayout.createSequentialGroup()
                        .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSocialSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSocialSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanFieldsLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(rBtnActive, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rBtnInactive, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
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
                    .addComponent(lblDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSocialSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSocialSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rBtnInactive, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rBtnActive, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
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
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTblPacientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jTblPacientes.setComponentPopupMenu(jPopMnuTableOptions);
        jTblPacientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTblPacientes);

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

        lblTitleListaPacientes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitleListaPacientes.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleListaPacientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleListaPacientes.setText("LISTA DE PACIENTE");
        lblTitleListaPacientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblTitleListaPacientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(jpanBtnList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleListaPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        this.pacienteSelected = null;
        UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, true);
        this.btnNuevo.setEnabled(false);
        this.btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private Paciente setPaciente(Paciente paciente) {
        paciente.setFirstName(this.txtName.getText());
        paciente.setLastName(this.txtLastName.getText());
        paciente.setDni(this.txtDNI.getText());
        paciente.setSocialSecurity(this.txtSocialSecurity.getText());
        paciente.setEmail(this.txtEmail.getText());
        paciente.setStatus(this.rBtnActive.isSelected() == true ? "ACTIVE" : "INACTIVE");
        paciente.setAddress(this.txtAddress.getText());
        paciente.setTelephone(this.txtTelephone.getText());
        return paciente;
    }

    private void newPaciente() {
        Paciente paciente = new Paciente();
        paciente = this.setPaciente(paciente);
        boolean request = this.pacienteController.registrarpaciente(paciente);
        if (request) {
            JOptionPane.showMessageDialog(null,
                    "Paciente Registrado con exito!!",
                    "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            this.loadTablePacientes();
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
            this.btnNuevo.setEnabled(true);
            this.btnGuardar.setEnabled(false);
        }
    }

    private void updatePaciente(Paciente paciente) {
        if (paciente != null) {
            paciente = this.setPaciente(paciente);
            boolean request = this.pacienteController.update(paciente);
            if (request) {
                JOptionPane.showMessageDialog(null,
                        "Paciente actualizado con exito!!",
                        "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                this.loadTablePacientes();
                UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
                this.btnNuevo.setEnabled(true);
                this.btnGuardar.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Paciente No seleccionado",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!this.update) {
            this.newPaciente();
        } else {
            this.updatePaciente(this.pacienteSelected);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnClearFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearFilterActionPerformed

    private void jMnuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuEditActionPerformed

        int row = this.jTblPacientes.getSelectedRow();
        if (row != -1) {
            Long idPaciente = ((Long) this.jTblPacientes.getValueAt(row, 0));
            Optional<Paciente> pacienteOptional = this.listPacientes.stream()
                    .filter(paciente -> Objects.equals(paciente.getId(), idPaciente))
                    .findFirst();
            if (pacienteOptional.isPresent()) {
                this.pacienteSelected = pacienteOptional.get();
                UtilGUI.borrarCamposDeComponentes(this.jPanFields);
                UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, true);
                this.loadForm(this.pacienteSelected);
                this.btnNuevo.setEnabled(false);
                this.btnGuardar.setEnabled(true);
                this.update = true;
            } else {
                UtilGUI.borrarCamposDeComponentes(this.jPanFields);
                UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
                this.btnNuevo.setEnabled(true);
                this.btnGuardar.setEnabled(false);
                this.update = false;
            }
        }
    }//GEN-LAST:event_jMnuEditActionPerformed

    @Override
    public void dispose() {
        this.ppal.salirAlMnuPpal();
        super.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClearFilter;
    private javax.swing.ButtonGroup btnGrupStatus;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JMenuItem jMnuEdit;
    private javax.swing.JPanel jPanFields;
    private javax.swing.JPanel jPanGeneral;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopMnuTableOptions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblPacientes;
    private javax.swing.JPanel jpanBtnList;
    private javax.swing.JPanel jpanButons;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSocialSecurity;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTelephone;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleListaPacientes;
    private javax.swing.JRadioButton rBtnActive;
    private javax.swing.JRadioButton rBtnInactive;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSocialSecurity;
    private javax.swing.JTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
