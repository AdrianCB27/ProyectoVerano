package ClasesUtiles.Inversor;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/InvertirEnProyectoServlet")
public class InvertirEnProyectoServlet extends HttpServlet {
    private String userName;
    private int codigoProyecto;
    private double cantidadInvertida;
    private DaoUsersSQL usersSQL;
    @Override
    public void init() throws ServletException {
        userName = "Username no inicializado";
        codigoProyecto = 0;
        cantidadInvertida = 0;
        usersSQL = new DaoUsersSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userName = req.getParameter("oldUserName");
        codigoProyecto = Integer.parseInt(req.getParameter("codigoProyecto"));
        cantidadInvertida = Double.parseDouble(req.getParameter("saldoAdded"));
        usersSQL.invertirProyecto(userName, codigoProyecto, cantidadInvertida);
        resp.sendRedirect("invertir.jsp");
    }
}
