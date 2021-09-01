<%@ page import="entity.Lances" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.DataBase" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="controller.Conexao" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lances</title>
    <style><%@include file="/WEB-INF/Lances/estiloLances.css"%></style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
    <%
        HttpSession session3 = request.getSession();
        String saida = String.valueOf(session3.getAttribute("lanceMensagem"));
        if(saida.equals("null") == false){
            PrintWriter out2 = response.getWriter();
            out2.write("<script type='text/javascript'>\n");
            out2.write("alert('"+ saida +"');\n");
            out2.write("</script>\n");
        }
    %>
    <script>
        setTimeout(function(){
             <%
                session3.removeAttribute("lanceMensagem");
             %>
        }, 1000);
    </script>
</head>
<body>
<nav id="menu-h">
    <a href="./leiloes">
        <img src = "${pageContext.request.contextPath}/images/logositeprof.png">
    </a>
    <ul>
        <li><a href="entrada?acao=inicio"><i class="fas fa-home"></i> Inicio</a></li>
        <li><a href="entrada?acao=cadastra"><i class="fas fa-clipboard"></i> Cadastro</a></li>
        <li><a href="entrada?acao=listar"><i class="fas fa-box"></i> Leilões</a></li>
        <li><a href="entrada?acao=lances"><i class="fas fa-pencil-alt"></i> Lances</a></li>
        <li><a href="entrada?acao=logout"><i class="fas fa-door-open"></i> Logout</a></li>
    </ul>
</nav>
<h1>Lances</h1>
<h2></h2>
<br>
<form action="entrada?acao=darLances" method="post" class="inputs">
    <input type="number" name="input-valorLance" placeholder="Informe o valor" class="inputs-valor">
    <input type="submit" value="Salvar" class="button">
</form>
<%
    Conexao conexao = new Conexao();
    ArrayList<Lances> result = new ArrayList<Lances>();
    String resultado = new String();
    HttpSession session2 = request.getSession();
    try {
        Connection con = conexao.getConexao();
        String sql = "select * from cadastroleilao where id = '"+ session2.getAttribute("IDSalvoLance") +"' ORDER BY id";
        PreparedStatement stmt = con.prepareStatement(sql);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            resultado = rs.getString("nome");
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
        String sql = "select * from lances where leilaonome = '" + resultado + "' ORDER BY valor DESC";
        PreparedStatement stmt = con.prepareStatement(sql);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            result.add(new Lances(null, rs.getString("nome"), rs.getLong("valor")));
        }
        stm.close();
        rs.close();
        stmt.execute();
        stmt.close();
        con.close();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }

    Iterator itr = result.iterator();
    while (itr.hasNext()) {
        Lances st = (Lances) itr.next();
%>
<div class="container">
    <img src = "${pageContext.request.contextPath}/images/userIconLance.png" alt="Avatar" style="width:100%;">
    <label class="Nome"><i class="fas fa-user"></i> <%=st.getNome() %> <label class="Valor"><i class="fas fa-hand-holding-usd"></i> R$ <%=st.getValor() %></label></label>
</div>
<%
    }
%>
</body>
</html>
