package ClasesUtiles.Inversor;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddSaldoServlet")
public class AddSaldoServlet extends HttpServlet {
    private String oldUsername;
    private double saldo;
    private DaoUsersSQL daoUsersSQL;

    @Override
    public void init() throws ServletException {
        oldUsername="oldUsername inicializado";
        saldo=0;
        daoUsersSQL=new DaoUsersSQL();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        oldUsername= req.getParameter("oldUserName");
        saldo= Double.parseDouble(req.getParameter("saldoAdded"));
        if (daoUsersSQL.setSaldoInversor(oldUsername, saldo)) {
            System.out.println("BIEN HECHO");
        }
        else System.out.println("MAL HECHO");
        resp.sendRedirect("carteraVirtualInversor.jsp");
    }
}
