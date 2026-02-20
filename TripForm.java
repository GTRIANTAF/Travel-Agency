package travelagency;

import java.util.HashMap;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class TripForm extends javax.swing.JFrame {
    
    private final HashMap<String, String> branchMap = new HashMap<>();
    private final HashMap<String, String> driverMap = new HashMap<>();
    private final HashMap<String, String> guideMap = new HashMap<>();
    private String selectedTripId = ""; // Για το Update/Delete

    public TripForm() {
        initComponents();
        fillStatusCombo(); // Για το ENUM
        fillCombos();
        loadData();
    }

    private void fillStatusCombo() {
        cmbStatus.removeAllItems();
        cmbStatus.addItem("PLANNED");
        cmbStatus.addItem("CONFIRMED");
        cmbStatus.addItem("ACTIVE");
        cmbStatus.addItem("COMPLETED");
        cmbStatus.addItem("CANCELLED");
    }

    private void fillCombos() {
        try (Connection con = DBConnection.connect(); Statement st = con.createStatement()) {
            // Branches
            ResultSet rsB = st.executeQuery("SELECT br_code, br_city FROM branch");
            while(rsB.next()) {
                branchMap.put(rsB.getString("br_city"), rsB.getString("br_code"));
                cmbBranch.addItem(rsB.getString("br_city"));
            }
            // Drivers & Guides (Join με worker για να βλέπουμε ονόματα)
            ResultSet rsD = st.executeQuery("SELECT wrk_AT, wrk_name, wrk_lname FROM worker JOIN driver ON wrk_AT = drv_AT");
            while(rsD.next()){
                String name = rsD.getString("wrk_name") + " " + rsD.getString("wrk_lname");
                driverMap.put(name, rsD.getString("wrk_AT"));
                cmbDriver.addItem(name);
            }
            ResultSet rsG = st.executeQuery("SELECT wrk_AT, wrk_name, wrk_lname FROM worker JOIN guide ON wrk_AT = gui_AT");
            while(rsG.next()){
                String name = rsG.getString("wrk_name") + " " + rsG.getString("wrk_lname");
                guideMap.put(name, rsG.getString("wrk_AT"));
                cmbGuide.addItem(name);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

private void loadData() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    
    // Set column headers to match your SQL exactly
    model.setColumnIdentifiers(new Object[]{
        "ID", "Departure", "Return", "Max Seats", "Adult Cost", "Child Cost", "Status", "Min Part.", "Branch", "Guide", "Driver"
    });

    //JOIN with branch, worker (for Guide), and worker (for Driver) to get names
    String sql = "SELECT t.tr_id, t.tr_departure, t.tr_return, t.tr_maxseats, t.tr_cost_adult, " +
                 "t.tr_cost_child, t.tr_status, t.tr_min_participants, b.br_city, " +
                 "CONCAT(wg.wrk_name, ' ', wg.wrk_lname) as guide_name, " +
                 "CONCAT(wd.wrk_name, ' ', wd.wrk_lname) as driver_name " +
                 "FROM trip t " +
                 "LEFT JOIN branch b ON t.tr_br_code = b.br_code " +
                 "LEFT JOIN worker wg ON t.tr_gui_AT = wg.wrk_AT " +
                 "LEFT JOIN worker wd ON t.tr_drv_AT = wd.wrk_AT";

    try (Connection con = DBConnection.connect();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("tr_id"),
                rs.getTimestamp("tr_departure"),
                rs.getTimestamp("tr_return"),
                rs.getInt("tr_maxseats"),
                rs.getBigDecimal("tr_cost_adult"),
                rs.getBigDecimal("tr_cost_child"),
                rs.getString("tr_status"),
                rs.getInt("tr_min_participants"),
                rs.getString("br_city"),
                rs.getString("guide_name"),
                rs.getString("driver_name")
            });
        }
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Load Error: " + e.getMessage());
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMinPart = new java.awt.TextField();
        txtCostAdult = new java.awt.TextField();
        jLabel3 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRet = new java.awt.TextField();
        txtMaxSeats = new java.awt.TextField();
        txtDep = new java.awt.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        cmbBranch = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbGuide = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cmbDriver = new javax.swing.JComboBox<>();
        txtCostChild = new java.awt.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtCostAdult.addActionListener(this::txtCostAdultActionPerformed);

        jLabel3.setText("Departure");

        jLabel4.setText("Return");

        jLabel2.setText("Max Seats");

        txtRet.addActionListener(this::txtRetActionPerformed);

        txtMaxSeats.addActionListener(this::txtMaxSeatsActionPerformed);

        txtDep.addActionListener(this::txtDepActionPerformed);

        jLabel7.setText("Cost (adult)");

        jLabel10.setText("Status");

        jLabel9.setText("Cost (child)");

        jLabel11.setText("Min Participants");

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

        Add.setText("Add");
        Add.addActionListener(this::AddActionPerformed);

        update.setText("Update");
        update.addActionListener(this::updateActionPerformed);

        Delete.setText("Delete");
        Delete.addActionListener(this::DeleteActionPerformed);

        jLabel12.setText("Guide");

        jLabel13.setText("Branch");

        jLabel14.setText("Driver");

        txtCostChild.addActionListener(this::txtCostChildActionPerformed);

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
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbGuide, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel10))
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMinPart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMaxSeats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtRet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCostAdult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCostChild, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Add)
                        .addGap(73, 73, 73)
                        .addComponent(update)
                        .addGap(67, 67, 67)
                        .addComponent(Delete))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtMaxSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtCostAdult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtCostChild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMinPart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cmbBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbGuide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbDriver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add)
                    .addComponent(update)
                    .addComponent(Delete))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCostAdultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostAdultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostAdultActionPerformed

    private void txtRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRetActionPerformed

    private void txtMaxSeatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaxSeatsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaxSeatsActionPerformed

    private void txtDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepActionPerformed

    private void txtCostChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostChildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostChildActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        String sql = "INSERT INTO trip (tr_departure, tr_return, tr_maxseats, tr_cost_adult, tr_cost_child, tr_status, tr_min_participants, tr_br_code, tr_gui_AT, tr_drv_AT) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.connect(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, txtDep.getText()); // Format: 2024-05-20 10:00:00
            pst.setString(2, txtRet.getText());
            pst.setInt(3, Integer.parseInt(txtMaxSeats.getText()));
            pst.setDouble(4, Double.parseDouble(txtCostAdult.getText()));
            pst.setDouble(5, Double.parseDouble(txtCostChild.getText()));
            pst.setString(6, cmbStatus.getSelectedItem().toString());
            pst.setInt(7, Integer.parseInt(txtMinPart.getText()));
            pst.setString(8, branchMap.get(cmbBranch.getSelectedItem().toString()));
            pst.setString(9, guideMap.get(cmbGuide.getSelectedItem().toString()));
            pst.setString(10, driverMap.get(cmbDriver.getSelectedItem().toString()));
            
            pst.executeUpdate();
            loadData();
            javax.swing.JOptionPane.showMessageDialog(this, "Trip Added!");
        } catch (Exception e) { javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
    }//GEN-LAST:event_AddActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if (selectedTripId.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Επιλέξτε πρώτα ένα ταξίδι από τον πίνακα!");
        return;
    }

        String sql = "UPDATE trip SET tr_departure=?, tr_return=?, tr_maxseats=?, tr_cost_adult=?, tr_cost_child=?, " +
                     "tr_status=?, tr_min_participants=?, tr_br_code=?, tr_gui_AT=?, tr_drv_AT=? WHERE tr_id=?";

        try (Connection con = DBConnection.connect(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, txtDep.getText());
            pst.setString(2, txtRet.getText());
            pst.setInt(3, Integer.parseInt(txtMaxSeats.getText()));
            pst.setDouble(4, Double.parseDouble(txtCostAdult.getText()));
            pst.setDouble(5, Double.parseDouble(txtCostChild.getText()));
            pst.setString(6, cmbStatus.getSelectedItem().toString());
            pst.setInt(7, Integer.parseInt(txtMinPart.getText()));
            pst.setString(8, branchMap.get(cmbBranch.getSelectedItem().toString()));
            pst.setString(9, guideMap.get(cmbGuide.getSelectedItem().toString()));
            pst.setString(10, driverMap.get(cmbDriver.getSelectedItem().toString()));
            pst.setInt(11, Integer.parseInt(selectedTripId));

            pst.executeUpdate();
            loadData();
            javax.swing.JOptionPane.showMessageDialog(this, "Τα στοιχεία του ταξιδιού ενημερώθηκαν!");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Σφάλμα Ενημέρωσης: " + e.getMessage());
        }
    }//GEN-LAST:event_updateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a trip first!");
            return;
        }

        // Get the ID from the first column (Column 0)
        String id = jTable1.getValueAt(row, 0).toString();  

        try (Connection con = DBConnection.connect();
            PreparedStatement pst = con.prepareStatement("DELETE FROM trip WHERE tr_id = ?")) {

            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(this, "Successfully Deleted!");
            loadData(); // Refresh the table

        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Cannot delete: This trip has existing Reservations or Destinations linked to it.\n" +
                    "Delete those records first.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            selectedTripId = jTable1.getValueAt(row, 0).toString();
            txtDep.setText(jTable1.getValueAt(row, 1).toString());
            txtRet.setText(jTable1.getValueAt(row, 2).toString());
            txtMaxSeats.setText(jTable1.getValueAt(row, 3).toString());
            txtCostAdult.setText(jTable1.getValueAt(row, 4).toString());
            txtCostChild.setText(jTable1.getValueAt(row, 5).toString());
            txtMinPart.setText(jTable1.getValueAt(row, 7).toString());
            cmbStatus.setSelectedItem(jTable1.getValueAt(row, 6).toString());
            cmbBranch.setSelectedItem(jTable1.getValueAt(row, 8).toString());
            cmbGuide.setSelectedItem(jTable1.getValueAt(row, 9).toString());
            cmbDriver.setSelectedItem(jTable1.getValueAt(row, 10).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new TripForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Delete;
    private javax.swing.JComboBox<String> cmbBranch;
    private javax.swing.JComboBox<String> cmbDriver;
    private javax.swing.JComboBox<String> cmbGuide;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.TextField txtCostAdult;
    private java.awt.TextField txtCostChild;
    private java.awt.TextField txtDep;
    private java.awt.TextField txtMaxSeats;
    private java.awt.TextField txtMinPart;
    private java.awt.TextField txtRet;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
