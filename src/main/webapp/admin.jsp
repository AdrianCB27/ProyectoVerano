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
</head>
<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>

</body>
</html>
