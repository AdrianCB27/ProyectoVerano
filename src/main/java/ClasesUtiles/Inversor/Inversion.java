package ClasesUtiles.Inversor;

public class Inversion {
    private String fechaInicio;
    private String ultimaActualizacion;
    private double cantidadParticipada;
    private int codigoProyecto;
    private String nombreProyecto;


    public Inversion(String fechaInicio,  String ultimaActualizacion, double cantidadParticipada, int codigoProyecto,String nombreProyecto) {
        this.fechaInicio = fechaInicio;
        this.nombreProyecto = nombreProyecto;
        this.ultimaActualizacion = ultimaActualizacion;
        this.cantidadParticipada = cantidadParticipada;
        this.codigoProyecto = codigoProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public double getCantidadParticipada() {
        return cantidadParticipada;
    }

    public int getCodigoProyecto() {
        return codigoProyecto;
    }
}
