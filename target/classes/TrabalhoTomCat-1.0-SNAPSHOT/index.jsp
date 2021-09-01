<%@ page import="java.util.Timer" %>
<%@ page import="java.util.TimerTask" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="dao.DataBase" %>
<%@ page import="entity.Leilao" %>
<%@ page import="java.util.List" %>
<%@ page import="acao.VerificarData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio</title>
    <style><%@include file="estiloinicio.css"%></style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
</head>
<body>
<nav id="menu-h">
    <a href="./leiloes">
        <img src = "${pageContext.request.contextPath}/images/logositeprof.png">
    </a>
    <ul>
        <li><a href="index.jsp"><i class="fas fa-home"></i>  Inicio</a></li>
        <li><a href="entrada?acao=listar"><i class="fas fa-box"></i> Leilões</a></li>
        <li><a href="entrada?acao=lances"><i class="fas fa-pencil-alt"></i> Lances</a></li>
        <li><a href="./registro"><i class="far fa-address-card"></i> Registrar</a></li>
        <li><a href="entrada?acao=login"><i class="fas fa-sign-in-alt"></i> Entrar</a></li>
    </ul>
</nav>
<div class="img-wrapper">
    <img src="images/leilaobackground.png" alt="">
</div>
</body>
</html>
<%
    VerificarData verificarData = new VerificarData(request, response);
    verificarData.executa();
%>
