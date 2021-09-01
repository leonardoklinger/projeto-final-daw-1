package acao;

import dao.DataBase;
import dao.LeilaoDAO;
import entity.Leilao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeletarLeilao {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public DeletarLeilao(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException {
        Long IDDelete = Long.valueOf(req.getParameter("IDDelete"));
        new LeilaoDAO(req, resp).deleta(IDDelete);
        DataBase dataBase = new DataBase(req, resp);
        dataBase.BuscarLeilaoPorNomeID(IDDelete);
        dataBase.DeletarLeilao(IDDelete);


        List<Leilao> leiloes = new ArrayList<>();
        List<Leilao> contatos = dataBase.BuscarDadosTodosDados();
        for (Leilao contato : contatos) {
            leiloes.add(new Leilao(contato.getId(), contato.getNome(), contato.getStatus(), contato.getValor(), contato.getDataExpiracao()));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Leilões/leiloes.jsp");
        req.setAttribute("leiloes", leiloes);
        dispatcher.forward(req, resp);
    }
}
