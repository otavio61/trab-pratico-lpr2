import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PainelNavinha extends JPanel implements ActionListener, KeyListener {
    // ALTURA -> y LARGURA -> x
    private final int ALTURA = 25;
    private final int LARGURA = 80;
    private final int TAMANHO_FONTE = 20;
    private char[][] plano_nave = new char[ALTURA][LARGURA];
    private char[][] plano_estrelas = new char[ALTURA][LARGURA];
    private int naveY = 18;
    private int naveX = 40;
    private boolean esquerda = false;
    private boolean direita = false;
    private int pontuacao = 0;

    public PainelNavinha() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moverNave();

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) esquerda = true;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) direita = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) esquerda = false;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)  direita = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        preencher();

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, TAMANHO_FONTE));

        desenharNave();

        g.setColor(Color.CYAN);
        for (int i = 0; i < plano_nave.length; i++) {
            String linha = new String(plano_nave[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE); // o uso de (i + 1) * TAMANHO_FONTE é para simular a divisão de caracteres por linha
        }

        desenharEstrelas();

        g.setColor(Color.YELLOW);
        for (int i = 0; i < plano_estrelas.length; i++) {
            String linha = new String(plano_estrelas[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE);
        }

        g.setColor(Color.WHITE);
        g.drawString("Pontuação: " + pontuacao, 10, 540);
    }

    /**
     * Insere os elementos que representam estrelas na matriz que representa o plano das estrelas,
     * verificando também se não existe elementos presentes na matriz que representa o plano da nave 
     */
    private void desenharEstrelas(){
        int quantidadeEstrelas = 0;
        int coordX, coordY;
        Random r = new Random();

        while (quantidadeEstrelas < 20) {
            coordX = r.nextInt(2, LARGURA);
            coordY = r.nextInt(1, ALTURA);

            if(plano_nave[coordY][coordX] == ' ') {
                plano_estrelas[coordY][coordX] = '.';
                quantidadeEstrelas++;
            }
        }
    }

    /**
     * Realiza a movimentação da nave
     */
    private void moverNave(){
        if(esquerda && naveX >= 3) naveX -= 2;
        else if (direita && naveX <= 69) naveX += 2;
    }

    /**
     * Desenha a nave na interface.
     * Crédito da arte: <a href='https://www.asciiart.eu/art/9b7a16c065fdb471'>Christian Jensen (também como C.J. e CJ)</a>
     */
    private void desenharNave() {
        plano_nave[naveY][naveX] = '.';

        plano_nave[naveY + 1][naveX - 1] = '.';
        plano_nave[naveY + 1][naveX] = '\'';
        plano_nave[naveY + 1][naveX + 1] = '.';

        plano_nave[naveY + 2][naveX - 1] = '|';
        plano_nave[naveY + 2][naveX] = 'o';
        plano_nave[naveY + 2][naveX + 1] = '|';

        plano_nave[naveY + 3][naveX - 2] = '.';
        plano_nave[naveY + 3][naveX - 1] = '\'';
        plano_nave[naveY + 3][naveX] = 'o';
        plano_nave[naveY + 3][naveX + 1] = '\'';
        plano_nave[naveY + 3][naveX + 2] = '.';

        plano_nave[naveY + 4][naveX - 2] = '|';
        plano_nave[naveY + 4][naveX - 1] = '.';
        plano_nave[naveY + 4][naveX] = '-';
        plano_nave[naveY + 4][naveX + 1] = '.';
        plano_nave[naveY + 4][naveX + 2] = '|';

        plano_nave[naveY + 5][naveX - 2] = '\'';
        plano_nave[naveY + 5][naveX + 2] = '\'';
    }

    /**
     * Preenche as matrizes que representam os planos da nave e estrelas com espaços em branco
     */
    private void preencher() {
        for (int i = 0; i < ALTURA; i++) 
            for (int j = 0; j < LARGURA; j++){
                plano_nave[i][j] = ' ';
                plano_estrelas[i][j] = ' ';
            }
    }
}
