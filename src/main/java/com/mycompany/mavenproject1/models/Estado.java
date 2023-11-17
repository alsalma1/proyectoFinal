package com.mycompany.mavenproject1.models;

import com.mycompany.mavenproject1.config.Conexion;
import com.mycompany.mavenproject1.controllers.AppController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import org.joda.time.DateTime;

public class Estado{
    private int id_estado;
    private int id_pista;
    private String estado;
    private DateTime fecha_inicio;
    private DateTime fecha_fin;
    
    public void setId_estado(int id_estado){
        this.id_estado = id_estado;
    }
    
    public void setId_pista(int id_pista) {
        this.id_pista = id_pista;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFecha_inicio(DateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(DateTime fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    public int getId_estado(){
        return id_estado;
    }
    
    public int getId_pista() {
        return id_pista;
    }

    public String getEstado() {
        return estado;
    }

    public DateTime getFecha_inicio() {
        return fecha_inicio;
    }

    public DateTime getFecha_fin() {
        return fecha_fin;
    }
    public static ArrayList<Estado> obtenerEstadosDB(int idPista) throws SQLException{
        AppController Controller = new AppController();
        PreparedStatement preparedStatement;
        ArrayList<Estado> estados;
        ResultSet datos;
        Connection connection = null;
        Conexion conexion = new Conexion();
        connection = conexion.establecerConexion();
            estados = new ArrayList<>();
            // Consulta SQL para coger todos los datos de todas las pistas que hay
            String consultaSQL = "SELECT * FROM estado_pistas WHERE id_pista = ?;";
            
            preparedStatement = connection.prepareStatement(consultaSQL);
            preparedStatement.setInt(1, idPista);
            //Ejecutar SQL
            datos = preparedStatement.executeQuery();
            //Recorrer todos los registros de la tabla pistas
            while (datos.next()) {
                int id_estado = datos.getInt("id_estado");
                int id_Pista = datos.getInt("id_pista");
                String estado_pista = datos.getString("estado_pista");
                
                Timestamp fechaInicioResult = datos.getTimestamp("fecha_inicio");          
                DateTime fechaInicio = new DateTime(fechaInicioResult.getTime());
                
                Timestamp fechaFinResult = datos.getTimestamp("fecha_fin");        
                DateTime fechaFin = new DateTime(fechaFinResult.getTime());
                
                Estado estado = new Estado();
                estado.setId_estado(id_estado);
                estado.setId_pista(id_Pista);
                estado.setEstado(estado_pista);
                estado.setFecha_inicio(fechaInicio);
                estado.setFecha_fin(fechaFin);
                estados.add(estado);
            }
            preparedStatement.close();
            datos.close();
            return estados;
    }
    public static ArrayList<Estado> obtenerEstadosListDB() throws SQLException{
        AppController Controller = new AppController();
        PreparedStatement preparedStatement;
        ArrayList<Estado> estados;
        ResultSet datos;
        Connection connection = null;
        Conexion conexion = new Conexion();
        connection = conexion.establecerConexion();
            estados = new ArrayList<>();
            // Consulta SQL para coger todos los datos de todas las pistas que hay
            String consultaSQL = "SELECT * FROM estado_pistas;";
            
            preparedStatement = connection.prepareStatement(consultaSQL);
            //Ejecutar SQL
            datos = preparedStatement.executeQuery();
            //Recorrer todos los registros de la tabla pistas
            while (datos.next()) {
                int id_estado = datos.getInt("id_estado");
                int id_Pista = datos.getInt("id_pista");
                String estado_pista = datos.getString("estado_pista");
                
                Timestamp fechaInicioResult = datos.getTimestamp("fecha_inicio");          
                DateTime fechaInicio = new DateTime(fechaInicioResult.getTime());
                
                Timestamp fechaFinResult = datos.getTimestamp("fecha_fin");        
                DateTime fechaFin = new DateTime(fechaFinResult.getTime());
                
                Estado estado = new Estado();
                estado.setId_estado(id_estado);
                estado.setId_pista(id_Pista);
                estado.setEstado(estado_pista);
                estado.setFecha_inicio(fechaInicio);
                estado.setFecha_fin(fechaFin);
                estados.add(estado);
            }
            preparedStatement.close();
            datos.close();
            return estados;
    }
    public static void insertarEstado(java.sql.Date fechaInicio, java.sql.Date fechaFin, String idPista, String horaInicio,String horaFin,String estado) throws ParseException{
        try {
            System.out.println(fechaInicio);
            Conexion conexion = new Conexion();
            Connection connection = conexion.establecerConexion();
            
            String fechaInicioStr = fechaInicio + " " + horaInicio;
            String fechaFinStr = fechaFin + " " + horaFin;
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            java.util.Date fechaI = dateFormat.parse(fechaInicioStr); // Manejo de errores si la cadena no tiene el formato esperado
            java.util.Date fechaF = dateFormat.parse(fechaFinStr);
            
            Timestamp FechaItimestamp = new Timestamp(fechaI.getTime());
            Timestamp FechaFtimestamp = new Timestamp(fechaF.getTime());
            // Ahora puedes usar 'timestamp' en tu operación de inserción en la base de datos
            System.out.println("Timestamp: " + FechaItimestamp);
            System.out.println("Timestamp: " + FechaFtimestamp);
            
            String consultaSQL = "INSERT INTO estado_pistas (id_pista,estado_pista,fecha_inicio,fecha_fin) VALUES (?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL);
            
            preparedStatement.setString(1,idPista);
            preparedStatement.setString(2,estado);
            preparedStatement.setTimestamp(3,FechaItimestamp);
            preparedStatement.setTimestamp(4,FechaFtimestamp);
            int filasAfectadas = preparedStatement.executeUpdate();
            
            //Cerrar recursos
            preparedStatement.close();
            connection.close();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,"Estado programado"); 
            } else {
                JOptionPane.showMessageDialog(null,"ERROR");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void eliminarEstado(String id_estado) throws SQLException{
        AppController Controller = new AppController();
        ArrayList<Estado> estados;
        ResultSet datos;
        Connection connection = null;
        Conexion conexion = new Conexion();
        connection = conexion.establecerConexion();
        
        String consultaSQL = "DELETE FROM estado_pistas WHERE id_estado = ?;";
        
        // Crear la sentencia preparada
        PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL);
        preparedStatement.setString(1, id_estado);

        // Ejecutar la sentencia DELETE
        int filasAfectadas = preparedStatement.executeUpdate();

        // Cerrar recursos
        preparedStatement.close();
        connection.close();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null,"Se elimino el estado numero " + id_estado); 
        } else {
            JOptionPane.showMessageDialog(null,"No se encontro ningun estado."); 
        }
    }
}

