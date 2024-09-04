<%@ page import="DAO.DaoProyectosSQL" %>
<%@ page import="ClasesUtiles.Proyecto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 25/06/2024
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor - Inicio</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/gestor.css">
    <style>
        #botonCerrarSesion {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background-color: black;
            border: none;
            padding: 0;
        }

        #botonCerrarSesion input[type="submit"] {
            background-color: black;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
        }

        #botonCerrarSesion input[type="submit"]:hover {
            background-color: #333;
        }

    </style>
</head>
<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>
<h1> Bienvenido al panel de Gestor, <% out.print(session.getAttribute("usuario"));%>.</h1>

<header>
    <div> <a href="gestor.jsp">Lista de proyectos</a></div>
    <div> <a href="configuracionCuentaGestor.jsp">Configuración cuenta</a></div>
    <div> <a href="crearProyecto.jsp">Crear Proyecto</a></div>

</header>
<table>
    <tr>
        <th>Codigo</th>
        <th>Nombre</th>
        <th>Descripcion</th>
        <th>Tipo</th>
        <th>Fecha Inicio</th>
        <th>Fecha Fin</th>
        <th>Cantidad Financiada</th>
        <th>Cantidad Necesaria</th>
        <th>Modificar</th>
        <th>Eliminar</th>
    </tr>
    <% DaoProyectosSQL proyectosSQL = new DaoProyectosSQL();
        ArrayList<Proyecto> proyectos =proyectosSQL.getAllProyects();
        for (Proyecto proyecto : proyectos) {
            out.print("<tr><td>" + proyecto.getCodigo() + "</td>");
            out.print("<td>" + proyecto.getNombre() + "</td>");
            out.print("<td>" + proyecto.getDescripcion() + "</td>");
            out.print("<td>" + proyecto.getTipo() + "</td>");
            out.print("<td>" + proyecto.getFechaInicio() + "</td>");
            out.print("<td>" + proyecto.getFechaFin() + "</td>");
            out.print("<td>" + proyecto.getCantidadFinanciada() + "</td>");
            out.print("<td>" + proyecto.getCantidadNecesaria() + "</td>");
            out.print("<form action='modificarProyectoGestor.jsp' method=\"post\">\n" +
                    "<input type='hidden' name='codigo' value='"+proyecto.getCodigo()+"'>"+
                    "    <td><button type=\"submit\">Modificar</button></td>\n" +
                    "</form>");
            out.print("<form action='EliminarProyectoGestorServlet' method=\"post\">\n" +
                    "<input type='hidden' name='codigo' value='"+proyecto.getCodigo()+"'>"+
                    "    <td><button type=\"submit\">Eliminar</button></td>\n" +
                    "</form>");
            out.print("</tr>");
        }%>
</table>
<form id="botonCerrarSesion" action="LogoutServlet" method="post">
    <input type="submit" value="Cerrar sesión">
</form>
</body>
</html>
