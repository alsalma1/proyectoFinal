package com.mycompany.mavenproject1.views;

import com.mycompany.mavenproject1.controllers.AppController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
//-----------------------------------------------------
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.internet.AddressException;
import java.util.logging.Level;
import java.util.logging.Logger;
//-----------------------------------------------------

public class ReservarPista extends javax.swing.JPanel {
    //Variables email
    private static String emailFrom = "itsmeme330@gmail.com";
    private static String passwordFrom = "ofsyekghxldlbraj";
    private String emailTo;
    private String subject;
    private String content;
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    //--------------------------
    private static AppController appController = new AppController();
        
    private List<String> horas = new ArrayList<>();
    private List<Integer> pistas = new ArrayList<>();
    //Para guardar las horas y pistas de las reservas del usuario
    private List<String> horasUsuario = new ArrayList<>();
    private List<Integer> pistasUsuario = new ArrayList<>();
    
    private List<Integer> pistasEnMantenimiento = new ArrayList<>();
    private Date fechaSeleccionada;
    private String horaPistaSeleccionada ;
    private int pistaSeleccionada;
    public String userEmail;

    public List<String> getHoras() {
        return horas;
    }

    public List<Integer> getPistas() {
        return pistas;
    }
    //
     public List<String> getHorasUsuario() {
        return horasUsuario;
    }

    public List<Integer> getPistasUsuario() {
        return pistasUsuario;
    }
    //
    public List<Integer> getPistasMantenimiento() {
        return pistasEnMantenimiento;
    }
    
    public ReservarPista(String email) {
        this.userEmail = email;
        initComponents();
        // Cambiar el tamaño de la columna 0
        TableColumn column = tablePistas.getColumnModel().getColumn(0);
        column.setPreferredWidth(12);
        tablePistas.setRowHeight(30);

        configurarFechaPistaListener();
        llenarPrimeraColumnaConHoras();
        //cambiarColorPrimeraFila();
        
        tablePistas.setEnabled(true);
        
        usuarioReservaPista();
        //pistaOcupada();        
    }
    
