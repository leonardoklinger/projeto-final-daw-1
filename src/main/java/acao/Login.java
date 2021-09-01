package acao;

import dao.DataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Login {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public Login(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException{
        String user = req.getParameter("input-usuario");
        String senha = req.getParameter("input-senha");
        DataBase dataBase = new DataBase(req, resp);
        entity.Login login = new entity.Login();
        login.setNome(user);
        login.setSenha(senha);
        if(dataBase.Login(login)[0] == "OK"){
            if(dataBase.Login(login)[2].trim().equals("admin")) {
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", user);
                session.setAttribute("roleUser", dataBase.Login(login)[2].trim());
                req.getRequestDispatcher("/WEB-INF/Leilões/inicio.jsp").forward(req, resp);
                return;
            }else{
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", user);
                session.setAttribute("roleUser", dataBase.Login(login)[2].trim());
                req.getRequestDispatcher("/WEB-INF/Leilões/iniciousercomum.jsp").forward(req, resp);
                return;
            }
        }else {
            req.getRequestDispatcher("/WEB-INF/Login/login.jsp").forward(req, resp);
        }
    }
}
