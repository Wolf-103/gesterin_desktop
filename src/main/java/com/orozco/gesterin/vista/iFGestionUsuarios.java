package com.orozco.gesterin.vista;

import com.orozco.gesterin.controller.RolController;
import com.orozco.gesterin.controller.UsuarioController;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.exception.FieldEmptyException;
import com.orozco.gesterin.exception.GlobalException;
import com.orozco.gesterin.model.Administrador;
import com.orozco.gesterin.model.Persona;
import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.model.Rol;
import com.orozco.gesterin.model.Usuario;
import com.orozco.gesterin.vista.validations.CustomDocumentFilter;
import com.orozco.gesterin.utils.AppConstants;
import com.orozco.gesterin.utils.BcryptUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
public final class iFGestionUsuarios extends javax.swing.JInternalFrame {

    private final JFPrincipal ppal;
    private final JDesktopPane desktop;
    private List<Persona> listaPersonas = new ArrayList<>();
    private List<Rol> listaRoles = new ArrayList<>();
    private final UsuarioController usuarioController;
    private final RolController rolController;
    private boolean update = false;
    private boolean isPasswordVisible = false;
    private Persona personaSelected = null;
    private Usuario usuarioSelected = null;
    private Rol rolSelected = null;

    /**
     * Creates new form GestionClientes
     *
     * @param esc
     * @param jppal
     */
    public iFGestionUsuarios(JDesktopPane esc, JFPrincipal jppal) {
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setWestPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setSouthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setEastPane(null);
        initComponents();
        this.ppal = jppal;
        this.desktop = esc;
        this.usuarioController = new UsuarioController();
        this.rolController = new RolController();
        this.initialStatus();
        this.initFields();
        this.loadRole();
        this.loadTableUsuarios(this.getAllPersonaUsuario());
        this.loadIconTitle();
    }

    private void loadIconTitle() {
        try {
            InputStream inpStrIMG = getClass().getResourceAsStream("/img/Usuarios 25x25.png");
            this.setFrameIcon(new ImageIcon(ImageIO.read(inpStrIMG)));
        } catch (IOException ex) {
            System.out.println("""
                               Error al Cargar Imagen. 
                                Hubo un problema al tratar de cargar una imagen de sistema.""");
        }
    }

