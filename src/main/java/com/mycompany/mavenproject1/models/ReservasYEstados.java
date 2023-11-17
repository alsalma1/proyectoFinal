package com.mycompany.mavenproject1.models;

import com.mycompany.mavenproject1.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservasYEstados {
    
    private int id_pista;
    private int id_reserva;
    private String email;
    private Date fecha;
    private String hora;
    
    
    public ReservasYEstados(int id_pista,int id_reserva,String email,Date fecha,String hora){
        this.id_pista = id_pista;
        this.id_reserva = id_reserva;
        this.email = email;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId_pista() {
        return id_pista;
    }
    
    public int getId_reserva() {
        return id_reserva;
    }
    
    public String getEmail() {
        return email;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setId_pista(int id_pista) {
        this.id_pista = id_pista;
    }
    
    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }
    
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
    public static ArrayList<ReservasYEstados> obtenerReservasMantenimiento(int idPista, java.sql.Date fechaInicio, java.sql.Date fechaFin, String horaInicio, String horaFin) {
        ArrayList<ReservasYEstados> reservasEncontradas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            String sql = "SELECT r.id_reserva, r.email_usuario, r.fecha, r.hora,r.id_pista " +
             "FROM reservas r " +
             "INNER JOIN estado_pistas ep ON r.id_pista = ep.id_pista " +
             "WHERE (" +
             "    (r.fecha = ? AND STR_TO_DATE(r.hora, '%H:%i') >= STR_TO_DATE(?, '%H:%i')) OR " +
             "    (r.fecha > ?)" +
             ") AND (" +
             "    (r.fecha = ? AND STR_TO_DATE(r.hora, '%H:%i') <= STR_TO_DATE(?, '%H:%i')) OR " +
             "    (r.fecha < ?)" +
             ") AND r.activa = 1 " +
             "AND r.id_pista = ?;";

            // Crear la declaración y ejecutar la consulta con parámetros
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, fechaInicio);
            statement.setString(2, horaInicio);
            statement.setDate(3, fechaInicio);
            statement.setDate(4, fechaFin);
            statement.setString(5, horaFin);
            statement.setDate(6, fechaFin);
            statement.setInt(7, idPista);

            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y almacenarlos en la lista de objetos ReservasYEstados
            while (resultSet.next()) {
                int id_reserva = resultSet.getInt("id_reserva");
                int idPistaSQL = resultSet.getInt("id_pista");
                String mail = resultSet.getString("email_usuario");
                java.sql.Date fechaReserva = resultSet.getDate("fecha");
                String horaReserva = resultSet.getString("hora");
                
                // Crear un objeto ReservasYEstados y agregarlo a la lista
                ReservasYEstados reservaEstado = new ReservasYEstados(idPistaSQL,id_reserva,mail, fechaReserva, horaReserva);
                reservasEncontradas.add(reservaEstado);
            }

            // Cerrar recursos
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservasEncontradas;
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
    

}
