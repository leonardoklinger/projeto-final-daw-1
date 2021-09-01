package acao;

import dao.DataBase;
import entity.Leilao;
import regras.LeilaoRegras;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class VerificarData {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public VerificarData(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }
    public void executa() throws InterruptedException, ServletException, IOException {
        DataBase dataBase = new DataBase(req, resp);
        LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                List<Leilao> contatos = dataBase.BuscaTodosLeilaoData();
                for (Leilao contato : contatos) {
                    try {
                        leilaoRegras.DataExpiracao(contato);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        },0,5000);
    }
}
