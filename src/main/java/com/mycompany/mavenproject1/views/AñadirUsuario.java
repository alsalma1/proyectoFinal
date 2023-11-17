package com.mycompany.mavenproject1.views;

import com.mycompany.mavenproject1.controllers.AppController;
import com.mycompany.mavenproject1.models.Usuario;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AñadirUsuario extends javax.swing.JPanel {

    private AppController appController = new AppController();
    private String filePath;
    private String fileName;
    
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    
    public AñadirUsuario() {
        initComponents();

    }
    
    private void limpiarCampos() {
        fieldNombre.setText("");
        fieldApellido.setText("");
        fieldDni.setText("");
        fieldEmail.setText("");
        fieldTelefono.setText("");
        boxSocio.setSelectedIndex(0);
        fieldFecha.setDate(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        fieldFecha = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        btnFile = new javax.swing.JButton();
        fieldEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fieldDni = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        fieldNombre = new javax.swing.JTextField();
        fieldTelefono = new javax.swing.JTextField();
        fieldApellido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        boxSocio = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(926, 540));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setForeground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(926, 540));

        fieldFecha.setBackground(java.awt.Color.lightGray);
        fieldFecha.setForeground(new java.awt.Color(0, 0, 0));
        fieldFecha.setDateFormatString("yyyy-MM-dd");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Fecha nacimiento");

        btnFile.setBackground(new java.awt.Color(204, 204, 204));
        btnFile.setText("Seleccionar archivo");
        btnFile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        fieldEmail.setBackground(java.awt.Color.lightGray);
        fieldEmail.setForeground(new java.awt.Color(0, 0, 0));
        fieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEmailActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Email");

        fieldDni.setBackground(java.awt.Color.lightGray);
        fieldDni.setForeground(new java.awt.Color(0, 0, 0));
        fieldDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDniActionPerformed(evt);
            }
        });
        fieldDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldDniKeyTyped(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Imagen");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Apellido");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Teléfono");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Añadir nuevo usuario");

        btnCancelar.setBackground(new java.awt.Color(0, 153, 255));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAñadir.setBackground(new java.awt.Color(0, 153, 255));
        btnAñadir.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Socio");

        fieldNombre.setBackground(java.awt.Color.lightGray);
        fieldNombre.setForeground(new java.awt.Color(0, 0, 0));
        fieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNombreActionPerformed(evt);
            }
        });
        fieldNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldNombreKeyTyped(evt);
            }
        });

        fieldTelefono.setBackground(java.awt.Color.lightGray);
        fieldTelefono.setForeground(new java.awt.Color(0, 0, 0));
        fieldTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTelefonoActionPerformed(evt);
            }
        });
        fieldTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldTelefonoKeyTyped(evt);
            }
        });

        fieldApellido.setBackground(java.awt.Color.lightGray);
        fieldApellido.setForeground(new java.awt.Color(0, 0, 0));
        fieldApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldApellidoKeyTyped(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre");

        boxSocio.setBackground(java.awt.Color.lightGray);
        boxSocio.setForeground(new java.awt.Color(0, 0, 0));
        boxSocio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sí", "No" }));
        boxSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxSocioActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("DNI");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(671, 671, 671)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldNombre)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldApellido)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldTelefono)
                                    .addComponent(fieldDni))
                                .addGap(78, 78, 78)))
                        .addGap(115, 115, 115)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldEmail)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(28, 28, 28))
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(boxSocio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(147, 147, 147))
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fieldFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(130, 130, 130)))
                                .addGap(109, 109, 109))))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(fieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(fieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(fieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(fieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(34, 34, 34)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119))
        );

        Calendar calendar = Calendar.getInstance();
        Date fechaActual = calendar.getTime();

        // Establecer la fecha actual como el límite máximo para la selección
        fieldFecha.setMaxSelectableDate(fechaActual);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void boxSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxSocioActionPerformed

    private void fieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNombreActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        //El boton para añadir el usuario
        String nombre, apellido, dni, email, telef, socio;
        Date fecha;
        
        nombre = fieldNombre.getText();
        apellido = fieldApellido.getText();
        dni = fieldDni.getText().toUpperCase();
        email = fieldEmail.getText();
        telef = fieldTelefono.getText();
        socio = boxSocio.getSelectedItem().toString();
        fecha = fieldFecha.getDate();

            // Verificar que los campos no estén vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || email.isEmpty() ||
                telef.isEmpty() || socio.isEmpty() || fecha == null) {
            // Mostrar un mensaje indicando que todos los campos deben estar llenos
            if(fecha == null){
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida");
            }
            else{
                // Mostrar un mensaje indicando que todos los campos deben estar llenos
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            }        
        } else {
                // Guardar la ruta de la imagen en la base de datos
                /*Usuario user = new Usuario();
                user.setDni(dni);
                user.setFoto(fileName);
                // Generar el nombre de archivo de destino con la extensión
                String extension = getExtension(new File(fileName));
                String nombreArchivoDestino = fileName;

                // Copiar la imagen al directorio "sources"
                String destinoEnResources = "src/main/resources/imagenes_usua/" + fileName;
                appController.guardarImagenEnCarpetaResources(filePath, destinoEnResources);
                appController.añadirUsuario(nombre, apellido, dni, email, telef, socio, fecha, nombreArchivoDestino);
                user.guardarImagen();*/
                limpiarCampos();
            // Llamar a la función para añadir el usuario solo si todos los campos están llenos
            if(!appController.validarDNI_NIE(dni)){
                JOptionPane.showMessageDialog(this, "El DNI o NIE no tiene un formato válido", "Formato Inválido", JOptionPane.WARNING_MESSAGE);
            }
            else{
                appController.añadirUsuario(nombre, apellido, dni, email, telef, socio, fecha);
                limpiarCampos();
            }
        }
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void fieldTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTelefonoActionPerformed

    private void fieldNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNombreKeyTyped
        // Verificar si la tecla presionada es una letra o un carácter especial permitido
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ' && c != '\'') {
            // Si no es una letra o un carácter especial permitido, consumir el evento para ignorarlo
            evt.consume();
        }
    }//GEN-LAST:event_fieldNombreKeyTyped

    private String getExtension(File file){
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return null; // No se encontró una extensión
        }
        return name.substring(lastIndexOf);
    }
    
    private void fieldTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldTelefonoKeyTyped
        // Obtener el texto actual en el campo de teléfono
        String telefono = fieldTelefono.getText();

        // Verificar si la tecla presionada es un número y si no excede la longitud de 9 caracteres
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || telefono.length() >= 9) {
            // Si no es un número o excede la longitud, consumir el evento para ignorarlo
            evt.consume();
        }
    }//GEN-LAST:event_fieldTelefonoKeyTyped

    private void fieldDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDniActionPerformed

    }//GEN-LAST:event_fieldDniActionPerformed

    private void fieldApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldApellidoKeyTyped
        // Verificar si la tecla presionada es una letra o un carácter especial permitido
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ' && c != '\'') {
            // Si no es una letra o un carácter especial permitido, consumir el evento para ignorarlo
            evt.consume();
        }
    }//GEN-LAST:event_fieldApellidoKeyTyped

    private void fieldDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldDniKeyTyped
        String dni = fieldDni.getText();
        if (dni.length() >= 9) {
            // Si ya hay 9 dígitos, consumir el evento para evitar más ingreso
            evt.consume();
        }
    }//GEN-LAST:event_fieldDniKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        appController.showJPanelDashboardAdmin(new GestionUsuarios());
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
        jFileChooser.setFileFilter(filtrado);
        int respuesta = jFileChooser.showOpenDialog(this);
        
        if (respuesta == JFileChooser.APPROVE_OPTION) {
        // El usuario ha seleccionado un archivo
        File selectedFile = jFileChooser.getSelectedFile();
        filePath = selectedFile.getAbsolutePath(); // Guarda la ruta del archivo
        //lblFilePath.setText("Ruta del archivo: " + filePath);
    }
    }//GEN-LAST:event_btnFileActionPerformed

    private void fieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEmailActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> boxSocio;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFile;
    private javax.swing.JTextField fieldApellido;
    private javax.swing.JTextField fieldDni;
    private javax.swing.JTextField fieldEmail;
    private com.toedter.calendar.JDateChooser fieldFecha;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
