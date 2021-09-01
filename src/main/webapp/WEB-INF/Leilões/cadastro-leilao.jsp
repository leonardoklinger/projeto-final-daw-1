<%@ page import="java.io.PrintWriter" %>
<%@ page import="acao.CadastrarLeilao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro Leilão</title>
    <style><%@include file="cadastra.css"%></style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
</head>
<body>
<nav id="menu-h">
    <a href="./leiloes">
        <img src = "${pageContext.request.contextPath}/images/logositeprof.png">
    </a>
    <ul>
        <li><a href="entrada?acao=inicio"><i class="fas fa-home"></i>  Inicio</a></li>
        <li><a href="entrada?acao=cadastra"><i class="fas fa-clipboard"></i> Cadastro</a></li>
        <li><a href="entrada?acao=listar"><i class="fas fa-box"></i> Leilões</a></li>
        <li><a href="entrada?acao=lances"><i class="fas fa-pencil-alt"></i> Lances</a></li>
        <li><a href="entrada?acao=logout"><i class="fas fa-door-open"></i> Logout</a></li>
    </ul>
</nav>
<h1>Cadastro</h1>
<h2></h2>
<form action="entrada?acao=cadastra" method="post" class="inputs">
    <input type="text" name="input-name" placeholder="Informe o nome" class="input-nome" required>
    <input type="number" name="input-valor" min="0" placeholder="Informe o valor minimo" class="input-numero" required>
    <input type="date" name="input-data" class="input-data" required>
    <input type="submit" value="Salvar" class="button">
</form>
</body>
</html>
