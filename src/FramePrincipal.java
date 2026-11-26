
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FramePrincipal extends JFrame{

    private JMenuBar menuBar;
    private JMenu menu;

    private PainelNavinha painelNavinha;

    public FramePrincipal(){
        super("Fliperama");
        setSize(980, 615);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setIconImage(new ImageIcon(getClass().getResource("/icons/ghost.png")).getImage());

        painelNavinha = new PainelNavinha();
        add(painelNavinha);


        menu = new JMenu("Navinha");


        menu.addMenuListener(new MenuListener() {
            
            @Override
            public void menuSelected(MenuEvent e) {
                painelNavinha.pausar();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                painelNavinha.retornar();
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                painelNavinha.retornar();
            }
        });

        menuBar = new JMenuBar();
        menuBar.add(menu);

        setJMenuBar(menuBar);

        setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    public void iniciarJogo() {
        setVisible(true);
        painelNavinha.requestFocusInWindow();
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
