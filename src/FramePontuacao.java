
import model.Jogo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FramePontuacao extends JFrame {

    private final Jogo[] jogos = new Jogo[10];

    private final DefaultTableModel modelo = new DefaultTableModel(
        new Object[]{"Posição", "Nome", "Pontuação"}, 0
    ) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };

    private final JTable tabela = new JTable(modelo);

    public FramePontuacao() {
        setTitle("Ranking - 10 maiores pontuações");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("AS 10 MAIORES PONTUAÇÕES",SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        tabela.setRowHeight(28);
        tabela.setFillsViewportHeight(true);




        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        atualizarTabela();
    }

    // Recebe somente a pontuação
    public void adicionarJogo(int pontuacao) {

        int posicao = -1;

        // Verifica se a pontuação entra no ranking
        for (int i = 0; i < jogos.length; i++) {

            if (jogos[i] == null || pontuacao > jogos[i].getPontuacao()) {
                posicao = i;
                break;
            }
        }if (posicao == -1) {

            JOptionPane.showMessageDialog(this,"Essa pontuação não entra no ranking.");

            return;
        }

        // Solicita o nome somente após a classificação
        String nome = solicitarNome();

        if (nome == null) {
            return;
        }

        // Abre espaço no ranking
        for (int i = jogos.length - 1; i > posicao; i--) {
            jogos[i] = jogos[i - 1];
        }

        jogos[posicao] = new Jogo(pontuacao, nome);

        atualizarTabela();
    }

    private String solicitarNome() {

        while (true) {

            String nome = JOptionPane.showInputDialog(this,"parabéns! Você entrou no ranking. Digite seu nome:");

            if (nome == null) {
                return null;
            }

            nome = nome.trim().toUpperCase();
            atualizarTabela();
            return nome;


        }
    }

    private void atualizarTabela() {

        modelo.setRowCount(0);

        for (int i = 0; i < jogos.length; i++) {

            if (jogos[i] != null) {

                modelo.addRow(new Object[]{
                    i + 1,
                    jogos[i].getNome(),
                    jogos[i].getPontuacao()
                });
            }
        }
    }
}
