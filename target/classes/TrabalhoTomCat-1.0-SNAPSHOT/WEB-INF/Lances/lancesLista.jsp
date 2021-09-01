<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Menu Inicial</title>
    <style><%@include file="/WEB-INF/Leilões/estilo.css"%></style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
</head>
<body>
<p><a href="${pageContext.request.contextPath}/entrada?acao=novo"></a></p>
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
<h1>Lances</h1>
<h2></h2>
<table id="minhaTabela">
    <thead>
    <tr id="tr-corpo">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Status</th>
        <th>Valor</th>
        <th>Data expiração</th>
        <th>Ação</th>
    </tr>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${lances}" var="l">
        <tr>
            <td>#${l.id}</td>
            <td>${l.nome}</td>
            <td id="statustd">${l.status}</td>
            <td id="valortd">R$ ${l.valor}</td>
            <td>${l.dataExpiracao}</td>
            <td><a href="entrada?acao=buscarLancesID&IDLance=${l.id}" id="editar">Lance <img src = "${pageContext.request.contextPath}/images/editlogo.png"></a> <a href="entrada?acao=deletarLance&IDDelete=${l.id}" id="excluir">Excluir <img src = "${pageContext.request.contextPath}/images/logoexcluir.png"></a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
