/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.reto2;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class Consultas extends javax.swing.JFrame {
    private ConfigData configData;

    /**
     * Creates new form Consultas
     */
    public Consultas() {
        initComponents();
        setLocationRelativeTo(null);
        configData = new ConfigData();
    }
    int selection = 1;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelConsulta = new javax.swing.JLabel();
        jComboBoxConsulta = new javax.swing.JComboBox<>();
        OKjButton = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelConsulta.setText("Seleciona la consulta:");

        jComboBoxConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", " " }));
        jComboBoxConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConsultaActionPerformed(evt);
            }
        });

        OKjButton.setText("OK");
        OKjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKjButtonActionPerformed(evt);
            }
        });

        jButtonVolver.setText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelConsulta)
                        .addGap(14, 14, 14))
                    .addComponent(jComboBoxConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OKjButton)
                    .addComponent(jButtonVolver))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabelConsulta)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OKjButton)
                    .addComponent(jComboBoxConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVolver)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConsultaActionPerformed
        MyConnection conexion = new MyConnection();
        switch (jComboBoxConsulta.getSelectedIndex()) {
            case 0:
                selection = 1;
               break;
           case 1:
               selection = 2;
               break;
           case 2:
               selection = 3;
               break;
           case 3:
               selection = 4;
               break;
           case 4:
               selection = 5;
               break;
           case 5:
               selection = 6;
               break;  
           case 6:
               selection = 7;
               break;
           case 7:
               selection = 8;
               break;    
        }
    }//GEN-LAST:event_jComboBoxConsultaActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
         setVisible(false);
         Main main = new Main();
         //main.setVisible(true);
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void OKjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKjButtonActionPerformed
        C1 c1 = new C1();
        C2 c2 = new C2();
        C3 c3 = new C3();
        C4 c4 = new C4();
        C5 c5 = new C5();
        C6 c6 = new C6();
        C7 c7 = new C7();
        C8 c8 = new C8();
        if (selection == 1) {
            setVisible(false);
            c1.setVisible(true);
        } else if (selection == 2) {
            setVisible(false);
            c2.setVisible(true);
        }else if (selection == 3) {
            setVisible(false);
            c3.setVisible(true);
        } else if (selection == 4) {
            setVisible(false);
            c4.setVisible(true);
        } else if (selection == 5) {
            setVisible(false);
            c5.setVisible(true);
        } else if (selection == 6) {
            setVisible(false);
            c6.setVisible(true);
        } else if (selection == 7) {
            setVisible(false);
            c7.setVisible(true);
        } else if (selection == 8) {
            setVisible(false);
            c8.setVisible(true);
        }
    }//GEN-LAST:event_OKjButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKjButton;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxConsulta;
    private javax.swing.JLabel jLabelConsulta;
    // End of variables declaration//GEN-END:variables

}
