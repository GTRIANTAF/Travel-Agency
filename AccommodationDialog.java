package travelagency;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AccommodationDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AccommodationDialog.class.getName());
    private final AccommodationForm parent;
    private final Integer accId; 
    private HashMap<String, Integer> destinationMap = new HashMap<>();

    public AccommodationDialog(AccommodationForm parent, Integer accId) {
        super(parent, true);
        this.parent = parent;
        this.accId = accId;
        initComponents();
        fillCombos();

        
        txtId.setEditable(false);
        
        if (accId != null) {
            loadDetails();
            txtId.setText(accId.toString()); // Show ID
            setTitle("Edit Accommodation (ID: " + accId + ")");
            btnSave.setText("Update");
        } else {
            clearFields();
            txtId.setText("(Auto)"); // Show placeholder
            setTitle("Add New Accommodation");
            btnSave.setText("Save");
        }
        
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void clearFields() {
        // Clear Text Fields
        txtName.setText("");
        txtStreet.setText("");
        txtNum.setText("");
        txtPostcode.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtRoomCap.setText("");
        txtPrice.setText("");
        txtRating.setText("");
        
        // Reset Checkboxes
        chkWifi.setSelected(false);
        chkRest.setSelected(false);
        chkAC.setSelected(false);
        chkDisabled.setSelected(false);
        
        // Reset Dropdowns to first option (optional)
        if (cmbType.getItemCount() > 0) cmbType.setSelectedIndex(0);
        if (cmbStars.getItemCount() > 0) cmbStars.setSelectedIndex(0);
        if (cmbStatus.getItemCount() > 0) cmbStatus.setSelectedIndex(0);
        if (cmbDst.getItemCount() > 0) cmbDst.setSelectedIndex(0);
    }
    
    private void fillCombos() {
        cmbType.addItem("Hotel"); cmbType.addItem("Hostel"); cmbType.addItem("Resort"); 
        cmbType.addItem("Apartment"); cmbType.addItem("Room");
        
        cmbStars.addItem("1"); cmbStars.addItem("2"); cmbStars.addItem("3"); 
        cmbStars.addItem("4"); cmbStars.addItem("5");

        cmbStatus.addItem("ACTIVE"); cmbStatus.addItem("INACTIVE");
        
        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("SELECT dst_id, dst_name FROM destination")) {
            
            java.sql.ResultSet rs = pst.executeQuery();
            cmbDst.removeAllItems();
            
            while (rs.next()) {
                String name = rs.getString("dst_name");
                int id = rs.getInt("dst_id");
                destinationMap.put(name, id);
                cmbDst.addItem(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadDetails() {
        try (java.sql.Connection con = DBConnection.connect();
             java.sql.PreparedStatement pst = con.prepareStatement("SELECT * FROM accommodation WHERE acc_id=?")) {
            
            pst.setInt(1, accId);
            java.sql.ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                txtName.setText(rs.getString("acc_name"));
                cmbType.setSelectedItem(rs.getString("acc_type"));
                cmbStars.setSelectedItem(rs.getString("acc_stars"));
                cmbStatus.setSelectedItem(rs.getString("acc_status"));
                txtStreet.setText(rs.getString("acc_street"));
                txtNum.setText(rs.getString("acc_street_num"));
                txtPostcode.setText(rs.getString("acc_postcode"));
                txtPhone.setText(rs.getString("acc_phone"));
                txtEmail.setText(rs.getString("acc_email"));
                txtRoomCap.setText(rs.getString("acc_roomcapacity"));
                txtPrice.setText(rs.getString("acc_pricepernight"));
                txtRating.setText(rs.getString("acc_rating"));

                // Select correct Destination
                int dstId = rs.getInt("acc_dst_id");
                for (String name : destinationMap.keySet()) {
                    if (destinationMap.get(name) == dstId) {
                        cmbDst.setSelectedItem(name);
                        break;
                    }
                }

                // Check Facilities
                String fac = rs.getString("acc_facilities");
                if (fac != null) {
                    if (fac.contains("WiFi")) chkWifi.setSelected(true);
                    if (fac.contains("Restaurant")) chkRest.setSelected(true);
                    if (fac.contains("AC")) chkAC.setSelected(true);
                    if (fac.contains("DisabledAccess")) chkDisabled.setSelected(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading details: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        cmbType = new javax.swing.JComboBox<>();
        txtRating = new javax.swing.JTextField();
        cmbStars = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        txtStreet = new javax.swing.JTextField();
        txtNum = new javax.swing.JTextField();
        txtPostcode = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtRoomCap = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        chkWifi = new javax.swing.JCheckBox();
        chkRest = new javax.swing.JCheckBox();
        chkAC = new javax.swing.JCheckBox();
        chkDisabled = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cmbDst = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name");

        jLabel2.setText("Type");

        jLabel3.setText("Rating");

        jLabel4.setText("Stars");

        jLabel5.setText("Status");

        jLabel6.setText("Street");

        jLabel7.setText("Number");

        jLabel8.setText("Post Code");

        jLabel9.setText("Phone");

        jLabel10.setText("Email");

        jLabel11.setText("Capacity");

        jLabel12.setText("Price");

        jLabel13.setText("Facilities");

        txtName.addActionListener(this::txtNameActionPerformed);

        cmbStars.addActionListener(this::cmbStarsActionPerformed);

        chkWifi.setText("Wifi");
        chkWifi.addActionListener(this::chkWifiActionPerformed);

        chkRest.setText("Restaurant");
        chkRest.addActionListener(this::chkRestActionPerformed);

        chkAC.setText("AC");

        chkDisabled.setText("Disabled");
        chkDisabled.addActionListener(this::chkDisabledActionPerformed);

        jLabel14.setText("ID");

        txtId.setEditable(false);
        txtId.addActionListener(this::txtIdActionPerformed);

        jLabel15.setText("Destination");

        cmbDst.addActionListener(this::cmbDstActionPerformed);

        btnSave.setText("Confrim");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addComponent(jLabel15)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cmbDst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbStars, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRating, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStreet, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNum)
                            .addComponent(chkWifi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkRest, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(chkAC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkDisabled, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrice)
                            .addComponent(txtRoomCap)
                            .addComponent(txtEmail)
                            .addComponent(txtPhone)
                            .addComponent(txtPostcode, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtId))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbStars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbDst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtRoomCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(chkWifi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkRest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkDisabled)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void cmbStarsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStarsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStarsActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void cmbDstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDstActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String name = txtName.getText();
        String type = cmbType.getSelectedItem().toString();
        String stars = cmbStars.getSelectedItem() != null ? cmbStars.getSelectedItem().toString() : null;
        String status = cmbStatus.getSelectedItem().toString();
        
        // Destination Logic
        if (cmbDst.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "Please add a Destination in DB first!");
             return;
        }
        String dstName = cmbDst.getSelectedItem().toString();
        int dstId = destinationMap.get(dstName);
        
        StringBuilder sb = new StringBuilder();
        if(chkWifi.isSelected()) sb.append("WiFi,");
        if(chkRest.isSelected()) sb.append("Restaurant,");
        if(chkAC.isSelected()) sb.append("AC,");
        if(chkDisabled.isSelected()) sb.append("DisabledAccess,");
        String facilities = sb.length() > 0 ? sb.substring(0, sb.length()-1) : null;

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required!");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect()) {
            String sql;
            if (accId == null) {
                sql = "INSERT INTO accommodation (acc_name, acc_type, acc_stars, acc_status, acc_street, acc_street_num, acc_postcode, acc_dst_id, acc_phone, acc_email, acc_roomcapacity, acc_pricepernight, acc_rating, acc_facilities) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            } else {
                sql = "UPDATE accommodation SET acc_name=?, acc_type=?, acc_stars=?, acc_status=?, acc_street=?, acc_street_num=?, acc_postcode=?, acc_dst_id=?, acc_phone=?, acc_email=?, acc_roomcapacity=?, acc_pricepernight=?, acc_rating=?, acc_facilities=? WHERE acc_id=?";
            }

            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, type);
            pst.setString(3, stars);
            pst.setString(4, status);
            pst.setString(5, txtStreet.getText());
            pst.setObject(6, txtNum.getText().isEmpty() ? null : Integer.parseInt(txtNum.getText()));
            pst.setString(7, txtPostcode.getText());
            pst.setInt(8, dstId);
            pst.setString(9, txtPhone.getText());
            pst.setString(10, txtEmail.getText());
            pst.setObject(11, txtRoomCap.getText().isEmpty() ? null : Integer.parseInt(txtRoomCap.getText()));
            pst.setObject(12, txtPrice.getText().isEmpty() ? null : Double.parseDouble(txtPrice.getText()));
            pst.setObject(13, txtRating.getText().isEmpty() ? 0.0 : Double.parseDouble(txtRating.getText()));
            pst.setString(14, facilities);

            if (accId != null) pst.setInt(15, accId);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Saved!");
            
            parent.loadData(); 
            this.dispose();    
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void chkWifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkWifiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkWifiActionPerformed

    private void chkRestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRestActionPerformed

    private void chkDisabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDisabledActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDisabledActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AccommodationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccommodationForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkAC;
    private javax.swing.JCheckBox chkDisabled;
    private javax.swing.JCheckBox chkRest;
    private javax.swing.JCheckBox chkWifi;
    private javax.swing.JComboBox<String> cmbDst;
    private javax.swing.JComboBox<String> cmbStars;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNum;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPostcode;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRating;
    private javax.swing.JTextField txtRoomCap;
    private javax.swing.JTextField txtStreet;
    // End of variables declaration//GEN-END:variables
}
