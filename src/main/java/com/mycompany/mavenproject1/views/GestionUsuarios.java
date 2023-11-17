package com.mycompany.mavenproject1.views;
import com.mycompany.mavenproject1.controllers.AppController;
import com.mycompany.mavenproject1.models.Usuario;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionUsuarios extends javax.swing.JPanel {
    private AppController appController = new AppController();

    public GestionUsuarios() {
        initComponents();
        cargarUsuariosEnTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnDesactivados = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(926, 540));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(926, 540));

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Email", "Nombre", "Apellido", "Contraseña", "Fecha nacimiento", "Teléfono", "DNI", "Socio", "Imagen", "Editar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableUsuarios);
        if (tableUsuarios.getColumnModel().getColumnCount() > 0) {
            tableUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tableUsuarios.getColumnModel().getColumn(0).setPreferredWidth(150);
            tableUsuarios.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Listado usuarios");

        btnDesactivados.setBackground(new java.awt.Color(0, 153, 255));
        btnDesactivados.setForeground(new java.awt.Color(255, 255, 255));
        btnDesactivados.setText("Usuarios desactivados");
        btnDesactivados.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDesactivados.setBorderPainted(false);
        btnDesactivados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivadosActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 153, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Añadir usuario");
        btnAdd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAdd.setBorderPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 424, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDesactivados, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDesactivados, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void btnDesactivadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivadosActionPerformed

        appController.showJPanelDashboardAdmin(new UsuariosDesactivados());
    }//GEN-LAST:event_btnDesactivadosActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

    }//GEN-LAST:event_addBtnActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        appController.showJPanelDashboardAdmin(new AñadirUsuario());
    }//GEN-LAST:event_btnAddActionPerformed

    public void cargarUsuariosEnTabla() {
        List<Usuario> usuarios = null;
        usuarios = Usuario.obtenerUsuarios();
        DefaultTableModel model = (DefaultTableModel) tableUsuarios.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos
      
        ButtonRenderer buttonRendererEdit = new ButtonRenderer();
        ButtonRenderer buttonRendererDelete = new ButtonRenderer();
        
        // Crea un icono que deseas mostrar en la celda
        Icon icono = new ImageIcon(getClass().getResource("/edit.png"));
        Icon icono1 = new ImageIcon(getClass().getResource("/eliminar.png"));
        // Asigna el renderizador personalizado a la columna
        tableUsuarios.getColumnModel().getColumn(9).setCellRenderer(new IconRenderer(icono));        
        tableUsuarios.getColumnModel().getColumn(10).setCellRenderer(new IconRenderer(icono1));
        tableUsuarios.getColumnModel().getColumn(8).setCellRenderer(new ImageRenderer());
        for (Usuario usuario : usuarios) {
            buttonRendererEdit.setAction("editar");
            String esSocio = usuario.getSocio() ? "Sí" : "No";
            // Obtener el nombre de la imagen con extensión
            String nombreImagen = usuario.getFoto();
            //String[] parts = nombreImagen.split("\\.");
            //String extension = "." + parts[parts.length - 1];
            // Obtener la extensión del nombre de la imagen
            //String nombreImage = usuario.getDni()+extension;
            String rutaImagen = "src/main/resources/imagenes_usua/" + nombreImagen;
            ImageIcon imagen = cargarImagen(rutaImagen, 80, 80);
            Object[] row = {
                    usuario.getEmail(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getContrasena(),
                    usuario.getFecha_nacimiento(),
                    usuario.getTelefono(),
                    usuario.getDni(),
                    esSocio,
                    imagen,
                    "Editar",
                    "Eliminar",
            };
            model.addRow(row);
        }
        tableUsuarios.setRowHeight(120);
        // Asignar MouseListener al botón para manejar el clic
        tableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            //@Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                    int column = tableUsuarios.getColumnModel().getColumnIndexAtX(evt.getX());
                    int row = evt.getY() / tableUsuarios.getRowHeight();

                    if (row < tableUsuarios.getRowCount() && column == 9) {
                        String dni = tableUsuarios.getValueAt(row, 6).toString();
                        buttonRendererEdit.buttonEditAction(dni);
                    } else if (row < tableUsuarios.getRowCount() && column == 10) {
                        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas desactivar este usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                        if (confirmacion == JOptionPane.YES_OPTION) {
                            String dni = tableUsuarios.getValueAt(row, 6).toString();
                            buttonRendererDelete.buttonDeleteAction(dni, GestionUsuarios.this);
                        }
                    }    
            }
        });       
    }
    public ImageIcon cargarImagen(String rutaImagen, int ancho, int alto) {
        try {
            File imagenFile = new File(rutaImagen);
            if (!imagenFile.exists()) {
                // Manejar la situación si la imagen no existe
                return null;
            }

            ImageIcon imagenIcon = new ImageIcon(ImageIO.read(imagenFile));

            // Redimensionar la imagen si es necesario
            Image imagen = imagenIcon.getImage();
            imagen = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

            return new ImageIcon(imagen);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier error que pueda ocurrir al cargar la imagen
            return null;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDesactivados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelTable;
    public javax.swing.JTable tableUsuarios;
    // End of variables declaration//GEN-END:variables
}
