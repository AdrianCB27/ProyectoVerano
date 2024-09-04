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
<head>
    <title>Administrador - Configuracion Cuenta</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/admin.css">
</head>
<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>
<% DaoUsersSQL daoUsersSQL = new DaoUsersSQL();
    ArrayList<String> datosAdmin=daoUsersSQL.getDatosUsuario();
%>
<h1> Configuración de la cuenta de Administrador</h1>

<header>
    <div><a href="admin.jsp">Bloquear o desbloquear usuarios</a></div>
    <div><a href="configuracionCuentaAdmin.jsp">Configuración cuenta</a></div>
    <div><a href="listaDeProyectosAdmin.jsp">Lista de proyectos</a></div>
    <div><a href="modificarUsuarios.jsp">Modificar usuarios</a></div>
</header>
<table class="listadoAdmin">
    <tr>
        <th>Nombre Usuario</th>
    </tr>
    <tr>
        <td><% out.print(datosAdmin.get(0)); %></td>
    </tr>
    <tr>
        <td>
            <form action="cambiarUserNameServlet" method="post">
                <input type="text" name="newUserName">
                <input type="hidden" value="<% out.print(datosAdmin.get(0)); %>" name="oldUserName">
                <button type="submit">Cambiar Nombre de Usuario</button>
            </form>
        </td>
    </tr>
</table>

<table class="listadoAdmin">
    <tr>
        <th>Nombre</th>
    </tr>
    <tr>
        <td><% out.print(datosAdmin.get(1)); %></td>
    </tr>
    <tr>
        <td>
            <form action="cambiarNombreServlet" method="post">
                <input type="text" name="newNombre">
                <input type="hidden" value="<% out.print(datosAdmin.get(0)); %>" name="oldUserName">
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
            <form action="cambiarPasswordServlet" method="post">
                <input type="password" name="newPassword">
                <input type="hidden" value="<% out.print(datosAdmin.get(0)); %>" name="oldUserName">
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
        <td><% out.print(datosAdmin.get(2)); %></td>
    </tr>
    <tr>
        <td>
            <form action="cambiarEmailServlet" method="post">
                <input type="email" name="newEmail">
                <input type="hidden" value="<% out.print(datosAdmin.get(0)); %>" name="oldUserName">
                <button type="submit">Cambiar Email</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
