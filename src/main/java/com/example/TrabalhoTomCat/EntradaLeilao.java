package com.example.TrabalhoTomCat;

import acao.*;
import dao.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/entrada")
public class EntradaLeilao extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("usuarioLogado") == null){
            Login login = new Login(req, resp);
            login.executa();
            return;
        }

        String acao = req.getParameter("acao");
        switch (acao) {
            case "novo":
                if(session.getAttribute("roleUser").equals("admin")) {
                    NovoLeilao novoLeilao = new NovoLeilao(req, resp);
                    novoLeilao.executa();
                }else {
                    req.getRequestDispatcher("/WEB-INF/Leilões/iniciousercomum.jsp").forward(req, resp);
                }
                break;
            case "cadastra":
                if(session.getAttribute("roleUser").equals("admin")) {
                    CadastrarLeilao cadastrarLeilao = new CadastrarLeilao(req, resp);
                    cadastrarLeilao.executa();
                }else {
                    req.getRequestDispatcher("/WEB-INF/Leilões/iniciousercomum.jsp").forward(req, resp);
                }
                break;
            case "deleta":
                if(session.getAttribute("roleUser").equals("admin")) {
                    DeletarLeilao deletarLeilao = new DeletarLeilao(req, resp);
                    deletarLeilao.executa();
                }else {
                    req.getRequestDispatcher("/WEB-INF/Leilões/iniciousercomum.jsp").forward(req, resp);
                }
                break;
            case "deletarLance":
                DeletarLances deletarLances = new DeletarLances(req, resp);
                deletarLances.executa();
                break;
            case "listar":
                ListaLeiloes listaLeiloes = new ListaLeiloes(req, resp);
                listaLeiloes.executa();
                break;
            case "update":
                if(session.getAttribute("roleUser").equals("admin")) {
                    UpdateEditar update = new UpdateEditar(req, resp);
                    update.executa();
                }else {
                    req.getRequestDispatcher("/WEB-INF/Leilões/iniciousercomum.jsp").forward(req, resp);
                }
                break;
            case "efetuaUpdate":
                if(session.getAttribute("roleUser").equals("admin")) {
                    Update update2 = new Update(req, resp);
                    update2.executa();
                }else {
                    req.getRequestDispatcher("/WEB-INF/Leilões/iniciousercomum.jsp").forward(req, resp);
                }
                break;
            case "inicio":
                Inicio inicio = new Inicio(req, resp);
                inicio.executa();
                break;
            case "login":
                if(session.getAttribute("usuarioLogado") != null) {
                    if(session.getAttribute("roleUser").equals("admin")){
                        req.getRequestDispatcher("/WEB-INF/Leilões/inicio.jsp").forward(req, resp);
                    }else{
                        req.getRequestDispatcher("/WEB-INF/Leilões/iniciousercomum.jsp").forward(req, resp);
                    }
                    return;
                }else {
                    Login login = new Login(req, resp);
                    login.executa();
                }
                break;
            case "logout":
                Logout logout = new Logout(req, resp);
                logout.executa();
                break;
            case "lances":
                ListaLances listaLances = new ListaLances(req, resp);
                listaLances.executa();
                break;
            case "darLances":
                DarLances darLances = new DarLances(req, resp);
                darLances.executa();
                break;
            case "filtroLeilao":
                FiltroLeilao filtroLeilao = new FiltroLeilao(req, resp);
                filtroLeilao.executa();
                break;
            case "buscarLancesID":
                BuscarLancesID buscarLancesID = new BuscarLancesID(req, resp);
                buscarLancesID.executa();
                break;
            default:
                System.out.println("Não existe !");
        }
    }
}

