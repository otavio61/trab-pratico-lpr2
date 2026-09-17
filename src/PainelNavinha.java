import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PainelNavinha extends JPanel implements KeyListener {
    // ALTURA -> y LARGURA -> x
    private final int ALTURA = 25;
    private final int LARGURA = 80;
    private final int TAMANHO_FONTE = 20;

    private char[][] plano_nave = new char[ALTURA][LARGURA];
    private char[][] plano_estrelas = new char[ALTURA][LARGURA];
    private char[][] plano_meteoros = new char[ALTURA][LARGURA];

    private int naveY = 18;
    private int naveX = 40;

    private boolean esquerda = false;
    private boolean direita = false;

    Timer timerAmbiente, timerMeteoro;

    private int pontuacao = 0;

    public PainelNavinha() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        timerAmbiente = new Timer(100, e -> {moverNave(); repaint();});
        timerAmbiente.start();

        preencherPlanoMeteoro(); // Isso deve acontecer apenas uma vez

        timerMeteoro = new Timer(500, e -> {desenharMeteoros(); repaint();}); // 100ms é um bom tempo, mas seria bom deixar um segundo Timer caso tenha opção de dificuldade
        timerMeteoro.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) esquerda = true;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) direita = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) esquerda = false;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)  direita = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        preencher();

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, TAMANHO_FONTE));
        g.drawString("== Jogo Navinha ==", 375, 20);

        desenharNave();

        g.setColor(Color.CYAN);
        for (int i = 0; i < plano_nave.length; i++) {
            String linha = new String(plano_nave[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE); // o uso de (i + 1) * TAMANHO_FONTE é para simular a divisão de caracteres por linha
        }

        g.setColor(Color.WHITE);
        for (int i = 0; i < plano_meteoros.length; i++) {
            String linha = new String(plano_meteoros[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE);
        }

        desenharEstrelas();

        g.setColor(Color.YELLOW);
        for (int i = 0; i < plano_estrelas.length; i++) {
            String linha = new String(plano_estrelas[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE);
        }

        g.setColor(Color.WHITE);
        g.drawString("Pontuação: " + pontuacao++, 10, 540);
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

            if(plano_nave[coordY][coordX] == ' ' && plano_meteoros[coordY][coordX] == ' ') {
                plano_estrelas[coordY][coordX] = '.';
                quantidadeEstrelas++;
            }
        }
    }

    private void desenharMeteoros(){
        int quantidadeMeteoros = 0;
        int coordX, coordY;
        Random r = new Random();

        for (int i = plano_meteoros.length - 1; i >= 0; i--) {
            for (int j = plano_meteoros[0].length - 1; j >= 0; j--) {
                if(plano_meteoros[i][j] == 'O'){
                    if (i + 1 > 24) 
                        plano_meteoros[i][j] = ' ';
                    else {
                        plano_meteoros[i + 1][j] = 'O';
                        plano_meteoros[i][j] = ' ';
                        quantidadeMeteoros++;
                    } 
                }
            }   
        }

        while (quantidadeMeteoros < 5) {
            coordX = r.nextInt(2, LARGURA);
            coordY = r.nextInt(1, 5);
            
            if(plano_meteoros[coordY][coordX] == ' '){
                plano_meteoros[coordY][coordX] = 'O';
                quantidadeMeteoros++;
            }
        }
    }

    /**
     * Realiza a movimentação da nave
     */
    private void moverNave(){
        if (esquerda && naveX >= 3) naveX -= 2;
        else if (direita && naveX <= 74) naveX += 2;
    }

    /**
     * Desenha a nave na interface.
     * Crédito da arte: <a href='https://www.asciiart.eu/art/9b7a16c065fdb471'>Christian Jensen (também como C.J. ou CJ)</a>
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
     * Preenche as matrizes que representam os planos dos elementos
     */
    private void preencher() {
        for (int i = 0; i < ALTURA; i++) 
            for (int j = 0; j < LARGURA; j++){
                plano_nave[i][j] = ' ';
                plano_estrelas[i][j] = ' ';
            }
    }

    /**
     * Preenche especificamente a matriz que representa o plano dos meteoros
     */
    private void preencherPlanoMeteoro(){
        for (int i = 0; i < ALTURA; i++) 
            for (int j = 0; j < LARGURA; j++)
                plano_meteoros[i][j] = ' '; // plano_meteoros é imutável entre os repaints
    }
}