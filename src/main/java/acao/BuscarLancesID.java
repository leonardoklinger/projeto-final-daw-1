package acao;

import dao.DataBase;
import dao.LancesDAO;
import entity.Lances;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscarLancesID {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public BuscarLancesID(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException {
        Long IDLance = Long.valueOf(req.getParameter("IDLance"));
        new LancesDAO().darLanceID(IDLance);
        HttpSession session = req.getSession();
        session.setAttribute("IDLanceee", IDLance);
        session.setAttribute("IDSalvoLance", req.getParameter("IDLance"));
        DataBase dataBase = new DataBase(req, resp);

        //List<Leilao> leiloes = new LeilaoDAO().getLeiloes();
        List<Lances> lances = new ArrayList<>();
        List<Lances> contatos = dataBase.BuscarLancesLeiloes();
        for (Lances contato : contatos) {
            lances.add(new Lances(contato.getLeilaoNome(), contato.getNome(), contato.getValor()));
        }
        if(session.getAttribute("roleUser").equals("admin")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Lances/lances.jsp");
            req.setAttribute("lancesID", lances);
            dispatcher.forward(req, resp);
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Lances/lancesComum.jsp");
            req.setAttribute("lancesID", lances);
            dispatcher.forward(req, resp);
        }
    }
}
