package DAO;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import ClasesUtiles.Gestor.Gestor;
import ClasesUtiles.Inversor.Inversor;

public class DaoUsersSQL {

    DAOManager dao;

    public DaoUsersSQL() {
        dao = new DAOManager();
    }

    public DAOManager getDao() {
        return dao;
    }

    public ArrayList<String> getAllUsersNames() {
        ArrayList<String> users = new ArrayList<>();
        try {
            dao.open();
            String sql = "SELECT * FROM usuarios";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(rs.getString("userName"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public ArrayList<Gestor> getAllUsersGestores() {
        ArrayList<Gestor> usersGestores = new ArrayList<>();
        try {
            dao.open();
            String sql = "SELECT * FROM gestores";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("userName");
                boolean estadoCuenta = rs.getBoolean("bloqueado");
                usersGestores.add(new Gestor(userName, estadoCuenta));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usersGestores;
    }


    public String getUserAdmin() {
        String userName = "";
        try {
            dao.open();
            String sql = "SELECT * FROM admins";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userName = rs.getString("userName");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userName;
    }

    public ArrayList<String> getDatosUsuario() {
        String userName = getUserAdmin();
        ArrayList<String> datosAdmin = new ArrayList<>();
        try {
            dao.open();
            String sql = "SELECT * FROM usuarios WHERE userName like ?";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                datosAdmin.add(rs.getString("userName"));
                datosAdmin.add(rs.getString("nombre"));
                datosAdmin.add(rs.getString("email"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return datosAdmin;
    }

    public ArrayList<String> getDatosUsuarioGestor(String username) {
        ArrayList<String> datosGestor = new ArrayList<>();
        try {
            dao.open();
            String sql = "SELECT * FROM usuarios WHERE userName like ?";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                datosGestor.add(rs.getString("userName"));
                datosGestor.add(rs.getString("nombre"));
                datosGestor.add(rs.getString("email"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return datosGestor;
    }

    public ArrayList<String> getDatosUsuarioInversor(String username) {
        ArrayList<String> datosInversor = new ArrayList<>();
        try {
            dao.open();
            String sql = "SELECT * FROM usuarios WHERE userName like ?";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                datosInversor.add(rs.getString("userName"));
                datosInversor.add(rs.getString("nombre"));
                datosInversor.add(rs.getString("email"));
                datosInversor.add(rs.getString("saldo"));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return datosInversor;
    }
    public String getSaldoInversor(String username) {
        try {
            dao.open();
            String sql = "SELECT * FROM inversores WHERE userName like ?";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return (rs.getString("saldo"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "0";
    }
    public boolean setSaldoInversor(String userName, double nuevoSaldo){
        double saldoSumado;
        try {
            dao.open();
            double saldoActual= Double.parseDouble(getSaldoInversor(userName));
            saldoSumado = nuevoSaldo+saldoActual;
            String sql = "UPDATE inversores SET saldo= ? WHERE userName=?";
            PreparedStatement ps=dao.getConn().prepareStatement(sql);
            ps.setDouble(1, saldoSumado);
            ps.setString(2, userName);
            return ps.executeUpdate()>0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Inversor> getAllUsersInversores() {
        ArrayList<Inversor> usersInversores = new ArrayList<>();
        try {
            dao.open();
            String sql = "SELECT * FROM inversores";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("userName");
                boolean estadoCuenta = rs.getBoolean("bloqueado");
                double saldo = rs.getDouble("saldo");
                usersInversores.add(new Inversor(userName, estadoCuenta, saldo));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usersInversores;
    }

    public static StringBuilder cypherPassword(String passwordEnClaro) {
        StringBuilder resumenPassword = new StringBuilder();
        try {
            byte[] bytesOfMessage = passwordEnClaro.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] digest = md.digest(bytesOfMessage);
            for (byte b : digest) {
                resumenPassword.append(String.format("%02x", b));
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return resumenPassword;
    }

    public boolean comprobarUserYPassword(String user, String pass) {
        try {
            String passCifrada = String.valueOf(cypherPassword(pass));
            dao.open();
            String sql = "SELECT * FROM usuarios WHERE userName like ? and userPassword like ?";
            PreparedStatement ps = dao.getConn().prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, passCifrada);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean addUsuario(String nombreCompleto, String user, String pass, String correo) {
        String insert = "INSERT INTO usuarios (userName,nombre,userPassword,email) values (?,?,?,?)";
        String insertInversor = "INSERT INTO inversores (saldo,bloqueado,userName) values (?,?,?)";
        try {
            dao.open();
            String passCifrada = String.valueOf(cypherPassword(pass));
            PreparedStatement ps = dao.getConn().prepareStatement(insert);
            ps.setString(1, user);
            ps.setString(2, nombreCompleto);
            ps.setString(3, passCifrada);
            ps.setString(4, correo);

            PreparedStatement ps2 = dao.getConn().prepareStatement(insertInversor);
            ps2.setDouble(1, 0);
            ps2.setBoolean(2, false);
            ps2.setString(3, user);
            int filas = ps.executeUpdate();
            int filas2 = ps2.executeUpdate();
            if (filas > 0 && filas2 > 0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public boolean esGestor(String username) {
        try {
            String consulta = "SELECT userName FROM gestores where userName like ?";
            dao.open();
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean esAdmin(String username) {
        try {
            String consulta = "SELECT userName FROM admins where userName like ?";
            dao.open();
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean esInversor(String username) {
        try {
            String consulta = "SELECT userName FROM inversores where userName like ?";
            dao.open();
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean bloquearDesbloquearInversores(String username) {
        try {
            String consulta = "SELECT bloqueado from inversores where userName like ?";
            dao.open();
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean estaBloqueado = rs.getBoolean("bloqueado");
                String update = "UPDATE inversores SET bloqueado = ? WHERE userName LIKE ?";
                PreparedStatement ps2 = dao.getConn().prepareStatement(update);
                ps2.setBoolean(1, !estaBloqueado);
                ps2.setString(2, username);
                int filas = ps2.executeUpdate();
                ps2.close();
                rs.close();
                ps.close();
                dao.close();
                return filas > 0;
            }
            rs.close();
            ps.close();
            dao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean bloquearDesbloquearGestores(String username) {
        try {
            String consulta = "SELECT bloqueado from gestores where userName like ?";
            dao.open();
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean estaBloqueado = rs.getBoolean("bloqueado");
                String update = "UPDATE gestores SET bloqueado = ? WHERE userName LIKE ?";
                PreparedStatement ps2 = dao.getConn().prepareStatement(update);
                ps2.setBoolean(1, !estaBloqueado);
                ps2.setString(2, username);
                int filas = ps2.executeUpdate();
                ps2.close();
                rs.close();
                ps.close();
                dao.close();
                return filas > 0;
            }
            rs.close();
            ps.close();
            dao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean cambiarAGestor(String username) {
        try {
            String consultaInsert = "INSERT INTO gestores (bloqueado, userName) values (0, ?)";
            dao.open();
            PreparedStatement ps = dao.getConn().prepareStatement(consultaInsert);
            ps.setString(1, username);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                String consultaDelete = "DELETE FROM inversores where userName like ?";
                PreparedStatement ps2 = dao.getConn().prepareStatement(consultaDelete);
                ps2.setString(1, username);
                int filas2 = ps2.executeUpdate();
                if (filas2 > 0) {
                    ps2.close();
                    dao.close();
                    ps.close();
                    return true;
                }
            }
            dao.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean cambiarAInversor(String username) {

        String consultaInsert = "INSERT INTO inversores (bloqueado,userName,saldo) values (0,?,0)";
        try {
            dao.open();
            PreparedStatement ps = dao.getConn().prepareStatement(consultaInsert);
            ps.setString(1, username);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                String consultaDelete = "DELETE FROM gestores where userName like ?";
                PreparedStatement ps2 = dao.getConn().prepareStatement(consultaDelete);
                ps2.setString(1, username);
                int filas2 = ps2.executeUpdate();
                if (filas2 > 0) {
                    ps2.close();
                    dao.close();
                    ps.close();
                    return true;
                }
            }
            dao.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean cambiarUserName(String oldUsername, String newUsername) {
        try {
            dao.open();
            String update = "UPDATE usuarios SET userName = ? WHERE userName = ?";
            PreparedStatement ps = dao.getConn().prepareStatement(update);
            ps.setString(1, newUsername);
            ps.setString(2, oldUsername);
            int filas = ps.executeUpdate();
            dao.close();
            ps.close();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean cambiarNombre(String oldUsername, String newNombre) {
        try {
            dao.open();
            String update = "UPDATE usuarios SET nombre = ? WHERE userName = ?";
            PreparedStatement ps = dao.getConn().prepareStatement(update);
            ps.setString(1, newNombre);
            ps.setString(2, oldUsername);
            int filas = ps.executeUpdate();
            dao.close();
            ps.close();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean cambiarEmail(String oldUsername, String newEmail) {
        try {
            dao.open();
            String update = "UPDATE usuarios SET email = ? WHERE userName = ?";
            PreparedStatement ps = dao.getConn().prepareStatement(update);
            ps.setString(1, newEmail);
            ps.setString(2, oldUsername);
            int filas = ps.executeUpdate();
            dao.close();
            ps.close();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean cambiarPassword(String oldUsername, String newPassword) {
        try {
            dao.open();
            String passCifrada = String.valueOf(cypherPassword(newPassword));
            String update = "UPDATE usuarios SET userPassword = ? WHERE userName = ?";
            PreparedStatement ps = dao.getConn().prepareStatement(update);
            ps.setString(1, passCifrada);
            ps.setString(2, oldUsername);
            int filas = ps.executeUpdate();
            dao.close();
            ps.close();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean invertirProyecto(String userName, int codigo, double cantidadAInvertir){
        double saldoInversor = 0, cantidadProyecto = 0;
        try{
            dao.open();
            String consulta = "SELECT saldo FROM inversores WHERE userName = ?";
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) saldoInversor = rs.getDouble("saldo");
            if(saldoInversor > cantidadAInvertir){
                String getCantidadProyecto = "SELECT cantidadFinanciada FROM proyectos WHERE codigo = ?";
                PreparedStatement ps2= dao.getConn().prepareStatement(getCantidadProyecto);
                ps2.setInt(1,codigo);
                ResultSet rs2= ps2.executeQuery();
                if (rs2.next()) cantidadProyecto= rs2.getDouble("cantidadFinanciada");
                String insertInversion= "INSERT INTO inversion (fechaInicio,ultimaActualizacion,cantidadParticipada,codigoProyecto,inversorUserName)" +
                        "values (?,?,?,?,?)";
                PreparedStatement ps3= dao.getConn().prepareStatement(insertInversion);
                String fechaHoy= LocalDate.now().toString();
                ps3.setString(1,fechaHoy);
                ps3.setString(2,fechaHoy);
                ps3.setDouble(3,cantidadAInvertir);
                ps3.setInt(4,codigo);
                ps3.setString(5,userName);
                int filas = ps3.executeUpdate();

                //________________________________
                double nuevoSaldo = saldoInversor-cantidadAInvertir;
                String updateSaldoInversor= "UPDATE inversores SET saldo=? WHERE userName = ?";
                PreparedStatement ps4= dao.getConn().prepareStatement(updateSaldoInversor);
                ps4.setDouble(1, nuevoSaldo);
                ps4.setString(2, userName);
                int filas2= ps4.executeUpdate();

                //________________________________
                double nuevaCantidadProyecto = cantidadProyecto + cantidadAInvertir;
                String updateCantidadProyecto= "UPDATE proyectos SET cantidadFinanciada=? WHERE codigo = ?";
                PreparedStatement ps5 = dao.getConn().prepareStatement(updateCantidadProyecto);
                ps5.setDouble(1, nuevaCantidadProyecto);
                ps5.setInt(2, codigo);
                int filas3= ps5.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
