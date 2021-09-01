package acao;

import dao.DataBase;
import dao.LeilaoDAO;
import entity.Leilao;
import regras.LeilaoRegras;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


public class CadastrarLeilao {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public CadastrarLeilao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        req.getRequestDispatcher("/WEB-INF/Leilões/cadastro-leilao.jsp").forward(req, resp);
    }

    public void executa() throws IOException, ServletException {
        DataBase dataBase = new DataBase(req, resp);
        String campoNome = req.getParameter("input-name");
        Integer valorMinimo = Integer.valueOf(req.getParameter("input-valor"));
        LocalDate dataLeilao = LocalDate.parse(req.getParameter("input-data"));
        HttpSession session = req.getSession();
        session.setAttribute("leilaoCadastrado", campoNome);

        if(dataBase.BuscaTodosLeilaoCadastro().isEmpty()){
            LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);
            Leilao leilao = new Leilao(null, campoNome, null , valorMinimo, dataLeilao);
            leilaoRegras.cadastro(leilao);
            LeilaoDAO leilaoDAO = new LeilaoDAO(req, resp);
            leilaoDAO.cadastra(leilao);
            dataBase.cadastrarLeilao(leilao);
            req.getRequestDispatcher("/WEB-INF/Leilões/cadastro-leilao.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/Leilões/cadastro-leilao.jsp").forward(req, resp);
        }
    }
}
