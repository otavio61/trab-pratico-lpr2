import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FramePrincipal extends JFrame{

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem navinhaJogar, navinhaCor;

    private PainelNavinha painelNavinha;

    public FramePrincipal(){
        super("Fliperama");
        setSize(980, 615);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/icons/ghost.png")).getImage());

        painelNavinha = new PainelNavinha();

        navinhaJogar = new JMenuItem("Novo jogo");
        navinhaJogar.addActionListener(e -> {adicionarPainelNavinha();});

        navinhaCor = new JMenuItem("Alterar cor da nave");
        navinhaCor.addActionListener(e -> {painelNavinha.alterarCorNave();});

        menu = new JMenu("Navinha");
        menu.add(navinhaJogar);
        menu.addSeparator();
        menu.add(navinhaCor);

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

    private void adicionarPainelNavinha(){
        remove(painelNavinha);

        painelNavinha = new PainelNavinha();
        
        add(painelNavinha);

        painelNavinha.requestFocusInWindow();

        validate();
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
