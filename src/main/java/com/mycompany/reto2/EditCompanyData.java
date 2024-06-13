/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.reto2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author profesor3
 */
public class EditCompanyData extends javax.swing.JFrame {

    /**
     * Creates new form EditEmpresaData
     */
    public EditCompanyData() {
        initComponents();
        setLocationRelativeTo(null);
        setUpTeachersList();
        setupEventListeners();
    }

    public void setUpTeachersList() {
        MyConnection conexion = new MyConnection();
        List<String> dataList = conexion.companyList();

        jListTeachers.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = dataList.toArray(new String[0]);

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }

    private void setupEventListeners() {
        jListTeachers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedCompanyIndex = getSelectedCompanyIndex();
                    String selectedCompanyData = getSelectedCompanyData(selectedCompanyIndex);
                    displayCompanyData(selectedCompanyData);
                }
            }
        });
    }

    private int getSelectedCompanyIndex() {
        return jListTeachers.getSelectedIndex();
    }

    private String getSelectedCompanyData(int index) {
        if (index != -1) {
            MyConnection connection = new MyConnection();
            List<String> dataList = connection.companyData(index); // Ajustar el índice según sea necesario
            return dataList.isEmpty() ? "" : dataList.get(0);
        }
        return "";
    }

    public void displayCompanyData(String data) {
        if (data != null && !data.isEmpty()) {
            String[] values = data.split(", ");
            if (values.length >= 3) {
                setTextFields(values);
            } else {
                setUnknownTextFields();
            }
        }
    }

    private void setTextFields(String[] values) {
        jTextFieldIDCompany.setText(values[0]);
        jTextFieldName.setText(values[1]);
        jTextFieldIDSector.setText(values[2]);
        jTextFieldState.setText(values[3]);
    }

    private void setUnknownTextFields() {
        jTextFieldIDCompany.setText("Unknown");
        jTextFieldName.setText("Unknown");
        jTextFieldIDSector.setText("Unknown");
        jTextFieldState.setText("Unknown");
    }

    public void reloadWindow() {
        jTextFieldIDCompany.setText("");
        jTextFieldName.setText("");
        jTextFieldIDSector.setText("");
        jTextFieldState.setText("");
        setUpTeachersList();
        setupEventListeners();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTeachers = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jLabelCompany = new javax.swing.JLabel();
        jTextFieldIDCompany = new javax.swing.JTextField();
        jLabelSector = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelFTCs = new javax.swing.JLabel();
        jTextFieldIDSector = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldState = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jButtonModify = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jListTeachers.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListTeachers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListTeachers.setToolTipText("");
        jListTeachers.setPreferredSize(new java.awt.Dimension(25, 90));
        jScrollPane1.setViewportView(jListTeachers);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.setPreferredSize(new java.awt.Dimension(25, 90));

        jLabelCompany.setText("ID:");

        jTextFieldIDCompany.setText("Unknown");
        jTextFieldIDCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDCompanyActionPerformed(evt);
            }
        });

        jLabelSector.setText("Company Name");

        jTextFieldName.setText("Unknown");
        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });

        jLabelFTCs.setText("IDSector");

        jTextFieldIDSector.setText("Unknown");
        jTextFieldIDSector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDSectorActionPerformed(evt);
            }
        });

        jLabel1.setText("State");

        jTextFieldState.setText("Unknown");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldIDCompany)
                    .addComponent(jTextFieldName)
                    .addComponent(jTextFieldIDSector)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCompany)
                            .addComponent(jLabelSector)
                            .addComponent(jLabelFTCs)
                            .addComponent(jLabel1))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addComponent(jTextFieldState))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCompany)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIDCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSector)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFTCs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIDSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonModify.setText("Modify");
        jButtonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModify)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBack))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonModify)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonBack))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIDCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDCompanyActionPerformed

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNameActionPerformed

    private void jTextFieldIDSectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDSectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDSectorActionPerformed

    private void jButtonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyActionPerformed
        String id = jTextFieldIDCompany.getText();
        String name = jTextFieldName.getText();
        String secondName = jTextFieldIDSector.getText();
        String state = jTextFieldState.getText();
        MyConnection conexion = new MyConnection();
        conexion.updateTeacher(id, name, secondName, state);
        reloadWindow();
    }//GEN-LAST:event_jButtonModifyActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        String id = jTextFieldIDCompany.getText();
        String name = jTextFieldName.getText();
        String secondName = jTextFieldIDSector.getText();
        String state = jTextFieldState.getText();
        MyConnection conexion = new MyConnection();
        conexion.addTeacher(id, name, secondName, state);
        reloadWindow();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        String id = jTextFieldIDCompany.getText();
        MyConnection conexion = new MyConnection();
        conexion.deactivateTeacher(id);
        reloadWindow();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        reloadWindow();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(EditCompanyData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditCompanyData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditCompanyData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditCompanyData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditCompanyData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonModify;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCompany;
    private javax.swing.JLabel jLabelFTCs;
    private javax.swing.JLabel jLabelSector;
    private javax.swing.JList<String> jListTeachers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldIDCompany;
    private javax.swing.JTextField jTextFieldIDSector;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldState;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}