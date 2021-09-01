<%@ page import="java.io.PrintWriter" %>
<%@ page import="acao.RegistroLogin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro</title>
    <style><%@include file="estilologin.css"%></style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
    <%
        HttpSession session2 = request.getSession();
        String saida = String.valueOf(session2.getAttribute("usuarioExistente"));

        if(saida.equals("null") == false){
            PrintWriter out2 = response.getWriter();
            out2.write("<script type='text/javascript'>\n");
            out2.write("alert('Usuário -> "+ saida +" já existe !');\n");
            out2.write("</script>\n");
        }
    %>
</head>
<body>
<div class="container">
    <div class="wrapper">
        <div class="title"><span>Registro</span></div>
        <form action="registro" method="post">
            <div class="row">
                <i class="fas fa-signature"></i>
                <input type="text" name="input-nome" placeholder="Nome" maxlength="300" required>
            </div>
            <div class="row">
                <i class="fas fa-user"></i>
                <input type="text" name="input-usuario" placeholder="Usuário" maxlength="50" required>
            </div>
            <div class="row">
                <i class="fas fa-envelope"></i>
                <input type="email" name="input-email" placeholder="E-mail" maxlength="255" required>
            </div>
            <div class="row">
                <i class="fas fa-lock"></i>
                <input type="password" name="input-senha" placeholder="Senha" maxlength="50" required>
            </div>
            <div class="row button">
                <input type="submit" value="registre-se">
            </div>
        </form>
    </div>
</div>
</body>
</html>
