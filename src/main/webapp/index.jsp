<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Bienvenido a nuestro portal de inicio de sesión</h1>
<form class="formulario" action="verificar" method="post">
    <label for="user">Usuario</label>
    <input id="user" type="text" name="user" required>
    <label for="pass">Contraseña</label>
    <input id="pass" type="password" name="pass" required>
    <button type="submit" class="botonEnviar">Iniciar sesión</button>

</form>
<p>¿No tienes cuenta? <a href="registro.jsp">Registrarse</a></p>


</body>
</html>