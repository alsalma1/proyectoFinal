package com.mycompany.mavenproject1.views;

import com.mycompany.mavenproject1.controllers.AppController;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BuscarReservasAdmin extends javax.swing.JPanel {
    private AppController appController = new AppController();
    private int idReserva;
    private String estadoReserva;
    public BuscarReservasAdmin() throws SQLException, ParseException {
        initComponents();
        pintarFechaActual();
        estadoComboBox(estadoComboBox);
        pistaComboBox(idPistaComboBox);
        fechaDateChooser.setDate(null);
        appController.pintarReservasUsuarioAdmin(buscarReservaTable);
        buscarReservaTable.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                int filaSelect = buscarReservaTable.getSelectedRow();
                if(filaSelect != -1) {
                    Object idReservaTabla = buscarReservaTable.getValueAt(filaSelect, 0);
                    int reserva = Integer.parseInt(idReservaTabla.toString());
                    idReserva = reserva;
                    Object estadoReservaTabla = buscarReservaTable.getValueAt(filaSelect, 5);
                    String estado = estadoReservaTabla.toString();
                    estadoReserva = estado;
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        buscarReserva = new javax.swing.JScrollPane();
        buscarReservaTable = new javax.swing.JTable();
        buscarBtn = new javax.swing.JButton();
        buscarField = new javax.swing.JTextField();
        volverBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        idPistaComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        estadoComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        fechaDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        eliminarReservaBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(926, 541));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setPreferredSize(new java.awt.Dimension(926, 541));

        buscarReservaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id de reserva", "Email usuario", "Id de pista", "Fecha", "Hora", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        buscarReserva.setViewportView(buscarReservaTable);

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        buscarField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarFieldActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver a reservas");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Email:");

        idPistaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        idPistaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPistaComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID de pista:");

        estadoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        estadoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Estado:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Fecha:");

        eliminarReservaBtn.setText("Eliminar reserva");
        eliminarReservaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarReservaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscarReserva)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscarField)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idPistaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(estadoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(fechaDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addComponent(volverBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addGap(650, 650, 650)
                        .addComponent(eliminarReservaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(backgroundLayout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))))
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(backgroundLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buscarField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(estadoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fechaDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idPistaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addComponent(buscarReserva, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(volverBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(eliminarReservaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed

        String usuarioBuscar = buscarField.getText();
        Date fecha = fechaDateChooser.getDate();
        if(fecha != null){
            if(AppController.comprobarEmailLabel(usuarioBuscar) || usuarioBuscar.isEmpty()){
                String estadoPista = (String) estadoComboBox.getSelectedItem();

                int activa = -1;
                if("Reservada".equals(estadoPista)){
                    activa = 1;
                }else if("Cancelada".equals(estadoPista)){
                    activa = 0;
                }
                String idPista = (String) idPistaComboBox.getSelectedItem();
                int pista = -1;
                if(!idPista.equals("Todas")){
                    pista = Integer.parseInt(idPista);
                }

                java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                try {
                    AppController.pintarReservasBuscadasAdmin(buscarReservaTable,usuarioBuscar,pista, fechaSQL,activa);
                } catch (SQLException ex) {
                    Logger.getLogger(BuscarReservasAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Introduce un email que este registrado", "Error", JOptionPane.ERROR_MESSAGE);
            } 
        }else{
                JOptionPane.showMessageDialog(null, "Introduce una fecha valida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void buscarFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarFieldActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        try {
            appController.showJPanelDashboardAdmin(new GestionReservasAdmin());
        } catch (SQLException ex) {
            Logger.getLogger(BuscarReservasAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BuscarReservasAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_volverBtnActionPerformed

    private void idPistaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPistaComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPistaComboBoxActionPerformed

    private void estadoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoComboBoxActionPerformed

    private void eliminarReservaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarReservaBtnActionPerformed
        int filaSeleccionada = buscarReservaTable.getSelectedRow();
        if(filaSeleccionada != -1) {
            if(!"Cancelada".equals(estadoReserva)){
                int opcion = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres eliminar la reserva " + idReserva + "?","Eliminar reserva", JOptionPane.YES_NO_OPTION);
                if(opcion == JOptionPane.YES_OPTION){
                    appController.eliminarReservaSeleccionadaAdmin(idReserva);
                    String usuarioBuscar = buscarField.getText();
                    Date fecha = fechaDateChooser.getDate();
                    String estadoPista = (String) estadoComboBox.getSelectedItem();

                    int activa = -1;
                    if("Reservada".equals(estadoPista)){
                        activa = 1;
                    }else if("Cancelada".equals(estadoPista)){
                        activa = 0;
                    }
                    String idPista = (String) idPistaComboBox.getSelectedItem();
                    int pista = -1;
                    if(!idPista.equals("Todas")){
                        pista = Integer.parseInt(idPista);
                    }

                    java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                    try {
                        AppController.pintarReservasBuscadasAdmin(buscarReservaTable,usuarioBuscar,pista, fechaSQL,activa);
                    } catch (SQLException ex) {
                        Logger.getLogger(BuscarReservasAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Esta reserva ya esta cancelada.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una reserva para eliminar.");
        }
    }//GEN-LAST:event_eliminarReservaBtnActionPerformed
    private void estadoComboBox(JComboBox estados){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Todas");
        modelo.addElement("Reservada");
        modelo.addElement("Cancelada");
        estados.setModel(modelo);
    }
    private void pistaComboBox(JComboBox pista){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Todas");
        modelo.addElement("1");
        modelo.addElement("2");
        modelo.addElement("3");
        modelo.addElement("4");
        modelo.addElement("5");
        pista.setModel(modelo);
    }
    private void pintarFechaActual(){
        LocalDate now = LocalDate.now();
        Locale spanishLocale = new Locale("es", "ES");
        Date fechaActual = convertirLocalDateADate(now);
        fechaDateChooser.setDate(fechaActual);
    }
    
    private static Date convertirLocalDateADate(LocalDate localDate) {
        // Convertir LocalDate a Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf.parse(localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField buscarField;
    private javax.swing.JScrollPane buscarReserva;
    private javax.swing.JTable buscarReservaTable;
    private javax.swing.JButton eliminarReservaBtn;
    private javax.swing.JComboBox<String> estadoComboBox;
    private com.toedter.calendar.JDateChooser fechaDateChooser;
    private javax.swing.JComboBox<String> idPistaComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables
}
