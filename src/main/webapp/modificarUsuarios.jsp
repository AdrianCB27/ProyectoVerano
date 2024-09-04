<%@ page import="DAO.DaoUsersSQL" %>
<%@ page import="ClasesUtiles.Gestor.Gestor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ClasesUtiles.Inversor.Inversor" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 09/07/2024
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrador - Modificar Usuarios</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/admin.css">

</head>
<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>
<h1> Aqui puedes modificar usuarios, <% out.print(session.getAttribute("usuario"));%>. </h1>

<header>
    <div> <a href="admin.jsp">Bloquear o desbloquear usuarios</a></div>
    <div> <a href="configuracionCuentaAdmin.jsp">Configuración cuenta</a></div>
    <div> <a href="listaDeProyectosAdmin.jsp">Lista de proyectos</a></div>
    <div> <a href="modificarUsuarios.jsp">Modificar usuarios</a></div>
</header>
<h2>Gestores</h2>
<div class="listadoGestores">
    <table>
        <tr>
            <th>Nombre de usuario</th>
            <th>Cambiar a Inversor</th>
        </tr>
        <% DaoUsersSQL usersSQL=new DaoUsersSQL();
            ArrayList<Gestor> gestores = usersSQL.getAllUsersGestores();
            for (Gestor gestor: gestores) {
                out.print("<tr><td>"+gestor.getUserName()+"</td>");
                out.print("<form action='cambiarGestorOInversor' method=\"post\">\n" +
                        "<input type='hidden' name='user' value='"+gestor.getUserName()+"'>"+
                        "    <td><button type=\"submit\">Convertir a Inversor</button></td>\n" +
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
            <th>Cambiar a Gestor</th>
        </tr>
        <% ArrayList<Inversor> inversores = usersSQL.getAllUsersInversores();
            for (Inversor inversor : inversores) {
                out.print("<tr><td>"+inversor.getUserName()+"</td>");
                out.print("<form action='cambiarGestorOInversor' method=\"post\">\n" +
                        "<input type='hidden' name='user' value='"+inversor.getUserName()+"'>"+
                        "    <td><button type=\"submit\">Convertir a Gestor</button></td>\n" +
                        "</form>");
                out.print("</tr>");
            }%>

    </table>
</div>
</body>
</html>
