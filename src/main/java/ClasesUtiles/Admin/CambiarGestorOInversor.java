package ClasesUtiles.Admin;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cambiarGestorOInversor")

public class CambiarGestorOInversor extends HttpServlet {
    private String usuario;
    private DaoUsersSQL daoSQL;

    public void init() {
        usuario = "usuario inicializado";
        daoSQL = new DaoUsersSQL();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usuario=req.getParameter("user");
        if (daoSQL.cambiarAGestor(usuario)||daoSQL.cambiarAInversor(usuario))resp.sendRedirect("modificarUsuarios.jsp");
    }
}
