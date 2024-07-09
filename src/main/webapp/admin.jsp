<%@ page import="java.util.ArrayList" %>
<%@ page import="ClasesUtiles.Inversor" %>
<%@ page import="ClasesUtiles.Gestor" %>
<%@ page import="DAO.DaoUsersSQL" %><%--
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
    <link rel="stylesheet" href="styles/admin.css">
</head>
<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>
<h1> Bienvenido al panel de Administrador, <% out.print(session.getAttribute("usuario"));%>.</h1>

<header>
    <div>Bloquear o desbloquear usuarios</div>
    <div>Configuración cuenta</div>
    <div>Lista de proyectos</div>
    <div>Modificar usuarios</div>
</header>
<h2>Gestores</h2>
<div class="listadoGestores">
    <table>
        <tr>
            <th>Nombre de usuario</th>
            <th>Estado Cuenta</th>
            <th>Bloquear/Desbloquear</th>
        </tr>
        <% DaoUsersSQL usersSQL=new DaoUsersSQL();
            ArrayList<Gestor> gestores = usersSQL.getAllUsersGestores();
            for (Gestor gestor: gestores) {
                out.print("<tr><td>"+gestor.getUserName()+"</td>");
                if (gestor.isBloqueado()) out.print("<td>Bloqueado</td>");
                else out.print("<td> Desbloqueado </td>");
                out.print("<form action='ModificarUsuarioServlet' method=\"post\">\n" +
                        "<input type='hidden' name='user' value='"+gestor.getUserName()+"'>"+
                        "    <td><button type=\"submit\">Bloquear/Desbloquear</button></td>\n" +
                        "</form>");
                out.print("</tr>");
            }%>
    </table>
</div>

<h2>Inversores</h2>
<div class="listadoInversores">
    <table>
        <tr>
            <th>Nombre de usuario</th>
            <th>Estado Cuenta</th>
            <th>Bloquear/Desbloquear</th>
        </tr>
        <% ArrayList<Inversor> inversores = usersSQL.getAllUsersInversores();
            for (Inversor inversor : inversores) {
                out.print("<tr><td>"+inversor.getUserName()+"</td>");
                if (inversor.isBloqueado()) out.print("<td>Bloqueado</td>");
                else out.print("<td> Desbloqueado </td>");
                out.print("<form action='ModificarUsuarioServlet' method=\"post\">\n" +
                        "<input type='hidden' name='user' value='"+inversor.getUserName()+"'>"+
                        "    <td><button type=\"submit\">Bloquear/Desbloquear</button></td>\n" +
                        "</form>");
                out.print("</tr>");
            }%>

    </table>
</div>

</body>
</html>
<!-- TODO modificar el propio usuario, hacer los proyectos, (CRUD), modificar otros usuarios (convertirlos en gestores o inversores)

