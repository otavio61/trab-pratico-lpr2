
import javax.swing.*;
import java.awt.*;


public class TelaInicial extends JFrame {
    private static final String MENU = "menu";
    private static final String JOGO = "jogo";

    private final JPanel telas = new JPanel(new CardLayout());
    private final FramePontuacao framePontuacao = new FramePontuacao();
    private Color corNave = Color.CYAN;
    private PainelNavinha painelNavinha;

    public TelaInicial() {
        setTitle("AMO - Menu Principal");
        setSize(980, 615);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setBackground(Color.BLACK);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        String logo =
                " ###  #   #  ### \n" +
                "#   # ## ## #   #\n" +
                "##### # # # #   #\n" +
                "#   # #   # #   #\n" +
                "#   # #   #  ### ";

        JTextArea titulo = new JTextArea(logo);
        titulo.setEditable(false);
        titulo.setOpaque(false);
        titulo.setFocusable(false);
        titulo.setForeground(Color.white);
        titulo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(Box.createVerticalGlue());
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(25));

        // Botões
        JButton novoJogo = criarBotao("NOVO JOGO");
        JButton alterarCor = criarBotao("ALTERAR COR DA NAVE");
        JButton pontuacao = criarBotao("RECORDS");

        painel.add(novoJogo);
        painel.add(Box.createVerticalStrut(15));

        painel.add(alterarCor);
        painel.add(Box.createVerticalStrut(15));

        painel.add(pontuacao);
        painel.add(Box.createVerticalGlue());

        // Ações dos botões
        novoJogo.addActionListener(e -> {
            abrirJogo();
        });

        alterarCor.addActionListener(e -> {
            Color novaCor = JColorChooser.showDialog(this, "Escolha a cor da nave", corNave);
            if (novaCor != null) {
                corNave = novaCor;
            }
        });

        pontuacao.addActionListener(e -> {
            framePontuacao.setVisible(true);
        });

        telas.add(painel, MENU);
        setContentPane(telas);
        setVisible(true);
    }

    private void abrirJogo() {
        if (painelNavinha != null) {
            telas.remove(painelNavinha);
        }

        painelNavinha = new PainelNavinha(this::mostrarMenu, corNave, framePontuacao);
        telas.add(painelNavinha, JOGO);
        ((CardLayout) telas.getLayout()).show(telas, JOGO);
        painelNavinha.requestFocusInWindow();
    }

    private void mostrarMenu() {
        ((CardLayout) telas.getLayout()).show(telas, MENU);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);

        botao.setFont(new Font("Monospaced", Font.BOLD, 16));
        botao.setForeground(Color.GREEN);
        botao.setBackground(Color.BLACK);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(250, 50));

        return botao;
    }


}
