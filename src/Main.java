import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends JFrame{

    public Main(){
        setTitle("Teste");
        setSize(900, 600);
        // setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new PainelNavinha());

        
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
