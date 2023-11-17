package com.mycompany.mavenproject1;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.mavenproject1.config.Conexion;
import com.mycompany.mavenproject1.controllers.AppController;
import com.mycompany.mavenproject1.views.ReservarPista;

public class Mavenproject1 {

    public static void main(String[] args) {
        //Para conectar Java con la bse de datos
        Conexion objetoConexion = new Conexion();
        objetoConexion.establecerConexion();
        Dashboard principal = new Dashboard();
        principal.setVisible(true);
        FlatLightLaf.setup();
    }
}
