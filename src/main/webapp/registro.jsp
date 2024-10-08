<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <link rel="stylesheet" href="styles/registro.css">
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
</head>
<body>
<br>
<header>
    <div class="tituloPrincipal">
        <h1><img src="imagenes/Logo.png" width="100px">Inmobiliaria Veraniega</h1>
    </div>
</header>
<h2 class="titulo">Bienvenido a nuestro portal de registro</h2>
<form class="formulario" action="registrar" method="post">
    <label for="nombre">Nombre Completo</label>
    <input id="nombre" type="text" name="nombre" required>
    <label for="user">Usuario</label>
    <input id="user" type="text" name="user" required>
    <label for="correo">Correo</label>
    <input id="correo" type="email" name="correo">
    <label for="pass">Contraseña</label>
    <input id="pass" type="password" name="pass" required>
    <button type="submit" class="botonEnviar">Registrarse</button>
</form>
</body>
</html>
