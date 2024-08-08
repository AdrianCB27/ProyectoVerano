package ClasesUtiles.Gestor;

import DAO.DaoProyectosSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ModificarFechaInicioProyectoGestorServlet")
public class ModificarFechaInicioProyectoGestorServlet extends HttpServlet {
    private int codigo;
    private String fechaInicio;
    private DaoProyectosSQL daoProyectosSQL;

    public void init(){
        codigo=-1;
        fechaInicio="no inicializado";
        daoProyectosSQL=new DaoProyectosSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        codigo= Integer.parseInt(req.getParameter("codigo"));
        fechaInicio=req.getParameter("fechaInicio");
        daoProyectosSQL.modificarFechaInicioProyecto(codigo, fechaInicio);
        resp.sendRedirect("gestor.jsp");
    }
}
