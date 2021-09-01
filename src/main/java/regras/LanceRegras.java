package regras;

import entity.Lances;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanceRegras {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public LanceRegras(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public static boolean valorMenorQueDoLeilao(Long valorUser, Long valorLeilao){
        return valorUser >= valorLeilao;
    }

    public static boolean lancesRepetidos(String ultimoLance, String leilaonome, String valorUser, String nomeUser){
        return ultimoLance.equals(leilaonome) && valorUser.equals(nomeUser);
    }

    public static boolean valorMenorQueOUltimo(Long valor, Long ultimoValor){
        return valor >= ultimoValor;
    }
}
