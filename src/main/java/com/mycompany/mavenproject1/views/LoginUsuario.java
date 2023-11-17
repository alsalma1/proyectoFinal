package com.mycompany.mavenproject1.views;

import com.mycompany.mavenproject1.controllers.AppController;

public class LoginUsuario extends javax.swing.JPanel {

    public LoginUsuario() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldEmailUsu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fieldPasswordUsu = new javax.swing.JPasswordField();
        btnLoginUsu = new javax.swing.JButton();

        btnCancelar.setText("Cancelar");

        setBackground(new java.awt.Color(51, 153, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(300, 609));

        bg.setBackground(new java.awt.Color(51, 153, 255));
        bg.setPreferredSize(new java.awt.Dimension(300, 609));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Iniciar sesión");

        fieldEmailUsu.setBackground(new java.awt.Color(204, 204, 204));
        fieldEmailUsu.setForeground(new java.awt.Color(0, 0, 0));
        fieldEmailUsu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        fieldEmailUsu.setPreferredSize(new java.awt.Dimension(500, 20));
        fieldEmailUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEmailUsuActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Email");
        jLabel2.setPreferredSize(new java.awt.Dimension(65, 25));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Contraseña");

        fieldPasswordUsu.setBackground(new java.awt.Color(204, 204, 204));
        fieldPasswordUsu.setForeground(new java.awt.Color(0, 0, 0));
        fieldPasswordUsu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        fieldPasswordUsu.setPreferredSize(new java.awt.Dimension(500, 20));
        fieldPasswordUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPasswordUsuActionPerformed(evt);
            }
        });

        btnLoginUsu.setBackground(new java.awt.Color(51, 102, 255));
        btnLoginUsu.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btnLoginUsu.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginUsu.setText("Iniciar sesión");
        btnLoginUsu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLoginUsu.setBorderPainted(false);
        btnLoginUsu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLoginUsu.setPreferredSize(new java.awt.Dimension(103, 27));
        btnLoginUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginUsuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(btnLoginUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldPasswordUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(fieldEmailUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldEmailUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPasswordUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addComponent(btnLoginUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fieldPasswordUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPasswordUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPasswordUsuActionPerformed

    private void btnLoginUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginUsuActionPerformed
        String email = fieldEmailUsu.getText();
        String contrasena = String.valueOf(fieldPasswordUsu.getPassword());
        // Llamar al método comprobarCredenciales
        AppController appController = new AppController();
        appController.comprobarCredencialesUsuario(email, contrasena);
    }//GEN-LAST:event_btnLoginUsuActionPerformed

    private void fieldEmailUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEmailUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEmailUsuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLoginUsu;
    private javax.swing.JTextField fieldEmailUsu;
    private javax.swing.JPasswordField fieldPasswordUsu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
