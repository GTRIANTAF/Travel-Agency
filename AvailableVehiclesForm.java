package travelagency;

import java.sql.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AvailableVehiclesForm extends JFrame {

    private JComboBox<String> cmbTrip;
    private JButton btnLoad;
    private JTable table;
    private DefaultTableModel model;
    
    private HashMap<String, Integer> tripMap = new HashMap<>();

    public AvailableVehiclesForm() {
        setTitle("Available Vehicles for Trip");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblTripId = new JLabel("Select Trip:");
        cmbTrip = new JComboBox<>();
        
        fillTripCombo();

        btnLoad = new JButton("Load Available Vehicles");
        btnLoad.addActionListener(e -> loadVehicles());

        model = new DefaultTableModel(
            new String[]{"veh_id", "veh_brand", "veh_model", "veh_type", "veh_seats", "veh_status"}, 0
        );
        table = new JTable(model);

        JPanel topPanel = new JPanel();
        topPanel.add(lblTripId);
        topPanel.add(cmbTrip);
        topPanel.add(btnLoad);

        add(topPanel, "North");
        add(new JScrollPane(table), "Center");
    }

    // Μέθοδος για να γεμίσει το ComboBox με τα διαθέσιμα ταξίδια
    private void fillTripCombo() {
        cmbTrip.removeAllItems();
        String sql = "SELECT tr_id, tr_departure FROM trip"; 
        
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("tr_id");
                Timestamp departure = rs.getTimestamp("tr_departure");
                String label = "Trip ID:" + id + " (Starts: " + departure + ")";
                
                tripMap.put(label, id);
                cmbTrip.addItem(label);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Σφάλμα κατά το γέμισμα των ταξιδιών: " + ex.getMessage());
        }
    }

    private void loadVehicles() {
        model.setRowCount(0);

        String selectedLabel = (String) cmbTrip.getSelectedItem();
        if (selectedLabel == null) {
            JOptionPane.showMessageDialog(this, "Δεν υπάρχουν διαθέσιμα ταξίδια.");
            return;
        }

        int tripId = tripMap.get(selectedLabel);

        try (Connection conn = DBConnection.connect();
             CallableStatement stmt = conn.prepareCall("{CALL available_vehicles_for_trip(?)}")) {

            stmt.setInt(1, tripId);
            ResultSet rs = stmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                model.addRow(new Object[]{
                    rs.getInt("veh_id"),
                    rs.getString("veh_brand"),
                    rs.getString("veh_model"),
                    rs.getString("veh_type"),
                    rs.getInt("veh_seats"),
                    rs.getString("veh_status")
                });
            }
            
            if (!found) {
                JOptionPane.showMessageDialog(this, "Δεν βρέθηκαν διαθέσιμα οχήματα για αυτό το ταξίδι.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AvailableVehiclesForm().setVisible(true));
    }
}