package dao;
import controller.Conexao;
import entity.Lances;
import entity.Leilao;
import entity.Login;
import regras.LeilaoRegras;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DataBase {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public DataBase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
    }
    LeilaoRegras leilaoRegras = new LeilaoRegras(req, resp);

    Conexao conexao = new Conexao();
    public void cadastrarLeilao(Leilao leilao) {
        try {
            Connection con = conexao.getConexao();
            String sql = "INSERT INTO cadastroleilao (nome, status, valor, data) values (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, leilao.getNome());
            stmt.setString(2, leilao.getStatus());
            stmt.setInt(3, Integer.valueOf(leilao.getValor()));
            stmt.setDate(4, Date.valueOf(leilao.getDataExpiracao()));
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeletarLeilao(Long IDDelete) {
        try {
            Connection con = conexao.getConexao();
            String sql = "delete from cadastroleilao where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, IDDelete);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Leilao> BuscarDadosTodosDadosLances(){
        List<Leilao> contatos = new ArrayList<Leilao>();
        try {
            Connection con = conexao.getConexao();
            String sql = "select * from cadastroleilao where status = 'ABERTO' ORDER BY id";
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contatos;
    }

    public List<Lances> BuscarLancesLeiloes(){
        List<Lances> lances = new ArrayList<Lances>();
        try {
            Connection con = conexao.getConexao();
            String sql = "select * from lances ORDER BY id";
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lances.add(new Lances(rs.getString("leilaonome"), rs.getString("nome"), rs.getLong("valor")));
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lances;
    }

    public String BuscaTodosLeilaoCadastro(){
        String leiloes = new String();
        HttpSession session = req.getSession();
        try {
            String sql = "select * from cadastroleilao where nome = '"+ session.getAttribute("leilaoCadastrado") +"' ORDER BY id";
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                leiloes = rs.getString("nome");
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return leiloes;
    }


    public void CadastrarLanceLeilao(String leilaoNome, String nome, Long valor) {
        try {
            Connection con = conexao.getConexao();
            String sql = "INSERT INTO lances (leilaonome, nome, valor) values (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, leilaoNome);
            stmt.setString(2, nome);
            stmt.setFloat(3, valor);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Update(String id, String campoNome, String status, Integer valorMinimo, LocalDate dataLeilao){
        try {
            Connection con = conexao.getConexao();
            String sql = "update cadastroleilao set nome = '"+ campoNome +"', status = '"+ status.toUpperCase() +"', valor = '"+ valorMinimo +"', data = '"+ dataLeilao +"' where id = '" + id + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void UpdataDataExpiracao(String expirado){
        try {
            Connection con = conexao.getConexao();
            String sql = "update cadastroleilao set status = 'EXPIRADO' where nome = '" + expirado + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String[] Login(Login login){
        String[] meuArray = new String[0];
        meuArray = new String[3];
        try {
            Connection con = conexao.getConexao();
            String sql = "select * from login where usuario = '"+ login.getNome() +"' and senha = '" + login.getSenha() + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                meuArray [0] = "OK";
                meuArray [1] = login.getNome();
                meuArray [2] = rs.getString("tipo");
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meuArray;
    }

    public String[] BuscarLogin(Login login){
        String[] meuArray = new String[0];
        meuArray = new String[3];
        try {
            Connection con = conexao.getConexao();
            String sql = "select * from login where usuario = '"+ login.getNome() +"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                meuArray [0] = rs.getString("usuario");
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meuArray;
    }

    public void CadastraLogin(String nome, String user, String senha, String email) {
        try {
            Connection con = conexao.getConexao();
            String sql = "INSERT INTO login (nome, usuario, senha, email, tipo) values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, user);
            stmt.setString(3, senha);
            stmt.setString(4, email);
            stmt.setString(5, "comum");
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void BuscarLeilaoPorNomeID(Long ID) throws ServletException, IOException {
        DataBase dataBase = new DataBase(req, resp);
        try {
            String sql = "select * from cadastroleilao where id = '"+ ID +"' ORDER BY id";
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                dataBase.DeletarLance(rs.getString("nome"));
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeletarLance(String nome) {
        try {
            Connection con = conexao.getConexao();
            String sql = "delete from lances where leilaonome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Leilao> BuscaTodosLeilaoData(){
        List<Leilao> leiloes = new ArrayList<>();
        try {
            Calendar c = Calendar.getInstance();
            java.util.Date data = c.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String sql = "select * from cadastroleilao where data = '" + sdf.format(data) +"'";
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                leiloes.add(new Leilao(null, rs.getString("nome"), rs.getString("status"), null, LocalDate.parse(rs.getString("data"))));
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return leiloes;
    }

    static String sql = new String();
    public List<Leilao> buscarTodos(){
        List<Leilao> leiloes = new ArrayList<>();

        if(leilaoRegras.controleStatus().toUpperCase().isEmpty()){
            sql = "select * from cadastroleilao ORDER BY id";
        }else if(leilaoRegras.controleStatus().toUpperCase().equals("TODOS")){
            sql = "select * from cadastroleilao ORDER BY id";
        }else{
            sql = "select * from cadastroleilao where status = '"+ leilaoRegras.controleStatus().toUpperCase() +"' ORDER BY id";
        }
        try {
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                leiloes.add(new Leilao(rs.getLong("id"), rs.getString("nome"), rs.getString("status"), Integer.parseInt(rs.getString("valor")), LocalDate.parse(rs.getString("data"))));
            }
            stm.close();
            rs.close();
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return leiloes;
    }

    public List<Leilao> BuscarDadosTodosDados(){
        List<Leilao> contatos = new ArrayList<Leilao>();
        try {
            Connection con = conexao.getConexao();
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contatos;
    }
}