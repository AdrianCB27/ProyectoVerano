package ClasesUtiles.Gestor;

import ClasesUtiles.Proyecto;
import DAO.DaoProyectosSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CrearProyectoServlet")
public class CrearProyectoServlet extends HttpServlet {
    String nombre, descripcion, tipo, fechaInicio, fechaFin;
    float cantidadNecesaria;
    DaoProyectosSQL daoProyectosSQL;

    @Override
    public void init() throws ServletException {
        daoProyectosSQL = new DaoProyectosSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        nombre = req.getParameter("nombre");
        descripcion = req.getParameter("descripcion");
        tipo = req.getParameter("tipo");
        fechaInicio = req.getParameter("fechaInicio");
        fechaFin = req.getParameter("fechaFin");
        cantidadNecesaria = Float.parseFloat(req.getParameter("cantidadNecesaria"));
        if (daoProyectosSQL.addProyecto(nombre,descripcion,tipo,fechaInicio,fechaFin,cantidadNecesaria,0f)){
            resp.sendRedirect("gestor.jsp");
        }else {
            resp.sendRedirect("errores/errorProyectoNoCreado.jsp");
        }
    }
}
