package acao;

import dao.DataBase;
import dao.LeilaoDAO;
import entity.Leilao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListaLeiloes extends HttpServlet {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public ListaLeiloes(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException {
        List<Leilao> leiloes = new LeilaoDAO(req, resp).getLeiloes();
        HttpSession session = req.getSession();
        if(session.getAttribute("roleUser").equals("admin")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Leilões/leiloes.jsp");
            req.setAttribute("leiloes", leiloes);
            dispatcher.forward(req, resp);
        }else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Leilões/leilaoUserComum.jsp");
            req.setAttribute("leiloes", leiloes);
            dispatcher.forward(req, resp);
        }
    }
}
