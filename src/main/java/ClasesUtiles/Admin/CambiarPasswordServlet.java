package ClasesUtiles.Admin;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cambiarPasswordServlet")
public class CambiarPasswordServlet extends HttpServlet {
    private String password;
    private String userName;
    private DaoUsersSQL daoUsersSQL;

    @Override
    public void init() throws ServletException {
        userName="username inicializado";
        password="password inicializado";
        daoUsersSQL=new DaoUsersSQL();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        password= req.getParameter("newPassword");
        userName= req.getParameter("oldUserName");
        daoUsersSQL.cambiarPassword(userName, password);
        resp.sendRedirect("configuracionCuentaAdmin.jsp");
    }
}
