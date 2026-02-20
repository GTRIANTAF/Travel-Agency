package travelagency;

public class GuideForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GuideForm.class.getName());

    /**
     * Creates new form GuideForm
     */
    public GuideForm() {
        initComponents();
        loadData();
    }
    private void loadData(){                                         
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        // Ορίζουμε τις στήλες
        model.setColumnIdentifiers(new Object[]{"Guide AT", "CV Description"});

        try {
            java.sql.Connection con = DBConnection.connect();
            String sql = "SELECT * FROM guide"; 
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String col1 = rs.getString("gui_AT"); 
                String col2 = rs.getString("gui_cv"); 
                
                model.addRow(new Object[]{col1, col2});
            }
            con.close(); 
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCV = new java.awt.TextField();
        txtAT = new java.awt.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CV");

        jLabel2.setText("Guide AT");

        txtCV.addActionListener(this::txtCVActionPerformed);

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

        jButton2.setText("Add");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("Delete");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtAT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(88, 88, 88)
                                .addComponent(jButton3))
                            .addComponent(txtCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(79, 79, 79)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton2)
                            .addComponent(jButton4))))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCVActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
            
            txtAT.setText(model.getValueAt(row, 0).toString());
            
            Object cv = model.getValueAt(row, 1);
            txtCV.setText(cv != null ? cv.toString() : "");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String at = txtAT.getText().trim();
        String cv = txtCV.getText().trim();
        
        if(at.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill Guide AT!");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("INSERT INTO guide (gui_AT, gui_cv) VALUES (?, ?)")) {
            
            pst.setString(1, at);
            pst.setString(2, cv);
            pst.executeUpdate();
            
            javax.swing.JOptionPane.showMessageDialog(this, "Guide Added!");
            loadData();
            txtAT.setText(""); txtCV.setText("");
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String at = txtAT.getText().trim();
        String cv = txtCV.getText().trim();
        
        if(at.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a guide to update.");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("UPDATE guide SET gui_cv=? WHERE gui_AT=?")) {
            
            pst.setString(1, cv);
            pst.setString(2, at);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Updated!");
                loadData();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Guide AT not found!");
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String at = txtAT.getText();
        
        if(at.isEmpty()) {
             javax.swing.JOptionPane.showMessageDialog(this, "Select a guide to delete.");
             return;
        }
        
        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM guide WHERE gui_AT=?")) {
            
            pst.setString(1, at);
            pst.executeUpdate();
            
            javax.swing.JOptionPane.showMessageDialog(this, "Deleted!");
            loadData();
            txtAT.setText(""); txtCV.setText("");
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new GuideForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.TextField txtAT;
    private java.awt.TextField txtCV;
    // End of variables declaration//GEN-END:variables
}
