package acao;


import controller.Conexao;
import entity.Leilao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpdateEditar {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public UpdateEditar(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public void executa() throws ServletException, IOException{
        String ID = req.getParameter("IDUpdate");
        req.setAttribute("IDUpdate" , ID);
        req.getRequestDispatcher("/WEB-INF/Leilões/update.jsp").forward(req, resp);
    }

    public List<Leilao> BuscarDadosPorID(){
        Conexao conexao = new Conexao();
        //UpdateEditar updateEditar = new UpdateEditar(req, resp);
        String teste = req.getParameter("IDUpdate");
        List<Leilao> contatos = new ArrayList<Leilao>();
        try {
            Connection con = conexao.getConexao();
            String sql = "select * from cadastroleilao where id = '" + teste +"' ";
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Leilao contato = new Leilao(rs.getLong("id"), rs.getString("nome"), rs.getString("status"), Integer.parseInt(rs.getString("valor")), LocalDate.parse(rs.getString("data")));
                contatos.add(contato);
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
        return contatos;
    }
}
