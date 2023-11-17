package com.mycompany.mavenproject1.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorPrimeraColumna extends DefaultTableCellRenderer {
    
    public ColorPrimeraColumna() {
        setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto en las celdas
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Verifica si es la primera columna
        if (column == 0) {
            component.setBackground(Color.decode("#98c3ed"));
            // Aplica negritas al texto
            Font boldFont = new Font(component.getFont().getName(), Font.BOLD, component.getFont().getSize());
            component.setFont(boldFont);
        }
        return component;
    }
}


