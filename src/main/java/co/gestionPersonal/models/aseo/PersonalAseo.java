package co.gestionPersonal.models.aseo;
import co.gestionPersonal.models.Persona;
import co.gestionPersonal.models.TipoDocumento;
import co.gestionPersonal.models.aseo.RolesAseo;


public class PersonalAseo extends Persona {
    private RolesAseo rol;
    private String jornada;
    private Boolean estado;

    public PersonalAseo(TipoDocumento tipoDocumento,Integer numeroDocumento,String nombre, String apellido, RolesAseo rol) {
        super(tipoDocumento,numeroDocumento,nombre,apellido);
        this.rol=rol;
        this.jornada = "Diurna";
        this.estado = true;
    }

    public RolesAseo getRol() {return rol;}
    public void setRol(RolesAseo rol) {this.rol = rol;}

    public Boolean getEstado() {return estado;}
    public void setEstado(Boolean estado) {this.estado = estado;}

    public String getJornada() {return jornada;}

}
