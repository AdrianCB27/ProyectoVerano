<%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 25/06/2024
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrador - Inicio</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
</head>
<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>
<h1> Bienvenido al panel de Administrador, <% out.print(session.getAttribute("usuario"));%>.</h1>
<!--/TODO bloquear y desbloquear usuarios, modificar su userName, contraseña, etc, ver, modificar o eliminar proyectos


</body>
</html>
