package travelagency;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {
    
   private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());

    public MainFrame() {
        initComponents();
        fillCombos();
        checkDbConnection();
    }
    
    public void fillCombos() {
      String[] tables = {
            "Επιλέξτε Πίνακα...", 
            "Vehicle", "Accommodation", "Trip Accommodation", "Trip History", 
            "Database Administrator", "System Log", "Trip", "Reservation", "Customer", 
            "Destination", "Event", "Travel To", "Driver", "Guide", 
            "Worker", "Admin", "Branch", "Manages", "Languages", 
            "Language Ref", "Phones"
        };
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(tables));
        
        jButton1.setText("Vehicle Monitor (Bonus)");
        jLabel1.setText("Checking connection...");
        setTitle("Travel Agency Manager");
    }
    
    private void checkDbConnection() {
        try (Connection conn = DBConnection.connect()) {
            jLabel1.setText("Status: Connected (root)");
            jLabel1.setForeground(java.awt.Color.GREEN.darker());
        } catch (SQLException e) {
            jLabel1.setText("Status: Disconnected");
            jLabel1.setForeground(java.awt.Color.RED);
            JOptionPane.showMessageDialog(this, "Database Connection Failed!\nCheck DBConnection.java");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setBackground(new java.awt.Color(232, 242, 242));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\"Επιλέξτε Πίνακα...\"", "\"vehicle\"", "\"accommodation\"", "\"trip_accommodation\"", "\"trip_history\"", "\"dba\"", "\"system_log\"", "\"trip\"", "\"reservation\"", "\"customer\"", "\"destination\"", "\"event\"", "\"travel_to\"", "\"driver\"", "\"guide\"", "\"worker\"", "\"admin\"", "\"branch\"", "\"manages\"", "\"languages\"", "\"language_ref\"", "\"phones\"" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jPanel1.setBackground(new java.awt.Color(232, 242, 242));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(232, 241, 242));
        jButton1.setText("jButton1");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel1.setBackground(new java.awt.Color(242, 232, 242));
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, 0, 638, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        javax.swing.JFrame vFrame = null;
        String selectedTable = jComboBox1.getSelectedItem().toString();

        switch (selectedTable) {
            case "Vehicle":
                new VehicleForm().setVisible(true);
                break;
            case "Accommodation":
                new AccommodationForm().setVisible(true);
                break;
            case "Customer":
                new CustomerForm().setVisible(true);
                break;
            case "Destination":
                new DestinationForm().setVisible(true);
                break;
            case "Driver":
                new DriverForm().setVisible(true);
                break;
            case "Guide":
                new GuideForm().setVisible(true);
                break;
            case "Worker":
                new WorkerForm().setVisible(true);
                break;
            case "Admin":
                new AdminForm().setVisible(true);
                break;
            case "Branch":
                new BranchForm().setVisible(true);
                break;
            case "Manages":
                new ManagesForm().setVisible(true);
                break;
            case "Languages":
                new LanguagesForm().setVisible(true);
                break;
            case "Language Ref":
                new LanguageRefForm().setVisible(true);
                break;
            case "Phones":
                new PhoneForm().setVisible(true);
                break;
            case "System Log":
                new SystemLog().setVisible(true);
                break;
            case "Trip":
                new TripForm().setVisible(true);
                break;
            case "Trip History":
                new TripHistoryForm().setVisible(true);
                break;
            case "Travel To":
                new TravelToForm().setVisible(true);
                break;
            case "Event":
                new TravelToForm().setVisible(true);
            break;
            case "Database Administrator":
                new DBAForm().setVisible(true);
            break;
            case "Trip Accommodation":
                new DBAForm().setVisible(true);
            break;
            case "Reservation":
                new ReservationForm().setVisible(true);
            break;
        default:
            if (!selectedTable.equals("Επιλέξτε Πίνακα...")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Table selection not recognized.");
            }
        }

        if (vFrame != null) {
            vFrame.setVisible(true);
            vFrame.setLocationRelativeTo(null);
        }
     
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AvailableVehiclesForm form = new AvailableVehiclesForm();
        form.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
