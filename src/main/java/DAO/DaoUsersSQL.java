package DAO;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoUsersSQL {
    private DAOManager daoManager;
    public DaoUsersSQL(){
        daoManager= DAOManager.getSinglentonInstance();
    }
    public ArrayList<String> getAllUsers(){
        ArrayList<String> users=new ArrayList<>();
        try {
            daoManager.open();
            String sql= "SELECT * FROM usuarios";
            PreparedStatement ps=daoManager.getConn().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                users.add(rs.getString("userName"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public static StringBuilder cypherPassword(String passwordEnClaro) {
        StringBuilder resumenPassword = new StringBuilder();
        try {
            byte[] bytesOfMessage = passwordEnClaro.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] digest = md.digest(bytesOfMessage);
            for (byte b : digest) {
                resumenPassword.append(String.format("%02x", b)); // Hexadecimal
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return resumenPassword;
    }
    public boolean comprobarUserYPassword(String user,String pass){
        try {
            String passCifrada= String.valueOf(cypherPassword(pass));
            daoManager.open();
            String sql= "SELECT * FROM usuarios WHERE userName like ? and userPassword like ?";
            PreparedStatement ps=daoManager.getConn().prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,passCifrada);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean addUsuario(String nombreCompleto, String user, String pass, String correo){
        String insert= "INSERT INTO usuarios (userName,nombre,userPassword,email) values (?,?,?,?)";
        try {
            daoManager.open();
            String passCifrada= String.valueOf(cypherPassword(pass));
            PreparedStatement ps=daoManager.getConn().prepareStatement(insert);
            ps.setString(1, user);
            ps.setString(2, nombreCompleto);
            ps.setString(3, passCifrada);
            ps.setString(4, correo);
            int filas=ps.executeUpdate();
            if (filas>0)return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;

    }

}
