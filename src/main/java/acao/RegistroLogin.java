package acao;

import dao.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registro", value = "/registro")
public class RegistroLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Login/registro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nome = req.getParameter("input-nome");
        String user = req.getParameter("input-usuario");
        String senha = req.getParameter("input-senha");
        String email = req.getParameter("input-email");
        DataBase dataBase = new DataBase(req, resp);
        entity.Login login = new entity.Login();
        login.setNome(user);

        if(dataBase.BuscarLogin(login)[0] == null){
            dataBase.CadastraLogin(nome, user, senha, email);
            req.getRequestDispatcher("/WEB-INF/Login/login.jsp").forward(req, resp);
        }else{
            session.setAttribute("usuarioExistente", user);
            req.getRequestDispatcher("/WEB-INF/Login/registro.jsp").forward(req, resp);
        }

    }
}