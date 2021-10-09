/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DTO.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rozo
 */
public class Utileria {
    
    
    
    
    public static Map<String, String> usuarioToMap(Usuario user) {
        Map<String, String> map = new HashMap<>();
        map.put("idUsuario", user.getIdUsuario().toString());
        map.put("user", user.getUser());
        map.put("fecCreacion", dateToString(user.getFechaCreacion()));
        map.put("TipoUsuario", user.getIdTipoUsuario().getDesTipoUsuario());
        map.put("nombres", user.getIdPersona().getNombres());
        map.put("apellido1", user.getIdPersona().getApellido1());
        map.put("apellido2", user.getIdPersona().getApellido2());
        map.put("fecNacimiento", dateToString(user.getIdPersona().getFechaNac()));
        map.put("tipoDocumento", user.getIdPersona().getTipoDoc());
        map.put("numDocumento", user.getIdPersona().getNumeroDoc()+ "");
       // map.put("genero", user.getIdPersona().getGenero(); cambiar a string
        map.put("direccion", user.getIdPersona().getDireccion());
        map.put("email", user.getIdPersona().getEmail());
        //cambiar a string
        map.put("telefono1", user.getIdPersona().getTelefono1()+"");
        map.put("telefono2", user.getIdPersona().getTelefono2()+"");
        //map.put("activo", user.getActivo() + ""); crear en la bd
        map.put("contra", user.getPassword());

        return map;
    }
    
   private static String dateToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(date);
    }
}
