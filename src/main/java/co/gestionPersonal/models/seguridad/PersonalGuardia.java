package co.gestionPersonal.models.seguridad;
import co.gestionPersonal.models.Persona;

import co.gestionPersonal.models.TipoDocumento;

public class PersonalGuardia extends Persona {
    private RolesGuarda rol;
    private Jornada jornada;
    private Boolean estado;

    public PersonalGuardia(TipoDocumento TipoDocumento, Integer numeroDocumento, String nombre, String apellido, RolesGuarda rol, Jornada jornada) {
        super(TipoDocumento,numeroDocumento,nombre,apellido);
        this.rol = rol;
        this.jornada = jornada;
        this.estado = true;
    }


    public RolesGuarda getRol() { return rol; }
    public void setRol(RolesGuarda rol) { this.rol = rol; }

    public Jornada getJornada() { return jornada; }
    public Jornada setJornada(Jornada jornada) {this.jornada = jornada; return this.jornada; }

    public Boolean getEstado() { return estado; }
    public void setEstado() { this.estado = !estado; }

}
