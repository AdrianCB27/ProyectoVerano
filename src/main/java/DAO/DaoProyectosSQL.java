package DAO;

import ClasesUtiles.Inversor.Inversion;
import ClasesUtiles.Proyecto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoProyectosSQL {
    DAOManager dao;

    public DaoProyectosSQL() {
        dao = new DAOManager();
    }

    public DAOManager getDao() {
        return dao;
    }

    //funcion para el inversor
    public ArrayList<Proyecto> getProyectosAInvertir() {
        ArrayList<Proyecto> proyects = new ArrayList<>();
        try {
            dao.open();
            String consulta = "SELECT * FROM proyectos where cantidadNecesaria > cantidadFinanciada";
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String tipo = rs.getString("tipo");
                String fechaInicio = rs.getString("fechaInicio");
                String fechaFin = rs.getString("fechaFin");
                float cantidadNecesaria = rs.getFloat("cantidadNecesaria");
                float cantidadFinanciada = rs.getFloat("cantidadFinanciada");
                proyects.add(new Proyecto(codigo, nombre, descripcion, tipo, fechaInicio, fechaFin, cantidadNecesaria, cantidadFinanciada));
            }
            ps.close();
            dao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return proyects;
    }
//funcion para el admin
    public ArrayList<Proyecto> getAllProyects() {
        ArrayList<Proyecto> proyects = new ArrayList<>();
        try {
            dao.open();
            String consulta = "SELECT * FROM proyectos";
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String tipo = rs.getString("tipo");
                String fechaInicio = rs.getString("fechaInicio");
                String fechaFin = rs.getString("fechaFin");
                float cantidadNecesaria = rs.getFloat("cantidadNecesaria");
                float cantidadFinanciada = rs.getFloat("cantidadFinanciada");
                proyects.add(new Proyecto(codigo, nombre, descripcion, tipo, fechaInicio, fechaFin, cantidadNecesaria, cantidadFinanciada));

            }
            ps.close();
            dao.close();
            return proyects;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Proyecto getDatosProyecto(int codigo) {
        try {
            dao.open();
            String consulta = "SELECT * FROM proyectos WHERE codigo = ?";
            PreparedStatement ps = dao.getConn().prepareStatement(consulta);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String tipo = rs.getString("tipo");
                String fechaInicio = rs.getString("fechaInicio");
                String fechaFin = rs.getString("fechaFin");
                float cantidadNecesaria = rs.getFloat("cantidadNecesaria");
                float cantidadFinanciada = rs.getFloat("cantidadFinanciada");
                return new Proyecto(codigo, nombre, descripcion, tipo, fechaInicio, fechaFin, cantidadNecesaria, cantidadFinanciada);
            }
            ps.close();
            dao.close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addProyecto(String nombre, String descripcion, String tipo, String fechaInicio, String fechaFin, float cantidadNecesaria, float cantidadFinanciada) {
try {
    dao.open();
    String insert = "INSERT INTO proyectos (nombre,descripcion,tipo,fechaInicio,fechaFin,cantidadNecesaria,cantidadFinanciada) values (?,?,?,?,?,?,?)";
    PreparedStatement ps = dao.getConn().prepareStatement(insert);
    ps.setString(1, nombre);
    ps.setString(2, descripcion);
    ps.setString(3, tipo);
    ps.setString(4, fechaInicio);
    ps.setString(5, fechaFin);
    ps.setFloat(6, cantidadNecesaria);
    ps.setFloat(7, cantidadFinanciada);
    int filas = ps.executeUpdate();
    ps.close();
    dao.close();
    if (filas > 0) return true;
} catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
        return false;
    }

    public boolean deleteProyecto(int codigo) {
        try {
            dao.open();
            String delete = "DELETE FROM proyectos WHERE codigo= ?";
            PreparedStatement ps = dao.getConn().prepareStatement(delete);
            ps.setInt(1, codigo);
            int filas = ps.executeUpdate();
            ps.close();
            dao.close();
            if (filas > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean modificarNombreProyecto (int codigo,String nombre){
        try{
            dao.open();
            String update = "UPDATE proyectos SET nombre=? WHERE codigo=?";
            PreparedStatement ps=dao.getConn().prepareStatement(update);
            ps.setString(1,nombre);
            ps.setInt(2,codigo);
            int filas = ps.executeUpdate();
            ps.close();
            dao.close();
            if (filas>0) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean modificarDescripcionProyecto (int codigo,String descripcion){
        try{
            dao.open();
            String update = "UPDATE proyectos SET descripcion=? WHERE codigo=?";
            PreparedStatement ps=dao.getConn().prepareStatement(update);
            ps.setString(1,descripcion);
            ps.setInt(2,codigo);
            int filas = ps.executeUpdate();
            ps.close();
            dao.close();
            if (filas>0) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean modificarTipoDeProyecto(int codigo, String tipo){
        try{
            dao.open();
            String update = "UPDATE proyectos SET tipo=? WHERE codigo=?";
            PreparedStatement ps=dao.getConn().prepareStatement(update);
            ps.setString(1,tipo);
            ps.setInt(2,codigo);
            int filas = ps.executeUpdate();
            ps.close();
            dao.close();
            if (filas>0) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean modificarFechaInicioProyecto (int codigo,String fechaInicio){
        try{
            dao.open();
            String update = "UPDATE proyectos SET fechaInicio=? WHERE codigo=?";
            PreparedStatement ps=dao.getConn().prepareStatement(update);
            ps.setString(1,fechaInicio);
            ps.setInt(2,codigo);
            int filas = ps.executeUpdate();
            ps.close();
            dao.close();
            if (filas>0) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean modificarFechaFinProyecto (int codigo,String fechaFin){
        try{
            dao.open();
            String update = "UPDATE proyectos SET fechaFin=? WHERE codigo=?";
            PreparedStatement ps=dao.getConn().prepareStatement(update);
            ps.setString(1,fechaFin);
            ps.setInt(2,codigo);
            int filas = ps.executeUpdate();
            ps.close();
            dao.close();
            if (filas>0) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public boolean modificarCantidadNecesaria (int codigo,float cantidadNecesaria){
        try{
            dao.open();
            String update = "UPDATE proyectos SET cantidadNecesaria=? WHERE codigo=?";
            PreparedStatement ps=dao.getConn().prepareStatement(update);
            ps.setFloat(1,cantidadNecesaria);
            ps.setInt(2,codigo);
            int filas = ps.executeUpdate();
            ps.close();
            dao.close();
            if (filas>0) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean aniadirCantidadProyecto (int codigo,float cantidadNueva){
        try{
            float cantidadAntigua=0f;
            float cantidadNecesaria=0f;
            dao.open();
            PreparedStatement ps2=dao.getConn().prepareStatement("SELECT cantidadFinanciada, cantidadNecesaria FROM proyectos WHERE codigo = ?");
            ps2.setInt(1,codigo);
            ResultSet rs= ps2.executeQuery();
            if (rs.next()) {
                cantidadAntigua=rs.getFloat("cantidadFinanciada");
                cantidadNecesaria=rs.getFloat("cantidadNecesaria");
            }
            float total= cantidadNueva+cantidadAntigua;
            if(total > cantidadNecesaria) return false;
            String update = "UPDATE proyectos SET cantidadFinanciada=? WHERE codigo=?";
            PreparedStatement ps=dao.getConn().prepareStatement(update);
            ps.setFloat(1,total);
            ps.setInt(2,codigo);
            int filas = ps.executeUpdate();
            ps.close();
            ps2.close();
            dao.close();
            if (filas>0) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public ArrayList<Inversion> getInversionesByUserName(String userName){
        try{
            String nombreProyecto="", fechaInicio, ultimaActualizacion;
            double cantidadParticipada;
            int codigoProyecto;
            ArrayList<Inversion> inversiones=new ArrayList<>();
            dao.open();
            System.out.println("holaaaa "+ userName);
            String consultaInversion = "SELECT  fechaInicio, ultimaActualizacion, cantidadParticipada,codigoProyecto FROM " +
                    "inversion WHERE inversorUserName= ?";
            PreparedStatement ps=dao.getConn().prepareStatement(consultaInversion);
            ps.setString(1, userName);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                fechaInicio = rs.getString("fechaInicio");
                ultimaActualizacion = rs.getString("ultimaActualizacion");
                cantidadParticipada = rs.getDouble("cantidadParticipada");
                codigoProyecto = rs.getInt("codigoProyecto");
                String consultaProyecto = "SELECT nombre FROM proyectos WHERE codigo = ?";
                PreparedStatement ps2= dao.getConn().prepareStatement(consultaProyecto);
                ps2.setInt(1, codigoProyecto);
                ResultSet rs2=ps2.executeQuery();
                if (rs2.next()) nombreProyecto = rs2.getString("nombre");
                inversiones.add(new Inversion(fechaInicio,ultimaActualizacion,cantidadParticipada,codigoProyecto, nombreProyecto));
            }

            return inversiones;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
