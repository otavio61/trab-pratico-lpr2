package model;

public class Nave {
    
    private int coordenadaX, coordenadaY;
    private char[][] plano;

    public Nave(int coordenadaX, int coordenadaY, int altura, int largura) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.plano = new char[altura][largura];
        desenharNave();
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public char[][] getPlano(){
        return plano;
    }

    /**
     * Desenha a nave na interface.
     * Crédito da arte: <a href='https://www.asciiart.eu/art/9b7a16c065fdb471'>Christian Jensen (também como C.J. ou CJ)</a>
     */
    private void desenharNave(){
        preencher();

        plano[coordenadaY][coordenadaX] = '.';

        plano[coordenadaY + 1][coordenadaX - 1] = '.';
        plano[coordenadaY + 1][coordenadaX] = '\'';
        plano[coordenadaY + 1][coordenadaX + 1] = '.';

        plano[coordenadaY + 2][coordenadaX - 1] = '|';
        plano[coordenadaY + 2][coordenadaX] = 'o';
        plano[coordenadaY + 2][coordenadaX + 1] = '|';

        plano[coordenadaY + 3][coordenadaX - 2] = '.';
        plano[coordenadaY + 3][coordenadaX - 1] = '\'';
        plano[coordenadaY + 3][coordenadaX] = 'o';
        plano[coordenadaY + 3][coordenadaX + 1] = '\'';
        plano[coordenadaY + 3][coordenadaX + 2] = '.';

        plano[coordenadaY + 4][coordenadaX - 2] = '|';
        plano[coordenadaY + 4][coordenadaX - 1] = '.';
        plano[coordenadaY + 4][coordenadaX] = '-';
        plano[coordenadaY + 4][coordenadaX + 1] = '.';
        plano[coordenadaY + 4][coordenadaX + 2] = '|';

        plano[coordenadaY + 5][coordenadaX - 2] = '\'';
        plano[coordenadaY + 5][coordenadaX + 2] = '\'';
    }

    /**
     * Preenche as matriz que representa o plano dos elementos com ' ' ({@code \U+0020}).
     */
    private void preencher(){
        for (int i = 0; i < plano.length; i++)
            for (int j = 0; j < plano[0].length; j++)
                plano[i][j] = ' ';
    }
}
