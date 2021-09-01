package acao;


import controller.Conexao;
import regras.LeilaoRegras;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EnviarEmail {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public EnviarEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
    }
    LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);
    public void executa(String status, String campoNome) throws ServletException, IOException {
        Conexao conexao = new Conexao();
        List<String> valorMaior = new ArrayList<>();
        List<String> nomeUser = new ArrayList<>();
        String nomeLeilao = new String();
        String nomedoUsuario = new String();
        String emailUser = new String();
        try {
            Connection con = conexao.getConexao();
            String sql = "select * from lances where leilaonome = '"+ campoNome +"' ORDER BY valor";
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                valorMaior.add(String.valueOf(rs.getLong("valor")));
                nomeLeilao = rs.getString("leilaonome");
                nomeUser.add(rs.getString("nome"));
                System.out.println(rs.getLong("valor") + " " + rs.getString("leilaonome") + " " + rs.getString("nome"));
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(nomeUser.isEmpty()){
            return;
        }else{
            try {
                Connection con = conexao.getConexao();
                String sql = "select * from login where usuario = '"+ nomeUser.get(nomeUser.size() - 1) +"'";
                PreparedStatement stmt = con.prepareStatement(sql);
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    nomedoUsuario = rs.getString("nome");
                    emailUser = rs.getString("email");
                }
                stm.close();
                rs.close();
                stmt.execute();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                Connection con = conexao.getConexao();
                String sql = "delete from lances where leilaonome = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, nomeLeilao);
                stmt.execute();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            leilaoRegras.Email(status.toUpperCase(), nomeLeilao, nomedoUsuario, emailUser);
        }
    }


}
