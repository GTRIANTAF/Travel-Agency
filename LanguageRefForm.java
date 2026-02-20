
package travelagency;


public class LanguageRefForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LanguageRefForm.class.getName());


    public LanguageRefForm() {
        initComponents();
        
        loadData();
    }

    private void loadData(){                                        
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        // 2. Ορίζουμε τις στήλες (Κωδικός, Όνομα)
        model.setColumnIdentifiers(new Object[]{"Code", "Name"});

        try {
            // 3. Σύνδεση στη βάση
            java.sql.Connection con = DBConnection.connect();
            
            // 4. Το Query για τον πίνακα language_ref
            String sql = "SELECT * FROM language_ref"; 
            
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String col1 = rs.getString(1); 
                String col2 = rs.getString(2); 
                
                // Προσθήκη γραμμής
                model.addRow(new Object[]{col1, col2});
            }
            
            con.close(); // Κλείσιμο σύνδεσης
            
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Σφάλμα: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new java.awt.TextField();
        txtCode = new java.awt.TextField();

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

        jButton2.setText("Add");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("Delete");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jLabel1.setText("Name");

        jLabel2.setText("Code");

        txtName.addActionListener(this::txtNameActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addComponent(jButton3)))
                        .addGap(87, 87, 87)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton2))))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String code = txtCode.getText().trim();
        String name = txtName.getText().trim();
        
        if(code.isEmpty() || name.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill in both Code and Name!");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("INSERT INTO language_ref (lang_code, lang_name) VALUES (?, ?)")) {
            
            pst.setString(1, code);
            pst.setString(2, name);
            pst.executeUpdate();
            
            javax.swing.JOptionPane.showMessageDialog(this, "Language Added Successfully!");
            
            loadData(); 
            txtCode.setText(""); 
            txtName.setText("");
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String code = txtCode.getText().trim(); 
        String name = txtName.getText().trim(); 
        
        if(code.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a language or enter a Code.");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
             // Ενημερώνουμε το Όνομα όπου ο Κωδικός είναι αυτός που δώσαμε
             java.sql.PreparedStatement pst = con.prepareStatement("UPDATE language_ref SET lang_name=? WHERE lang_code=?")) {
            
            pst.setString(1, name);
            pst.setString(2, code);
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Language Updated!");
                loadData();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Language Code not found!");
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String code = txtCode.getText();
        
        if(code.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter the Code to delete.");
            return;
        }
        
            try (java.sql.Connection con = DBConnection.connect();
                 java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM language_ref WHERE lang_code=?")) {
                
                pst.setString(1, code);
                int rows = pst.executeUpdate();
                
                if (rows > 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Deleted Successfully!");
                    loadData();
                    txtCode.setText(""); 
                    txtName.setText("");
                }
                
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error (Check if used elsewhere): " + e.getMessage());
            }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // Βρίσκουμε ποια γραμμή επιλέχθηκε
        int row = jTable1.getSelectedRow();
        
        if (row != -1) {
            String code = jTable1.getValueAt(row, 0).toString();
            String name = jTable1.getValueAt(row, 1).toString();
            
            txtCode.setText(code);
            txtName.setText(name);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed
   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LanguageRefForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.TextField txtCode;
    private java.awt.TextField txtName;
    // End of variables declaration//GEN-END:variables
}
