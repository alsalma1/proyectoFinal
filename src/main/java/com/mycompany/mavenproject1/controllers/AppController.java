package com.mycompany.mavenproject1.controllers;

import com.mycompany.mavenproject1.Dashboard;
import com.mycompany.mavenproject1.DashboardAdmin;
import com.mycompany.mavenproject1.DashboardUsuario;
import com.mycompany.mavenproject1.models.Admin;
import com.mycompany.mavenproject1.models.Estado;
import com.mycompany.mavenproject1.models.Pista;
import com.mycompany.mavenproject1.models.Reserva;
import com.mycompany.mavenproject1.models.ReservasYEstados;
import com.mycompany.mavenproject1.models.Usuario;
import com.mycompany.mavenproject1.views.AñadirUsuario;
import com.mycompany.mavenproject1.views.CustomCellRenderer;
import com.mycompany.mavenproject1.views.EditarUsuario;
import com.mycompany.mavenproject1.views.GestionUsuarios;
import com.mycompany.mavenproject1.views.MisReservas;
import com.mycompany.mavenproject1.views.PerfilUsuario;
import com.mycompany.mavenproject1.views.ReservarPista;
import com.mycompany.mavenproject1.views.UsuariosDesactivados;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class AppController {
    
    public static EditarUsuario editUser = new EditarUsuario();
    public static AñadirUsuario añadirUsuario = new AñadirUsuario();
    public static GestionUsuarios gestionUsuarios = new GestionUsuarios();
    public static UsuariosDesactivados usuariosDesactivados = new UsuariosDesactivados();
    public static PerfilUsuario perfil;
    public static ReservarPista reservarPista;
    public static MisReservas misReservas = new MisReservas(); 
    public static DashboardAdmin dashA = new DashboardAdmin();
    public static Dashboard dashBoard = new Dashboard();
    public static String email;
    public static DashboardUsuario dashU;
    public static Usuario usuario = new Usuario();
    public static Admin admin = new Admin();
    
    /* ------------------ Adminastrador --------------------- */
    
    public void verificarLogin(String email,String password){
        dashU = new DashboardUsuario(email);
        if(admin.esAdmin(email,password)){
            dashA.setVisible(true);
        } else if(usuario.esUsuario(email, password)){
            Usuario user = new Usuario();
            user.setEmail(email);
            String nombre = user.obtenerNombre();
            String mensaje = dashU.msg.getText()+" "+nombre;
            dashU.msg.setText(mensaje);
            dashU.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorerctos, intenta otra vez!");
            dashBoard.setVisible(true);
        }   
    }
    /* ----------------------------Manejo de paneles en la app------------------*/
    public static void showJPanelDashboardAdmin(JPanel p){
        p.setSize(926, 540);
        p.setLocation(0,0);
        
        dashA.content.removeAll();
        dashA.content.setLayout(new BorderLayout()); // Asegúrate de establecer un BorderLayout si aún no lo has hecho
        dashA.content.add(p, BorderLayout.CENTER);
        dashA.content.revalidate();
        dashA.content.repaint();
    }
    public void añadirEstadoPista(JDateChooser fechaInicioChooser, JDateChooser fechaFinChooser,JComboBox horaInicioComboBox,JComboBox horaFinComboBox,JComboBox pistaComboBox,boolean estado,JPanel panel) throws ParseException{
        Date fechaI = fechaInicioChooser.getDate();
        Date fechaF = fechaFinChooser.getDate();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicioComparar = dateFormat.format(fechaI);
        String fechaFinComparar = dateFormat.format(fechaF);
        
        String horaInicio = (String) horaInicioComboBox.getSelectedItem();
        String horaFin = (String) horaFinComboBox.getSelectedItem();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        
        LocalTime horaI = LocalTime.parse(horaInicio,formatter);
        LocalTime horaF = LocalTime.parse(horaFin,formatter);
        
        long fechaInicio = fechaI.getTime();
        java.sql.Date fechaInicioSQL = new java.sql.Date(fechaInicio);
            
        long fechaFin = fechaF.getTime();
        java.sql.Date fechaFinSQL = new java.sql.Date(fechaFin);
        
        if(fechaI == null || fechaF == null){
            JOptionPane.showMessageDialog(null, "Introduce una fecha.");          
        }else if(fechaF.before(fechaI)){
            JOptionPane.showMessageDialog(null, "Introduce unas fechas validas");
        }else if(fechaInicioComparar.equals(fechaFinComparar) && (horaF.isBefore(horaI) || horaI.equals(horaF))){
            JOptionPane.showMessageDialog(null, "Introduce una hora de fin o inicio valida");
        }else{
            String idPista = (String) pistaComboBox.getSelectedItem();
            String estadoPista = "Mantenimiento";
            if(estado == true){
                estadoPista = "Ocupada";
            }
            int respuesta = JOptionPane.showConfirmDialog(panel,
                "Quieres poner en mantenimiento la pista "+ idPista +"\ncon fecha inicio: " + fechaInicioSQL + " a las " + horaInicio +
                "\nfecha fin: " + fechaFinSQL + " a las " + horaFin + "\n todas las reservas seran desactivadas. Quieres continuar?",
                "Estado de la pista",
                JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                int idPistaInt = Integer.parseInt(idPista);
                addEstado(fechaInicioSQL, fechaFinSQL, idPista, horaInicio, horaFin,estadoPista);
                eliminarReservasMantenimiento(idPistaInt,fechaInicioSQL,fechaFinSQL,horaInicio,horaFin);
            }
        }    
    }
    public static void addEstado(java.sql.Date fechaInicio, java.sql.Date fechaFin, String idPista, String horaInicio,String horaFin,String estado) throws ParseException{
        Estado.insertarEstado(fechaInicio, fechaFin, idPista, horaInicio, horaFin,estado);
    }
    public static void eliminarReservasMantenimiento(int idPista,java.sql.Date fechaInicio,java.sql.Date fechaFin,String horaInicio,String horaFin){
            ArrayList<ReservasYEstados> reservasEncontradas = ReservasYEstados.obtenerReservasMantenimiento(idPista, fechaInicio, fechaFin, horaInicio, horaFin);
            HashMap<String, HashMap<Integer, ArrayList<String>>> informacionReservasUsuario = obtenerInformacionReservasPorUsuario(reservasEncontradas);
            for (ReservasYEstados reservas : reservasEncontradas) {
                reservas.eliminarReserva();
            }
            enviarReservasPorCorreo(informacionReservasUsuario);
    }
    public static HashMap<String, HashMap<Integer, ArrayList<String>>> obtenerInformacionReservasPorUsuario(ArrayList<ReservasYEstados> reservasEncontradas) {
        HashMap<String, HashMap<Integer, ArrayList<String>>> informacionReservasPorUsuario = new HashMap<>();

        for (ReservasYEstados reserva : reservasEncontradas) {
            String correoUsuario = reserva.getEmail();
            int idReserva = reserva.getId_reserva();
            String fechaHoraReserva = reserva.getFecha() + " " + reserva.getHora(); // Suponiendo que tienes métodos getFecha() y getHora()

            // Si el correo no existe en el mapa, crea una nueva entrada
            informacionReservasPorUsuario.putIfAbsent(correoUsuario, new HashMap<>());

            // Si el ID de reserva no existe para este usuario, crea una nueva lista
            informacionReservasPorUsuario.get(correoUsuario).putIfAbsent(idReserva, new ArrayList<>());

            // Agrega la fecha y hora de la reserva a la lista correspondiente al ID de reserva
            informacionReservasPorUsuario.get(correoUsuario).get(idReserva).add(fechaHoraReserva);
        }

        return informacionReservasPorUsuario;
    }
    public static void enviarReservasPorCorreo(HashMap<String, HashMap<Integer, ArrayList<String>>> informacionReservasPorUsuario) {
        for (Map.Entry<String, HashMap<Integer, ArrayList<String>>> usuario : informacionReservasPorUsuario.entrySet()) {
            String correoUsuario = usuario.getKey();
            HashMap<Integer, ArrayList<String>> reservasUsuario = usuario.getValue();
            StringBuilder contenidoCorreo = new StringBuilder();

            // Construir el contenido del correo para este usuario
            for (Map.Entry<Integer, ArrayList<String>> reserva : reservasUsuario.entrySet()) {
                int idReserva = reserva.getKey();
                ArrayList<String> fechasReserva = reserva.getValue();

                contenidoCorreo.append("Reserva ID: ").append(idReserva).append("<br>");

                for (String fechaHora : fechasReserva) {
                    contenidoCorreo.append("- ").append(fechaHora).append("<br>");
                }

                contenidoCorreo.append("<br>");
            }

            // Enviar el correo al usuario con la información de sus reservas
            enviarCorreo(correoUsuario, "Eliminación de Reservas", contenidoCorreo.toString());
        }
    }
    /* ------------------ Usuario --------------------- */
    public static void actualizarTablaPista(JTable table){
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        ArrayList<Pista> pistas = null;
        try {
            pistas = Pista.obtenerPistasDB();
        } catch (SQLException ex) {
            // Manejo de excepciones
        }
        for (Pista pista : pistas) {
            Object[] newRow = {pista.getId_pista()};
            modelo.addRow(newRow);
        }
        modelo.fireTableDataChanged();
    }
    
    public void comprobarCredencialesUsuario(String email, String contraseña){
        Usuario user = new Usuario();
        user.setEmail(email);
        user.setContrasena(contraseña);
        if(user.comprobarDatosUsuario()){
            this.email = email;
            dashU.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "¡Datos incorerctos o usuario no registrado en el sistema, intenta otra vez!");
        }
    }
    public static boolean comprobarEmailLabel(String email){
        if(Usuario.existeUsuarioParaReservas(email)){
            return true;
        }else{
            return false;
        }
    }
    public void eliminarReservaSeleccionadaAdmin(int idReserva){
        Reserva reserva = new Reserva();
        if(idReserva > 0){
            reserva.eliminarReservaSeleccionada(idReserva);
        }else{
            JOptionPane.showMessageDialog(null,"Elige una reserva primero.","Error", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    public void addPista(){
        Pista.addPista();
    }
    public static void pintarReservasUsuarioAdmin(JTable table) throws SQLException, ParseException{
        ArrayList<Reserva> reservas = Reserva.obtenerReservasDB();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        
        for(Reserva reserva : reservas){
            Object[] newRow = {reserva.getId_reserva(), reserva.getEmail_usuario(),reserva.getId_pista(),reserva.getFecha(),reserva.getHora(),"Reservada"};
            modelo.addRow(newRow);
        }
    }
    public static void pintarReservasBuscadasAdmin(JTable table,String email, int idPista, java.sql.Date fecha, int estado) throws SQLException{
        ArrayList<Reserva> reservasBuscadas = Reserva.buscarReservas(email,idPista,fecha,estado);
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        
        for(Reserva reserva : reservasBuscadas){
            if(reserva.getActiva() == 1){
                System.out.println(reserva.getId_reserva());
                Object[] newRow = {reserva.getId_reserva(), reserva.getEmail_usuario(),reserva.getId_pista(),reserva.getFecha(),reserva.getHora(),"Reservada"};
                modelo.addRow(newRow);
            }else{
                Object[] newRow = {reserva.getId_reserva(), reserva.getEmail_usuario(),reserva.getId_pista(),reserva.getFecha(),reserva.getHora(),"Cancelada"};
                modelo.addRow(newRow);
            }
        }
    }
    public void mostrarUsuarios(){
        Usuario usuario = new Usuario();

        // Llamar al método obtenerUsuarios
        List<Usuario> usuarios = usuario.obtenerUsuarios();
        gestionUsuarios.cargarUsuariosEnTabla();

        if (usuarios.isEmpty()) {
            gestionUsuarios.panelTable.setVisible(false);
            // Crear y configurar el JTextField para mostrar el mensaje
            JTextField mensajeTextField = new JTextField("No hay ningún usuario registrado en el sistema.");
            mensajeTextField.setEditable(false);
            mensajeTextField.setBorder(null);
            mensajeTextField.setBackground(gestionUsuarios.getBackground());  // Asigna el color de fondo del contenedor
            mensajeTextField.setBounds(300, 250, 500, 50);  // Ajusta las coordenadas y tamaño según tu interfaz
            mensajeTextField.setFont(new java.awt.Font("Segoe UI", 1, 16));
            mensajeTextField.setForeground(Color.red);
            // Agregar el JTextField al contenedor
            gestionUsuarios.add(mensajeTextField);
        } else {
            gestionUsuarios.cargarUsuariosEnTabla();
        }
        // Mostrar la ventana de GestionUsuarios
        dashA.showJPanel(new GestionUsuarios());
    }
    public void datosUsurios(){
        // Llamar al método obtenerUsuarios
        List<Usuario> usuarios = usuario.obtenerUsuarios();
        gestionUsuarios.cargarUsuariosEnTabla();
    }
    public static void eliminarEstado(String id_estado,int idPista,JTable table) throws SQLException{
        Estado.eliminarEstado(id_estado);
        pintarTablaEstadosPista(table,idPista);
    }
    public static void pintarTablaEstadosPista(JTable table,int idPista) throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        ArrayList<Estado> estados = Estado.obtenerEstadosDB(idPista);
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        for(Estado estado : estados){
            DateTime fechaInicio = estado.getFecha_inicio();
            DateTime fechaFin = estado.getFecha_fin();
            
            String fechaI = fechaInicio.toString(formatter);
            String fechaF = fechaFin.toString(formatter);
            
            Object[] newRow = {estado.getId_estado(),estado.getEstado(),fechaI,fechaF};
            modelo.addRow(newRow);
        }
        modelo.fireTableDataChanged();
    }
    public void añadirUsuario(String nombre, String apellido, String dni, String email, String telef, String socio, Date fecha){
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setEmail(email);
        usuario.setTelefono(telef);
        usuario.setFoto("th (1).jpeg");
        
        //Cambiar el formato de la fecha a "yyyy-MM-dd"
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String birth = simpleDateFormat.format(fecha);
        
        //Cambiar la fecha de String a objeto Date
        try{
            
            Date fechaNacimiento = simpleDateFormat.parse(birth);
            // Convertir java.util.Date a java.sql.Date
            java.sql.Date fechaNacimientoSQL = new java.sql.Date(fechaNacimiento.getTime());
            usuario.setFecha_nacimiento(fechaNacimientoSQL);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(socio.equals("Sí")) {
            usuario.setSocio(Boolean.TRUE);
        } else {
            usuario.setSocio(Boolean.FALSE);
        }
        
        //Generar contraseña aleatoria
        String randomString = generarRandomString();
        String contraseña = nombre.toLowerCase()+randomString;
        usuario.setContrasena(contraseña);
        
        if(usuario.existeUsuario() || admin.existeMailAdmin(email)){
            JOptionPane.showMessageDialog(null, "Este email o Dni ya estan registrados en el sistema, intenta otra vez!");
        }
        else{
            usuario.insertarUsuario();
            actualizarYMostrarUsuarios();
            showJPanelDashboardAdmin(new GestionUsuarios());
        }
    }
    
    public boolean validarDNI_NIE(String dni_nie) {
        // Expresión regular para validar DNI (8 dígitos seguidos de una letra) o NIE (letra seguida de 7 dígitos y una letra)
        String regexDNI = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$";
        String regexNIE = "^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$";

        // Comprobar si cumple con el formato de DNI o NIE
        return dni_nie.matches(regexDNI) || dni_nie.matches(regexNIE);
    }

    public static String generarRandomString() {
        // Lista de caracteres que queremos considerar
        List<Character> characters = new ArrayList<>();
        for (char ch = 'a'; ch <= 'z' || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'); ch++) {
            characters.add(ch);
        }
        // Generar la cadena aleatoria
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.size());
            randomString.append(characters.get(randomIndex));
        }
        return randomString.toString();
    }
    
    public void mostrarDatosUsuario(String dni){
        Usuario user = usuario.datosUsuarioConDni(dni);
        String contraseña = "";
        // Imprime los datos de los usuarios
        System.out.println(user.getNombre());
        editUser.fieldNombre.setText(user.getNombre());
        editUser.fieldApellido.setText(user.getApellido());
        editUser.fieldDni.setText(user.getDni());
        editUser.fieldTelefono.setText(user.getTelefono());
        editUser.fieldEmail.setText(user.getEmail());
        editUser.fieldFecha.setDate(user.getFecha_nacimiento());
        contraseña = user.getContrasena();
        // Establecer selección del JComboBox basado en user.getSocio()
        if (user.getSocio()) {
            editUser.boxSocio.setSelectedItem("Sí");
        } else {
            editUser.boxSocio.setSelectedItem("No");
        }
        editUser.setContraseña(contraseña);
        showJPanelDashboardAdmin(editUser);
    }
    
    public void editarUsuario(String nombre, String apellido, String dni, String email, String telef, String socio, Date fecha, String contraseña){
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setTelefono(telef);
        user.setDni(dni);
        user.setEmail(email);
        user.setContrasena(contraseña);
        
        java.sql.Date fechaNacimientoSQL = new java.sql.Date(fecha.getTime());
        user.setFecha_nacimiento(fechaNacimientoSQL);  
        
        if(socio.equals("Sí")) {
            user.setSocio(Boolean.TRUE);
        } else {
            user.setSocio(Boolean.FALSE);
        }
        
        try {
            user.editarUsuario();
            JOptionPane.showMessageDialog(null, "Usuario modificado correctamente!");
            actualizarYMostrarUsuarios();
            showJPanelDashboardAdmin(new GestionUsuarios());
            // Actualiza y muestra la ventana de gestión de usuarios
        } catch (Exception e) {
            // Si hay un error, muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al editar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarYMostrarUsuarios(){

        // Llamar al método obtenerUsuarios para obtener los datos actualizados
        List<Usuario> usuarios = usuario.obtenerUsuarios();

        // Cerrar la ventana anterior de GestionUsuarios si está abierta
        if (gestionUsuarios != null && gestionUsuarios.isVisible()) {
            dashA.showJPanel(new GestionUsuarios());
        }

        gestionUsuarios.cargarUsuariosEnTabla();

        // Mostrar la ventana de GestionUsuarios
        showJPanelDashboardAdmin(new GestionUsuarios());
    }
    public void desactivarUsuario(String dni, GestionUsuarios gestionUsuarios){
        Usuario user = new Usuario();
        user.setDni(dni);
        user.desactivar();
        JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente!");
        gestionUsuarios.setVisible(false);
        actualizarYMostrarUsuarios();
    }
    
    public void mostrarUsuariosDesactivados(){

        // Llamar al método obtenerUsuarios
        List<Usuario> usuarios = usuario.obtenerUsuariosDesactivados();
        UsuariosDesactivados usuariosDesactivados = new UsuariosDesactivados();

        if (usuarios.isEmpty()) {
            usuariosDesactivados.panelTable.setVisible(false);
            // Crear y configurar el JTextField para mostrar el mensaje
            JTextField mensajeTextField = new JTextField("No hay ningún usuario desactivado del sistema");
            mensajeTextField.setEditable(false);
            mensajeTextField.setBorder(null);
            mensajeTextField.setBackground(usuariosDesactivados.getBackground());  // Asigna el color de fondo del contenedor
            mensajeTextField.setBounds(300, 250, 500, 50);  // Ajusta las coordenadas y tamaño según tu interfaz
            mensajeTextField.setFont(new java.awt.Font("Segoe UI", 1, 16));
            mensajeTextField.setForeground(Color.red);
            // Agregar el JTextField al contenedor
            usuariosDesactivados.add(mensajeTextField);
        } else {
            usuariosDesactivados.mostrarTabla();
        }
        
        showJPanelDashboardAdmin(new UsuariosDesactivados());
    }
    
    public void activarUsuario(String dni, UsuariosDesactivados usuariosDesactivados){
        Usuario user = new Usuario();
        user.setDni(dni);
        user.activarUsuario();
        JOptionPane.showMessageDialog(null, "El usuario con DNI "+dni+" se ha activado correctamente!");
        mostrarUsuariosDesactivados();
        
    }

    public void mostrarPerfilUsuario(String email){        
        Usuario user = new Usuario();
        perfil = new PerfilUsuario();
        user.setEmail(email);

        List<Usuario> infoUser = user.datosUsuarioLogeado();
        for (Usuario usuario : infoUser) {
            perfil.labelNombre.setText(usuario.getNombre()+" "+usuario.getApellido());
            perfil.labelDni.setText(usuario.getDni());
            
            // Convertir la fecha al formato dd-MM-yyyy
            String fechaFormateada = convertirFormatoFecha(usuario.getFecha_nacimiento());
            perfil.labelFechaN.setText(fechaFormateada);
            perfil.labelEmail.setText(usuario.getEmail());
            perfil.labelTelefono.setText(usuario.getTelefono());
            
            //Mostrar la imagen del usuario
            String nombreImagen = usuario.getFoto();
            String rutaImagen = "/imagenes_usua/" + nombreImagen;
            // Cargar la imagen desde el archivo en la ruta proporcionada
            ImageIcon imagenIcon = new ImageIcon(getClass().getResource(rutaImagen));
            perfil.fotoUser.setIcon(imagenIcon);
            
            //Font
            Font fuentePersonalizada = new Font("Arial", Font.PLAIN, 14);
            perfil.labelEmail.setFont(fuentePersonalizada);
            perfil.labelDni.setFont(fuentePersonalizada);
            perfil.labelTelefono.setFont(fuentePersonalizada);
            perfil.labelFechaN.setFont(fuentePersonalizada);
        }
        dashU.showJPanel(perfil);
    }
    
    public String convertirFormatoFecha(Date fecha) {
        try {
            // Crear un formato para la fecha en formato dd-MM-yyyy
            SimpleDateFormat formatoDestino = new SimpleDateFormat("dd-MM-yyyy");

            // Formatear la fecha al nuevo formato
            return formatoDestino.format(fecha);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void mostrarMisReservas(String email){
        misReservas = new MisReservas();
        Reserva reserva = new Reserva();
        reserva.setEmail_usuario(email);
        misReservas.setUserEmail(email);
        List<Reserva> mevasReservas = reserva.reservasUsuarioLogeado();
        misReservas.cargarReservasEnTabla(mevasReservas);
        misReservas.setVisible(true);
        dashU.showJPanel(misReservas);
    }

    public void avisarUsuario(String emailUsuarioLogeado) {

    }

    /* ------------------ Login --------------------- */
    
        
    /* ------------------ Reservas --------------------- */
    public void mostrarPistas(String email){
        reservarPista = new ReservarPista(email);
        dashU.showJPanel(reservarPista);
    }
    public static void llenarPrimeraColumnaConHoras(DefaultTableModel modelo) {
    modelo.setRowCount(0);
    // Llena la primera columna con las horas desde las 8:00 hasta las 21:00
    for (int hora = 9; hora <= 21; hora++) {
        modelo.addRow(new Object[]{String.format("%02d:00", hora)});
        }
    }
    public void buscarFecha(Date fechaSeleccionada){
        Reserva reserva = new Reserva();
        java.sql.Date fechaSQL = new java.sql.Date(fechaSeleccionada.getTime()); // Convertir a java.sql.Date
        reserva.setFecha(fechaSQL);
        
        List<Reserva> resultados = reserva.existeFecha();
        
        // Limpiar las listas para evitar duplicados
        reservarPista.getHoras().clear();
        reservarPista.getPistas().clear();

        // Iterar a través de la lista de resultados
        for (Reserva datosReserva : resultados) {
            String hora = datosReserva.getHora();
            int idPista = datosReserva.getId_pista();

            // Agregar la hora y la pista a las listas
            reservarPista.getHoras().add(hora);
            reservarPista.getPistas().add(idPista);
        }
    }
    
    public void hacerLaReserva(String hora, int pista, Date fecha, String email){
        //Obtener fecha actual
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        // Formatea la fecha en el formato deseado
        String fechaFormateada = fechaActual.format(formatterFecha);
       //Obtener hora actual
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaActualFormateada = horaActual.format(formatter);
        Reserva reserva = new Reserva();
        reserva.setEmail_usuario(email);
        
        java.sql.Date fechaSeleccionada = new java.sql.Date(fecha.getTime());
        reserva.setFecha(fechaSeleccionada);
        reserva.setId_pista(pista);
        reserva.setHora(hora);
        LocalTime horaParaComparar = LocalTime.parse(hora, formatter);
        
        // Convierte la cadena "fechaFormateada" en un objeto Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaFormateadaDate = null;
        try {
            fechaFormateadaDate = dateFormat.parse(fechaFormateada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
        if(reserva.comprobarReserva()){
            JOptionPane.showMessageDialog(null, "No puedes reservar diferentes pistas en la misma hora y fecha!");
        }
        else if(horaParaComparar.isBefore(horaActual) && fechaFormateadaDate.equals(fecha)){
            JOptionPane.showMessageDialog(null, "No puedes reservar en una hora que ya ha pasado!");
        }
        else{
            reserva.reservar();
        }
    }
     
    public void userReservas(String fecha){
        Reserva reserva = new Reserva();
        //Convertir fecha de String a Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(fecha);
            java.sql.Date fechaSQL = new java.sql.Date(date.getTime());
            reserva.setEmail_usuario(reservarPista.userEmail);
            reserva.setFecha(fechaSQL);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Reserva> reservasUsuario = reserva.obtenerReservasUsuario();

        // Limpiar las listas para evitar duplicados
        reservarPista.getHorasUsuario().clear();
        reservarPista.getPistasUsuario().clear();
        
        for (Reserva reserva1 : reservasUsuario) {
            String hora = reserva1.getHora();
            int idPista = reserva1.getId_pista();
            // Agregar la hora y la pista a las listas
            reservarPista.getHorasUsuario().add(hora);
            reservarPista.getPistasUsuario().add(idPista);
        }
    }
    
    public void eliminarReserva(int idReserva, MisReservas misReservas, String email, int numPista, String hora, String fecha){
        Reserva reserva = new Reserva();
        ReservarPista reservarPista = new ReservarPista(email);
        reserva.setId_reserva(idReserva);
        
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaHoy = fechaActual.format(formatoFecha);

        //No se puede eliminar una reserva el mimsmo dia
        if(fechaHoy.equals(fecha)){
            JOptionPane.showMessageDialog(null, "No puedes eliminar la reserva al mismo dia!");
        }
        else{
            reserva.eliminarReserva();
            //----------No olvidar poner un mensaje de confirmacion antes de borrar la reserva
            JOptionPane.showMessageDialog(null, "Tu reserva con numero "+idReserva+" se eliminó correctamente!");
            mostrarMisReservas(email);

            //Buscar el id de la reserva eliminada en la tabla reservasseleccionadas y comprobar si la fecha de la reserva es superior a la fecha actual
            if(reserva.reservaSeleccionadaEliminada()){
                //String usu = reserva.usuarioReservaSelecionada();
                String[] usuarios = reserva.usuariosReservaSeleccionada();
                if(reserva.fechaEsSuperior()){
                    for (String usuario : usuarios) {
                        reservarPista.createEmail(usuario, numPista, hora, fecha);
                        reservarPista.sendEmail();
                    }
                    //reservarPista.createEmail(usu, numPista, hora, fecha);
                    //reservarPista.sendEmail();
                }
            }
        }
    }
    
    
    public void guardarImagenEnCarpetaResources(String sourcePath, String destinationPath) {
        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);

        // Copiar el archivo desde la fuente al destino
        try {
            Path source = sourceFile.toPath();
            Path destination = destinationFile.toPath();
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void buscarReservaSeleccionada(Date fecha, String hora, int pista){
       Reserva reserva = new Reserva();
       
       java.sql.Date fechaSeleccionada = new java.sql.Date(fecha.getTime());
       reserva.setFecha(fechaSeleccionada);
       reserva.setHora(hora);
       reserva.setId_pista(pista);
       if(reserva.reservaSeleccionada()){
           System.out.println("Esta desactivada");
        }
    }
    
    public void guardarPistaSeleccionada(String email, Date fecha, String hora, int pista){
        Reserva reserva = new Reserva();
        java.sql.Date fechaSeleccionada = new java.sql.Date(fecha.getTime());
        reserva.setFecha(fechaSeleccionada);
        reserva.setHora(hora);
        reserva.setId_pista(pista);
        int idReserva = reserva.buscarIdReserva();
        guardarReserva(idReserva,email);
    }
    
    public void guardarReserva(int idReserva, String email){
        Reserva reserva = new Reserva();
        reserva.setEmail_usuario(email);
        reserva.setId_reserva(idReserva);
        reserva.guardarReservaSeleccionada();
    }
    
    public void desactivarReservasPasadas(){
        Reserva reserva = new Reserva();
        Calendar calendario = Calendar.getInstance();
        Date fechaActual = calendario.getTime();
        java.sql.Date fechaSql = new java.sql.Date(fechaActual.getTime());
        reserva.setFecha(fechaSql);
        reserva.desactivarReservasPasadas();
        
        //Si la fecha de la reserva es igual a la fecha de hoy comparar las horas
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormateada = horaActual.format(formatoHora);
        reserva.setHora(horaFormateada);
        reserva.desactivarReservasPasadasPorHora();
    }
        public static void pintarTablaReservas(JTable table, String fecha) throws SQLException, ParseException{
        ArrayList<Reserva> reservas = Reserva.obtenerReservasDB();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        
        // Limpiar todas las celdas
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 1; j < modelo.getColumnCount(); j++) {
                modelo.setValueAt("", i, j);
            }
        }
        
        for(Reserva reserva : reservas){
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaTxt = formato.format(reserva.getFecha());
            if(fechaTxt.equals(fecha)){
                String horaReserva = reserva.getHora();
                String pistaReservada = "Pista " + reserva.getId_pista();
                int rowIndex = -1;
                for (int i = 0; i < modelo.getRowCount(); i++) {
                if (modelo.getValueAt(i, 0).equals(horaReserva)) {
                    rowIndex = i;
                    
                    }
                }
                if (rowIndex != -1) {
                // Buscar la columna correspondiente a la pista
                int pistaColumnIndex = -1;
                for (int j = 1; j < modelo.getColumnCount(); j++) {  // Comenzar desde 1 para omitir la columna "horas"
                    if (modelo.getColumnName(j).equals(pistaReservada)) {
                        pistaColumnIndex = j;
                        
                        }
                    }
                modelo.setValueAt("Reservado", rowIndex, pistaColumnIndex);
                }
            }
        }
        CustomCellRenderer customRenderer = new CustomCellRenderer();
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }
        pintarEstadosTablaReservas(table,fecha);
    }
        
        public static void pintarEstadosTablaReservas(JTable table,String fecha) throws SQLException{
        ArrayList<Estado> estados = Estado.obtenerEstadosListDB();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        
        for(Estado estado : estados){
            // Obtener la fecha en formato yyyy-MM-dd
            String fechaInicio = estado.getFecha_inicio().toString("yyyy-MM-dd");
            // Obtener la hora en formato HH:mm
            String horaInicio = estado.getFecha_inicio().toString("HH:mm");
            
            String fechaFin = estado.getFecha_fin().toString("yyyy-MM-dd");

            String horaFin = estado.getFecha_fin().toString("HH:mm");
            
            if(fechaInicio.equals(fechaFin) && fechaInicio.equals(fecha)){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime horaI = LocalTime.parse(horaInicio, formatter);
                LocalTime horaF = LocalTime.parse(horaFin, formatter);
                String pistaEstado = "Pista " + estado.getId_pista();
                while(!horaI.isAfter(horaF)){
                    String horaString = horaI.format(formatter);
                    int rowIndex = -1;
                    for (int i = 0; i < modelo.getRowCount(); i++) {
                    if (modelo.getValueAt(i, 0).equals(horaString)) {
                        rowIndex = i;
                        
                        }
                    }
                    if (rowIndex != -1) {
                    // Buscar la columna correspondiente a la pista
                    int pistaColumnIndex = -1;
                    for (int j = 1; j < modelo.getColumnCount(); j++) {  // Comenzar desde 1 para omitir la columna "horas"
                        if (modelo.getColumnName(j).equals(pistaEstado)) {
                            pistaColumnIndex = j;
                            
                            }
                        }
                    modelo.setValueAt(estado.getEstado(), rowIndex, pistaColumnIndex);
                    }                    
                    horaI = horaI.plusHours(1);                  
                }
            }else{
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechasInicio = LocalDate.parse(fechaInicio, dateFormatter);
                LocalDate fechasFin = LocalDate.parse(fechaFin, dateFormatter);
                String fechaEscogida = "";
                while (!fechasInicio.isAfter(fechasFin)) {
                    String fechaActual = fechasInicio.format(dateFormatter);
                    if(fechaActual.equals(fecha)){
                        fechaEscogida = fechaActual;
                    }
                    fechasInicio = fechasInicio.plusDays(1); // Avanzar al siguiente día
                }
                if(fechaEscogida.equals(fechaInicio)){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horaFechaInicio = LocalTime.parse(horaInicio, formatter);
                    LocalTime ultimaHora = LocalTime.parse("21:00", formatter);
                    String pistaEstado = "Pista " + estado.getId_pista();
                    
                    while (!horaFechaInicio.isAfter(ultimaHora)) {
                        String horaString = horaFechaInicio.format(formatter);
                        int rowIndex = -1;
                        for (int i = 0; i < modelo.getRowCount(); i++) {
                        if (modelo.getValueAt(i, 0).equals(horaString)) {
                            rowIndex = i;
                            
                            }
                        }
                        if (rowIndex != -1) {
                        // Buscar la columna correspondiente a la pista
                        int pistaColumnIndex = -1;
                        for (int j = 1; j < modelo.getColumnCount(); j++) {  // Comenzar desde 1 para omitir la columna "horas"
                            if (modelo.getColumnName(j).equals(pistaEstado)) {
                                pistaColumnIndex = j;
                                
                                }
                            }
                        modelo.setValueAt(estado.getEstado(), rowIndex, pistaColumnIndex);
                        }                    
                        horaFechaInicio = horaFechaInicio.plusHours(1);
                    }
                    
                }else if(fechaEscogida.equals(fechaFin)){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horaFechaFin = LocalTime.parse(horaFin, formatter);
                    LocalTime primeraHora = LocalTime.parse("09:00", formatter);
                    String pistaEstado = "Pista " + estado.getId_pista();
                    
                    while (!primeraHora.isAfter(horaFechaFin)) {
                        String horaString = primeraHora.format(formatter);
                        int rowIndex = -1;
                        for (int i = 0; i < modelo.getRowCount(); i++) {
                        if (modelo.getValueAt(i, 0).equals(horaString)) {
                            rowIndex = i;
                            
                            }
                        }
                        if (rowIndex != -1) {
                        // Buscar la columna correspondiente a la pista
                        int pistaColumnIndex = -1;
                        for (int j = 1; j < modelo.getColumnCount(); j++) {  // Comenzar desde 1 para omitir la columna "horas"
                            if (modelo.getColumnName(j).equals(pistaEstado)) {
                                pistaColumnIndex = j;
                                
                                }
                            }
                        modelo.setValueAt(estado.getEstado(), rowIndex, pistaColumnIndex);
                        }                    
                        primeraHora = primeraHora.plusHours(1);
                    }
                }else if(fechaEscogida.equals(fecha)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime primeraHora = LocalTime.parse("09:00", formatter);
                    LocalTime ultimaHora = LocalTime.parse("21:00", formatter);
                    String pistaEstado = "Pista " + estado.getId_pista();
                    
                    while (!primeraHora.isAfter(ultimaHora)) {
                        String horaString = primeraHora.format(formatter);
                        int rowIndex = -1;
                        for (int i = 0; i < modelo.getRowCount(); i++) {
                        if (modelo.getValueAt(i, 0).equals(horaString)) {
                            rowIndex = i;
                            
                            }
                        }
                        if (rowIndex != -1) {
                        // Buscar la columna correspondiente a la pista
                        int pistaColumnIndex = -1;
                        for (int j = 1; j < modelo.getColumnCount(); j++) {  // Comenzar desde 1 para omitir la columna "horas"
                            if (modelo.getColumnName(j).equals(pistaEstado)) {
                                pistaColumnIndex = j;
                                
                                }
                            }
                        modelo.setValueAt(estado.getEstado(), rowIndex, pistaColumnIndex);
                        }                    
                        primeraHora = primeraHora.plusHours(1);
                    }
                }
            }     
        }  
    }
    /*--------------------------------Correo----------------------------*/
    public static void enviarCorreo(String destinatario, String asunto, String contenido) {
        final String emailFrom = "itsmeme330@gmail.com";
        final String passwordFrom = "ofsyekghxldlbraj";

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587"); // Usar el puerto 587
        propiedades.setProperty("mail.smtp.user", emailFrom);
        propiedades.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");


        Session sesion = Session.getDefaultInstance(propiedades);

        try {
            MimeMessage correo = new MimeMessage(sesion);
            correo.setFrom(new InternetAddress(emailFrom));
            correo.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            correo.setSubject(asunto);
            correo.setText(contenido, "ISO-8859-1", "html");
            
            if (sesion != null && correo != null) {
                Transport mTransport = sesion.getTransport("smtp");
                mTransport.connect(emailFrom, passwordFrom);
                mTransport.sendMessage(correo, correo.getRecipients(Message.RecipientType.TO));
                mTransport.close();

                JOptionPane.showMessageDialog(null, "Correo enviado correctamente!");
            } else {
                JOptionPane.showMessageDialog(null, "Error: La sesión de correo o el mensaje son nulos.");
            }
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }  
}
