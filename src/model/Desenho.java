package model;

/**
 * Classe abstrata criada para ser responsável pela base dos 
 * desenhos para serem colocadas no componente {@code JPanel}.
 */
public abstract class Desenho{

    protected char[][] plano;

    public Desenho(int altura, int largura){
        plano = new char[altura][largura];
    }

    /**
     * Método criado para inserir os caracteres no plano.
     * Para gerar um desenho, utilize este método e
     * o método {@code preencher()}
     * 
     * @see Desenho#preencher()
     */
    public abstract char[][] desenhar();

    /**
     * Preenche as matriz que representa o plano dos elementos com ' ' ({@code '\U+0020}'').
     * Isso garante que não exista problemas de espaços vazios ({@code '\u0000'} ou {@code '\0'})
     */
    protected void preencher(){
        for (int i = 0; i < plano.length; i++)
            for (int j = 0; j < plano[0].length; j++)
                plano[i][j] = ' ';
    }
}