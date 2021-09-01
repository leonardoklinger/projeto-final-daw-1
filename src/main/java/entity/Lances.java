package entity;

public class Lances {
    private String nome;
    private String leilaoNome;
    private Long valor;

    public Lances(String leilaoNome, String nome, Long valor) {;
        this.nome = nome;
        this.valor = valor;
        this.leilaoNome = leilaoNome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public String getLeilaoNome() {
        return leilaoNome;
    }

    public void setLeilaoNome(String leilaoNome) {
        this.leilaoNome = leilaoNome;
    }
}