    public void initFields() {
        ((AbstractDocument) this.txtName.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.NAME_LASTNAME_MAX,
                        AppConstants.PATTERN_NAME_LASTNAME_SLIM_COMPILER)
                );
        ((AbstractDocument) this.txtLastName.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.NAME_LASTNAME_MAX,
                        AppConstants.PATTERN_NAME_LASTNAME_SLIM_COMPILER)
                );
        ((AbstractDocument) this.txtTelephone.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.TELEPHONE_MAX,
                        AppConstants.PATTERN_TELEPHONE_SLIM_COMPILER)
                );
        ((AbstractDocument) this.txtEmail.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.EMAIL_MAX,
                        AppConstants.PATTERN_EMAIL_VALID_CHARACTERS_COMPILE)
                );
        ((AbstractDocument) this.txtUsuario.getDocument())
                .setDocumentFilter(new CustomDocumentFilter(
                        AppConstants.NAME_LASTNAME_MAX,
                        AppConstants.PATTERN_USERNAME_COMPILE)
                );
        this.txtPassword.setText("");
    }

    public boolean isUpdate() {
        return this.update;
    }

    public Profesional getProfesionalSelected() {
        if (this.personaSelected instanceof Profesional pro) {
            return pro;
        }
        return null;
    }

    public void setProfesionalSeletcted(Profesional pro) {
        this.personaSelected = pro;
    }

    public void loadForm(Persona persona) {
        this.txtName.setText(persona.getNombre() != null ? persona.getNombre() : "");
        this.txtLastName.setText(persona.getApellido() != null ? persona.getApellido() : "");
        this.txtTelephone.setText(persona.getTelefono() != null ? persona.getTelefono() : "");
        this.txtEmail.setText(persona.getEmail() != null ? persona.getEmail() : "");

        this.usuarioSelected = null;
        if (persona instanceof Administrador admin) {
            this.usuarioSelected = admin.getUsuario();
            this.btnEspecialidades.setText("Deshabiliado");
            this.btnEspecialidades.setEnabled(false);
        } else if (persona instanceof Profesional pro) {
            this.usuarioSelected = pro.getUsuario();
        }
        if (this.usuarioSelected != null) {
            this.txtUsuario.setText(this.usuarioSelected.getNombre() != null ? this.usuarioSelected.getNombre() : "");
            if (this.usuarioSelected.getEstado()) {
                this.rBtnActive.setSelected(true);
            } else {
                this.rBtnInactive.setSelected(true);
            }
            if (this.usuarioSelected.getRol() != null) {
                Rol rol = this.usuarioSelected.getRol();
                this.cboRol.setSelectedItem(rol);
                if (this.rolSelected.getNombre().equals("PROFESIONAL")) {
                    this.btnEspecialidades.setEnabled(true);
                    if (this.update == true) {
                        this.btnEspecialidades.setText("Administrar");
                    } else {
                        this.btnEspecialidades.setText("Ver Asignados");
                    }
                }
            } else {
                this.cboRol.setSelectedIndex(0);
            }
        } else {
            this.rBtnInactive.setSelected(true);
        }
        this.txtPassword.setText("contraseña oculta");
    }

    private void loadRole() {
        this.listaRoles = this.rolController.findAll();
        Collections.sort(this.listaRoles, (Rol o1, Rol o2) -> o1.getNombre().compareToIgnoreCase(o2.getNombre()));
        UtilGUI.CargarCombo(this.listaRoles, this.cboRol);
        UtilGUI.CargarCombo(this.listaRoles, this.cboFilterRole);
    }

    private List<Persona> getAllPersonaUsuario() {
        List<Persona> listaPersonaUsuario = this.usuarioController.getAllPeopleUsers();
        Collections.sort(listaPersonaUsuario, (Persona o1, Persona o2) -> o1.getId().compareTo(o2.getId()));
        return listaPersonaUsuario;
    }

    private List<Profesional> getAllProfessional() {
        return this.usuarioController.getAllPeopleProfessionals();
    }

    private List<Administrador> getAllAdministrators() {
        return this.usuarioController.getAllPeopleUserAdministradors();
    }

    public void loadTableUsuarios(List<? extends Persona> listUsers) {
        String[] columnNames = {"ID", "NOMBRE", "APELLIDO", "EMAIL", "ROL"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.listaPersonas = new ArrayList<>(listUsers);
        for (Persona persona : this.listaPersonas) {
            Object[] data = new Object[columnNames.length];
            data[0] = persona.getId();
            data[1] = persona.getNombre();
            data[2] = persona.getApellido();
            data[3] = persona.getEmail();
            String rol = "";
            if (persona instanceof Administrador admin) {
                rol = admin.getUsuario().getRol().getNombre().toUpperCase();
            } else if (persona instanceof Profesional pro) {
                rol = pro.getUsuario().getRol().getNombre().toUpperCase();
            }
            data[4] = rol;
            model.addRow(data);
        }
        this.jTblUsuario.setModel(model);
    }

    private void clearFilters() {
        UtilGUI.borrarCamposDeComponentes(this.jPanFileters);
    }

    private void initialStatus() {
        this.update = false;
        this.personaSelected = null;
        this.usuarioSelected = null;
        this.rolSelected = null;
        this.btnEspecialidades.setText("Deshabilitado");
        UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
        UtilGUI.borrarCamposDeComponentes(this.jPanFields);
        UtilGUI.borrarCamposDeComponentes(this.jPanFileters);
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
        jMnuBaja = new javax.swing.JMenuItem();
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
        btnEspecialidades = new javax.swing.JButton();
        btnHidenPasswword = new javax.swing.JButton();
        jpanButons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblUsuario = new javax.swing.JTable();
        jPanFileters = new javax.swing.JPanel();
        btnClearFilter = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        cboFilterRole = new javax.swing.JComboBox<>();
        lblTitleListaClientes = new javax.swing.JLabel();

        jMnuInfo.setLabel("Abrir");
        jMnuInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuInfoActionPerformed(evt);
            }
        });
        jPopMnuTableOptions.add(jMnuInfo);

        jMnuEdit.setText("Editar");
        jMnuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuEditActionPerformed(evt);
            }
        });
        jPopMnuTableOptions.add(jMnuEdit);

        jMnuBaja.setText("Baja");
        jMnuBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuBajaActionPerformed(evt);
            }
        });
        jPopMnuTableOptions.add(jMnuBaja);

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

        cboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        cboRol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRolActionPerformed(evt);
            }
        });

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

        btnEspecialidades.setBackground(new java.awt.Color(0, 0, 153));
        btnEspecialidades.setForeground(new java.awt.Color(255, 255, 255));
        btnEspecialidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Adjuntar 25x25.png"))); // NOI18N
        btnEspecialidades.setText("Ver Asignados");
        btnEspecialidades.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEspecialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEspecialidadesActionPerformed(evt);
            }
        });

        btnHidenPasswword.setBackground(new java.awt.Color(0, 0, 153));
        btnHidenPasswword.setForeground(new java.awt.Color(255, 255, 255));
        btnHidenPasswword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Consulta Verde 25x25.png"))); // NOI18N
        btnHidenPasswword.setText("Mostrar");
        btnHidenPasswword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHidenPasswwordActionPerformed(evt);
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
                                    .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanFieldsLayout.createSequentialGroup()
                                            .addComponent(txtPassword)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnHidenPasswword))
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(jPanFieldsLayout.createSequentialGroup()
                        .addComponent(lblEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHidenPasswword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEspecialidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        jpanButons.setBackground(new java.awt.Color(204, 204, 204));

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Cancelar.png"))); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Nuevo.png"))); // NOI18N
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
                .addGap(80, 80, 80)
                .addComponent(btnNuevo)
                .addGap(30, 30, 30)
                .addComponent(btnGuardar)
                .addGap(30, 30, 30)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanButonsLayout.setVerticalGroup(
            jpanButonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanButonsLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jpanButonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo))
                .addGap(14, 14, 14))
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

        jPanFileters.setBackground(new java.awt.Color(255, 255, 255));
        jPanFileters.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanFileters.setForeground(new java.awt.Color(255, 255, 255));

        btnClearFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Cancelar BN.png"))); // NOI18N
        btnClearFilter.setText("Quitar Filtros");
        btnClearFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClearFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFilterActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BTN Buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cboFilterRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        cboFilterRole.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboFilterRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanFiletersLayout = new javax.swing.GroupLayout(jPanFileters);
        jPanFileters.setLayout(jPanFiletersLayout);
        jPanFiletersLayout.setHorizontalGroup(
            jPanFiletersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanFiletersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanFiletersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanFiletersLayout.createSequentialGroup()
                        .addComponent(cboFilterRole, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClearFilter)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanFiletersLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addGap(92, 92, 92))))
        );
        jPanFiletersLayout.setVerticalGroup(
            jPanFiletersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanFiletersLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanFiletersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanFiletersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClearFilter)
                    .addComponent(cboFilterRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                    .addComponent(jPanFileters, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleListaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanFileters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        this.personaSelected = null;
        this.usuarioSelected = null;
        this.rolSelected = null;
        UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, true);
        UtilGUI.borrarCamposDeComponentes(this.jPanFields);
        this.btnEspecialidades.setText("Deshabilitado");
        this.btnEspecialidades.setEnabled(false);
        this.btnNuevo.setEnabled(false);
        this.btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void setPersona(Persona persona) {
        persona.setNombre(UtilGUI.capitalizeAll(this.txtName.getText()));
        persona.setApellido(UtilGUI.capitalizeAll(this.txtLastName.getText()));
        persona.setEmail(this.txtEmail.getText().toLowerCase());
        persona.setTelefono(this.txtTelephone.getText());
    }

    private Profesional setProfesional(Profesional prof) {
        prof.setUsuario(prof.getUsuario());
        return prof;
    }

    private Administrador setAdministrador(Administrador admin) {
        admin.setUsuario(admin.getUsuario());
        return admin;
    }

    private Usuario setUsuario(Usuario us) {
        us.setEstado(this.rBtnActive.isSelected());
        us.setNombre(this.txtUsuario.getText());
        if (!new String(this.txtPassword.getPassword()).equals("contraseña oculta")) {
            us.setConstrasena(new String(this.txtPassword.getPassword()));
            us.setConstrasena(BcryptUtil.hashPassword(us.getConstrasena()));
        }
        us.setRol(this.rolSelected);

        return us;
    }

    private void newPersona() {
        Persona persona = null;
        try {
            Usuario us = this.setUsuario(new Usuario());
            switch (this.rolSelected.getNombre()) {
                case "PROFESIONAL" -> {
                    Profesional pro = new Profesional();
                    this.setPersona(pro);
                    pro.setUsuario(us);
                    persona = pro;
                }
                case "ADMINISTRADOR" -> {
                    Administrador admin = new Administrador();
                    this.setPersona(admin);
                    admin.setUsuario(us);
                    persona = admin;
                }
                default ->
                    throw new GlobalException(4001, "ROL NO ENCONTRADO",
                            "Generar usuario");
            }

            persona = this.usuarioController.save(persona);

        } catch (GlobalException ex) {
            ControllerExceptionHandler.handleError(ex, """
                                        Ocurrió un problema al intentar de registrar al nuevo usuario, reinicie la aplicación e intentelo de nuevo. 
                                        Si el problema persiste contacte a su técnico designado.""");
        }
        if (persona != null) {
            JOptionPane.showMessageDialog(null,
                    "Usuario Registrado con éxito!!",
                    "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            this.loadTableUsuarios(this.getAllPersonaUsuario());
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
            UtilGUI.borrarCamposDeComponentes(this.jPanFileters);
            this.personaSelected = persona;
            this.loadForm(persona);
            this.txtPassword.setText("contraseña oculta");
            this.btnNuevo.setEnabled(true);
            this.btnGuardar.setEnabled(false);
        }
    }

    private void updateUser() {
        if (this.personaSelected != null) {
            try {
                this.setPersona(this.personaSelected);
                switch (this.rolSelected.getNombre()) {
                    case "ADMINISTRADOR":
                        Administrador admin = (Administrador) this.personaSelected;
                        admin.setUsuario(this.setUsuario(admin.getUsuario()));
                        this.personaSelected = this.usuarioController.update(admin);
                        break;
                    case "PROFESIONAL":
                        Profesional prof = (Profesional) this.personaSelected;
                        prof.setUsuario(this.setUsuario(prof.getUsuario()));
                        this.personaSelected = this.usuarioController.update(prof);
                        break;
                    default:
                        throw new GlobalException(4001, "ROL NO ENCONTRADO",
                                "Generar usuario");
                }
            } catch (GlobalException ex) {
                ControllerExceptionHandler.handleError(ex, """
                                        Ocurrió un problema al intentar de registrar al nuevo usuario, reinicie la aplicación e intentelo de nuevo. 
                                        Si el problema persiste contacte a su tecnico designado.""");
            }

            if (this.personaSelected != null) {
                JOptionPane.showMessageDialog(null,
                        "Usuario actualizado con exito!!",
                        "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                this.update = false;
                this.loadTableUsuarios(this.getAllPersonaUsuario());
                UtilGUI.borrarCamposDeComponentes(this.jPanFields);
                UtilGUI.borrarCamposDeComponentes(this.jPanFileters);
                UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
                this.loadForm(this.personaSelected);
                this.txtPassword.setText("contraseña oculta");
                this.btnNuevo.setEnabled(true);
                this.btnGuardar.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Usuario No seleccionado",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean validarSiExisteNombreUsuario() {
        if (this.update == true && this.usuarioSelected != null) {
            if (!this.txtUsuario.getText().equals(this.usuarioSelected.getNombre())) {
                return this.usuarioController.validarUsername(this.txtUsuario.getText());
            } else {
                return false;
            }
        }
        return this.usuarioController.validarUsername(this.txtUsuario.getText());
    }

    private boolean validarSiExisteEmail() {
        if (this.update == true && this.personaSelected != null) {
            if (!this.txtEmail.getText().equals(this.personaSelected.getEmail())) {
                return this.usuarioController.validarEmail(this.txtEmail.getText());
            } else {
                return false;
            }
        }
        return this.usuarioController.validarEmail(this.txtEmail.getText());
    }

    private boolean validarSiExisteTelefono() {
        if (this.update == true && this.personaSelected != null) {
            if (!this.txtTelephone.getText().equals(this.personaSelected.getTelefono())) {
                return this.usuarioController.validarTelefono(this.txtTelephone.getText());
            } else {
                return false;
            }
        }
        return this.usuarioController.validarTelefono(this.txtTelephone.getText());
    }

    private boolean validarContraseña() {
        if (!new String(this.txtPassword.getPassword()).equals("contraseña oculta")) {
            if (!AppConstants.PATTERN_PASSWORD_COMPILLE.matcher(new String(this.txtPassword.getPassword())).matches()) {
                return false;
            }
        }
        return true;
    }

    public boolean validateFields() {
        boolean verificado = false;
        try {
            if (this.txtName.getText().equals("")) {
                this.txtName.requestFocus();
                throw new FieldEmptyException(4012, "Campo Nombre vacío", "Debe cargar NOMBRE del Usuario");
            } else if (this.txtLastName.getText().equals("")) {
                this.txtLastName.requestFocus();
                throw new FieldEmptyException(4012, "Campo Apellido vacío", "Debe cargar APELLIDO del Usuario");
            } else if (this.txtTelephone.getText().equals("")) {
                this.txtTelephone.requestFocus();
                throw new FieldEmptyException(4012, "Campo Teléfono vacío", "Debe cargar TELÉFONO del Usuario");
            } else if (this.validarSiExisteTelefono()) {
                this.txtTelephone.requestFocus();
                throw new FieldEmptyException(4012, "Campo Teléfono no válido", "El Teléfono pertenece a un usuario registrado");
            } else if (!this.rBtnActive.isSelected() && !this.rBtnInactive.isSelected()) {
                this.rBtnActive.requestFocus();
                throw new FieldEmptyException(4012, "Campo Estado no seleccionado.", "Debe seleccionar el ESTADO del Usuario");
            } else if (this.txtEmail.getText().equals("")) {
                this.txtEmail.requestFocus();
                throw new FieldEmptyException(4012, "Campo Email vacío", "Debe cargar EMAIL del Usuario");
            } else if (!UtilGUI.validateEmail(this.txtEmail.getText())) {
                this.txtEmail.requestFocus();
                throw new FieldEmptyException(4012, "Campo Correo Electrónico no valido", """
                                                            Verifique la informaci\u00f3n ingresada, el email debe tener el formato: 
                                                            xxxxx...@xxxxx.xxx """);
            } else if (this.validarSiExisteEmail()) {
                this.txtEmail.requestFocus();
                throw new FieldEmptyException(4012, "Campo Correo Electrónico no válido", "Correo Electrónico pertenece a un usuario registrado");
            } else if (this.txtUsuario.getText().equals("")) {
                this.txtUsuario.requestFocus();
                throw new FieldEmptyException(4012, "Campo Nombre de Usuario vacío", "Debe cargar el Nombre de Usuario");
            } else if (this.validarSiExisteNombreUsuario()) {
                this.txtUsuario.requestFocus();
                throw new FieldEmptyException(4012, "Campo Nombre de Usuario no válido", "El Nombre de Usuario pertenece a un usaurio registrado");
            } else if (this.txtPassword.getPassword().equals("")) {
                this.txtPassword.requestFocus();
                throw new FieldEmptyException(4012, "Campo Contraseña vacío", "Debe cargar la contraseña del Usuario");
            } else if (this.update == false && this.txtPassword.getPassword().equals("contraseña oculta")) {
                this.txtPassword.requestFocus();
                throw new FieldEmptyException(4012, "Campo Contraseña vacío", "Debe cargar la contraseña del Usuario");
            } else if (!this.validarContraseña()) {
                this.txtPassword.requestFocus();
                throw new FieldEmptyException(4012, "Campo Contraseña Inválida", "Contraseña debe contener una longitud mínima de " + AppConstants.PASSWORD_MIN
                        + " y máxima de " + AppConstants.PASSWORD_MAX
                        + ", debe contener al menos una letra minúscula, mayúscula y un número. Acetpta asteriscos, guión bajo y medio.");
            } else if (this.cboRol.getSelectedIndex() == 0) {
                this.cboRol.requestFocus();
                throw new FieldEmptyException(4012, "Campo Rol No Seleccionado", "Debe seleccionar el Rol del Usuario");
            } else {
                verificado = true;
            }

        } catch (FieldEmptyException ex) {
            ControllerExceptionHandler.handleError(ex, "Verificar Campos");
        }

        return verificado;
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (this.validateFields()) {
            if (!this.update) {
                this.newPersona();
            } else {
                this.updateUser();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnClearFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFilterActionPerformed
        this.clearFilters();
    }//GEN-LAST:event_btnClearFilterActionPerformed

    private Optional<Persona> getEntityFromTable() {
        int row = this.jTblUsuario.getSelectedRow();
        if (row != -1) {
            Long idPersona = ((Long) this.jTblUsuario.getValueAt(row, 0));
            return getPersonToList(idPersona);
        }
        return null;
    }

    private Optional<Persona> getPersonToList(Long idPersona) {
        return this.listaPersonas.stream()
                .filter(persona -> Objects.equals(persona.getId(), idPersona))
                .findFirst();
    }

    private void jMnuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuEditActionPerformed

        Optional<Persona> personaOptional = this.getEntityFromTable();
        if (personaOptional.isPresent()) {
            this.personaSelected = personaOptional.get();
            this.update = true;
            UtilGUI.borrarCamposDeComponentes(this.jPanFields);
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, true);
            this.loadForm(this.personaSelected);
//            this.cboRol.setEnabled(false);
            this.btnNuevo.setEnabled(false);
            this.btnGuardar.setEnabled(true);
        } else {
            UtilGUI.borrarCamposDeComponentes(this.jPanFields);
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
            this.btnNuevo.setEnabled(true);
            this.btnGuardar.setEnabled(false);
            this.update = false;
        }
    }//GEN-LAST:event_jMnuEditActionPerformed


    private void btnEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEspecialidadesActionPerformed
        iFAsignacionEspecialidad asignacionEspecialidad = new iFAsignacionEspecialidad(this.desktop, this);
        UtilGUI.openInternalFrame(this.desktop, asignacionEspecialidad);
        this.setVisible(false);
    }//GEN-LAST:event_btnEspecialidadesActionPerformed

    private boolean validateFieldBuscar() {
        try {
            if (this.txtBuscar.getText().equals("") || this.txtBuscar.getText().length() < 3) {
                this.txtBuscar.requestFocus();
                throw new FieldEmptyException(4012, "Campo buscar vacío o inferior a 3 caracteres.", "Debe cargar NOMBRE, APELLIDO o CORREO ELECTRONICO, o almenos 3 caracteres del párametro a buscar");
            } else {
                return true;
            }
        } catch (FieldEmptyException ex) {
            ControllerExceptionHandler.handleError(ex, "Verificar Campos");
        }
        return false;
    }

    private List<Persona> finPersonUserWithParams(String param) {
        return this.usuarioController.findPersonUserWithParam(param);
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (this.validateFieldBuscar()) {
            List<Persona> findPersonUser = this.finPersonUserWithParams(this.txtBuscar.getText());
            if (!findPersonUser.isEmpty()) {
                this.loadTableUsuarios(findPersonUser);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron coincidencias con los datos cargados",
                        "Información ´sobre búsqueda",
                        JOptionPane.INFORMATION_MESSAGE);
                this.loadTableUsuarios(new LinkedList<>());
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cboRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRolActionPerformed
        if (this.cboRol.getSelectedIndex() != 0) {
            this.rolSelected = (Rol) this.cboRol.getSelectedItem();
        } else {
            this.rolSelected = null;
        }
    }//GEN-LAST:event_cboRolActionPerformed

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

    private void btnHidenPasswwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHidenPasswwordActionPerformed
        this.hiddenPassword();
    }//GEN-LAST:event_btnHidenPasswwordActionPerformed

    private void jMnuInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuInfoActionPerformed
        Optional<Persona> personaOptional = this.getEntityFromTable();
        if (personaOptional.isPresent()) {
            this.update = false;
            this.personaSelected = personaOptional.get();
            UtilGUI.borrarCamposDeComponentes(this.jPanFields);
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
            this.loadForm(this.personaSelected);
            this.btnNuevo.setEnabled(false);
            this.btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_jMnuInfoActionPerformed

    private void cboFilterRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterRoleActionPerformed
        if (this.cboFilterRole.getSelectedIndex() != 0) {
            Rol rolSelc = (Rol) this.cboFilterRole.getSelectedItem();
            switch (rolSelc.getNombre()) {
                case "ADMINISTRADOR" -> {
                    this.loadTableUsuarios(this.getAllAdministrators());
                }
                case "PROFESIONAL" -> {
                    this.loadTableUsuarios(this.getAllProfessional());
                }
            }
        } else {
            this.loadTableUsuarios(this.getAllPersonaUsuario());
        }
    }//GEN-LAST:event_cboFilterRoleActionPerformed

    private void jMnuBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuBajaActionPerformed
        Optional<Persona> personaOptional = this.getEntityFromTable();
        if (personaOptional.isPresent()) {
            this.update = false;
            UtilGUI.borrarCamposDeComponentes(this.jPanFields);
            UtilGUI.deshabilitarHabilitarComponentes(this.jPanFields, false);
            this.personaSelected = personaOptional.get();
            int delete = JOptionPane.showConfirmDialog(this, "Desea dar de baja al usuario: " + this.personaSelected.getNombre() + " " + this.personaSelected.getApellido(),
                    "Confirmar baja de Usuario", JOptionPane.YES_NO_OPTION);
            if(delete==0){
                this.usuarioController.bajaUsuario(this.personaSelected);
                this.loadTableUsuarios(this.getAllPersonaUsuario());
                this.initialStatus();
            }
        }
    }//GEN-LAST:event_jMnuBajaActionPerformed

    @Override
    public void dispose() {
        this.ppal.salirAlMnuPpal();
        super.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClearFilter;
    private javax.swing.JButton btnEspecialidades;
    private javax.swing.ButtonGroup btnGrupStatus;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHidenPasswword;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboFilterRole;
    private javax.swing.JComboBox<String> cboRol;
    private javax.swing.JMenuItem jMnuBaja;
    private javax.swing.JMenuItem jMnuEdit;
    private javax.swing.JMenuItem jMnuInfo;
    private javax.swing.JPanel jPanFields;
    private javax.swing.JPanel jPanFileters;
    private javax.swing.JPanel jPanGeneral;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopMnuTableOptions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblUsuario;
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
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelephone;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
