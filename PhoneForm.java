package travelagency;
import java.util.HashMap;
import java.sql.*;

public class PhoneForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PhoneForm.class.getName());
    private final HashMap<String, String> branchMap = new HashMap<>();
    
    private String oldBranchCode = "";
    private String oldPhoneNumber = "";
    
    public PhoneForm() {
        initComponents();
        fillBranchCombo();
        loadData();
    }
    
    private void fillBranchCombo() {
        cmbBranch.removeAllItems();
        try (Connection con = DBConnection.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT br_code, br_city, br_street FROM branch")) {
            
            while (rs.next()) {
                String branchDisplay = rs.getString("br_city") + " (" + rs.getString("br_street") + ")";
                branchMap.put(branchDisplay, rs.getString("br_code"));
                cmbBranch.addItem(branchDisplay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      private void loadData(){                                        
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Branch Code", "Branch City", "Phone Number"});

        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement(
                 "SELECT p.ph_br_code, b.br_city, p.ph_number FROM phones p JOIN branch b ON p.ph_br_code = b.br_code")) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("ph_br_code"),
                    rs.getString("br_city"),
                    rs.getString("ph_number")
                });
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Load Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtPhone = new java.awt.TextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cmbBranch = new javax.swing.JComboBox<>();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPhone.addActionListener(this::txtPhoneActionPerformed);

        jButton2.setText("Add");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("Delete");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jLabel1.setText("Branch Code");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("Phone Num");

        cmbBranch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cmbBranch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addComponent(jButton2)
                    .addGap(88, 88, 88)
                    .addComponent(jButton3)
                    .addGap(79, 79, 79)
                    .addComponent(jButton4)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(271, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton2)
                        .addComponent(jButton4))
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String brCode = branchMap.get((String) cmbBranch.getSelectedItem());
        String phone = txtPhone.getText().trim();

        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement(
                 "INSERT INTO phones (ph_br_code, ph_number) VALUES (?, ?)")) {
            
            pst.setString(1, brCode);
            pst.setString(2, phone);
            pst.executeUpdate();
            
            javax.swing.JOptionPane.showMessageDialog(this, "Phone Added!");
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String newBrCode = branchMap.get((String) cmbBranch.getSelectedItem());
        String newPhone = txtPhone.getText().trim();

        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement(
                 "UPDATE phones SET ph_br_code=?, ph_number=? WHERE ph_br_code=? AND ph_number=?")) {
            
            pst.setString(1, newBrCode);
            pst.setString(2, newPhone);
            pst.setString(3, oldBranchCode);
            pst.setString(4, oldPhoneNumber);
            
            pst.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, "Phone Updated!");
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Update Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) return;

        String brCode = jTable1.getValueAt(row, 0).toString();
        String phone = jTable1.getValueAt(row, 2).toString();

        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement("DELETE FROM phones WHERE ph_br_code=? AND ph_number=?")) {
            pst.setString(1, brCode);
            pst.setString(2, phone);
            pst.executeUpdate();
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Delete Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int row = jTable2.getSelectedRow(); // Changed from jTable1 to jTable2
        if (row != -1) {
        // Use jTable2 to get the values
        oldBranchCode = jTable2.getValueAt(row, 0).toString();
        oldPhoneNumber = jTable2.getValueAt(row, 2).toString();
        
        txtPhone.setText(oldPhoneNumber);
        
        String cityName = jTable2.getValueAt(row, 1).toString();
        for (int i = 0; i < cmbBranch.getItemCount(); i++) {
            if (cmbBranch.getItemAt(i).startsWith(cityName)) {
                cmbBranch.setSelectedIndex(i);
                break;
            }
        }
        }
    }//GEN-LAST:event_jTable2MouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new PhoneForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBranch;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private java.awt.TextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
