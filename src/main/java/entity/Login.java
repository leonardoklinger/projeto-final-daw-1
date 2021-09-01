package entity;

public class Login {
    private String nome;
    private String senha;

    public Login() {
        this.nome = nome;
        this.senha = senha;
    }
    public String getNome(){
        return nome;
    }
    public String getSenha(){
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

