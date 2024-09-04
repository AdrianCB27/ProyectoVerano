<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.DaoProyectosSQL" %>
<%@ page import="ClasesUtiles.Proyecto" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 06/08/2024
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor - Lista De Proyectos </title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/gestor.css">
</head>

<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>
<% int codigo = Integer.parseInt(request.getParameter("codigo"));
    DaoProyectosSQL daoProyectosSQL = new DaoProyectosSQL();
    Proyecto datosProyecto = daoProyectosSQL.getDatosProyecto(codigo);%>
<h1> Aquí puedes modificar el proyecto, <% out.print(session.getAttribute("usuario"));%>.</h1>
<header>
    <div> <a href="gestor.jsp">Lista de proyectos</a></div>
    <div> <a href="configuracionCuentaGestor.jsp">Configuración cuenta</a></div>
    <div> <a href="crearProyecto.jsp">Crear Proyecto</a></div>
</header>

<table class="listadoProyectos">
    <tr>
        <th>Nombre Proyecto</th>
    </tr>
    <tr>
        <td><% out.print(datosProyecto.getNombre()); %></td>
    </tr>
    <tr>
        <td>
            <form action="ModificarNombreProyectoGestorServlet" method="post">
                <input type="text" name="nuevoNombreProyecto">
                <input type="hidden" value="<% out.print(datosProyecto.getCodigo()); %>" name="codigo">
                <button type="submit">Cambiar nombre</button>
            </form>
        </td>
    </tr>
</table>
<table class="listadoProyectos">
    <tr>
        <th>Descripcion Proyecto</th>
    </tr>
    <tr>
        <td><% out.print(datosProyecto.getDescripcion()); %></td>
    </tr>
    <tr>
        <td>
            <form action="ModificarDescripcionProyectoGestorServlet" method="post">
                <input type="text" name="nuevaDescripcionProyecto">
                <input type="hidden" value="<% out.print(datosProyecto.getCodigo()); %>" name="codigo">
                <button type="submit">Cambiar Descripcion</button>
            </form>
        </td>
    </tr>
</table>
<table class="listadoProyectos">
    <tr>
        <th>Tipo Proyecto</th>
    </tr>
    <tr>
        <td><% out.print(datosProyecto.getTipo()); %></td>
    </tr>
    <tr>
        <td>
            <form action="ModificarTipoProyectoGestorServlet" method="post">
                <input type="text" name="nuevoTipoProyecto">
                <input type="hidden" value="<% out.print(datosProyecto.getCodigo()); %>" name="codigo">
                <button type="submit">Cambiar tipo</button>
            </form>
        </td>
    </tr>
</table>
<table class="listadoProyectos">
    <tr>
        <th>Fecha Inicio</th>
    </tr>
    <tr>
        <td><% out.print(datosProyecto.getFechaInicio()); %></td>
    </tr>
    <tr>
        <td>
            <form action="ModificarFechaInicioProyectoGestorServlet" method="post">
                <input type="text" name="fechaInicio">
                <input type="hidden" value="<% out.print(datosProyecto.getCodigo()); %>" name="codigo">
                <button type="submit">Cambiar Fecha Inicio</button>
            </form>
        </td>
    </tr>
</table>
<table class="listadoProyectos">
    <tr>
        <th>Fecha Fin</th>
    </tr>
    <tr>
        <td><% out.print(datosProyecto.getFechaFin()); %></td>
    </tr>
    <tr>
        <td>
            <form action="ModificarFechaFinProyectoGestorServlet" method="post">
                <input type="text" name="fechaFin">
                <input type="hidden" value="<% out.print(datosProyecto.getCodigo()); %>" name="codigo">
                <button type="submit">Cambiar Fecha Fin</button>
            </form>
        </td>
    </tr>
</table>
<table class="listadoProyectos">
    <tr>
        <th>Cantidad Necesaria</th>
    </tr>
    <tr>
        <td><% out.print(datosProyecto.getCantidadNecesaria()); %></td>
    </tr>
    <tr>
        <td>
            <form action="ModificarCantidadNecesariaProyectoGestorServlet" method="post">
                <input type="text" name="cantidadNecesaria">
                <input type="hidden" value="<% out.print(datosProyecto.getCodigo()); %>" name="codigo">
                <button type="submit">Cambiar Cantidad Necesaria</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
