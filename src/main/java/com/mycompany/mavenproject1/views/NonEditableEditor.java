package com.mycompany.mavenproject1.views;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

public class NonEditableEditor extends DefaultCellEditor {
    public NonEditableEditor(JTextField textField) {
        super(textField);
        textField.setEditable(false);  // Hace que la celda sea no editable
    }
}

