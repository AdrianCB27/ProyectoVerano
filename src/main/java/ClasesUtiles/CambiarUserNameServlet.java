package ClasesUtiles;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cambiarUserNameServlet")
public class CambiarUserNameServlet extends HttpServlet {
    private String oldUserName;
    private String newUserName;
    private DaoUsersSQL daoUsersSQL;

    @Override
    public void init() throws ServletException {
        oldUserName="userName inicializado";
        newUserName="newUsername inicializado";
        daoUsersSQL=new DaoUsersSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       oldUserName= req.getParameter("oldUserName");
       newUserName=req.getParameter("newUserName");
       daoUsersSQL.cambiarUserName(oldUserName, newUserName);
       resp.sendRedirect("configuracionCuenta.jsp");
    }
}