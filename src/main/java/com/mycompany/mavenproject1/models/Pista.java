package com.mycompany.mavenproject1.models;
import java.util.ArrayList;
import java.sql.*;
import com.mycompany.mavenproject1.config.Conexion;
import java.sql.Connection;
import javax.swing.SwingWorker;
import com.mycompany.mavenproject1.views.GestionPistas;
import javax.swing.JOptionPane;
public class Pista {
    private int id_pista;
    // Constructor
    public Pista(int id_pista) {
        this.id_pista = id_pista;
    }
    
    //Getters and Setters

    public int getId_pista() {
        return id_pista;
    }

    public void setId_pista(int id_pista) {
        this.id_pista = id_pista;
    }
    //METODOS
    public static ArrayList<Pista> obtenerPistasDB() throws SQLException{
        PreparedStatement preparedStatement;
        ArrayList<Pista> pistas;
        ResultSet datos;
        Connection connection = null;
        Conexion conexion = new Conexion();
        connection = conexion.establecerConexion();
            pistas = new ArrayList<>();
            // Consulta SQL para coger todos los datos de todas las pistas que hay
            String consultaSQL = "SELECT * FROM pistas;";
            preparedStatement = connection.prepareStatement(consultaSQL);
            //Ejecutar SQL
            datos = preparedStatement.executeQuery();
            //Recorrer todos los registros de la tabla pistas
            while (datos.next()) {
                int idPista = datos.getInt("id_pista");
                Pista pista = new Pista(idPista);
                pistas.add(pista);
            }
            preparedStatement.close();
            datos.close();
            return pistas;
        }
    public static void addPista() {
        try {
            Conexion conexion = new Conexion();
            Connection connection = conexion.establecerConexion();

            String consultaSQL = "INSERT INTO pistas VALUES ();";
            PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL);
            
            int filasAfectadas = preparedStatement.executeUpdate();
            
            //Cerrar recursos
            preparedStatement.close();
            connection.close();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,"Pista añadida correctamente!");
                
            } else {
                JOptionPane.showMessageDialog(null,"ERROR");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}

