package LoginYRegistro;

import DAO.DAOManager;
import DAO.DaoUsersSQL;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private String usuario;
    private String password;
    private DaoUsersSQL daoSQL;

    public void init() {
        usuario = "usuario inicializado";
        password = "contraseña inicializado";
        daoSQL = new DaoUsersSQL();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usuario = req.getParameter("user");
        password = req.getParameter("pass");
        //TODO comprobar el tipo de usuario con un switch para luego redirigirlo a su pagina correspondiente
        if (daoSQL.comprobarUserYPassword(usuario,password)) {
            HttpSession session= req.getSession();
            session.setAttribute("usuario",usuario);
            if(daoSQL.esAdmin(usuario)) resp.sendRedirect("admin.jsp");
            else if(daoSQL.esGestor(usuario)) resp.sendRedirect("gestor.jsp");
            else if(daoSQL.esInversor(usuario)) resp.sendRedirect("inversor.jsp");

        } else {
            resp.sendRedirect("errores/errorLogin.jsp");
        }
    }
}
