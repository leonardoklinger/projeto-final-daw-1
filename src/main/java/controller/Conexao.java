package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public Connection getConexao() {
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "rushfan2112";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, user, senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
