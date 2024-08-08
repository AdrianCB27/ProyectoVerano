package ClasesUtiles.Gestor;

import DAO.DaoProyectosSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ModificarFechaFinProyectoGestorServlet")
public class ModificarFechaFinProyectoGestorServlet extends HttpServlet {
    private int codigo;
    private String nuevaFechaFinProyecto;
    private DaoProyectosSQL daoProyectosSQL;

    public void init(){
        codigo=-1;
        nuevaFechaFinProyecto="no inicializado";
        daoProyectosSQL=new DaoProyectosSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        codigo= Integer.parseInt(req.getParameter("codigo"));
        nuevaFechaFinProyecto=req.getParameter("fechaFin");
        daoProyectosSQL.modificarFechaFinProyecto(codigo, nuevaFechaFinProyecto);
        resp.sendRedirect("gestor.jsp");
    }
}
