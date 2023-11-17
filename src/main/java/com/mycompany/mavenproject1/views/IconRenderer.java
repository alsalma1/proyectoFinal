package com.mycompany.mavenproject1.views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IconRenderer extends DefaultTableCellRenderer {
    private Icon icon;

    public IconRenderer(Icon icon) {
        this.icon = icon;
        setHorizontalAlignment(JLabel.CENTER); // Alinea el icono en el centro de la celda
    }

    @Override
    protected void setValue(Object value) {
        setIcon(icon);
    }
}

