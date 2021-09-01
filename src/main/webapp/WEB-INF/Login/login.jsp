<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style><%@include file="estilologin.css"%></style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

</head>
<body>
<div class="container">
    <div class="wrapper">
        <div class="title"><span>Login</span></div>
        <form action="entrada?acao=Login" method="post">
            <div class="row">
                <i class="fas fa-user"></i>
                <input type="text" name="input-usuario" placeholder="Usuário" required>
            </div>
            <div class="row">
                <i class="fas fa-lock"></i>
                <input type="password" name="input-senha" placeholder="Senha" required>
            </div>
            <div class="row button">
                <input type="submit" value="Login">
            </div>
            <div class="signup-link">Não é membro ? <a href="./registro">Registre agora</a></div>
        </form>
    </div>
</div>

</body>
</html>
