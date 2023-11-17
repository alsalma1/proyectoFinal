package com.mycompany.mavenproject1.views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
            setText(""); // No mostrar ningún texto
        } else {
            super.setValue(value);
        }
    }
}

