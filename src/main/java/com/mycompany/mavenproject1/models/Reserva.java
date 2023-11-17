package com.mycompany.mavenproject1.models;

import com.mycompany.mavenproject1.config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class Reserva {
    
    private int id_reserva;
    private String email_usuario;
    private int id_pista;
    private Date fecha;
    private String hora;
    private int activa;
    
    //Getters and Setters
    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public int getId_pista() {
        return id_pista;
    }

    public void setId_pista(int id_pista) {
        this.id_pista = id_pista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public int getActiva(){
        return activa;
    }
    
    public void setActiva(int activa){
        this.activa = activa;
    }
    //Metodos
    public List<Reserva> existeFecha() {        
        List<Reserva> datosReserva = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener la cuenta de registros
            String sqlCount = "SELECT COUNT(*) AS count FROM reservas WHERE fecha = ? AND activa = 1";
            preparedStatement = connection.prepareStatement(sqlCount);

            // Establecer los valores para los parámetros
            preparedStatement.setDate(1, getFecha());

            // Ejecutar la consulta
            resultSet = preparedStatement.executeQuery();
            // Verificar si hay al menos un registro
            if (resultSet != null && resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0) {
                    // Si count es mayor que 0, realizar otra consulta para obtener la hora y el id_pista
                    String sqlSelect = "SELECT hora, id_pista FROM reservas WHERE fecha = ? AND activa = 1";
                    preparedStatement = connection.prepareStatement(sqlSelect);
                    preparedStatement.setDate(1, getFecha());

                    resultSet = preparedStatement.executeQuery();

                    // Verificar si hay resultados
                    while (resultSet != null && resultSet.next()) {
                        String hora = resultSet.getString("hora");
                        int idPista = resultSet.getInt("id_pista");

                        // Crear un objeto datosReserva para cada resultado y agregarlo a la lista
                        Reserva reserva = new Reserva();
                        reserva.setHora(hora);
                        reserva.setId_pista(idPista);

                        datosReserva.add(reserva);
                    }
                }
            }
            return datosReserva; // Devolver la lista de resultados
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Devolver una lista vacía en caso de error
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }  
    
        /*public List<Integer> pistasMantenimiento() {
        
        List<Integer> pistas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener la cuenta de registros
            String sql = "SELECT id_pista FROM pistas WHERE estado = 'Mantenimiento'";
            preparedStatement = connection.prepareStatement(sql);

            // Ejecutar la consulta
            resultSet = preparedStatement.executeQuery();
            
            // Procesa los resultados
            while (resultSet.next()) {
                int idPista = resultSet.getInt("id_pista");
                pistas.add(idPista);
            }
            return pistas; // Devolver la lista de resultados
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Devolver una lista vacía en caso de error
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }  */
        
    public void reservar(){       
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener usuarios
            String sql = "INSERT INTO reservas (email_usuario, id_pista, fecha, hora, activa) VALUES (?, ?, ?, ?, 1)";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setString(1, getEmail_usuario());
            preparedStatement.setInt(2, getId_pista());
            preparedStatement.setDate(3, getFecha());
            preparedStatement.setString(4, getHora());
            
            // Ejecutar la consulta de inserción
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Has reservado la pista "+getId_pista()+", el dia "+getFecha()+" a las "+getHora());
        } catch (SQLException e) {
            //
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                 // Manejo de errores: imprime el error en la consola
            }
        }
    }

    public List<Reserva> obtenerReservasUsuario(){
        List<Reserva> reservasUsuario = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            String sqlCount = "SELECT * FROM reservas WHERE email_usuario = ? AND fecha = ? AND activa = 1";
            preparedStatement = connection.prepareStatement(sqlCount);
            preparedStatement.setString(1, getEmail_usuario());
            preparedStatement.setDate(2, getFecha());
            resultSet = preparedStatement.executeQuery();
            int rowCount = 0;
            while (resultSet != null && resultSet.next()) {
                String hora = resultSet.getString("hora");
                int idPista = resultSet.getInt("id_pista");

                // Crear un objeto datosReserva para cada resultado y agregarlo a la lista
                Reserva reserva = new Reserva();
                reserva.setHora(hora);
                reserva.setId_pista(idPista);
                reservasUsuario.add(reserva);
                rowCount++;
            }
            
            return reservasUsuario; // Devolver la lista de resultados
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Devolver una lista vacía en caso de error
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Reserva> reservasUsuarioLogeado(){
        List<Reserva> misReservas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            String sqlCount = "SELECT * FROM reservas WHERE email_usuario = ? AND activa = 1";
            preparedStatement = connection.prepareStatement(sqlCount);
            preparedStatement.setString(1, getEmail_usuario());
            resultSet = preparedStatement.executeQuery();
            int rowCount = 0;
            while (resultSet != null && resultSet.next()) {
                int numReserva = resultSet.getInt("id_reserva");
                Date fecha = resultSet.getDate("fecha");
                String hora = resultSet.getString("hora");
                int idPista = resultSet.getInt("id_pista");

                // Crear un objeto datosReserva para cada resultado y agregarlo a la lista
                Reserva reserva = new Reserva();
                reserva.setId_reserva(numReserva);
                reserva.setHora(hora);
                reserva.setId_pista(idPista);
                reserva.setFecha(fecha);
                misReservas.add(reserva);
                rowCount++;
            }
            
            return misReservas; // Devolver la lista de resultados
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Devolver una lista vacía en caso de error
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void eliminarReserva(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement y parámetros
            String sql = "UPDATE reservas SET activa = 0 WHERE id_reserva = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, getId_reserva());

            // Ejecutar la consulta
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Este metodo tiene que comprobar si existe una reserva del mismo usuario en la misma fecha y hora pero diferentes pistas
    public boolean comprobarReserva(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener usuarios
            String sql = "SELECT COUNT(*) AS count FROM reservas WHERE email_usuario = ? AND fecha = ? AND hora = ? AND id_pista != ? AND activa = 1";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setString(1, getEmail_usuario());
            preparedStatement.setDate(2, getFecha());
            preparedStatement.setString(3, getHora());
            preparedStatement.setInt(4, getId_pista());
            
            // Ejecutar la consulta de inserción
            resultSet = preparedStatement.executeQuery();
            
            // Verificar si hay al menos un registro
            if (resultSet != null && resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Boolean reservaSeleccionada(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener reservas
            String sql = "SELECT activa FROM reservas WHERE fecha = ? AND hora = ? AND id_pista = ? AND activa = 1";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setDate(1, getFecha());
            preparedStatement.setString(2, getHora());
            preparedStatement.setInt(3, getId_pista());
            
            // Ejecutar la consulta de inserción
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet != null && resultSet.next()) {
                int activa = resultSet.getInt("activa");
                return activa == 0;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public int buscarIdReserva(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idReserva = -1;
        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener la cuenta de registros
            String sql = "SELECT id_reserva FROM reservas WHERE fecha = ? AND hora = ? AND id_pista = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, getFecha());
            preparedStatement.setString(2, getHora());
            preparedStatement.setInt(3, getId_pista());
            resultSet = preparedStatement.executeQuery();
            
            // Procesa los resultados
            if (resultSet.next()) {
                idReserva = resultSet.getInt("id_reserva");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idReserva;
    }
    
    public void guardarReservaSeleccionada(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener usuarios
            String sql = "INSERT INTO reservasseleccionadas (emailUsuario, idReserva) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setString(1, getEmail_usuario());
            preparedStatement.setInt(2, getId_reserva());

            // Ejecutar la consulta de inserción
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                 // Manejo de errores: imprime el error en la consola
            }
        }
    }
    
    public void desactivarReservasPasadas(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement y parámetros
            String sql = "UPDATE reservas SET activa = 0 WHERE fecha < ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, getFecha());

            // Ejecutar la consulta
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void desactivarReservasPasadasPorHora() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement y parámetros
            String sql = "UPDATE reservas SET activa = 0 WHERE fecha = ? AND hora <= ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, getFecha());
            preparedStatement.setString(2, getHora());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

        
    public boolean reservaSeleccionadaEliminada() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener la cuenta de registros
            String sqlCount = "SELECT COUNT(*) AS count FROM reservasseleccionadas WHERE idReserva = ?";
            preparedStatement = connection.prepareStatement(sqlCount);

            // Establecer los valores para los parámetros
            preparedStatement.setInt(1, getId_reserva());

            // Ejecutar la consulta
            resultSet = preparedStatement.executeQuery();

            // Verificar si hay al menos un registro
            if (resultSet != null && resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0) {
                    return true; // Devolver true si count > 0
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Devolver false si no se cumple la condición
        return false;
    }
    
    public boolean fechaEsSuperior(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener la cuenta de registros
            String sqlCount = "SELECT fecha FROM reservas WHERE id_reserva = ?";
            preparedStatement = connection.prepareStatement(sqlCount);
            preparedStatement.setInt(1, getId_reserva());
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                // Obtener la fecha de la reserva desde la base de datos
                LocalDate fechaReserva = resultSet.getDate("fecha").toLocalDate();
                // Obtener la fecha actual
                LocalDate fechaActual = LocalDate.now();
                // Comparar las fechas
                if (fechaActual.isBefore(fechaReserva)) {
                    return true; // Devolver true si la fecha actual es menor que la fecha de la reserva
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Devolver false si no se cumple la condición
        return false;
    }
    
    public String[] usuariosReservaSeleccionada() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> usuarios = new ArrayList<>();

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener todos los usuarios por idReserva
            String sql = "SELECT emailUsuario FROM reservasseleccionadas WHERE idReserva = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer el valor para el parámetro (idReserva)
            preparedStatement.setInt(1, getId_reserva());

            // Ejecutar la consulta
            resultSet = preparedStatement.executeQuery();

            // Recorrer los resultados y agregar cada usuario a la lista
            while (resultSet != null && resultSet.next()) {
                String emailUsuario = resultSet.getString("emailUsuario");
                usuarios.add(emailUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Convertir la lista de usuarios a un array de strings y devolverlo
        return usuarios.toArray(new String[0]);
    }

        public static ArrayList<Reserva> obtenerReservasDB() throws SQLException, ParseException {
        PreparedStatement preparedStatement;
        ArrayList<Reserva> reservas;
        ResultSet datos;
        Connection connection = null;
        Conexion conexion = new Conexion();
        connection = conexion.establecerConexion();
        reservas = new ArrayList<>();
        
        // Consulta SQL para coger todos los datos de todas las pistas que hay
        String consultaSQL = "SELECT id_reserva,email_usuario,id_pista,fecha,hora FROM reservas WHERE activa = '1';";
        preparedStatement = connection.prepareStatement(consultaSQL);
        //Ejecutar SQL
        datos = preparedStatement.executeQuery();
        //Recorrer todos los registros de la tabla pistas
        while (datos.next()) {
            int idReserva = datos.getInt("id_reserva");
            String emailUsuario = datos.getString("email_usuario");
            int idPista = datos.getInt("id_pista");
            String fecha = datos.getString("fecha");
            String horaReserva = datos.getString("hora");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            // Convertir la cadena a un objeto Date
            java.util.Date fechaUtil = formato.parse(fecha);
            // Obtener el tiempo en milisegundos
            long tiempoEnMilisegundos = fechaUtil.getTime();
            // Crear un nuevo objeto java.sql.Date utilizando el tiempo en milisegundos
            java.sql.Date fechaSql = new java.sql.Date(tiempoEnMilisegundos);
            Reserva reserva = new Reserva();
            reserva.setId_reserva(idReserva);
            reserva.setEmail_usuario(emailUsuario);
            reserva.setId_pista(idPista);
            reserva.setFecha(fechaSql);
            reserva.setHora(horaReserva);
            reservas.add(reserva);
        }
        preparedStatement.close();
        datos.close();
        return reservas;
    }
        
    public void eliminarReservaSeleccionada(int idReserva){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement y parámetros
            String sql = "UPDATE reservas SET activa = 0 WHERE id_reserva = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idReserva);

            // Ejecutar la consulta
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static ArrayList<Reserva> buscarReservas(String email, int idPista, Date fecha, int estado) throws SQLException {
        ArrayList<Reserva> reservasEncontradas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Construir la consulta SQL dinámica
            String sql = "SELECT * FROM reservas WHERE 1=1";

            if (email != null && !email.isEmpty()) {
                sql += " AND email_usuario = ?";
            }

            if (idPista != -1) {
                sql += " AND id_pista = ?";
            }

            if (fecha != null) {
                sql += " AND fecha = ?";
            }

            if (estado != -1) {
                sql += " AND activa = ?";
            }

            // Preparar la sentencia SQL con los parámetros correspondientes
            statement = connection.prepareStatement(sql);

            int parameterIndex = 1;
            if (email != null && !email.isEmpty()) {
                statement.setString(parameterIndex++, email);
            }

            if (idPista != -1) {
                statement.setInt(parameterIndex++, idPista);
            }

            if (fecha != null) {
                statement.setDate(parameterIndex++, new java.sql.Date(fecha.getTime()));
            }

            if (estado != -1) {
                statement.setInt(parameterIndex++, estado);
            }

            // Ejecutar la consulta con los parámetros establecidos
            resultSet = statement.executeQuery();

            // Recorrer los resultados y almacenarlos en la lista de reservas
            while (resultSet.next()) {
                int idReserva = resultSet.getInt("id_reserva");
                String emailUsuario = resultSet.getString("email_usuario");
                int idPistaSQL = resultSet.getInt("id_pista");
                Date fechaReserva = resultSet.getDate("fecha");
                String horaSQL = resultSet.getString("hora");
                int estadoSQL = resultSet.getInt("activa");
                Reserva reserva = new Reserva();
                reservasEncontradas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            statement.close();
            resultSet.close();
            connection.close();
        }

        return reservasEncontradas;
    }


}
