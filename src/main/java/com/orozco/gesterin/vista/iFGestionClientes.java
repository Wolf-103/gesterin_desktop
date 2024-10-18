package com.orozco.gesterin.vista;

import com.orozco.gesterin.model.Cliente;
import com.orozco.gesterin.controller.ClienteController;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.exception.FieldEmptyException;
import com.orozco.gesterin.vista.validations.CustomDocumentFilter;
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
public final class iFGestionClientes extends javax.swing.JInternalFrame {

    JFPrincipal ppal;
    JDesktopPane desktop;
    List<Cliente> listClientes = new ArrayList<>();
    ClienteController clienteController;
    boolean update = false;
    Cliente clienteSelected = null;

    /**
     * Creates new form GestionClientes
     *
     * @param esc
     * @param jppal
     */
    public iFGestionClientes(JDesktopPane esc, JFPrincipal jppal) {
        initComponents();
        this.ppal = jppal;
        this.desktop = esc;
        this.clienteController = new ClienteController();
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
        ((AbstractDocument) this.txtDNI.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.DNI_MAX,
                        Pattern.compile(AppConstants.PATTERN_DNI_SLIM))
                );
        ((AbstractDocument) this.txtSocialSecurity.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.SOCIAL_SECURITY_SLIM_MAX,
                        Pattern.compile(AppConstants.PATTERN_SOCIAL_SECURITY_SLIM))
                );
        ((AbstractDocument) this.txtAddress.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.ADDRESS_MAX,
                        Pattern.compile(AppConstants.PATTERN_ADDRESS_SLIM))
                );
        ((AbstractDocument) this.txtTelephone.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.TELEPHONE_MAX,
                        Pattern.compile(AppConstants.PATTERN_TELEPHONE_SLIM))
                );
        ((AbstractDocument) this.txtEmail.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.EMAIL_MAX,
                        Pattern.compile(AppConstants.PATTERN_EMAIL_VALID_CHARACTERS))
                );
    }

    public void loadForm(Cliente cliente) {
        this.txtName.setText(cliente.getNombre() != null ? cliente.getNombre() : "");
        this.txtLastName.setText(cliente.getApellido() != null ? cliente.getApellido() : "");
        this.txtDNI.setText(cliente.getDni() != null ? cliente.getDni() : "");
        this.txtAddress.setText(cliente.getDireccion() != null ? cliente.getDireccion() : "");
        this.txtSocialSecurity.setText(cliente.getObraSocial() != null ? cliente.getObraSocial() : "");
        this.txtEmail.setText(cliente.getEmail() != null ? cliente.getEmail() : "");
        this.txtTelephone.setText(cliente.getTelefono() != null ? cliente.getTelefono() : "");
        if (cliente.getEstado() != null) {
            if (cliente.getEstado()) {
                this.rBtnActive.setSelected(true);
            } else {
                this.rBtnInactive.setSelected(true);
            }
        } else {
            this.rBtnInactive.setSelected(true);
        }
    }

    public void loadTableClientes() {
        String[] columnNames = {"ID", "NOMBRE", "APELLIDO", "DNI", "STATUS"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.listClientes = this.clienteController.findAll();
        for (Cliente cliente : this.listClientes) {
            Object[] data = new Object[columnNames.length];
            data[0] = cliente.getId();
            data[1] = cliente.getNombre();
            data[2] = cliente.getApellido();
            data[3] = cliente.getDni();
            data[4] = cliente.getEstado();
            model.addRow(data);
        }
        this.jTblCliente.setModel(model);
    }

    public void initialStatus() {
        this.clienteSelected = null;
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
        jMnuInfo = new javax.swing.JMenuItem();
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
        jTblCliente = new javax.swing.JTable();
        jpanBtnList = new javax.swing.JPanel();
        btnClearFilter = new javax.swing.JButton();
        lblTitleListaClientes = new javax.swing.JLabel();

        jMnuInfo.setActionCommand("");
        jMnuInfo.setLabel("Abrir");
        jMnuInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuInfoActionPerformed(evt);
            }
        });
        jPopMnuTableOptions.add(jMnuInfo);

        jMnuEdit.setText("Editar");
        jMnuEdit.setActionCommand("");
        jMnuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuEditActionPerformed(evt);
            }
        });
        jPopMnuTableOptions.add(jMnuEdit);

        setClosable(true);
        setTitle("Gestión de Clientes");

        jPanGeneral.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 51, 51));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("FORMULARIO REGISTRO DE CLIENTES");
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

        jTblCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        jTblCliente.setComponentPopupMenu(jPopMnuTableOptions);
        jTblCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblCliente);

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
        lblTitleListaClientes.setText("LISTA DE CLIENTES");
        lblTitleListaClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblTitleListaClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
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
        this.clienteSelected = null;
        UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, true);
        UtilGUI.borrarCamposDeComponentes(this.jPanFields);
        this.btnNuevo.setEnabled(false);
        this.btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private Cliente setCliente(Cliente cliente) {
        cliente.setNombre(this.txtName.getText());
        cliente.setApellido(this.txtLastName.getText());
        cliente.setDni(this.txtDNI.getText());
        cliente.setObraSocial(this.txtSocialSecurity.getText());
        cliente.setEmail(this.txtEmail.getText());
        cliente.setEstado(this.rBtnActive.isSelected());
        cliente.setDireccion(this.txtAddress.getText());
        cliente.setTelefono(this.txtTelephone.getText());
        return cliente;
    }

    private void newCliente() {
        Cliente cliente = new Cliente();
        cliente = this.setCliente(cliente);
        cliente = this.clienteController.save(cliente);
        if (cliente != null) {
            JOptionPane.showMessageDialog(null,
                    "Cliente Registrado con exito!!",
                    "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            this.loadTableClientes();
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
            this.btnNuevo.setEnabled(true);
            this.btnGuardar.setEnabled(false);
        }
    }

    private void updateCliente(Cliente cliente) {
        if (cliente != null) {
            cliente = this.setCliente(cliente);
            cliente = this.clienteController.update(cliente);
            if (cliente != null) {
                JOptionPane.showMessageDialog(null,
                        "Cliente actualizado con exito!!",
                        "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                this.loadTableClientes();
                UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
                this.btnNuevo.setEnabled(true);
                this.btnGuardar.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Cliente No seleccionado",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean validateFields() {
        boolean verificado = false;
        try {
//------Datos Generales Cliente 
            if (this.txtDNI.getText().equals("")) {
                this.txtDNI.requestFocus();
                throw new FieldEmptyException(4012, "Campo DNI vacío", "El dni no puede estar constituido por digitos repetidos.");
            } else {
                if (UtilGUI.validarNumerosRepetidos(this.txtDNI.getText(), "dni")) {
//                    this.jTabPanelCliente.setSelectedIndex(0);
                    this.txtDNI.requestFocus();
                    throw new FieldEmptyException(4012, "Campo DNI no válido", "El dni no puede estar constituido por digitos repetidos.");
                } else {
                    if (this.txtDNI.getText().length() < AppConstants.DNI_MIN) {
                        this.txtDNI.requestFocus();
                        throw new FieldEmptyException(4012, "Campo DNI no válido.", "El dni no puede tener una longitud menor a " + AppConstants.DNI_MIN + ".");
                    } else {
                        if (this.txtName.getText().equals("")) {
                            this.txtName.requestFocus();
                            throw new FieldEmptyException(4012, "Campo Nombre vacío", "Debe cargar NOMBRE del Cliente");
                        } else {
                            if (this.txtLastName.getText().equals("")) {
                                this.txtLastName.requestFocus();
                                throw new FieldEmptyException(4012, "Campo Apellido vacío", "Debe cargar APELLIDO del Cliente");
                            } else {
                                if (this.txtAddress.getText().equals("")) {
                                    this.txtAddress.requestFocus();
                                    throw new FieldEmptyException(4012, "Campo Dirección vacío", "Debe cargar DIRECCION del Cliente");
                                } else {
                                    if (this.txtSocialSecurity.getText().equals("")) {
                                        this.txtSocialSecurity.requestFocus();
                                        throw new FieldEmptyException(4012, "Campo Obra Social vacío", "Debe cargar OBRA SOCIAL del Cliente");
                                    } else {
                                        if (this.txtTelephone.getText().equals("")) {
                                            this.txtTelephone.requestFocus();
                                            throw new FieldEmptyException(4012, "Campo Teléfono vacío", "Debe cargar TELÉFONO del Cliente");
                                        } else {
                                            if (!this.rBtnActive.isSelected() && !this.rBtnInactive.isSelected()) {
                                                this.rBtnActive.requestFocus();
                                                throw new FieldEmptyException(4012, "Campo Estado no seleccionado.", "Debe seleccionar el ESTADO del Cliente");
                                            } else {
                                                if (this.txtEmail.getText().equals("")) {
                                                    this.txtEmail.requestFocus();
                                                    throw new FieldEmptyException(4012, "Campo Email vacío", "Debe cargar EMAIL del Cliente");
                                                } else {
                                                    if (!UtilGUI.validateEmail(this.txtEmail.getText())) {
                                                        this.txtEmail.requestFocus();
                                                        throw new FieldEmptyException(4012, "Campo Email no valido", """
                                                            Verifique la informaci\u00f3n ingresada, el email debe tener el formato: 
                                                            xxxxx...@xxxxx.xxx """);
                                                    } else {
                                                        verificado = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (FieldEmptyException ex) {
            ControllerExceptionHandler.handleError(ex, "Verificar Campos");
        }

        return verificado;
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (this.validateFields())
            if (!this.update) {
                this.newCliente();
            } else {
                this.updateCliente(this.clienteSelected);
            }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnClearFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearFilterActionPerformed

    private Optional<Cliente> getCLientFromTable() {
        int row = this.jTblCliente.getSelectedRow();
        if (row != -1) {
            Long idCliente = ((Long) this.jTblCliente.getValueAt(row, 0));
            return this.listClientes.stream()
                    .filter(cliente -> Objects.equals(cliente.getId(), idCliente))
                    .findFirst();
        }
        return null;
    }

    private void jMnuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuEditActionPerformed
        Optional<Cliente> clienteOptional = this.getCLientFromTable();
        if (clienteOptional.isPresent()) {
            this.clienteSelected = clienteOptional.get();
            UtilGUI.borrarCamposDeComponentes(this.jPanFields);
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, true);
            this.loadForm(this.clienteSelected);
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
    
    }//GEN-LAST:event_jMnuEditActionPerformed

    private void jTblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblClienteMouseClicked
        
    }//GEN-LAST:event_jTblClienteMouseClicked

    private void jMnuInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuInfoActionPerformed
        Optional<Cliente> clienteOptional = this.getCLientFromTable();
        if (clienteOptional.isPresent()) {
            this.clienteSelected = clienteOptional.get();
            UtilGUI.borrarCamposDeComponentes(this.jPanFields);
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
            this.loadForm(this.clienteSelected);
            this.btnNuevo.setEnabled(false);
            this.btnGuardar.setEnabled(false);
            this.update = false;
        }
    }//GEN-LAST:event_jMnuInfoActionPerformed
    
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
    private javax.swing.JMenuItem jMnuInfo;
    private javax.swing.JPanel jPanFields;
    private javax.swing.JPanel jPanGeneral;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopMnuTableOptions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblCliente;
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
    private javax.swing.JLabel lblTitleListaClientes;
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
