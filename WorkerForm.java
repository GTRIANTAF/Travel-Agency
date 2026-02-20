package travelagency;
import java.util.HashMap;
import java.sql.*;
/**
 *
 * @author trian
 */
public class WorkerForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(WorkerForm.class.getName());
    private final HashMap<String, String> branchMap = new HashMap<>();
    /**
     * Creates new form WorkerForm
     */
    public WorkerForm() {
        initComponents();
        fillCombos();
        loadData();
    }
    
    private void fillCombos() {
        cmbBranch.removeAllItems();
        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement("SELECT br_code, br_city, br_street FROM branch")) {
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String branchDisplay = rs.getString("br_city") + " (" + rs.getString("br_street") + ")";
                String code = rs.getString("br_code");
                
                branchMap.put(branchDisplay, code);
                cmbBranch.addItem(branchDisplay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData(){                                         
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        // Column identifiers matching the SQL schema
        model.setColumnIdentifiers(new Object[]{"AT", "Name", "Surname", "Email", "Salary", "Branch"});

        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement(
                 "SELECT w.wrk_AT, w.wrk_name, w.wrk_lname, w.wrk_email, w.wrk_salary, b.br_city " +
                 "FROM worker w LEFT JOIN branch b ON w.wrk_br_code = b.br_code")) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("wrk_AT"),
                    rs.getString("wrk_name"),
                    rs.getString("wrk_lname"),
                    rs.getString("wrk_email"),
                    rs.getString("wrk_salary"),
                    rs.getString("br_city")
                });
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSalary = new java.awt.TextField();
        txtAT = new java.awt.TextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtEmail3 = new java.awt.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSurname = new java.awt.TextField();
        txtName = new java.awt.TextField();
        jLabel5 = new javax.swing.JLabel();
        cmbBranch = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtSalary.addActionListener(this::txtSalaryActionPerformed);

        txtAT.addActionListener(this::txtATActionPerformed);

        jButton2.setText("Add");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        txtEmail3.addActionListener(this::txtEmail3ActionPerformed);

        jLabel7.setText("Salary");

        jLabel9.setText("Branch Code");

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

        jLabel4.setText("Surname");

        jLabel2.setText("Email");

        txtSurname.addActionListener(this::txtSurnameActionPerformed);

        txtName.addActionListener(this::txtNameActionPerformed);

        jLabel5.setText("AT");

        cmbBranch.addActionListener(this::cmbBranchActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAT, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3)
                                    .addComponent(jButton4)))
                            .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(241, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtATActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String selectedBranch = (String) cmbBranch.getSelectedItem();
        String brCode = branchMap.get(selectedBranch); // Get AT/ID from Map like your blueprint

        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement(
                 "INSERT INTO worker (wrk_AT, wrk_name, wrk_lname, wrk_email, wrk_salary, wrk_br_code) VALUES (?, ?, ?, ?, ?, ?)")) {
            
            pst.setString(1, txtAT.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3, txtSurname.getText());
            pst.setString(4, txtEmail3.getText());
            pst.setDouble(5, Double.parseDouble(txtSalary.getText()));
            pst.setString(6, brCode);
            
            pst.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, "Worker Added!");
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       String selectedBranch = (String) cmbBranch.getSelectedItem();
        String brCode = branchMap.get(selectedBranch);

        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement(
                 "UPDATE worker SET wrk_name=?, wrk_lname=?, wrk_email=?, wrk_salary=?, wrk_br_code=? WHERE wrk_AT=?")) {
            
            pst.setString(1, txtName.getText());
            pst.setString(2, txtSurname.getText());
            pst.setString(3, txtEmail3.getText());
            pst.setDouble(4, Double.parseDouble(txtSalary.getText()));
            pst.setString(5, brCode);
            pst.setString(6, txtAT.getText());
            
            pst.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, "Worker Updated!");
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtEmail3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmail3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmail3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            // Standard text field projection
            txtAT.setText(jTable1.getValueAt(row, 0).toString());
            txtName.setText(jTable1.getValueAt(row, 1).toString());
            txtSurname.setText(jTable1.getValueAt(row, 2).toString());
            txtEmail3.setText(jTable1.getValueAt(row, 3).toString());
            txtSalary.setText(jTable1.getValueAt(row, 4).toString());

            String cityNameInTable = jTable1.getValueAt(row, 5).toString();

            boolean found = false;
            for (int i = 0; i < cmbBranch.getItemCount(); i++) {
                String item = cmbBranch.getItemAt(i);
                if (item.startsWith(cityNameInTable)) {
                    cmbBranch.setSelectedIndex(i);
                    found = true;
                    break;
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a worker to delete.");
            return;
        }

        String at = jTable1.getValueAt(row, 0).toString();

        try (Connection con = DBConnection.connect();
             PreparedStatement pst = con.prepareStatement("DELETE FROM worker WHERE wrk_AT = ?")) {
            
            pst.setString(1, at);
            pst.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, "Worker Deleted!");
            loadData();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurnameActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaryActionPerformed

    private void cmbBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBranchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBranchActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new WorkerForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBranch;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.TextField txtAT;
    private java.awt.TextField txtEmail3;
    private java.awt.TextField txtName;
    private java.awt.TextField txtSalary;
    private java.awt.TextField txtSurname;
    // End of variables declaration//GEN-END:variables
}
