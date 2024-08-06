package ClasesUtiles;

import DAO.DaoProyectosSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ModificarDescripcionProyectoServlet")
public class ModificarDescripcionProyectoServlet extends HttpServlet {
    private int codigo;
    private String nuevaDescripcionProyecto;
    private DaoProyectosSQL daoProyectosSQL;

    public void init(){
        codigo=-1;
        nuevaDescripcionProyecto="no inicializado";
        daoProyectosSQL=new DaoProyectosSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        codigo= Integer.parseInt(req.getParameter("codigo"));
        nuevaDescripcionProyecto=req.getParameter("nuevaDescripcionProyecto");
        daoProyectosSQL.modificarNombreProyecto(codigo, nuevaDescripcionProyecto);
        resp.sendRedirect("listaDeProyectosAdmin.jsp");
    }
}
