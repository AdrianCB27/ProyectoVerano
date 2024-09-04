<%@ page import="java.util.ArrayList" %>
<%@ page import="ClasesUtiles.Inversor.Inversion" %>
<%@ page import="DAO.DaoProyectosSQL" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 25/06/2024
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inversor - Inicio</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/inversor.css">
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
}
    String nombreUsuario = session.getAttribute("usuario").toString();
%>

<h1> Bienvenido al panel de Inversor, <% out.print(session.getAttribute("usuario"));%>.</h1>

<header>
    <div> <a href="inversor.jsp">Ver mis inversiones</a></div>
    <div> <a href="configuracionCuentaInversor.jsp">Configuración cuenta</a></div>
    <div> <a href="listaDeProyectosInversor.jsp">Ver Proyectos</a></div>
    <div> <a href="carteraVirtualInversor.jsp">Cartera Virtual</a></div>
</header>

<table>
    <tr>
        <th>Nombre del proyecto</th>
        <th>Cantidad participada</th>
        <th>Fecha Inicio</th>
        <th>Última actualización</th>
    </tr>
    <%DaoProyectosSQL daoProyectosSQL = new DaoProyectosSQL();
        ArrayList<Inversion> inversiones=daoProyectosSQL.getInversionesByUserName(nombreUsuario);
        for (Inversion inversion : inversiones ) {
            out.print("<tr><td>" + inversion.getNombreProyecto() + "</td>");
            out.print("<td>" + inversion.getCantidadParticipada() + "</td>");
            out.print("<td>" + inversion.getFechaInicio() + "</td>");
            out.print("<td>" + inversion.getUltimaActualizacion() + "</td>");
            out.print("</tr>");
        }%>
</table>

<form id="botonCerrarSesion" action="LogoutServlet" method="post">
    <input type="submit" value="Cerrar sesión">
</form>
</body>
</html>
