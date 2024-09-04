<%@ page import="DAO.DaoUsersSQL" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 14/08/2024
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inversor - Configuracion Cuenta</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/inversor.css">
</head>
<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>
<% DaoUsersSQL daoUsersSQL = new DaoUsersSQL();
    ArrayList<String> datosInversor =daoUsersSQL.getDatosUsuarioInversor(String.valueOf(session.getAttribute("usuario")));
%>
<h1> Configuración de la cuenta de Inversor</h1>

<header>
    <div> <a href="inversor.jsp">Ver mis inversiones</a></div>
    <div> <a href="configuracionCuentaInversor.jsp">Configuración cuenta</a></div>
    <div> <a href="listaDeProyectosInversor.jsp">Ver Proyectos</a></div>
    <div> <a href="carteraVirtualInversor.jsp">Cartera Virtual</a></div>
</header>
<table class="listadoAdmin">
    <tr>
        <th>Nombre</th>
    </tr>
    <tr>
        <td><% out.print(datosInversor.get(1)); %></td>
    </tr>
    <tr>
        <td>
            <form action="CambiarNombreInversorServlet" method="post">
                <input type="text" name="newNombre">
                <input type="hidden" value="<% out.print(datosInversor.get(0)); %>" name="oldUserName">
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
            <form action="CambiarPasswordInversorServlet" method="post">
                <input type="password" name="newPassword">
                <input type="hidden" value="<% out.print(datosInversor.get(0)); %>" name="oldUserName">
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
        <td><% out.print(datosInversor.get(2)); %></td>
    </tr>
    <tr>
        <td>
            <form action="CambiarEmailInversorServlet" method="post">
                <input type="email" name="newEmail">
                <input type="hidden" value="<% out.print(datosInversor.get(0)); %>" name="oldUserName">
                <button type="submit">Cambiar Email</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
