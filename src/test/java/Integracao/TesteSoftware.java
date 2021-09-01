package Integracao;

import dao.DataBase;
import dao.LeilaoDAO;
import entity.Lances;
import entity.Leilao;
import org.junit.Test;
import regras.LeilaoRegras;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class TesteSoftware {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    @Test
    public void filtraLeilaoPorStatusTodos() throws ServletException, IOException {
        DataBase dataBase = new DataBase(req, resp);
        LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);
        leilaoRegras.controleFiltro("EXPIRADO");
        leilaoRegras.controleStatus();
        dataBase.buscarTodos();
        LeilaoDAO leilaoDAO = new LeilaoDAO(req, resp);
        System.out.println(leilaoDAO.getLeiloes().size());
    }

    @Test
    public void cadastroInativo() throws ServletException, IOException {
        DataBase dataBase = new DataBase(req, resp);
        LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);
        Leilao leilao = new Leilao(null, "PS6", null , 1000, LocalDate.parse("2021-09-01"));
        leilaoRegras.cadastro(leilao);
        dataBase.cadastrarLeilao(leilao);
        System.out.println(leilao.getNome() + " Foi cadastro !");
    }

    @Test
    public void DataExpiracao() throws ServletException, IOException {
        DataBase dataBase = new DataBase(req, resp);
        LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);
        Leilao leilao = new Leilao(null, "Slateste", "INATIVO" , 500, LocalDate.parse("2021-09-01"));
        leilaoRegras.DataExpiracao(leilao);
        dataBase.cadastrarLeilao(leilao);
    }

    @Test
    public void cadastrarLance() throws ServletException, IOException {
        DataBase dataBase = new DataBase(req, resp);
        Lances lances = new Lances("PS6", "Leonardo", 500L);
        dataBase.CadastrarLanceLeilao(lances.getLeilaoNome(), lances.getNome(), lances.getValor());
    }
}
