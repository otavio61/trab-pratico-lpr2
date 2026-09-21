import javax.swing.SwingUtilities;

public class Main{

    public static void main(String[] args) {
        
        /**
         * O uso de SwingUtilities.invokeLater garante que os componentes da 
         * biblioteca Swing sejam executados na Thread dedicada (Event Dispatch Thread)
         */
        SwingUtilities.invokeLater(() -> {
            new FramePrincipal().setVisible(true);
        }); 
    }
}
