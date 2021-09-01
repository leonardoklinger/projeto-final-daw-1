package acao;

import dao.DataBase;
import dao.LancesDAO;
import dao.LeilaoDAO;
import entity.Leilao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeletarLances {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public DeletarLances(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException {
        Long IDDelete = Long.valueOf(req.getParameter("IDDelete"));
        new LancesDAO().deletarLances(IDDelete);
        DataBase dataBase = new DataBase(req, resp);
        dataBase.BuscarLeilaoPorNomeID(IDDelete);

        List<Leilao> leiloes = new ArrayList<>();
        List<Leilao> contatos = dataBase.BuscarDadosTodosDadosLances();
        for (Leilao contato : contatos) {
            leiloes.add(new Leilao(contato.getId(), contato.getNome(), contato.getStatus(), contato.getValor(), contato.getDataExpiracao()));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Lances/lancesLista.jsp");
        req.setAttribute("lances", leiloes);
        dispatcher.forward(req, resp);
    }
}
