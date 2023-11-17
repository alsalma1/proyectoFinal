package com.mycompany.mavenproject1.views;


import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JFrame;
import com.mycompany.mavenproject1.views.*;
import com.mycompany.mavenproject1.controllers.*;
import com.mycompany.mavenproject1.models.Pista;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
public class GestionPistas extends javax.swing.JPanel {
    private int idPista = 1;
    private String idEstado;
    public GestionPistas() throws SQLException {
        
        initComponents();
        AppController.actualizarTablaPista(tablePistas);
        AppController.pintarTablaEstadosPista(tableEstado, idPista);
        labelPista.setText("Pista: " + idPista);
        tablePistas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablePistas.rowAtPoint(e.getPoint());
                int columna = tablePistas.columnAtPoint(e.getPoint());
                //int idPista = 1;
                if (fila > -1 && columna > -1) {
                    // Obtener el valor de la celda
                    Object valor = tablePistas.getValueAt(fila, columna);
                    idPista = (int) valor;
                    labelPista.setText("Pista: " + idPista);
                    try {
                    AppController.pintarTablaEstadosPista(tableEstado, idPista);
                    } catch (SQLException ex) {
                    Logger.getLogger(GestionPistas.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
        });
        tableEstado.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tableEstado.rowAtPoint(e.getPoint());

                if (fila > -1) {
                    // Obtener el valor de la celda
                    Object valor = tableEstado.getValueAt(fila, 0);
                    idEstado = String.valueOf(valor);    
                }
                
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        scrollPanePistas = new javax.swing.JScrollPane();
        tablePistas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        scrollPaneEstado = new javax.swing.JScrollPane();
        tableEstado = new javax.swing.JTable();
        cambiarEstadoBtn = new javax.swing.JButton();
        labelPista = new javax.swing.JLabel();
        eliminarMantenimientoBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(926, 540));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(926, 540));

        tablePistas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pista"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePistas.getTableHeader().setReorderingAllowed(false);
        scrollPanePistas.setViewportView(tablePistas);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Gestion de pistas");

        tableEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id de estado", "Estado de la pista", "Fecha de inicio", "Fecha de fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneEstado.setViewportView(tableEstado);

        cambiarEstadoBtn.setText("Reservar o poner en Mantenimiento");
        cambiarEstadoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarEstadoBtnActionPerformed(evt);
            }
        });

        labelPista.setBackground(new java.awt.Color(255, 255, 255));
        labelPista.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPista.setText("Pista:");

        eliminarMantenimientoBtn.setText("Eliminar estado de la pista");
        eliminarMantenimientoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarMantenimientoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(scrollPaneEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrollPanePistas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(cambiarEstadoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eliminarMantenimientoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPista, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPista, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(scrollPanePistas, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cambiarEstadoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(scrollPaneEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarMantenimientoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void cambiarEstadoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarEstadoBtnActionPerformed
        AppController.showJPanelDashboardAdmin(new AñadirEstado());
    }//GEN-LAST:event_cambiarEstadoBtnActionPerformed

    private void eliminarMantenimientoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarMantenimientoBtnActionPerformed
        try {
            System.out.println(idPista);
            AppController.eliminarEstado(idEstado,idPista,tableEstado);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPistas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_eliminarMantenimientoBtnActionPerformed

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel bg;
    private javax.swing.JButton cambiarEstadoBtn;
    private javax.swing.JButton eliminarMantenimientoBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelPista;
    private javax.swing.JScrollPane scrollPaneEstado;
    private javax.swing.JScrollPane scrollPanePistas;
    private javax.swing.JTable tableEstado;
    public static javax.swing.JTable tablePistas;
    // End of variables declaration//GEN-END:variables
}
