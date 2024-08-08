<%@ page import="DAO.DaoUsersSQL" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 09/07/2024
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% if (session==null || session.getAttribute("usuario") == null){
  response.sendRedirect("index.jsp");

}%>
<head>
  <title>Gestor - Configuracion Cuenta</title>
  <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
  <link rel="stylesheet" href="styles/gestor.css">
</head>
<body>
<% DaoUsersSQL daoUsersSQL = new DaoUsersSQL();
  ArrayList<String> datosGestor =daoUsersSQL.getDatosUsuarioGestor(String.valueOf(session.getAttribute("usuario")));
%>
<h1> Configuración de la cuenta de Gestor</h1>

<header>
  <div> <a href="gestor.jsp">Lista de proyectos</a></div>
  <div> <a href="configuracionCuentaGestor.jsp">Configuración cuenta</a></div>
  <div> <a href="crearProyecto.jsp">Crear Proyecto</a></div>
</header>

<table class="listadoAdmin">
  <tr>
    <th>Nombre</th>
  </tr>
  <tr>
   <td><% out.print(datosGestor.get(1)); %></td>
  </tr>
  <tr>
    <td>
      <form action="CambiarNombreGestorServlet" method="post">
        <input type="text" name="newNombre">
        <input type="hidden" value="<% out.print(datosGestor.get(0)); %>" name="oldUserName">
        <button type="submit">Cambiar Nombre</button>
      </form>
    </td>
  </tr>
</table>
<table class="listadoAdmin">
  <tr>
    <th>Contraseña</th>
  </tr>
  <tr>
    <td>*********</td>
  </tr>
  <tr>
    <td>
      <form action="CambiarPasswordGestorServlet" method="post">
        <input type="password" name="newPassword">
        <input type="hidden" value="<% out.print(datosGestor.get(0)); %>" name="oldUserName">
        <button type="submit">Cambiar Contraseña</button>
      </form>
    </td>
  </tr>
</table>

<table class="listadoAdmin">
  <tr>
    <th>Email</th>
  </tr>
  <tr>
    <td><% out.print(datosGestor.get(2)); %></td>
  </tr>
  <tr>
    <td>
      <form action="CambiarEmailGestorServlet" method="post">
        <input type="email" name="newEmail">
        <input type="hidden" value="<% out.print(datosGestor.get(0)); %>" name="oldUserName">
        <button type="submit">Cambiar Email</button>
      </form>
    </td>
  </tr>
</table>
</body>
</html>
