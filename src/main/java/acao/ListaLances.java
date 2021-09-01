package acao;

import dao.DataBase;
import dao.LancesDAO;
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

public class ListaLances extends HttpServlet {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public ListaLances(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException {
        List<Leilao> lances = new LancesDAO().getLeiloes();
        HttpSession session = req.getSession();

        if(session.getAttribute("roleUser").equals("admin")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Lances/lancesLista.jsp");
            req.setAttribute("lances", lances);
            dispatcher.forward(req, resp);
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Lances/lancesListaComum.jsp");
            req.setAttribute("lances", lances);
            dispatcher.forward(req, resp);
        }
    }
}
