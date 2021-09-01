package dao;
import controller.Conexao;
import entity.Leilao;
import regras.LeilaoRegras;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeilaoDAO {
    String sql;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    List<Leilao> leiloes = new ArrayList<>();
        public LeilaoDAO(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.req = req;
            this.resp = resp;
            DataBase dataBase = new DataBase(req, resp);
            List<Leilao> contatos = dataBase.buscarTodos();
            for (Leilao contato : contatos) {
                leiloes.add(new Leilao(contato.getId(), contato.getNome(), contato.getStatus(), contato.getValor(), contato.getDataExpiracao()));
            }
        }

    public List<Leilao> getLeiloes(){
        return leiloes;
    }
    public void deleta(Long IDDelete){
       Optional<Leilao> leilao = leiloes.stream().filter(l -> l.getId().equals(IDDelete)).findAny();
       Leilao leilaoExistente = leilao.get();
       leiloes.remove(leilaoExistente);
    }

    public void cadastra(Leilao leilao){
        leiloes.add(leilao);
    }

}
