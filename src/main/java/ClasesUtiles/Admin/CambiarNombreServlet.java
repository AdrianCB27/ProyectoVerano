package ClasesUtiles.Admin;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cambiarNombreServlet")
public class CambiarNombreServlet extends HttpServlet {
    private String oldUserName;
    private String newNombre;
    private DaoUsersSQL daoUsersSQL;

    @Override
    public void init() throws ServletException {
        oldUserName="oldUserName inicializado";
        newNombre="newNombre inicializado";
        daoUsersSQL=new DaoUsersSQL();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        oldUserName= req.getParameter("oldUserName");
        newNombre=req.getParameter("newNombre");
        daoUsersSQL.cambiarNombre(oldUserName, newNombre);
        resp.sendRedirect("configuracionCuentaAdmin.jsp");
    }
}
