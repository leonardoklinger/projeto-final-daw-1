package acao;

import controller.Conexao;
import dao.DataBase;
import entity.Lances;
import regras.LanceRegras;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DarLances {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public DarLances(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException{
        String valor = req.getParameter("input-valorLance");
        LanceRegras lanceRegras = new LanceRegras(req, resp);
        if(valor.isEmpty()){
            return;
        }
        HttpSession session3 = req.getSession();
        if(session3.getAttribute("usuarioLogado") == null && valor == null){
        }else{
            DataBase dataBase = new DataBase(req, resp);
            Conexao conexao = new Conexao();
            String nomeLeilaoo = new String();
            String valorLeilao = new String();
            HttpSession session = req.getSession();
            try {
                Connection con = conexao.getConexao();
                String sql = "select * from cadastroleilao where id = '"+ session3.getAttribute("IDLanceee") +"' ORDER BY id";
                PreparedStatement stmt = con.prepareStatement(sql);
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    nomeLeilaoo = rs.getString("nome");
                    valorLeilao = String.valueOf(rs.getLong("valor"));
                }
                stm.close();
                rs.close();
                stmt.execute();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ArrayList<Lances> result = new ArrayList<Lances>();
            List<String> ultimoLance = new ArrayList<>();
            List<String> usuario = new ArrayList<>();
            List<Lances> contatos = dataBase.BuscarLancesLeiloes();
            for (Lances contato : contatos) {
                result.add(new Lances(contato.getLeilaoNome(), contato.getNome(), contato.getValor()));
                ultimoLance.add(contato.getLeilaoNome());
                usuario.add(contato.getNome());
            }
            Lances lances = new Lances(nomeLeilaoo, String.valueOf(session3.getAttribute("usuarioLogado")), Long.parseLong(valor));
            Iterator itr = result.iterator();
            while (itr.hasNext()) {
                Lances st = (Lances) itr.next();
            }

            List<String> ultimoValor = new ArrayList<>();
            String vazio = new String();
            try {
                Connection con = conexao.getConexao();
                String sql = "select * from lances where leilaonome = '"+ nomeLeilaoo +"' ORDER BY id";
                PreparedStatement stmt = con.prepareStatement(sql);
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    ultimoValor.add(String.valueOf(rs.getLong("valor")));
                    vazio = String.valueOf(rs.getLong("valor"));
                }
                stm.close();
                rs.close();
                stmt.execute();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if(lanceRegras.valorMenorQueDoLeilao(Long.parseLong(valor), Long.parseLong(valorLeilao)) == false){
                session.setAttribute("lanceMensagem", "Valor incompartível");
                resp.sendRedirect(req.getServletContext().getContextPath() + "/entrada?acao=buscarLancesID&IDLance="+session3.getAttribute("IDSalvoLance"));
                return;
            }else if(ultimoLance.size() == 0){
                dataBase.CadastrarLanceLeilao(lances.getLeilaoNome(), lances.getNome(), lances.getValor());
                resp.sendRedirect(req.getServletContext().getContextPath() + "/entrada?acao=buscarLancesID&IDLance="+session3.getAttribute("IDSalvoLance"));
            }else if(vazio.isEmpty() == true){
                dataBase.CadastrarLanceLeilao(lances.getLeilaoNome(), lances.getNome(), lances.getValor());
                resp.sendRedirect(req.getServletContext().getContextPath() + "/entrada?acao=buscarLancesID&IDLance="+session3.getAttribute("IDSalvoLance"));
                return;
            }else if(lanceRegras.lancesRepetidos(ultimoLance.get(ultimoLance.size() - 1), lances.getLeilaoNome(), usuario.get(usuario.size() - 1), lances.getNome()) == true){
                session.setAttribute("lanceMensagem", "Você não pode dar 2 lances seguidos !");
                resp.sendRedirect(req.getServletContext().getContextPath() + "/entrada?acao=buscarLancesID&IDLance="+session3.getAttribute("IDSalvoLance"));
            }else{
                if(Long.parseLong(valor) > Long.parseLong(ultimoValor.get(ultimoValor.size() - 1)) == false){
                    System.out.println(Long.parseLong(valor));
                    System.out.println(ultimoValor.get(ultimoValor.size() - 1));
                    session.setAttribute("lanceMensagem", "Valor menor que do último lance !");
                    resp.sendRedirect(req.getServletContext().getContextPath() + "/entrada?acao=buscarLancesID&IDLance="+session3.getAttribute("IDSalvoLance"));
                    return;
                }else{
                    dataBase.CadastrarLanceLeilao(lances.getLeilaoNome(), lances.getNome(), lances.getValor());
                    resp.sendRedirect(req.getServletContext().getContextPath() + "/entrada?acao=buscarLancesID&IDLance="+session3.getAttribute("IDSalvoLance"));
                    return;
                }
            }
        }
    }
}
