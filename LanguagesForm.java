package travelagency;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class LanguagesForm extends javax.swing.JFrame {
    
    private final HashMap<String, String> guideMap = new HashMap<>(); // Name -> AT
    private String oldGuideAT = "";
    private String oldLang = "";
  
    public LanguagesForm() {
        initComponents();
        fillGuideCombo();
        loadData();
    }

    private void fillGuideCombo() {
        cmbGuide.removeAllItems();
        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement(
                 "SELECT g.gui_AT, w.wrk_name, w.wrk_lname FROM guide g JOIN worker w ON g.gui_AT = w.wrk_AT")) {
            
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("wrk_name") + " " + rs.getString("wrk_lname");
                String at = rs.getString("gui_AT");
                guideMap.put(fullName, at);
                cmbGuide.addItem(fullName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void loadData() {
        javax.swing.table.DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Guide AT", "Guide Name", "Language"});

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement(
                 "SELECT l.lng_gui_AT, w.wrk_name, w.wrk_lname, l.lng_language_code " +
                 "FROM languages l JOIN worker w ON l.lng_gui_AT = w.wrk_AT")) {

            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("lng_gui_AT"),
                    rs.getString("wrk_name") + " " + rs.getString("wrk_lname"),
                    rs.getString("lng_language_code")
                });
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbGuide = new javax.swing.JComboBox<>();
        txtLanguage = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Select Guide");

        jLabel2.setText("Select Language");

        cmbGuide.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtLanguage.addActionListener(this::txtLanguageActionPerformed);

        jButton1.setText("Add");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Delete");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbGuide, 0, 155, Short.MAX_VALUE)
                            .addComponent(txtLanguage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbGuide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLanguageActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String selectedGuide = (String) cmbGuide.getSelectedItem();
        String language = txtLanguage.getText().trim();
        String at = guideMap.get(selectedGuide);

        if (language.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter a language!");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement(
                 "INSERT INTO languages (lng_gui_AT, lng_language_code) VALUES (?, ?)")) {
            
            pst.setString(1, at);
            pst.setString(2, language);
            pst.executeUpdate();
            
            javax.swing.JOptionPane.showMessageDialog(this, "Language added to guide!");
            loadData();
            txtLanguage.setText("");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error (Possible duplicate): " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) return;

        String at = jTable1.getValueAt(row, 0).toString();
        String lang = jTable1.getValueAt(row, 2).toString();

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement(
                 "DELETE FROM languages WHERE lng_gui_AT=? AND lng_language_code=?")) {
            
            pst.setString(1, at);
            pst.setString(2, lang);
            pst.executeUpdate();
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row != -1) {
        
        oldGuideAT = jTable1.getValueAt(row, 0).toString();
        oldLang = jTable1.getValueAt(row, 2).toString();

        cmbGuide.setSelectedItem(jTable1.getValueAt(row, 1).toString());
        txtLanguage.setText(oldLang);
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (oldGuideAT.equals("") || oldLang.equals("")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please select a record from the table first!");
        return;
        }

        String newGuideAT = guideMap.get((String) cmbGuide.getSelectedItem());
        String newLang = txtLanguage.getText().trim();

        String sql = "UPDATE languages SET lng_gui_AT = ?, lng_language_code = ? " +
                     "WHERE lng_gui_AT = ? AND lng_language_code = ?";

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, newGuideAT);
            pst.setString(2, newLang);
            pst.setString(3, oldGuideAT);
            pst.setString(4, oldLang);

            pst.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, "Language updated successfully!");
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LanguagesForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbGuide;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtLanguage;
    // End of variables declaration//GEN-END:variables
}
