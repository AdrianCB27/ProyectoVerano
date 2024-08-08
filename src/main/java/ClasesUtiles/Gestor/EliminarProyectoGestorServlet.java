package ClasesUtiles.Gestor;

import DAO.DaoProyectosSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EliminarProyectoGestorServlet")
public class EliminarProyectoGestorServlet extends HttpServlet {
    private int codigo;
    private DaoProyectosSQL daoProyectosSQL;

    public void init(){
        codigo=-1;
        daoProyectosSQL=new DaoProyectosSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        codigo= Integer.parseInt(req.getParameter("codigo"));
        daoProyectosSQL.deleteProyecto(codigo);
        resp.sendRedirect("listaDeProyectosGestor.jsp");
    }
}
