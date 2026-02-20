package travelagency;
//
public class TravelAgency {

    public static void main(String[] args) {
        // Ρυθμίζουμε το Look and Feel για να φαίνεται πιο μοντέρνο (Nimbus)
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Δεν φορτώθηκε το Nimbus theme.");
        }

        // Εκκίνηση του MainFrame (GUI)
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
    
}