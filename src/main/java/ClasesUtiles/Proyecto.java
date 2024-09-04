package ClasesUtiles;

public class Proyecto {
    private  int codigo;
    private  String nombre;
    private  String descripcion;
    private  String tipo;
    private  String fechaInicio;
    private  String fechaFin;
    private  float cantidadNecesaria;
    private  float cantidadFinanciada;

    public Proyecto(int codigo, String nombre, String descripcion, String tipo, String fechaInicio, String fechaFin, float cantidadNecesaria, float cantidadFinanciada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadNecesaria = cantidadNecesaria;
        this.cantidadFinanciada = cantidadFinanciada;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public float getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public float getCantidadFinanciada() {
        return cantidadFinanciada;
    }
}