    public void createEmail(String remitente, int numPista, String hora, String fecha) {
        //emailTo = "alamisalma949@gmail.com";
        emailTo = remitente;
        subject = "Pista Disponible para Reserva";
        content = "Nos complace informarte que la pista que seleccionaste está disponible para reservar.<br><br>"
                    + "DETALLES DE LA PISTA SELECCIONADA:<br>"
                    +"- Dia: "+fecha+"<br>"
                    +"- Hora: "+hora+"<br>"
                    +"- Numero pista: "+numPista+"<br>";

                
        
         // Simple mail transfer protocol
        mProperties = new Properties();
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587"); // Usar el puerto 587
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        mProperties.put("mail.smtp.starttls.enable", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
        
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");
            
        } catch (AddressException ex) {
            Logger.getLogger(ReservarPista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ReservarPista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendEmail() {
        try {
            if (mSession != null && mCorreo != null) {
                Transport mTransport = mSession.getTransport("smtp");
                mTransport.connect(emailFrom, passwordFrom);
                mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
                mTransport.close();

                JOptionPane.showMessageDialog(null, "Correo enviado correctamente!");
            } else {
                JOptionPane.showMessageDialog(null, "Error: La sesión de correo o el mensaje son nulos.");
            }
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(ReservarPista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ReservarPista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*public void showPistasMantenimiento(List<Integer> lista){
        for (int idPista : lista) {
            // Obtén el número de columna basado en el ID de Pista
            int colIndex = idPista;

            // Verifica si el índice de la columna es válido
            if (colIndex >= 1 && colIndex < tablePistas.getColumnCount()) {
                // Obtén el modelo de la tabla
                DefaultTableModel model = (DefaultTableModel) tablePistas.getModel();
                     
                // Itera a través de todas las filas de la tabla
                for (int row = 0; row < model.getRowCount(); row++) {
                    // Cambia el valor de la celda en la columna especificada a "Mantenimiento"
                    model.setValueAt("Mantenimiento", row, colIndex);
                }
                
                CustomCellRenderer customRenderer = new CustomCellRenderer();
                for (int i = 1; i < tablePistas.getColumnCount(); i++) {
                    tablePistas.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
                }
            }
        }
    }*/

    private void configurarFechaPistaListener() {  
        fechaPista.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    try {
                        obtenerFechaSeleccionada();
                    } catch (SQLException ex) {
                        Logger.getLogger(ReservarPista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        // Obtén la fecha actual
        Date fechaActual = new Date();

        // Crea un calendario y agrega 7 días a la fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date fechaMaxima = calendar.getTime();

        // Establece los límites mínimo y máximo para el JDateChooser
        fechaPista.setMinSelectableDate(fechaActual);
        fechaPista.setMaxSelectableDate(fechaMaxima);
    }
 
    public void obtenerFechaSeleccionada() throws SQLException {
        fechaSeleccionada = fechaPista.getDate();
        if (fechaSeleccionada != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha
            String fechaFormateada = sdf.format(fechaSeleccionada);
            //Crear una nueva fecha con el formato deseado
            try {
                fechaSeleccionada = sdf.parse(fechaFormateada);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            appController.buscarFecha(fechaSeleccionada);
            // Obtén el modelo de la tabla, que es necesario para actualizar las celdas.
            DefaultTableModel model = (DefaultTableModel) tablePistas.getModel();

            // Recorre la tabla y borra las celdas reservadas
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 1; col < model.getColumnCount(); col++) {
                    if ("Reservado".equals(model.getValueAt(row, col)) || "Mantenimiento".equals(model.getValueAt(row, col)) || "Ocupada".equals(model.getValueAt(row,col))) {
                        model.setValueAt("", row, col);
                    }
                }
            }

            // Llenar las celdas correspondientes en la tabla
            for (int i = 0; i < horas.size(); i++) {
                String hora = horas.get(i);
                int pista = pistas.get(i);

                // Encontrar la fila correspondiente a la hora
                int rowIndex = Integer.parseInt(hora.substring(0, 2)) - 9;

                // Encontrar la columna correspondiente a la pista
                int colIndex = pista;

                // Actualizar el valor de la celda en la tabla
                model.setValueAt("Reservado", rowIndex, colIndex);
            }

            // Obtén el modelo de la tabla y establece el renderizador personalizado en la columna 1 y siguientes
            CustomCellRenderer customRenderer = new CustomCellRenderer();
            for (int i = 1; i < tablePistas.getColumnCount(); i++) {
                tablePistas.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
            } 
            mostrarReservasUsuario(fechaFormateada);
            appController.pintarEstadosTablaReservas(tablePistas,fechaFormateada);
        }
    }
    
    public void mostrarReservasUsuario(String fecha){
        appController.userReservas(fecha);
        // Obtén el modelo de la tabla, que es necesario para actualizar las celdas.
        DefaultTableModel model = (DefaultTableModel) tablePistas.getModel();

        // Recorre la tabla y borra las celdas reservadas
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 1; col < model.getColumnCount(); col++) {
                if ("reservaUsuario".equals(model.getValueAt(row, col))) {
                    model.setValueAt("", row, col);
                }
            }
        }

        // Llenar las celdas correspondientes en la tabla
        for (int i = 0; i < horasUsuario.size(); i++) {
            String hora = horasUsuario.get(i);
            int pista = pistasUsuario.get(i);
            // Encontrar la fila correspondiente a la hora
            int rowIndex = Integer.parseInt(hora.substring(0, 2)) - 9;

            // Encontrar la columna correspondiente a la pista
            int colIndex = pista;

            // Actualizar el valor de la celda en la tabla
            model.setValueAt("reservaUsuario", rowIndex, colIndex);
        }

        // Obtén el modelo de la tabla y establece el renderizador personalizado en la columna 1 y siguientes
        CustomCellRenderer customRenderer = new CustomCellRenderer();
        for (int i = 1; i < tablePistas.getColumnCount(); i++) {
            tablePistas.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        } 
    }   

    private void llenarPrimeraColumnaConHoras() {
        // Crea una instancia del renderizador personalizado
        ColorPrimeraColumna firstColumnRenderer = new ColorPrimeraColumna(); // Cambia Color.RED al color que desees

        // Establece el renderizador en la primera columna
        tablePistas.getColumnModel().getColumn(0).setCellRenderer(firstColumnRenderer);
        DefaultTableModel model = (DefaultTableModel) tablePistas.getModel();
        model.setRowCount(0);
        // Llena la primera columna con las horas desde las 8:00 hasta las 21:00
        for (int hora = 9; hora <= 21; hora++) {
            model.addRow(new Object[]{String.format("%02d:00", hora)});
        }
    }
        
    public void usuarioReservaPista(){
        tablePistas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (fechaPista.getDate() != null) {
                    int row = tablePistas.getSelectedRow();
                    int col = tablePistas.getSelectedColumn();

                    // Verificar si la celda está vacía (sin texto)
                    DefaultTableModel model = (DefaultTableModel) tablePistas.getModel();
                    Object cellValue = model.getValueAt(row, col);
                    String estado = String.valueOf(cellValue);
                    System.out.println(estado);
                    if(cellValue == null){
                        if (row >= 0 && col >= 1 && model.getValueAt(row, col) == null) {
                            tablePistas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                            String hora = (String) model.getValueAt(row, 0);
                            int pista = col;
                            System.out.println(pista);
                            String emailUsuarioLogeado = userEmail;
                            appController.hacerLaReserva(hora, pista, fechaSeleccionada, emailUsuarioLogeado);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha
                            String fechaFormateada = sdf.format(fechaSeleccionada);
                            
                            mostrarReservasUsuario(fechaFormateada);
                            try {
                                //Crear una nueva fecha con el formato deseado
                                fechaSeleccionada = sdf.parse(fechaFormateada);
                            } catch (ParseException ex) {
                                Logger.getLogger(ReservarPista.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        } else {
                            appController.avisarUsuario(userEmail);
                            tablePistas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restablecer el cursor
                        }
                    }else if("Mantenimiento".equals(estado)){
                        JOptionPane.showMessageDialog(null, "Esta pista esta en mantenimiento.");
                    }else if("Ocupada".equals(estado)){
                        JOptionPane.showMessageDialog(null, "Esta pista esta ocupada.");
                    }else if("Reservado".equals(estado)){
                        pistaOcupada();
                    }else if("reservaUsuario".equals(estado)){
                        JOptionPane.showMessageDialog(null, "Esta pista ya la tienes reservada.");
                    }
                }else {
                    // Aviso al usuario para seleccionar una fecha primero
                    JOptionPane.showMessageDialog(null, "Selecciona una fecha antes de hacer una reserva.");
                }
            }
        });
    }
    
    public void actualizarTabla(Date fecha){
        // Establecer la fecha en el JDateChooser
        fechaPista.setDate(fecha); 
    }

    public void pistaOcupada() {
        tablePistas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tablePistas.getSelectedRow();
                int selectedColumn = tablePistas.getSelectedColumn();
                DefaultTableModel model = (DefaultTableModel) tablePistas.getModel();

                if (selectedRow != -1 && selectedColumn != -1 && selectedColumn > 0) {
                    Object cellValue = model.getValueAt(selectedRow, selectedColumn);

                    // Comprueba si el valor de la celda es "Reservado"
                    if ("Reservado".equals(cellValue)) {
                        String hora = (String) model.getValueAt(selectedRow, 0); // Obtiene la hora desde la fila
                        int pista = selectedColumn; // Obtiene el número de pista desde la columna
                        horaPistaSeleccionada = hora;
                        pistaSeleccionada = pista;
                        JOptionPane.showMessageDialog(null, "La pista está ocupada. Te notificaremos cuando esté disponible.");
                        appController.guardarPistaSeleccionada(userEmail, fechaSeleccionada, hora, pista);
                        //appController.buscarReservaSeleccionada(fechaSeleccionada, hora, pista);
                    } else if("Mantenimiento".equals(cellValue)){
                        JOptionPane.showMessageDialog(null, "La pista esta en matenimiento.");
                    } else if("Ocupada".equals(cellValue)){
                        JOptionPane.showMessageDialog(null, "La pista esta ocuapada.");
                    }
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        fechaPista = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePistas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(930, 560));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(930, 560));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        fechaPista.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaPistaPropertyChange(evt);
            }
        });
        fechaPista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fechaPistaKeyTyped(evt);
            }
        });

        jLabel1.setText("Elige una fecha:  ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fechaPista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(82, 82, 82))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fechaPista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Ocupada");

        jPanel4.setBackground(new java.awt.Color(40, 152, 238));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Mis reservas");

        jPanel2.setBackground(new java.awt.Color(255, 6, 6));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mantenimiento");

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tablePistas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tablePistas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", "Pista 1", "Pista 2", "Pista 3", "Pista 4", "Pista 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePistas.setGridColor(new java.awt.Color(255, 0, 51));
        jScrollPane1.setViewportView(tablePistas);
        if (tablePistas.getColumnModel().getColumnCount() > 0) {
            tablePistas.getColumnModel().getColumn(1).setResizable(false);
            tablePistas.getColumnModel().getColumn(2).setResizable(false);
            tablePistas.getColumnModel().getColumn(3).setResizable(false);
            tablePistas.getColumnModel().getColumn(4).setResizable(false);
            tablePistas.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Reserva una pista");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(67, 67, 67)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(302, 302, 302))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fechaPistaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaPistaKeyTyped
    }//GEN-LAST:event_fechaPistaKeyTyped

    private void fechaPistaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaPistaPropertyChange

    }//GEN-LAST:event_fechaPistaPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        //createEmail();
        sendEmail();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private com.toedter.calendar.JDateChooser fechaPista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePistas;
    // End of variables declaration//GEN-END:variables
}
