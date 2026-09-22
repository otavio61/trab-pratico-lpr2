package model;

import java.util.Random;

public class Estrela extends Desenho{

    int altura, largura;

    public Estrela(int altura, int largura) {
        super(altura, largura);

        this.altura = altura;
        this.largura = largura;
    }

    @Override
    public char[][] desenhar() {

        return plano;
    }
    
}
