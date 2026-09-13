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
    private char[][] tela;
    private int naveY = 18;
    private int naveX = 36;
    private boolean esquerda = false;
    private boolean direita = false;

    public PainelNavinha() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        tela = new char[ALTURA][LARGURA];

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

        desenharEstrelas();
        desenharNave();

        for (int i = 0; i < tela.length; i++) {
            String linha = new String(tela[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE); // o uso de (i + 1) * TAMANHO_FONTE é para simular divisão de caracteres por linha
        }
    }

    /**
     * Insere os elementos que representam estrelas na matriz que representa a interface
     */
    private void desenharEstrelas(){
        int quantidadeEstrelas = 0;
        int coordX, coordY;
        Random r = new Random();

        while (quantidadeEstrelas <= 20) {
            coordX = r.nextInt(2, LARGURA);
            coordY = r.nextInt(1, ALTURA);

            if(tela[coordY][coordX] == ' ') {
                desenhar('.', coordY, coordX);
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
        desenhar('.', naveY, naveX);

        desenhar('.', naveY + 1, naveX - 1);
        desenhar('\'', naveY + 1, naveX);
        desenhar('.', naveY + 1, naveX + 1);

        desenhar('|', naveY + 2, naveX - 1);
        desenhar('o', naveY + 2, naveX);
        desenhar('|', naveY + 2, naveX + 1);

        desenhar('.', naveY + 3, naveX - 2);
        desenhar('\'', naveY + 3, naveX - 1);
        desenhar('o', naveY + 3, naveX);
        desenhar('\'', naveY + 3, naveX + 1);
        desenhar('.', naveY + 3, naveX + 2);

        desenhar('|', naveY + 4, naveX - 2);
        desenhar('.', naveY + 4, naveX - 1);
        desenhar('-', naveY + 4, naveX);
        desenhar('.', naveY + 4, naveX + 1);
        desenhar('|', naveY + 4, naveX + 2);

        desenhar('\'', naveY + 5, naveX - 2);
        desenhar('\'', naveY + 5, naveX + 2);
    }

    /**
     * Preenche a matriz que representa a interface com espaços em branco
     */
    private void preencher() {
        for (int i = 0; i < ALTURA; i++) 
            for (int j = 0; j < LARGURA; j++) 
                tela[i][j] = ' ';
    }

    /**
     * Insere um novo caractere na matriz que representa a interface
     * 
     * @param c Novo caractere
     * @param y Coordenada do eixo y
     * @param x Coordernada do eixo x
     */
    private void desenhar(char c, int y, int x) {
        tela[y][x] = c;
    }
}
