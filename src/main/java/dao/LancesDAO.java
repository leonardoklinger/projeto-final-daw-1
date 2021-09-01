package dao;

import entity.Leilao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LancesDAO {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public LancesDAO(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
    }
    List<Leilao> leiloes = new ArrayList<>();
    public LancesDAO() throws ServletException, IOException {
        DataBase dataBase = new DataBase(req, resp);
        List<Leilao> contatos = dataBase.BuscarDadosTodosDadosLances();
        for (Leilao contato : contatos) {
            leiloes.add(new Leilao(contato.getId(), contato.getNome(), contato.getStatus(), contato.getValor(), contato.getDataExpiracao()));
        }
    }

    public List<Leilao> getLeiloes(){
        return leiloes;
    }

    public void darLanceID(Long IDLance){
        Optional<Leilao> leilao = leiloes.stream().filter(l -> l.getId().equals(IDLance)).findAny();
        Leilao leilaoExistente = leilao.get();
    }

    public void deletarLances(Long IDDelete){
        Optional<Leilao> leilao = leiloes.stream().filter(l -> l.getId().equals(IDDelete)).findAny();
        Leilao leilaoExistente = leilao.get();
        leiloes.remove(leilaoExistente);
    }

}
