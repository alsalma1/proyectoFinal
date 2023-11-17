package com.mycompany.mavenproject1.views;

import com.mycompany.mavenproject1.models.Reserva;
import java.awt.Cursor;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class MisReservas extends javax.swing.JPanel {
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public MisReservas() {
        initComponents();
        JTableHeader header = tableMisReservas.getTableHeader();
        header.setPreferredSize(new Dimension(100, 50));
        centrarTextoCeldas();
    }
    
    public void centrarTextoCeldas(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // Aplicar el centroRenderer a todas las columnas de la tabla
        for (int i = 0; i < tableMisReservas.getColumnCount(); i++) {
            tableMisReservas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tableMisReservas.setDefaultRenderer(String.class, centerRenderer);
    }

    
    public void cargarReservasEnTabla(List<Reserva> lista) {
        // Establecer el ancho de la segunda columna (índice 1) a 100 píxeles
        TableColumn column = tableMisReservas.getColumnModel().getColumn(4);
        column.setPreferredWidth(20);
        
        DefaultTableModel model = (DefaultTableModel) tableMisReservas.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        SimpleDateFormat formatoFechaNuevo = new SimpleDateFormat("dd-MM-yyyy");
        ButtonRenderer buttonRendererDelete = new ButtonRenderer();
        ButtonRenderer buttonRendererIcono = new ButtonRenderer();
        
        Icon icono = new ImageIcon(getClass().getResource("/eliminar.png"));
        tableMisReservas.getColumnModel().getColumn(4).setCellRenderer(new IconRenderer(icono));
        //tableMisReservas.getColumnModel().getColumn(4).setCellRenderer(buttonRendererDelete);
        
        for (Reserva re : lista) {
            String fechaFormateada = formatoFechaNuevo.format(re.getFecha());
            String horaString = re.getHora();
            String[] partes = horaString.split(":");
            int hora = Integer.parseInt(partes[0]);
            hora = (hora + 1) % 24;
            Object[] row = {
                re.getId_reserva(),
                fechaFormateada, // Fecha formateada en "dd-MM-yyyy"
                re.getHora()+"-"+hora+":00",
                re.getId_pista(),
                ""
            };
            model.addRow(row);
        }
        tableMisReservas.setRowHeight(50);
        tableMisReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                int column = tableMisReservas.getColumnModel().getColumnIndexAtX(evt.getX());
                if(column == 4){
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        tableMisReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta reserva?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    int column = tableMisReservas.getColumnModel().getColumnIndexAtX(evt.getX());
                    int row = evt.getY() / tableMisReservas.getRowHeight();
                    if (row < tableMisReservas.getRowCount() && column == 4) {
                        Object idReservaObj = tableMisReservas.getValueAt(row, 0);
                        Object fechaObj = tableMisReservas.getValueAt(row, 1);
                        Object horaObj = tableMisReservas.getValueAt(row, 2);
                        Object numPistaObj = tableMisReservas.getValueAt(row, 3);
                        if (idReservaObj != null && fechaObj != null && horaObj != null && numPistaObj != null) {
                            int idReserva = Integer.parseInt(idReservaObj.toString());
                            String hora = horaObj.toString();
                            int numPista = Integer.parseInt(numPistaObj.toString());
                            String fecha = fechaObj.toString();
                            buttonRendererDelete.buttonDeleteAction(idReserva, MisReservas.this, getUserEmail(), numPista,hora, fecha );
                        }
                    }
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMisReservas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(930, 560));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(930, 560));

        tableMisReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero reserva", "Fecha", "Hora", "Numero pista", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableMisReservas);
        if (tableMisReservas.getColumnModel().getColumnCount() > 0) {
            tableMisReservas.getColumnModel().getColumn(0).setResizable(false);
            tableMisReservas.getColumnModel().getColumn(1).setResizable(false);
            tableMisReservas.getColumnModel().getColumn(2).setResizable(false);
            tableMisReservas.getColumnModel().getColumn(3).setResizable(false);
            tableMisReservas.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Mis reservas");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                .addGap(133, 133, 133))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tableMisReservas;
    // End of variables declaration//GEN-END:variables
}
