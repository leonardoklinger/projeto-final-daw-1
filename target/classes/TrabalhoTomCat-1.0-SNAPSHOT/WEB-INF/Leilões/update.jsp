<%@ page import="dao.DataBase" %>
<%@ page import="entity.Leilao" %>
<%@ page import="java.util.List" %>
<%@ page import="acao.UpdateEditar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <style><%@include file="update.css"%></style>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
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
<h1>Editando</h1>
<h2></h2>
<form action="entrada?acao=efetuaUpdate&IDUpdate=${IDUpdate}" method="post" class="inputs">
    <% UpdateEditar update=new UpdateEditar(request, response); List<Leilao> contatos = update.BuscarDadosPorID();
        for (Leilao contato : contatos) {
    %>
    <input type="text" name="input-name" value="<%=contato.getNome() %>" class="input-nome" required>
    <input type="number" name="input-valor" min="0" value="<%=contato.getValor() %>" class="input-numero"
           required>
    <input type="date" name="input-data" value="<%=contato.getDataExpiracao() %>" class="input-data" required>
    <select name="status" class="combobox">
        <option value="Inativo">INATIVO</option>
        <option value="Aberto">ABERTO</option>
        <option value="Finalizado">FINALIZADO</option>
        <option value="Expirado">EXPIRADO</option>
    </select>
    <input type="submit" value="Salvar" class="button">
    <%
        }
    %>
</form>
</body>
</html>