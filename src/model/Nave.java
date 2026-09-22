package model;

public class Nave extends Desenho{
    
    private int coordenadaNaveX, coordenadaNaveY;
    private boolean esquerda, direita;

    public Nave(int altura, int largura, int coordenadaNaveX, int coordenadaNaveY) {
        super(altura, largura);
        
        this.coordenadaNaveX = coordenadaNaveX;
        this.coordenadaNaveY = coordenadaNaveY;
        this.esquerda = false;
        this.direita = false;
    }

    public boolean isEsquerda() {
        return esquerda;
    }

    public void setEsquerda(boolean esquerda) {
        this.esquerda = esquerda;
    }

    public boolean isDireita() {
        return direita;
    }

    public void setDireita(boolean direita) {
        this.direita = direita;
    }

    public int getCoordenadaNaveX() {
        return coordenadaNaveX;
    }

    public void setCoordenadaNaveX(int coordenadaNaveX) {
        this.coordenadaNaveX = coordenadaNaveX;
    }

    public int getCoordenadaNaveY() {
        return coordenadaNaveY;
    }

    public void setCoordenadaNaveY(int coordenadaNaveY) {
        this.coordenadaNaveY = coordenadaNaveY;
    }

    @Override 
    public char[][] desenhar(){
        preencher();

        plano[coordenadaNaveY][coordenadaNaveX] = '.';

        plano[coordenadaNaveY + 1][coordenadaNaveX - 1] = '.';
        plano[coordenadaNaveY + 1][coordenadaNaveX] = '\'';
        plano[coordenadaNaveY + 1][coordenadaNaveX + 1] = '.';

        plano[coordenadaNaveY + 2][coordenadaNaveX - 1] = '|';
        plano[coordenadaNaveY + 2][coordenadaNaveX] = 'o';
        plano[coordenadaNaveY + 2][coordenadaNaveX + 1] = '|';

        plano[coordenadaNaveY + 3][coordenadaNaveX - 2] = '.';
        plano[coordenadaNaveY + 3][coordenadaNaveX - 1] = '\'';
        plano[coordenadaNaveY + 3][coordenadaNaveX] = 'o';
        plano[coordenadaNaveY + 3][coordenadaNaveX + 1] = '\'';
        plano[coordenadaNaveY + 3][coordenadaNaveX + 2] = '.';

        plano[coordenadaNaveY + 4][coordenadaNaveX - 2] = '|';
        plano[coordenadaNaveY + 4][coordenadaNaveX - 1] = '.';
        plano[coordenadaNaveY + 4][coordenadaNaveX] = '-';
        plano[coordenadaNaveY + 4][coordenadaNaveX + 1] = '.';
        plano[coordenadaNaveY + 4][coordenadaNaveX + 2] = '|';

        plano[coordenadaNaveY + 5][coordenadaNaveX - 2] = '\'';
        plano[coordenadaNaveY + 5][coordenadaNaveX + 2] = '\'';

        return plano;
    }
}
