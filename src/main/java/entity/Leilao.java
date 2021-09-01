package entity;

import java.time.LocalDate;

public class Leilao {
    private Long id;
    private Integer valor;
    private String nome;
    private LocalDate dataExpiracao;
    private String status;

        public Leilao(Long id, String nome, String status, Integer valor, LocalDate dataExpiracao) {
        this.nome = nome;
        this.dataExpiracao = dataExpiracao;
        this.valor = valor;
        this.status = status;
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Integer getValor() {
        return valor;
    }
    public String getNome() {
        return nome;
    }
    public String getStatus(){
        return status;
    }
    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
