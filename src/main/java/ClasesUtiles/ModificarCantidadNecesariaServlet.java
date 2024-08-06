package ClasesUtiles;

import DAO.DaoProyectosSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ModificarCantidadNecesariaServlet")
public class ModificarCantidadNecesariaServlet extends HttpServlet {
    private int codigo;
    private String cantidadNecesaria;
    private DaoProyectosSQL daoProyectosSQL;

    public void init(){
        codigo=-1;
        cantidadNecesaria="no inicializado";
        daoProyectosSQL=new DaoProyectosSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        codigo= Integer.parseInt(req.getParameter("codigo"));
        cantidadNecesaria=req.getParameter("cantidadNecesaria");
        daoProyectosSQL.modificarNombreProyecto(codigo, cantidadNecesaria);
        resp.sendRedirect("listaDeProyectosAdmin.jsp");
    }
}
