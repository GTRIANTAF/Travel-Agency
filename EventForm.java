package travelagency;

import java.sql.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class EventForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EventForm.class.getName());

    private HashMap<String, Integer> tripMap = new HashMap<>();
    
    private int oldTripId = -1;
    private String oldStartTime = "";
   
    public EventForm() {
        initComponents();
        fillTripCombo();
        loadData();
    }

    private void fillTripCombo() {
        cmbTrip.removeAllItems();
        try (Connection con = DBConnection.connect(); 
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT tr_id, tr_departure FROM trip")) {
            while (rs.next()) {
                String label = "Trip #" + rs.getInt("tr_id") + " (" + rs.getTimestamp("tr_departure") + ")";
                tripMap.put(label, rs.getInt("tr_id"));
                cmbTrip.addItem(label);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Trip ID", "Start Time", "End Time", "Description"});

        String sql = "SELECT * FROM event ORDER BY ev_tr_id, ev_start";
        try (Connection con = DBConnection.connect(); 
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ev_tr_id"),
                    rs.getTimestamp("ev_start"),
                    rs.getTimestamp("ev_end"),
                    rs.getString("ev_descr")
                });
            }
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Load Error: " + e.getMessage()); }
    }
    
    private void selectTripInCombo(int id) {
        for (String label : tripMap.keySet()) {
            if (tripMap.get(label) == id) {
                cmbTrip.setSelectedItem(label);
                break;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtDeparture = new java.awt.TextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtArrival = new java.awt.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        cmbTrip = new javax.swing.JComboBox<>();
        txtStart = new java.awt.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Add");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel3.setText("Start");

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

        jLabel4.setText("End");

        jLabel7.setText("Description");

        jLabel5.setText("Event ID");

        jButton4.setText("Delete");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        cmbTrip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel7))
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtDeparture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cmbTrip, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 363, Short.MAX_VALUE)
                                        .addComponent(jButton2)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbTrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String sql = "INSERT INTO event (ev_tr_id, ev_start, ev_end, ev_descr) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.connect(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, tripMap.get(cmbTrip.getSelectedItem().toString()));
            pst.setString(2, txtStart.getText()); // YYYY-MM-DD HH:MM:SS
            pst.setString(3, txtArrival.getText());
            pst.setString(4, txtDeparture.getText());

            pst.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Η δραστηριότητα προστέθηκε!");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Insert Error: " + e.getMessage()); }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if (oldTripId == -1) return;
        String sql = "UPDATE event SET ev_tr_id=?, ev_start=?, ev_end=?, ev_descr=? WHERE ev_tr_id=? AND ev_start=?";
        try (Connection con = DBConnection.connect(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, tripMap.get(cmbTrip.getSelectedItem().toString()));
            pst.setString(2, txtStart.getText());
            pst.setString(3, txtArrival.getText());
            pst.setString(4, txtDeparture.getText());
            
            // WHERE clause με τις παλιές τιμές
            pst.setInt(5, oldTripId);
            pst.setString(6, oldStartTime);

            pst.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Ενημερώθηκε!");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Update Error: " + e.getMessage()); }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            oldTripId = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
            oldStartTime = jTable1.getValueAt(row, 1).toString();
            
            // Sync GUI
            selectTripInCombo(oldTripId);
            txtStart.setText(oldStartTime);
            txtArrival.setText(jTable1.getValueAt(row, 2).toString());
            txtDeparture.setText(jTable1.getValueAt(row, 3).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if (oldTripId == -1) return;
        try (Connection con = DBConnection.connect(); 
             PreparedStatement pst = con.prepareStatement("DELETE FROM event WHERE ev_tr_id=? AND ev_start=?")) {
            pst.setInt(1, oldTripId);
            pst.setString(2, oldStartTime);
            pst.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Διαγράφηκε!");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Delete Error: " + e.getMessage()); }
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
        java.awt.EventQueue.invokeLater(() -> new EventForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbTrip;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.TextField txtArrival;
    private java.awt.TextField txtDeparture;
    private java.awt.TextField txtStart;
    // End of variables declaration//GEN-END:variables
}
