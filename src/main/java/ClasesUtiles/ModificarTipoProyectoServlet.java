package ClasesUtiles;

import DAO.DaoProyectosSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ModificarTipoProyectoServlet")
public class ModificarTipoProyectoServlet extends HttpServlet {
    private int codigo;
    private String nuevoTipoProyecto;
    private DaoProyectosSQL daoProyectosSQL;

    public void init(){
        codigo=-1;
        nuevoTipoProyecto="no inicializado";
        daoProyectosSQL=new DaoProyectosSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        codigo= Integer.parseInt(req.getParameter("codigo"));
        nuevoTipoProyecto=req.getParameter("nuevoTipoProyecto");
        daoProyectosSQL.modificarNombreProyecto(codigo, nuevoTipoProyecto);
        resp.sendRedirect("listaDeProyectosAdmin.jsp");
    }
}
