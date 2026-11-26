package model;
public class Jogo {
    private int pontuacao;
    private String nome;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Jogo(int pontuacao, String nome) {
        this.pontuacao = pontuacao;
        this.nome = nome;
    }
}
