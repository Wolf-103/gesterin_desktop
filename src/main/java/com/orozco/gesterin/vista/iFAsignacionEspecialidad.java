package com.orozco.gesterin.vista;

import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.controller.EspecialidadController;
import com.orozco.gesterin.controller.ProfesionalController;
import com.orozco.gesterin.model.Especialidad;
import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.service.Implement.ProfesionalServiceImpl;
import com.orozco.gesterin.service.ProfesionalService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 sep. 2024
 * @description Sistema GESTERIN
 */
public class iFAsignacionEspecialidad extends javax.swing.JInternalFrame {

    private final JDesktopPane desktop;
    private Profesional profesionalSelected;
    private Especialidad especialidadSelected;
    private final iFGestionUsuarios ifGestionUsuarios;
    private List<Especialidad> especialidadesDisponibles;
    private List<Especialidad> especialidadesAsignadas;

    ProfesionalController profesionalController;
    EspecialidadController especialidadesController;

    /**
     * Creates new form iFSeleccionEspecialidad
     *
     * @param desktop
     * @param gestionUsuarios
     */
    public iFAsignacionEspecialidad(JDesktopPane desktop, iFGestionUsuarios gestionUsuarios) {
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setWestPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setSouthPane(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setEastPane(null);
        initComponents();

        this.desktop = desktop;
        this.ifGestionUsuarios = gestionUsuarios;
        ProfesionalService profesionalService = new ProfesionalServiceImpl(new PersonaDAO(), new UsuarioDAO());
        this.profesionalController = new ProfesionalController(profesionalService);
        this.especialidadesController = new EspecialidadController(profesionalService.getProfesionalDAO());
        this.profesionalSelected = this.ifGestionUsuarios.getProfesionalSelected();
        this.init();
    }

    private void init() {
        this.profesionalSelected = this.profesionalController.findById(this.profesionalSelected.getId());
        this.especialidadesDisponibles = this.especialidadesNoAsignadas(this.profesionalSelected.getListaEspecialidades());
        this.especialidadesAsignadas = this.profesionalSelected.getListaEspecialidades();
        this.loadTable(this.jTblAsignadas);
        this.loadTable(this.jTblDisponibles);

        if (this.ifGestionUsuarios.isUpdate()) {
            if (this.especialidadesDisponibles.isEmpty()) {
                this.btnAgregarTodas.setEnabled(false);
            } else {
                this.btnAgregarTodas.setEnabled(true);
            }

            this.btnAgregar.setEnabled(false);
            this.btnSacar.setEnabled(false);
        } else {
            this.btnAgregarTodas.setEnabled(false);
            this.btnSacarTodas.setEnabled(false);
            this.btnAgregar.setEnabled(false);
            this.btnSacar.setEnabled(false);
        }
    }

    /**
     * Buscar especialidades del sin asignar
     *
     * @param especialidadsUs lista de especialidades del usuario
     * @return Lista especialidades sin asignar
     */
    public List<Especialidad> especialidadesNoAsignadas(List<Especialidad> especialidadsUs) {
        List<Especialidad> especialidadesSinAsignar = new ArrayList<>(this.especialidadesController.findAll());
        if (!especialidadsUs.isEmpty()) {
            for (Especialidad espUs : especialidadsUs) {
                especialidadesSinAsignar.removeIf(e -> e.equals(espUs));
            }
        }
        return especialidadesSinAsignar;

//        List<Especialidad> especialidadesSinAsignar = this.especialidadesController.findAll();
//        if (!especialidadsUs.isEmpty()) {
//            for (Especialidad e : this.especialidadesController.findAll()) {
//                for (Especialidad espUs : especialidadsUs) {
//                    if (e.equals(espUs)) {
//                        especialidadesSinAsignar.remove(e);
//                        break;
//                    }
//                }
//            }
//        } else {
//            especialidadesSinAsignar = this.especialidadesController.findAll();
//        }
//        return especialidadesSinAsignar;
    }

    public void loadTable(JTable jTable) {
        String[] columnNames = {"ID", "ESPECIALIDAD", "DESCRIPCIÓN"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Especialidad> listaEspecialidades;
        if (jTable.equals(this.jTblDisponibles)) {
            listaEspecialidades = this.especialidadesDisponibles;
            jTable = this.jTblDisponibles;
        } else {
            if (this.especialidadesAsignadas.get(0).getId() == 0) {
                listaEspecialidades = new ArrayList<>();
            } else {
                listaEspecialidades = this.especialidadesAsignadas;
            }
            jTable = this.jTblAsignadas;
        }
        Object[] data = new Object[columnNames.length];
        if (!listaEspecialidades.isEmpty()) {
            for (Especialidad especialidad : listaEspecialidades) {
                data[0] = especialidad.getId();
                data[1] = especialidad.getNombre();
                data[2] = especialidad.getDescripcion();
                model.addRow(data);
            }
        } else {
            if (jTable.equals(this.jTblDisponibles)) {
                this.btnAgregarTodas.setEnabled(false);
            } else {
                this.btnSacarTodas.setEnabled(true);
            }
        }
        jTable.setModel(model);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanGeneral = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblDisponibles = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblTitleListaClientes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblAsignadas = new javax.swing.JTable();
        jpanButons = new javax.swing.JPanel();
        btnAgregarTodas = new javax.swing.JButton();
        btnSacar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnSacarTodas = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setClosable(true);
        setTitle("Asignación de Especialidades");

        jPanGeneral.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 51, 51));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("DISPONIBLES");
        lblTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTblDisponibles.setModel(new javax.swing.table.DefaultTableModel(
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
        jTblDisponibles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTblDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblDisponibles);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitleListaClientes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitleListaClientes.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleListaClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleListaClientes.setText("ASIGNADAS");
        lblTitleListaClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTblAsignadas.setModel(new javax.swing.table.DefaultTableModel(
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
        jTblAsignadas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTblAsignadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblAsignadasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTblAsignadas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleListaClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleListaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jpanButons.setBackground(new java.awt.Color(204, 204, 204));

        btnAgregarTodas.setBackground(new java.awt.Color(102, 0, 0));
        btnAgregarTodas.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarTodas.setText("Asignar Todas >>>");
        btnAgregarTodas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTodasActionPerformed(evt);
            }
        });

        btnSacar.setBackground(new java.awt.Color(102, 0, 0));
        btnSacar.setForeground(new java.awt.Color(255, 255, 255));
        btnSacar.setText("<Desasignar");
        btnSacar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(0, 102, 0));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Asignar >");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnSacarTodas.setBackground(new java.awt.Color(0, 102, 0));
        btnSacarTodas.setForeground(new java.awt.Color(255, 255, 255));
        btnSacarTodas.setText("<<< Sacar Todas");
        btnSacarTodas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSacarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacarTodasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanButonsLayout = new javax.swing.GroupLayout(jpanButons);
        jpanButons.setLayout(jpanButonsLayout);
        jpanButonsLayout.setHorizontalGroup(
            jpanButonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanButonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanButonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSacarTodas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarTodas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSacar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpanButonsLayout.setVerticalGroup(
            jpanButonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanButonsLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnSacar)
                .addGap(120, 120, 120)
                .addComponent(btnAgregarTodas)
                .addGap(18, 18, 18)
                .addComponent(btnSacarTodas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnVolver.setBackground(new java.awt.Color(0, 0, 153));
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanGeneralLayout = new javax.swing.GroupLayout(jPanGeneral);
        jPanGeneral.setLayout(jPanGeneralLayout);
        jPanGeneralLayout.setHorizontalGroup(
            jPanGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanButons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanGeneralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addGap(17, 17, 17))
        );
        jPanGeneralLayout.setVerticalGroup(
            jPanGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanButons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTodasActionPerformed

        if (this.profesionalSelected != null) {
            if (this.especialidadesController.addEspecialidadesToProfesional(this.profesionalSelected.getId(),
                    this.especialidadesDisponibles.stream().map(esp -> esp.getId()).toList())) {
                this.init();
            }
        }

    }//GEN-LAST:event_btnAgregarTodasActionPerformed

    private void btnSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarActionPerformed

        if (this.especialidadSelected != null && this.profesionalSelected != null) {
            if (this.especialidadesController.deleteEspecialidadToProfesional(this.profesionalSelected.getId(), this.especialidadSelected.getId())) {
                this.init();
            }
        }

    }//GEN-LAST:event_btnSacarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (this.especialidadSelected != null && this.profesionalSelected != null) {
            if (this.especialidadesController.addEspecialidadToProfesional(this.profesionalSelected.getId(), this.especialidadSelected.getId())) {
                this.init();
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSacarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacarTodasActionPerformed
        if (this.profesionalSelected != null) {
            if (this.especialidadesController.deleteEspecialidadesFromProfesional(this.profesionalSelected.getId(),
                    this.especialidadesAsignadas.stream().map(esp -> esp.getId()).toList())) {
                this.init();
            }
        }
    }//GEN-LAST:event_btnSacarTodasActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.ifGestionUsuarios.setProfesionalSeletcted(this.profesionalSelected);
        this.ifGestionUsuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed


