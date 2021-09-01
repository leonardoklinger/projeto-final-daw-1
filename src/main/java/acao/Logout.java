package acao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public Logout(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException{
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

