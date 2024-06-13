<%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 13/06/2024
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro</title>
</head>
<body>
<h1>Bienvenido a nuestro portal de inicio de sesión</h1>
<form class="formulario" action="registrar" method="post">
    <label for="nombre">Nombre Completo</label>
    <input id="nombre" type="text" name="nombre" required>
    <label for="user">Usuario</label>
    <input id="user" type="text" name="user" required>
    <label for="correo">Correo</label>
    <input id="correo" type="email" name="correo">
    <label for="pass">Contraseña</label>
    <input id="pass" type="password" name="pass" required>
    <button type="submit" class="botonEnviar">Iniciar sesión</button>
</form>
</body>
</html>
