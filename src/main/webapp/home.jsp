<%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 18/06/2024
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">

</head>
<body>
<h1>Hola, <% out.print(session.getAttribute("usuario"));%></h1>
</body>
</html>
