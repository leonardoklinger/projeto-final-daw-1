package acao;

import regras.LeilaoRegras;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroLeilao {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public FiltroLeilao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws IOException, ServletException {
        String status = req.getParameter("StatusBox");
        LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);
        leilaoRegras.controleFiltro(status);
        resp.sendRedirect(req.getServletContext().getContextPath() + "/entrada?acao=listar");
    }
}
