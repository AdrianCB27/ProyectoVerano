<%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 08/08/2024
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestor - Inicio</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/gestor.css"></head>
<style>
    .formulario {
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
        max-width: 400px;
        width: 100%;
        display: flex;
        flex-direction: column;
        margin-bottom: 20px;
    }

    .formulario label {
        margin: 10px 0 5px;
        font-weight: bold;
        color: #333333;
    }
    .formulario select {
        padding: 10px;
        border: 1px solid #cccccc;
        border-radius: 5px;
        margin-bottom: 20px;
        font-size: 1em;
    }

    .formulario input {
        padding: 10px;
        border: 1px solid #cccccc;
        border-radius: 5px;
        margin-bottom: 20px;
        font-size: 1em;
    }

    .botonEnviar {
        background-color: #ff0000;
        color: #ffffff;
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        font-size: 1em;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
</style>

<body>
<% if (session==null || session.getAttribute("usuario") == null){
    response.sendRedirect("index.jsp");
}%>
<h1> Panel de creación de proyectos</h1>

<header>
    <div> <a href="gestor.jsp">Lista de proyectos</a></div>
    <div> <a href="configuracionCuentaGestor.jsp">Configuración cuenta</a></div>
    <div> <a href="crearProyecto.jsp">Crear Proyecto</a></div>
</header>
<h2>Creación de un nuevo proyecto</h2>
<form class="formulario" action="CrearProyectoServlet" method="post">
    <label for="nombre">Nombre</label>
    <input id="nombre"  type="text" name="nombre" required>
    <label for="descripcion">Descripción</label>
    <input id="descripcion" type="text" name="descripcion" required>
    <label for="tipo">Tipo</label>
    <select id="tipo" name="tipo" required>
        <option value="Alquiler">Alquiler</option>
        <option value="Plusvalia">Plusvalia</option>
        <option value="Préstamo">Préstamo</option>
    </select>
    <label for="fechaInicio">Fecha de inicio</label>
    <input id="fechaInicio" type="text" name="fechaInicio" required>
    <label for="fechaFin">Fecha fin</label>
    <input id="fechaFin" type="text" name="fechaFin" required>
    <label for="cantidadNecesaria">Cantidad necesaria</label>
    <input id="cantidadNecesaria" type="text" name="cantidadNecesaria" required>
    <button type="submit" class="botonEnviar">Crear Proyecto</button>
</form>
</body>
</html>
