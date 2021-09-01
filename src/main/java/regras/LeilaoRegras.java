package regras;

import dao.DataBase;
import dao.LeilaoDAO;
import entity.Leilao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class LeilaoRegras {

    private HttpServletRequest req;
    private HttpServletResponse resp;

    public LeilaoRegras(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    static String status = new String();

    public void controleFiltro(String filtro){
        status = filtro;
    }

    public String controleStatus(){
        return status;
    }

    public void cadastro(Leilao leilao) throws ServletException, IOException {
        leilao.setStatus("INATIVO");
    }

    public void DataExpiracao(Leilao leilao) throws ServletException, IOException {
        DataBase dataBase = new DataBase(req, resp);
        LocalDate dataverificar = LocalDate.now();
        if(String.valueOf(leilao.getDataExpiracao()).equals(String.valueOf(dataverificar))) {
            leilao.setStatus("EXPIRADO");
            dataBase.UpdataDataExpiracao(leilao.getNome());
        }
    }

    public void statusExpirado(Leilao leilao){
        if(leilao.getStatus().equals("EXPIRADO")){
            leilao.setStatus("FINALIZADO");
        }
    }

    public void statusExpiradoAberto(Leilao leilao){
        if(leilao.getStatus().equals("EXPIRADO") || leilao.getStatus().equals("ABERTO")){
            leilao.setStatus("FINALIZADO");
        }
    }

    public static boolean Email(String status, String nomeLeilao, String nomedoUsuario, String emailUser){
        if(status.equals("FINALIZADO")){
                    String email = "leonardoklingerdeveloper@gmail.com";
        String senha = "fiofwumvqnvpraiz";
        String mensagem = "<body style=\"font-family:Arial; padding-left: center; text-align: center; color: white\"> <div style=\"background: #63c7f6; width: 450px; height: 460px; margin: 10px auto; border-radius: 20px;\"> <img src=\"https://i.imgur.com/i67UthN.png\" style=\"margin: 8px auto;\"> <b><p style=\" font-size: 30px;\">Olá, "+ nomedoUsuario +"</p></b> <p style=\" font-size: 14px;\">Informamos que você acaba de arrematar um ótimo produto.</p>  <b><p style=\" font-size:24px;\">"+ nomeLeilao  +"</p></b> <p style=\" font-size: 14px;\">Nós do Leilão ficamos muito felizes pela sua aquisição.</p> <img src=\"https://i.imgur.com/MusaR2m.png\"> <footer style=\" font-size: 10px; color: red;\">Atenção, não responder este e-mail!</footer> </div> </body>";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, senha);
                    }
                });
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            Address[] toUser = InternetAddress.parse(emailUser);
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Ganhador do leilão");//titulo
            message.setContent(mensagem, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
            return true;
        }else{
            return false;
        }
    }

}
