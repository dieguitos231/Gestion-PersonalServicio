package co.gestionPersonal.controller;

import co.gestionPersonal.models.Persona;
import co.gestionPersonal.models.TipoDocumento;
import co.gestionPersonal.models.aseo.PersonalAseo;
import co.gestionPersonal.models.aseo.RolesAseo;
import co.gestionPersonal.models.seguridad.PersonalGuardia;
import co.gestionPersonal.models.seguridad.Jornada;
import co.gestionPersonal.models.seguridad.RolesGuarda;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import co.gestionPersonal.utils.Validador;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    private final List<PersonalAseo> listaEmpleadosAseo = new ArrayList<>(
            List.of(
                    new PersonalAseo(TipoDocumento.CC, 21312389, "Carlos", "Rodriguez", RolesAseo.ASEO_GENERAL),
                    new PersonalAseo(TipoDocumento.CC, 1031807049, "Carlos", "Rodriguez", RolesAseo.ASEO_GENERAL),
                    new PersonalAseo(TipoDocumento.CC, 80430953, "Carlos", "Rodriguez", RolesAseo.PODADOR)
            )
    );
    private final List<PersonalGuardia> listaEmpleadosSeguridad = new ArrayList<>(
            List.of(
                    new PersonalGuardia(TipoDocumento.CC, 190823534, "Andres", "Martinez", RolesGuarda.SUPERVISOR, Jornada.NOCTURNA),
                    new PersonalGuardia(TipoDocumento.CC, 190823343, "Andres", "Martinez", RolesGuarda.PORTERIA, Jornada.NOCTURNA),
                    new PersonalGuardia(TipoDocumento.CC, 1908624233, "Andres", "Martinez", RolesGuarda.PORTERIA, Jornada.NOCTURNA)
            )
    );

    /*
     * Endpoint de inicio de pagina
     */
    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Bienvenido a la gestion de personal de conjunto");
    }

    /*
     * Filtrar lista de empleados o buscar por id
     */
    @GetMapping("/empleados")
    public ResponseEntity<?> empleados(@RequestParam(required = false) Integer id) {
        //Validamos si no hay ningun registro para no continuar
        if (listaEmpleadosAseo.isEmpty() && listaEmpleadosSeguridad.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay empleados registrados");
        }
        //Si nos pasan un ID, buscamos en cada lista
        if (id != null) {
            for (PersonalGuardia empleado : listaEmpleadosSeguridad) {
                if (empleado.getNumeroDocumento().equals(id)) {
                    return ResponseEntity.ok(empleado);
                }
            }
            for (PersonalAseo empleado : listaEmpleadosAseo) {
                if (empleado.getNumeroDocumento().equals(id)) {
                    return ResponseEntity.ok(empleado);
                }
            }
            //Si no existe mandamos un estado 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el empleado con el id: " + id);
        }

        //Unimos las listas para devolver a todos los empleados
        List<Persona> empleados = new ArrayList<>();
        empleados.addAll(listaEmpleadosAseo);
        empleados.addAll(listaEmpleadosSeguridad);
        return ResponseEntity.ok(empleados);
    }
    /*
     * Filtrar por rol o rol especifico
     */

    @GetMapping("/{rol}")
    public ResponseEntity<?> busquedaPorRol(@PathVariable String rol, @RequestParam(required = false) String as, @RequestParam(required = false) String seg) {

        if ("seguridad".equals(rol)) {
            if (listaEmpleadosSeguridad.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay empleados registrados");
            }
            if (seg != null && !seg.isEmpty()) {
                if (Validador.esRolValido("seguridad", seg)) {
                    return ResponseEntity.badRequest().body("El rol de seguridad " + seg + " no es valido.");
                }
                RolesGuarda rolBuscado=RolesGuarda.valueOf(seg.toUpperCase());
                List<PersonalGuardia> guardasFiltrados = new ArrayList<>();
                for (PersonalGuardia persona : listaEmpleadosSeguridad) {
                    if (persona.getRol().equals(rolBuscado)) {
                        guardasFiltrados.add(persona);
                    }
                }

                return ResponseEntity.ok(guardasFiltrados);
            }

            return ResponseEntity.ok(listaEmpleadosSeguridad);
        }

        if ("aseo".equals(rol)) {

            if (listaEmpleadosAseo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay empleados registrados");
            }

            if (as != null && !as.isEmpty()) {
                if (Validador.esRolValido("aseo", as)) {
                    return ResponseEntity.badRequest().body("El rol de aseo " + as + " no es valido.");
                }
                RolesAseo rolBuscado=RolesAseo.valueOf(as.toUpperCase());
                List<PersonalAseo> personalAseosFiltrados = new ArrayList<>();
                for (PersonalAseo persona : listaEmpleadosAseo) {

                    if (persona.getRol().equals(rolBuscado)) {
                        personalAseosFiltrados.add(persona);
                    }

                }
                return ResponseEntity.ok(personalAseosFiltrados);
            }

            return ResponseEntity.ok(listaEmpleadosAseo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El rol no existe. Busque por seguridad o aseo");
    }

    @PostMapping("/seguridad")
    public ResponseEntity<?> nuevoEmpleadoSeguridad(@RequestBody PersonalGuardia nuevaPersona ) {
        if(Validador.esNumeroDocumentoValido(nuevaPersona.getNumeroDocumento())){
            Integer numeroDocumento = nuevaPersona.getNumeroDocumento();

            for (PersonalGuardia guarda : listaEmpleadosSeguridad) {
                if (guarda.getNumeroDocumento().equals(numeroDocumento)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El numero de documento " + numeroDocumento + " ya existe.");
                }
            }
            for (PersonalAseo aseo : listaEmpleadosAseo) {
                if (aseo.getNumeroDocumento().equals(numeroDocumento)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El numero de documento " + numeroDocumento + " ya existe.");
                }
            }
            return ResponseEntity.ok(numeroDocumento);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El numero de documento no es valido.");
    }
    @PostMapping("/aseo")
    public ResponseEntity<?> nuevoEmpleadoAseo(@RequestBody PersonalAseo nuevaPersona ) {
        if(Validador.esNumeroDocumentoValido(nuevaPersona.getNumeroDocumento())){
            Integer numeroDocumento = nuevaPersona.getNumeroDocumento();

            for (PersonalGuardia guarda : listaEmpleadosSeguridad) {
                if (guarda.getNumeroDocumento().equals(numeroDocumento)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El numero de documento " + numeroDocumento + " ya existe.");
                }
            }
            for (PersonalAseo aseo : listaEmpleadosAseo) {
                if (aseo.getNumeroDocumento().equals(numeroDocumento)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El numero de documento " + numeroDocumento + " ya existe.");
                }
            }
            return ResponseEntity.ok(numeroDocumento);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El numero de documento no es valido.");
    }
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer id) {
        if(Validador.esNumeroDocumentoValido(id)){
            for (PersonalGuardia guarda : listaEmpleadosSeguridad) {
                if (guarda.getNumeroDocumento().equals(id)) {
                    listaEmpleadosSeguridad.remove(guarda);
                    return ResponseEntity.ok("Se ha eliminado el usuario con numero :"+guarda.getNumeroDocumento());
                }
            }
            for (PersonalAseo aseo : listaEmpleadosAseo) {
                if (aseo.getNumeroDocumento().equals(id)) {
                    listaEmpleadosAseo.remove(aseo);
                    return ResponseEntity.ok("Se ha eliminado el usuario con numero :"+aseo.getNumeroDocumento());
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El numero de documento no existe.");
    }
}
