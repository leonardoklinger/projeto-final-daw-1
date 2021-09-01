package Unitario;

import dao.DataBase;
import dao.LeilaoDAO;
import entity.Email;
import entity.Lances;
import entity.Leilao;
import org.junit.Test;
import org.mockito.Mockito;
import regras.LanceRegras;
import regras.LeilaoRegras;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TesteSoftware {
    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);

    private DataBase dataBase = Mockito.mock(DataBase.class);
    private LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);
    private LanceRegras lanceRegras = new LanceRegras(req, resp);

    @Test
    public void filtraLeilaoPorStatusTodos() throws ServletException, IOException {
        leilaoRegras.controleFiltro("ABERTO");
        leilaoRegras.controleStatus();
        DataBase dataBase2 = new DataBase(req, resp);
        dataBase2.buscarTodos();
        LeilaoDAO leilaoDAO = new LeilaoDAO(req, resp);
        System.out.println(leilaoDAO.getLeiloes().size());
        assertEquals(leilaoDAO.getLeiloes().size(), leilaoDAO.getLeiloes().size());
    }

    @Test
    public void CadastraLeilao(){
        Leilao leilao = new Leilao(1L, "Leo", "INATIVO" , 500, LocalDate.parse("2021-08-30"));
        dataBase.cadastrarLeilao(leilao);
        assertEquals(leilao, leilao);
    }

    @Test
    public void VerificarCadastroLeilao() throws ServletException, IOException {
        Leilao leilao = new Leilao(1L, "Leo", "ABERTO" , 500, LocalDate.parse("2021-08-30"));
        dataBase.cadastrarLeilao(leilao);
        leilaoRegras.cadastro(leilao);
        assertEquals("INATIVO", leilao.getStatus());
    }

    @Test
    public void DataExpiracao() throws ServletException, IOException {
        Leilao leilao = new Leilao(1L, "Leo", "INATIVO" , 500, LocalDate.parse("2021-08-31"));
        dataBase.cadastrarLeilao(leilao);
        leilaoRegras.DataExpiracao(leilao);
        assertEquals("EXPIRADO", leilao.getStatus());
    }

    @Test
    public void statusExpirado(){
        Leilao leilao = new Leilao(1L, "Leo", "EXPIRADO" , 500, LocalDate.parse("2021-08-31"));
        leilaoRegras.statusExpirado(leilao);
        assertEquals("FINALIZADO", leilao.getStatus());
    }

    @Test
    public void statusExpiradoAberto(){
        Leilao leilao = new Leilao(1L, "Leo", "ABERTO" , 500, LocalDate.parse("2021-08-31"));
        leilaoRegras.statusExpiradoAberto(leilao);
        assertEquals("FINALIZADO", leilao.getStatus());
    }

    @Test
    public void valorMenorQueDoLeilao(){
        Leilao leilao = new Leilao(1L, "PS5", "ABERTO" , 500, LocalDate.parse("2021-08-31"));
        Lances lances = new Lances("PS5", "Leo", 500L);
        Boolean resultado = lanceRegras.valorMenorQueDoLeilao(lances.getValor(), Long.valueOf(leilao.getValor()));
        assertEquals(true, resultado);
    }

    @Test
    public void lancesRepetidos(){
        Leilao leilao = new Leilao(1L, "PS5", "ABERTO" , 500, LocalDate.parse("2021-08-31"));
        Lances lances = new Lances("PS5", "Leo", 500L);
        Lances lances2 = new Lances("PS5", "Leo", 500L);
        Boolean resultado = lanceRegras.lancesRepetidos(lances.getLeilaoNome(), leilao.getNome(), lances.getNome(), lances2.getNome());
        assertEquals(true, resultado);
    }

    @Test
    public void valorMenorOuIgualQueOUltimo(){
        Lances lances = new Lances("PS5", "Leo", 500L);
        Lances lances2 = new Lances("PS5", "Leo", 600L);
        Boolean resultado = lanceRegras.valorMenorQueOUltimo(lances.getValor(), lances2.getValor());
        assertEquals(true, resultado);
    }

    @Test
    public void Email(){
        Email email = new Email("FINALIZADO", "PS5", "Leo", "leonardoklinger16@gmail.com");
        Boolean resultado = leilaoRegras.Email(email.getStatus(), email.getNomeLeilao(), email.getNomeUser(), email.getEmailUser());
        assertEquals(true, resultado);
    }


    @Test
    public void CadastraLance(){
        Lances lances = new Lances("PS5", "Leo", 500L);
        dataBase.CadastrarLanceLeilao(lances.getLeilaoNome(), lances.getNome(), lances.getValor());
        assertEquals(lances, lances);
    }

}

