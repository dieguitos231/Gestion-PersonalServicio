package co.gestionPersonal.models;

public class Persona {
    private TipoDocumento tipoDocumento;
    private Integer numeroDocumento;
    private String nombre;
    private String apellido;

    public Persona(TipoDocumento tipoDocumento, Integer numeroDocumento, String nombre, String apellido){
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public TipoDocumento getTipoDocumento() {return tipoDocumento;}
    public void setTipoDocumento(TipoDocumento tipoDocumento) {this.tipoDocumento = tipoDocumento;}

    public Integer getNumeroDocumento() {return numeroDocumento;}
    public void setNumeroDocumento(Integer numeroDocumento) {this.numeroDocumento = numeroDocumento;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

}
