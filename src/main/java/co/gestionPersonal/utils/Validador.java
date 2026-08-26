package co.gestionPersonal.utils;

import co.gestionPersonal.models.aseo.RolesAseo;
import co.gestionPersonal.models.seguridad.RolesGuarda;

public class Validador {
    /*
    * Validador de roles de seguiridad
    */
    public static boolean esRolValido(String rol,String rolDigitado){
        if("seguridad".equals(rol) && rolDigitado!= null){
            for (RolesGuarda r:RolesGuarda.values()){
                if(r.name().equals(rolDigitado.toUpperCase())){
                    return true;
                }
            }
            return false;
        }
        if("aseo".equals(rol) && rolDigitado != null){
            for (RolesAseo r : RolesAseo.values()) {
                if (r.name().equals(rolDigitado.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static Boolean esNumeroDocumentoValido(Integer numeroDocumento){
        if (numeroDocumento == null){
            return false;
        }
        int longitudNumeroDocumento = numeroDocumento.toString().length();
        return longitudNumeroDocumento >= 6 && longitudNumeroDocumento <= 11;
    }

}
