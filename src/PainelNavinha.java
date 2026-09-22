import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Meteoro;
import model.Nave;

public class PainelNavinha extends JPanel implements KeyListener {
    // ALTURA -> y LARGURA -> x
    private final int ALTURA = 25;
    private final int LARGURA = 80;
    private final int TAMANHO_FONTE = 20;

    private char[][] plano_nave;
    private char[][] plano_meteoros;
    private char[][] plano_estrelas = new char[ALTURA][LARGURA];

    private Nave nave = new Nave(ALTURA, LARGURA, 40, 18);
    private Meteoro meteoro = new Meteoro(ALTURA, LARGURA);

    private int pontuacao = 0;
    
    Timer timerAmbiente, timerColisao;

    int tempoAmbiente = 100, tempoColisao = 50; // tempo padrão em ms

    public PainelNavinha() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        
        timerAmbiente = new Timer(tempoAmbiente, e -> {verificarColisao(); moverNave(); repaint();});
        timerAmbiente.start();

        timerColisao = new Timer(tempoColisao, e -> {verificarColisao();});
        timerColisao.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) nave.setEsquerda(true);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) nave.setDireita(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) nave.setEsquerda(false);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) nave.setDireita(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, TAMANHO_FONTE));
        g.drawString("== Jogo Navinha ==", 375, 20);
        g.drawString("Pontuação: " + ++pontuacao, 10, 540);

        preencher();

        plano_nave = nave.desenhar();

        g.setColor(Color.CYAN);
        for (int i = 0; i < plano_nave.length; i++) {
            String linha = new String(plano_nave[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE); // o uso de (i + 1) * TAMANHO_FONTE é para simular a divisão de caracteres por linha
        }

        plano_meteoros = meteoro.desenhar();

        g.setColor(Color.WHITE);
        for (int i = 0; i < plano_meteoros.length; i++) {
            String linha = new String(plano_meteoros[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE);
        }

        desenharEstrelas();

        for (int i = 0; i < plano_estrelas.length; i++) {
            String linha = new String(plano_estrelas[i]);

            g.drawString(linha, 0, (i + 1) * TAMANHO_FONTE);
        }
    }

    /**
     * Verifica se houve colisão entre os meteoros e a nave;
     */
    private void verificarColisao() {
        if(plano_meteoros == null) return;

        for (int i = plano_meteoros.length - 1; i >= 0; i--) 
            for (int j = plano_meteoros[0].length - 1; j >= 0; j--) 
                if(plano_meteoros[i][j] == 'O' && plano_nave[i][j] != ' '){
                    finalizar();
                    return;
                }
    }

    /**
     * Insere os elementos que representam estrelas na matriz que representa o plano das estrelas,
     * verificando também se não existe elementos presentes na matriz que representa o plano da nave.
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

    /**
     * Realiza a movimentação da nave.
     */
    private void moverNave(){
        int coordenadaX = nave.getCoordenadaNaveX();

        if (nave.isEsquerda() && coordenadaX >= 3) nave.setCoordenadaNaveX(coordenadaX - 2);
        else if (nave.isDireita() && coordenadaX <= 74) nave.setCoordenadaNaveX(coordenadaX + 2);
    }

    /**
     * Preenche as matrizes que representam os planos dos elementos.
     */
    private void preencher() {
        for (int i = 0; i < ALTURA; i++) 
            for (int j = 0; j < LARGURA; j++){
                plano_estrelas[i][j] = ' ';
            }
    }

    /**
     * Encerra a execução do jogo. Essa função será chamada
     * quando o jogador "perder", por ter encostado em um meteoro.
     */
    private void finalizar(){
        timerAmbiente.stop();
        timerColisao.stop();
        JOptionPane.showMessageDialog(this, "Fim de jogo. Pontuação: " + pontuacao, "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
    }
}