    private void jTblDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblDisponiblesMouseClicked
        int row = this.jTblDisponibles.getSelectedRow();
        if (row != -1 && this.ifGestionUsuarios.isUpdate()) {
            Long idEspecialidad = ((Long) this.jTblDisponibles.getValueAt(row, 0));
            Optional<Especialidad> espOptional = this.especialidadesDisponibles.stream()
                    .filter(especialidad -> Objects.equals(especialidad.getId(), idEspecialidad))
                    .findFirst();
            if (espOptional.isPresent() && espOptional.get().getId() != 0) {
                this.especialidadSelected = espOptional.get();
                this.btnAgregar.setEnabled(true);
                this.btnSacar.setEnabled(false);
            } else {
                this.especialidadSelected = null;
                this.btnAgregar.setEnabled(false);
                this.btnSacar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jTblDisponiblesMouseClicked

    private void jTblAsignadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblAsignadasMouseClicked
        int row = this.jTblAsignadas.getSelectedRow();
        if (row != -1 && this.ifGestionUsuarios.isUpdate()) {
            Long idEspecialidad = ((Long) this.jTblAsignadas.getValueAt(row, 0));
            Optional<Especialidad> espOptional = this.especialidadesAsignadas.stream()
                    .filter(especialidad -> Objects.equals(especialidad.getId(), idEspecialidad))
                    .findFirst();
            if (espOptional.isPresent() && espOptional.get().getId() != 0) {
                this.especialidadSelected = espOptional.get();
                this.btnAgregar.setEnabled(false);
                this.btnSacar.setEnabled(true);
            } else {
                this.especialidadSelected = null;
                this.btnAgregar.setEnabled(false);
                this.btnSacar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jTblAsignadasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarTodas;
    private javax.swing.JButton btnSacar;
    private javax.swing.JButton btnSacarTodas;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel jPanGeneral;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTblAsignadas;
    private javax.swing.JTable jTblDisponibles;
    private javax.swing.JPanel jpanButons;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleListaClientes;
    // End of variables declaration//GEN-END:variables
}
