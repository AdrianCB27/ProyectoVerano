package DAO;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ClasesUtiles.Gestor;
import ClasesUtiles.Inversor;

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
}
