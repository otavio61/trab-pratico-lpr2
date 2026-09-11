import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{

    public Main(){
        setTitle("Teste");
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new Painel());

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

class Painel extends JPanel{

    public Painel(){
        setBackground(Color.BLACK);
    }
}