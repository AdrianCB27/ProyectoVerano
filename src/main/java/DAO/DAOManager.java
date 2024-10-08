package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOManager {
    // Atributos
    private Connection conn;
    private final String URL;
    private final String USER;
    private final String PASS;
    private static DAOManager singlenton; // Atributo estatico que guarda una referencia al DAO

    // Constructor PRIVADO para que no se pueda utilizar desde el exterior
    public DAOManager() {
        this.conn = null;
        this.URL = "jdbc:mysql://127.0.0.1:3307/inmobiliaria"; // Dirección del servidor y de BD a usar
        this.USER = "root"; // Usuario de la BBDD
        this.PASS = "avocado"; // Clave de la BBDD
    }

    // "Constructor" PÚBLICO. Comprueba si el atributo singlenton ya tiene valor.
    // Si no lo tiene, crea la conexión y el objeto.
    // Si lo tiene, devuelve null para que no se creen más objetos de la clase com.example.loginjsp.DAOManager.

   /* public static DAOManager getSinglentonInstance(){
        if (singlenton == null) {
            singlenton = new DAOManager();
            return singlenton;
        }else return null;
    }*/

    // Abre la conexión con BD y la guarda en conn
    public void open() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); //Cargo el driver de conexión JDBC
        conn = DriverManager.getConnection(URL, USER, PASS); //Uso la clase DriverManager para crear la conexión
    }

    // Devuelve la conexión con la BD
    public Connection getConn() {
        return conn;
    }

    // Cierra la conexión con BD
    public void close() throws Exception {
        if(conn!=null) conn.close();
    }

}
