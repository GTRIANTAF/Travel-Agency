package travelagency;
import java.sql.Date;

public class CustomerForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerForm.class.getName());
    
    public CustomerForm() {
        initComponents();
        loadData();
    }
    
    private void loadData(){   
       javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
       model.setRowCount(0);
       
       // 1. Define all columns to match the Database
       model.setColumnIdentifiers(new Object[]{"ID", "Name", "Surname", "Email", "Phone", "Address", "Birth Date"});

       try {
           java.sql.Connection con = DBConnection.connect();
           String sql = "SELECT * FROM customer"; 
           java.sql.PreparedStatement pst = con.prepareStatement(sql);
           java.sql.ResultSet rs = pst.executeQuery();
           
           while(rs.next()){
               String col1 = rs.getString("cust_id"); 
               String col2 = rs.getString("cust_name"); 
               String col3 = rs.getString("cust_lname"); 
               String col4 = rs.getString("cust_email"); 
               String col5 = rs.getString("cust_phone"); 
               String col6 = rs.getString("cust_address"); 
               String col7 = rs.getString("cust_birth_date"); 
               
               model.addRow(new Object[]{col1, col2, col3, col4, col5, col6, col7});
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

        txtEmail1 = new java.awt.TextField();
        txtEmail2 = new java.awt.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSurname = new java.awt.TextField();
        txtName = new java.awt.TextField();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new java.awt.TextField();
        txtId = new java.awt.TextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtEmail3 = new java.awt.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAddress = new java.awt.TextField();
        jLabel10 = new javax.swing.JLabel();
        txtBirthDate = new java.awt.TextField();

        jScrollPane2.setViewportView(jTextPane1);

        jLabel8.setText("Phone");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        jLabel5.setText("ID");

        txtId.addActionListener(this::txtIdActionPerformed);

        jButton2.setText("Add");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Update");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        txtEmail3.addActionListener(this::txtEmail3ActionPerformed);

        jLabel7.setText("Phone");

        jLabel9.setText("Address");

        jLabel10.setText("Birth Date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                            .addComponent(txtEmail3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3)
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton4)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4))
                    .addComponent(jLabel10))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
            
            // Map columns (0 to 6) to TextFields
            txtId.setText(model.getValueAt(row, 0).toString());
            txtName.setText(model.getValueAt(row, 1).toString());
            txtSurname.setText(model.getValueAt(row, 2).toString());
            txtEmail3.setText(model.getValueAt(row, 3).toString()); 
            txtPhone.setText(model.getValueAt(row, 4).toString());
            
            // Check for nulls in Address and Date
            Object addr = model.getValueAt(row, 5);
            txtAddress.setText(addr != null ? addr.toString() : "");
            
            Object bdate = model.getValueAt(row, 6);
            txtBirthDate.setText(bdate != null ? bdate.toString() : "");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String id = txtId.getText();
       
       if(id.isEmpty()) {
           javax.swing.JOptionPane.showMessageDialog(this, "Select a customer to delete.");
           return;
       }
       
       try (java.sql.Connection con = DBConnection.connect();
            java.sql.PreparedStatement pst = con.prepareStatement("DELETE FROM customer WHERE cust_id=?")) {
           
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

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String name = txtName.getText();
        String lname = txtSurname.getText();
        String email = txtEmail3.getText(); 
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String bdate = txtBirthDate.getText(); // Must be YYYY-MM-DD

        if(name.isEmpty() || lname.isEmpty() || email.isEmpty() || phone.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill Name, Surname, Email and Phone!");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
            java.sql.PreparedStatement pst = con.prepareStatement(
            "INSERT INTO customer (cust_name, cust_lname, cust_email, cust_phone, cust_address, cust_birth_date) VALUES (?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, name);
            pst.setString(2, lname);
            pst.setString(3, email);
            pst.setString(4, phone);
            pst.setString(5, address);
            
            // Handle Date: If empty, send NULL, otherwise convert string to SQL Date
            if(bdate.isEmpty()) {
                pst.setNull(6, java.sql.Types.DATE);
            } else {
                try {
                    pst.setDate(6, Date.valueOf(bdate));
                } catch (IllegalArgumentException e) {
                     javax.swing.JOptionPane.showMessageDialog(this, "Invalid Date Format! Use YYYY-MM-DD");
                     return;
                }
            }

            pst.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(this, "Customer Added!");
            loadData();
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String id = txtId.getText().trim();
        String name = txtName.getText();
        String lname = txtSurname.getText();
        String email = txtEmail3.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String bdate = txtBirthDate.getText();

        if(id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a customer to update.");
            return;
        }

        try (java.sql.Connection con = DBConnection.connect();
            java.sql.PreparedStatement pst = con.prepareStatement(
            "UPDATE customer SET cust_name=?, cust_lname=?, cust_email=?, cust_phone=?, cust_address=?, cust_birth_date=? WHERE cust_id=?")) {

            pst.setString(1, name);
            pst.setString(2, lname);
            pst.setString(3, email);
            pst.setString(4, phone);
            pst.setString(5, address);
            pst.setString(6, bdate);
        
            pst.setInt(7, Integer.parseInt(id));

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

    private void txtSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurnameActionPerformed

    private void txtEmail3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmail3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmail3ActionPerformed


    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(() -> new CustomerForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private java.awt.TextField txtAddress;
    private java.awt.TextField txtBirthDate;
    private java.awt.TextField txtEmail1;
    private java.awt.TextField txtEmail2;
    private java.awt.TextField txtEmail3;
    private java.awt.TextField txtId;
    private java.awt.TextField txtName;
    private java.awt.TextField txtPhone;
    private java.awt.TextField txtSurname;
    // End of variables declaration//GEN-END:variables
}