<%@ page import="DAO.DaoProyectosSQL" %>
<%@ page import="ClasesUtiles.Proyecto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 14/08/2024
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inversor - Lista De Proyectos</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/inversor.css">
</head>
<body>
<% if (session==null || session.getAttribute("usuario") == null){
  response.sendRedirect("index.jsp");
}%>
<h1> Estas son las listas de proyectos, <% out.print(session.getAttribute("usuario"));%>.</h1>

<header>
  <div> <a href="inversor.jsp">Ver mis inversiones</a></div>
  <div> <a href="configuracionCuentaInversor.jsp">Configuración cuenta</a></div>
  <div> <a href="listaDeProyectosInversor.jsp">Ver Proyectos</a></div>
  <div> <a href="carteraVirtualInversor.jsp">Cartera Virtual</a></div>
</header>
<h2>Proyectos actualmente existentes</h2>

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
    <th>Invertir</th>
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

      out.print("<form action='InvertirEnProyectoServlet' method=\"post\">\n" +
              "<input type='hidden' name='codigo' value='"+proyecto.getCodigo()+"'>"+
              "<input type='hidden' name='userName' value='"+session.getAttribute("usuario")+"'>"+

              "    <td><button type=\"submit\">Invertir</button></td>\n" +
              "</form>");
      out.print("</tr>");
    }%>
</table>
</body>
</html>
