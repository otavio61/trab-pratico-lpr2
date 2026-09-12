import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import javax.swing.JPanel;

public class PainelNavinha extends JPanel implements KeyListener {
    // ALTURA -> y LARGURA -> x
    private final int ALTURA = 25;
    private final int LARGURA = 80;
    private final int naveY = 15;
    private final int naveX = 36;
    private char[][] tela;

    private boolean esquerda = false;
    private boolean direita = false;

    public PainelNavinha() {
        setBackground(Color.BLACK);

        addKeyListener(this);

        Timer timer = new Timer(100, e -> {render();});
        timer.start();
    }

    private void render(){



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

        tela = new char[ALTURA][LARGURA];
        preencher();

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.setColor(Color.WHITE);

        drawShip();

        for (int i = 0; i < tela.length; i++) {
            String linha = new String(tela[i]);

            g.drawString(linha, 0, (i + 1) * 20); // o uso de (i + 1) * 20 é para simular divisão de caracteres por linha
        }
    }

    private void drawShip() {
        draw('.', naveY, naveX);

        draw('.', naveY + 1, naveX - 1);
        draw('\'', naveY + 1, naveX);
        draw('.', naveY + 1, naveX + 1);

        draw('|', naveY + 2, naveX - 1);
        draw('o', naveY + 2, naveX);
        draw('|', naveY + 2, naveX + 1);

        draw('.', naveY + 3, naveX - 2);
        draw('\'', naveY + 3, naveX - 1);
        draw('o', naveY + 3, naveX);
        draw('\'', naveY + 3, naveX + 1);
        draw('.', naveY + 3, naveX + 2);

        draw('|', naveY + 4, naveX - 2);
        draw('.', naveY + 4, naveX - 1);
        draw('-', naveY + 4, naveX);
        draw('.', naveY + 4, naveX + 1);
        draw('|', naveY + 4, naveX + 2);

        draw('\'', naveY + 5, naveX - 2);
        draw('\'', naveY + 5, naveX + 2);
    }

    private void preencher() {
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                tela[i][j] = ' ';
            }
        }
    }

    private void draw(char c, int y, int x) {
        tela[y][x] = c;
    }


}
