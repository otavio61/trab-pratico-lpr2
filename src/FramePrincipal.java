import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FramePrincipal extends JFrame{
    
    public FramePrincipal(){
        super("Fliperama");
        setSize(980, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/icons/ghost.png")).getImage());

        add(new PainelNavinha());
        
        setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    private void setLookAndFeel(String lookAndFeel){
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
