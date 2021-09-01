package acao;

import dao.DataBase;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class Update {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws IOException, ServletException {
        DataBase dataBase = new DataBase(req, resp);
        String ID = req.getParameter("IDUpdate");
        String campoNome = req.getParameter("input-name");
        String status = req.getParameter("status");
        Integer valorMinimo = Integer.valueOf(req.getParameter("input-valor"));
        LocalDate dataLeilao = LocalDate.parse(req.getParameter("input-data"));
        EnviarEmail enviarEmail = new EnviarEmail(req, resp);
        enviarEmail.executa(status, campoNome);
        dataBase.Update(ID, campoNome, status, valorMinimo, dataLeilao);
        resp.sendRedirect(req.getServletContext().getContextPath() + "/entrada?acao=listar");
    }
}
