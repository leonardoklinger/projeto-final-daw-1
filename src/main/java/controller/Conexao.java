package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public Connection getConexao() {
        String driver = "org.postgresql.Driver";
        String user = "bfcawrqdxzszny";
        String senha = "ac932df7169dc873683c57a72bdcfe1a79bb4f4e92a78e5258700dd1c7203aab";
        String url = "jdbc:postgresql://ec2-34-205-230-1.compute-1.amazonaws.com:5432/d1bvbui62jahec";
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
