package acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Inicio {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public Inicio(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("roleUser").equals("admin")) {
            req.getRequestDispatcher("/WEB-INF/Leilões/inicio.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/Leilões/iniciousercomum.jsp").forward(req, resp);
        }
    }
}

