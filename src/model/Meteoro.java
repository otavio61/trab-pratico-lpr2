package model;

import java.util.Random;

public class Meteoro extends Desenho{

    private int largura;

    public Meteoro(int altura, int largura) {
        super(altura, largura);

        this.largura = largura;

        preencher();
    }

    @Override
    public char[][] desenhar() {
        int quantidadeMeteoros = 0;
        int coordX, coordY;
        Random r = new Random();

        for (int i = plano.length - 1; i >= 0; i--) {
            for (int j = plano[0].length - 1; j >= 0; j--) {
                if(plano[i][j] == 'O'){
                    if (i + 1 > 24) 
                        plano[i][j] = ' ';
                    else {
                        plano[i + 1][j] = 'O';
                        plano[i][j] = ' ';
                        quantidadeMeteoros++;
                    } 
                }
            }   
        }

        while (quantidadeMeteoros < 5) {
            coordX = r.nextInt(2, largura);
            coordY = r.nextInt(1, 5);
            
            if(plano[coordY][coordX] == ' '){
                plano[coordY][coordX] = 'O';
                quantidadeMeteoros++;
            }
        }

        return plano;
    }
}
