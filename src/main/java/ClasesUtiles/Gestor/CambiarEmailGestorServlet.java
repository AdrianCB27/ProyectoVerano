package ClasesUtiles.Gestor;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CambiarEmailGestorServlet")
public class CambiarEmailGestorServlet extends HttpServlet {
    private String oldUsername;
    private String newEmail;
    private DaoUsersSQL daoUsersSQL;

    @Override
    public void init() throws ServletException {
        oldUsername="oldUsername inicializado";
        newEmail="newEmail inicializado";
        daoUsersSQL=new DaoUsersSQL();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        oldUsername= req.getParameter("oldUserName");
        newEmail=req.getParameter("newEmail");
        daoUsersSQL.cambiarEmail(oldUsername, newEmail);
        resp.sendRedirect("configuracionCuentaGestor.jsp");
    }
}