package LoginYRegistro;

import DAO.DaoUsersSQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/registrar")
public class RegistroServlet extends HttpServlet {
    String nombreCompleto;
    String usuario;
    String correo;
    String pass;
    DaoUsersSQL daoSQL;


    @Override
    public void init() throws ServletException {
        nombreCompleto = "No completado";
        usuario = "No completado";
        pass = "No completado";
        correo = "No completado";

        daoSQL = new DaoUsersSQL();
    }
    public boolean yaExisteUsuario(String usuarioForm){
        ArrayList<String> usuariosExistentes=daoSQL.getAllUsersNames();
        for (String u: usuariosExistentes) {
            if (u.equalsIgnoreCase(usuario)) return true;
        }
        return false;
    }
    public static boolean comprobarFortalezaPass(String pass){
        char[] abecedarioMay={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] abecedarioMin={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] numeros={'1','2','3','4','5','6','7','8','9','0'};
        char caracterMay, caracterMin,caracterNumero;
        boolean mayuscula=false, minuscula=false;
        if (pass.length()>=8){
            if (pass.contains("@")||pass.contains("-")||pass.contains("_")||pass.contains("*")||pass.contains("+")
                    ||pass.contains(".")||pass.contains(",")) {
                for (int i = 0; i < pass.length(); i++) {
                    caracterMay=pass.charAt(i);
                    for (int j = 0; j < abecedarioMay.length; j++) {
                        if(caracterMay==abecedarioMay[j]){
                            mayuscula=true;
                            break;
                        }
                    }
                }
                if (mayuscula){
                    for (int i = 0; i < pass.length(); i++) {
                        caracterMin=pass.charAt(i);
                        for (int j = 0; j < abecedarioMin.length; j++) {
                            if(caracterMin==abecedarioMin[j]){
                                minuscula=true;
                                break;
                            }
                        }
                    }
                }
                if(minuscula) {
                    for (int i = 0; i < pass.length(); i++) {
                        caracterNumero = pass.charAt(i);
                        for (int j = 0; j < numeros.length; j++) {
                            if (caracterNumero == numeros[j]) {
                                return true;
                            }
                        }
                    }
                }
                else{

                    return false;
                }
            }
            else{

                return false;
            }
        }
        else{

            return false;
        }
        return false;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        //Parametros obtenidos del formulario de registro
        nombreCompleto = request.getParameter("nombre");
        usuario = request.getParameter("user");
        pass = request.getParameter("pass");
        correo = request.getParameter("correo");
        if (yaExisteUsuario(usuario)){
            try {
                response.sendRedirect("errores/errorUsuarioYaExiste.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (!comprobarFortalezaPass(pass)){
            try {
                response.sendRedirect("errores/errorPassNoSegura.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            daoSQL.addUsuario(nombreCompleto,usuario,pass,correo);
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
