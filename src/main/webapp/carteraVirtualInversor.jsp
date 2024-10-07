<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.DaoUsersSQL" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 14/08/2024
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Inversor - Cartera Virtual</title>
    <link rel="icon" href="imagenes/Favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/inversor.css">
    <style>
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-size: 1.5em;
            color: dodgerblue;
            margin-bottom: 10px;
        }

        input[type='number'] {
            padding: 10px;
            font-size: 1em;
            border: 2px solid dodgerblue;
            border-radius: 5px;
            margin-bottom: 20px;
            outline: none;
            transition: border-color 0.3s;
        }

        input[type='number']:focus {
            border-color: lightblue;
        }

        input[type='submit'] {
            background-color: dodgerblue;
            color: white;
            padding: 10px;
            font-size: 1em;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type='submit']:hover {
            background-color: lightblue;
        }
    </style>
</head>
<body>
<% if (session == null || session.getAttribute("usuario") == null) {
    response.sendRedirect("index.jsp");
}
    DaoUsersSQL daoUsersSQL = new DaoUsersSQL();
    String userName= String.valueOf(session.getAttribute("usuario"));

    String saldoInversor = daoUsersSQL.getSaldoInversor(String.valueOf(session.getAttribute("usuario")));%>
<h1> Esta es tu cartera virtual </h1>

<header>
    <div><a href="inversor.jsp">Ver mis inversiones</a></div>
    <div><a href="configuracionCuentaInversor.jsp">Configuración cuenta</a></div>
    <div><a href="listaDeProyectosInversor.jsp">Ver Proyectos</a></div>
    <div><a href="carteraVirtualInversor.jsp">Cartera Virtual</a></div>
</header>
<h1 class="mostrarSaldo" id="miEtiqueta"></h1>

<script>
    let numero =<% out.print(saldoInversor); %> ;
    let numeroLimitado = numero.toFixed(2);
    document.getElementById("miEtiqueta").innerText = "Tu saldo es de: "+ numeroLimitado;
</script>

<% out.print("<div class='container'><form action='AddSaldoServlet' method='post'>\n" +
        "    <label>Añadir saldo a mi cuenta</label>\n" +
        "    <input type='hidden' value='"+userName+"' name='oldUserName'>\n" +
        " <input type='number' name='saldoAdded' step='0.01'>\n" +
        " <input type='submit' value='Añadir'>\n" +
"</form></div>");%>

</body>
</html>