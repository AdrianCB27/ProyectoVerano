package ClasesUtiles.Admin;

import DAO.DaoProyectosSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ModificarNombreProyectoServlet")
public class ModificarNombreProyectoServlet extends HttpServlet {
    private int codigo;
    private String nuevoNombreProyecto;
    private DaoProyectosSQL daoProyectosSQL;

    public void init(){
        codigo=-1;
        nuevoNombreProyecto="no inicializado";
        daoProyectosSQL=new DaoProyectosSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        codigo= Integer.parseInt(req.getParameter("codigo"));
        nuevoNombreProyecto=req.getParameter("nuevoNombreProyecto");
        daoProyectosSQL.modificarNombreProyecto(codigo, nuevoNombreProyecto);
        resp.sendRedirect("listaDeProyectosAdmin.jsp");
    }
}
