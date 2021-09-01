package acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NovoLeilao {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public NovoLeilao(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException{
        req.getRequestDispatcher("/WEB-INF/Leilões/cadastro-leilao.jsp").forward(req, resp);
    }
}
