package travelagency;

public class DestinationForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DestinationForm.class.getName());

    public DestinationForm() {
        initComponents();
        loadData(); // Φόρτωση με το άνοιγμα
    }

    private void loadData(){                                         
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        

        model.setColumnIdentifiers(new Object[]{"ID", "Name", "Description", "Type","Language Code"});

        try {
            java.sql.Connection con = DBConnection.connect();
            
            String sql = "SELECT * FROM destination"; 
            
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String col1 = rs.getString("dst_id"); 
                String col2 = rs.getString("dst_name"); 
                String col3 = rs.getString("dst_descr"); 
                String col4 = rs.getString("dst_rtype");
                String col5 = rs.getString("dst_language_code");
                
                // Προσθήκη γραμμής
                model.addRow(new Object[]{col1, col2, col3, col4,col5});
            }
            
            con.close(); 
            
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Σφάλμα: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtName = new java.awt.TextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDescr = new java.awt.TextField();
        jLabel5 = new javax.swing.JLabel();
        txtId = new java.awt.TextField();
        txtLangCode = new java.awt.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtType = new java.awt.TextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtName.addActionListener(this::txtNameActionPerformed);

        jButton2.setText("Add");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

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

        jButton4.setText("Delete");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jLabel3.setText("Name");

        jLabel4.setText("Description");

        jLabel5.setText("ID");

        txtId.addActionListener(this::txtIdActionPerformed);

        txtLangCode.addActionListener(this::txtLangCodeActionPerformed);

        jLabel6.setText("Language Code");

        jLabel7.setText("Type");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(83, 83, 83)
                                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel7))
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtLangCode, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addGap(32, 32, 32)
                        .addComponent(jButton3)
                        .addGap(38, 38, 38)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton4)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txtDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLangCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String name = txtName.getText();
        String descr = txtDescr.getText();
        String type = txtType.getText();
        String lang = txtLangCode.getText();
        
        if(name.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill in the Name!");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("INSERT INTO destination (dst_name, dst_descr, dst_rtype,dst_language_code) VALUES (?, ?, ?, ?)")) {
            
            pst.setString(1, name);
            pst.setString(2, descr);
            pst.setString(3, type);
            pst.setString(4, lang);
            pst.executeUpdate();
            
            javax.swing.JOptionPane.showMessageDialog(this, "Destination Added!");
            
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String id = txtId.getText().trim();
        String name = txtName.getText().trim(); 
        String descr = txtDescr.getText().trim();
        String type = txtType.getText().trim();
        String lang = txtLangCode.getText().trim();
        
        if(id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a destination to update.");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("UPDATE destination SET dst_name=?, dst_descr=?, dst_rtype=?,dst_language_code=? WHERE dst_id=?")) {
            
            pst.setString(1, name);
            pst.setString(2, descr);
            pst.setString(3, type);
             pst.setString(4, lang);
            pst.setInt(5, Integer.parseInt(id));
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Updated Successfully!");
                loadData();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "ID not found!");
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
  int row = jTable1.getSelectedRow();
        
        if (row != -1) {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
            
            txtId.setText(model.getValueAt(row, 0).toString());
            txtName.setText(model.getValueAt(row, 1).toString());
            txtDescr.setText(model.getValueAt(row, 2).toString());
            txtType.setText(model.getValueAt(row, 3).toString());
            txtLangCode.setText(model.getValueAt(row, 4).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      String id = txtId.getText();
        
        if(id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a destination to delete.");
            return;
        }
        
        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM destination WHERE dst_id=?")) {
            
            pst.setInt(1, Integer.parseInt(id));
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Deleted Successfully!");
                loadData();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error (Check dependencies): " + e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtLangCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLangCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLangCodeActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new DestinationForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.TextField txtDescr;
    private java.awt.TextField txtId;
    private java.awt.TextField txtLangCode;
    private java.awt.TextField txtName;
    private java.awt.TextField txtType;
    // End of variables declaration//GEN-END:variables
}
