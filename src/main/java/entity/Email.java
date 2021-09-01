package entity;

public class Email {
    private String nomeLeilao;
    private String nomeUser;
    private String emailUser;
    private String status;

    public Email(String status, String nomeLeilao, String nomeUser, String emailUser) {
        this.status = status;
        this.nomeLeilao = nomeLeilao;
        this.nomeUser = nomeUser;
        this.emailUser = emailUser;
    }

    public String getNomeLeilao() {
        return nomeLeilao;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getStatus() {
        return status;
    }
}
