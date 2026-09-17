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
    private final int LARGURA = 950;
    private final int TAMANHO_FONTE = 20;
    private char[][] tela;
    private int naveY = 18;
    private int naveX = 463;
    private boolean esquerda = false;
    private boolean direita = false;
    private int pontuacao = 0;

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
        
        //Sugestão: Consolas
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, TAMANHO_FONTE));

        // desenhar(new char[]{'.', '.', ':', '!', '|', '|', '|', '#', '|', 'T', '|', '!', ':', '.',
        // '-', '-', '-', ' ', '-', '-', '+', '-', '<', '>', '-', '+', '-', ' ', '-', '-', '-', ' ', ' '},
        // new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 8, 8, 8, 8, 8, 8, 8, 8, 8,
        // 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
        // new int[]{69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 60, 61, 62, 63, 64, 65,
        // 66, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79});

        g.setColor(Color.CYAN);
        desenhar(new char[]{'.', '.', '\'', '.', '|', 'o', '|', '.', '\'', 'o', '\'', '.', '|', '.', '-', '.', '|', '\'', '\''}, 
        new int[]{naveY, naveY + 1, naveY + 1, naveY + 1, naveY + 2, naveY + 2, naveY + 2, naveY + 3, naveY + 3, naveY + 3, naveY + 3, naveY + 3, naveY + 4, naveY + 4, naveY + 4, naveY + 4, naveY + 4, naveY + 5, naveY + 5},
        new int[]{naveX + 1, naveX - 12, naveX, naveX + 12, naveX - 12, naveX, naveX + 12, naveX - 24, naveX - 12, naveX, naveX + 12, naveX + 24, naveX - 24, naveX - 12, naveX, naveX + 12, naveX + 24, naveX - 24, naveX + 24}, g);
        
        g.setColor(Color.YELLOW);
        desenharEstrelasPequenas(g);


        // for (int i = 0; i < tela.length; i++) {
        //     String linha = new String(tela[i]);

        //     g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE); // o uso de (i + 1) * TAMANHO_FONTE é para simular divisão de caracteres por linha
        // }

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, TAMANHO_FONTE + 5));
        g.drawString("Pontuação : " + pontuacao++, 6, 540);
    }

    /**
     * Insere os elementos que representam estrelas pequenas na matriz que representa a interface
     * 
     * @param g Contexto gráfico
     */
    private void desenharEstrelasPequenas(Graphics g){
        int quantidadeEstrelas = 0;
        int coordX, coordY;
        Random r = new Random();

        while (quantidadeEstrelas < 20) {
            coordX = r.nextInt(0, LARGURA);
            coordY = r.nextInt(1, ALTURA);

            if(tela[coordY][coordX] == ' ') {
                desenhar('.', coordY, coordX, g);
                quantidadeEstrelas++;
            }
        }
    }

    /**
     * Realiza a movimentação da nave
     */
    private void moverNave(){
        if(esquerda && naveX >= 71) naveX -= 24;
        else if (direita && naveX <= 878) naveX += 24;
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
     * @param g Contexto gráfico 
     */
    private void desenhar(char c, int y, int x, Graphics g) {
        g.drawString(Character.toString(c), x, (y + 1) * TAMANHO_FONTE);
        tela[y][x] = c;
    }

    /**
     * Insere os novos caracteres na matriz que representa a interface
     * 
     * @param c Novos caractere
     * @param y Coordenadas do eixo y
     * @param x Coordernadas do eixo x
     * @param g Contexto gráfico 
     */
    private void desenhar(char[] c, int[] y, int[] x, Graphics g) {
        for (int i = 0; i < c.length; i++) {
            g.drawString(Character.toString(c[i]), x[i], (y[i] + 1) * TAMANHO_FONTE);
            tela[y[i]][x[i]] = c[i];
        }
    }
}
