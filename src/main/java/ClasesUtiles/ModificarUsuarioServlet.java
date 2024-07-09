package ClasesUtiles;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ModificarUsuarioServlet")
public class ModificarUsuarioServlet extends HttpServlet {

    private String usuario;
    private DaoUsersSQL daoSQL;

    public void init() {
        usuario = "usuario inicializado";
        daoSQL = new DaoUsersSQL();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usuario = req.getParameter("user");
        if (daoSQL.bloquearDesbloquearInversores(usuario)|| daoSQL.bloquearDesbloquearGestores(usuario)){
            resp.sendRedirect("admin.jsp");
        }
    }
}
