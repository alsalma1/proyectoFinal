package com.mycompany.mavenproject1.config;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conectar = null;
    String usuario = "root";
    String contrasenia = "";
    String bd = "padel";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            //System.out.println("MESSAGE: Se conectó correctamente a la base de datos");

        } catch(Exception e) {
            System.out.println("ERROR: No se conectó a la base de datos, error: "+e.toString());

        }
        return conectar;
    }
}
