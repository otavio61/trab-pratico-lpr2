import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame{

    public Main(){
        setTitle("Teste");
        setSize(980, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/icons/ghost.png")).getImage());

        add(new PainelNavinha());

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
