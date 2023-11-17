package com.mycompany.mavenproject1.views;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if ("Reservado".equals(value)) {
            // Configura el color de fondo y el color del texto para celdas "Reservado"
            component.setBackground(Color.RED);
            component.setForeground(Color.RED);
        } else if ("Mantenimiento".equals(value)) {
            // Configura el color de fondo y el color del texto para celdas "Mantenimiento"
            component.setBackground(Color.YELLOW);
            component.setForeground(Color.YELLOW);
        }else if("reservaUsuario".equals(value)){
            component.setBackground(Color.decode("#2898ee"));
            component.setForeground(Color.decode("#2898ee"));
        }else if("Ocupada".equals(value)){
            component.setBackground(Color.decode("#fc4b08"));
            component.setForeground(Color.decode("#fc4b08"));   
        }else {
            // Establece el fondo predeterminado para otras celdas
            component.setBackground(table.getBackground());
            component.setForeground(table.getForeground());
        }

        return component;
    }
}

