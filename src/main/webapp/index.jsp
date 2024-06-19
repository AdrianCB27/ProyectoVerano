<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles/index.css">
</head>
<body>
<header><h1>Inmobiliaria Veraniega</h1></header>
<h2 class="titulo">Bienvenido a nuestro portal de inicio de sesión</h2>
<form class="formulario" action="login" method="post">
    <label for="user">Usuario</label>
    <input id="user" type="text" name="user" required>
    <label for="pass">Contraseña</label>
    <input id="pass" type="password" name="pass" required>
    <button type="submit" class="botonEnviar">Iniciar sesión</button>

</form>
<p class="noCuenta">¿No tienes cuenta? <a href="registro.jsp">Registrarse</a></p>


</body>
</html>